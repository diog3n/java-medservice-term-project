package org.medservice;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class MainMedForm extends JFrame {
    // Главная панель, в которой располгаются все остальные компоненты
    private JPanel mainPanel;
    // Панель со вкладками
    private JTabbedPane mainTabbedPane;
    // Вкладка пациента
    private JPanel patientPanel;
    // Вкладка медработника
    private JPanel mdPanel;
    // Вкладка записи на прием
    private JPanel appointPanel;
    // Таблица данных пациентов
    private JTable patientTable;
    // Панель с ползунками для прокрутки таблицы
    private JScrollPane patientTableScrollPane;
    // Текстовое поле для имени пациента
    private JTextField patientFirstNameTextField;
    // Текстовое поле для отчества пациента
    private JTextField patientMiddleNameTextField;
    // Текстовое поле для фамилии пациента
    private JTextField patientLastNameTextField;
    // Текстовое поле для адреса пациента
    private JTextField patientHomeAddrTextField;
    // Текстовое поле для идентификатора пациента
    private JTextField patientRecordIdTextField;
    // Текстовое поле для даты рождения пациента
    private JFormattedTextField patientDOBFmtTextField;
    // Кнопка для создания новой записи о пациенте на основе данных в текстовых полях
    private JButton patientSaveAsNewButton;
    // Кнопка для обновления записи о пациенте на основе данных в текстовыз полях
    private JButton patientUpdateButton;
    // Кнопка для очистки текстовых полей пациента
    private JButton patientNewButton;
    // Таблица записей о медработниках
    private JTable mdTable;
    // Текстовое поле для имени медработника
    private JTextField mdFirstNameTextField;
    // Текстовое поле для специализации медработника
    private JTextField mdSpecTextField;
    // Текстовое поле для отчества медработника
    private JTextField mdMiddleNameTextField;
    // Текстовое поле для номера кабинета медработника
    private JTextField mdRoomTextField;
    // Текстовое поле для фамилии медработника
    private JTextField mdLastNameTextField;
    // Текстовое поле для идентификационного номера медработника
    private JTextField mdPersonnelIdTextField;
    // Кнопка для очистки текстовых полей медработника
    private JButton mdNewButton;
    // Кнопка для создания новой записи о медработникк на основе данных в текстовых полях
    private JButton mdSaveAsNewButton;
    // Кнопка для обновления записи о медработнике на основе данных в текстовых полях
    private JButton mdUpdateButton;
    // Панель с ползунками для прокрутки таблицы в случае надобности
    private JScrollPane mdScrollPane;
    // Таблица с записями к врачу
    private JTable apmtTable;
    // Текстовое поле для даты приема
    private JTextField apmtDateTextField;
    // Текстовое поле для идентификационного номера медработника, к которому производится запись
    private JTextField apmtPatientMIDTextField;
    // Текстовое поле для идентификационного номера пациента, записывающегося на прием
    private JTextField apmtMDIDTextField;
    // Текстовое поле для времени записи на прием
    private JTextField apmtTimeTextField;
    // Кнопка для очистки тексотвых полей записи на прием
    private JButton apmtNewButton;
    // Кнопка для сохранения новой записи к врачу на основе данных в текстовых полях
    private JButton apmtSaveAsNewButton;
    // Кнопка для обновления записи к врачу на основе данных в текстовых полях
    private JButton apmtUpdateButton;
    // Текстовое поле для индентификационного поля записи на прием
    private JTextField apmtIdTextField;
    // Панель с ползунками для прокрутки таблицы записей на прием
    private JScrollPane apmtTableScrollPane;
    // Кнопка для удаления записи о пациенте
    private JButton patientDeleteButton;
    // Кнопка для удаления записи о медработнике
    private JButton mdDeleteButton;
    // Кнопка для удаления записи на прием
    private JButton apmtDeleteButton;
    // Кнопка для сортировки по признаку
    private JButton mdSortByButton;
    // Кнопка для поиска по идентификационному номеру
    private JButton mdFindButton;
    // Комбо-боксы с критериями для сортировки
    private JComboBox<String> apmtSortComboBox;
    private JComboBox<String> mdSortComboBox;
    private JComboBox<String> patientSortComboBox;
    // Кнопка для сортировки записей о пациентах по признаку
    private JButton patientSortByButton;
    // Кнопка для поиска по идентификационному номеру
    private JButton patientFindButton;
    // Кнопка для сортировки по признаку
    private JButton apmtSortByButton;
    // Кнопка для поиска по идентификационному номеру
    private JButton apmtFindButton;

    // Конструктор
    public MainMedForm() {
        super("MedIS");
        $$$setupUI$$$();
        setContentPane(mainPanel);
        setSize(new Dimension(1000, 800));
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Геттеры, сеттеры и прочий сгенерированный интегрированной средой разработки код
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JTabbedPane getMainTabbedPane() {
        return mainTabbedPane;
    }

    public void setMainTabbedPane(JTabbedPane mainTabbedPane) {
        this.mainTabbedPane = mainTabbedPane;
    }

    public JPanel getPatientPanel() {
        return patientPanel;
    }

    public void setPatientPanel(JPanel patientPanel) {
        this.patientPanel = patientPanel;
    }

    public JPanel getMdPanel() {
        return mdPanel;
    }

    public void setMdPanel(JPanel mdPanel) {
        this.mdPanel = mdPanel;
    }

    public JPanel getAppointPanel() {
        return appointPanel;
    }

    public void setAppointPanel(JPanel appointPanel) {
        this.appointPanel = appointPanel;
    }

    public JTable getPatientTable() {
        return patientTable;
    }

    public void setPatientTable(JTable patientTable) {
        this.patientTable = patientTable;
    }

    public JScrollPane getPatientTableScrollPane() {
        return patientTableScrollPane;
    }

    public void setPatientTableScrollPane(JScrollPane patientTableScrollPane) {
        this.patientTableScrollPane = patientTableScrollPane;
    }

    public JTextField getPatientFirstNameTextField() {
        return patientFirstNameTextField;
    }

    public void setPatientFirstNameTextField(JTextField patientFirstNameTextField) {
        this.patientFirstNameTextField = patientFirstNameTextField;
    }

    public JTextField getPatientMiddleNameTextField() {
        return patientMiddleNameTextField;
    }

    public void setPatientMiddleNameTextField(JTextField patientMiddleNameTextField) {
        this.patientMiddleNameTextField = patientMiddleNameTextField;
    }

    public JTextField getPatientLastNameTextField() {
        return patientLastNameTextField;
    }

    public void setPatientLastNameTextField(JTextField patientLastNameTextField) {
        this.patientLastNameTextField = patientLastNameTextField;
    }

    public JTextField getPatientHomeAddrTextField() {
        return patientHomeAddrTextField;
    }

    public void setPatientHomeAddrTextField(JTextField patientHomeAddrTextField) {
        this.patientHomeAddrTextField = patientHomeAddrTextField;
    }

    public JTextField getPatientRecordIdTextField() {
        return patientRecordIdTextField;
    }

    public void setPatientRecordIdTextField(JTextField patientRecordIdTextField) {
        this.patientRecordIdTextField = patientRecordIdTextField;
    }

    public JFormattedTextField getPatientDOBFmtTextField() {
        return patientDOBFmtTextField;
    }

    public void setPatientDOBFmtTextField(JFormattedTextField patientDOBFmtTextField) {
        this.patientDOBFmtTextField = patientDOBFmtTextField;
    }

    public JButton getPatientSaveAsNewButton() {
        return patientSaveAsNewButton;
    }

    public void setPatientSaveAsNewButton(JButton patientSaveAsNewButton) {
        this.patientSaveAsNewButton = patientSaveAsNewButton;
    }

    public JButton getPatientUpdateButton() {
        return patientUpdateButton;
    }

    public void setPatientUpdateButton(JButton patientUpdateButton) {
        this.patientUpdateButton = patientUpdateButton;
    }

    public JButton getPatientNewButton() {
        return patientNewButton;
    }

    public void setPatientNewButton(JButton patientNewButton) {
        this.patientNewButton = patientNewButton;
    }

    public JTable getMdTable() {
        return mdTable;
    }

    public void setMdTable(JTable mdTable) {
        this.mdTable = mdTable;
    }

    public JTextField getMdFirstNameTextField() {
        return mdFirstNameTextField;
    }

    public void setMdFirstNameTextField(JTextField mdFirstNameTextField) {
        this.mdFirstNameTextField = mdFirstNameTextField;
    }

    public JTextField getMdSpecTextField() {
        return mdSpecTextField;
    }

    public void setMdSpecTextField(JTextField mdSpecTextField) {
        this.mdSpecTextField = mdSpecTextField;
    }

    public JTextField getMdMiddleNameTextField() {
        return mdMiddleNameTextField;
    }

    public void setMdMiddleNameTextField(JTextField mdMiddleNameTextField) {
        this.mdMiddleNameTextField = mdMiddleNameTextField;
    }

    public JTextField getMdRoomTextField() {
        return mdRoomTextField;
    }

    public void setMdRoomTextField(JTextField mdRoomTextField) {
        this.mdRoomTextField = mdRoomTextField;
    }

    public JTextField getMdLastNameTextField() {
        return mdLastNameTextField;
    }

    public void setMdLastNameTextField(JTextField mdLastNameTextField) {
        this.mdLastNameTextField = mdLastNameTextField;
    }

    public JTextField getMdPersonnelIdTextField() {
        return mdPersonnelIdTextField;
    }

    public void setMdPersonnelIdTextField(JTextField mdPersonnelIdTextField) {
        this.mdPersonnelIdTextField = mdPersonnelIdTextField;
    }

    public JButton getMdNewButton() {
        return mdNewButton;
    }

    public void setMdNewButton(JButton mdNewButton) {
        this.mdNewButton = mdNewButton;
    }

    public JButton getMdSaveAsNewButton() {
        return mdSaveAsNewButton;
    }

    public void setMdSaveAsNewButton(JButton mdSaveAsNewButton) {
        this.mdSaveAsNewButton = mdSaveAsNewButton;
    }

    public JButton getMdUpdateButton() {
        return mdUpdateButton;
    }

    public void setMdUpdateButton(JButton mdUpdateButton) {
        this.mdUpdateButton = mdUpdateButton;
    }

    public JScrollPane getMdScrollPane() {
        return mdScrollPane;
    }

    public void setMdScrollPane(JScrollPane mdScrollPane) {
        this.mdScrollPane = mdScrollPane;
    }

    public JTable getApmtTable() {
        return apmtTable;
    }

    public void setApmtTable(JTable apmtTable) {
        this.apmtTable = apmtTable;
    }

    public JTextField getApmtDateTextField() {
        return apmtDateTextField;
    }

    public void setApmtDateTextField(JTextField apmtDateTextField) {
        this.apmtDateTextField = apmtDateTextField;
    }

    public JTextField getApmtPatientMIDTextField() {
        return apmtPatientMIDTextField;
    }

    public void setApmtPatientMIDTextField(JTextField apmtPatientMIDTextField) {
        this.apmtPatientMIDTextField = apmtPatientMIDTextField;
    }

    public JTextField getApmtMDIDTextField() {
        return apmtMDIDTextField;
    }

    public void setApmtMDIDTextField(JTextField apmtMDIDTextField) {
        this.apmtMDIDTextField = apmtMDIDTextField;
    }

    public JTextField getApmtTimeTextField() {
        return apmtTimeTextField;
    }

    public void setApmtTimeTextField(JTextField apmtTimeTextField) {
        this.apmtTimeTextField = apmtTimeTextField;
    }

    public JButton getApmtNewButton() {
        return apmtNewButton;
    }

    public void setApmtNewButton(JButton apmtNewButton) {
        this.apmtNewButton = apmtNewButton;
    }

    public JButton getApmtSaveAsNewButton() {
        return apmtSaveAsNewButton;
    }

    public void setApmtSaveAsNewButton(JButton apmtSaveAsNewButton) {
        this.apmtSaveAsNewButton = apmtSaveAsNewButton;
    }

    public JButton getApmtUpdateButton() {
        return apmtUpdateButton;
    }

    public void setApmtUpdateButton(JButton apmtUpdateButton) {
        this.apmtUpdateButton = apmtUpdateButton;
    }

    public JTextField getApmtIdTextField() {
        return apmtIdTextField;
    }

    public void setApmtIdTextField(JTextField apmtIdTextField) {
        this.apmtIdTextField = apmtIdTextField;
    }

    public JScrollPane getApmtTableScrollPane() {
        return apmtTableScrollPane;
    }

    public void setApmtTableScrollPane(JScrollPane apmtTableScrollPane) {
        this.apmtTableScrollPane = apmtTableScrollPane;
    }

    public JButton getPatientDeleteButton() {
        return patientDeleteButton;
    }

    public void setPatientDeleteButton(JButton patientDeleteButton) {
        this.patientDeleteButton = patientDeleteButton;
    }

    public JButton getMdDeleteButton() {
        return mdDeleteButton;
    }

    public void setMdDeleteButton(JButton mdDeleteButton) {
        this.mdDeleteButton = mdDeleteButton;
    }

    public JButton getApmtDeleteButton() {
        return apmtDeleteButton;
    }

    public void setApmtDeleteButton(JButton apmtDeleteButton) {
        this.apmtDeleteButton = apmtDeleteButton;
    }

    public JButton getMdSortByButton() {
        return mdSortByButton;
    }

    public void setMdSortByButton(JButton mdSortByButton) {
        this.mdSortByButton = mdSortByButton;
    }

    public JButton getMdFindButton() {
        return mdFindButton;
    }

    public void setMdFindButton(JButton mdFindButton) {
        this.mdFindButton = mdFindButton;
    }

    public JComboBox getMdSortComboBox() {
        return mdSortComboBox;
    }

    public void setMdSortComboBox(JComboBox mdSortComboBox) {
        this.mdSortComboBox = mdSortComboBox;
    }

    public JComboBox getPatientSortComboBox() {
        return patientSortComboBox;
    }

    public void setPatientSortComboBox(JComboBox patientSortComboBox) {
        this.patientSortComboBox = patientSortComboBox;
    }

    public JButton getPatientSortByButton() {
        return patientSortByButton;
    }

    public void setPatientSortByButton(JButton patientSortByButton) {
        this.patientSortByButton = patientSortByButton;
    }

    public JButton getPatientFindButton() {
        return patientFindButton;
    }

    public void setPatientFindButton(JButton patientFindButton) {
        this.patientFindButton = patientFindButton;
    }

    public JButton getApmtSortByButton() {
        return apmtSortByButton;
    }

    public void setApmtSortByButton(JButton apmtSortByButton) {
        this.apmtSortByButton = apmtSortByButton;
    }

    public JButton getApmtFindButton() {
        return apmtFindButton;
    }

    public void setApmtFindButton(JButton apmtFindButton) {
        this.apmtFindButton = apmtFindButton;
    }

    public JComboBox getApmtSortComboBox() {
        return apmtSortComboBox;
    }

    public void setApmtSortComboBox(JComboBox apmtSortComboBox) {
        this.apmtSortComboBox = apmtSortComboBox;
    }

    private void createUIComponents() {

    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(5, 10, 10, 10), -1, -1));
        mainTabbedPane = new JTabbedPane();
        mainPanel.add(mainTabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        patientPanel = new JPanel();
        patientPanel.setLayout(new GridLayoutManager(8, 4, new Insets(5, 5, 5, 5), -1, -1));
        mainTabbedPane.addTab("Patients", patientPanel);
        patientTableScrollPane = new JScrollPane();
        patientPanel.add(patientTableScrollPane, new GridConstraints(7, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        patientTable = new JTable();
        patientTableScrollPane.setViewportView(patientTable);
        patientFirstNameTextField = new JTextField();
        patientPanel.add(patientFirstNameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("First Name:");
        patientPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(78, 19), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Date of birth:");
        patientPanel.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientMiddleNameTextField = new JTextField();
        patientPanel.add(patientMiddleNameTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        patientLastNameTextField = new JTextField();
        patientPanel.add(patientLastNameTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        patientHomeAddrTextField = new JTextField();
        patientPanel.add(patientHomeAddrTextField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        patientRecordIdTextField = new JTextField();
        patientPanel.add(patientRecordIdTextField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Middle Name:");
        patientPanel.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Last Name:");
        patientPanel.add(label4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Home address:");
        patientPanel.add(label5, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Med. Record ID:");
        patientPanel.add(label6, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientDOBFmtTextField = new JFormattedTextField();
        patientPanel.add(patientDOBFmtTextField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        patientSaveAsNewButton = new JButton();
        patientSaveAsNewButton.setText("Save as new");
        patientPanel.add(patientSaveAsNewButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientUpdateButton = new JButton();
        patientUpdateButton.setEnabled(true);
        patientUpdateButton.setText("Update");
        patientPanel.add(patientUpdateButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientNewButton = new JButton();
        patientNewButton.setText("New");
        patientPanel.add(patientNewButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientDeleteButton = new JButton();
        patientDeleteButton.setText("Delete");
        patientPanel.add(patientDeleteButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientSortComboBox = new JComboBox();
        patientPanel.add(patientSortComboBox, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientSortByButton = new JButton();
        patientSortByButton.setText("Sort by");
        patientPanel.add(patientSortByButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientFindButton = new JButton();
        patientFindButton.setText("Find by ID");
        patientPanel.add(patientFindButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mdPanel = new JPanel();
        mdPanel.setLayout(new GridLayoutManager(8, 4, new Insets(5, 5, 5, 5), -1, -1));
        mainTabbedPane.addTab("Personnel", mdPanel);
        mdScrollPane = new JScrollPane();
        mdPanel.add(mdScrollPane, new GridConstraints(7, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        mdTable = new JTable();
        mdScrollPane.setViewportView(mdTable);
        mdFirstNameTextField = new JTextField();
        mdPanel.add(mdFirstNameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        mdSpecTextField = new JTextField();
        mdPanel.add(mdSpecTextField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        mdMiddleNameTextField = new JTextField();
        mdPanel.add(mdMiddleNameTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        mdRoomTextField = new JTextField();
        mdPanel.add(mdRoomTextField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        mdLastNameTextField = new JTextField();
        mdPanel.add(mdLastNameTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        mdPersonnelIdTextField = new JTextField();
        mdPanel.add(mdPersonnelIdTextField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("First Name:");
        mdPanel.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Middle Name:");
        mdPanel.add(label8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Last Name:");
        mdPanel.add(label9, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Speciality: ");
        mdPanel.add(label10, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Room number:");
        mdPanel.add(label11, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("Personnel ID:");
        mdPanel.add(label12, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mdNewButton = new JButton();
        mdNewButton.setText("New");
        mdPanel.add(mdNewButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mdSaveAsNewButton = new JButton();
        mdSaveAsNewButton.setText("Save as new");
        mdPanel.add(mdSaveAsNewButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mdUpdateButton = new JButton();
        mdUpdateButton.setText("Update");
        mdPanel.add(mdUpdateButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mdDeleteButton = new JButton();
        mdDeleteButton.setText("Delete");
        mdPanel.add(mdDeleteButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mdSortByButton = new JButton();
        mdSortByButton.setText("Sort by");
        mdPanel.add(mdSortByButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mdFindButton = new JButton();
        mdFindButton.setText("Find by ID");
        mdPanel.add(mdFindButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mdSortComboBox = new JComboBox();
        mdPanel.add(mdSortComboBox, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        appointPanel = new JPanel();
        appointPanel.setLayout(new GridLayoutManager(7, 4, new Insets(5, 5, 5, 5), -1, -1));
        mainTabbedPane.addTab("Appointments", appointPanel);
        apmtTableScrollPane = new JScrollPane();
        appointPanel.add(apmtTableScrollPane, new GridConstraints(6, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        apmtTable = new JTable();
        apmtTableScrollPane.setViewportView(apmtTable);
        apmtDateTextField = new JTextField();
        appointPanel.add(apmtDateTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        apmtPatientMIDTextField = new JTextField();
        appointPanel.add(apmtPatientMIDTextField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        apmtMDIDTextField = new JTextField();
        appointPanel.add(apmtMDIDTextField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        apmtTimeTextField = new JTextField();
        appointPanel.add(apmtTimeTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("Appointment date:");
        appointPanel.add(label13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("Appointment time:");
        appointPanel.add(label14, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setText("Patient Med. Record ID: ");
        appointPanel.add(label15, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setText("MD Personnel ID: ");
        appointPanel.add(label16, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        apmtNewButton = new JButton();
        apmtNewButton.setText("New");
        appointPanel.add(apmtNewButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        apmtSaveAsNewButton = new JButton();
        apmtSaveAsNewButton.setText("Save as new");
        appointPanel.add(apmtSaveAsNewButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        apmtUpdateButton = new JButton();
        apmtUpdateButton.setText("Update");
        appointPanel.add(apmtUpdateButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setText("Appointment ID:");
        appointPanel.add(label17, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        apmtIdTextField = new JTextField();
        appointPanel.add(apmtIdTextField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        apmtDeleteButton = new JButton();
        apmtDeleteButton.setText("Delete");
        appointPanel.add(apmtDeleteButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        apmtSortByButton = new JButton();
        apmtSortByButton.setText("Sort by");
        appointPanel.add(apmtSortByButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        apmtFindButton = new JButton();
        apmtFindButton.setText("Find by ID");
        appointPanel.add(apmtFindButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        apmtSortComboBox = new JComboBox();
        appointPanel.add(apmtSortComboBox, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
