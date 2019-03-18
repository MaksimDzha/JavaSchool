public class Test {

    public static void main(String[] args) {
        String[] s = new String[1];
        System.out.println(Integer.class.getCanonicalName());
        s[0] = "java.lang.Integer";
//        s[1] = "get";
//        ShowMethods.main(s);
        ShowDeclaredMethods.main(s);
    }
}
