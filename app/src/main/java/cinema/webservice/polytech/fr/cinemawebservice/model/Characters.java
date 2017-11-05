package cinema.webservice.polytech.fr.cinemawebservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 05-Nov-17.
 */
public class Characters {
    @SerializedName("idFilm")
    @Expose
    private long idFilm;

    @SerializedName("idActor")
    @Expose
    private long idActor;

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("film")
    @Expose
    private Film film;

    @Expose
    private Actor actor;

    public long getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(long idFilm) {
        this.idFilm = idFilm;
    }

    public long getIdActor() {
        return idActor;
    }

    public void setIdActor(long idActor) {
        this.idActor = idActor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
