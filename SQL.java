

// my-sql-connector-xxx.jar in den Projektpfad einbinden:
// BlueJ: Einstellungen(preferences) – Reiter Bibliotheken(libraries) – “add file” 
// – Speicherort der Datei my-sql-connector-xxx.jar (in der Regel im Projektverzeichnis) angeben
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQL {
    
    private Statement statement = null;
    private Connection connection = null;   
    private ResultSet resultSet = null;
    private ResultSetMetaData metaData = null;
    private int numberOfColumns = 0;
    public SQL()
    {
        try{
            
            String driver = "com.mysql.jdbc.Driver";            
                Class.forName(driver);       
    
                String url = "jdbc:mysql://192.168.3.3:3306/team23";
                String username = "team23";
                String password = "4e6T8u";
    
                connection = DriverManager.getConnection(url ,username,password);
                statement = connection.createStatement();
    
                System.out.println("Connection Established");
                    
        
        } catch(SQLException sqlException){
            System.out.println("sqlException");
            sqlException.printStackTrace();      
            System.exit(1);      
        } catch(ClassNotFoundException classNotFound) {
            System.out.println("ClassNotFoundException");
            classNotFound.printStackTrace();
            System.exit(1);      
        }
        }
     
     
   
    
    public void neu(String name, int Score)
    {
          try   {
              String driver = "com.mysql.jdbc.Driver";          
            Class.forName(driver);       


            System.out.println("Connection Established");

            
            statement.executeUpdate("INSERT INTO Highscore VALUES ('"+name+"',"+Integer.toString(Score)+")"); 
        } catch(SQLException sqlException){
            System.out.println("sqlException");
            sqlException.printStackTrace();      
            System.exit(1);      
        } catch(ClassNotFoundException classNotFound) {
            System.out.println("ClassNotFoundException");
            classNotFound.printStackTrace();
            System.exit(1);      

     }
    }
    
    public void update()
    {
       try{
       resultSet = statement.executeQuery("SELECT * FROM highscore");
       metaData = resultSet.getMetaData();
       numberOfColumns = metaData.getColumnCount();

       for(int i=1 ; i<=numberOfColumns ; i++){
           System.out.printf("%-8s\t",metaData.getColumnName(i));  //formatiert wg. Zellbreite
       }        
       System.out.println();

       while(resultSet.next()){
           for(int i=1 ; i<=numberOfColumns ; i++){
                System.out.printf("%-8s\t",resultSet.getObject(i));
           }        
           System.out.println();
       }

       //while(resultSet.next()){
       //    for(int i=1 ; i<=numberOfColumns ; i++) {
       //        System.out.printf("%-8s\t",resultSet.getObject(i));
       //    }
           System.out.println();
       //}   
       } catch(SQLException sqlException){
            System.out.println("sqlException");
            sqlException.printStackTrace();      
            System.exit(1);               
     }             
    }
}