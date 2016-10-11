/*
 * Copyright (C) 2016  Arun Kumar Selvaraj

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package org.ppythagoras.proteus.core.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility methods to work on Json objects and arrays.
 * 
 * @author Arun Kumar Selvaraj
 *
 */
public class JSONUtils implements Cloneable, Serializable {

	/**
	 * Get list of values for a specified search key field.
	 * 
	 * @param input
	 * @param searchKey
	 * @return
	 */
	public static List<Object> getValue(JSONObject input, String searchKey) {

		List<Object> returnList = new ArrayList<Object>();

		Object currentJsonElement = input;

		for (String key : searchKey.split("\\.")) {
			currentJsonElement = pullDataFromJson(currentJsonElement, key);
		}

		List<Object> intermediateList = jsonToObjectArray(currentJsonElement);

		for (Object obj : intermediateList) {
			returnList.add(obj);
		}

		return returnList;
	}

	/**
	 * Recursive algorithm to get the value from a deeply nested Json structure.
	 * 
	 * @param inputObject
	 * @param key
	 * @return
	 */
	private static Object pullDataFromJson(Object inputObject, String key) {

		Object returnObject = null;

		try {

			if (inputObject instanceof JSONObject) {
				JSONObject jsonObject = (JSONObject) inputObject;
				if (jsonObject.has(key)) {
					returnObject = jsonObject.get(key);
				}

			} else if (inputObject instanceof JSONArray) {

				JSONArray jsonArray = (JSONArray) inputObject;

				JSONArray returnArray = new JSONArray();

				for (int j = 0; j < jsonArray.length(); j++) {

					Object obj = jsonArray.get(j);

					Object obj2 = pullDataFromJson(obj, key);

					if (obj2 instanceof JSONObject) {
						returnArray.put(obj2);
					} else if (obj2 instanceof JSONArray) {
						JSONArray arr2 = (JSONArray) obj2;
						for (int k = 0; k < arr2.length(); k++) {
							returnArray.put(arr2.get(k));
						}
					} else {
						returnArray.put(obj2);
					}
				}

				returnObject = returnArray;

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return returnObject;
	}

	/**
	 * Convert Json object or Json array into a list of objects
	 * 
	 * @param object
	 * @return
	 */
	private static List<Object> jsonToObjectArray(Object object) {
		List<Object> returnList = new ArrayList<Object>();
		if (object != null) {
			if (object instanceof JSONArray) {
				JSONArray array = (JSONArray) object;
				if (array == null)
					return null;

				for (int i = 0; i < array.length(); i++) {
					List<Object> intermediate;
					if (!array.isNull(i)) {
						try {
							intermediate = jsonToObjectArray(array.get(i));
							returnList.addAll(intermediate);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
			} else if (object instanceof JSONObject) {

				returnList.add(object);

			} else {
				returnList.add(object);
			}
		}
		return returnList;
	}

	/**
	 * Merge two Json objects.
	 * 
	 * @param copyTo
	 * @param copyFrom
	 */
	public static void putAll(JSONObject copyTo, JSONObject copyFrom) {
		try {
			JSONObject inputObject = (JSONObject) copyFrom;
			for (String key : JSONObject.getNames(inputObject)) {
				put(copyTo, key, inputObject.get(key));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add a key value pair to a Json object. This method is to add any
	 * additional logic that may be required in the future to be applied before
	 * ading an element into Json.
	 * 
	 * @param input
	 * @param key
	 * @param value
	 */
	public static void put(JSONObject input, String key, Object value) {
		if (value instanceof JSONObject) {
			try {
				input.put(key, value);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
