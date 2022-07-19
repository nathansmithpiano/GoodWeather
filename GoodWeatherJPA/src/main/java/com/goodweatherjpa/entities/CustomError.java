package com.goodweatherjpa.entities;

import java.util.Objects;

import org.json.JSONObject;

import com.goodweatherjpa.settings.Settings;

public class CustomError {
	
	private int id;
	private String origin;
	private String message;
	
	public CustomError() {
		super();
	}

	public CustomError(String origin, String message) {
		super();
		this.origin = origin;
		this.message = message;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, origin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomError other = (CustomError) obj;
		return Objects.equals(message, other.message) && Objects.equals(origin, other.origin);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName() + " ");
		
		JSONObject jsonObject = new JSONObject(this);
		builder.append(jsonObject.toString(Settings.JSON_TOSTRING_SPACES));
		
		return builder.toString();
	}
	
	

}
