package database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import beans.Category;
import beans.City;
import beans.ItemProduct;
import beans.Store;

/**
 * Created by Desarrollo on 16/10/2017.
 */

public class ItemProductControl {
    public long addItemProduct(ItemProduct product, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        long inserted = 0;

        values.put(DataBaseHandler.KEY_PRODUCT_TITLE, product.getTitle());
        values.put(DataBaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
        values.put(DataBaseHandler.KEY_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(DataBaseHandler.KEY_PRODUCT_CATEGORY, product.getCategory().getIdCategory());
        values.put(DataBaseHandler.KEY_PRODUCT_STORE, product.getStore().getId());

        // Inserting row
        inserted = db.insert(DataBaseHandler.TABLE_PRODUCT, null, values); // En dónde, si hay partes vacías qué pongo, valores a insertar

        // Closing database connection
        try {db.close();} catch (Exception e) {}
        db = null;
        values = null;

        return inserted;
    }

    public int updateProduct(ItemProduct product, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataBaseHandler.KEY_PRODUCT_TITLE, product.getTitle());
        values.put(DataBaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
        values.put(DataBaseHandler.KEY_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(DataBaseHandler.KEY_PRODUCT_CATEGORY, product.getCategory().getIdCategory());
        values.put(DataBaseHandler.KEY_PRODUCT_STORE, product.getStore().getId());

        // Updating row
        // En dónde, los valores a actualizar, condición, reemplazo de los '?'
        int count = db.update(DataBaseHandler.TABLE_PRODUCT, values, DataBaseHandler.KEY_PRODUCT_ID + " = ?", new String[]{String.valueOf(product.getCode())});

        try { db.close();} catch (Exception e) {}
        db = null;

        return count;
    }

    public void deleteProduct(int idProduct, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();

        // En dónde borras, qué condición, los '?' se reemplazan con tod0 lo del tercer parametro, en este caso el valor convertido a String del ID
        db.delete(DataBaseHandler.TABLE_PRODUCT, DataBaseHandler.KEY_PRODUCT_ID + " = ?", new String[]{String.valueOf(idProduct)});
    }

    public ItemProduct getProductById(int idProduct, DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ItemProduct product = new ItemProduct();

        String selectQuery = "SELECT P." + DataBaseHandler.KEY_PRODUCT_ID + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_TITLE + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_IMAGE + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_DESCRIPTION + ","

                + "CA." + DataBaseHandler.KEY_CATEGORY_ID + ","
                + "CA." + DataBaseHandler.KEY_CATEGORY_NAME + ","

                + "CI." + DataBaseHandler.KEY_CITY_ID + ","
                + "CI." + DataBaseHandler.KEY_CITY_NAME + ","

                + "S." + DataBaseHandler.KEY_STORE_ID + ","
                + "S." + DataBaseHandler.KEY_STORE_NAME + ","
                + "S." + DataBaseHandler.KEY_STORE_PHONE + ","
                + "S." + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                + "S." + DataBaseHandler.KEY_STORE_LAT + ","
                + "S." + DataBaseHandler.KEY_STORE_LNG + " FROM "

                + DataBaseHandler.TABLE_PRODUCT + " P, "
                + DataBaseHandler.TABLE_CATEGORY + " CA, "
                + DataBaseHandler.TABLE_CITY + " CI, "
                + DataBaseHandler.TABLE_STORE + " S WHERE P."

                + DataBaseHandler.KEY_PRODUCT_ID + " = " + idProduct
                + " AND P." + DataBaseHandler.KEY_PRODUCT_CATEGORY + " = CA." + DataBaseHandler.KEY_CATEGORY_ID
                + " AND P." + DataBaseHandler.KEY_PRODUCT_STORE + " = S." + DataBaseHandler.KEY_STORE_ID
                + " AND S." + DataBaseHandler.KEY_STORE_CITY + " = CI." + DataBaseHandler.KEY_CITY_ID;

        Cursor cursor = db.rawQuery(selectQuery, null); // El segundo parametro es para los '?' que llegues a tener en un WHERE en la query, lleva un array de strings
        if(cursor.moveToFirst()){
            product.setCode(cursor.getInt(0));
            product.setTitle(cursor.getString(1));
            product.setImage(cursor.getInt(2));
            product.setDescription(cursor.getString(3));

            Category category = new Category();
            category.setIdCategory(cursor.getInt(4));
            category.setName(cursor.getString(5));

            City city = new City();
            city.setIdCity(cursor.getInt(6));
            city.setName(cursor.getString(7));

            Store store = new Store();
            store.setId(cursor.getInt(8));
            store.setName(cursor.getString(9));
            store.setPhone(cursor.getString(10));
            store.setThumbnail(cursor.getInt(11));
            store.setLatitude(cursor.getDouble(12));
            store.setLongitude(cursor.getDouble(13));

            store.setCity(city);
            product.setCategory(category);
            product.setStore(store);
        }

        try {cursor.close();db.close();
        } catch (Exception e) {}

        db = null;
        cursor = null;

        return product;
    }

    public ArrayList<ItemProduct> getProductsWhere(String strWhere, String strOrderBy, DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<ItemProduct> products = new ArrayList<>();

        String query = "SELECT P." + DataBaseHandler.KEY_PRODUCT_ID + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_TITLE + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_IMAGE + ","
                + "P." + DataBaseHandler.KEY_PRODUCT_DESCRIPTION + ","

                + "CA." + DataBaseHandler.KEY_CATEGORY_ID + ","
                + "CA." + DataBaseHandler.KEY_CATEGORY_NAME + ","

                + "CI." + DataBaseHandler.KEY_CITY_ID + ","
                + "CI." + DataBaseHandler.KEY_CITY_NAME + ","

                + "S." + DataBaseHandler.KEY_STORE_ID + ","
                + "S." + DataBaseHandler.KEY_STORE_NAME + ","
                + "S." + DataBaseHandler.KEY_STORE_PHONE + ","
                + "S." + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                + "S." + DataBaseHandler.KEY_STORE_LAT + ","
                + "S." + DataBaseHandler.KEY_STORE_LNG + " FROM "

                + DataBaseHandler.TABLE_PRODUCT + " P, "
                + DataBaseHandler.TABLE_CATEGORY + " CA, "
                + DataBaseHandler.TABLE_CITY + " CI, "
                + DataBaseHandler.TABLE_STORE + " S WHERE "

                + strWhere
                + " AND P." + DataBaseHandler.KEY_PRODUCT_CATEGORY + " = CA." + DataBaseHandler.KEY_CATEGORY_ID
                + " AND P." + DataBaseHandler.KEY_PRODUCT_STORE + " = S." + DataBaseHandler.KEY_STORE_ID
                + " AND S." + DataBaseHandler.KEY_STORE_CITY + " = CI." + DataBaseHandler.KEY_CITY_ID
                + " ORDER BY " + strOrderBy;

        Cursor cursor = db.rawQuery(query, null); // El segundo parametro es para los '?' que llegues a tener en un WHERE en la query, lleva un array de strings

        while(cursor.moveToNext()){
            // Agrego todos los datos que obtuve
            ItemProduct product = new ItemProduct();
            product.setCode(cursor.getInt(0));
            product.setTitle(cursor.getString(1));
            product.setImage(cursor.getInt(2));
            product.setDescription(cursor.getString(3));

            Category category = new Category();
            category.setIdCategory(cursor.getInt(4));
            category.setName(cursor.getString(5));

            City city = new City();
            city.setIdCity(cursor.getInt(6));
            city.setName(cursor.getString(7));

            Store store = new Store();
            store.setId(cursor.getInt(8));
            store.setName(cursor.getString(9));
            store.setPhone(cursor.getString(10));
            store.setThumbnail(cursor.getInt(11));
            store.setLatitude(cursor.getDouble(12));
            store.setLongitude(cursor.getDouble(13));

            store.setCity(city);
            product.setCategory(category);
            product.setStore(store);

            products.add(product);
        }

        try {cursor.close();db.close();
        } catch (Exception e) {}

        db = null;
        cursor = null;

        return products;
    }
}
