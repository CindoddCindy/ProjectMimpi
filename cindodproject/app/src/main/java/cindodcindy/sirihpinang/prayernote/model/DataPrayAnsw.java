package cindodcindy.sirihpinang.prayernote.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataPrayAnsw extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DBNAME = "db_answer";
    private static final String TABLENAME = "answer";

    private static String colIDAnsw = "idAnsw";
    private static String colDateFrPray = "dateFrPray";
    private static String colPrayFrPray = "isipray";
    private static String colDateAnsw= "dateAnsw";
    private static String colPrayAnsw= "isiAnsw";



    public DataPrayAnsw(Context context) {
        super(context, DBNAME, null, VERSION);
    }






    public DataPrayAnsw(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLENAME + " (" +
                colIDAnsw + " INTEGER PRIMARY KEY AUTOINCREMENT, " + colDateFrPray+ " TEXT," + colPrayFrPray + " TEXT, " +
           colDateAnsw + " TEXT, " + colPrayAnsw + " TEXT)";
        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);
        onCreate(db);


    }

    public void insertDataAnsw(String datePray, String pray, String dateAnsw, String prayAnsw){
        String insertData = "INSERT INTO "+ TABLENAME + " ("+ colDateFrPray +","+colPrayFrPray+","+colDateAnsw+","+colPrayAnsw+") VALUES ('"+datePray +"', '"+pray+"','"+dateAnsw+"','"+prayAnsw+"')";
        this.getWritableDatabase().execSQL(insertData);
    }

    public void updateDataAnsw(int id, String datePray, String prayFrPray, String dateAnsw, String prayAnsw){
        String updateData = "UPDATE "+TABLENAME+ " SET "+ colDateFrPray + "= '"+datePray +"', "+colPrayFrPray + "= '"+prayFrPray + "', "+colDateAnsw+"= '"+dateAnsw +"', "+colPrayAnsw+"= '"+prayAnsw+"' WHERE "+colIDAnsw +" ="+id;
        this.getWritableDatabase().execSQL(updateData);
    }

    public void deleteDataAnsw(int id){
        String deleteData = "DELETE FROM "+TABLENAME +" WHERE id="+id;
        this.getWritableDatabase().execSQL(deleteData);
    }

    public PojoAnsw getDataAnsw(int id){
        PojoAnsw pojoAnsw = null;
        String selectData = "SELECT * FROM "+TABLENAME + " WHERE id="+String.valueOf(id);
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            pojoAnsw = new PojoAnsw(Integer.parseInt(data.getString(data.getColumnIndex(colIDAnsw))),
                    data.getString(data.getColumnIndex(colDateFrPray)), data.getString(data.getColumnIndex(colPrayFrPray)),data.getString(data.getColumnIndex(colDateAnsw)),data.getString(data.getColumnIndex(colPrayAnsw)));
        }
        return pojoAnsw;
    }

    public List<PojoAnsw> getAllAnsw(){
        List<PojoAnsw> pojoAnsws = new ArrayList<>();
        String selectData = "SELECT * FROM "+TABLENAME;
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            do{
                pojoAnsws.add(new PojoAnsw(Integer.parseInt(data.getString(data.getColumnIndex(colIDAnsw))),
                        data.getString(data.getColumnIndex(colDateFrPray)), data.getString(data.getColumnIndex(colPrayFrPray)),data.getString(data.getColumnIndex(colDateAnsw)),data.getString(data.getColumnIndex(colPrayAnsw))));
            }while (data.moveToNext());
        }
        return pojoAnsws;
    }
}


