package org.ppythagoras.proteus.core.examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ppythagoras.proteus.core.client.ProteusClient;

public class Example {

	public static void main(String[] args) throws JSONException {

		ProteusClient client = ProteusClient.getInstance();

		JSONObject outputObject = singleDocumentExample(client, "example-data/templates/example-template-basic.json");

		System.out.println("*********Single Document Demo - using JSONObject (Basic)*********");
		System.out.println(outputObject.toString(3));

		JSONArray outputArray = multiDocumentExample(client, "example-data/templates/example-template-basic.json");

		System.out.println("*********Multi Document Demo - using JSONArray (Basic)*********");
		System.out.println(outputArray.toString(3));

		outputObject = singleDocumentExample(client, "example-data/templates/example-template-advanced.json");

		System.out.println("*********Single Document Demo - using JSONObject (Advanced)*********");
		System.out.println(outputObject.toString(3));

		outputArray = multiDocumentExample(client, "example-data/templates/example-template-advanced.json");

		System.out.println("*********Multi Document Demo - using JSONArray (Advanced)*********");
		System.out.println(outputArray.toString(3));

		List<JSONArray> multipletemplateOutput = multiTemplateExample(client,
				"example-data/templates/example-template-basic.json",
				"example-data/templates/example-template-advanced.json");

		System.out.println("*********Multi Template Demo - using JSONArray (Advanced)*********");
		for (JSONArray json : multipletemplateOutput) {
			System.out.println(json.toString(3));
		}

	}

	/**
	 * Using single JSONObject as input
	 * 
	 * @param client
	 * @return
	 * @throws JSONException
	 */
	private static JSONObject singleDocumentExample(ProteusClient client, String templatePath) throws JSONException {

		JSONObject inputJson = new JSONObject(getJsonTextFromFile("example-data/input/example-jsonobject.json"));

		String templateJsonText = getJsonTextFromFile(templatePath);

		JSONObject output = client.transform(inputJson, templateJsonText);

		return output;

	}

	/**
	 * Using JSONArray as input for multiple documents input
	 * 
	 * @param client
	 * @return
	 * @throws JSONException
	 */
	private static JSONArray multiDocumentExample(ProteusClient client, String templatePath) throws JSONException {

		JSONArray inputJson = new JSONArray(getJsonTextFromFile("example-data/input/example-jsonarray.json"));

		String templateJsonText = getJsonTextFromFile(templatePath);

		JSONArray output = client.transform(inputJson, templateJsonText);

		return output;

	}

	/**
	 * Using JSONArray as input for multiple documents input
	 * 
	 * @param client
	 * @return
	 * @throws JSONException
	 */
	private static List<JSONArray> multiTemplateExample(ProteusClient client, String... templatePath)
			throws JSONException {

		JSONArray inputJson = new JSONArray(getJsonTextFromFile("example-data/input/example-jsonarray.json"));

		String[] templateJsonText = new String[templatePath.length];

		for (int i = 0; i < templatePath.length; i++) {
			templateJsonText[i] = getJsonTextFromFile(templatePath[i]);
		}

		List<JSONArray> output = client.transform(inputJson, templateJsonText);

		return output;

	}

	private static String getJsonTextFromFile(String filepath) {

		String text = null;

		try {

			File file = new File(filepath);
			byte[] bytes = Files.readAllBytes(file.toPath());

			text = new String(bytes, "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return text;
	}

}
