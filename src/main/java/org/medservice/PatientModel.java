package org.medservice;

import java.util.HashMap;

public class PatientModel extends DataModel {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
    private String homeAddress;
    public static String[] columnNames;

    public static HashMap<String, String> columnNamesToSql = new HashMap<>();

    // Инициализация статических полей
    static {
        columnNamesToSql.putIfAbsent("ID", "id");
        columnNamesToSql.putIfAbsent("First name", "first_name");
        columnNamesToSql.putIfAbsent("Middle name", "middle_name");
        columnNamesToSql.putIfAbsent("Last Name", "last_name");
        columnNamesToSql.putIfAbsent("Date of birth", "date_of_birth");
        columnNamesToSql.putIfAbsent("Home address", "home_address");
        columnNames = new String[] {
                "ID",
                "First name",
                "Middle name",
                "Last Name",
                "Date of birth",
                "Home address"
        };
    }
    // Конструкторы
    public PatientModel(int id, String firstName, String middleName, String lastName, String dateOfBirth, String homeAddress) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
    }
    public PatientModel() {
        this(-1, "", "", "", "", "");
    }
    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "Patient:" +
                "\nId =" + id +
                "\nFirst Name ='" + firstName + '\'' +
                "\nMiddle Name ='" + middleName + '\'' +
                "\nLast Name ='" + lastName + '\'' +
                "\nDate Of Birth =" + dateOfBirth +
                "\nHome Address ='" + homeAddress + '\'';
    }
}
