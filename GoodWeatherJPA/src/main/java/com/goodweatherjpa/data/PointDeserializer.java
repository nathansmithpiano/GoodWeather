package com.goodweatherjpa.data;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.goodweatherjpa.entities.Point;

public class PointDeserializer extends StdDeserializer<Point> {

	private static final long serialVersionUID = 1L;
	JsonVerifier verifier;

	public PointDeserializer() {
		this(null);
	}

	public PointDeserializer(Class<Point> vc) {
		super(vc);
		verifier = new JsonVerifier();
		verifier.setOrigin(this.getClass().toGenericString());
	}

	@Override
	public Point deserialize(JsonParser parser, DeserializationContext deserializer) {

		// initialize
		ObjectCodec codec = parser.getCodec();
		JsonNode node = null;
		Point point = null;

		// attempt to parse into JsonNode
		try {
			node = codec.readTree(parser);
		} catch (IOException e) {
			e.printStackTrace();
		}

		point = createPoint(node);

		return point;
	}
	
	private Point createPoint(JsonNode node) {
		Point entity = new Point();
		String prop = null;
		String propParent = null;
		
		// id (uri, PK)
		prop = "id";
		if (verifier.verifyHasAndIsUrl(node, prop)) {
			entity.setUri(node.get(prop).asText());
			
		}
		
		return entity;
	}

}
