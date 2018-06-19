

// my-sql-connector-xxx.jar in den Projektpfad einbinden:
// BlueJ: Einstellungen(preferences) – Reiter Bibliotheken(libraries) – “add file” 
// – Speicherort der Datei my-sql-connector-xxx.jar (in der Regel im Projektverzeichnis) angeben
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TestClass {

    public static void main(String[] args){
        System.out.println("Das ist ein Test für die Datenbank-Anbindung");

        Connection connection = null;		 
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;
        int numberOfColumns = 0;

        try	{
            String driver = "com.mysql.jdbc.Driver";			
            Class.forName(driver);		 

            String url = "jdbc:mysql://192.168.3.3:3306/team11";
            String username = "team11";
            String password = "123";

            connection = DriverManager.getConnection(url ,username,password);
            statement = connection.createStatement();

            System.out.println("Connection Established");

            //resultSet = statement.executeQuery("SELECT * FROM q11TableTest");
            statement.executeUpdate("CREATE TABLE Highscore Nickname VARCHAR(100), Highscore INTEGER");
            metaData = resultSet.getMetaData();
            numberOfColumns = metaData.getColumnCount();

            for(int i=1 ; i<=numberOfColumns ; i++){
                System.out.printf("%-8s\t",metaData.getColumnName(i));	//formatiert wg. Zellbreite
            }		 
            System.out.println();

            while(resultSet.next()){
                for(int i=1 ; i<=numberOfColumns ; i++){
                    System.out.printf("%-8s\t",resultSet.getObject(i));
                }		 
                System.out.println();
            }

            while(resultSet.next()){
                for(int i=1 ; i<=numberOfColumns ; i++)	{
                    System.out.printf("%-8s\t",resultSet.getObject(i));
                }
                System.out.println();
            }		 
        } catch(SQLException sqlException){
            System.out.println("sqlException");
            sqlException.printStackTrace();		 
            System.exit(1);		 
        } catch(ClassNotFoundException classNotFound) {
            System.out.println("ClassNotFoundException");
            classNotFound.printStackTrace();
            System.exit(1);		 
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(Exception exception){
                System.out.println("FinallyException");
                exception.printStackTrace();
                System.exit(1);		 

            }
        }
    }
}