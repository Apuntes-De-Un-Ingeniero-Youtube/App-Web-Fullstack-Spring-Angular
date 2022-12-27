package com.juan.estevez.app.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PATIENT")
	private String idPatient;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DATE_OF_BIRTH")
	private String dateOfBirth;
	
	@Column(name = "ID_TYPE")
	private String idType;
	
	@Column(name = "EPS")
	private String eps;
	
	@Column(name = "CLINIC_HISTORY")
	private String historyClinic;


	public Patient(String idPatient, String historyClinic, String dateOfBirth, String eps, String idType, String name) {

		this.idPatient = idPatient;
		this.historyClinic = historyClinic;
		this.dateOfBirth = dateOfBirth;
		this.eps = eps;
		this.idType = idType;
		this.name = name;
	}

	public Patient() {
	}

	public String getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}

	public String getHistoryClinic() {
		return historyClinic;
	}

	public void setHistoryClinic(String historyClinic) {
		this.historyClinic = historyClinic;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, eps, historyClinic, idPatient, idType, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(eps, other.eps)
				&& Objects.equals(historyClinic, other.historyClinic) && Objects.equals(idPatient, other.idPatient)
				&& Objects.equals(idType, other.idType) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Patient [idPatient=" + idPatient + ", historyClinic=" + historyClinic + ", dateOfBirth=" + dateOfBirth
				+ ", eps=" + eps + ", idType=" + idType + ", name=" + name + "]";
	}

}
