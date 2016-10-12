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

package org.ppythagoras.proteus.core.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.ppythagoras.proteus.core.constants.Constants;
import org.ppythagoras.proteus.core.exception.ProteusException;
import org.ppythagoras.proteus.core.utils.JSONUtils;

/**
 * Tranformation logic implementation.
 * 
 * @author Arun Kumar Selvaraj
 *
 */
public class Transformer {

	public static JSONObject transformImpl(JSONObject input, JSONObject template) throws JSONException {
		Iterator keys = template.keys();

		while (keys.hasNext()) {
			String key = (String) keys.next();
			Object value = template.opt(key);

			if (value instanceof JSONObject) {
				transformImpl(input, (JSONObject) value);
			}

			else if (value instanceof String) {

				List<Object> list;
				String findKey = (String) value;

				if (findKey.startsWith(Constants._AS_SET)) {
					Map<String, List<Object>> intermediateMap = new HashMap();
					int referenceLength = 0;
					for (String subkey : findKey.substring(7, findKey.length() - 1).split(",")) {

						String[] parts = subkey.split(Constants.AS_SPACE);

						String alias = null;

						if (parts.length == 2) {
							alias = parts[1];
						} else {
							alias = parts[0].substring(parts[0].lastIndexOf(Constants.DOT) + 1);
						}

						List<Object> intermediateList = JSONUtils.getValue(input, parts[0]);

						referenceLength = intermediateList.size();

						intermediateMap.put(alias, intermediateList);
					}
					list = concatenate(intermediateMap, referenceLength);
				} else {
					list = JSONUtils.getValue(input, findKey);
				}

				if (list != null && list.size() == 0) {
					template.put(key, "");
				} else if (list != null && list.size() == 1) {
					template.put(key, list.get(0));
				} else {
					template.put(key, list);
				}
			}

		}

		return template;

	}

	/**
	 * Groups multiple lists of objects into a single list of Json objects with
	 * list name as keys of the Json object.
	 * 
	 * @param intermediateMap
	 * @param referenceLength
	 * @return
	 */
	private static List<Object> concatenate(Map<String, List<Object>> intermediateMap, int referenceLength) {

		boolean mergable = true;

		for (String key : intermediateMap.keySet()) {
			if (intermediateMap.get(key).size() != referenceLength) {
				mergable = false;
				break;
			}
		}

		List<Object> returnList = new ArrayList();

		if (mergable) {

			for (int i = 0; i < referenceLength; i++) {
				JSONObject json = new JSONObject();
				for (String key : intermediateMap.keySet()) {
					try {
						json.put(key, intermediateMap.get(key).get(i));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				returnList.add(json);
			}

		} else {
			throw new ProteusException(
					"Reference keys cannot be combined as a set. Number of values for each key does not match");
		}

		return returnList;
	}
}
