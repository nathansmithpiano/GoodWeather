package com.goodweatherjpa.data;

import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonVerifier {

	private ErrorLogger logger;
	private String origin;

	public JsonVerifier() {
		logger = new ErrorLogger();
		logger.setOrigin(origin);
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public boolean verifyHas(JsonNode node, String prop) {
		// verify node has property
		if (node.has(prop)) {
			return true;
		} else {
			logger.log("node is missing property: " + prop);
		}
		return false;
	}

	public boolean verifyHasAndIsTextual(JsonNode node, String prop) {
		// verify node has property and is textual
		if (verifyHas(node, prop)) {
			if (node.get(prop).isTextual()) {
				return true;
			} else {
				logger.log("property (" + prop + ") is not textual: " + node.get(prop).toString());
			}
		}
		return false;
	}
	
	public boolean verifyHasAndIsUrl(JsonNode node, String prop) {
		// verify node has property, is textual, and is URL
		if (verifyHasAndIsTextual(node, prop)) {
			// attempt to create URL, return false if exception thrown
			String errorType = null;
			try {
				new URL(node.get(prop).asText());
			} catch (MalformedURLException e) {
				errorType = e.getClass().getSimpleName();
			}
			
			if (errorType == null) {
				return true;
			} else {
				logger.log("property (" + prop + ") is not a URL: " + node.get(prop).toString());
			}
		}
		return false;
	}

}
