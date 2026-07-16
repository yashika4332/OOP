package gui;

import cases.CyberCase;
import core.*;
import persons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ReportGUI extends JFrame {

    private CaseTracker caseTracker;
    private FileManager fileManager;

    private JComboBox<String> caseCombo;
    private JTextArea reportArea;

    public ReportGUI(DashboardGUI dashboard, CaseTracker caseTracker, FileManager fileManager) {
        this.caseTracker = caseTracker;
        this.fileManager = fileManager;

        setTitle("Report Generator");
        setSize(700, 600);
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

        JLabel titleLabel = new JLabel("REPORT GENERATOR");
        titleLabel.setBounds(0, 10, 700, 30);
        titleLabel.setForeground(new Color(0, 200, 255));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel selectLabel = new JLabel("Select Case:");
        selectLabel.setBounds(20, 55, 100, 25);
        selectLabel.setForeground(Color.WHITE);
        selectLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        caseCombo = new JComboBox<>();
        caseCombo.setBounds(130, 55, 350, 25);
        caseCombo.setBackground(new Color(50, 50, 50));
        caseCombo.setForeground(Color.WHITE);
        loadCasesInCombo();

        JButton summaryBtn  = createButton("Case Summary",    new Color(0, 150, 200),  500, 55);
        JButton fullBtn     = createButton("Full Report",     new Color(0, 150, 100),  500, 95);
        JButton threatBtn   = createButton("Threat Analysis", new Color(200, 100, 0),  500, 135);
        JButton allCasesBtn = createButton("All Cases List",  new Color(100, 0, 200),  500, 175);
        JButton clearBtn    = createButton("Clear",           new Color(100, 100, 100),500, 215);

        reportArea = new JTextArea();
        reportArea.setBackground(new Color(10, 10, 10));
        reportArea.setForeground(new Color(0, 255, 100));
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        reportArea.setEditable(false);
        reportArea.setCaretColor(Color.WHITE);
        reportArea.setText("Select a case and click a button to generate report...");

        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBounds(20, 260, 650, 280);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 200, 255)));
        scrollPane.getViewport().setBackground(new Color(10, 10, 10));

        summaryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { generateSummary(); }
        });

        fullBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { generateFullReport(); }
        });

        threatBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { generateThreatReport(); }
        });

        allCasesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { generateAllCasesList(); }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { reportArea.setText(""); }
        });

        JButton closeBtn = createButton("Close", new Color(150, 50, 50), 290, 555);
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { dispose(); }
        });

        mainPanel.add(titleLabel);
        mainPanel.add(selectLabel);
        mainPanel.add(caseCombo);
        mainPanel.add(summaryBtn);
        mainPanel.add(fullBtn);
        mainPanel.add(threatBtn);
        mainPanel.add(allCasesBtn);
        mainPanel.add(clearBtn);
        mainPanel.add(scrollPane);
        mainPanel.add(closeBtn);

        add(mainPanel);
    }

    private void loadCasesInCombo() {
        caseCombo.removeAllItems();
        ArrayList<CyberCase> allCases = caseTracker.getAllCases();
        for (int i = 0; i < allCases.size(); i++) {
            caseCombo.addItem(allCases.get(i).getCaseId() + " - " + allCases.get(i).getTitle());
        }
    }

    private CyberCase getSelectedCase() {
        int index = caseCombo.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a case first!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return caseTracker.getAllCases().get(index);
    }

    private void generateSummary() {
        CyberCase selected = getSelectedCase();
        if (selected == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append("===== CASE SUMMARY =====\n");
        sb.append("Case ID   : ").append(selected.getCaseId()).append("\n");
        sb.append("Type      : ").append(selected.getCaseType()).append("\n");
        sb.append("Title     : ").append(selected.getTitle()).append("\n");
        sb.append("Status    : ").append(selected.getStatus()).append("\n");
        sb.append("Date      : ").append(selected.getDateReported()).append("\n");
        sb.append("Invest.   : ").append(selected.getInvestigatorId()).append("\n");
        sb.append("Victim    : ").append(selected.getVictimId()).append("\n");
        sb.append("========================\n");

        reportArea.setText(sb.toString());
    }

    private void generateFullReport() {
        CyberCase selected = getSelectedCase();
        if (selected == null) return;

        ArrayList<Evidence> allEvidence = fileManager.loadEvidence();
        ArrayList<Evidence> caseEvidence = new ArrayList<>();
        for (int i = 0; i < allEvidence.size(); i++) {
            if (allEvidence.get(i).getCaseId().equals(selected.getCaseId())) {
                caseEvidence.add(allEvidence.get(i));
            }
        }

        ArrayList<Suspect> allSuspects = fileManager.loadSuspects();
        ArrayList<Suspect> caseSuspects = new ArrayList<>();
        for (int i = 0; i < allSuspects.size(); i++) {
            if (allSuspects.get(i).getCaseId().equals(selected.getCaseId())) {
                caseSuspects.add(allSuspects.get(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("############################################\n");
        sb.append("#       CYBER CRIME INVESTIGATION          #\n");
        sb.append("#           FULL CASE REPORT               #\n");
        sb.append("############################################\n\n");

        sb.append("--- CASE DETAILS ---\n");
        sb.append("Case ID     : ").append(selected.getCaseId()).append("\n");
        sb.append("Case Type   : ").append(selected.getCaseType()).append("\n");
        sb.append("Title       : ").append(selected.getTitle()).append("\n");
        sb.append("Description : ").append(selected.getDescription()).append("\n");
        sb.append("Date        : ").append(selected.getDateReported()).append("\n");
        sb.append("Status      : ").append(selected.getStatus()).append("\n");
        sb.append("Investigator: ").append(selected.getInvestigatorId()).append("\n");
        sb.append("Victim      : ").append(selected.getVictimId()).append("\n\n");

        sb.append("--- SUSPECTS (").append(caseSuspects.size()).append(") ---\n");
        if (caseSuspects.isEmpty()) {
            sb.append("No suspects found.\n");
        } else {
            for (int i = 0; i < caseSuspects.size(); i++) {
                Suspect s = caseSuspects.get(i);
                sb.append((i + 1)).append(". ").append(s.getName())
                        .append(" | IP: ").append(s.getIpAddress())
                        .append(" | Threat: ").append(s.getThreatLevel()).append("\n");
            }
        }

        sb.append("\n--- EVIDENCE (").append(caseEvidence.size()).append(") ---\n");
        if (caseEvidence.isEmpty()) {
            sb.append("No evidence found.\n");
        } else {
            for (int i = 0; i < caseEvidence.size(); i++) {
                Evidence ev = caseEvidence.get(i);
                sb.append((i + 1)).append(". [").append(ev.getEvidenceType()).append("] ")
                        .append(ev.getDescription())
                        .append(" | Date: ").append(ev.getCollectedDate()).append("\n");
            }
        }

        sb.append("\n############################################\n");
        sb.append("#            END OF REPORT                 #\n");
        sb.append("############################################\n");

        reportArea.setText(sb.toString());
    }

    private void generateThreatReport() {
        CyberCase selected = getSelectedCase();
        if (selected == null) return;

        ThreatAnalyzer analyzer = new ThreatAnalyzer();
        String threatLevel    = analyzer.analyzeCase(selected);
        String recommendation = analyzer.getRecommendation(threatLevel);

        StringBuilder sb = new StringBuilder();
        sb.append("===== THREAT ANALYSIS REPORT =====\n");
        sb.append("Case ID       : ").append(selected.getCaseId()).append("\n");
        sb.append("Case Type     : ").append(selected.getCaseType()).append("\n");
        sb.append("Title         : ").append(selected.getTitle()).append("\n");
        sb.append("Threat Level  : ").append(threatLevel).append("\n");
        sb.append("Recommendation: ").append(recommendation).append("\n");
        sb.append("===================================\n");

        reportArea.setText(sb.toString());
    }

    private void generateAllCasesList() {
        ArrayList<CyberCase> allCases = caseTracker.getAllCases();

        StringBuilder sb = new StringBuilder();
        sb.append("===== ALL CASES LIST =====\n\n");

        if (allCases.isEmpty()) {
            sb.append("No cases found.\n");
        } else {
            for (int i = 0; i < allCases.size(); i++) {
                CyberCase c = allCases.get(i);
                sb.append((i + 1)).append(". ")
                        .append(c.getCaseId()).append(" | ")
                        .append(c.getCaseType()).append(" | ")
                        .append(c.getTitle()).append(" | ")
                        .append(c.getStatus()).append("\n");
            }
        }

        sb.append("\nTotal Cases: ").append(allCases.size()).append("\n");
        sb.append("==========================\n");

        reportArea.setText(sb.toString());
    }

    private JButton createButton(String text, Color color, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 160, 30);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 11));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }
}