public class Tests extends OperationDecorator{

    public Tests(Examination e) {
        super(e);
    }

    @Override
    public int cost() {
        return super.cost() + 7;
    }

    @Override
    public String operations() {
        return this.examination.operations() + "tests ";
    }
}
