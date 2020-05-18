package cindodcindy.sirihpinang.prayernote.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class DataPray extends SQLiteOpenHelper {


    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "PrayAppDB";
    private static final String TABLE_PRAY_NOTES = "tb_pray";

    private static final String KEY_PRAY_NOTE_ID = "id";
    private static final String KEY_PRAY_NOTE_DATE = "date";
    private static final String KEY_PRAY_NOTE_CONTENT = "content";
    private static final String KEY_PRAY_NOTE_DATE_ANSW = "date_answ";
    private static final String KEY_PRAY_NOTE_CONTENT_ANSW = "content_answ";


    public DataPray(Context context) {

        super(context, DB_NAME, null, DB_VERSION);


    }





    public DataPray(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataPray(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DataPray(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_PRAY_NOTES + " (" +
                KEY_PRAY_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_PRAY_NOTE_DATE+ " TEXT," +
                KEY_PRAY_NOTE_CONTENT + " TEXT," + KEY_PRAY_NOTE_DATE_ANSW + "TEXT," + KEY_PRAY_NOTE_CONTENT_ANSW +
                "TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRAY_NOTES);
        onCreate(db);
    }


    public void insertData(String date, String pray, String date_answ, String pray_ans){
        String insertData = "INSERT INTO "+ TABLE_PRAY_NOTES + " ("+ KEY_PRAY_NOTE_DATE +","+ KEY_PRAY_NOTE_CONTENT +","+ KEY_PRAY_NOTE_DATE_ANSW +" ,"+ KEY_PRAY_NOTE_CONTENT_ANSW +") VALUES ('"+date +"', '"+pray+"','"+date_answ+"','"+pray_ans+"')";
        this.getWritableDatabase().execSQL(insertData);
    }

    public void updateData(int id, String date, String prays,String date_answ, String pray_answ){
        String updateData = "UPDATE "+TABLE_PRAY_NOTES+ " SET "+ KEY_PRAY_NOTE_DATE + "= '"+date +"', "+KEY_PRAY_NOTE_CONTENT + "= '"+prays + "',"+KEY_PRAY_NOTE_DATE_ANSW + "= '"+date_answ + "', "+KEY_PRAY_NOTE_CONTENT_ANSW +" = '"+pray_answ+"' WHERE "+KEY_PRAY_NOTE_ID +" ="+id;
        this.getWritableDatabase().execSQL(updateData);
    }

    public void deleteData(int id){
        String deleteData = "DELETE FROM "+TABLE_PRAY_NOTES +" WHERE id="+id;
        this.getWritableDatabase().execSQL(deleteData);
    }

    public PrayPojo getData(int id){
        PrayPojo prayPojo = null;
        String selectData = "SELECT * FROM "+TABLE_PRAY_NOTES + " WHERE id="+String.valueOf(id);
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            prayPojo = new PrayPojo(Integer.parseInt(data.getString(data.getColumnIndex(KEY_PRAY_NOTE_ID))),
                    data.getString(data.getColumnIndex(KEY_PRAY_NOTE_DATE)), data.getString(data.getColumnIndex(KEY_PRAY_NOTE_CONTENT)),data.getString(data.getColumnIndex(KEY_PRAY_NOTE_DATE_ANSW)),data.getString(data.getColumnIndex(KEY_PRAY_NOTE_CONTENT_ANSW)));
        }
        return prayPojo;
    }

    public List<PrayPojo> getAll(){
        List<PrayPojo> model = new ArrayList<>();
        String selectData = "SELECT * FROM "+TABLE_PRAY_NOTES;
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            do{
                model.add(new PrayPojo(Integer.parseInt(data.getString(data.getColumnIndex(KEY_PRAY_NOTE_ID))),
                        data.getString(data.getColumnIndex(KEY_PRAY_NOTE_DATE)), data.getString(data.getColumnIndex(KEY_PRAY_NOTE_CONTENT)),data.getString(data.getColumnIndex(KEY_PRAY_NOTE_DATE_ANSW)),data.getString(data.getColumnIndex(KEY_PRAY_NOTE_CONTENT_ANSW))));
            }while (data.moveToNext());
        }
        return model;
    }

}


