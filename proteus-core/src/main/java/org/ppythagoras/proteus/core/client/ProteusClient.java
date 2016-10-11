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

package org.ppythagoras.proteus.core.client;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Client instance that will act as interface for this library.
 * 
 * @author Arun Kumar Selvaraj
 *
 */
public class ProteusClient {

	private ProteusClientImpl clientImpl;

	private ProteusClient() {

		super();

		clientImpl = new ProteusClientImpl();

	}

	public static ProteusClient getInstance() {

		ProteusClient client = new ProteusClient();

		return client;

	}

	/**
	 * Endpoint method for transforming a Json object based on a template text.
	 * 
	 * @param input
	 * @param templateText
	 * @return
	 */
	public JSONObject transform(JSONObject input, String templateText) {
		return ProteusClientImpl.transformImpl(input, templateText);
	}

	/**
	 * Endpoint method for transforming a Json object based on a Json template.
	 * 
	 * @param input
	 * @param template
	 * @return
	 */
	public JSONObject transform(JSONObject input, JSONObject template) {
		return transform(input, template.toString());
	}

	/**
	 * Endpoint method for transforming list of Json objects based on a template
	 * text.
	 * 
	 * @param input
	 * @param templateText
	 * @return
	 */
	public List<JSONObject> transform(List<JSONObject> input, String templateText) {

		List<JSONObject> output = new ArrayList();

		for (JSONObject item : input) {
			output.add(transform(item, templateText));
		}

		return output;
	}

	/**
	 * Endpoint method for transforming list of Json objects based on a Json
	 * template.
	 * 
	 * @param input
	 * @param template
	 * @return
	 */
	public List<JSONObject> transform(List<JSONObject> input, JSONObject template) {
		return transform(input, template.toString());
	}

	/**
	 * Endpoint method for transforming a Json array based on a template text.
	 * 
	 * @param input
	 * @param templateText
	 * @return
	 */
	public JSONArray transform(JSONArray input, String templateText) {

		JSONArray output = new JSONArray();

		for (int i = 0; i < input.length(); i++) {
			JSONObject item = input.optJSONObject(i);
			output.put(transform(item, templateText));
		}

		return output;
	}

	/**
	 * Endpoint method for transforming a Json array based on a Json template.
	 * 
	 * @param input
	 * @param template
	 * @return
	 */
	public JSONArray transform(JSONArray input, JSONObject template) {
		return transform(input, template.toString());
	}

}
