package cinema.webservice.polytech.fr.cinemawebservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 05-Nov-17.
 */
public class Film {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("budget")
    @Expose
    private long budget;

    @SerializedName("duration")
    @Expose
    private long duration;

    @SerializedName("grossing")
    @Expose
    private long grossing;

    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("director")
    @Expose
    private Director director;

    @SerializedName("category")
    @Expose
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getGrossing() {
        return grossing;
    }

    public void setGrossing(long grossing) {
        this.grossing = grossing;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
