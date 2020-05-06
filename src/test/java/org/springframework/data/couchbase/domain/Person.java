package org.springframework.data.couchbase.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class Person extends AbstractEntity {
	Optional<String> firstname;
	Optional<String> lastname;

	@CreatedBy private String creator;

	@LastModifiedBy private String lastModifiedBy;

	@LastModifiedDate private long lastModification;

	@CreatedDate private long creationDate; // =System.currentTimeMillis();

	@Version private long version;

	public Person() {}

	public Person(String firstname, String lastname) {
		this();
		setFirstname(firstname);
		setLastname(lastname);
	}

	public Person(int id, String firstname, String lastname) {
		this(firstname, lastname);
		setId(new UUID(id, id));
	}

	static String optional(String name, Optional<String> obj) {
		if (obj != null)
			if (obj.isPresent())
				return ("  " + name + ": '" + obj.get() + "'\n");
			else
				return "  " + name + ": null\n";
		return "";
	}

	public Optional<String> getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname == null ? null : (Optional.ofNullable(firstname.equals("") ? null : firstname));
	}

	public void setFirstname(Optional<String> firstname) {
		this.firstname = firstname;
	}

	public Optional<String> getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname == null ? null : (Optional.ofNullable(lastname.equals("") ? null : lastname));
	}

	public void setLastname(Optional lastname) {
		this.lastname = lastname;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Person : {\n");
		sb.append("  id : " + getId());
		sb.append(optional(", firstname", firstname));
		sb.append(optional(", lastname", lastname));
		sb.append(", version : " + version);
		if (creator != null)
			sb.append(", creator : " + creator);
		if (creationDate != 0)
			sb.append(", creationDate : " + creationDate);
		if (lastModifiedBy != null)
			sb.append(", lastModifiedBy : " + lastModifiedBy);
		if (lastModification != 0)
			sb.append(", lastModification : " + lastModification);
		sb.append("}");
		return sb.toString();
	}
}