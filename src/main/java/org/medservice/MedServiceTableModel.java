package org.medservice;

import javax.swing.table.DefaultTableModel;

public class MedServiceTableModel extends DefaultTableModel {
    private String[] columnNames;

    public MedServiceTableModel(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }
}
