package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda?useTimezone=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection createConnectionToMySql() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = createConnectionToMySql();

        if(con != null){
            System.out.println("Conexão obtida com sucesso");
            con.close();
        }
    }

}
