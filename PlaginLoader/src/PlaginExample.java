public class PlaginExample implements Plagin {

    @Override
    public void load() {
        System.out.println("Module " + this.getClass() + " loading ...");
    }

    @Override
    public void unload() {
        System.out.println("Module " + this.getClass() + " inloading ...");
    }
}
