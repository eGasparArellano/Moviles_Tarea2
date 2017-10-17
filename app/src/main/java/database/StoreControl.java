package database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import beans.City;
import beans.Store;

/**
 * Created by Desarrollo on 15/10/2017.
 */

public class StoreControl {

    public long addStore(Store store, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        long inserted = 0;

        values.put(DataBaseHandler.KEY_STORE_CITY, store.getCity().getIdCity());
        values.put(DataBaseHandler.KEY_STORE_LAT, store.getLatitude());
        values.put(DataBaseHandler.KEY_STORE_LNG, store.getLongitude());
        values.put(DataBaseHandler.KEY_STORE_NAME, store.getName());
        values.put(DataBaseHandler.KEY_STORE_PHONE, store.getPhone());
        values.put(DataBaseHandler.KEY_STORE_THUMBNAIL, store.getThumbnail());

        // Inserting Row
        inserted = db.insert(DataBaseHandler.TABLE_STORE, null, values); // En dónde, si hay partes vacías qué pongo, valores a insertar

        // Closing database connection
        try {db.close();} catch (Exception e) {}
        db = null;
        values = null;

        return inserted;
    }

    public void deleteStore(int idStore, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();

        // En dónde borras, qué condición, los '?' se reemplazan con tod0 lo del tercer parametro, en este caso el valor convertido a String del ID
        db.delete(DataBaseHandler.TABLE_STORE, DataBaseHandler.KEY_STORE_ID + " = ?", new String[] { String.valueOf(idStore) });

        try {db.close();} catch (Exception e) {}
        db = null;
    }

    public int updateStore(Store store, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataBaseHandler.KEY_STORE_CITY, store.getCity().getIdCity());
        values.put(DataBaseHandler.KEY_STORE_LAT, store.getLatitude());
        values.put(DataBaseHandler.KEY_STORE_LNG, store.getLongitude());
        values.put(DataBaseHandler.KEY_STORE_NAME, store.getName());
        values.put(DataBaseHandler.KEY_STORE_PHONE, store.getPhone());
        values.put(DataBaseHandler.KEY_STORE_THUMBNAIL, store.getThumbnail());

        // Updating row
        // En dónde, los valores a actualizar, condición, reemplazo de los '?'
        int count = db.update(DataBaseHandler.TABLE_STORE, values, DataBaseHandler.KEY_STORE_ID + " = ?", new String[] { String.valueOf(store.getId()) });

        try { db.close();} catch (Exception e) {}
        db = null;

