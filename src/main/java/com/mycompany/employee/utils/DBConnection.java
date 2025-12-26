/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static BasicDataSource dataSource;

    static {
        try {
            // 1. Đọc cấu hình từ file properties
            Properties props = new Properties();
            try (InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
                if (is == null) {
                    throw new RuntimeException("Không tìm thấy file db.properties trong classpath");
                }
                props.load(is);
            }

            // 2. Khởi tạo DBCP DataSource
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(props.getProperty("db.driver"));
            dataSource.setUrl(props.getProperty("db.url"));
            dataSource.setUsername(props.getProperty("db.username"));
            dataSource.setPassword(props.getProperty("db.password"));

            // Cấu hình pool cơ bản
            dataSource.setInitialSize(Integer.parseInt(props.getProperty("db.initialSize", "5")));
            dataSource.setMaxTotal(Integer.parseInt(props.getProperty("db.maxTotal", "20")));
            
            LOGGER.info("DataSource khởi tạo thành công với DBCP2.");

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi nghiêm trọng khi khởi tạo DataSource: " + e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Lấy kết nối từ pool với cơ chế thử lại (Retry) 3 lần, mỗi lần cách nhau 2 giây.
     */
    public static Connection getConnection() throws SQLException {
        int maxRetries = 3;
        int delayMs = 2000;
        SQLException lastException = null;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                Connection conn = dataSource.getConnection();
                LOGGER.log(Level.INFO, "Lấy kết nối thành công tại lần thử thứ {0}", attempt);
                return conn;
            } catch (SQLException e) {
                lastException = e;
                LOGGER.log(Level.WARNING, "Lần thử {0}/{1} thất bại. Lỗi: {2}", 
                        new Object[]{attempt, maxRetries, e.getMessage()});
                
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(delayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        
        LOGGER.log(Level.SEVERE, "Không thể kết nối DB sau {0} lần thử.", maxRetries);
        throw new SQLException("Kết nối thất bại sau " + maxRetries + " lần thử. Chi tiết: " + lastException.getMessage(), lastException);
    }

    // --- CÁC PHƯƠNG THỨC ĐÓNG TÀI NGUYÊN AN TOÀN ---

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                LOGGER.info("Đã trả kết nối về pool thành công.");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Lỗi khi đóng Connection: " + e.getMessage(), e);
            }
        }
    }

    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                LOGGER.info("Đã đóng Statement.");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Lỗi khi đóng Statement: " + e.getMessage(), e);
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                LOGGER.info("Đã đóng ResultSet.");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Lỗi khi đóng ResultSet: " + e.getMessage(), e);
            }
        }
    }
}