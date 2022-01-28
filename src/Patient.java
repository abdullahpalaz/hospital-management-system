public class Patient {

    int patientId;
    String name;
    String surname;
    String adress;
    String phoneNumber;
    Admission admission;


    public Patient(int patientId, String name, String surname, String adress, String phoneNumber){
        this.patientId = patientId;
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public void setAdmission(Admission admission){
        this.admission = admission;
    }
}
