package gui;

import core.Evidence;
import core.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EvidenceGUI extends JFrame {

    private FileManager fileManager;
    private ArrayList<Evidence> evidenceList;

    private JTable evidenceTable;
    private DefaultTableModel tableModel;

    private JTextField evidenceIdField;
    private JTextField caseIdField;
    private JTextField evidenceTypeField;
    private JTextArea descriptionArea;
    private JTextField collectedByField;
    private JTextField collectedDateField;
    private JTextField filePathField;

    public EvidenceGUI(DashboardGUI dashboard, FileManager fileManager) {
        this.fileManager  = fileManager;

        evidenceList = fileManager.loadEvidence();

        setTitle("Evidence Manager");
        setSize(900, 600);
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
        mainPanel.setBackground(new Color(20, 20, 20));

        JLabel titleLabel = new JLabel("EVIDENCE MANAGER");
        titleLabel.setBounds(0, 10, 900, 30);
        titleLabel.setForeground(new Color(0, 200, 255));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBounds(10, 50, 380, 430);
        formPanel.setBackground(new Color(30, 30, 30));
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 255)));

        JLabel formTitle = new JLabel("ADD EVIDENCE");
        formTitle.setBounds(0, 10, 380, 25);
        formTitle.setForeground(new Color(0, 200, 255));
        formTitle.setFont(new Font("Arial", Font.BOLD, 13));
        formTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel evIdLabel = makeLabel("Evidence ID:", 15, 45);
        evidenceIdField  = makeField(130, 45, 220);

        JLabel caseIdLabel = makeLabel("Case ID:", 15, 80);
        caseIdField        = makeField(130, 80, 220);

        JLabel typeLabel      = makeLabel("Evidence Type:", 15, 115);
        evidenceTypeField     = makeField(130, 115, 220);

        JLabel descLabel = makeLabel("Description:", 15, 150);
        descriptionArea  = new JTextArea();
        descriptionArea.setBackground(new Color(50, 50, 50));
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setCaretColor(Color.WHITE);
        descriptionArea.setLineWrap(true);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setBounds(130, 150, 220, 60);
        descScroll.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 255)));

        JLabel collectedByLabel   = makeLabel("Collected By:", 15, 220);
        collectedByField          = makeField(130, 220, 220);

        JLabel collectedDateLabel = makeLabel("Date:", 15, 255);
        collectedDateField        = makeField(130, 255, 220);

        JLabel filePathLabel = makeLabel("File Path:", 15, 290);
        filePathField        = makeField(130, 290, 220);

        JButton addBtn = new JButton("ADD EVIDENCE");
        addBtn.setBounds(90, 340, 180, 35);
        addBtn.setBackground(new Color(0, 150, 200));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 12));
        addBtn.setFocusPainted(false);
        addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addBtn.setBorder(BorderFactory.createEmptyBorder());

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEvidence();
            }
        });

        JButton clearBtn = new JButton("CLEAR");
        clearBtn.setBounds(90, 385, 180, 30);
        clearBtn.setBackground(new Color(100, 100, 100));
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFont(new Font("Arial", Font.BOLD, 12));
        clearBtn.setFocusPainted(false);
        clearBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearBtn.setBorder(BorderFactory.createEmptyBorder());

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        formPanel.add(formTitle);
        formPanel.add(evIdLabel);
        formPanel.add(evidenceIdField);
        formPanel.add(caseIdLabel);
        formPanel.add(caseIdField);
        formPanel.add(typeLabel);
        formPanel.add(evidenceTypeField);
        formPanel.add(descLabel);
        formPanel.add(descScroll);
        formPanel.add(collectedByLabel);
        formPanel.add(collectedByField);
        formPanel.add(collectedDateLabel);
        formPanel.add(collectedDateField);
        formPanel.add(filePathLabel);
        formPanel.add(filePathField);
        formPanel.add(addBtn);
        formPanel.add(clearBtn);

        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(400, 50, 490, 430);
        tablePanel.setBackground(new Color(20, 20, 20));
        tablePanel.setLayout(null);

        JLabel tableTitle = new JLabel("ALL EVIDENCE");
        tableTitle.setBounds(0, 10, 490, 25);
        tableTitle.setForeground(new Color(0, 200, 255));
        tableTitle.setFont(new Font("Arial", Font.BOLD, 13));
        tableTitle.setHorizontalAlignment(SwingConstants.CENTER);

        String[] columns = {"Ev ID", "Case ID", "Type", "Collected By", "Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        evidenceTable = new JTable(tableModel);
        evidenceTable.setBackground(new Color(30, 30, 30));
        evidenceTable.setForeground(Color.WHITE);
        evidenceTable.setGridColor(new Color(60, 60, 60));
        evidenceTable.setSelectionBackground(new Color(0, 100, 150));
        evidenceTable.setFont(new Font("Arial", Font.PLAIN, 12));
        evidenceTable.setRowHeight(25);
        evidenceTable.getTableHeader().setBackground(new Color(10, 10, 10));
        evidenceTable.getTableHeader().setForeground(new Color(0, 200, 255));
        evidenceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane tableScroll = new JScrollPane(evidenceTable);
        tableScroll.setBounds(0, 40, 490, 340);
        tableScroll.getViewport().setBackground(new Color(30, 30, 30));
        tableScroll.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 255)));

        JButton deleteBtn = new JButton("DELETE SELECTED");
        deleteBtn.setBounds(150, 390, 180, 30);
        deleteBtn.setBackground(new Color(200, 50, 50));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 12));
        deleteBtn.setFocusPainted(false);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBtn.setBorder(BorderFactory.createEmptyBorder());

        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEvidence();
            }
        });

        tablePanel.add(tableTitle);
        tablePanel.add(tableScroll);
        tablePanel.add(deleteBtn);

        JButton closeBtn = new JButton("CLOSE");
        closeBtn.setBounds(380, 520, 120, 35);
        closeBtn.setBackground(new Color(100, 100, 100));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFont(new Font("Arial", Font.BOLD, 12));
        closeBtn.setFocusPainted(false);
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.setBorder(BorderFactory.createEmptyBorder());

        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        mainPanel.add(titleLabel);
        mainPanel.add(formPanel);
        mainPanel.add(tablePanel);
        mainPanel.add(closeBtn);

        add(mainPanel);
    }

    private void addEvidence() {
        String evidenceId   = evidenceIdField.getText().trim();
        String caseId       = caseIdField.getText().trim();
        String type         = evidenceTypeField.getText().trim();
        String description  = descriptionArea.getText().trim();
        String collectedBy  = collectedByField.getText().trim();
        String date         = collectedDateField.getText().trim();
        String filePath     = filePathField.getText().trim();

        if (evidenceId.isEmpty() || caseId.isEmpty() || type.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Evidence ID, Case ID and Type are required!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Evidence newEvidence = new Evidence(
                evidenceId, caseId, type, description, collectedBy, date, filePath
        );

        evidenceList.add(newEvidence);
        fileManager.saveEvidence(evidenceList);
        refreshTable();
        clearFields();

        JOptionPane.showMessageDialog(this,
                "Evidence added successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteEvidence() {
        int selectedRow = evidenceTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select evidence to delete!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this evidence?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            evidenceList.remove(selectedRow);
            fileManager.saveEvidence(evidenceList);
            refreshTable();
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < evidenceList.size(); i++) {
            Evidence ev = evidenceList.get(i);
            Object[] row = {
                    ev.getEvidenceId(),
                    ev.getCaseId(),
                    ev.getEvidenceType(),
                    ev.getCollectedBy(),
                    ev.getCollectedDate()
            };
            tableModel.addRow(row);
        }
    }

    private void clearFields() {
        evidenceIdField.setText("");
        caseIdField.setText("");
        evidenceTypeField.setText("");
        descriptionArea.setText("");
        collectedByField.setText("");
        collectedDateField.setText("");
        filePathField.setText("");
    }

    private JLabel makeLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 115, 25);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 11));
        return label;
    }

    private JTextField makeField(int x, int y, int width) {
        JTextField field = new JTextField();
        field.setBounds(x, y, width, 25);
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 255)));
        return field;
    }
}