package com.gl.dwss.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages={"com.gl.dwss"})
@EnableConfigurationProperties({GlobalAppProperties.class})
public class SpringBootDemoConfig {

}
