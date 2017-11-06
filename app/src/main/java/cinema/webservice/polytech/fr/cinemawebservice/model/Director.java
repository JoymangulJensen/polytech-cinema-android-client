package cinema.webservice.polytech.fr.cinemawebservice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by JOYMANGUL Jensen Selwyn
 * on 05-Nov-17.
 */
public class Director implements Parcelable {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("firstName")
    @Expose
    private String firstName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.firstName);
    }

    protected Director(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.firstName = in.readString();
    }

    public static final Parcelable.Creator<Director> CREATOR = new Parcelable.Creator<Director>() {
        @Override
        public Director createFromParcel(Parcel source) {
            return new Director(source);
        }

        @Override
        public Director[] newArray(int size) {
            return new Director[size];
        }
    };
}
