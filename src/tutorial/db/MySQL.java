package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by Oliver on 14/07/2017.
 */
public class MySQL {
    /**
     * 使用 JDBC 连接 MySQL 数据库。
     * Java 连接 MySQL 需要驱动包，最新版下载地址为：http://dev.mysql.com/downloads/connector/j/，解压后得到jar库文件，然后在对应的项目中导入该库文件。
     */
    private Properties properties = System.getProperties();
    private Connection connection;

    public static void main(String[] args) {
        MySQL mySQL = new MySQL();
        System.out.println("SQL return: " + mySQL.query("SELECT * FROM products_jd ORDER BY ID DESC LIMIT 5"));
    }

    public void getConnect() {
        try {
            InputStream inputStream = MySQL.class.getResourceAsStream("mysql.properties");
            properties.load(inputStream);

            String driver = properties.getProperty("mysql.driver");
            String url = properties.getProperty("mysql.url");
            String user = properties.getProperty("mysql.user");
            String password = properties.getProperty("mysql.password");

            // 注册 JDBC 驱动
            Class.forName(driver);

            // 打开链接
            System.out.println("连接数据库...");
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public List<HashMap<String, String>> query(String s) {
        getConnect();
        HashMap<String, String> result = new HashMap<>();
        List<HashMap<String, String>> results = new ArrayList<>();
        try {
            // 执行查询
            System.out.println(" 实例化Statement对...");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(s);

            // 分析返回结果
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSetMetaData.getColumnLabel(i) + ":" + resultSet.getString(i) + ", ");
                    result.put(resultSetMetaData.getColumnLabel(i), resultSet.getString(i));
                }
                System.out.println();
                results.add(result);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
