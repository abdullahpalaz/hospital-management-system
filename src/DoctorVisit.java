public class DoctorVisit extends OperationDecorator {

    public DoctorVisit(Examination e) {
        super(e);
    }

    @Override
    public int cost() {
        return super.cost() + 15;
    }

    @Override
    public String operations() {
        return this.examination.operations() + "doctorvisit ";
    }
}
