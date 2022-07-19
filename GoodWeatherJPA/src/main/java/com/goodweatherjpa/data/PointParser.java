package com.goodweatherjpa.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.goodweatherjpa.entities.Point;

public class PointParser {

	public Point parse(String endpoint, double latitude, double longitude) {
		
		// for debugging
		JsonVerifier verifier = new JsonVerifier();
		
		// initialize, register, and configure
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("PointDeserializer", new Version(1, 0, 0, null, null, null));
		module.addDeserializer(Point.class, new PointDeserializer());
		mapper.registerModule(module);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Point point = null;
		
		String urlString = endpoint + latitude + "," + longitude;

		try {
			point = mapper.readValue(new URL(urlString), Point.class);
		} catch (MalformedURLException e) {
//			errorType = e.getClass().getSimpleName();
			e.printStackTrace();
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return point;
	}

}