        return count;
    }

    public Store getStoreById(int idStore, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getReadableDatabase();

        Store store = new Store();
        String selectQuery = "SELECT S." + DataBaseHandler.KEY_STORE_ID + ","
                + "S." + DataBaseHandler.KEY_STORE_LAT + ","
                + "S." + DataBaseHandler.KEY_STORE_LNG + ","
                + "S." + DataBaseHandler.KEY_STORE_NAME + ","
                + "S." + DataBaseHandler.KEY_STORE_PHONE + ","
                + "S." + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                + "C." + DataBaseHandler.KEY_CITY_ID + ","
                + "C." + DataBaseHandler.KEY_CITY_NAME + " FROM "
                + DataBaseHandler.TABLE_STORE + " AS S, "                  // Tal vez le falta el 'AS'
                + DataBaseHandler.TABLE_CITY + " AS C WHERE S."            // Aquí igual
                + DataBaseHandler.KEY_STORE_ID + "= " + idStore
                + " AND S." + DataBaseHandler.KEY_STORE_CITY
                + " = C." + DataBaseHandler.KEY_CITY_ID;

        Cursor cursor = db.rawQuery(selectQuery, null); // El segundo parametro es para los '?' que llegues a tener en un WHERE en la query, lleva un array de strings

        if (cursor.moveToFirst()) {
            store.setId(cursor.getInt(0));
            store.setLatitude(cursor.getDouble(1));
            store.setLongitude(cursor.getDouble(2));
            store.setName(cursor.getString(3));
            store.setPhone(cursor.getString(4));
            store.setThumbnail(cursor.getInt(5));
            City city = new City();
            city.setIdCity(cursor.getInt(6));
            city.setName(cursor.getString(7));
            store.setCity(city);
        }
        try {cursor.close();db.close();
        } catch (Exception e) {}

        db = null;
        cursor = null;

        return store;
    }

    public ArrayList<Store> getStoresWhere(String strWhere, String strOrderBy, DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Store> stores = new ArrayList<>();
        String query;

        if(strWhere == null && strOrderBy != null){
            query = "SELECT CI." + DataBaseHandler.KEY_CITY_ID + ","
                    + "CI." + DataBaseHandler.KEY_CITY_NAME + ","

                    + "S." + DataBaseHandler.KEY_STORE_ID + ","
                    + "S." + DataBaseHandler.KEY_STORE_NAME + ","
                    + "S." + DataBaseHandler.KEY_STORE_PHONE + ","
                    + "S." + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                    + "S." + DataBaseHandler.KEY_STORE_LAT + ","
                    + "S." + DataBaseHandler.KEY_STORE_LNG + " FROM "

                    + DataBaseHandler.TABLE_CITY + " CI, "
                    + DataBaseHandler.TABLE_STORE + " S "

                    + " AND S." + DataBaseHandler.KEY_STORE_CITY + " = CI." + DataBaseHandler.KEY_CITY_ID
                    + " ORDER BY " + strOrderBy;
        } else if(strWhere != null && strOrderBy == null){
            query = "SELECT CI." + DataBaseHandler.KEY_CITY_ID + ","
                    + "CI." + DataBaseHandler.KEY_CITY_NAME + ","

                    + "S." + DataBaseHandler.KEY_STORE_ID + ","
                    + "S." + DataBaseHandler.KEY_STORE_NAME + ","
                    + "S." + DataBaseHandler.KEY_STORE_PHONE + ","
                    + "S." + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                    + "S." + DataBaseHandler.KEY_STORE_LAT + ","
                    + "S." + DataBaseHandler.KEY_STORE_LNG + " FROM "

                    + DataBaseHandler.TABLE_CITY + " CI, "
                    + DataBaseHandler.TABLE_STORE + " S WHERE "

                    + strWhere
                    + " AND S." + DataBaseHandler.KEY_STORE_CITY + " = CI." + DataBaseHandler.KEY_CITY_ID;
        } else if(strWhere != null && strOrderBy != null){
            query = "SELECT CI." + DataBaseHandler.KEY_CITY_ID + ","
                    + "CI." + DataBaseHandler.KEY_CITY_NAME + ","

                    + "S." + DataBaseHandler.KEY_STORE_ID + ","
                    + "S." + DataBaseHandler.KEY_STORE_NAME + ","
                    + "S." + DataBaseHandler.KEY_STORE_PHONE + ","
                    + "S." + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                    + "S." + DataBaseHandler.KEY_STORE_LAT + ","
                    + "S." + DataBaseHandler.KEY_STORE_LNG + " FROM "

                    + DataBaseHandler.TABLE_CITY + " CI, "
                    + DataBaseHandler.TABLE_STORE + " S WHERE "

                    + strWhere
                    + " AND S." + DataBaseHandler.KEY_STORE_CITY + " = CI." + DataBaseHandler.KEY_CITY_ID
                    + " ORDER BY " + strOrderBy;
        } else{
            query = "SELECT CI." + DataBaseHandler.KEY_CITY_ID + ","
                    + "CI." + DataBaseHandler.KEY_CITY_NAME + ","

                    + "S." + DataBaseHandler.KEY_STORE_ID + ","
                    + "S." + DataBaseHandler.KEY_STORE_NAME + ","
                    + "S." + DataBaseHandler.KEY_STORE_PHONE + ","
                    + "S." + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                    + "S." + DataBaseHandler.KEY_STORE_LAT + ","
                    + "S." + DataBaseHandler.KEY_STORE_LNG + " FROM "

                    + DataBaseHandler.TABLE_CITY + " CI, "
                    + DataBaseHandler.TABLE_STORE + " S "

                    + " WHERE S." + DataBaseHandler.KEY_STORE_CITY + " = CI." + DataBaseHandler.KEY_CITY_ID;
        }

        Cursor cursor = db.rawQuery(query, null); // El segundo parametro es para los '?' que llegues a tener en un WHERE en la query, lleva un array de strings

        while(cursor.moveToNext()){
            City city = new City();
            city.setIdCity(cursor.getInt(0));
            city.setName(cursor.getString(1));

            Store store = new Store();
            store.setId(cursor.getInt(2));
            store.setName(cursor.getString(3));
            store.setPhone(cursor.getString(4));
            store.setThumbnail(cursor.getInt(5));
            store.setLatitude(cursor.getDouble(6));
            store.setLongitude(cursor.getDouble(7));

            store.setCity(city);

            stores.add(store);
        }

        try {cursor.close();db.close();
        } catch (Exception e) {}

        db = null;
        cursor = null;

        return stores;
    }
}
