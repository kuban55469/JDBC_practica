package peaksoft.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.01.2023
 */
public class DataBaseConnection {
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/jdbc_practica",
                    "postgres",
                    "postgres"
            );
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
