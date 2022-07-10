package com.goodweatherjpa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "geometry")
public class Geometry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;

	// ----- RELATIONSHIPS -----

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "geometry_coordinates", joinColumns = {
			@JoinColumn(name = "geometry_id") }, inverseJoinColumns = { @JoinColumn(name = "coordinates_id") })
	private List<Coordinates> coordinates;

	public Geometry() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// ----- RELATIONSHIPS -----

	public List<Coordinates> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Coordinates> coordinates) {
		this.coordinates = coordinates;
	}

	public void addCoordinates(Coordinates coordinates) {
		if (this.coordinates == null) {
			this.coordinates = new ArrayList<>();
		}
		this.coordinates.add(coordinates);
	}

	public void removeCoordinates(Coordinates coordinates) {
		if (this.coordinates != null) {
			this.coordinates.remove(coordinates);
		}
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
		Geometry other = (Geometry) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(" {\n\tid: ");
		builder.append(id);
		builder.append("\n\ttype: ");
		builder.append(type);
		
		builder.append("\n\tcoordinates: ");
		builder.append(coordinates);
		
		builder.append("\n}");
		return builder.toString();
	}

}
