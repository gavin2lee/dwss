package com.gl.order.client.batch.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleBatchJobBootstrap {
    private static final Logger log = LoggerFactory.getLogger(SimpleBatchJobBootstrap.class);

    private void boot(String... args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/quartz-application.xml");

        try {
            Thread.sleep(1000 * 0);
        } catch (InterruptedException e) {
            log.error("", e);
        }
        
        Runtime.getRuntime().addShutdownHook(new Thread(){

            @Override
            public void run() {
                log.warn("try to shutdown");
                if(AbstractApplicationContext.class.isAssignableFrom(ctx.getClass())){
                    ((AbstractApplicationContext)ctx).close();
                }
            }
            
        });
    }

    public static void main(String[] args) {
        log.info("start");
        new SimpleBatchJobBootstrap().boot(args);
        log.info("exit");
    }

}
