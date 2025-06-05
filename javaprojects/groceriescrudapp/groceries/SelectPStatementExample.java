package nl.cubicated.groceries;

import java.sql.*;

public class SelectPStatementExample {
    private  static final String QUERY="select ItemID, Name, Country, Price from groceryitems where ItemID=?";

    public static void main(String[] args) {
        // Step 1: Establishing a Connection
        try(Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false",
                "root","NoneOfYourBusiness");

            // Step 2: Create a statement using connection object
            PreparedStatement preparedStatement=connection.prepareStatement(QUERY)){
            // set parameters
            preparedStatement.setInt(1,2);
            System.out.println(preparedStatement);

            // Step 3: Execute the query or update query
            ResultSet rs=preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object
            while(rs.next()){
                int id=rs.getInt("ItemID");
                String name=rs.getString("Name");
                String country=rs.getString("Country");
                String price=rs.getString("price");
                System.out.println(id+", "+name+", "+country+", "+price);
            }
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
