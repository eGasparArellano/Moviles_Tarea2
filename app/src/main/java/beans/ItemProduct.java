package beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Desarrollo on 23/09/2017.
 */

public class ItemProduct implements Parcelable {
    private int image, code;
    private String title, store, location, phone, description;

    public ItemProduct(){
        image = 0;
        title = "";
        store = "";
        location = "";
        phone = "";
        description = "";
    }

    // En el mismo orden que lees debes escribir
    public ItemProduct(Parcel in){
        code = in.readInt();
        image = in.readInt();
        title = in.readString();
        store = in.readString();
        location = in.readString();
        phone = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Escribes en el orden en el que leíste
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeInt(image);
        parcel.writeString(title);
        parcel.writeString(store);
        parcel.writeString(location);
        parcel.writeString(phone);
        parcel.writeString(description);
    }

    public static final Parcelable.Creator<ItemProduct> CREATOR = new Parcelable.Creator<ItemProduct>() {
        @Override
        public ItemProduct createFromParcel(Parcel in) {
            return new ItemProduct(in);
        }

        @Override
        public ItemProduct[] newArray(int size) {
            return new ItemProduct[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ItemProduct{image=" + image + ", title='" + title + "', store='" + store
                + "', location='" + location + "', phone='" + phone + "', description='" + description + "'}";
    }
}
