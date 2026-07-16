package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "1234";

    public LoginGUI() {
        setTitle("Cyber Crime Investigation System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setResizable(false);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(30, 30, 30));

        JLabel titleLabel = new JLabel("CYBER CRIME INVESTIGATION");
        titleLabel.setBounds(50, 20, 300, 25);
        titleLabel.setForeground(new Color(0, 200, 255));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitleLabel = new JLabel("-- SYSTEM LOGIN --");
        subtitleLabel.setBounds(50, 45, 300, 20);
        subtitleLabel.setForeground(new Color(150, 150, 150));
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(70, 90, 100, 25);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        usernameField = new JTextField();
        usernameField.setBounds(170, 90, 160, 25);
        usernameField.setBackground(new Color(50, 50, 50));
        usernameField.setForeground(Color.WHITE);
        usernameField.setCaretColor(Color.WHITE);
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 255)));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 130, 100, 25);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 130, 160, 25);
        passwordField.setBackground(new Color(50, 50, 50));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 255)));

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(140, 175, 120, 35);
        loginButton.setBackground(new Color(0, 150, 200));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 13));
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setFocusPainted(false);

        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 220, 300, 25);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkLogin();
                }
            }
        });

        mainPanel.add(titleLabel);
        mainPanel.add(subtitleLabel);
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(messageLabel);

        add(mainPanel);
    }

    private void checkLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
            messageLabel.setForeground(new Color(0, 255, 0));
            messageLabel.setText("Login successful! Opening dashboard...");

            Timer timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); 
                    new DashboardGUI(); 
                }
            });
            timer.setRepeats(false);
            timer.start();

        } else {
            messageLabel.setForeground(new Color(255, 50, 50));
            messageLabel.setText("Invalid username or password!");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}