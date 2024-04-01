
package net.handytrack.HANDYTRACKMAIN;
import net.handytrack.database.DBmanipulation;
import net.handytrack.database.DBquery;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class RegisterForm {
    private JFrame fr;
    private JTextField firstNameField, lastNameField, phoneNumberField, emailField, usernameField;
    private JPasswordField passwordField, passwordagainField;
    private JButton registerButton, backToLogin;
    private JPanel registerPanel, btnPanel, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13;
    private JLabel job;


    public RegisterForm(){
       // Initialize components
        fr = new JFrame("Register Form");
        firstNameField = new JTextField("First Name");
        lastNameField = new JTextField("Last Name");
        phoneNumberField = new JTextField("Phone Number");
        emailField = new JTextField("Email");
        usernameField = new JTextField("Username");
        passwordField = new JPasswordField("Password");
        passwordagainField = new JPasswordField("Enter Password Again");
        registerButton = new JButton("Register");
        backToLogin = new JButton("Back to Login");
        registerPanel = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel();
        p9 = new JPanel();
        p10 = new JPanel();
        p11 = new JPanel();
        p12 = new JPanel();
        p13 = new JPanel();
        job = new JLabel("Job Position");
        job.setFont(new Font("Arial", Font.BOLD, 16));
        p13.setLayout(new BorderLayout());
            p13.add(job,BorderLayout.WEST);
        String[] items = {"Delivery Man", "Officer"};
        JComboBox<String> combo;
        combo = new JComboBox<>(items);

        p2.setPreferredSize(new Dimension(400, 40));
        p3.setPreferredSize(new Dimension(50, 560));
        p4.setPreferredSize(new Dimension(50, 560));
        p5.setPreferredSize(new Dimension(400, 80));
        registerPanel.setPreferredSize(new Dimension(300, 520));

        // Set text field properties and listeners
        setConFirstNameField(firstNameField);
        setConLastNameField(lastNameField);
        setConPhoneNumberField(phoneNumberField);
        setConEmailField(emailField);
        setConUsernameField(usernameField);
        setConPasswordField(passwordField);
        setConPasswordAgainField(passwordagainField);
        setConJobPosition(combo);

        // Style GUI
        styleTextField(firstNameField);
        styleTextField(lastNameField);
        styleTextField(phoneNumberField);
        styleTextField(emailField);
        styleTextField(usernameField);
        styleTextField(passwordField);
        styleTextField(passwordagainField);
        stylePanel(p1);
        stylePanel(p2);
        stylePanel(p3);
        stylePanel(p4);
        stylePanel(p5);
        stylePanel(registerPanel);
        stylePanelRegisPanel(p6);
        stylePanelRegisPanel(p7);
        stylePanelRegisPanel(p8);
        stylePanelRegisPanel(p9);
        stylePanelRegisPanel(p10);
        stylePanelRegisPanel(p11);
        stylePanelRegisPanel(p12);
        stylePanelRegisPanel(p13);
        styleButton(backToLogin);
        backToLogin.setBackground(new Color(210, 224, 251));
        styleButton(registerButton);

        registerPanel.setSize(400, 300);
        registerPanel.setLayout(new GridLayout(15, 1));
        registerPanel.add(firstNameField);
        registerPanel.add(p6);
        registerPanel.add(lastNameField);
        registerPanel.add(p7);
        registerPanel.add(phoneNumberField);
        registerPanel.add(p8);
        registerPanel.add(emailField);
        registerPanel.add(p9);
        registerPanel.add(usernameField);
        registerPanel.add(p11);
        registerPanel.add(passwordField);
        registerPanel.add(p12);
        registerPanel.add(passwordagainField);
        registerPanel.add(p13);
        registerPanel.add(combo);

        p5.add(backToLogin);p5.add(registerButton);
        p1.setLayout(new BorderLayout());
            p1.add(p2,BorderLayout.NORTH);
        p1.add(p3,BorderLayout.WEST);p1.add(registerPanel,BorderLayout.CENTER);p1.add(p4,BorderLayout.EAST);
            p1.add(p5,BorderLayout.SOUTH);

        fr.add(p1);
        fr.setLocation(900, 200);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(400, 680);
        fr.setResizable(false);
        fr.setVisible(true);
        //back to login action listener
        backToLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource()==backToLogin){
                    new LoginEdit();
                    fr.dispose();
                }
            }
        });
        // Register button action listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Check conditions and perform registration
                if (isValidRegistration()) {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String phoneNumber = phoneNumberField.getText();
                    String email = emailField.getText();
                    String username = usernameField.getText();
                    String password = String.valueOf(passwordField.getPassword());
                    String selectedPosition = (String) combo.getSelectedItem();
                    try {
                        String sql = String.format("INSERT INTO login(name, surename, tel, email, username, password, jobposition) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",firstName,lastName,phoneNumber,email,username,password,selectedPosition);
                        DBmanipulation.getInstance().getUpdate(sql);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    // Perform registration process
                    JOptionPane.showMessageDialog(fr, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Display error message if registration conditions are not met
                    JOptionPane.showMessageDialog(fr, "Failed to register.", "Error", JOptionPane.ERROR_MESSAGE);
                    //check error
                    checkEmptyAndInvalidFields();
                }
            }

        });
    }
    private void setConFirstNameField(JTextField tf) {
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(tf.getText().equals("First Name") || tf.getText().equals("***Please fill the First Name***")){
                    tf.setCaretPosition(0);
                }
            }
            public void focusLost(){}

            @Override
            public void focusLost(FocusEvent e) {
                if (tf.getText().isEmpty() || tf.getText().equals("***Please fill the First Name***")) {
                    tf.setText("First Name");
                    tf.setForeground(Color.BLACK);
                }
            }
        });
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(tf.getText().equals("First Name") || tf.getText().equals("***Please fill the First Name***")){
                    tf.setText("");
                    tf.setForeground(Color.BLACK);
                }
            }
            public void keyReleased(){}
            public void keyPressed(){}
        });

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastNameField.requestFocusInWindow();
            }
        });
    }
    private void setConLastNameField(JTextField tf) {
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(tf.getText().equals("Last Name") || tf.getText().equals("***Please fill the Last Name***")){
                    tf.setCaretPosition(0);
                }
            }
            public void focusLost(){}

            @Override
            public void focusLost(FocusEvent e) {
                if (tf.getText().isEmpty() || tf.getText().equals("***Please fill the Last Name***")) {
                    tf.setText("Last Name");
                    tf.setForeground(Color.BLACK);
                }
            }
        });
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(tf.getText().equals("Last Name") || tf.getText().equals("***Please fill the Last Name***")){
                    tf.setText("");
                    tf.setForeground(Color.BLACK);
                }
            }
            public void keyReleased(){}
            public void keyPressed(){}
        });
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phoneNumberField.requestFocusInWindow();
            }
        });
    }
    private void setConPhoneNumberField(JTextField tf) {
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(tf.getText().equals("Phone Number") || tf.getText().equals("***Please fill the Phone Number***") || tf.getText().equals("***Invalid Phone Number***")){
                    tf.setCaretPosition(0);
                }
            }
            public void focusLost(){}

            @Override
            public void focusLost(FocusEvent e) {
                if (tf.getText().isEmpty() || tf.getText().equals("***Please fill the Phone Number***") || tf.getText().equals("***Invalid Phone Number***")) {
                    tf.setText("Phone Number");
                    tf.setForeground(Color.BLACK);
                }
            }
        });
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(tf.getText().equals("Phone Number") || tf.getText().equals("***Please fill the Phone Number***") || tf.getText().equals("***Invalid Phone Number***")){
                    tf.setText("");
                    tf.setForeground(Color.BLACK);
                }
            }
            public void keyReleased(){}
            public void keyPressed(){}
        });
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailField.requestFocusInWindow();
            }
        });
    }
    private void setConEmailField(JTextField tf) {
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(tf.getText().equals("Email") || tf.getText().equals("***Please fill the Email***") || tf.getText().equals("***Invalid Email***")){
                    tf.setCaretPosition(0);
                }
            }
            public void focusLost(){}

            @Override
            public void focusLost(FocusEvent e) {
                if (tf.getText().isEmpty() || tf.getText().equals("***Please fill the Email***") || tf.getText().equals("***Invalid Email***")) {
                    tf.setText("Email");
                    tf.setForeground(Color.BLACK);
                }
            }
        });
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(tf.getText().equals("Email") || tf.getText().equals("***Please fill the Email***") || tf.getText().equals("***Invalid Email***")){
                    tf.setText("");
                    tf.setForeground(Color.BLACK);
                }
            }
            public void keyReleased(){}
            public void keyPressed(){}
        });
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.requestFocusInWindow();
            }
        });
    }
    private void setConUsernameField(JTextField tf) {
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(tf.getText().equals("Username") || tf.getText().equals("***Please fill the Username***")){
                    tf.setCaretPosition(0);
                }
            }
            public void focusLost(){}

            @Override
            public void focusLost(FocusEvent e) {
                if (tf.getText().isEmpty() || tf.getText().equals("***Please fill the Username***")) {
                    tf.setText("Username");
                    tf.setForeground(Color.BLACK);
                }
            }
        });
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(tf.getText().equals("Username") || tf.getText().equals("***Please fill the Username***")){
                    tf.setText("");
                    tf.setForeground(Color.BLACK);
                }
            }
            public void keyReleased(){}
            public void keyPressed(){}
        });
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.requestFocusInWindow();
            }
        });
    }
    private void setConPasswordField(JTextField tf) {
        passwordField.setEchoChar((char) 0);
        tf.setText("Password");
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password") || String.valueOf(passwordField.getPassword()).equals("***Please fill the Password***") || String.valueOf(passwordField.getPassword()).equals("***Invalid Password***")) {
                    passwordField.setCaretPosition(0);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty() || String.valueOf(passwordField.getPassword()).equals("***Please fill the Password***") || String.valueOf(passwordField.getPassword()).equals("***Invalid Password***")) {
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText("Password");
                    passwordField.setForeground(Color.BLACK);
                }
            }
        });

        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password") || String.valueOf(passwordField.getPassword()).equals("***Please fill the Password***") || String.valueOf(passwordField.getPassword()).equals("***Invalid Password***")) {
                    passwordField.setText("");
                    passwordField.setEchoChar('\u25cf');
                    passwordField.setForeground(Color.BLACK);
                }
            }
        });
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordagainField.requestFocusInWindow();
            }
        });
    }
    private void setConPasswordAgainField(JTextField tf) {
        passwordagainField.setEchoChar((char) 0);passwordagainField.setText("Enter Password Again");
        passwordagainField.setEchoChar((char) 0);
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordagainField.getPassword()).equals("Enter Password Again") || String.valueOf(passwordagainField.getPassword()).equals("***Please fill the Password Again***") || String.valueOf(passwordagainField.getPassword()).equals("***Passwords doesn't match***")) {
                    passwordagainField.setCaretPosition(0);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordagainField.getPassword()).isEmpty() || String.valueOf(passwordagainField.getPassword()).equals("***Please fill the Password Again***") || String.valueOf(passwordagainField.getPassword()).equals("***Passwords doesn't match***")) {
                    passwordagainField.setEchoChar((char) 0);
                    passwordagainField.setText("Enter Password Again");
                    passwordagainField.setForeground(Color.BLACK);
                }
            }
        });

        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (String.valueOf(passwordagainField.getPassword()).equals("Enter Password Again") || String.valueOf(passwordagainField.getPassword()).equals("***Please fill the Password Again***") || String.valueOf(passwordagainField.getPassword()).equals("***Passwords doesn't match***")) {
                    passwordagainField.setText("");
                    passwordagainField.setEchoChar('\u25cf');
                    passwordagainField.setForeground(Color.BLACK);
                }
            }
        });
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButton.doClick();
            }
        });
    }
    private void setConJobPosition(JComboBox cm){
        cm.addActionListener(e -> {
            String selectedPosition = (String) cm.getSelectedItem();
            if (selectedPosition == null || selectedPosition.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a job position.", "Error", JOptionPane.ERROR_MESSAGE);
                cm.setSelectedIndex(-1);
            }
        });
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new RegisterForm();

    }

    //check empty all field
    private boolean isEmptyFirstNameField(JTextField tf) {
        return tf.getText().equals("First Name")||tf.getText().equals("***Please fill the First Name***");
    }
    private boolean isEmptyLasttNameField(JTextField tf) {
        return tf.getText().equals("Last Name")||tf.getText().equals("***Please fill the Last Name***");
    }private boolean isEmptyPhoneField(JTextField tf) {
        return tf.getText().equals("Phone Number")||tf.getText().equals("***Please fill the Phone Number***");
    }private boolean isEmptyEmailField(JTextField tf) {
        return tf.getText().equals("Email")||tf.getText().equals("***Please fill the Email***");
    }private boolean isEmptyUsernameField(JTextField tf) {
        return tf.getText().equals("Username")||tf.getText().equals("***Please fill the Username***");
    }private boolean isEmptyPasswordField(JTextField tf) {
        return tf.getText().equals("Password")||tf.getText().equals("***Please fill the Password***");
    }private boolean isEmptyPasswordAgainField(JTextField tf) {
        return tf.getText().equals("Enter Password Again")||tf.getText().equals("***Please fill the Password Again***");
    }
    //check invalid
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^0\\d{9}$");
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9+_\\-\\.]+@[a-zA-Z0-9+\\-]+\\.[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    private boolean hasLowerCase(String password) {
        return password.matches(".*[a-z].*");
    }

    private boolean hasUpperCase(String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean hasDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private boolean hasSpecialChar(String password) {
        return password.matches(".*[@#$%^&+=!_].*");
    }

    private boolean hasMinLength(String password) {
        return password.length() >= 8;
    }
    private boolean isValidPasswordField(JPasswordField passwordField) {
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        // Explanation of regex:
        // ^                 Start of string
        // (?=.*[a-z])       At least one lowercase letter
        // (?=.*[A-Z])       At least one uppercase letter
        // (?=.*\\d)         At least one digit
        // (?=.*[@#$%^&+=!_]) At least one special character from the given list
        // .{8,}             Minimum length of 8 characters
        // $                 End of string
        return hasLowerCase(password) &&
                hasUpperCase(password) &&
                hasDigit(password) &&
                hasSpecialChar(password) &&
                hasMinLength(password);
    }
    private boolean passwordFieldMatches(JPasswordField passwordField1, JPasswordField passwordField2) {
        return String.valueOf(passwordField1.getPassword()).equals(String.valueOf(passwordField2.getPassword()));
    }
    private boolean isUsernameDuplicate(String username) {
        // Connect to the database and check if username exists
        String sql = String.format("SELECT * FROM login WHERE username = '%s'", username);
        ResultSet rs = DBquery.getInstance().getSelect(sql);
        Boolean a =false;
        try {
            if (rs.next()){
                if (username.equals(rs.getString("username"))) {
                    a = true;
                }

            }
        } catch(SQLException e) {


        }finally {

        }
        return a;
    }
    private boolean isValidRegistration() {
        // Check if all fields are filled correctly
        return !isEmptyFirstNameField(firstNameField) &&
               !isEmptyLasttNameField(lastNameField) &&
               !isEmptyPhoneField(phoneNumberField) &&
               !isEmptyEmailField(emailField) &&
               !isEmptyUsernameField(usernameField) &&
               !isEmptyPasswordField(passwordField) &&
               !isEmptyPasswordAgainField(passwordagainField)&&
               isValidPhoneNumber(phoneNumberField.getText()) &&
               isValidEmail(emailField.getText()) &&
               isValidPasswordField(passwordField) &&
               isValidPasswordField(passwordagainField) &&
               passwordFieldMatches(passwordField, passwordagainField) &&
               !isUsernameDuplicate(usernameField.getText());  // Check if username is not duplicate
    }
    private void checkEmptyAndInvalidFields() {
        if (isEmptyFirstNameField(firstNameField)) {
            firstNameField.setText("***Please fill the First Name***");
            firstNameField.setForeground(Color.red);
        }
        if (isEmptyLasttNameField(lastNameField)) {
            lastNameField.setText("***Please fill the Last Name***");
            lastNameField.setForeground(Color.red);
        }
        if (isEmptyPhoneField(phoneNumberField)) {
            phoneNumberField.setText("***Please fill the Phone Number***");
            phoneNumberField.setForeground(Color.red);
        }
        else{
            if (!isValidPhoneNumber(phoneNumberField.getText())) {
                phoneNumberField.setText("***Invalid Phone Number***");
                phoneNumberField.setForeground(Color.red);
            }
        }
        if (isEmptyEmailField(emailField)) {
            emailField.setText("***Please fill the Email***");
            emailField.setForeground(Color.red);
        }
        else{
            if (!isValidEmail(emailField.getText())) {
                emailField.setText("***Invalid Email***");
                emailField.setForeground(Color.red);
            }
        }
        if (isEmptyUsernameField(usernameField)) {
            usernameField.setText("***Please fill the Username***");
            usernameField.setForeground(Color.red);
        }
        else{
            if (isUsernameDuplicate(usernameField.getText())) {
            JOptionPane.showMessageDialog(fr, "Username already exists.", null, JOptionPane.WARNING_MESSAGE);
        }
        }
        if (isEmptyPasswordField(passwordField)) {
            passwordField.setEchoChar((char) 0);
            passwordField.setText("***Please fill the Password***");
            passwordField.setForeground(Color.red);
        }
        else{
            if (!isValidPasswordField(passwordField)) {
                passwordField.setEchoChar((char) 0);
                passwordField.setText("***Invalid Password***");
                JOptionPane.showMessageDialog(fr, "Password should contain at least 8 character with aA-zZ,0-9,and special charactor", null, JOptionPane.WARNING_MESSAGE);
                passwordField.setForeground(Color.red);
            }
        }
        if (isEmptyPasswordAgainField(passwordagainField)) {
            passwordagainField.setEchoChar((char) 0);
            passwordagainField.setText("***Please fill the Password Again***");
            passwordagainField.setForeground(Color.red);
        }
        else{
            if (!passwordFieldMatches(passwordField, passwordagainField)) {
                passwordagainField.setEchoChar((char) 0);
                passwordagainField.setText("***Passwords doesn't match***");
                passwordagainField.setForeground(Color.red);
            }
        }

    }
    private void styleTextField(JTextField tf){
        tf.setOpaque(false);
        tf.setForeground(Color.BLACK); // Set text color
        tf.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Bottom border only
        tf.setFont(new Font("Arial", Font.PLAIN, 12));
    }
    private void stylePanel(JPanel p){
        p.setOpaque(true);
        p.setBackground(new Color(210, 224, 251));
        p.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK)); // Bottom border only

    }
    private void stylePanelRegisPanel(JPanel p){
        p.setPreferredSize(new Dimension(300, 40));
        p.setOpaque(true);
        p.setBackground(new Color(210, 224, 251));
        p.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
    }
    private void styleButton(JButton b){
        b.setFont(new Font("Arial", Font.BOLD, 16));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.ORANGE); // Set text color
        b.setPreferredSize(new Dimension(150, 40));
    }

}