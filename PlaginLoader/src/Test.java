public class Test {
    public static void main(String[] args) {
        PluginManager p = new PluginManager("C://Java/pluginRootDirectory/pluginName/");
        p.load("PlaginExample", "class PlaginExample");
        System.out.println(PlaginExample.class.getClassLoader().toString());
    }
}
