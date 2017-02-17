package com.gl.dwss.dirsync.server.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages={"com.gl.dwss"})
@EnableConfigurationProperties({DirSyncServerProperties.class})
public class DirSyncServerConfiguration {

}
