public class Imaging extends OperationDecorator{

    public Imaging(Examination e) {
        super(e);
    }

    @Override
    public int cost() {
        return super.cost() + 10;
    }

    @Override
    public String operations() {
        return this.examination.operations() + "imaging ";
    }
}
