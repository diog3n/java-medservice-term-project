CREATE SCHEMA medical_service_db;

CREATE  TABLE medical_service_db.medical_personnel ( 
	id                   INT UNSIGNED NOT NULL   AUTO_INCREMENT  PRIMARY KEY,
	first_name           VARCHAR(100)  NOT NULL     ,
	middle_name          VARCHAR(100)  NOT NULL     ,
	last_name            VARCHAR(100)  NOT NULL     ,
	speciality           VARCHAR(100)  NOT NULL     ,
	room_number          INT UNSIGNED NOT NULL     
 ) engine=InnoDB;

CREATE  TABLE medical_service_db.patient ( 
	id                   INT UNSIGNED NOT NULL   AUTO_INCREMENT  PRIMARY KEY,
	first_name           VARCHAR(100)  NOT NULL     ,
	middle_name          VARCHAR(100)  NOT NULL     ,
	last_name            VARCHAR(100)  NOT NULL     ,
	date_of_birth        DATE  NOT NULL DEFAULT (CURRENT_TIMESTAMP)    ,
	home_address         VARCHAR(256)  NOT NULL     
 ) engine=InnoDB;

CREATE  TABLE medical_service_db.appointment ( 
	id                   INT UNSIGNED NOT NULL   AUTO_INCREMENT  PRIMARY KEY,
	date_time            DATETIME  NOT NULL DEFAULT (CURRENT_TIMESTAMP)    ,
	medical_personnel_id INT UNSIGNED NOT NULL     ,
	patient_id           INT UNSIGNED NOT NULL     ,
	CONSTRAINT fk_appointment_to_personnel FOREIGN KEY ( medical_personnel_id ) REFERENCES medical_service_db.medical_personnel( id ) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_appointment_to_patient FOREIGN KEY ( patient_id ) REFERENCES medical_service_db.patient( id ) ON DELETE CASCADE ON UPDATE CASCADE
 ) engine=InnoDB;

ALTER TABLE medical_service_db.patient COMMENT 'Represents a patient';

