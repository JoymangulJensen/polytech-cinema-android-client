package cinema.webservice.polytech.fr.cinemawebservice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 05-Nov-17.
 */
public class Characters implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.idFilm);
        dest.writeLong(this.idActor);
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.film, flags);
        dest.writeParcelable(this.actor, flags);
    }

    public static final Creator<Characters> CREATOR = new Creator<Characters>() {
        @Override
        public Characters createFromParcel(Parcel source) {
            return new Characters(source);
        }

        @Override
        public Characters[] newArray(int size) {
            return new Characters[size];
        }
    };

    protected Characters(Parcel in) {
        this.idFilm = in.readLong();
        this.idActor = in.readLong();
        this.id = in.readLong();
        this.name = in.readString();
        this.film = in.readParcelable(Film.class.getClassLoader());
        this.actor = in.readParcelable(Actor.class.getClassLoader());
    }

}
