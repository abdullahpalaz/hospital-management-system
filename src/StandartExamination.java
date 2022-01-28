public class StandartExamination implements Examination{

    public String examinationType;


    public StandartExamination(String s){
        this.examinationType = s;
    }

    @Override
    public int cost() {
       if (this.examinationType.equals("Inpatient")){
           return 10;
       } else if (this.examinationType.equals("Outpatient")){
           return 15;
       } else{
           return 0;
       }
    }

    @Override
    public String operations() {
        return "";
    }

    @Override
    public String getType() {
        return this.examinationType;
    }

    public static Examination addOperation(String s, Examination ex) {
        switch (s){
            case "imaging":
                ex = new Imaging(ex);
                return ex;
            case "tests":
                ex = new Tests(ex);
                return ex;
            case "doctorvisit":
                ex = new DoctorVisit(ex);
                return ex;
            case "measurements":
                ex = new Measurements(ex);
                return ex;
            }
        return null;
    }
}
