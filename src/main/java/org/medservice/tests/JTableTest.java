package org.medservice.tests;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.sql.*;

public class JTableTest {
    private String[] columnNames = {"Id", "First name", "Second name", "Age"};
    DefaultTableModel peopleTableModel = new DefaultTableModel() {
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int index) {
            return columnNames[index];
        }
    };
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String DB_USERNAME = "ilya";
    private static final String DB_PASSWORD = "welosso_2001";
    private static final String DB_TEST_QUERY = "SELECT * FROM test_table;";
   // private JTable table;
    private JFrame mainFrame;
    private Connection connection;
    private ArrayList<Person> people = new ArrayList<>();

    public JTableTest() {
        getConnection();
        getDataFromConnection();
        printPeople();

        populateTableModel();
        JTable table = new JTable(peopleTableModel);
        new TestForm(peopleTableModel);

        /*
        // Build the frame
        mainFrame = new JFrame();
        mainFrame.setSize(1000, 800);
        mainFrame.setResizable(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);





        table.setMinimumSize(new Dimension(250, 70));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane tableScrollPane = new JScrollPane(
                table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        mainFrame.add(tableScrollPane);

        //Must be done last
        //mainFrame.pack();
        mainFrame.setVisible(true); */
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

    private void getConnection() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println(people);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    private void getDataFromConnection() {
        if (connection == null) {
            System.out.println("Connection is null");
            return;
        }
        try {
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(DB_TEST_QUERY);
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
