package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Model.Film;
import db.FilmDAO;

/**
 * Servlet implementation class HomeServletPage
 */
@WebServlet("/getFilmBytitle")
public class getFilmBytitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String format;
    
    public getFilmBytitle() {
        
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		format  = request.getParameter("data-format");
		String title = request.getParameter("title");
		
		System.out.println("Data is outputted in " + format + " format.");
		// Connect to the Database
		
		Film films = new Film();
		FilmDAO dao = new FilmDAO();
		
		// String title = new String();
		films = dao.getFilmBytitle(title);
		
		// Convert ArrayList to json
		Gson gson = new Gson();
		String data ="", address=""; 
		if(format.equals("json")) {
			data = gson.toJson(films);
			response.setContentType("application/json");
			request.setAttribute("json", data);
			address="json";
			
		} else if (format.equals("xml")) {
			response.setContentType("text/xml");
			request.setAttribute("film", films);
			address ="xml";
		}
		
		request.setAttribute("json", data);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/"+address+".jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}