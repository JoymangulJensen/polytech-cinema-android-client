package cinema.webservice.polytech.fr.cinemawebservice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 05-Nov-17.
 */
public class Actor implements Parcelable {
    public static final Creator<Actor> CREATOR = new Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel source) {
            return new Actor(source);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("birthday")
    @Expose
    private Date birthday;
    @SerializedName("deathDate")
    @Expose
    private Date deathDate;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("name")
    @Expose
    private String name;

    public Actor() {
    }

    protected Actor(Parcel in) {
        this.id = in.readLong();
        long tmpBirthday = in.readLong();
        this.birthday = tmpBirthday == -1 ? null : new Date(tmpBirthday);
        long tmpDeathDate = in.readLong();
        this.deathDate = tmpDeathDate == -1 ? null : new Date(tmpDeathDate);
        this.firstName = in.readString();
        this.name = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayStr() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.getBirthday());
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getDeathDateStr() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.getDeathDate());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.birthday != null ? this.birthday.getTime() : -1);
        dest.writeLong(this.deathDate != null ? this.deathDate.getTime() : -1);
        dest.writeString(this.firstName);
        dest.writeString(this.name);
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getName();
    }
}
