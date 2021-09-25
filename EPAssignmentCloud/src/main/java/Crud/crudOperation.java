package Crud;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import Model.Film;
import db.FilmDAO;

@Path("/allFilmList")
public class crudOperation {
	FilmDAO dao = new FilmDAO();
	Film f = new Film();
	
	@DELETE
	@Path("/{ID}")
	public void delete(@PathParam("ID") int ID) {
		dao.delete(ID);
	}
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Film addFilm(Film films) {
		dao.addFilm(films.getId(),films.getTitle(),films.getYear(),films.getDirector(),films.getStars(),films.getReview());
		f = dao.getFilmByID(films.getId());
		return films;
	}
	@PUT
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Film updateFilm(Film films) {
		dao.addFilm(films.getId(),films.getTitle(),films.getYear(),films.getDirector(),films.getStars(),films.getReview());
		return films;	
	}
}
