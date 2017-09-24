package beans;

/**
 * Created by Desarrollo on 23/09/2017.
 */

public class ItemProduct {
    private int image;
    private String title, store, location, phone, description;

    public ItemProduct(){
        image = 0;
        title = "";
        store = "";
        location = "";
        phone = "";
        description = "";
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
