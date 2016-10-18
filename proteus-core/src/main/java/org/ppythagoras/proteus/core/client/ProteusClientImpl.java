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

import org.json.JSONException;
import org.json.JSONObject;
import org.ppythagoras.proteus.core.processor.Transformer;

/**
 * Client implementation.
 * 
 * @author Arun Kumar Selvaraj
 *
 */
class ProteusClientImpl {

	ProteusClientImpl() {
		super();
	}

	static JSONObject transformImpl(JSONObject input, String templateText) {

		JSONObject template = null;

		try {

			template = Transformer.transformImpl(input, new JSONObject(templateText));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return template;
	}
}
