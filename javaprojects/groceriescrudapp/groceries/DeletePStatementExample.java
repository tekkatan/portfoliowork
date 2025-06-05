package nl.cubicated.groceries;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePStatementExample {

    private static final String DELETE_GROCERY_SQL="delete from groceryitems where ItemID=?;";

    public static void main(String[] args) throws SQLException {
        DeletePStatementExample deleteStatementExample=new DeletePStatementExample();
        deleteStatementExample.deleteRecord();
    }

    public void deleteRecord() throws  SQLException{
        System.out.println(DELETE_GROCERY_SQL);

        //Step 1: Establishing a Connection
        try(Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false",
                "root","NoneOfYourBusiness");
            // Step 2 Create a statement using connection object
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE_GROCERY_SQL)
        ){
            preparedStatement.setInt(1,1);

            //Step 3: Execute the query or update query
            int result=preparedStatement.executeUpdate();
            System.out.println("Number of record affected: "+result);
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
