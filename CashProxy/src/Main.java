import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        TestClass testClass = new TestClass("Max");
        TestInterface proxyTestClass = (TestInterface) Proxy.newProxyInstance(
                TestClass.class.getClassLoader(),
                TestClass.class.getInterfaces(),
                new CacheInvocationHandler(testClass));
//        proxyTestClass.getName();
        proxyTestClass.getNameLength();
        testClass.setName("Alex");
//        proxyTestClass.setName("Alex");
//        proxyTestClass.getName();
        proxyTestClass.getNameLength();

    }
}
