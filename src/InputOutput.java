import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class InputOutput {

    public static void readInputWriteOutput(String inputFileName, String outputFileName, ArrayList<Patient> patientArray) throws Exception{
        File fileRead = new File(inputFileName);
        BufferedReader bf = new BufferedReader(new FileReader(fileRead));

        File fileWrite = new File(outputFileName);
        FileWriter outputWriter = new FileWriter(fileWrite);

        String line;

        while ((line = bf.readLine()) != null){
            String[] oneLine = line.split(" ");
            switch (oneLine[0]){
                case "AddPatient":
                    int patientIdToAdd = Integer.parseInt(oneLine[1]);
                    String adress = "Adress: ";
                    for (int i = 5; i < oneLine.length; i++){
                        adress = adress.concat(oneLine[i]+" ");
                    }
                    Patient patient1 = new Patient(patientIdToAdd, oneLine[2], oneLine[3], adress, oneLine[4]);
                    patientArray.add(patient1);
                    outputWriter.write("Patient "+patientIdToAdd+" "+oneLine[2]+" added\n");
                    break;
                case "RemovePatient":
                    int patientIdToRemove = Integer.parseInt(oneLine[1]);
                    Patient patient2 = PatientData.getById(patientIdToRemove, patientArray);
                    outputWriter.write("Patient "+patientIdToRemove+" "+patient2.name+" removed\n");
                    patientArray.removeIf(patient -> (patient.patientId==patientIdToRemove));
                    break;
                case "CreateAdmission":
                    int admissionIdToCreate = Integer.parseInt(oneLine[1]);
                    int patientIdForAdmission = Integer.parseInt(oneLine[2]);
                    Patient patientToAddAdmission = PatientData.getById(patientIdForAdmission, patientArray);
                    Admission admissionCreated = new Admission(admissionIdToCreate);
                    patientToAddAdmission.setAdmission(admissionCreated);
                    outputWriter.write("Admission "+admissionIdToCreate+" created\n");
                    break;
                case "AddExamination":
                    int admissionIdForExamination = Integer.parseInt(oneLine[1]);
                    Admission admissionToAddEx = AdmissionData.getById(admissionIdForExamination, patientArray);
                    Examination ex1 = new StandartExamination(oneLine[2]);
                    for (int i = 3; i < oneLine.length; i++){
                        ex1 = StandartExamination.addOperation(oneLine[i], ex1);
                    }
                    admissionToAddEx.addExamination(ex1);
                    outputWriter.write(oneLine[2]+" examination added to admission "+admissionIdForExamination+"\n");
                    break;
                case "TotalCost":
                    int admissionIdForCost = Integer.parseInt(oneLine[1]);
                    Admission admissionForCost = AdmissionData.getById(admissionIdForCost, patientArray);
                    outputWriter.write("Total cost for admission "+admissionIdForCost+"\n");
                    for (Examination ex: admissionForCost.examinations){
                        outputWriter.write("\t"+ex.getType()+" "+ex.operations()+ex.cost()+"$\n");
                    }
                    outputWriter.write("\tTotal: "+admissionForCost.getCost()+"$\n");
                    break;
                case "ListPatients":
                    outputWriter.write("Patient List:\n");
                    Collections.sort(patientArray, PatientData.patientComparatorByPatientName);
                    for (Patient p: patientArray){
                        outputWriter.write(p.patientId+" "+p.name+" "+p.surname+" "+p.phoneNumber+" "+p.adress+"\n");
                    }
                    break;
            }
        }
        outputWriter.close();
    }

}
