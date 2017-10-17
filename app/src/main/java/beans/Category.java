package beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Desarrollo on 15/10/2017.
 */

public class Category implements Parcelable{
    private int idCategory;
    private String name;

    public Category() {}

    public Category(int idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    // En el mismo orden que lees debes escribir
    public Category(Parcel in) {
        idCategory = in.readInt();
        name = in.readString();
    }

    // Escribes en el orden en el que leíste
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idCategory);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
