package com.tyrone.sentinel.example.config;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 加载系统环境变量
 * @author tyrone
 */
public class SentinelInitialized {
    private static final Properties properties = new Properties();
    private static final String PROJECTNAME = "project.name";

    static {
        InputStream inputStream = SentinelInitialized.class.getClassLoader()
                .getResourceAsStream("sentinel.properties");
        try {
            properties.load(inputStream);
        } catch (IOException ioe2) {
            try {
                inputStream.close();
            } catch (IOException ioe1) {
                ioe1.printStackTrace();
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException ioe2) {
                ioe2.printStackTrace();
            }
        }
    }

    public static void loadSystemProperties() {
        if (StringUtils.isBlank(System.getProperty(PROJECTNAME)) && !StringUtils.isBlank(properties.getProperty(PROJECTNAME))) {
            System.setProperty(PROJECTNAME, properties.getProperty(PROJECTNAME));
        }
    }

}