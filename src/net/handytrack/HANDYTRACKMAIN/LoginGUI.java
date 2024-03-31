package net.handytrack.HANDYTRACKMAIN;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import net.handytrack.database.DBquery;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class LoginGUI {

    //private DBConnect db;
    private int userId;
    private String name;
    private String num;
    private JFrame fr;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signinButton, signupButton, forgotPasswordButton;
    private JLabel welcomeLabel, prolabel, locklabel, logolabel;
    private JPanel pwel, ppro, ppas, plogo, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30;
    private ImageIcon pro, scalepro, lock, scalelock, logo, scalelogo;
    private HANDYTRACKMAIN hdm;

    @SuppressWarnings("empty-statement")
    public LoginGUI() {
        hdm = new HANDYTRACKMAIN();
        fr = new JFrame("Handy Track's LOGIN");
        this.userId = userId;
        prolabel = new JLabel();
        locklabel = new JLabel();
        logolabel = new JLabel();
        welcomeLabel = new JLabel("Welcome to HANDY TRACK", (int) Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(new Color(210, 224, 251));
        //welcomeLabel.setBackground(new Color(210, 224, 251)); // Set bg color
        welcomeLabel.setForeground(new Color(9, 55, 125)); // Set text color
        usernameField = new JTextField("Username");
        usernameField.setColumns(15); // Set the desired length
        usernameField.setOpaque(false);
        usernameField.setForeground(Color.BLACK); // Set text color
        usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Bottom border only
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setCaretPosition(0);
                }
            }

            public void focusLost() {
            }

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
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                }
            }

            public void keyReleased() {
            }

            public void keyPressed() {
            }
        });

        passwordField = new JPasswordField();
        passwordField.setColumns(15); // Set the desired length
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.BLACK); // Set text color
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Bottom border only
        passwordField.setEchoChar((char) 0);
        passwordField.setText("Password");
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

        forgotPasswordButton = new JButton("forgot Password?");
        forgotPasswordButton.setContentAreaFilled(false);
        forgotPasswordButton.setFont(new Font("Arial", Font.BOLD, 10));
        forgotPasswordButton.setForeground(Color.BLACK); // Set text color
        forgotPasswordButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Set black border on the bottom
        //forgotPasswordButton.setBorderPainted(false); 
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(fr, "Contact the relevant person to request a key \n\n Enter organization's key", "Forgot Password", JOptionPane.QUESTION_MESSAGE);
                if ("organization_key".equals(input)) {
                    JOptionPane.showMessageDialog(fr, "Your password is: *******");
                } else {
                    JOptionPane.showMessageDialog(fr, "Wrong Password!!!", "Invalid organization's key", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        forgotPasswordButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                forgotPasswordButton.setForeground(Color.RED); // Set text color 
            }

            @Override
            public void focusLost(FocusEvent e) {
                forgotPasswordButton.setForeground(Color.BLACK); // Set text color
            }
        });

        signinButton = new JButton("Sign-in");
        signinButton.setFont(new Font("Arial", Font.BOLD, 16));
        signinButton.setForeground(Color.BLUE);
        signinButton.setBackground(new Color(247, 195, 21));
        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == signinButton) {
                    if (loginuser()) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        hdm.getFrame().setVisible(true);
                        fr.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                }
            }
        });

        signupButton = new JButton("Sign-up");
        signupButton.setBackground(new Color(210, 224, 251));
        signupButton.setFont(new Font("Arial", Font.BOLD, 10));
        signupButton.setForeground(Color.BLACK); // Set text color
        signupButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Set black border on the bottom
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(fr, "Contact the relevant person to request a key \n\n Enter organization's key", "Before Register", JOptionPane.QUESTION_MESSAGE);

                if ("organization_key".equals(input)) {
                    RegisterGUI registerGUI = new RegisterGUI();
                } else {
                    JOptionPane.showMessageDialog(fr, "Wrong Password!!!", "Invalid organization's key", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        pwel = new JPanel();
        ppro = new JPanel();
        ppas = new JPanel();
        plogo = new JPanel();
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
        p14 = new JPanel();
        p15 = new JPanel();
        p16 = new JPanel();
        p17 = new JPanel();
        p18 = new JPanel();
        p19 = new JPanel();
        p20 = new JPanel();
        p21 = new JPanel();
        p22 = new JPanel();
        p23 = new JPanel();
        p24 = new JPanel();
        p25 = new JPanel();
        p26 = new JPanel();
        p27 = new JPanel();
        p28 = new JPanel();
        p29 = new JPanel();
        p30 = new JPanel();

        // Set preferred sizes for JLabels
        prolabel.setPreferredSize(new Dimension(25, 25));
        locklabel.setPreferredSize(new Dimension(25, 25));
        logolabel.setPreferredSize(new Dimension(50, 50));

        // Load image icons
        pro = new ImageIcon("resources/Picture/user.png");
        lock = new ImageIcon("resources/Picture/lock.png");
        logo = new ImageIcon("resources/Picture/LOGO.png");

        // Scale image icons
//        scalepro = ImageUtils.getScaledImageIcon(pro, prolabel.getPreferredSize().width, prolabel.getPreferredSize().height);
//        scalelock = ImageUtils.getScaledImageIcon(lock, locklabel.getPreferredSize().width, locklabel.getPreferredSize().height);
        // Set icons to JLabels
        prolabel.setIcon(scalepro);
        locklabel.setIcon(scalelock);
        logolabel.setIcon(logo);

        //////////STYLE GUI///////////////////////////////////////
        Border shadowBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2), // shadow
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        p3.setBorder(shadowBorder);
        p3.setBackground(Color.WHITE);
        ppro.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
        ppas.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        //p4.setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, Color.BLACK));
        pwel.setOpaque(true);
        pwel.setBackground(new Color(210, 224, 251));
        plogo.setOpaque(true);
        plogo.setBackground(new Color(210, 224, 251));
        p1.setOpaque(true);
        p1.setBackground(new Color(210, 224, 251));
        p2.setOpaque(true);
        p2.setBackground(new Color(210, 224, 251));
        p5.setOpaque(true);
        p5.setBackground(new Color(210, 224, 251));
        p6.setOpaque(true);
        p6.setBackground(new Color(210, 224, 251));
        p7.setOpaque(true);
        p7.setBackground(new Color(210, 224, 251));
        p8.setOpaque(true);
        p8.setBackground(new Color(210, 224, 251));
        p9.setOpaque(true);
        p9.setBackground(new Color(210, 224, 251));
        p10.setOpaque(true);
        p10.setBackground(new Color(210, 224, 251));
        p11.setOpaque(true);
        p11.setBackground(new Color(210, 224, 251));

        pwel.setPreferredSize(new Dimension(350, 100));
        p1.setPreferredSize(new Dimension(50, 200));
        p2.setPreferredSize(new Dimension(50, 200));
        p3.setPreferredSize(new Dimension(250, 100));
        p4.setPreferredSize(new Dimension(350, 100));
        p5.setPreferredSize(new Dimension(350, 25));
        //p6.setPreferredSize(new Dimension(175, 50));
        //p7.setPreferredSize(new Dimension(175, 50));
        p8.setPreferredSize(new Dimension(120, 75));
        p9.setPreferredSize(new Dimension(115, 75));
        p10.setPreferredSize(new Dimension(115, 75));
        // p11.setPreferredSize(new Dimension(350,50));
        p7.add(forgotPasswordButton);
        p11.add(signupButton);
        plogo.add(logolabel);

        ppro.add(prolabel);
        ppro.add(usernameField);
        ppas.add(locklabel);
        ppas.add(passwordField);
        p5.setLayout(new GridLayout(1, 1));
//        p5.add(p6);
        p5.add(p7);
        p8.setLayout(new GridLayout(2, 1));
        p8.add(signinButton);
        p8.add(p11);

        pwel.setLayout(new GridLayout(2, 1));
        pwel.add(welcomeLabel);
        pwel.add(plogo);

        p3.setLayout(new GridLayout(2, 1));
        p3.add(ppro);
        p3.add(ppas);
        p4.setLayout(new BorderLayout());
        p4.add(p5, BorderLayout.NORTH);
        p4.add(p8, BorderLayout.CENTER);
        p4.add(p9, BorderLayout.WEST);
        p4.add(p10, BorderLayout.EAST);

        fr.setLayout(new BorderLayout());
        fr.add(pwel, BorderLayout.NORTH);
        fr.add(p1, BorderLayout.WEST);
        fr.add(p2, BorderLayout.EAST);
        fr.add(p3, BorderLayout.CENTER);
        fr.add(p4, BorderLayout.SOUTH);

        /////////////////////////////////////////////////////////////////
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(350, 400);
        fr.setLocation(600, 200);
        fr.setResizable(false);
        fr.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new LoginGUI();

    }

    public Boolean loginuser() {
        String name = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        String sql = String.format("SELECT * FROM login WHERE username = '%s' and password = '%s'", name, pass);
        ResultSet rs = DBquery.getInstance().getSelect(sql);
        try {
            if (rs.next()) {
                userId = rs.getInt("iduser"); // Store the userId if login is successful
                String sql1 = String.format("SELECT * FROM login WHERE iduser = '%d'", userId);
                ResultSet rs1 = DBquery.getInstance().getSelect(sql1);
                if (rs1.getString("password").equals(pass)) {
                    hdm.setKeyuser(this.userId);
                    hdm.fetchUser();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getuserId() {
        return userId;
    }

}
