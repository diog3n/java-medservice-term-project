package org.medservice;

import java.util.HashMap;

public class MdModel extends DataModel {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String speciality;
    private int roomNum;
    public static String[] columnNames;
    public static HashMap<String, String> columnNamesToSql = new HashMap<>();

    // Инициализация статических полей
    static {
        columnNamesToSql.putIfAbsent("ID", "id");
        columnNamesToSql.putIfAbsent("First name", "first_name");
        columnNamesToSql.putIfAbsent("Middle name", "middle_name");
        columnNamesToSql.putIfAbsent("Last Name", "last_name");
        columnNamesToSql.putIfAbsent("Speciality", "speciality");
        columnNamesToSql.putIfAbsent("Room", "room_number");
        columnNames = new String[] {
                "ID",
                "First name",
                "Middle name",
                "Last Name",
                "Speciality",
                "Room"
        };
    }
    // Конструкторы
    public MdModel(int id, String firstName, String middleName, String lastName, String speciality, int roomNum) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.roomNum = roomNum;
    }

    public MdModel() {
        this(-1, "", "", "", "", -1);
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    @Override
    public String toString() {
        return "MdModel:" +
                "\nId ="  + id +
                "\nFirst Name ='" + firstName + '\'' +
                "\nMiddle Name ='" + middleName + '\'' +
                "\nLast Name ='" + lastName + '\'' +
                "\nSpeciality ='" + speciality + '\'' +
                "\nRoom Number =" + roomNum;
    }
}
