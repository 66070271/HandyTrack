package net.handytrack.HANDYTRACKMAIN;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import net.handytrack.database.DBmanipulation;
import net.handytrack.database.DBquery;
import net.handytrack.tracker.RealTrack;
import org.sqlite.core.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginEdit implements ActionListener{
    private int userId;
    private String name;
    private String num;
    private JFrame fr;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signinButton, signupButton, forgotPasswordButton, guestModeButton;
    private JLabel welcomeLabel, prolabel, locklabel, logolabel;
    private JPanel pwel, ppro, ppas, plogo, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
    private ImageIcon pro, scalepro, lock, scalelock, logo;
    private HANDYTRACKMAIN hdm;

    public LoginEdit(){
        hdm = new HANDYTRACKMAIN();
        fr = new JFrame("Handy Track's LOGIN");
        this.userId = userId;
        prolabel = new JLabel();
        locklabel = new JLabel();
        logolabel = new JLabel();
        welcomeLabel = new JLabel("Welcome to HANDY TRACK", (int) Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setOpaque(true);
        welcomeLabel.setForeground(Color.ORANGE);
        welcomeLabel.setBackground(new Color(210, 224, 251));

        usernameField = new JTextField("Username");


        passwordField = new JPasswordField();


        forgotPasswordButton = new JButton("forgot Password?");

        guestModeButton = new JButton("Guest Mode");


        signinButton = new JButton("Sign-in");


        signupButton = new JButton("Sign-up");

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

        // Set preferred sizes for JLabels
        prolabel.setPreferredSize(new Dimension(25, 25));
        locklabel.setPreferredSize(new Dimension(25, 25));
        logolabel.setPreferredSize(new Dimension(50, 50));

        // Load image icons
        pro = new ImageIcon("resources/Picture/user.png");
        lock = new ImageIcon("resources/Picture/lock.png");
        logo = new ImageIcon("resources/Picture/LOGO.png");

        // Scale image icons
        scalepro = ImageUtils.getScaledImageIcon(pro, prolabel.getPreferredSize().width, prolabel.getPreferredSize().height);
        scalelock = ImageUtils.getScaledImageIcon(lock, locklabel.getPreferredSize().width, locklabel.getPreferredSize().height);
        // Set icons to JLabels
        prolabel.setIcon(scalepro);
        locklabel.setIcon(scalelock);
        logolabel.setIcon(logo);

        //////////STYLE GUI///////////////////////////////////////

        p3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        p3.setBackground(Color.WHITE);
        ppro.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
        ppas.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        setConUsernameField(usernameField);
        setConPasswordField(passwordField);
        setConSigninButton(signinButton);
        setConSignupButton(signupButton);

        setConForgotButton(forgotPasswordButton);
        stylePanel(pwel);
        stylePanel(plogo);
        stylePanel(p1);
        stylePanel(p2);
        stylePanel(p5);
        stylePanel(p6);
        stylePanel(p7);
        stylePanel(p8);
        stylePanel(p9);
        stylePanel(p10);
        stylePanel(p11);
        guestModeButton.addActionListener(this);

        pwel.setPreferredSize(new Dimension(350, 100));
        p1.setPreferredSize(new Dimension(50, 200));
        p2.setPreferredSize(new Dimension(50, 200));
        p3.setPreferredSize(new Dimension(250, 100));
        p4.setPreferredSize(new Dimension(350, 100));
        p5.setPreferredSize(new Dimension(350, 25));
        p6.setPreferredSize(new Dimension(175, 50));
        p8.setPreferredSize(new Dimension(350, 75));

        p9.add(guestModeButton);p9.add(signinButton);
        p10.add(signupButton);
        p7.add(forgotPasswordButton);

        plogo.add(logolabel);

        ppro.add(prolabel);
        ppro.add(usernameField);
        ppas.add(locklabel);
        ppas.add(passwordField);
        p5.setLayout(new GridLayout(1, 1));
        p5.add(p6);
        p5.add(p7);
        p8.setLayout(new GridLayout(2, 1));
        p8.add(p9);
        p8.add(p10);

        pwel.setLayout(new GridLayout(2, 1));
        pwel.add(welcomeLabel);
        pwel.add(plogo);

        p3.setLayout(new GridLayout(2, 1));
        p3.add(ppro);
        p3.add(ppas);
        p4.setLayout(new BorderLayout());
        p4.add(p5, BorderLayout.NORTH);
        p4.add(p8, BorderLayout.CENTER);


        fr.setLayout(new BorderLayout());
        fr.add(pwel, BorderLayout.NORTH);
        fr.add(p1, BorderLayout.WEST);
        fr.add(p2, BorderLayout.EAST);
        fr.add(p3, BorderLayout.CENTER);
        fr.add(p4, BorderLayout.SOUTH);

        /////////////////////////////////////////////////////////////////
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(350, 400);
        fr.setLocation(800, 300);
        fr.setResizable(false);
        fr.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new LoginEdit();
    }

    public Boolean loginuser() {
        String name = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        String sql = String.format("SELECT * FROM login WHERE username = '%s' and password = '%s'", name, pass);
        DBquery db  = new DBquery();
        ResultSet rs = db.getSelect(sql);
        try{
            userId = rs.getInt("iduser");
        }catch(SQLException ex){
        }
        String sql1 = String.format("SELECT * FROM login WHERE iduser = '%d'", userId);
        DBquery db1  = new DBquery();
        ResultSet rs1 = db1.getSelect(sql1);
        try {
            if (rs.next()) {
                 // Store the userId if login is successful
                if (rs1.getString("password").equals(pass)) {
                    hdm.setKeyuser(this.userId);
                    hdm.fetchUser();

                    return true;

                } else {

                    return false;
                }

            } else {
                DBquery.getInstance().disconnect();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            db1.disconnect();
            db.disconnect();
        }

    }
    private void setWelcomeLabel(JLabel l){
        l.setFont(new Font("Arial", Font.BOLD, 24));
        l.setOpaque(true);
        l.setBackground(new Color(210, 224, 251));
        l.setForeground(new Color(9, 55, 125)); // Set text color
    }
    private void setConUsernameField(JTextField tf){
        tf.setColumns(15); // Set the desired length
        tf.setOpaque(false);
        tf.setForeground(Color.BLACK); // Set text color
        tf.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Bottom border only
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tf.getText().equals("Username")) {
                    tf.setCaretPosition(0);
                }
            }

            public void focusLost() {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tf.getText().isEmpty()) {
                    tf.setText("Username");
                }
            }
        });
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (tf.getText().equals("Username")) {
                    tf.setText("");
                }
            }
        });
    }
    private void setConPasswordField(JTextField tf){
        tf.setColumns(15); // Set the desired length
        tf.setOpaque(false);
        tf.setForeground(Color.BLACK); // Set text color
        tf.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Bottom border only
        passwordField.setEchoChar((char) 0);
        tf.setText("Password");
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    tf.setCaretPosition(0);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setEchoChar((char) 0);
                    tf.setText("Password");
                }
            }
        });

        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    tf.setText("");
                    passwordField.setEchoChar('\u25cf');
                }
            }
        });
    }
    private void setConForgotButton(JButton bn){
        bn.setContentAreaFilled(false);
        bn.setFont(new Font("Arial", Font.BOLD, 10));
        bn.setForeground(Color.BLACK); // Set text color
        bn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Set black border on the bottom
        bn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(fr, "Contact the relevant person to request a key \n\n Enter organization's key", "Forgot Password", JOptionPane.QUESTION_MESSAGE);
                if ("organization_key".equals(input)) {
                    String username = JOptionPane.showInputDialog(fr, "Please fill your Username", null, JOptionPane.QUESTION_MESSAGE);
                    ResultSet rs = DBquery.getInstance().getSelect(String.format("SELECT * FROM login WHERE username = '%s'",username));

                try {
                        JOptionPane.showMessageDialog(fr, "Your Password is:" + rs.getString("password"), "Invalid organization's key", JOptionPane.INFORMATION_MESSAGE);
                }catch (SQLException ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(fr, "Invalid Username", null, JOptionPane.ERROR_MESSAGE);
                }finally {
                    DBquery.getInstance().disconnect();
                }

                } else {
                    JOptionPane.showMessageDialog(fr, "Wrong Password!!!", "Invalid organization's key", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        bn.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                bn.setForeground(Color.RED); // Set text color
            }

            @Override
            public void focusLost(FocusEvent e) {
                bn.setForeground(Color.BLACK); // Set text color
            }
        });
    }
    private void setConSigninButton(JButton bn){
        bn.setFont(new Font("Arial", Font.BOLD, 14));
        bn.setForeground(Color.ORANGE);
        bn.setBackground(Color.BLACK);
        bn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bn) {
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
    }
    private void setConSignupButton(JButton b){
        b.setBackground(new Color(210, 224, 251));
        b.setFont(new Font("Arial", Font.BOLD, 10));
        b.setForeground(Color.BLACK); // Set text color
        b.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Set black border on the bottom
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(fr, "Contact the relevant person to request a key \n\n Enter organization's key", "Before Register", JOptionPane.QUESTION_MESSAGE);

                if ("organization_key".equals(input)) {
                    RegisterForm registerGUI = new RegisterForm();
                } else {
                    JOptionPane.showMessageDialog(fr, "Wrong Password!!!", "Invalid organization's key", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    private void stylePanel(JPanel p){
        p.setOpaque(true);
        p.setBackground(new Color(210, 224, 251));
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(guestModeButton)){
            new RealTrack();
        }
    }
    public int getuserId() {
        return userId;
    }

}