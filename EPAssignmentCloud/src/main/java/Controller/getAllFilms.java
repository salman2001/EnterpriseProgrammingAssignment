package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

import Model.Film;
import Model.filmStore;

import db.FilmDAO;


/**
 * Servlet implementation class getAllFilms
 */
@WebServlet("/getAllFilms")
public class getAllFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;
String format;
FilmDAO dao = new FilmDAO();
Film film = new Film();
    /**
     * Default constructor. 
     */
    public getAllFilms() {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		format  = request.getParameter("data-format");
		String action = "";
		System.out.println("Data is outputted in " + format + " format.");
		// Connect to the Database
		
		FilmDAO dao = new FilmDAO();
		ArrayList<Film> films = dao.getAllFilms();
		
		// Convert ArrayList to json
		Gson gson = new Gson();
		String data ="", address=""; 
		if(format.equals("json")) {
			data = gson.toJson(films);
			response.setContentType("application/json");
			request.setAttribute("json", data);
			address="json";		
		} 
		if (format.equals("xml")) {
				try {
					PrintWriter writer = response.getWriter();
					filmStore filmStore = new filmStore();
					filmStore.setfilmList(films);
					JAXBContext context = JAXBContext.newInstance(filmStore.class);
			        Marshaller m = context.createMarshaller();
			        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			        
			        m.marshal(filmStore, writer);
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		request.setAttribute("json", data);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/json.jsp");
		dispatcher.forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request, response);
	}
}
	