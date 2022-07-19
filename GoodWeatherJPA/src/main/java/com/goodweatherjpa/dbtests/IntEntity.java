package com.goodweatherjpa.dbtests;

import java.util.Objects;

public class IntEntity {

	private int id;

	private String contents;

	public IntEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		IntEntity other = (IntEntity) obj;
		return id == other.id;
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
