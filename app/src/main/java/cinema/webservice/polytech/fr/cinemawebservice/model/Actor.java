package cinema.webservice.polytech.fr.cinemawebservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 05-Nov-17.
 */
public class Actor {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("deathDate")
    @Expose
    private Object deathDate;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("name")
    @Expose
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Object getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Object deathDate) {
        this.deathDate = deathDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
