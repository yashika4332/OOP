package gui;

import cases.CyberCase;
import core.Evidence;
import core.FileManager;
import core.ThreatAnalyzer;
import persons.Suspect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CaseDetailGUI extends JFrame {

    private CyberCase cyberCase;
    private FileManager fileManager;
    private DashboardGUI dashboard;

    private JTable evidenceTable;
    private JTable suspectTable;
    private DefaultTableModel evidenceModel;
    private DefaultTableModel suspectModel;

    public CaseDetailGUI(DashboardGUI dashboard, CyberCase cyberCase, FileManager fileManager) {
        this.dashboard   = dashboard;
        this.cyberCase   = cyberCase;
        this.fileManager = fileManager;

        setTitle("Case Detail — " + cyberCase.getCaseId());
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(dashboard);
        setResizable(false);

        initComponents();
        loadEvidenceTable();
        loadSuspectTable();
        setVisible(true);
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(15, 15, 25));

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 850, 60);
        headerPanel.setBackground(new Color(8, 8, 18));
        headerPanel.setLayout(null);

        JLabel caseIdLabel = new JLabel(cyberCase.getCaseId());
        caseIdLabel.setBounds(20, 8, 200, 25);
        caseIdLabel.setForeground(new Color(0, 200, 255));
        caseIdLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel typeLabel = new JLabel("[ " + cyberCase.getCaseType() + " ]");
        typeLabel.setBounds(20, 33, 200, 18);
        typeLabel.setForeground(new Color(100, 150, 200));
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        ThreatAnalyzer analyzer = new ThreatAnalyzer();
        String threat = analyzer.analyzeCase(cyberCase);
        Color threatColor;
        if      (threat.equals("Critical")) threatColor = new Color(255, 50,  50);
        else if (threat.equals("High"))     threatColor = new Color(255, 150, 0);
        else if (threat.equals("Medium"))   threatColor = new Color(255, 220, 0);
        else                                threatColor = new Color(0,   200, 100);

        JLabel threatLabel = new JLabel("  ● " + threat + " Threat  ");
        threatLabel.setBounds(600, 18, 160, 25);
        threatLabel.setForeground(threatColor);
        threatLabel.setFont(new Font("Arial", Font.BOLD, 13));
        threatLabel.setOpaque(true);
        threatLabel.setBackground(new Color(20, 20, 35));
        threatLabel.setBorder(BorderFactory.createLineBorder(threatColor));

        JLabel statusLabel = new JLabel("  " + cyberCase.getStatus() + "  ");
        statusLabel.setBounds(440, 18, 150, 25);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(getStatusColor(cyberCase.getStatus()));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(caseIdLabel);
        headerPanel.add(typeLabel);
        headerPanel.add(statusLabel);
        headerPanel.add(threatLabel);

        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(10, 70, 830, 150);
        infoPanel.setBackground(new Color(20, 20, 35));
        infoPanel.setLayout(null);
        infoPanel.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 60)));

        JLabel infoTitle = new JLabel("CASE INFORMATION");
        infoTitle.setBounds(15, 8, 200, 20);
        infoTitle.setForeground(new Color(0, 200, 255));
        infoTitle.setFont(new Font("Arial", Font.BOLD, 12));

        addInfoRow(infoPanel, "Title",       cyberCase.getTitle(),          15,  35);
        addInfoRow(infoPanel, "Description", cyberCase.getDescription(),    15,  60);
        addInfoRow(infoPanel, "Date",        cyberCase.getDateReported(),   15,  85);

        addInfoRow(infoPanel, "Investigator", cyberCase.getInvestigatorId(), 430, 35);
        addInfoRow(infoPanel, "Victim ID",    cyberCase.getVictimId(),       430, 60);
        addInfoRow(infoPanel, "Case Type",    cyberCase.getCaseType(),       430, 85);

        ThreatAnalyzer ta = new ThreatAnalyzer();
        String rec = ta.getRecommendation(threat);
        JLabel recLabel = new JLabel("⚠  " + rec);
        recLabel.setBounds(15, 115, 800, 20);
        recLabel.setForeground(threatColor);
        recLabel.setFont(new Font("Arial", Font.ITALIC, 11));

        infoPanel.add(infoTitle);
        infoPanel.add(recLabel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(10, 230, 830, 330);
        tabbedPane.setBackground(new Color(20, 20, 35));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 12));

        JPanel evidencePanel = new JPanel();
        evidencePanel.setBackground(new Color(15, 15, 25));
        evidencePanel.setLayout(new BorderLayout());

        String[] evCols = {"Evidence ID", "Type", "Description", "Collected By", "Date"};
        evidenceModel = new DefaultTableModel(evCols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        evidenceTable = makeTable(evidenceModel);
        JScrollPane evScroll = new JScrollPane(evidenceTable);
        evScroll.getViewport().setBackground(new Color(18, 18, 30));
        evScroll.setBorder(BorderFactory.createEmptyBorder());
        evidencePanel.add(evScroll, BorderLayout.CENTER);

        JPanel suspectPanel = new JPanel();
        suspectPanel.setBackground(new Color(15, 15, 25));
        suspectPanel.setLayout(new BorderLayout());

        String[] susCols = {"Name", "IP Address", "Device", "Threat Level", "Status"};
        suspectModel = new DefaultTableModel(susCols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        suspectTable = makeTable(suspectModel);
        JScrollPane susScroll = new JScrollPane(suspectTable);
        susScroll.getViewport().setBackground(new Color(18, 18, 30));
        susScroll.setBorder(BorderFactory.createEmptyBorder());
        suspectPanel.add(susScroll, BorderLayout.CENTER);

        tabbedPane.addTab("  Evidence  ", evidencePanel);
        tabbedPane.addTab("  Suspects  ", suspectPanel);
        tabbedPane.setBackgroundAt(0, new Color(0, 100, 150));
        tabbedPane.setBackgroundAt(1, new Color(100, 0, 150));

        JButton closeBtn = makeBtn("Close", new Color(80, 80, 100), 680, 590);
        closeBtn.addActionListener(e -> dispose());

        JButton reportBtn = makeBtn("Generate Report", new Color(0, 130, 80), 520, 590);
        reportBtn.addActionListener(e -> {
            new ReportGUI(dashboard, dashboard.getCaseTracker(), fileManager);
        });

        mainPanel.add(headerPanel);
        mainPanel.add(infoPanel);
        mainPanel.add(tabbedPane);
        mainPanel.add(closeBtn);
        mainPanel.add(reportBtn);

        add(mainPanel);
    }

    private void loadEvidenceTable() {
        evidenceModel.setRowCount(0);
        ArrayList<Evidence> allEvidence = fileManager.loadEvidence();
        for (int i = 0; i < allEvidence.size(); i++) {
            Evidence ev = allEvidence.get(i);
            if (ev.getCaseId().equals(cyberCase.getCaseId())) {
                evidenceModel.addRow(new Object[]{
                        ev.getEvidenceId(), ev.getEvidenceType(),
                        ev.getDescription(), ev.getCollectedBy(), ev.getCollectedDate()
                });
            }
        }
    }

    private void loadSuspectTable() {
        suspectModel.setRowCount(0);
        ArrayList<Suspect> allSuspects = fileManager.loadSuspects();
        for (int i = 0; i < allSuspects.size(); i++) {
            Suspect s = allSuspects.get(i);
            if (s.getCaseId().equals(cyberCase.getCaseId())) {
                suspectModel.addRow(new Object[]{
                        s.getName(), s.getIpAddress(),
                        s.getDeviceInfo(), s.getThreatLevel(), s.getStatus()
                });
            }
        }
    }

    private void addInfoRow(JPanel panel, String key, String value, int x, int y) {
        JLabel keyLabel = new JLabel(key + ":");
        keyLabel.setBounds(x, y, 90, 18);
        keyLabel.setForeground(new Color(100, 150, 200));
        keyLabel.setFont(new Font("Arial", Font.BOLD, 11));

        JLabel valLabel = new JLabel(value != null ? value : "N/A");
        valLabel.setBounds(x + 95, y, 300, 18);
        valLabel.setForeground(new Color(200, 210, 220));
        valLabel.setFont(new Font("Arial", Font.PLAIN, 11));

        panel.add(keyLabel);
        panel.add(valLabel);
    }

    private Color getStatusColor(String status) {
        if (status.equals("Open"))               return new Color(160, 100, 0);
        if (status.equals("Under Investigation")) return new Color(0, 80, 160);
        if (status.equals("Closed"))             return new Color(0, 100, 50);
        if (status.equals("Escalated"))          return new Color(150, 20, 20);
        return new Color(60, 60, 80);
    }

    private JTable makeTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setBackground(new Color(18, 18, 30));
        table.setForeground(new Color(200, 210, 220));
        table.setGridColor(new Color(30, 30, 50));
        table.setSelectionBackground(new Color(0, 80, 130));
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(28);
        table.getTableHeader().setBackground(new Color(10, 10, 20));
        table.getTableHeader().setForeground(new Color(0, 200, 255));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        return table;
    }

    private JButton makeBtn(String text, Color color, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 150, 32);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}