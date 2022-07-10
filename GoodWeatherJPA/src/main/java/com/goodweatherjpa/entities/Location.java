package com.goodweatherjpa.entities;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Location {

	@Id
	private String name;

	// ----- RELATIONSHIPS -----

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "coordinates_id")
	private Coordinates coordinates;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// ----- RELATIONSHIPS -----

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(" {\n\tname: ");
		builder.append(name);
		builder.append("\n\tcoordinates: ");
		builder.append(coordinates);
		builder.append("\n}");
		return builder.toString();
	}
	
	

}
