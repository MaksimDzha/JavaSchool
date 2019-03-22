public class PlaginExample implements Plagin {

    @Override
    public void load() {
        System.out.println("Plagin " + this.getClass() + " loading ...");
    }

//    @Override
//    public void unload() {
//        System.out.println("Plagin " + this.getClass() + " unloading ...");
//    }
}
