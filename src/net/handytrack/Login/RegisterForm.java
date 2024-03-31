package net.handytrack.Login;

import net.handytrack.database.DBmanipulation;
import net.handytrack.database.DBquery;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterForm {
    private JFrame fr;
    private JTextField firstNameField, lastNameField, phoneNumberField, emailField, usernameField;
    private JPasswordField passwordField, passwordagainField;
    private JButton registerButton;


    public RegisterForm() {
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

        // Set text field properties and listeners
        setConFirstNameField(firstNameField);
        setConPhoneNumberField(firstNameField);
        setConLastNameField(lastNameField);
        setConPhoneNumberField(emailField);
        setConEmailField(emailField);
        setConUsernameField(usernameField);
        setConPasswordField(passwordField);
        setConPasswordAgainField(passwordagainField);

        // Style GUI
        JPanel registerPanel = new JPanel();
        registerPanel.setSize(400, 300);
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
        fr.setResizable(false);
        fr.setVisible(true);

        // Register button action listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Check conditions and perform registration
                if (isValidRegistration()) {
                    // Perform registration process
                    JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Display error message if registration conditions are not met
                    JOptionPane.showMessageDialog(null, "Failed to register.", "Error", JOptionPane.ERROR_MESSAGE);
                    //check error
                    checkEmptyFields();
                    checkInvalidFields();


                }


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

    private void setConFirstNameField(JTextField tf) {
        firstNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (firstNameField.getText().equals("First Name") || firstNameField.getText().equals("***Please fill the First Name***")) {
                    firstNameField.setCaretPosition(0);
                }
            }

            public void focusLost() {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (firstNameField.getText().isEmpty() || firstNameField.getText().equals("***Please fill the First Name***")) {
                    firstNameField.setText("First Name");
                    firstNameField.setForeground(Color.BLACK);
                }
            }
        });
        firstNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (firstNameField.getText().equals("First Name") || lastNameField.getText().equals("***Please fill the First Name***")) {
                    firstNameField.setText("");
                    firstNameField.setForeground(Color.BLACK);
                }
            }

            public void keyReleased() {
            }

            public void keyPressed() {
            }
        });

        firstNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastNameField.requestFocusInWindow();
            }
        });
    }

    private void setConLastNameField(JTextField tf) {
        lastNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (lastNameField.getText().equals("Last Name") || lastNameField.getText().equals("***Please fill the Last Name***")) {
                    lastNameField.setCaretPosition(0);
                }
            }

            public void focusLost() {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (lastNameField.getText().isEmpty() || lastNameField.getText().equals("***Please fill the Last Name***")) {
                    lastNameField.setText("Last Name");
                    lastNameField.setForeground(Color.BLACK);
                }
            }
        });
        lastNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (lastNameField.getText().equals("Last Name") || lastNameField.getText().equals("***Please fill the Last Name***")) {
                    lastNameField.setText("");
                    lastNameField.setForeground(Color.BLACK);
                }
            }

            public void keyReleased() {
            }

            public void keyPressed() {
            }
        });
        lastNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phoneNumberField.requestFocusInWindow();
            }
        });
    }

    private void setConPhoneNumberField(JTextField tf) {
        phoneNumberField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneNumberField.getText().equals("Phone Number") || phoneNumberField.getText().equals("***Please fill the Phone Number***") || phoneNumberField.getText().equals("***Invalid Phone Number***")) {
                    phoneNumberField.setCaretPosition(0);
                }
            }

            public void focusLost() {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (phoneNumberField.getText().isEmpty() || phoneNumberField.getText().equals("***Please fill the Phone Number***") || phoneNumberField.getText().equals("***Invalid Phone Number***")) {
                    phoneNumberField.setText("Phone Number");
                    phoneNumberField.setForeground(Color.BLACK);
                }
            }
        });
        phoneNumberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (phoneNumberField.getText().equals("Phone Number") || phoneNumberField.getText().equals("***Please fill the Phone Number***") || phoneNumberField.getText().equals("***Invalid Phone Number***")) {
                    phoneNumberField.setText("");
                    phoneNumberField.setForeground(Color.BLACK);
                }
            }

            public void keyReleased() {
            }

            public void keyPressed() {
            }
        });
        phoneNumberField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailField.requestFocusInWindow();
            }
        });
    }

    private void setConEmailField(JTextField tf) {
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("Email") || emailField.getText().equals("***Please fill the Email***")) {
                    emailField.setCaretPosition(0);
                }
            }

            public void focusLost() {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty() || emailField.getText().equals("***Please fill the Email***")) {
                    emailField.setText("Email");
                    emailField.setForeground(Color.BLACK);
                }
            }
        });
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (emailField.getText().equals("Email") || emailField.getText().equals("***Please fill the Email***")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }

            public void keyReleased() {
            }

            public void keyPressed() {
            }
        });
        emailField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.requestFocusInWindow();
            }
        });
    }

    private void setConUsernameField(JTextField tf) {
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username") || usernameField.getText().equals("***Please fill the Username***")) {
                    usernameField.setCaretPosition(0);
                }
            }

            public void focusLost() {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty() || usernameField.getText().equals("***Please fill the Username***")) {
                    usernameField.setText("Username");
                    usernameField.setForeground(Color.BLACK);
                }
            }
        });
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (usernameField.getText().equals("Username") || usernameField.getText().equals("***Please fill the Username***")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            public void keyReleased() {
            }

            public void keyPressed() {
            }
        });
        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.requestFocusInWindow();
            }
        });
    }

    private void setConPasswordField(JTextField tf) {
        passwordField.setEchoChar((char) 0);
        passwordField.setText("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password") || String.valueOf(passwordField.getPassword()).equals("***Please fill the Password***")) {
                    passwordField.setCaretPosition(0);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty() || String.valueOf(passwordField.getPassword()).equals("***Please fill the Password***")) {
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText("Password");
                    passwordField.setForeground(Color.BLACK);
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password") || String.valueOf(passwordField.getPassword()).equals("***Please fill the Password***")) {
                    passwordField.setText("");
                    passwordField.setEchoChar('\u25cf');
                    passwordField.setForeground(Color.BLACK);
                }
            }
        });
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordagainField.requestFocusInWindow();
            }
        });
    }

    private void setConPasswordAgainField(JTextField tf) {
        passwordagainField.setEchoChar((char) 0);
        passwordagainField.setText("Enter Password Again");
        passwordagainField.setEchoChar((char) 0);
        passwordagainField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordagainField.getPassword()).equals("Enter Password Again") || String.valueOf(passwordagainField.getPassword()).equals("***Please fill the Password Again***")) {
                    passwordagainField.setCaretPosition(0);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordagainField.getPassword()).isEmpty() || String.valueOf(passwordagainField.getPassword()).equals("***Please fill the Password Again***")) {
                    passwordagainField.setEchoChar((char) 0);
                    passwordagainField.setText("Enter Password Again");
                    passwordagainField.setForeground(Color.BLACK);
                }
            }
        });

        passwordagainField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (String.valueOf(passwordagainField.getPassword()).equals("Enter Password Again") || String.valueOf(passwordagainField.getPassword()).equals("***Please fill the Password Again***")) {
                    passwordagainField.setText("");
                    passwordagainField.setEchoChar('\u25cf');
                    passwordagainField.setForeground(Color.BLACK);
                }
            }
        });
        passwordagainField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButton.doClick();
            }
        });
    }

    //check empty all field
    private boolean isEmptyFirstNameField(JTextField tf) {
        return tf.getText().equals("First Name");
    }

    private boolean isEmptyLasttNameField(JTextField tf) {
        return tf.getText().equals("Last Name");
    }

    private boolean isEmptyPhoneField(JTextField tf) {
        return tf.getText().equals("Phone Number");
    }

    private boolean isEmptyEmailField(JTextField tf) {
        return tf.getText().equals("Email");
    }

    private boolean isEmptyUsernameField(JTextField tf) {
        return tf.getText().equals("Username");
    }

    private boolean isEmptyPasswordField(JTextField tf) {
        return tf.getText().equals("Password");
    }

    private boolean isEmptyPasswordAgainField(JTextField tf) {
        return tf.getText().equals("Enter Password Again");
    }

    //check invalid
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^0\\d{9}$");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPasswordField(JPasswordField passwordField) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return String.valueOf(passwordField.getPassword()).matches(passwordRegex);
    }

    private boolean passwordFieldMatches(JPasswordField passwordField1, JPasswordField passwordField2) {
        return String.valueOf(passwordField1.getPassword()).equals(String.valueOf(passwordField2.getPassword()));
    }

    private boolean isUsernameDuplicate(String username) {
        // Connect to the database and check if username exists

        ResultSet resultSet = DBquery.getInstance().getSelect("SELECT * FROM login WHERE username='" + username + "'");
        try {
            if (resultSet.next()) {
                // Username exists in the database
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBmanipulation.getInstance().disconnect(); // Disconnect from the database
        }
        return false;
    }

    private boolean isValidRegistration() {
        // Check if all fields are filled correctly
        return !isEmptyFirstNameField(firstNameField) &&
                !isEmptyLasttNameField(lastNameField) &&
                !isEmptyPhoneField(phoneNumberField) &&
                !isEmptyEmailField(emailField) &&
                !isEmptyUsernameField(usernameField) &&
                !isEmptyPasswordField(passwordField) &&
                !isEmptyPasswordAgainField(passwordagainField) &&
                isValidPhoneNumber(phoneNumberField.getText()) &&
                isValidEmail(emailField.getText()) &&
                isValidPasswordField(passwordField) &&
                isValidPasswordField(passwordagainField) &&
                passwordFieldMatches(passwordField, passwordagainField) &&
                !isUsernameDuplicate(usernameField.getText());  // Check if username is not duplicate
    }

    private void checkEmptyFields() {
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
        } else {
            if (!isValidPhoneNumber(phoneNumberField.getText())) {
                phoneNumberField.setText("***Invalid Phone Number***");
                phoneNumberField.setForeground(Color.red);
            }
        }
        if (isEmptyEmailField(emailField)) {
            emailField.setText("***Please fill the Email***");
            emailField.setForeground(Color.red);
        }
        if (isEmptyUsernameField(usernameField)) {
            usernameField.setText("***Please fill the Username***");
            usernameField.setForeground(Color.red);
        }
        if (isEmptyPasswordField(passwordField)) {
            passwordField.setEchoChar((char) 0);
            passwordField.setText("***Please fill the Password***");
            passwordField.setForeground(Color.red);
        }
        if (isEmptyPasswordAgainField(passwordagainField)) {
            passwordagainField.setEchoChar((char) 0);
            passwordagainField.setText("***Please fill the Password Again***");
            passwordagainField.setForeground(Color.red);
        }
    }

    private void checkInvalidFields() {

        if (!isValidEmail(emailField.getText())) {
            emailField.setText("***Invalid Email***");
            emailField.setForeground(Color.red);
        }
        if (!isValidPasswordField(passwordField)) {
            passwordField.setEchoChar((char) 0);
            passwordField.setText("***Invalid Password***");
            passwordField.setForeground(Color.red);
        }
        if (!passwordFieldMatches(passwordField, passwordagainField)) {
            passwordagainField.setEchoChar((char) 0);
            passwordagainField.setText("***Passwords do not match***");
            passwordagainField.setForeground(Color.red);
        }
        if (isUsernameDuplicate(usernameField.getText())) {
            usernameField.setText("***Username already exists***");
            usernameField.setForeground(Color.red);
        }
    }

}