package core;

import cases.CyberCase;
import persons.Suspect;

public class ThreatAnalyzer {

    public static final String LOW      = "Low";
    public static final String MEDIUM   = "Medium";
    public static final String HIGH     = "High";
    public static final String CRITICAL = "Critical";

    public String analyzeCase(CyberCase cyberCase) {
        String caseType = cyberCase.getCaseType();
        String threatLevel;

        if (caseType.equals("Ransomware") || caseType.equals("Data Breach")) {
            threatLevel = CRITICAL;
        } else if (caseType.equals("Malware")) {
            threatLevel = HIGH;
        } else if (caseType.equals("Phishing")) {
            threatLevel = MEDIUM;
        } else {
            threatLevel = LOW;
        }

        System.out.println("Case ID     : " + cyberCase.getCaseId());
        System.out.println("Case Type   : " + caseType);
        System.out.println("Threat Level: " + threatLevel);

        return threatLevel;
    }

    public String analyzeSuspect(Suspect suspect) {
        String currentThreat = suspect.getThreatLevel();

        System.out.println("Suspect     : " + suspect.getName());
        System.out.println("IP Address  : " + suspect.getIpAddress());
        System.out.println("Threat Level: " + currentThreat);

        return currentThreat;
    }

    public String getRecommendation(String threatLevel) {
        if (threatLevel.equals(CRITICAL)) {
            return "Immediate action required! Escalate to senior authorities.";
        } else if (threatLevel.equals(HIGH)) {
            return "High priority case. Assign experienced investigator.";
        } else if (threatLevel.equals(MEDIUM)) {
            return "Monitor closely. Regular updates required.";
        } else {
            return "Low risk. Handle as routine case.";
        }
    }

    public void displayThreatReport(CyberCase cyberCase) {
        System.out.println("===== THREAT ANALYSIS REPORT =====");
        String threatLevel = analyzeCase(cyberCase);
        System.out.println("Recommendation: " + getRecommendation(threatLevel));
        System.out.println("===================================");
    }
}