package Model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(namespace = "Model")
public class filmStore {

    // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "listFilms")
    // XmlElement sets the name of the entities
    @XmlElement(name = "Film")
    
    private ArrayList<Film> films;
    Film film = new Film();
    
    public void setfilmList(ArrayList<Film> films) {
       this.films = films;
    }

    public ArrayList<Film> getFilms() {
        return films;
    }
}
    