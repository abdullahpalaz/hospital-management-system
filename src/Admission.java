import java.util.ArrayList;

public class Admission {

    int admissionId;
    ArrayList<Examination> examinations = new ArrayList<Examination>();

    public Admission(int admissionId){
        this.admissionId = admissionId;
    }
    public Admission(){}

    public void addExamination(Examination examination){
        this.examinations.add(examination);
    }

    public int getCost(){
        int totalCost = 0;
        for(Examination ex: examinations){
            totalCost += ex.cost();
        }
        return totalCost;
    }
}
