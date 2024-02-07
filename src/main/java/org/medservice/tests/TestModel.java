package org.medservice.tests;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class TestModel {
    private DefaultTableModel peopleTableModel = new DefaultTableModel() {
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int index) {
            return columnNames[index];
        }
    };
    private final String[] columnNames = {"Id", "First name", "Second name", "Age"};
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String DB_COLLECT_ALL_QUERY = "SELECT * FROM test_table;";

    public String[] getColumnNames() {
        return columnNames;
    }

    public DefaultTableModel getPeopleTableModel() {
        return peopleTableModel;
    }

    public Connection getConnection() {
        return connection;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    // private JTable table;
    private Connection connection;
    private ArrayList<Person> people = new ArrayList<>();

    public TestModel(String dbUsername, String dbPassword) {
        connect(dbUsername, dbPassword);
        collectData();
        printPeople();

        populateTableModel();
        JTable table = new JTable(peopleTableModel);
        new TestForm(peopleTableModel);
    }

    private void populateTableModel() {
        for (var person : people) {
            Object[] row = {
                    person.getId(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getAge()
            };
            peopleTableModel.addRow(row);
        }
    }

    public void connect(String dbUsername, String dbPassword) {
        try {
            this.connection = DriverManager.getConnection(DB_URL, dbUsername, dbPassword);
            System.out.println(people);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public ResultSet runQuery(String dbQuery) {
        if (connection == null) {
            System.out.println("Connection is null");
            return null;
        }
        ResultSet rs;
        try {
            Statement st = this.connection.createStatement();
            rs = st.executeQuery(dbQuery);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
        return rs;
    }

    public void collectData() {
        try(ResultSet rs = runQuery(DB_COLLECT_ALL_QUERY)) {
            while (rs.next()) {
                people.add(new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"))
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    private void printPeople() {
        System.out.println(people);
    }
}
