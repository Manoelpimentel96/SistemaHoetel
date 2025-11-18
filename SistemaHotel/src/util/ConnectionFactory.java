/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author manoelpimentel
 */
public class ConnectionFactory {
/*
    private static final HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig("config/db.properties");
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setConnectionTimeout(30000);
            config.setIdleTimeout(600000);
            config.setMaxLifetime(1800000);
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao configurar pool de conexões: " + e.getMessage(), e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

  */private static final String URL = "jdbc:mysql://localhost:3306/sistemahotel"; // troque pelo nome do seu banco
    private static final String USER = "root"; // troque se necessário
    private static final String PASSWORD = "@2005Ma96"; // troque se necessário

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco: " + e.getMessage(), e);
        }
    }
}
