package beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Desarrollo on 26/09/2017.
 */

public class Store implements Parcelable{
    private int id;
    private String name;
    private String phone;
    private City city;
    private int thumbnail;
    private double latitude;
    private double longitude;

    public Store(){}

    public Store(int id, String name, String phone, City city, int thumbnail, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.thumbnail = thumbnail;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // En el mismo orden que lees debes escribir
    protected Store(Parcel in) {
        id = in.readInt();
        name = in.readString();
        phone = in.readString();
        city = in.readParcelable(City.class.getClassLoader());
        thumbnail = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    // Escribes en el orden en el que leíste
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeParcelable(city, i);
        parcel.writeInt(thumbnail);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString(){
        return name + " - " + getCity().getName() + ", Jalisco";
    }
}
