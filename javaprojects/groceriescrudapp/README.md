# Welcome

This Github repo contains a selection of my work
including the following softwaredevelopment 
languages:

Note:
- If you are using any of my code, please add a link to me/my website/ or **this** github repo. Thumbs up for the supporting devs 


# Groceriescrud app

### Info
- The Groceriescrud app is a CRUD App made with Java OpenJDK 17 
  and uses SQL PreparedStatements with the MySQL Database.
- Of course the password is fake in this case due to safety measures

### Usage - Create the table
- Below you can find the table creation code with a preparedStatement.
```java
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
```
### Usage - Batch Insert of Data
- Let's say that we have already filled the database with the following data:
![ Groceriescrud app](javaprojects\groceriescrudapp\mysqldb1.png "Example Groceries in database - Groceriescrud app")
- The User can insert a batch of data using the following code, which is a part of the code found 
  in BatchInsertPP.java:
```java
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
```
- Of course it still possible to insert one row at a time using the java file: InsertPStatementExample.java


### Usage - Deleting a row
- The User can delete groceries with the following code:
```java

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

```
### Usage - Selecting a row and view or read it's content
- The User can get a resultset of content from a given row 

```java
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
```
### Usage - Updating a row 
- The User can update a value, in this case change the name of the product Egg to Eggs
```java
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
```

