import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception{

        String inputFileName = args[0];
        String outputFileName = "output.txt";
        String patientDataFileName = "patient.txt";
        String admissionDataFileName = "admission.txt";

        ArrayList<Patient> patients = new ArrayList<Patient>();

        PatientData.fromFileToArray(patientDataFileName, patients);
        AdmissionData.fromFileToArray(admissionDataFileName, patients);

        InputOutput.readInputWriteOutput(inputFileName, outputFileName, patients);

        PatientData.fromArrayToFile(patientDataFileName, patients);
        AdmissionData.fromArrayToFile(admissionDataFileName, patients);

    }
}
