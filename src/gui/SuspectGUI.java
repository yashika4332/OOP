package gui;

import core.FileManager;
import persons.Suspect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SuspectGUI extends JFrame {

    private FileManager fileManager;
    private ArrayList<Suspect> suspectList;

    private JTable suspectTable;
    private DefaultTableModel tableModel;

    private JTextField personIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField caseIdField;
    private JTextField ipAddressField;
    private JTextField deviceInfoField;
    private JComboBox<String> threatLevelCombo;
    private JComboBox<String> statusCombo;

    public SuspectGUI(DashboardGUI dashboard, FileManager fileManager) {
        this.fileManager = fileManager;
        suspectList      = fileManager.loadSuspects();

        setTitle("Suspect Manager");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(dashboard);
        setResizable(false);

        initComponents();
        refreshTable();
        setVisible(true);
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(15, 15, 25));

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 950, 55);
        headerPanel.setBackground(new Color(8, 8, 18));
        headerPanel.setLayout(null);

        JLabel titleLabel = new JLabel("SUSPECT MANAGER");
        titleLabel.setBounds(20, 8, 400, 25);
        titleLabel.setForeground(new Color(180, 0, 255));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel subLabel = new JLabel("Add and track suspects linked to cases");
        subLabel.setBounds(20, 32, 400, 16);
        subLabel.setForeground(new Color(80, 60, 100));
        subLabel.setFont(new Font("Arial", Font.PLAIN, 11));

        headerPanel.add(titleLabel);
        headerPanel.add(subLabel);

        JPanel formPanel = new JPanel();
        formPanel.setBounds(10, 65, 360, 520);
        formPanel.setBackground(new Color(20, 20, 35));
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 0, 180)));

        JLabel formTitle = new JLabel("ADD SUSPECT");
        formTitle.setBounds(0, 10, 360, 22);
        formTitle.setForeground(new Color(180, 0, 255));
        formTitle.setFont(new Font("Arial", Font.BOLD, 13));
        formTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator sep = new JSeparator();
        sep.setBounds(15, 35, 330, 2);
        sep.setForeground(new Color(60, 0, 100));

        personIdField  = makeFormRow(formPanel, "Person ID:",    15, 50);
        nameField      = makeFormRow(formPanel, "Full Name:",    15, 85);
        emailField     = makeFormRow(formPanel, "Email:",        15, 120);
        phoneField     = makeFormRow(formPanel, "Phone:",        15, 155);
        addressField   = makeFormRow(formPanel, "Address:",      15, 190);
        caseIdField    = makeFormRow(formPanel, "Case ID:",      15, 225);
        ipAddressField = makeFormRow(formPanel, "IP Address:",   15, 260);
        deviceInfoField= makeFormRow(formPanel, "Device Info:",  15, 295);

        JLabel threatLabel = new JLabel("Threat Level:");
        threatLabel.setBounds(15, 330, 110, 25);
        threatLabel.setForeground(new Color(160, 160, 180));
        threatLabel.setFont(new Font("Arial", Font.PLAIN, 11));

        threatLevelCombo = new JComboBox<>(new String[]{"Low", "Medium", "High", "Critical"});
        threatLevelCombo.setBounds(130, 330, 200, 25);
        threatLevelCombo.setBackground(new Color(30, 30, 50));
        threatLevelCombo.setForeground(Color.WHITE);
        threatLevelCombo.setFont(new Font("Arial", Font.PLAIN, 11));

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(15, 365, 110, 25);
        statusLabel.setForeground(new Color(160, 160, 180));
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));

        statusCombo = new JComboBox<>(new String[]{
                "Under Investigation", "Arrested", "Released", "Wanted"
        });
        statusCombo.setBounds(130, 365, 200, 25);
        statusCombo.setBackground(new Color(30, 30, 50));
        statusCombo.setForeground(Color.WHITE);
        statusCombo.setFont(new Font("Arial", Font.PLAIN, 11));

        JButton addBtn = new JButton("ADD SUSPECT");
        addBtn.setBounds(40, 415, 140, 35);
        addBtn.setBackground(new Color(130, 0, 200));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 12));
        addBtn.setFocusPainted(false);
        addBtn.setBorder(BorderFactory.createEmptyBorder());
        addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton clearBtn = new JButton("CLEAR");
        clearBtn.setBounds(195, 415, 130, 35);
        clearBtn.setBackground(new Color(50, 50, 70));
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFont(new Font("Arial", Font.BOLD, 12));
        clearBtn.setFocusPainted(false);
        clearBtn.setBorder(BorderFactory.createEmptyBorder());
        clearBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSuspect();
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        formPanel.add(formTitle);
        formPanel.add(sep);
        formPanel.add(threatLabel);
        formPanel.add(threatLevelCombo);
        formPanel.add(statusLabel);
        formPanel.add(statusCombo);
        formPanel.add(addBtn);
        formPanel.add(clearBtn);

        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(380, 65, 560, 520);
        tablePanel.setBackground(new Color(15, 15, 25));
        tablePanel.setLayout(null);

        JLabel tableTitle = new JLabel("ALL SUSPECTS");
        tableTitle.setBounds(0, 10, 560, 22);
        tableTitle.setForeground(new Color(180, 0, 255));
        tableTitle.setFont(new Font("Arial", Font.BOLD, 13));
        tableTitle.setHorizontalAlignment(SwingConstants.CENTER);

        String[] columns = {"Name", "Case ID", "IP Address", "Threat", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        suspectTable = new JTable(tableModel);
        suspectTable.setBackground(new Color(18, 18, 30));
        suspectTable.setForeground(new Color(200, 210, 220));
        suspectTable.setGridColor(new Color(30, 30, 50));
        suspectTable.setSelectionBackground(new Color(80, 0, 130));
        suspectTable.setFont(new Font("Arial", Font.PLAIN, 12));
        suspectTable.setRowHeight(28);
        suspectTable.setShowVerticalLines(false);
        suspectTable.getTableHeader().setBackground(new Color(10, 10, 20));
        suspectTable.getTableHeader().setForeground(new Color(180, 0, 255));
        suspectTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        suspectTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    String threat = (String) table.getValueAt(row, 3);
                    if      (threat != null && threat.equals("Critical")) { c.setBackground(new Color(60, 10, 10)); c.setForeground(new Color(255, 80, 80)); }
                    else if (threat != null && threat.equals("High"))     { c.setBackground(new Color(50, 25, 5));  c.setForeground(new Color(255, 150, 50)); }
                    else if (threat != null && threat.equals("Medium"))   { c.setBackground(new Color(40, 35, 5));  c.setForeground(new Color(255, 210, 60)); }
                    else                                                   { c.setBackground(new Color(10, 35, 20)); c.setForeground(new Color(80, 200, 120)); }
                }
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return c;
            }
        });

        JScrollPane tableScroll = new JScrollPane(suspectTable);
        tableScroll.setBounds(0, 40, 560, 420);
        tableScroll.getViewport().setBackground(new Color(18, 18, 30));
        tableScroll.setBorder(BorderFactory.createLineBorder(new Color(60, 0, 100)));

        JButton deleteBtn = new JButton("DELETE SELECTED");
        deleteBtn.setBounds(170, 470, 200, 32);
        deleteBtn.setBackground(new Color(160, 20, 20));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 12));
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBorder(BorderFactory.createEmptyBorder());
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSuspect();
            }
        });

        tablePanel.add(tableTitle);
        tablePanel.add(tableScroll);
        tablePanel.add(deleteBtn);

        JButton closeBtn = new JButton("CLOSE");
        closeBtn.setBounds(410, 595, 120, 32);
        closeBtn.setBackground(new Color(50, 50, 70));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFont(new Font("Arial", Font.BOLD, 12));
        closeBtn.setFocusPainted(false);
        closeBtn.setBorder(BorderFactory.createEmptyBorder());
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> dispose());

        mainPanel.add(headerPanel);
        mainPanel.add(formPanel);
        mainPanel.add(tablePanel);
        mainPanel.add(closeBtn);

        add(mainPanel);
    }

    private void addSuspect() {
        String personId   = personIdField.getText().trim();
        String name       = nameField.getText().trim();
        String email      = emailField.getText().trim();
        String phone      = phoneField.getText().trim();
        String address    = addressField.getText().trim();
        String caseId     = caseIdField.getText().trim();
        String ipAddress  = ipAddressField.getText().trim();
        String deviceInfo = deviceInfoField.getText().trim();
        String threat     = (String) threatLevelCombo.getSelectedItem();
        String status     = (String) statusCombo.getSelectedItem();

        if (personId.isEmpty() || name.isEmpty() || caseId.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Person ID, Name and Case ID are required!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Suspect newSuspect = new Suspect(
                personId, name, email, phone, address,
                caseId, ipAddress, deviceInfo, threat, status
        );

        suspectList.add(newSuspect);
        fileManager.saveSuspects(suspectList);
        refreshTable();
        clearFields();

        JOptionPane.showMessageDialog(this,
                "Suspect added successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteSuspect() {
        int row = suspectTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a suspect to delete!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this suspect?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            suspectList.remove(row);
            fileManager.saveSuspects(suspectList);
            refreshTable();
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < suspectList.size(); i++) {
            Suspect s = suspectList.get(i);
            tableModel.addRow(new Object[]{
                    s.getName(), s.getCaseId(),
                    s.getIpAddress(), s.getThreatLevel(), s.getStatus()
            });
        }
    }

    private void clearFields() {
        personIdField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
        caseIdField.setText("");
        ipAddressField.setText("");
        deviceInfoField.setText("");
        threatLevelCombo.setSelectedIndex(0);
        statusCombo.setSelectedIndex(0);
    }

    private JTextField makeFormRow(JPanel panel, String labelText, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 110, 25);
        label.setForeground(new Color(160, 160, 180));
        label.setFont(new Font("Arial", Font.PLAIN, 11));

        JTextField field = new JTextField();
        field.setBounds(x + 115, y, 215, 25);
        field.setBackground(new Color(30, 30, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(80, 0, 140)));
        field.setFont(new Font("Arial", Font.PLAIN, 11));

        panel.add(label);
        panel.add(field);
        return field;
    }
}