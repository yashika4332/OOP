package gui;

import cases.CyberCase;
import core.CaseTracker;
import core.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DashboardGUI extends JFrame {

    private CaseTracker caseTracker;
    private FileManager fileManager;
    private JTable caseTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    private JLabel totalLabel;
    private JLabel openLabel;
    private JLabel investigatingLabel;
    private JLabel closedLabel;
    private JLabel escalatedLabel;

    public DashboardGUI() {
        fileManager  = new FileManager();
        fileManager.createDataFolder();
        caseTracker  = new CaseTracker();

        ArrayList<CyberCase> loadedCases = fileManager.loadCases();
        for (int i = 0; i < loadedCases.size(); i++) {
            caseTracker.addCase(loadedCases.get(i));
        }

        setTitle("Cyber Crime Investigation System");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        headerPanel.setBounds(0, 0, 1000, 65);
        headerPanel.setBackground(new Color(8, 8, 18));
        headerPanel.setLayout(null);

        JLabel dotLabel = new JLabel("⬡");
        dotLabel.setBounds(15, 15, 30, 35);
        dotLabel.setForeground(new Color(0, 200, 255));
        dotLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel titleLabel = new JLabel("CYBER CRIME INVESTIGATION SYSTEM");
        titleLabel.setBounds(50, 10, 500, 25);
        titleLabel.setForeground(new Color(0, 200, 255));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel subtitleLabel = new JLabel("Digital Forensics & Case Management");
        subtitleLabel.setBounds(50, 35, 400, 18);
        subtitleLabel.setForeground(new Color(80, 120, 150));
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 11));

        JLabel userIcon = new JLabel("👤 Admin");
        userIcon.setBounds(800, 15, 100, 20);
        userIcon.setForeground(new Color(150, 200, 255));
        userIcon.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel timeLabel = new JLabel("CCIS v1.0");
        timeLabel.setBounds(800, 35, 100, 18);
        timeLabel.setForeground(new Color(60, 80, 100));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 10));

        JButton logoutBtn = new JButton("LOGOUT");
        logoutBtn.setBounds(910, 18, 75, 28);
        logoutBtn.setBackground(new Color(180, 30, 30));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 10));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorder(BorderFactory.createEmptyBorder());
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        headerPanel.add(dotLabel);
        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);
        headerPanel.add(userIcon);
        headerPanel.add(timeLabel);
        headerPanel.add(logoutBtn);

        JPanel statsPanel = new JPanel();
        statsPanel.setBounds(10, 75, 980, 80);
        statsPanel.setBackground(new Color(15, 15, 25));
        statsPanel.setLayout(new GridLayout(1, 5, 10, 0));

        totalLabel       = makeStatCard("TOTAL CASES", "0", new Color(0, 150, 255));
        openLabel        = makeStatCard("OPEN", "0", new Color(255, 180, 0));
        investigatingLabel = makeStatCard("INVESTIGATING", "0", new Color(0, 180, 255));
        closedLabel      = makeStatCard("CLOSED", "0", new Color(0, 200, 100));
        escalatedLabel   = makeStatCard("ESCALATED", "0", new Color(255, 50, 50));

        statsPanel.add(totalLabel);
        statsPanel.add(openLabel);
        statsPanel.add(investigatingLabel);
        statsPanel.add(closedLabel);
        statsPanel.add(escalatedLabel);

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setBounds(10, 165, 980, 45);
        toolbarPanel.setBackground(new Color(20, 20, 35));
        toolbarPanel.setLayout(null);
        toolbarPanel.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 50)));

        JButton addCaseBtn  = makeToolBtn("+ New Case",      new Color(0, 130, 200),  10, 8);
        JButton suspectBtn  = makeToolBtn("+ Suspect",       new Color(150, 0, 200),  130, 8);
        JButton evidenceBtn = makeToolBtn("Evidence",        new Color(0, 130, 130),  250, 8);
        JButton reportBtn   = makeToolBtn("Reports",         new Color(0, 140, 80),   360, 8);
        JButton statusBtn   = makeToolBtn("Update Status",   new Color(180, 100, 0),  470, 8);
        JButton refreshBtn  = makeToolBtn("↺ Refresh",       new Color(50, 50, 80),   590, 8);

        JLabel searchIcon = new JLabel("🔍");
        searchIcon.setBounds(700, 12, 25, 22);
        toolbarPanel.add(searchIcon);

        searchField = new JTextField();
        searchField.setBounds(725, 10, 200, 26);
        searchField.setBackground(new Color(30, 30, 50));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 255)));
        searchField.setFont(new Font("Arial", Font.PLAIN, 12));
        searchField.putClientProperty("JTextField.placeholderText", "Search cases...");

        toolbarPanel.add(addCaseBtn);
        toolbarPanel.add(suspectBtn);
        toolbarPanel.add(evidenceBtn);
        toolbarPanel.add(reportBtn);
        toolbarPanel.add(statusBtn);
        toolbarPanel.add(refreshBtn);
        toolbarPanel.add(searchIcon);
        toolbarPanel.add(searchField);

        String[] columns = {"  Case ID", "  Type", "  Title", "  Date Reported", "  Status", "  Investigator", "  Threat"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        caseTable = new JTable(tableModel);
        caseTable.setBackground(new Color(18, 18, 30));
        caseTable.setForeground(new Color(200, 210, 220));
        caseTable.setGridColor(new Color(30, 30, 50));
        caseTable.setSelectionBackground(new Color(0, 80, 130));
        caseTable.setSelectionForeground(Color.WHITE);
        caseTable.setFont(new Font("Arial", Font.PLAIN, 12));
        caseTable.setRowHeight(30);
        caseTable.setShowVerticalLines(false);
        caseTable.setIntercellSpacing(new Dimension(0, 1));

        caseTable.getTableHeader().setBackground(new Color(10, 10, 20));
        caseTable.getTableHeader().setForeground(new Color(0, 200, 255));
        caseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        caseTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
        caseTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 150, 255)));

        caseTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                if (!isSelected) {
                    String status = (String) table.getValueAt(row, 4);
                    if (status != null) {
                        if (status.trim().equals("Escalated")) {
                            c.setBackground(new Color(60, 15, 15));
                            c.setForeground(new Color(255, 100, 100));
                        } else if (status.trim().equals("Open")) {
                            c.setBackground(new Color(40, 35, 10));
                            c.setForeground(new Color(255, 210, 80));
                        } else if (status.trim().equals("Closed")) {
                            c.setBackground(new Color(10, 40, 20));
                            c.setForeground(new Color(80, 220, 120));
                        } else if (status.trim().equals("Under Investigation")) {
                            c.setBackground(new Color(10, 30, 50));
                            c.setForeground(new Color(80, 180, 255));
                        } else {
                            c.setBackground(new Color(18, 18, 30));
                            c.setForeground(new Color(200, 210, 220));
                        }
                    }
                }
                setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(caseTable);
        scrollPane.setBounds(10, 218, 980, 360);
        scrollPane.getViewport().setBackground(new Color(18, 18, 30));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 60)));
        scrollPane.getVerticalScrollBar().setBackground(new Color(20, 20, 35));

        JPanel statusBar = new JPanel();
        statusBar.setBounds(0, 585, 1000, 28);
        statusBar.setBackground(new Color(8, 8, 18));
        statusBar.setLayout(null);

        JLabel statusText = new JLabel("  ● System Ready  |  Double-click a case to view details");
        statusText.setBounds(10, 5, 600, 18);
        statusText.setForeground(new Color(60, 120, 80));
        statusText.setFont(new Font("Arial", Font.PLAIN, 11));

        statusBar.add(statusText);

        addCaseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CaseGUI(DashboardGUI.this, caseTracker, fileManager);
            }
        });

        suspectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SuspectGUI(DashboardGUI.this, fileManager);
            }
        });

        evidenceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EvidenceGUI(DashboardGUI.this, fileManager);
            }
        });

        reportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReportGUI(DashboardGUI.this, caseTracker, fileManager);
            }
        });

        statusBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateSelectedCaseStatus();
            }
        });

        refreshBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });

        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(DashboardGUI.this,
                        "Are you sure you want to logout?",
                        "Logout", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginGUI();
                }
            }
        });

        caseTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = caseTable.getSelectedRow();
                    if (row != -1) {
                        CyberCase selected = caseTracker.getAllCases().get(row);
                        new CaseDetailGUI(DashboardGUI.this, selected, fileManager);
                    }
                }
            }
        });

        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                filterTable(searchField.getText().trim());
            }
        });

        mainPanel.add(headerPanel);
        mainPanel.add(statsPanel);
        mainPanel.add(toolbarPanel);
        mainPanel.add(scrollPane);
        mainPanel.add(statusBar);

        add(mainPanel);
    }

    private void updateSelectedCaseStatus() {
        int row = caseTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a case first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] statuses = {"Open", "Under Investigation", "Closed", "Escalated"};
        String newStatus = (String) JOptionPane.showInputDialog(
                this, "Select new status:",
                "Update Status", JOptionPane.QUESTION_MESSAGE,
                null, statuses, statuses[0]
        );

        if (newStatus != null) {
            CyberCase selected = caseTracker.getAllCases().get(row);
            selected.updateStatus(newStatus);
            fileManager.saveCases(caseTracker.getAllCases());
            refreshTable();
        }
    }

    private void filterTable(String query) {
        tableModel.setRowCount(0);
        ArrayList<CyberCase> allCases = caseTracker.getAllCases();

        for (int i = 0; i < allCases.size(); i++) {
            CyberCase c = allCases.get(i);
            if (query.isEmpty() ||
                    c.getCaseId().toLowerCase().contains(query.toLowerCase()) ||
                    c.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    c.getCaseType().toLowerCase().contains(query.toLowerCase())) {

                addRowToTable(c);
            }
        }
    }

    public CaseTracker getCaseTracker() {
        return caseTracker;
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        ArrayList<CyberCase> allCases = caseTracker.getAllCases();

        int open = 0, investigating = 0, closed = 0, escalated = 0;

        for (int i = 0; i < allCases.size(); i++) {
            CyberCase c = allCases.get(i);
            addRowToTable(c);

            String status = c.getStatus();
            if (status.equals("Open"))                  open++;
            else if (status.equals("Under Investigation")) investigating++;
            else if (status.equals("Closed"))           closed++;
            else if (status.equals("Escalated"))        escalated++;
        }

        updateStats(allCases.size(), open, investigating, closed, escalated);
    }

    private void addRowToTable(CyberCase c) {
        String threat = "";
        String type = c.getCaseType();
        if (type.equals("Ransomware") || type.equals("Data Breach")) threat = "🔴 Critical";
        else if (type.equals("Malware"))    threat = "🟠 High";
        else if (type.equals("Phishing"))   threat = "🟡 Medium";
        else                                threat = "🟢 Low";

        Object[] row = {
                "  " + c.getCaseId(),
                "  " + c.getCaseType(),
                "  " + c.getTitle(),
                "  " + c.getDateReported(),
                "  " + c.getStatus(),
                "  " + c.getInvestigatorId(),
                "  " + threat
        };
        tableModel.addRow(row);
    }

    private void updateStats(int total, int open, int investigating, int closed, int escalated) {
        totalLabel.setText("<html><center><font size='4' color='#00aaff'>" + total + "</font><br><font size='2' color='#446688'>TOTAL</font></center></html>");
        openLabel.setText("<html><center><font size='4' color='#ffcc00'>" + open + "</font><br><font size='2' color='#886633'>OPEN</font></center></html>");
        investigatingLabel.setText("<html><center><font size='4' color='#00bbff'>" + investigating + "</font><br><font size='2' color='#336688'>INVESTIGATING</font></center></html>");
        closedLabel.setText("<html><center><font size='4' color='#00cc66'>" + closed + "</font><br><font size='2' color='#336644'>CLOSED</font></center></html>");
        escalatedLabel.setText("<html><center><font size='4' color='#ff4444'>" + escalated + "</font><br><font size='2' color='#883333'>ESCALATED</font></center></html>");
    }

    private JLabel makeStatCard(String title, String value, Color color) {
        JLabel label = new JLabel(
                "<html><center><font size='5' color='" + String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()) + "'>" + value + "</font><br><font size='2' color='#556677'>" + title + "</font></center></html>",
                SwingConstants.CENTER
        );
        label.setOpaque(true);
        label.setBackground(new Color(20, 20, 35));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 30, 55)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return label;
    }

    private JButton makeToolBtn(String text, Color color, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 110, 28);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 11));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}