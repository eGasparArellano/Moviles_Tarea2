package beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Desarrollo on 23/09/2017.
 */

public class ItemProduct implements Parcelable {
    private int code;
    private int image;
    private String title;
    private String description;
    private Category category;
    private Store store;

    public ItemProduct(){}

    // En el mismo orden que lees debes escribir
    public ItemProduct(Parcel in) {
        code = in.readInt();
        image = in.readInt();
        title = in.readString();
        description = in.readString();
        category = in.readParcelable(Category.class.getClassLoader());
        store = in.readParcelable(Store.class.getClassLoader());
    }

    // Escribes en el orden en el que leíste
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeInt(image);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeParcelable(category, flags);
        dest.writeParcelable(store, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemProduct> CREATOR = new Creator<ItemProduct>() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "ItemProduct{image=" + image + ", title='" + title + "', store='" + store.getName()
                + "', location='" + store.getCity().getName() + "', phone='" + this.store.getPhone() + "', description='" + description + "'}";
    }
}
