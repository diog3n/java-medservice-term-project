package org.medservice;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.*;
public class MainMedFormController {
    // Отвечает за включение и выключение режима отладки
    private final boolean DEBUG_MODE = false;
    // Название таблицы пациента
    private final String PATIENT_TABLE_NAME = "patient";
    // Название таблицы медработников
    private final String MD_TABLE_NAME = "medical_personnel";
    // Навзание таблицы записей на прием
    private final String APPOINTMENT_TABLE_NAME = "appointment";
    // Переменная представления, инъекция зависимости
    private MainMedForm view;
    // Переменная подключения к базе данных
    private Connection dbConnection;
    // Переменные, представляющие модели таблиц базы данных
    private MedServiceTableModel patientTableModel = new MedServiceTableModel(PatientModel.columnNames);
    private MedServiceTableModel mdTableModel = new MedServiceTableModel(MdModel.columnNames);
    private MedServiceTableModel apmtTableModel = new MedServiceTableModel(AppointmentModel.columnNames);
    // Конструкторы
    public MainMedFormController(MainMedForm view) {
        this.view = view;
        initView();
    }
    // Вывод сообщений отладки
    private void debugPrintLn(String text) {
        if (DEBUG_MODE) System.out.println(text);
    }
    // Инициализация представления
    private void initView() {
        // Спросить у пользователя данные для входа
        askForCredentials();
        // Настроить выпадающие списки с критериями для сортировки
        setUpComboBoxes();
        // Настроить эвенты для кнопок
        setUpEventListeners();
        // Обновить таблицы
        updateTables();
    }
    // Данная функция добавляет названия столбцов в выпадающие списки
    private void setUpComboBoxes() {
        for (String column : PatientModel.columnNames) {
            this.view.getPatientSortComboBox().addItem(column);
        }
        for (String column : MdModel.columnNames) {
            this.view.getMdSortComboBox().addItem(column);
        }
        for (String column : AppointmentModel.columnNames) {
            this.view.getApmtSortComboBox().addItem(column);
        }
    }
    // Данная функция обновляет таблицы
    private void updateTables() {
        updatePatientTable();
        updateMdTable();
        updateApmtTable();
    }
    // Эта функция очищает текстовые поля пациента
    private void clearPatientTextFields() {
        this.view.getPatientRecordIdTextField().setText("");
        this.view.getPatientFirstNameTextField().setText("");
        this.view.getPatientMiddleNameTextField().setText("");
        this.view.getPatientLastNameTextField().setText("");
        this.view.getPatientDOBFmtTextField().setText("");
        this.view.getPatientHomeAddrTextField().setText("");
    }
    /* Эта функция, при клике на строчку в таблице, автоматически заполняет
     * текстовые поля пациента */
    private void onPatientTableCellClick(ListSelectionEvent e) {
        var table = this.view.getPatientTable();
        int selectedRow = table.getSelectedRow();
        debugPrintLn("Selected row: " + selectedRow);
        if (selectedRow < 0) return;

        String patientId = Integer.toString((int) table.getValueAt(selectedRow, 0));
        String patientFirstName = (String) table.getValueAt(selectedRow, 1);
        String patientMiddleName = (String) table.getValueAt(selectedRow, 2);
        String patientLastName = (String) table.getValueAt(selectedRow, 3);
        String patientDateOfBirth = (String) table.getValueAt(selectedRow, 4);
        String patientHomeAddress = (String) table.getValueAt(selectedRow, 5);

        this.view.getPatientRecordIdTextField().setText(patientId);
        this.view.getPatientFirstNameTextField().setText(patientFirstName);
        this.view.getPatientMiddleNameTextField().setText(patientMiddleName);
        this.view.getPatientLastNameTextField().setText(patientLastName);
        this.view.getPatientDOBFmtTextField().setText(patientDateOfBirth);
        this.view.getPatientHomeAddrTextField().setText(patientHomeAddress);
    }
    // Эта функция очищает текстовые поля медработника
    private void clearMdTextFields() {
        this.view.getMdPersonnelIdTextField().setText("");
        this.view.getMdFirstNameTextField().setText("");
        this.view.getMdMiddleNameTextField().setText("");
        this.view.getMdLastNameTextField().setText("");
        this.view.getMdSpecTextField().setText("");
        this.view.getMdRoomTextField().setText("");
    }
    /* Эта функция, при клике на строчку в таблице, автоматически заполняет
     * текстовые поля медработника */
    private void onMdTableCellClick(ListSelectionEvent e) {
        var table = this.view.getMdTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) return;

        String mdId = Integer.toString((int) table.getValueAt(selectedRow, 0));
        String mdFirstName = (String) table.getValueAt(selectedRow, 1);
        String mdMiddleName = (String) table.getValueAt(selectedRow, 2);
        String mdLastName = (String) table.getValueAt(selectedRow, 3);
        String mdSpeciality = (String) table.getValueAt(selectedRow, 4);
        String mdRoomNumber = Integer.toString((int) table.getValueAt(selectedRow, 5));

