package ru.practicum;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class LaterApplication {
    private static final int PORT = 8080;

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        // connector -- это компонент, который отвечает за "сеть"
        tomcat.getConnector().setPort(PORT);

        // то самое "приложение" или "контекст" с пустым путём
        Context tomcatContext = tomcat.addContext("", null);

        // класс Wrapper позволяет задать дополнительные настройки для сервлета
        Wrapper testServletWrapper =
                Tomcat.addServlet(tomcatContext, "testServlet", new TestServlet());
        // addMapping() сопоставляет URL-путь с сервлетом
        testServletWrapper.addMapping("/test");
        tomcat.start();
    }
}
