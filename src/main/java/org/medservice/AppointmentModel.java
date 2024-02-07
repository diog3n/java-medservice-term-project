package org.medservice;

import java.util.HashMap;

public class AppointmentModel extends DataModel {
    private int appointmentId;
    private String dateTime;
    private int personnelId;
    private String mdFirstName;
    private String mdMiddleName;
    private String mdLastName;
    private int patientId;
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    public static String[] columnNames;

    public static HashMap<String, String> columnNamesToSql = new HashMap<>();
    // Инициализация статических полей
    static {
        columnNamesToSql.putIfAbsent("ID", "appointment.id");
        columnNamesToSql.putIfAbsent("Date and time", "date_time");
        columnNamesToSql.putIfAbsent("MDID", "MID");
        columnNamesToSql.putIfAbsent("MD First Name", "md_first_name");
        columnNamesToSql.putIfAbsent("MD Middle name", "md_middle_name");
        columnNamesToSql.putIfAbsent("MD Last Name", "md_last_name");
        columnNamesToSql.putIfAbsent("Patient ID", "MRID");
        columnNamesToSql.putIfAbsent("Pat. First Name", "pat_first_name");
        columnNamesToSql.putIfAbsent("Pat. Middle Name", "pat_middle_name");
        columnNamesToSql.putIfAbsent("Pat. Last Name", "pat_last_name");

        columnNames = new String[] {
                "ID",
                "Date and time",
                "MDID",
                "MD First name",
                "MD Middle name",
                "MD Last name",
                "Patient ID",
                "Pat. First Name",
                "Pat. Middle name",
                "Pat. Last name"
        };
    }
    // Конструкторы
    public AppointmentModel(int appointmentId,
                            String dateTime,
                            int personnelId,
                            String mdFirstName,
                            String mdMiddleName,
                            String mdLastName,
                            int patientId,
                            String patientFirstName,
                            String patientMiddleName,
                            String patientLastName) {
        this.appointmentId = appointmentId;
        this.dateTime = dateTime;
        this.personnelId = personnelId;
        this.mdFirstName = mdFirstName;
        this.mdMiddleName = mdMiddleName;
        this.mdLastName = mdLastName;
        this.patientId = patientId;
        this.patientFirstName = patientFirstName;
        this.patientMiddleName = patientMiddleName;
        this.patientLastName = patientLastName;
    }

    // Геттеры и сеттеры
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(int personnelId) {
        this.personnelId = personnelId;
    }

    public String getMdFirstName() {
        return mdFirstName;
    }

    public void setMdFirstName(String mdFirstName) {
        this.mdFirstName = mdFirstName;
    }

    public String getMdMiddleName() {
        return mdMiddleName;
    }

    public void setMdMiddleName(String mdMiddleName) {
        this.mdMiddleName = mdMiddleName;
    }

    public String getMdLastName() {
        return mdLastName;
    }

    public void setMdLastName(String mdLastName) {
        this.mdLastName = mdLastName;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    @Override
    public String toString() {
        return "AppointmentModel{" +
                "appointmentId=" + appointmentId +
                ", dateTime='" + dateTime + '\'' +
                ", personnelId=" + personnelId +
                ", mdFirstName='" + mdFirstName + '\'' +
                ", mdMiddleName='" + mdMiddleName + '\'' +
                ", mdLastName='" + mdLastName + '\'' +
                ", patientId=" + patientId +
                ", patientFirstName='" + patientFirstName + '\'' +
                ", patientMiddleName='" + patientMiddleName + '\'' +
                ", patientLastName='" + patientLastName + '\'' +
                '}';
    }
}
