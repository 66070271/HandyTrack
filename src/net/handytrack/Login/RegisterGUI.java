package net.handytrack.Login;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class RegisterGUI {
    //private DBConnect db;
    private JFrame fr;
    private JTextField firstNameField, lastNameField, phoneNumberField, emailField, usernameField;
    private JPasswordField passwordField, passwordagainField;
    private JButton registerButton;
    
    public RegisterGUI() {
        fr = new JFrame("Registration");
        firstNameField = new JTextField("First Name");
        firstNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(firstNameField.getText().equals("First Name") || firstNameField.getText().equals("***Please fill the First Name***")){
                    firstNameField.setCaretPosition(0);
                }
            }
            public void focusLost(){}

            @Override
            public void focusLost(FocusEvent e) {
                if (firstNameField.getText().isEmpty()|| firstNameField.getText().equals("***Please fill the First Name***")) {
                    firstNameField.setText("First Name");
                }
            }
        });
        firstNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(firstNameField.getText().equals("First Name")|| firstNameField.getText().equals("***Please fill the First Name***")){
                    firstNameField.setText("");
                    firstNameField.setForeground(Color.BLACK);
                }
            }
            public void keyReleased(){}
            public void keyPressed(){}
        });
        firstNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastNameField.requestFocusInWindow();
            }
        });
        lastNameField = new JTextField("Last Name");
        lastNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(lastNameField.getText().equals("Last Name") || lastNameField.getText().equals("***Please fill the Last Name***")){
                    lastNameField.setCaretPosition(0);
                }
            }
            public void focusLost(){}
//
            @Override
            public void focusLost(FocusEvent e) {
                if (lastNameField.getText().isEmpty() || lastNameField.getText().equals("***Please fill the Last Name***")) {
                    lastNameField.setText("Last Name");
                }
            }
        });
        lastNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(lastNameField.getText().equals("Last Name") || lastNameField.getText().equals("***Please fill the Last Name***")){
                    lastNameField.setText("");
                    lastNameField.setForeground(Color.BLACK);
                }
            }
            public void keyReleased(){}
            public void keyPressed(){}
        });
        lastNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phoneNumberField.requestFocusInWindow();
            }
        });
        phoneNumberField = new JTextField("Phone Number");
        phoneNumberField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(phoneNumberField.getText().equals("Phone Number")){
                    phoneNumberField.setCaretPosition(0);
                }
            }
            public void focusLost(){}

            @Override
            public void focusLost(FocusEvent e) {
                if (phoneNumberField.getText().isEmpty()) {
                    phoneNumberField.setText("Phone Number");
                }
            }
        });
        phoneNumberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(phoneNumberField.getText().equals("Phone Number")){
                    phoneNumberField.setText("");
                }
            }
            public void keyReleased(){}
            public void keyPressed(){}
        });
        phoneNumberField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailField.requestFocusInWindow();
            }
        });
        emailField = new JTextField("Email");
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(emailField.getText().equals("Email")){
                    emailField.setCaretPosition(0);
                }
            }
            public void focusLost(){}

            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setText("Email");
                }
            }
        });
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(emailField.getText().equals("Email")){
                    emailField.setText("");
                }
            }
            public void keyReleased(){}
            public void keyPressed(){}
        });
        emailField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.requestFocusInWindow();
            }
        });
        usernameField = new JTextField("Username");
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(usernameField.getText().equals("Username")){
                    usernameField.setCaretPosition(0);
                }
            }
            public void focusLost(){}

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Username");
                }
            }
        });
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(usernameField.getText().equals("Username")){
                    usernameField.setText("");
                }
            }
            public void keyReleased(){}
            public void keyPressed(){}
        });
        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.requestFocusInWindow();
            }
        });
        passwordField = new JPasswordField("Password");
        passwordField.setEchoChar((char) 0);passwordField.setText("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    passwordField.setCaretPosition(0);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText("Password");
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                    passwordField.setEchoChar('\u25cf');
                }
            }
        });
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordagainField.requestFocusInWindow();
            }
        });
        passwordagainField = new JPasswordField("Enter Password Again");
        passwordagainField.setEchoChar((char) 0);passwordagainField.setText("Enter Password Again");
        passwordagainField.setEchoChar((char) 0);
        passwordagainField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordagainField.getPassword()).equals("Enter Password Again")) {
                    passwordagainField.setCaretPosition(0);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordagainField.getPassword()).isEmpty()) {
                    passwordagainField.setEchoChar((char) 0);
                    passwordagainField.setText("Enter Password Again");
                }
            }
        });

        passwordagainField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (String.valueOf(passwordagainField.getPassword()).equals("Enter Password Again")) {
                    passwordagainField.setText("");
                    passwordagainField.setEchoChar('\u25cf');
                }
            }
        });
        passwordagainField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButton.doClick(); 
            }
        });
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String passwordAgain = String.valueOf(passwordagainField.getPassword());

                // Check if all fields are filled
                if (firstName.equals("First Name")){
                    firstNameField.setText("***Please fill the First Name***");
                    firstNameField.setForeground(Color.red);
                }
                if (lastName.equals("Last Name")){
                    lastNameField.setText("***Please fill the Last Name***");
                    lastNameField.setForeground(Color.red);
                }
                if (firstName.equals("First Name")){
                    firstNameField.setText("***Please fill the First Name***");
                    firstNameField.setForeground(Color.red);
                }
                if (firstName.equals("First Name")){
                    firstNameField.setText("***Please fill the First Name***");
                    firstNameField.setForeground(Color.red);
                }
                if (firstName.equals("First Name")){
                    firstNameField.setText("***Please fill the First Name***");
                    firstNameField.setForeground(Color.red);
                }
                if (firstName.equals("First Name")){
                    firstNameField.setText("***Please fill the First Name***");
                    firstNameField.setForeground(Color.red);
                }
                if (firstName.equals("First Name")){
                    firstNameField.setText("***Please fill the First Name***");
                    firstNameField.setForeground(Color.red);
                }
                
            }
                
                // Check if passwords match
                // Check if username already exists
                // Check if email format is valid
                // Insert new user into the database
                
        });     
        
        
        
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridLayout(8, 1));
        registerPanel.add(firstNameField);
        registerPanel.add(lastNameField);
        registerPanel.add(phoneNumberField);
        registerPanel.add(emailField);
        registerPanel.add(usernameField);
        registerPanel.add(passwordField);
        registerPanel.add(passwordagainField);
        registerPanel.add(registerButton);

        fr.add(registerPanel);
        fr.setLocation(900, 200);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(400, 300);
        fr.setVisible(true);
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new RegisterGUI();

    }
    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        passwordagainField.setText("");
    }
    
}
