package cinema.webservice.polytech.fr.cinemawebservice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 05-Nov-17.
 */
public class Film implements Parcelable {
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
    private Date releaseDate;

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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.budget);
        dest.writeLong(this.duration);
        dest.writeLong(this.grossing);
        dest.writeLong(this.releaseDate != null ? this.releaseDate.getTime() : -1);
        dest.writeString(this.title);
        dest.writeParcelable(this.director, flags);
        dest.writeParcelable(this.category, flags);
    }

    public Film() {
    }

    protected Film(Parcel in) {
        this.id = in.readLong();
        this.budget = in.readLong();
        this.duration = in.readLong();
        this.grossing = in.readLong();
        long tmpReleaseDate = in.readLong();
        this.releaseDate = tmpReleaseDate == -1 ? null : new Date(tmpReleaseDate);
        this.title = in.readString();
        this.director = in.readParcelable(Director.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
