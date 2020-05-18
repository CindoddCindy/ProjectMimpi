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

import cindodcindy.sirihpinang.prayernote.view.PrayListAdapter;

public class DataPray extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DBNAME = "db_pray";
    private static final String TABLENAME = "pray";

    private static String colID = "id";
    private static String colTanggal = "tanggal";
    private static String colDoa = "doa";


    public DataPray(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataPray(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLENAME + " (" +
                colID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + colTanggal + " TEXT," +
                colDoa + " TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);
        onCreate(db);

    }

    public void insertData(String tanggal, String doa){
        String insertData = "INSERT INTO "+ TABLENAME + " ("+ colTanggal +","+colDoa+") VALUES ('"+tanggal +"', '"+doa+"')";
        this.getWritableDatabase().execSQL(insertData);
    }

    public void updateData(int id, String tanggal, String doa){
        String updateData = "UPDATE "+TABLENAME+ " SET "+ colTanggal + "= '"+tanggal +"', "+colDoa + "= '"+doa + "' WHERE "+colID +" ="+id;
        this.getWritableDatabase().execSQL(updateData);
    }

    public void deleteData(int id){
        String deleteData = "DELETE FROM "+TABLENAME +" WHERE id="+id;
        this.getWritableDatabase().execSQL(deleteData);
    }

    public PrayPojo getData(int id){
        PrayPojo prayPojo = null;
        String selectData = "SELECT * FROM "+TABLENAME + " WHERE id="+String.valueOf(id);
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            prayPojo = new PrayPojo(Integer.parseInt(data.getString(data.getColumnIndex(colID))),
                    data.getString(data.getColumnIndex(colTanggal)), data.getString(data.getColumnIndex(colDoa)));
        }
        return prayPojo;
    }

    public List<PrayPojo> getAll(){
        List<PrayPojo> model = new ArrayList<>();
        String selectData = "SELECT * FROM "+TABLENAME;
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            do{
                model.add(new PrayPojo(Integer.parseInt(data.getString(data.getColumnIndex(colID))),
                        data.getString(data.getColumnIndex(colTanggal)), data.getString(data.getColumnIndex(colDoa))));
            }while (data.moveToNext());
        }
        return model;
    }

}


