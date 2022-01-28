public class OperationDecorator implements Examination {

    Examination examination;

    public OperationDecorator(Examination e){
        this.examination = e;
    }

    @Override
    public int cost() {
        return this.examination.cost();
    }

    @Override
    public String operations() {
        return this.examination.operations();
    }

    @Override
    public String getType() {
        return this.examination.getType();
    }
}
