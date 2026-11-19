package tiffin;
public class SpringModuleCheck {
    public static void main(String[] args) {
        String[] classes = {
            "org.springframework.core.SpringVersion",
            "org.springframework.context.ApplicationContext",
            "org.springframework.jdbc.core.JdbcTemplate",
            "org.springframework.aop.framework.ProxyFactory"
        };
        for (String name : classes) {
            try { Class.forName(name); System.out.println(name + " : FOUND"); }
            catch (ClassNotFoundException e) { System.out.println(name + " : MISSING"); }
        }
        try {
            Class<?> versionClass = Class.forName("org.springframework.core.SpringVersion");
            Object version = versionClass.getMethod("getVersion").invoke(null);
            System.out.println("Spring version: " + version);
        } catch (ReflectiveOperationException e) {
            System.out.println("Spring version: unavailable");
        }
    }
}
