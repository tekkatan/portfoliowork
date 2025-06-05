package nl.cubicated.groceries;

import java.sql.*;
import java.util.Arrays;

public class BatchInsertPP {
    public static void main(String[] args) {
        batchInsertItems();
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
    public static void printBatchUpdateException(BatchUpdateException b){
        System.err.println("-----BatchUpdateException-----");
        System.err.println("SQLState: "+b.getSQLState());
        System.err.println("Message: "+b.getMessage());
        System.err.println("Vendor: "+b.getErrorCode());
        System.err.println("Update counts: ");
        int[] updateCounts=b.getUpdateCounts();

        for(int i=0;i<updateCounts.length;i++){
            System.err.print(updateCounts[i]+"  ");
        }
    }

    private static void batchInsertItems(){
        /*
        Method for inserting multiple values in a batch to the given table
        in the given database.

        Note: the primary key ItemID is not filled in as this
        is autoicremented.
         */
        String INSERT_GROCERYITEMS_SQL="INSERT INTO GROCERYITEMS"+
                "(name,country,price)"+
                "VALUES(?,?,?)";
        try(Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false",
                "root","NoneOfYourBusiness");
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_GROCERYITEMS_SQL);
            ){
            connection.setAutoCommit(false);
            preparedStatement.setString(1,"Stroopwafel");
            preparedStatement.setString(2,"The Netherlands");
            preparedStatement.setString(3,"0.43");
            preparedStatement.addBatch();
            preparedStatement.setString(1,"Frites");
            preparedStatement.setString(2,"Belgium");
            preparedStatement.setString(3,"1.16");
            preparedStatement.addBatch();
            preparedStatement.setString(1,"Baguette");
            preparedStatement.setString(2,"France");
            preparedStatement.setString(3,"1.28");
            preparedStatement.addBatch();
            // execute batch
            int[] updateTable=preparedStatement.executeBatch();
            System.out.println(Arrays.toString(updateTable));
            connection.commit();
            connection.setAutoCommit(true);
        }catch (BatchUpdateException batchUpdateException){
            printBatchUpdateException(batchUpdateException);
        }catch(SQLException e){
            printSQLException(e);
        }
    }
}