        this.view.getMdPersonnelIdTextField().setText(mdId);
        this.view.getMdFirstNameTextField().setText(mdFirstName);
        this.view.getMdMiddleNameTextField().setText(mdMiddleName);
        this.view.getMdLastNameTextField().setText(mdLastName);
        this.view.getMdSpecTextField().setText(mdSpeciality);
        this.view.getMdRoomTextField().setText(mdRoomNumber);
    }
    // Эта функция очищает текстовые поля записи на прием
    private void clearApmtTextFields() {
        this.view.getApmtIdTextField().setText("");
        this.view.getApmtDateTextField().setText("");
        this.view.getApmtTimeTextField().setText("");
        this.view.getApmtMDIDTextField().setText("");
        this.view.getApmtPatientMIDTextField().setText("");
    }
    /* Эта функция, при клике на строчку в таблице, автоматически заполняет
     * текстовые поля записи на прием */
    private void onApmtTableCellClick(ListSelectionEvent e) {
        var table = this.view.getApmtTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) return;
        String apmtId = Integer.toString((int) table.getValueAt(selectedRow, 0));
        String dateTime = (String) table.getValueAt(selectedRow, 1);

        String date = dateTime.split(" ")[0];
        String time = dateTime.split(" ")[1];

        String mdId = Integer.toString((int) table.getValueAt(selectedRow, 2));
        String mdFirstName = (String) table.getValueAt(selectedRow, 3);
        String mdMiddleName = (String) table.getValueAt(selectedRow, 4);
        String mdLastName = (String) table.getValueAt(selectedRow, 5);
        String patientId = Integer.toString((int) table.getValueAt(selectedRow, 6));
        String patientFirstName = (String) table.getValueAt(selectedRow, 7);
        String patientMiddleName = (String) table.getValueAt(selectedRow, 8);
        String patientLastName = (String) table.getValueAt(selectedRow, 9);

        this.view.getApmtIdTextField().setText(apmtId);
        this.view.getApmtDateTextField().setText(date);
        this.view.getApmtTimeTextField().setText(time);
        this.view.getApmtMDIDTextField().setText(mdId);
        this.view.getApmtPatientMIDTextField().setText(patientId);
    }
    // Данная функция настраивает сенсоры событий
    private void setUpEventListeners() {
        // Настриваем сенсор на клик по строчке таблицы пациента
        this.view.getPatientTable().setColumnSelectionAllowed(false);
        var patientTableSelectionModel = this.view.getPatientTable().getSelectionModel();
        patientTableSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientTableSelectionModel.addListSelectionListener(this::onPatientTableCellClick);
        // Настриваем сенсор на клик по строчке таблицы медработника
        this.view.getMdTable().setColumnSelectionAllowed(false);
        var mdTableSelectionModel = this.view.getMdTable().getSelectionModel();
        mdTableSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mdTableSelectionModel.addListSelectionListener(this::onMdTableCellClick);
        // Настраиваем сенсор на клик по строчке таблицы записи на прием
        this.view.getApmtTable().setColumnSelectionAllowed(false);
        var apmtTableSelectionModel = this.view.getApmtTable().getSelectionModel();
        apmtTableSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        apmtTableSelectionModel.addListSelectionListener(this::onApmtTableCellClick);

        // Настройка сенсоров для кнопок страницы пациента
        this.view.getPatientSaveAsNewButton().addActionListener(this::onSaveAsNewButtonClick);
        this.view.getPatientDeleteButton().addActionListener(this::onDeleteButtonClick);
        this.view.getPatientFindButton().addActionListener(this::onFindButtonClick);
        this.view.getPatientNewButton().addActionListener(this::onNewButtonClick);
        this.view.getPatientSortByButton().addActionListener(this::onSortByButtonClick);
        this.view.getPatientUpdateButton().addActionListener(this::onUpdateButtonClick);

        // Настройка сенсоров для кнопок страницы медработника
        this.view.getMdSaveAsNewButton().addActionListener(this::onSaveAsNewButtonClick);
        this.view.getMdDeleteButton().addActionListener(this::onDeleteButtonClick);
        this.view.getMdFindButton().addActionListener(this::onFindButtonClick);
        this.view.getMdNewButton().addActionListener(this::onNewButtonClick);
        this.view.getMdSortByButton().addActionListener(this::onSortByButtonClick);
        this.view.getMdUpdateButton().addActionListener(this::onUpdateButtonClick);

        // Настройка сенсоров для кнопок страницы записи на прием
        this.view.getApmtSaveAsNewButton().addActionListener(this::onSaveAsNewButtonClick);
        this.view.getApmtDeleteButton().addActionListener(this::onDeleteButtonClick);
        this.view.getApmtFindButton().addActionListener(this::onFindButtonClick);
        this.view.getApmtNewButton().addActionListener(this::onNewButtonClick);
        this.view.getApmtSortByButton().addActionListener(this::onSortByButtonClick);
        this.view.getApmtUpdateButton().addActionListener(this::onUpdateButtonClick);
    }

    // Заполняет таблицу, используя данные из списка записей на прием
    private void fillApmtTableModelViaArrayList(ArrayList<AppointmentModel> apmts) {
        for (var ap : apmts) {
            Object[] row = {
                    ap.getAppointmentId(),
                    ap.getDateTime(),
                    ap.getPersonnelId(),
                    ap.getMdFirstName(),
                    ap.getMdMiddleName(),
                    ap.getMdLastName(),
                    ap.getPatientId(),
                    ap.getPatientFirstName(),
                    ap.getPatientMiddleName(),
                    ap.getPatientLastName()
            };
            apmtTableModel.addRow(row);
        }
    }
    // Заполняет список записей на прием из ResultSet и возвращает его
    private ArrayList<AppointmentModel> apmtArrayListFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<AppointmentModel> apmnts = new ArrayList<>();
        while (rs.next()) {
            apmnts.add(new AppointmentModel(
                    rs.getInt("id"),
                    rs.getString("date_time"),
                    rs.getInt("MID"),
                    rs.getString("md_first_name"),
                    rs.getString("md_middle_name"),
                    rs.getString("md_last_name"),
                    rs.getInt("MRID"),
                    rs.getString("pat_first_name"),
                    rs.getString("pat_middle_name"),
                    rs.getString("pat_last_name"))
            );
        }

        debugPrintLn(apmnts.toString());

        return apmnts;
    }
    // Обновить таблицу записей на прием
    private void updateApmtTableModel(String query) {
        try {
            // Выполнить запрос и получить данные в формате ResultSet
            Statement st = this.dbConnection.createStatement();
            ResultSet rs = st.executeQuery(query);
            // Заполнить список записей на прием
            ArrayList<AppointmentModel> apmts = apmtArrayListFromResultSet(rs);
            // Очистить таблицу и заполнить ее новыми данными
            apmtTableModel = new MedServiceTableModel(AppointmentModel.columnNames);
            fillApmtTableModelViaArrayList(apmts);
        } catch (SQLException sqle) {
            // В случае возникновения ошибок вывести информацию о неполадках
            if (DEBUG_MODE) sqle.printStackTrace();
        }
    }
    // Обновляет таблицу записей на прием
    private void updateApmtTable() {
        // Стандартный запрос на выборку всей необходимой информации
        String UPDATE_APMT_TM_QUERY = "SELECT " +
                "appointment.id, date_time, " +
                "personnel.id AS MID, " +
                "personnel.first_name AS md_first_name, " +
                "personnel.middle_name AS md_middle_name, " +
                "personnel.last_name AS md_last_name, " +
                "patient.id AS MRID, " +
                "patient.first_name AS pat_first_name, " +
                "patient.middle_name AS pat_middle_name, " +
                "patient.last_name AS pat_last_name  " +
                "FROM " + APPOINTMENT_TABLE_NAME + " AS appointment " +
                "LEFT JOIN " + PATIENT_TABLE_NAME + " AS patient ON patient.id = appointment.patient_id " +
                "LEFT JOIN " + MD_TABLE_NAME + " AS personnel ON personnel.id = appointment.medical_personnel_id " +
                "ORDER BY " +
                AppointmentModel.columnNamesToSql.getOrDefault(
                        (String) this.view.getApmtSortComboBox().getSelectedItem(), "appointment.id") + ";";
        // Обновить таблицу
        updateApmtTableModel(UPDATE_APMT_TM_QUERY);
        // Вставить ее в представление
        this.view.getApmtTable().setModel(apmtTableModel);
    }
    // Заполняет таблицу, используя данные из списка медработников

    private void fillMdTableModelViaArrayList(ArrayList<MdModel> mds) {
        for (var md : mds) {
            Object[] row = {
                    md.getId(),
                    md.getFirstName(),
                    md.getMiddleName(),
                    md.getLastName(),
                    md.getSpeciality(),
                    md.getRoomNum()
            };
            mdTableModel.addRow(row);
        }
    }
    // Заполняет список медработников из данных, полученных в виде ResultSet
    private ArrayList<MdModel> mdArrayListFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<MdModel> mds = new ArrayList<>();
        while (rs.next()) {
            mds.add(new MdModel(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("middle_name"),
                    rs.getString("last_name"),
                    rs.getString("speciality"),
                    rs.getInt("room_number"))
            );
        }

        debugPrintLn(mds.toString());

        return mds;
    }
    // Обновляет данные в таблице медработников
    private void updateMdTableModel(String query) {
        try {
            // Выполняем запрос на выборку данных из базы
            Statement st = this.dbConnection.createStatement();
            ResultSet rs = st.executeQuery(query);
            // Заполняем список данными из ResultSet
            ArrayList<MdModel> mds = mdArrayListFromResultSet(rs);
            // Очищаем таблицу и заполняем ее
            mdTableModel = new MedServiceTableModel(MdModel.columnNames);
            fillMdTableModelViaArrayList(mds);
        } catch (SQLException sqle) {
            // При возникновении проблем вывести информацию о неполадках
            if (DEBUG_MODE) sqle.printStackTrace();
        }
    }
    // Обновить таблицу медработника
    private void updateMdTable() {
        // Запрос на выборку всех данных
        String UPDATE_MD_TM_QUERY = "SELECT " +
                "id, " +
                "first_name, " +
                "middle_name, " +
                "last_name, " +
                "speciality, " +
                "room_number " +
                "FROM " +
                MD_TABLE_NAME + " ORDER BY " +
                MdModel.columnNamesToSql.getOrDefault(
                        (String) this.view.getMdSortComboBox().getSelectedItem(), "id") +
                ";";
        // Обновление таблицы
        updateMdTableModel(UPDATE_MD_TM_QUERY);
        this.view.getMdTable().setModel(mdTableModel);
    }
    // Заполняет таблицу пациентов данными из списка
    private void fillPatientTableModelViaArrayList(ArrayList<PatientModel> patients) {
        for (var patient : patients) {
            Object[] row = {
                    patient.getId(),
                    patient.getFirstName(),
                    patient.getMiddleName(),
                    patient.getLastName(),
                    patient.getDateOfBirth(),
                    patient.getHomeAddress()
            };
            patientTableModel.addRow(row);
        }
    }
    // Заполняет список пациентов данными, представленными в виде ResultSet
    private ArrayList<PatientModel> patientsArrayListFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<PatientModel> patients = new ArrayList<>();
        while (rs.next()) {
            patients.add(new PatientModel(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("middle_name"),
                    rs.getString("last_name"),
                    rs.getString("date_of_birth"),
                    rs.getString("home_address"))
            );
        }
        debugPrintLn(patients.toString());
        return patients;
    }
    // Обновить данные в таблице пациентов
    private void updatePatientTableModel(String query) {
        try {
            // Выполнить запрос к базе данных
            Statement st = this.dbConnection.createStatement();
            ResultSet rs = st.executeQuery(query);
            // Заполнить список пациентов данными из ResultSet
            ArrayList<PatientModel> patients = patientsArrayListFromResultSet(rs);
            // Очистить таблицу и заполнить ее новыми данными
            patientTableModel = new MedServiceTableModel(PatientModel.columnNames);
            fillPatientTableModelViaArrayList(patients);
        } catch (SQLException sqle) {
            // При возникновении неполадок вывести информацию об ошибке
            if (DEBUG_MODE) sqle.printStackTrace();
        }
    }
    // Обновить таблицу пациентов
    private void updatePatientTable() {
        // Запрос на выборку всех данных
        String UPDATE_PAT_TM_QUERY = "SELECT id, " +
                "first_name, " +
                "middle_name, " +
                "last_name, " +
                "date_of_birth, " +
                "home_address FROM " +
                PATIENT_TABLE_NAME + " ORDER BY " +
                PatientModel.columnNamesToSql.getOrDefault(
                        (String) this.view.getPatientSortComboBox().getSelectedItem(), "id")
                + ";";
        // Обновить таблицу пациенту
        updatePatientTableModel(UPDATE_PAT_TM_QUERY);
        this.view.getPatientTable().setModel(patientTableModel);
    }
    // Показывает диалог, запрашивающий имя пользователя
    private String getUsernameThroughDialog() {
        if (DEBUG_MODE) return "ilya"; // TODO: DELETE THIS WHEN FINISHED!

        Object[] options = {"Ok", "Cancel"};
        JTextField loginField = new JTextField(10);
        int action = JOptionPane.showOptionDialog(
                null,
                loginField,
                "Enter username",
                DEFAULT_OPTION,
                INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
        if (action == 1) {
            System.exit(0);
        }
        return loginField.getText();
    }
    // Показывает диалог, запрашивающий пароль пользователя
    private String getPasswordThroughDialog() {
        if (DEBUG_MODE) return "welosso_2001"; // TODO: DELETE THIS WHEN FINISHED!

        JPasswordField pwdField = new JPasswordField(10);
        Object[] options = {"Ok", "Cancel"};
        int action = JOptionPane.showOptionDialog(
                null,
                pwdField,
                "Enter password",
                DEFAULT_OPTION,
                INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
        if (action == 1) {
            System.exit(0);
        }
        return pwdField.getText();
    }

    // Запрашивает даннные и не открывает сессию, пока они не окажутся корректными
    private void askForCredentials() {
        while (true) {
            String dbUsername = getUsernameThroughDialog();
            String dbPassword = getPasswordThroughDialog();
            try {
                // Если данные для входа верны, то сессия будет открыта и цикл прервется
                connectToDb("jdbc:mysql://localhost:3306/medical_service_db", dbUsername, dbPassword);
                break;
            } catch (SQLException sqle) {
                if (DEBUG_MODE) {
                    sqle.printStackTrace();
                    break;
                }
            }
        }
    }
    // Подключается к базе данных
    private void connectToDb(String dbUrl, String dbUsername, String dbPassword) throws SQLException {
        this.dbConnection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
    // Проверяет, является ли год високосным
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
    // Проверяет, является ли введенная дата корректной с логической точки зрения
    private boolean isCorrectDate(String dateString) {
        // Проверка даты на соответствие формату
        if (!isCorrectDateFormat(dateString)) return false;
        // Разделяет дату на отдельные части - год, день, месяц
        String[] dateSplit = dateString.split("-");
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        // Проверяет дату на корректность
        int februaryDays = isLeapYear(year) ? 29 : 28;
        if (month > 12 || month < 1) return false;
        int[] daysInMonths = {31, februaryDays, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (day < 1) return false;
        if (day > daysInMonths[month - 1]) return false;
        return true;
    }
    // Проверяет время на соответствие формату
    private boolean isCorrectTimeFormat(String timeString) {
        return isCorrectFormat("##:##:##", timeString, "1234567890", ':');
    }
    /* Проверяет строку str на соответствие формату fmt, где строка должна состоять из символов,
     * записанных в alphabet и разделенных символом delimiter */
    private boolean isCorrectFormat(String fmt, String str, String alphabet, char delimiter) {
        if (fmt.length() != str.length()) return false;
        for (int pos = 0; pos < str.length(); pos++) {
            if (fmt.charAt(pos) == '#' && alphabet.indexOf(str.charAt(pos)) >= 0) continue;
            if (fmt.charAt(pos) == delimiter && str.charAt(pos) == delimiter) continue;
            return false;
        }
        return true;
    }
    // Проверяет введенное время на предмет логических ошибок
    private boolean isCorrectTime(String timeString) {
        // Проверка на соответствие формату чч:мм:сс
        if (!isCorrectTimeFormat(timeString)) return false;
        // Разделяет время на часы, минуты и секунды
        String[] timeStringSplit = timeString.split(":");
        int hours = Integer.parseInt(timeStringSplit[0]);
        int minutes = Integer.parseInt(timeStringSplit[1]);
        int seconds = Integer.parseInt(timeStringSplit[2]);
        // Проверяет время на предмет логических ошибок
        if (hours > 23) return false;
        return minutes <= 59 && seconds <= 59;
    }
    // Проверяет дату на соответствию формату
    private boolean isCorrectDateFormat(String dateString) {
        return isCorrectFormat("####-##-##", dateString, "1234567890", '-');
    }
    // Проверяет, является ли строка числом
    private boolean isNumber(String number) {
        String digits = "1234567890";
        for (int pos = 0; pos < number.length(); pos++) {
            if (digits.indexOf(number.charAt(pos)) < 0) return false;
        }
        return true;
    }
    // Показывает диалоговое окно, оповещающее пользователя о том, что ID не является числом или отсутствует
    private int showIdOptionDialog(String id) {
        Object[] options = {"Ok", "Cancel"};
        return JOptionPane.showOptionDialog(
                this.view,
                "Record ID is not a number. Set ID = " + id + "?",
                "Error",
                DEFAULT_OPTION,
                INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
    }
    // Показывает диалог, подтверждающий, что пользователь действительно хочет удалить запись
    private int showDeleteEntryOptionDialog() {
        Object[] options = {"Ok", "Cancel"};
        return JOptionPane.showOptionDialog(
                this.view,
                "This will delete the entry forever.\nAre you sure?",
                "Confirm action",
                DEFAULT_OPTION,
                INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
    }
    // Проверяет, являются ли текстовые поля пустыми
    private boolean areApmtFieldsEmpty() {
        String message = "";

        if (!isCorrectDate(this.view.getApmtDateTextField().getText())) {
            message = "Incorrect date format. Expected: yyyy-mm-dd.";
        } else if (!isCorrectTime(this.view.getApmtTimeTextField().getText())) {
            message = "Incorrect time format. Expected: hh:mm:ss.";
        } else if (this.view.getApmtPatientMIDTextField().getText().isEmpty()) {
            message = "Patient ID is empty.";
        } else if (this.view.getApmtMDIDTextField().getText().isEmpty()) {
            message = "MDID is empty";
        }

        if (!message.isEmpty()) {
            showMessageDialog(this.view, message);
            debugPrintLn(message);
            return true;
        }

        String id = this.view.getApmtIdTextField().getText();
        // Если ID не является числом или отсутствует, система предложит сгенерированный автоматически
        if (id.isEmpty() || !isNumber(id)) {
            debugPrintLn("ID is empty or not a number");
            id = Integer.toString(apmtTableModel.getRowCount() + 1);
            int action = showIdOptionDialog(id);
            if (action == 1) {
                this.view.getApmtIdTextField().setText("");
                return true;
            } else {
                this.view.getApmtIdTextField().setText(id);
            }
        }

        return false;
    }

    private boolean areMdFieldsEmpty() {
        String message = "";

        if (this.view.getMdFirstNameTextField().getText().isEmpty()) {
            message = "First name is empty!";
        } else if (this.view.getMdMiddleNameTextField().getText().isEmpty()) {
            message = "Middle name is empty";
        } else if (this.view.getMdLastNameTextField().getText().isEmpty()) {
            message = "Last name is empty";
        } else if (this.view.getMdSpecTextField().getText().isEmpty()) {
            message = "Speciality is empty";
        } else if (this.view.getMdRoomTextField().getText().isEmpty()) {
            message = "Room number is empty";
        }

        if (!message.isEmpty()) {
            showMessageDialog(this.view, message);
            debugPrintLn(message);
            return true;
        }

        String id = this.view.getMdPersonnelIdTextField().getText();
        // Если ID не является числом или отсутствует, система предложит сгенерированный автоматически
        if (id.isEmpty() || !isNumber(id)) {
            debugPrintLn("ID is empty or not a number");
            id = Integer.toString(mdTableModel.getRowCount() + 1);
            int action = showIdOptionDialog(id);
            if (action == 1) {
                this.view.getMdPersonnelIdTextField().setText("");
                return true;
            } else {
                this.view.getMdPersonnelIdTextField().setText(id);
            }
        }

        return false;
    }

    private boolean arePatientFieldsEmpty() {
        String message = "";

        if (this.view.getPatientFirstNameTextField().getText().isEmpty()) {
            message = "First name is empty";
        } else if (this.view.getPatientMiddleNameTextField().getText().isEmpty()) {
            message = "Middle name is empty";
        } else if (this.view.getPatientLastNameTextField().getText().isEmpty()) {
            message = "Last name is empty";
        } else if (this.view.getPatientHomeAddrTextField().getText().isEmpty()) {
            message = "Home address is empty";
        } else if (!isCorrectDate(this.view.getPatientDOBFmtTextField().getText())) {
            message = "Incorrect date format. Expected: yyyy-mm-dd.";
        }

        if (!message.isEmpty()) {
            showMessageDialog(this.view, message);
            debugPrintLn(message);
            return true;
        }

        String id = this.view.getPatientRecordIdTextField().getText();
        // Если ID не является числом или отсутствует, система предложит сгенерированный автоматически
        if (id.isEmpty() || !isNumber(id)) {
            debugPrintLn("ID is empty or not a number");
            id = Integer.toString(patientTableModel.getRowCount() + 1);
            int action = showIdOptionDialog(id);
            if (action == 1) {
                this.view.getPatientRecordIdTextField().setText("");
                return true;
            } else {
                this.view.getPatientRecordIdTextField().setText(id);
            }
        }
        return false;
    }
    // Разбивает длинную строку на несколько строчек заданной длины
    private String breakStringIntoLines(String str, int lineLength) {
        if (str.length() < lineLength) return str;

        StringBuilder lines = new StringBuilder(str.substring(0, lineLength));

        for (int pos = lineLength; pos < str.length(); pos++) {
            if (pos % lineLength == 0) lines.append('\n');
            lines.append(str.charAt(pos));
        }

        return lines.toString();
    }
    // Показывает диалог с сообщением об ошибке
    private void showErrorMessageDialog(String message) {
        showMessageDialog(
                this.view, "Wrong data, consult with your administrator about this error:\n"
                        + message
        );
    }
    // Безопасно выполняет запрос
    private void executeQuerySafe(String query) {
        debugPrintLn("EXECUTING QUERY: " + query);
        if (query.isEmpty()) return;
        try {
            Statement st = this.dbConnection.createStatement();
            st.execute(query);
        } catch (SQLIntegrityConstraintViolationException sqle) {
            if (DEBUG_MODE) sqle.printStackTrace();
            showErrorMessageDialog(breakStringIntoLines(sqle.getMessage(), 64));
        } catch (SQLException sqle) {
            if (DEBUG_MODE) sqle.printStackTrace();
        }
    }
    // Следующие функции генерируют запросы для тех или иных функций системы
    private String generateFindPatientQuery() {
        if (this.view.getPatientRecordIdTextField().getText().isEmpty()) {
            showMessageDialog(this.view, "ID is empty");
            return "";
        }
        return String.format(
                "SELECT id, " +
                "first_name, " +
                "middle_name, " +
                "last_name, " +
                "date_of_birth, " +
                "home_address FROM " +
                PATIENT_TABLE_NAME +
                " WHERE id = " + this.view.getPatientRecordIdTextField().getText() +
                " ORDER BY " +
                PatientModel.columnNamesToSql.getOrDefault((String) this.view.getPatientSortComboBox().getSelectedItem(), "id") +
                ";");
    }

    private String generateFindApmtQuery() {
        if (this.view.getApmtIdTextField().getText().isEmpty()) {
            showMessageDialog(this.view, "ID is epmty");
            return "";
        }
        return String.format(
                "SELECT " +
                "%s.id, " +
                "date_time, personnel.id AS MID, " +
                "personnel.first_name AS md_first_name, " +
                "personnel.middle_name AS md_middle_name, " +
                "personnel.last_name AS md_last_name, " +
                "patient.id AS MRID, " +
                "patient.first_name AS pat_first_name, " +
                "patient.middle_name AS pat_middle_name, " +
                "patient.last_name AS pat_last_name" +
                "FROM " +
                "%s AS appointment " +
                "LEFT JOIN " +
                "%s AS patient ON patient.id = appointment.patient_id " +
                "LEFT JOIN " +
                "%s AS personnel ON personnel.id = appointment.medical_personnel_id " +
                "WHERE appointment.id = %s;",
                APPOINTMENT_TABLE_NAME,
                APPOINTMENT_TABLE_NAME,
                PATIENT_TABLE_NAME,
                MD_TABLE_NAME,
                this.view.getApmtIdTextField().getText()
        );
    }

    private String generateFindMdQuery() {
        if (this.view.getMdPersonnelIdTextField().getText().isEmpty()) {
            showMessageDialog(this.view, "ID is empty");
            return "";
        }
        return String.format(
                "SELECT " +
                "id, " +
                "first_name, " +
                "middle_name, " +
                "last_name, " +
                "speciality, " +
                "room_number " +
                "FROM %s" +
                "WHERE id = %s;",
                MD_TABLE_NAME,
                this.view.getMdPersonnelIdTextField().getText()
        );
    }

    private String generateSaveAsNewPatientQuery() {
        if (arePatientFieldsEmpty()) return "";
        return String.format(
                "INSERT INTO %s (id, first_name, middle_name, last_name, date_of_birth, home_address) " +
                "VALUES ( %s, \"%s\", \"%s\", \"%s\", \"%s\", \"%s\" );",
                PATIENT_TABLE_NAME,
                this.view.getPatientRecordIdTextField().getText(),
                this.view.getPatientFirstNameTextField().getText(),
                this.view.getPatientMiddleNameTextField().getText(),
                this.view.getPatientLastNameTextField().getText(),
                this.view.getPatientDOBFmtTextField().getText(),
                this.view.getPatientHomeAddrTextField().getText()
        );
    }

    private String generateSaveAsNewApmtQuery() {
        if (areApmtFieldsEmpty()) return "";
        return String.format(
                "INSERT INTO %s (id, date_time, medical_personnel_id, patient_id) " +
                "VALUES ( %s, \"%s\", %s, %s );",
                APPOINTMENT_TABLE_NAME,
                this.view.getApmtIdTextField().getText(),
                this.view.getApmtDateTextField().getText() + " " + this.view.getApmtTimeTextField().getText(),
                this.view.getApmtMDIDTextField().getText(),
                this.view.getApmtPatientMIDTextField().getText()
        );
    }

    private String generateSaveAsNewMdQuery() {
        if (areMdFieldsEmpty()) return "";
        return String.format(
                "INSERT INTO %s (id, first_name, middle_name, last_name, speciality, room_number) " +
                "VALUES ( %s, \"%s\", \"%s\", \"%s\", \"%s\", %s );",
                MD_TABLE_NAME,
                this.view.getMdPersonnelIdTextField().getText(),
                this.view.getMdFirstNameTextField().getText(),
                this.view.getMdMiddleNameTextField().getText(),
                this.view.getMdLastNameTextField().getText(),
                this.view.getMdSpecTextField().getText(),
                this.view.getMdRoomTextField().getText()
        );
    }

    public String generateUpdatePatientQuery() {
        if (arePatientFieldsEmpty()) return "";
        return String.format(
                "UPDATE %s SET " +
                "first_name = \"%s\", " +
                "middle_name = \"%s\", " +
                "last_name = \"%s\", " +
                "date_of_birth = \"%s\", " +
                "home_address = \"%s\"" +
                "WHERE id = %s;",
                PATIENT_TABLE_NAME,
                this.view.getPatientFirstNameTextField().getText(),
                this.view.getPatientMiddleNameTextField().getText(),
                this.view.getPatientLastNameTextField().getText(),
                this.view.getPatientDOBFmtTextField().getText(),
                this.view.getPatientHomeAddrTextField().getText(),
                this.view.getPatientRecordIdTextField().getText()
        );
    }

    public String generateUpdateApmtQuery() {
        if (areApmtFieldsEmpty()) return "";
        return String.format(
                "UPDATE " +
                "%s " +
                "SET " +
                "date_time = \"%s\", " +
                "medical_personnel_id = %s, " +
                "patient_id = %s " +
                "WHERE id = %s ;",
                APPOINTMENT_TABLE_NAME,
                this.view.getApmtDateTextField().getText() + " " + this.view.getApmtTimeTextField().getText(),
                this.view.getApmtMDIDTextField().getText(),
                this.view.getApmtPatientMIDTextField().getText(),
                this.view.getApmtIdTextField().getText()
        );
    }

    private String generateUpdateMdQuery() {
        if (areMdFieldsEmpty()) return "";
        return String.format(
                "UPDATE " +
                "%s " +
                "SET " +
                "first_name = \"%s\", " +
                "middle_name = \"%s\", " +
                "last_name = \"%s\", " +
                "speciality = \"%s\", " +
                "room_number = \"%s\" " +
                "WHERE id = %s ;",
                MD_TABLE_NAME,
                this.view.getMdFirstNameTextField().getText(),
                this.view.getMdMiddleNameTextField().getText(),
                this.view.getMdLastNameTextField().getText(),
                this.view.getMdSpecTextField().getText(),
                this.view.getMdRoomTextField().getText(),
                this.view.getMdPersonnelIdTextField().getText()
        );
    }

    public String generateDeletePatientQuery() {
        if (arePatientFieldsEmpty()) return "";
        return String.format(
                "DELETE FROM %s WHERE id = %s;",
                PATIENT_TABLE_NAME,
                this.view.getPatientRecordIdTextField().getText()
        );
    }

    public String generateDeleteApmtQuery() {
        if (areApmtFieldsEmpty()) return "";
        return String.format(
                "DELETE FROM %s WHERE id = %s;",
                APPOINTMENT_TABLE_NAME,
                this.view.getApmtIdTextField().getText()
        );
    }

    public String generateDeleteMdQuery() {
        if (areMdFieldsEmpty()) return "";
        return String.format(
                "DELETE FROM %s WHERE id = %s;",
                MD_TABLE_NAME,
                this.view.getMdPersonnelIdTextField().getText()
        );
    }
    // Показывает диалог с превью данных, которые будут вставлены в таблицу записей на прием
    public int showPreviewDialog() {
        if (areApmtFieldsEmpty()) return 1;
        // Запрос на выбор записи с id в поле идентификатора пациента на странице записи на прием
        String previewPatientQuery = String.format(
                "SELECT id, " +
                "first_name, " +
                "middle_name, " +
                "last_name, " +
                "date_of_birth, " +
                "home_address FROM %s" +
                " WHERE id = %s",
                PATIENT_TABLE_NAME,
                this.view.getApmtPatientMIDTextField().getText()
        );
        // Запрос на выбор записи с id в поле идентификатора медработника на странице записи на прием
        String previewMdQuery = String.format(
                "SELECT " +
                "id, " +
                "first_name, " +
                "middle_name, " +
                "last_name, " +
                "speciality, " +
                "room_number " +
                "FROM %s " +
                "WHERE id = %s;",
                MD_TABLE_NAME,
                this.view.getApmtMDIDTextField().getText());

        PatientModel previewPatient = new PatientModel();
        MdModel previewMd = new MdModel();

        // Выполнение запроосов
        try {
            Statement patSt = this.dbConnection.createStatement();
            ResultSet patRs = patSt.executeQuery(previewPatientQuery);
            previewPatient = patientsArrayListFromResultSet(patRs).get(0);

            Statement mdSt = this.dbConnection.createStatement();
            ResultSet mdRs = mdSt.executeQuery(previewMdQuery);
            previewMd = mdArrayListFromResultSet(mdRs).get(0);
        } catch (IndexOutOfBoundsException e) {
            // Данная ошибка наиболее вероятна
            if (DEBUG_MODE) e.printStackTrace();
            showErrorMessageDialog(breakStringIntoLines("No such patient and/or MD.", 64));
            return 1;
        } catch (SQLException sqle) {
            // Данная ошибка скорее всего не возникнет
            if (DEBUG_MODE) sqle.printStackTrace();
            showErrorMessageDialog(breakStringIntoLines(sqle.getMessage(), 64));
        }

        String data = String.format("You are going to add this data to database:\n%s\n%s\n",
                previewPatient.toString(),
                previewMd.toString()
        );

        Object[] options = {"Ok", "Cancel"};
        return JOptionPane.showOptionDialog(
                this.view,
                data,
                "Preview",
                DEFAULT_OPTION,
                INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
    }
    // Обработчики событий.
    /* Все обработчики работают по одному принципу. Сначала они считывают событие, а потом
     * выясняют, что являлось его источником и предпринимают те или иные действия в зависимости
     * от источника.  */
    private void onNewButtonClick(ActionEvent e) {
        var sourceButton = e.getSource();
        if (sourceButton == this.view.getPatientNewButton()) {
            clearPatientTextFields();
        } else if (sourceButton == this.view.getMdNewButton()) {
            clearMdTextFields();
        } else if (sourceButton == this.view.getApmtNewButton()) {
            clearApmtTextFields();
        }
    }

    private void onSaveAsNewButtonClick(ActionEvent e) {
        var sourceButton = e.getSource();
        String query = "";
        if (sourceButton == this.view.getPatientSaveAsNewButton()) {
            query = generateSaveAsNewPatientQuery();
        } else if (sourceButton == this.view.getMdSaveAsNewButton()) {
            query = generateSaveAsNewMdQuery();
        } else if (sourceButton == this.view.getApmtSaveAsNewButton()) {
            if (showPreviewDialog() == 1) return;
            query = generateSaveAsNewApmtQuery();
        }

        executeQuerySafe(query);
        updateTables();
    }

    private void onUpdateButtonClick(ActionEvent e) {
        var sourceButton = e.getSource();
        String query = "";

        if (sourceButton == this.view.getPatientUpdateButton()) {
            query = generateUpdatePatientQuery();
        } else if (sourceButton == this.view.getMdUpdateButton()) {
            query = generateUpdateMdQuery();
        } else if (sourceButton == this.view.getApmtUpdateButton()) {
            if (showPreviewDialog() == 1) return;
            query = generateUpdateApmtQuery();
        }

        executeQuerySafe(query);
        updateTables();
    }

    private void onDeleteButtonClick(ActionEvent e) {
        var sourceButton = e.getSource();
        String query = "";

        if (sourceButton == this.view.getPatientDeleteButton()) {
            query = generateDeletePatientQuery();
        } else if (sourceButton == this.view.getMdDeleteButton()) {
            query = generateDeleteMdQuery();
        } else if (sourceButton == this.view.getApmtDeleteButton()) {
            query = generateDeleteApmtQuery();
        }

        if (!query.isEmpty()) {
            if (showDeleteEntryOptionDialog() == 1) return;
        }

        executeQuerySafe(query);
        updateTables();
    }

    private void onSortByButtonClick(ActionEvent e) {
        var sourceButton = e.getSource();

        if (sourceButton == this.view.getPatientSortByButton()) {
            updatePatientTable();
        } else if (sourceButton == this.view.getMdSortByButton()) {
            updateMdTable();
        } else if (sourceButton == this.view.getApmtSortByButton()) {
            updateApmtTable();
        }
    }

    private void onFindButtonClick(ActionEvent e) {
        var sourceButton = e.getSource();
        String query = "";

        if (sourceButton == this.view.getPatientFindButton()) {
            query = generateFindPatientQuery();
            updatePatientTableModel(query);
            this.view.getPatientTable().setModel(patientTableModel);
        } else if (sourceButton == this.view.getMdFindButton()) {
            query = generateFindMdQuery();
            updateMdTableModel(query);
            this.view.getPatientTable().setModel(mdTableModel);
        } else if (sourceButton == this.view.getApmtFindButton()) {
            query = generateFindApmtQuery();
            updateApmtTableModel(query);
            this.view.getMdTable().setModel(apmtTableModel);
        }
    }
}