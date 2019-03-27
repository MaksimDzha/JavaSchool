public class TestClass implements TestInterface {

    private String name;

    public TestClass(String name){
        this.name = name;
    }

    @Override
    public String getName(){
        System.out.println(name);
        return name;
    }

    @Override
    @Cache
    public int getNameLength() {
        int length = name.length();
        System.out.println(length);
        return length;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
