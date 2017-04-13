package com.vipabc.interfacetest.utils;

import shelper.environment.Environment;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
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

    public Properties getEnvPropties(int brandId, String fileName) {
        String sign = brandId == 4 ? "" : "_v";
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("." + Environment.get("Selenium.EnvironmentRoot") + fileName + sign + ".env");
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
        return prop;
    }

    public static void main(String args[]) {
        Environment.set();
        Pro i = new Pro();
        System.out.println(i.getEnvPropties("test.mysqUrl", "test"));
        System.out.println(i.getEnvPropties("url.www", "url"));

    }

    public String readFile(String path) {
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String lineTxt;
            while ((lineTxt = br.readLine()) != null) {
                br.close();
                return lineTxt;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void writeFile(String path, String value) {
        try {
            File file = new File(path);
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            br.write(value);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getEnvPropties(String fileName) {
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
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
        return prop;
    }

    public void setEnvPropties(String fileName, String key, String value) {
        Properties prop = new Properties();
        FileOutputStream fis = null;
        try {
            fis = new FileOutputStream(fileName, true);
            prop.setProperty(key, value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.store(fis, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setEnvPropties(String fileName, Map<String, Integer> map) {
        Properties prop = new Properties();
        FileOutputStream fis = null;
        try {
            fis = new FileOutputStream(fileName);
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                prop.setProperty((String) key, String.valueOf((map.get(key))));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.store(fis, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
