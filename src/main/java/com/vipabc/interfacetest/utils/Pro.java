package com.vipabc.interfacetest.utils;

import shelper.environment.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Pro {

    public String getEnvPropties(String urlKey, String fileName) {
        Properties prop = new Properties();
        FileInputStream fis = null;
        String url = null;
        String separator = File.separator;

        try {
            fis = new FileInputStream("." + Environment.get("Selenium.EnvironmentRoot") + fileName + ".env");
//			System.out.println("@"+"."+ Environment.get("Selenium.EnvironmentRoot")+fileName+".env");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return prop.getProperty(urlKey);
    }

    public static void main(String args[]) {
        Environment.set();
        Pro i = new Pro();
        System.out.println(i.getEnvPropties("test.mysqUrl", "test"));
        System.out.println(i.getEnvPropties("url.www", "url"));

    }
}
