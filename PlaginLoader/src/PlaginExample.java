public class PlaginExample implements Plagin {

    @Override
    public void load() {
        System.out.println("Module " + this.getClass() + " loading ...");
    }

    @Override
    public int run() {
        System.out.println("Module " + this.getClass() + " running ...");
        return Plagin.EXIT_SUCCESS;
    }

    @Override
    public void unload() {
        System.out.println("Module " + this.getClass() + " inloading ...");
    }
}
