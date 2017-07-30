package com.gl.order.online.web.boot;

import java.io.OutputStream;
import java.net.Socket;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class OrderOnlineWebBoot {
    static int startPort = 9069;
    static int shutdownPort = 9059;

    public static void main(String[] args) throws LifecycleException {
        // 设置工作目录
        String catalinaHome = "/home/gavin/Dev/GitRepo/dwss/order/logs";
        Tomcat tomcat = new Tomcat();
        tomcat.setHostname("localhost");
        tomcat.setPort(startPort);
        // 设置工作目录,其实没什么用,tomcat需要使用这个目录进行写一些东西
        tomcat.setBaseDir(catalinaHome);

        // 设置程序的目录信息
        tomcat.getHost().setAppBase("/home/gavin/Dev/GitRepo/dwss/order/logs/webapp/");
        // Add AprLifecycleListener
        StandardServer server = (StandardServer) tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);
        // 注册关闭端口以进行关闭
        tomcat.getServer().setPort(shutdownPort);

        // 加载上下文
        StandardContext standardContext = new StandardContext();
        standardContext.setPath("/online");// contextPath
        standardContext.setDocBase("/home/gavin/Dev/GitRepo/dwss/order/order-online-web/target/order-online-web/");//文件目录位置
        standardContext.addLifecycleListener(new Tomcat.DefaultWebXmlListener());
        // 保证已经配置好了。
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        standardContext.setSessionCookieName("t-session");
        tomcat.getHost().addChild(standardContext);
        
        String ctxPath = "classpath:spring/order-online-web-application-context.xml";
        
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(ctxPath);
        GenericWebApplicationContext webCtx = new GenericWebApplicationContext();
//        webCtx.setConfigLocation("classpath:spring/order-online-web-application-context.xml");
        DispatcherServlet dispatchServlet = new DispatcherServlet();
        dispatchServlet.setContextConfigLocation("classpath:spring/order-online-web-application-context.xml");
//        dispatchServlet.refresh();
        tomcat.addServlet("/online", "online", dispatchServlet);
        tomcat.start();
        tomcat.getServer().await();

    }

    public static void shutdown() throws Exception {
        Socket socket = new Socket("localhost", shutdownPort);
        OutputStream stream = socket.getOutputStream();
        for (int i = 0; i < "shutdown".length(); i++) {
            stream.write("shutdown".charAt(i));
        }
        stream.flush();
        stream.close();
        socket.close();
    }
}
