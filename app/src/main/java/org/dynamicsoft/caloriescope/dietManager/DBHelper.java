package org.dynamicsoft.caloriescope.dietManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String Table_name = "SQLiteExample";
    private static final String TAG = "DBHelper";
    private static String COL1 = "ID";
    private static String COL2 = "TYPE";
    private static String COL3 = "DayCode";
    private static String COL4 = "Name";
    private static String COL5 = "Items";
    private SQLiteDatabase db = this.getWritableDatabase();


    public DBHelper(Context context) {
        super(context, Table_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_name + "(" +
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +
                COL4 + " TEXT," +
                COL5 + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        onCreate(db);
    }

    public boolean adddata(String daycode, String name, String items, String type) {
        // SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, type);
        contentValues.put(COL3, daycode);
        contentValues.put(COL4, name);
        contentValues.put(COL5, items);

        Log.d(TAG, "ic_diet_plan_fab_add data: Adding" + name + "to" + Table_name);
        long result = db.insert(Table_name, "", contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getdata(String key) {
        //  SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table_name + " WHERE " + COL3 + " = '" + key + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemData(int name, String key) {
        // SQLiteDatabase db=this.getWritableDatabase();
        String q = "SELECT * FROM " + Table_name + " WHERE " + COL1 + " = '" + name + "'" + " AND " + COL2 + " = '" + key + "'";
        ;
        Cursor data = db.rawQuery(q, null);
        return data;
    }

    public void update(String newname, int id, String oldname) {
        String qu = "UPDATE " + Table_name + " SET " + COL4 +
                " = '" + newname + "'WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL4 + " = '" + oldname + "'";
        db.execSQL(qu);

    }

    public void delete(int id, String name) {
        String q = "DELETE FROM " + Table_name + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL4 + " = '" + name + " '";
        db.execSQL(q);
    }

    public void deletex(String x) {
        String[] con = {x};
        int res = db.delete(Table_name, "ID = ?", con);

    }
}