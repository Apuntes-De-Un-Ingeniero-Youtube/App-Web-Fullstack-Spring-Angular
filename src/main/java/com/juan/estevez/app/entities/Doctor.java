package com.juan.estevez.app.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "doctor")
@Data
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

}
