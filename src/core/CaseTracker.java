package core;

import cases.CyberCase;
import java.util.ArrayList;

public class CaseTracker {

    private ArrayList<CyberCase> caseList;

    public CaseTracker() {
        caseList = new ArrayList<>();
    }

    public void addCase(CyberCase cyberCase) {
        caseList.add(cyberCase);
        System.out.println("Case added: " + cyberCase.getCaseId() + " - " + cyberCase.getTitle());
    }

    public boolean removeCase(String caseId) {
        for (int i = 0; i < caseList.size(); i++) {
            if (caseList.get(i).getCaseId().equals(caseId)) {
                caseList.remove(i);
                System.out.println("Case removed: " + caseId);
                return true;
            }
        }
        System.out.println("Case not found: " + caseId);
        return false;
    }

    public CyberCase findCase(String caseId) {
        for (int i = 0; i < caseList.size(); i++) {
            if (caseList.get(i).getCaseId().equals(caseId)) {
                return caseList.get(i);
            }
        }
        return null;
    }

    public void updateCaseStatus(String caseId, String newStatus) {
        CyberCase cyberCase = findCase(caseId);
        if (cyberCase != null) {
            cyberCase.updateStatus(newStatus);
        } else {
            System.out.println("Case not found: " + caseId);
        }
    }

    public ArrayList<CyberCase> filterByStatus(String status) {
        ArrayList<CyberCase> filtered = new ArrayList<>();
        for (int i = 0; i < caseList.size(); i++) {
            if (caseList.get(i).getStatus().equals(status)) {
                filtered.add(caseList.get(i));
            }
        }
        return filtered;
    }

    public ArrayList<CyberCase> filterByType(String caseType) {
        ArrayList<CyberCase> filtered = new ArrayList<>();
        for (int i = 0; i < caseList.size(); i++) {
            if (caseList.get(i).getCaseType().equals(caseType)) {
                filtered.add(caseList.get(i));
            }
        }
        return filtered;
    }

    public ArrayList<CyberCase> getAllCases() {
        return caseList;
    }

    public int getTotalCases() {
        return caseList.size();
    }

    public void displayAllCases() {
        System.out.println("===== ALL CASES =====");
        if (caseList.isEmpty()) {
            System.out.println("No cases found.");
        } else {
            for (int i = 0; i < caseList.size(); i++) {
                System.out.println((i + 1) + ". " + caseList.get(i).toString());
            }
        }
        System.out.println("Total: " + caseList.size() + " cases");
        System.out.println("=====================");
    }
}