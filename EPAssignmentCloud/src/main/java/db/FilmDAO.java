package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Film;


public class FilmDAO {
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
   //Google Cloud database connected here
    String user = "root";
    String password = "maani066";
    String url = "jdbc:mysql://35.246.74.210:3306/Filmdatabase";

	public FilmDAO() {}
	public Connection openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }
		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();	
		} catch(SQLException se) { System.out.println(se); }
		return conn;
	}
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}	
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Sql statement to get all films
		try{
		    ResultSet rs = stmt.executeQuery("select * from films");
	    // Retrieve the results
		    while(rs.next()){
		    	oneFilm = getNextFilm(rs);
		    	allFilms.add(oneFilm);
		   }
		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	   return allFilms;
   }
   public Film getFilmByID(int id){   
		openConnection();
		oneFilm=null;
	    // Select statement to get film by id
		try{
		    ResultSet rs = stmt.executeQuery("select * from films where id="+id);
	    // Retrieve the results
		    while(rs.next()){
		    	oneFilm = getNextFilm(rs);
		    }
		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return oneFilm;
   }
   public Film getFilmBytitle(String title){
		openConnection();
		oneFilm=null;
	    // Select statement to get film by title
		try{
		    ResultSet rs = stmt.executeQuery("select * from films where title="+title);
	    // Retrieve the results
		    while(rs.next()){
		    	oneFilm = getNextFilm(rs);
		    }
		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { 
			System.out.println(se);
			}
	   return oneFilm;
  }
   public void delete(int id){
	   
		openConnection();
		oneFilm=null;
	    // delete statement to delete film from database
		try{
		    Connection connect = openConnection();
		    PreparedStatement del = connect.prepareStatement("Delete FROM films where id = "+"'" +id+"'");
		    del.executeUpdate();
		    
		} catch(Exception se) { 
			
			}
   }
   public void addFilm(int id, String title, int year, String director, String stars, String review){
			   
			openConnection();
			oneFilm=null;
		    // Adding a film to database using crudOperations
			try{  
				Connection connect = openConnection(); 
	            PreparedStatement add=connect.prepareStatement("Insert into films(id,title,year,director, stars, review) values (?,?,?,?,?,?)");
	            add.setInt(1,id);  
	            add.setString(2,title);  
	            add.setInt(3,year);  
	            add.setString(4,director);  
	            add.setString(5,stars); 
	            add.setString(6,review);  
	              
	            add.executeUpdate();  
	            System.out.println("Movie Added Successfully");
	        }catch(Exception se){}  
	             }
   public void updateFilm(int id, String title, int year, String director, String stars, String review){
	   
		openConnection();
		oneFilm=null;
	    //Updating already existing film in database
		try{  
			Connection connect = openConnection(); 
           PreparedStatement update=connect.prepareStatement(  
                        "update films(id,title,year,director, stars, review)");
           update.setInt(1,id);  
           update.setString(2,title);  
           update.setInt(3,year);  
           update.setString(4,director);  
           update.setString(5,stars); 
           update.setString(6,review);  
             
           update.executeUpdate();  

       }catch(Exception se){}  
            }		
		}
   
	
   

