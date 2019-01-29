/* This class creates a database using SQlite. The database hold the points and scores of the player
 * 
 * Name: Ahmed Mansour
 * Date: 12/10/18
 */

package JavaFXProject;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC {
	
   public void createTable() {
      Connection conn = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:gamereport.db");
         System.out.println("Opened database successfully!!");

         stmt = conn.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS SCORES  (\n"
               +  " name       text    NOT NULL,\n" 
        	   +  " score      integer NOT NULL    \n"
        	   +  ");";  
         stmt.executeUpdate(sql);
         stmt.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully!!");
   }
   
   public void insertPerson(String name, int score) { 
	   Connection conn = null;
	   
           try {
        	 conn = DriverManager.getConnection("jdbc:sqlite:gamereport.db");
        	 String sqlInsert = "INSERT INTO SCORES(name, score) VALUES(?,?)";
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
			 pstmt.setString(1, name);
			 pstmt.setInt(2, score);
			 pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

   }
   
   public ArrayList<String> findAllPeople() {
	   String sqlGetInfo = "SELECT name, score FROM SCORES";
	   ArrayList<String> personArray = new ArrayList<String>();
	   Connection conn = null;
	   
	   try {
		conn = DriverManager.getConnection("jdbc:sqlite:gamereport.db");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlGetInfo);
		 while ( rs.next() ) {
			 personArray.add(rs.getString("name"));
			 personArray.add(Integer.toString(rs.getInt("score")));
		 }
		 rs.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   return personArray;
  }
}