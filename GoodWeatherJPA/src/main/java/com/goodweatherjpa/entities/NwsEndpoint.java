package com.goodweatherjpa.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

import com.goodweatherjpa.settings.Settings;

@Entity
@Table(name = "nws_endpoint")
public class NwsEndpoint {
	
	@Id
	private String type;
	
	private String endpoint;

	public NwsEndpoint() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NwsEndpoint other = (NwsEndpoint) obj;
		return Objects.equals(type, other.type);
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
