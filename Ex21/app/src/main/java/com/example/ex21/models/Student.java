package com.example.ex21.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class Student extends SQLiteOpenHelper {
    private static final String DB_NAME = "Database";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "Student";

    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String VALUE_COL = "value";

    public Student(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String createTable = String.format("CREATE TABLE %s ( %s  TEXT PRIMARY KEY , %s TEXT , %s INTEGER )" , TABLE_NAME , ID_COL , NAME_COL , VALUE_COL);
            sqLiteDatabase.execSQL(createTable);
        Log.d("loi" , "Khởi tạo CSDL");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void addStudent(String id, String name, int value) {
        // Lấy đối tượng database ở chế độ ghi
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_COL, id);  // Thêm id
        values.put(NAME_COL, name);  // Thêm name
        values.put(VALUE_COL, value);  // Thêm value

        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            Log.d("loi", "Thêm dữ liệu thất bại");
        } else {
            Log.d("loi", "Thêm dữ liệu thành công");
        }
        db.close();
    }
    public void updateStudent(String id, String name, int value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(VALUE_COL, value);

        int result = db.update(TABLE_NAME, values, ID_COL + " = ?", new String[]{id});

        if (result > 0) {
            Log.d("loi", "Cập nhật dữ liệu thành công");
        } else {
            Log.d("loi", "Cập nhật dữ liệu thất bại");
        }

        db.close();
    }
    public void deleteStudent(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, ID_COL + " = ?", new String[]{id});

        if (result > 0) {
            Log.d("loi", "Xóa dữ liệu thành công");
        } else {
            Log.d("loi", "Xóa dữ liệu thất bại");
        }

        db.close();
    }
    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(sql , null);
        return data;
    }
    public Cursor getDataByID(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s = ?" , TABLE_NAME , ID_COL);
        Cursor data = db.rawQuery(sql, new String[]{id});
        return data;
    }
}
