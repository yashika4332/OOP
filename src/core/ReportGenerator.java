package core;

import cases.CyberCase;
import persons.Investigator;
import persons.Suspect;
import persons.Victim;

import java.util.ArrayList;

public class ReportGenerator {

    public void generateFullReport(CyberCase cyberCase, Investigator investigator,
                                   Victim victim, ArrayList<Suspect> suspects,
                                   ArrayList<Evidence> evidenceList) {

        System.out.println("############################################");
        System.out.println("#         CYBER CRIME INVESTIGATION        #");
        System.out.println("#              FULL CASE REPORT             #");
        System.out.println("############################################");

        System.out.println("\n--- CASE DETAILS ---");
        cyberCase.generateReport();

        System.out.println("\n--- INVESTIGATOR ---");
        if (investigator != null) {
            investigator.displayInfo();
        } else {
            System.out.println("No investigator assigned.");
        }

        System.out.println("\n--- VICTIM ---");
        if (victim != null) {
            victim.displayInfo();
        } else {
            System.out.println("No victim recorded.");
        }

        System.out.println("\n--- SUSPECTS ---");
        if (suspects == null || suspects.isEmpty()) {
            System.out.println("No suspects found.");
        } else {
            for (int i = 0; i < suspects.size(); i++) {
                System.out.println("Suspect " + (i + 1) + ":");
                suspects.get(i).displayInfo();
            }
        }

        System.out.println("\n--- EVIDENCE ---");
        if (evidenceList == null || evidenceList.isEmpty()) {
            System.out.println("No evidence logged.");
        } else {
            for (int i = 0; i < evidenceList.size(); i++) {
                System.out.println("Evidence " + (i + 1) + ":");
                evidenceList.get(i).displayEvidence();
            }
        }

        System.out.println("\n############################################");
        System.out.println("#              END OF REPORT               #");
        System.out.println("############################################");
    }

    public void generateSummary(CyberCase cyberCase) {
        System.out.println("===== CASE SUMMARY =====");
        System.out.println("Case ID   : " + cyberCase.getCaseId());
        System.out.println("Type      : " + cyberCase.getCaseType());
        System.out.println("Title     : " + cyberCase.getTitle());
        System.out.println("Status    : " + cyberCase.getStatus());
        System.out.println("Date      : " + cyberCase.getDateReported());
        System.out.println("========================");
    }

    public void generateCaseList(ArrayList<CyberCase> caseList) {
        System.out.println("===== ALL CASES =====");
        if (caseList == null || caseList.isEmpty()) {
            System.out.println("No cases found.");
        } else {
            for (int i = 0; i < caseList.size(); i++) {
                System.out.println((i + 1) + ". " + caseList.get(i).toString());
            }
        }
        System.out.println("Total Cases: " + (caseList != null ? caseList.size() : 0));
        System.out.println("=====================");
    }
}