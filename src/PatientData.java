import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PatientData{

    public static void fromFileToArray(String patientDataFileName, ArrayList<Patient> patientArray) throws Exception{
        File file = new File(patientDataFileName);
        BufferedReader bf = new BufferedReader(new FileReader(file));

        String line;

        while ((line = bf.readLine()) != null){
            String[] onePatient = line.split("\t");
            int id = Integer.parseInt(onePatient[0]);
            String[] patientNameSurname = onePatient[1].split(" ");
            Patient patient = new Patient(id, patientNameSurname[0], patientNameSurname[1], onePatient[3], onePatient[2]);
            patientArray.add(patient);
        }
    }

    public static void fromArrayToFile(String patientDataFileName, ArrayList<Patient> patientArray) throws Exception{
        File file = new File(patientDataFileName);
        FileWriter writer = new FileWriter(file, false);
        Collections.sort(patientArray, PatientData.patientComparatorByPatientID);
        for (Patient p: patientArray){
            writer.write(p.patientId+"\t"+p.name+" "+p.surname+"\t"+p.phoneNumber+"\t"+p.adress+"\n");
        }
        writer.close();
    }


    public static Patient getById(int id, ArrayList<Patient> patients){
        for (Patient p: patients){
            if (p.patientId == id){
                return p;
            }
        }
        return null;
    }


    public static Comparator<Patient> patientComparatorByPatientID = new Comparator<Patient>() {
        @Override
        public int compare(Patient p1, Patient p2) {
            return p1.patientId - p2.patientId;
        }
    };

    public static Comparator<Patient> patientComparatorByPatientName = new Comparator<Patient>() {
        @Override
        public int compare(Patient p1, Patient p2) {
            return p1.name.compareTo(p2.name);
        }
    };

    public static Comparator<Patient> patientComparatorByAdmissionID = new Comparator<Patient>() {
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.admission.admissionId - o2.admission.admissionId;
        }
    };
}
