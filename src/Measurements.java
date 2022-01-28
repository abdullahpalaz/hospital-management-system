public class Measurements extends OperationDecorator{

    public Measurements(Examination e) {
        super(e);
    }

    @Override
    public int cost() {
        return super.cost() + 5;
    }

    @Override
    public String operations() {
        return this.examination.operations() + "measurements ";
    }
}
