package com.goodweatherjpa.dbtests;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "string_entity")
public class StringEntity {

	// Sample URI: https://api.weather.gov/points/39.9884,-105.2336
	// 48 characters long

	// Testing String: 50 random characters
	
	@Id
	private String id;

	private String contents;

	public StringEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringEntity other = (StringEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getName());
		builder.append(" {\n\tid: ");
		builder.append(id);
		builder.append("\n\tcontents: ");
		builder.append(contents);
		builder.append("\n}");
		return builder.toString();
	}

}
