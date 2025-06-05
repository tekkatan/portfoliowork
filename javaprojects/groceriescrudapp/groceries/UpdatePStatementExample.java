package nl.cubicated.groceries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePStatementExample {

    private static final String UPDATE_USERS_SQL="update groceryitems set name=? where ItemID=?;";

    public static void main(String[] args) throws SQLException {
        UpdatePStatementExample updateStatementExample=new UpdatePStatementExample();
        updateStatementExample.updateRecord();
    }

    public void updateRecord() throws SQLException{
        System.out.println(UPDATE_USERS_SQL);
        // Step 1: Establishing a connection
        try(Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false",
                "root","NoneOfYourBusiness");
            // Step 2 Create a statement using connection object
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USERS_SQL)
        ){
            preparedStatement.setString(1,"Eggs");
            preparedStatement.setInt(2,2);
            System.out.println(preparedStatement);

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }
        // Step 4: try-with-resources statement will auto close the connection
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
