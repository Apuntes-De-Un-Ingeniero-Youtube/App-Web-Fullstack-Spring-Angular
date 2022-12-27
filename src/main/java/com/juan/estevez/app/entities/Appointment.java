package com.juan.estevez.app.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_APPOINTMENT")
	private int idAppointment;
	
	@Column(name = "DOCTOR")
	private String doctor;
	
	@Column(name = "PATIENT")
	private String patient;
	
	@Column(name = "FECHA")
	private String date;
	
	@Column(name = "HOUR")
	private int hour;	

	public Appointment() {

	}

	public Appointment(int idAppointment, String doctor, String patient, String date, int hour) {
		super();
		this.idAppointment = idAppointment;
		this.doctor = doctor;
		this.patient = patient;
		this.date = date;
		this.hour = hour;
	}

	public int getIdAppointment() {
		return idAppointment;
	}

	public void setIdAppointment(int idAppointment) {
		this.idAppointment = idAppointment;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, doctor, hour, idAppointment, patient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(date, other.date) && Objects.equals(doctor, other.doctor) && hour == other.hour
				&& idAppointment == other.idAppointment && Objects.equals(patient, other.patient);
	}

	@Override
	public String toString() {
		return "Appointment [idAppointment=" + idAppointment + ", doctor=" + doctor + ", patient=" + patient + ", date="
				+ date + ", hour=" + hour + "]";
	}

}
