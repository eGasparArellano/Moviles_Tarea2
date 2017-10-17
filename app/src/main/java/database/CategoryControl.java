package database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import beans.Category;

/**
 * Created by Desarrollo on 16/10/2017.
 */

public class CategoryControl {
    public ArrayList<Category> getAllCategories(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Category> categories = new ArrayList<>();

        String query = "SELECT " + DataBaseHandler.KEY_CATEGORY_ID + ", "
                + DataBaseHandler.KEY_CATEGORY_NAME
                + " FROM " + DataBaseHandler.TABLE_CATEGORY;

        Cursor cursor = db.rawQuery(query, null); // El segundo parametro es para los '?' que llegues a tener en un WHERE en la query, lleva un array de strings

        while(cursor.moveToNext()){
            Category category = new Category();
            category.setIdCategory(cursor.getInt(0));
            category.setName(cursor.getString(1));

            categories.add(category);
        }

        try {cursor.close();db.close();
        } catch (Exception e) {}

        db = null;
        cursor = null;

        return categories;
    }
}
