SELECT appointment.id, date_time, personnel.id AS MID, personnel.first_name AS md_first_name, personnel.middle_name AS md_middle_name, personnel.last_name AS md_last_name, patient.id AS MRID, patient.first_name AS pat_first_name, patient.middle_name AS pat_middle_name, patient.last_name AS pat_last_nameFROM medical_service_db.appointment AS appointment LEFT JOIN medical_service_db.patient AS patient ON patient.id = appointment.patient_id LEFT JOIN medical_service_db.medical_personnel AS personnel ON personnel.id = appointment.medical_personnel_id;