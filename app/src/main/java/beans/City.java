package beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Desarrollo on 15/10/2017.
 */

public class City implements Parcelable {
    private int idCity;
    private String name;

    public City(){}

    public City(int idCity, String name) {
        this.idCity = idCity;
        this.name = name;
    }

    // En el mismo orden que lees debes escribir
    public City(Parcel in) {
        idCity = in.readInt();
        name = in.readString();
    }

    // Escribes en el orden en el que leíste
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idCity);
        parcel.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
