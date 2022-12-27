package com.juan.estevez.app.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_DOCTOR")
	private String idDoctor;
	
	@Column(name = "DOCTORS_NAME")
	private String doctorsName;
	
	@Column(name = "ID_TYPE")
	private String idType;
	
	@Column(name = "NUMBER_PROFESSIONAL_CARD")
	private String numberProfessionalCard;
	
	@Column(name = "SPECIALTY")
	private String specialty;
	
	@Column(name = "YEARS_EXPERIENCE")
	private int yearsExperience;
	
	@Column(name = "ATTENTION_START_TIME")
	private int attentionStartTime;
	
	@Column(name = "ATTENTION_END_TIME")
	private int attentionEndTime;

	public Doctor() {
	}

	public Doctor(String idDoctor, String doctorName, String idType, String numberProfessionalCard, String specialty,
			int yearsExperience, int attentionStartTime, int attentionEndTime) {
		this.idDoctor = idDoctor;
		this.doctorsName = doctorName;
		this.idType = idType;
		this.numberProfessionalCard = numberProfessionalCard;
		this.specialty = specialty;
		this.yearsExperience = yearsExperience;
		this.attentionStartTime = attentionStartTime;
		this.attentionEndTime = attentionEndTime;
	}

	public String getIsDoctor() {
		return idDoctor;
	}

	public void setIsDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getDoctorName() {
		return doctorsName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorsName = doctorName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getNumberProfessionalCard() {
		return numberProfessionalCard;
	}

	public void setNumberProfessionalCard(String numberProfessionalCard) {
		this.numberProfessionalCard = numberProfessionalCard;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getYearsExperience() {
		return yearsExperience;
	}

	public void setYearsExperience(int yearsExperience) {
		this.yearsExperience = yearsExperience;
	}

	public int getAttentionStartTime() {
		return attentionStartTime;
	}

	public void setAttentionStartTime(int attentionStartTime) {
		this.attentionStartTime = attentionStartTime;
	}

	public int getAttentionEndTime() {
		return attentionEndTime;
	}

	public void setAttentionEndTime(int attentionEndTime) {
		this.attentionEndTime = attentionEndTime;
	}

	@Override
	public String toString() {
		return "Doctor [idDoctor=" + idDoctor + ", doctorName=" + doctorsName + ", idType=" + idType
				+ ", numberProfessionalCard=" + numberProfessionalCard + ", specialty=" + specialty
				+ ", yearsExperience=" + yearsExperience + ", attentionStartTime=" + attentionStartTime
				+ ", attentionEndTime=" + attentionEndTime + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attentionEndTime, attentionStartTime, doctorsName, idType, idDoctor, numberProfessionalCard,
				specialty, yearsExperience);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return attentionEndTime == other.attentionEndTime && attentionStartTime == other.attentionStartTime
				&& Objects.equals(doctorsName, other.doctorsName) && Objects.equals(idType, other.idType)
				&& Objects.equals(idDoctor, other.idDoctor)
				&& Objects.equals(numberProfessionalCard, other.numberProfessionalCard)
				&& Objects.equals(specialty, other.specialty) && yearsExperience == other.yearsExperience;
	}

}
