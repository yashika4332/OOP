package core;

import cases.*;
import persons.*;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String CASES_FILE      = "data/cases.txt";
    private static final String SUSPECTS_FILE   = "data/suspects.txt";
    private static final String EVIDENCE_FILE   = "data/evidence.txt";
    private static final String PERSONS_FILE    = "data/persons.txt";

    public void saveCases(ArrayList<CyberCase> caseList) {
        try {
            FileWriter fw = new FileWriter(CASES_FILE);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < caseList.size(); i++) {
                bw.write(caseList.get(i).toFileString());
                bw.newLine();
            }

            bw.close();
            fw.close();
            System.out.println("Cases saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving cases: " + e.getMessage());
        }
    }

    public ArrayList<CyberCase> loadCases() {
        ArrayList<CyberCase> caseList = new ArrayList<>();

        try {
            FileReader fr = new FileReader(CASES_FILE);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String caseType = line.split("\\|")[0];

                if (caseType.equals("PHISHING")) {
                    caseList.add(PhishingCase.fromFileString(line));

                } else if (caseType.equals("MALWARE")) {
                    caseList.add(MalwareCase.fromFileString(line));

                } else if (caseType.equals("IDENTITYTHEFT")) {
                    caseList.add(IdentityTheftCase.fromFileString(line));

                } else if (caseType.equals("RANSOMWARE")) {
                    caseList.add(RansomwareCase.fromFileString(line));

                } else if (caseType.equals("DATABREACH")) {
                    caseList.add(DataBreachCase.fromFileString(line));
                }
            }

            br.close();
            fr.close();
            System.out.println("Cases loaded: " + caseList.size());

        } catch (IOException e) {
            System.out.println("No cases file found. Starting fresh.");
        }

        return caseList;
    }

    public void saveSuspects(ArrayList<Suspect> suspectList) {
        try {
            FileWriter fw = new FileWriter(SUSPECTS_FILE);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < suspectList.size(); i++) {
                bw.write(suspectList.get(i).toFileString());
                bw.newLine();
            }

            bw.close();
            fw.close();
            System.out.println("Suspects saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving suspects: " + e.getMessage());
        }
    }

    public ArrayList<Suspect> loadSuspects() {
        ArrayList<Suspect> suspectList = new ArrayList<>();

        try {
            FileReader fr = new FileReader(SUSPECTS_FILE);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                suspectList.add(Suspect.fromFileString(line));
            }

            br.close();
            fr.close();
            System.out.println("Suspects loaded: " + suspectList.size());

        } catch (IOException e) {
            System.out.println("No suspects file found. Starting fresh.");
        }

        return suspectList;
    }

    public void saveEvidence(ArrayList<Evidence> evidenceList) {
        try {
            FileWriter fw = new FileWriter(EVIDENCE_FILE);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < evidenceList.size(); i++) {
                bw.write(evidenceList.get(i).toFileString());
                bw.newLine();
            }

            bw.close();
            fw.close();
            System.out.println("Evidence saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving evidence: " + e.getMessage());
        }
    }

    public ArrayList<Evidence> loadEvidence() {
        ArrayList<Evidence> evidenceList = new ArrayList<>();

        try {
            FileReader fr = new FileReader(EVIDENCE_FILE);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                evidenceList.add(Evidence.fromFileString(line));
            }

            br.close();
            fr.close();
            System.out.println("Evidence loaded: " + evidenceList.size());

        } catch (IOException e) {
            System.out.println("No evidence file found. Starting fresh.");
        }

        return evidenceList;
    }

    public void savePersons(ArrayList<Person> personList) {
        try {
            FileWriter fw = new FileWriter(PERSONS_FILE);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < personList.size(); i++) {
                bw.write(personList.get(i).toFileString());
                bw.newLine();
            }

            bw.close();
            fw.close();
            System.out.println("Persons saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving persons: " + e.getMessage());
        }
    }

    public ArrayList<Person> loadPersons() {
        ArrayList<Person> personList = new ArrayList<>();

        try {
            FileReader fr = new FileReader(PERSONS_FILE);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String personType = line.split("\\|")[0];

                if (personType.equals("INVESTIGATOR")) {
                    personList.add(Investigator.fromFileString(line));

                } else if (personType.equals("ANALYST")) {
                    personList.add(Analyst.fromFileString(line));

                } else if (personType.equals("VICTIM")) {
                    personList.add(Victim.fromFileString(line));
                }
            }

            br.close();
            fr.close();
            System.out.println("Persons loaded: " + personList.size());

        } catch (IOException e) {
            System.out.println("No persons file found. Starting fresh.");
        }

        return personList;
    }

    public void createDataFolder() {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
            System.out.println("Data folder created.");
        }
    }
}