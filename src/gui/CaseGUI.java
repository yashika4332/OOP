package gui;

import cases.*;
import core.CaseTracker;
import core.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CaseGUI extends JFrame {

    private DashboardGUI dashboard;
    private CaseTracker caseTracker;
    private FileManager fileManager;

    private JTextField caseIdField;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField dateField;
    private JTextField investigatorIdField;
    private JTextField victimIdField;
    private JComboBox<String> caseTypeCombo;

    private JPanel extraPanel;

    private JTextField fakeUrlField;
    private JTextField targetedPlatformField;
    private JTextField victimsAffectedField;

    private JTextField malwareTypeField;
    private JTextField affectedSystemField;
    private JTextField malwareHashField;

    private JTextField stolenIdTypeField;
    private JTextField stolenIdNumberField;
    private JTextField misusedPlatformField;

    private JTextField ransomAmountField;
    private JTextField paymentMethodField;
    private JTextField encryptedFilesField;
    private JCheckBox ransomPaidCheck;

    private JTextField dataTypeField;
    private JTextField recordsBreachedField;
    private JTextField affectedSystemBreachField;
    private JTextField breachSourceField;

    public CaseGUI(DashboardGUI dashboard, CaseTracker caseTracker, FileManager fileManager) {
        this.dashboard    = dashboard;
        this.caseTracker  = caseTracker;
        this.fileManager  = fileManager;

        setTitle("Add New Case");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(dashboard);
        setResizable(false);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(20, 20, 20));

        JLabel titleLabel = new JLabel("ADD NEW CASE");
        titleLabel.setBounds(0, 10, 550, 30);
        titleLabel.setForeground(new Color(0, 200, 255));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel typeLabel = makeLabel("Case Type:", 30, 55);
        caseTypeCombo = new JComboBox<>(new String[]{
                "Phishing", "Malware", "Identity Theft", "Ransomware", "Data Breach"
        });
        caseTypeCombo.setBounds(170, 55, 200, 25);
        caseTypeCombo.setBackground(new Color(50, 50, 50));
        caseTypeCombo.setForeground(Color.WHITE);

        JLabel caseIdLabel = makeLabel("Case ID:", 30, 90);
        caseIdField = makeField(170, 90);

        JLabel titleFieldLabel = makeLabel("Title:", 30, 125);
        titleField = makeField(170, 125);

        JLabel descLabel = makeLabel("Description:", 30, 160);
        descriptionArea = new JTextArea();
        descriptionArea.setBackground(new Color(50, 50, 50));
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setCaretColor(Color.WHITE);
        descriptionArea.setLineWrap(true);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setBounds(170, 160, 340, 60);
        descScroll.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 255)));

        JLabel dateLabel = makeLabel("Date (yyyy-mm-dd):", 30, 230);
        dateField = makeField(170, 230);

        JLabel invLabel = makeLabel("Investigator ID:", 30, 265);
        investigatorIdField = makeField(170, 265);

        JLabel victimLabel = makeLabel("Victim ID:", 30, 300);
        victimIdField = makeField(170, 300);

        extraPanel = new JPanel();
        extraPanel.setBounds(0, 335, 550, 220);
        extraPanel.setBackground(new Color(20, 20, 20));
        extraPanel.setLayout(null);

        showPhishingFields();

        caseTypeCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) caseTypeCombo.getSelectedItem();
                extraPanel.removeAll();

                if (selected.equals("Phishing")) {
                    showPhishingFields();
                } else if (selected.equals("Malware")) {
                    showMalwareFields();
                } else if (selected.equals("Identity Theft")) {
                    showIdentityTheftFields();
                } else if (selected.equals("Ransomware")) {
                    showRansomwareFields();
                } else if (selected.equals("Data Breach")) {
                    showDataBreachFields();
                }

                extraPanel.revalidate();
                extraPanel.repaint();
            }
        });

        JButton submitBtn = new JButton("SAVE CASE");
        submitBtn.setBounds(170, 560, 150, 35);
        submitBtn.setBackground(new Color(0, 150, 200));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 13));
        submitBtn.setFocusPainted(false);
        submitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitBtn.setBorder(BorderFactory.createEmptyBorder());

        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setBounds(330, 560, 100, 35);
        cancelBtn.setBackground(new Color(150, 50, 50));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 13));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelBtn.setBorder(BorderFactory.createEmptyBorder());

        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveCase();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        mainPanel.add(titleLabel);
        mainPanel.add(typeLabel);
        mainPanel.add(caseTypeCombo);
        mainPanel.add(caseIdLabel);
        mainPanel.add(caseIdField);
        mainPanel.add(titleFieldLabel);
        mainPanel.add(titleField);
        mainPanel.add(descLabel);
        mainPanel.add(descScroll);
        mainPanel.add(dateLabel);
        mainPanel.add(dateField);
        mainPanel.add(invLabel);
        mainPanel.add(investigatorIdField);
        mainPanel.add(victimLabel);
        mainPanel.add(victimIdField);
        mainPanel.add(extraPanel);
        mainPanel.add(submitBtn);
        mainPanel.add(cancelBtn);

        add(mainPanel);
    }

    private void showPhishingFields() {
        extraPanel.add(makeLabel("Fake URL:", 30, 10));
        fakeUrlField = makeField(170, 10);
        extraPanel.add(fakeUrlField);

        extraPanel.add(makeLabel("Target Platform:", 30, 45));
        targetedPlatformField = makeField(170, 45);
        extraPanel.add(targetedPlatformField);

        extraPanel.add(makeLabel("Victims Affected:", 30, 80));
        victimsAffectedField = makeField(170, 80);
        extraPanel.add(victimsAffectedField);
    }

    private void showMalwareFields() {
        extraPanel.add(makeLabel("Malware Type:", 30, 10));
        malwareTypeField = makeField(170, 10);
        extraPanel.add(malwareTypeField);

        extraPanel.add(makeLabel("Affected System:", 30, 45));
        affectedSystemField = makeField(170, 45);
        extraPanel.add(affectedSystemField);

        extraPanel.add(makeLabel("Malware Hash:", 30, 80));
        malwareHashField = makeField(170, 80);
        extraPanel.add(malwareHashField);
    }

    private void showIdentityTheftFields() {
        extraPanel.add(makeLabel("Stolen ID Type:", 30, 10));
        stolenIdTypeField = makeField(170, 10);
        extraPanel.add(stolenIdTypeField);

        extraPanel.add(makeLabel("Stolen ID Number:", 30, 45));
        stolenIdNumberField = makeField(170, 45);
        extraPanel.add(stolenIdNumberField);

        extraPanel.add(makeLabel("Misused Platform:", 30, 80));
        misusedPlatformField = makeField(170, 80);
        extraPanel.add(misusedPlatformField);
    }

    private void showRansomwareFields() {
        extraPanel.add(makeLabel("Ransom Amount:", 30, 10));
        ransomAmountField = makeField(170, 10);
        extraPanel.add(ransomAmountField);

        extraPanel.add(makeLabel("Payment Method:", 30, 45));
        paymentMethodField = makeField(170, 45);
        extraPanel.add(paymentMethodField);

        extraPanel.add(makeLabel("Encrypted Files:", 30, 80));
        encryptedFilesField = makeField(170, 80);
        extraPanel.add(encryptedFilesField);

        ransomPaidCheck = new JCheckBox("Ransom Paid?");
        ransomPaidCheck.setBounds(170, 115, 150, 25);
        ransomPaidCheck.setBackground(new Color(20, 20, 20));
        ransomPaidCheck.setForeground(Color.WHITE);
        extraPanel.add(ransomPaidCheck);
    }

    private void showDataBreachFields() {
        extraPanel.add(makeLabel("Data Type:", 30, 10));
        dataTypeField = makeField(170, 10);
        extraPanel.add(dataTypeField);

        extraPanel.add(makeLabel("Records Breached:", 30, 45));
        recordsBreachedField = makeField(170, 45);
        extraPanel.add(recordsBreachedField);

        extraPanel.add(makeLabel("Affected System:", 30, 80));
        affectedSystemBreachField = makeField(170, 80);
        extraPanel.add(affectedSystemBreachField);

        extraPanel.add(makeLabel("Breach Source:", 30, 115));
        breachSourceField = makeField(170, 115);
        extraPanel.add(breachSourceField);
    }

    private void saveCase() {

        String caseId       = caseIdField.getText().trim();
        String title        = titleField.getText().trim();
        String description  = descriptionArea.getText().trim();
        String date         = dateField.getText().trim();
        String investigator = investigatorIdField.getText().trim();
        String victim       = victimIdField.getText().trim();
        String caseType     = (String) caseTypeCombo.getSelectedItem();

        if (caseId.isEmpty() || title.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Case ID, Title and Date are required!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CyberCase newCase = null;

        try {
            if (caseType.equals("Phishing")) {
                newCase = new PhishingCase(
                        caseId, title, description, date, investigator, victim,
                        fakeUrlField.getText().trim(),
                        targetedPlatformField.getText().trim(),
                        Integer.parseInt(victimsAffectedField.getText().trim().isEmpty() ? "0" : victimsAffectedField.getText().trim())
                );

            } else if (caseType.equals("Malware")) {
                newCase = new MalwareCase(
                        caseId, title, description, date, investigator, victim,
                        malwareTypeField.getText().trim(),
                        affectedSystemField.getText().trim(),
                        malwareHashField.getText().trim()
                );

            } else if (caseType.equals("Identity Theft")) {
                newCase = new IdentityTheftCase(
                        caseId, title, description, date, investigator, victim,
                        stolenIdTypeField.getText().trim(),
                        stolenIdNumberField.getText().trim(),
                        misusedPlatformField.getText().trim()
                );

            } else if (caseType.equals("Ransomware")) {
                newCase = new RansomwareCase(
                        caseId, title, description, date, investigator, victim,
                        ransomAmountField.getText().trim(),
                        paymentMethodField.getText().trim(),
                        encryptedFilesField.getText().trim(),
                        ransomPaidCheck.isSelected()
                );

            } else if (caseType.equals("Data Breach")) {
                newCase = new DataBreachCase(
                        caseId, title, description, date, investigator, victim,
                        dataTypeField.getText().trim(),
                        Integer.parseInt(recordsBreachedField.getText().trim().isEmpty() ? "0" : recordsBreachedField.getText().trim()),
                        affectedSystemBreachField.getText().trim(),
                        breachSourceField.getText().trim()
                );
            }

            caseTracker.addCase(newCase);

            fileManager.saveCases(caseTracker.getAllCases());

            dashboard.refreshTable();

            JOptionPane.showMessageDialog(this,
                    "Case saved successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error saving case: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JLabel makeLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 140, 25);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        return label;
    }

    private JTextField makeField(int x, int y) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 340, 25);
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 255)));
        return field;
    }
}