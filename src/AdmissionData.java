import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class AdmissionData {

    public static void fromFileToArray(String admissionDataFileName, ArrayList<Patient> patientArray) throws Exception{
        File file = new File(admissionDataFileName);
        BufferedReader bf = new BufferedReader(new FileReader(file));

        String line;
        int patientIDatm = 0;
        while ((line = bf.readLine()) != null) {
            if (Character.isDigit(line.charAt(0))) {
                String[] ids = line.split("\t");
                int admissionId = Integer.parseInt(ids[0]);
                int patientId = Integer.parseInt(ids[1]);
                patientIDatm = patientId;
                Patient patient = PatientData.getById(patientId, patientArray);
                Admission admission = new Admission(admissionId);
                patient.setAdmission(admission);
            }else if (line.startsWith("Inpatient") || line.startsWith("Outpatient")) {
                String[] oneExamination = line.split("\t");
                String[] operations = oneExamination[1].split(" ");
                Examination ex = new StandartExamination(oneExamination[0]);
                for (String operation: operations){
                    ex = StandartExamination.addOperation(operation, ex);
                }
                PatientData.getById(patientIDatm, patientArray).admission.examinations.add(ex);
            }
        }
    }

    public static void fromArrayToFile(String admissionDataFileName, ArrayList<Patient> patientArray) throws Exception{
        ArrayList<Patient> compareArray = new ArrayList<Patient>();
        for(Patient p: patientArray) {
            if (p.admission != null && p.admission.examinations != null) {
                compareArray.add(p);
            }
        }
        Collections.sort(compareArray, PatientData.patientComparatorByAdmissionID);
        File file = new File(admissionDataFileName);
        FileWriter writer = new FileWriter(file, false);
        for (Patient p: compareArray){
            writer.write(p.admission.admissionId+"\t"+p.patientId+"\n");
            for (Examination e: p.admission.examinations){
                System.out.println(e.getType());
                writer.write(e.getType()+"\t"+e.operations()+"\n");
            }
        }
        writer.close();
    }

    public static Admission getById(int id, ArrayList<Patient> patientArray) {
        for (Patient p: patientArray) {
            if (p.admission != null) {
                if (p.admission.admissionId == id) {
                    return p.admission;
                }
            }
        }
        return null;
    }
}
