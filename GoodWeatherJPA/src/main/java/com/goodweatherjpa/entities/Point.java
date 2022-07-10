package com.goodweatherjpa.entities;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.json.JSONObject;

import com.goodweatherjpa.settings.Settings;

@Entity
public class Point {

	@Id
	private String uri;

	// ----- RELATIONSHIPS -----

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_name")
	private Location location;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "coordinates_id")
	private Coordinates coordinates;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "geometry_id")
	private Geometry geometry;

	public Point() {
		super();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	// ----- RELATIONSHIPS -----

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uri);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return Objects.equals(uri, other.uri);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName() + " ");
		
		JSONObject jsonObject = new JSONObject(this);
		builder.append(jsonObject.toString(Settings.JSON_TOSTRING_SPACES));
		
		return builder.toString();
		
//		StringBuilder builder = new StringBuilder();
//		builder.append(getClass().getSimpleName());
//		builder.append(" {\n\turi: ");
//		builder.append(uri);
//
//		// ----- RELATIONSHIPS -----
//
//		if (location == null) {
//			builder.append("\n\tlocation: null");
//		} else {
//			builder.append("\n\tlocation.getName(): ");
//			builder.append(location.getName());
//		}
//
//		builder.append("\n\tcoordinates: ");
//		builder.append(coordinates);
//		builder.append("\n\tgeometry: ");
//		builder.append(geometry);
//		builder.append("\n}");
//		return builder.toString();
	}

}
