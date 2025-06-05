package nl.cubicated.groceries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPStatementExample {

    private static final String INSERT_GROCERY_SQL="INSERT INTO groceryitems"
            +"(Name, Country, Price)"
            +"VALUES(?,?,?);";

    public static void main(String[] args) {
        InsertPStatementExample createTableExample=new InsertPStatementExample();
        createTableExample.insertRecord();
    }

    public void insertRecord(){
        System.out.println(INSERT_GROCERY_SQL);
        // Step 1: Establishing a Connection
        try(Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false",
                "root","NoneOfYourBusiness");
            // Step 2: Create a statement using connection object
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_GROCERY_SQL)
        ){
            // setting placeholder values
            //preparedStatement.setInt(1,1);
            preparedStatement.setString(1,"Rabarber");
            preparedStatement.setString(2,"Deutschland");
            preparedStatement.setString(3,"0.21");

            System.out.println(preparedStatement);

            // Step 3: Execute the query or update query
            int result=preparedStatement.executeUpdate();
            System.out.println(result);
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex){
        for(Throwable e:ex){
            if(e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState: "+((SQLException)e).getSQLState());
                System.err.println("Error code: "+((SQLException)e).getErrorCode());
                Throwable t=ex.getCause();
                while(t!=null){
                    System.out.println("Cause: "+t);
                    t=t.getCause();
                }
            }
        }
    }
}
