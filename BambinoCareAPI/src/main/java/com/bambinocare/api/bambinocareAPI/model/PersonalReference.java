package com.bambinocare.api.bambinocareAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "personal_reference")
public class PersonalReference {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reference_id", nullable = false, unique = true)
	private Integer referenceId;

	@JoinColumn(name = "bambinaia_id", nullable = false)
	@OneToOne(fetch = FetchType.EAGER)
	private Bambinaia bambinaia;

	@Column(name = "firstname", nullable = false)
	private String firstname;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "how_long_known", nullable = false)
	private String how_long_known;

	@Column(name = "relationship", nullable = false)
	private String relationship;

	public PersonalReference(Integer referenceId, Bambinaia bambinaia, String firstname, String lastname,
			String phone, String how_long_known, String relationship) {
		super();
		this.referenceId = referenceId;
		this.bambinaia = bambinaia;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.how_long_known = how_long_known;
		this.relationship = relationship;
	}

	public PersonalReference() {
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	public Bambinaia getBambinaia() {
		return bambinaia;
	}

	public void setBambinaia(Bambinaia bambinaia) {
		this.bambinaia = bambinaia;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHow_long_known() {
		return how_long_known;
	}

	public void setHow_long_known(String how_long_known) {
		this.how_long_known = how_long_known;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

}