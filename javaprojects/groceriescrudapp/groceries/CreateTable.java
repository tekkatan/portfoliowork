package nl.cubicated.groceries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {

    private static final String createTableCities="CREATE TABLE GROCERYITEMS(\r\n"+
            "ItemID int(3) PRIMARY KEY AUTO_INCREMENT,\r\n"+
            "Name varchar(30) NOT NULL, \r\n"+
            "Country varchar(100) NOT NULL, \r\n"+
            "Price numeric(3,2) NOT NULL \r\n);";

    public static void main(String[] args) throws SQLException{
        CreateTable createTable=new CreateTable();
        createTable.createTableSQL();
    }

    public void createTableSQL()throws SQLException{
        // Establish connection
        try(Connection connection= DriverManager.
            getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false",
                "root","NoneOfYourBusiness");
            // Statement creation using connection object
            PreparedStatement preparedStatement=connection.prepareStatement(createTableCities)
            ){
            // Execute the query
            boolean result=preparedStatement.execute();
            System.out.println(result);
        }catch(SQLException e){
            CustomSQLException.printSQLException(e);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
