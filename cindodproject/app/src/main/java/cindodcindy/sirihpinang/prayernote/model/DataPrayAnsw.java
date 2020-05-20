package cindodcindy.sirihpinang.prayernote.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataPrayAnsw extends SQLiteOpenHelper {
    private static final int VERSIONANSW = 3;
    private static final String DBNAMEANSW = "db_answer";
    private static final String TABLENAME = "answer";

    private static String colIDAnsw = "id_answ";
    private static String colTanggalPr = "tanggal_pr";
    private static String colDoaPr = "doa_pr";
    private static String colTanggalAnsw = "tanggal_answ";
    private static String colDoaPrAnsw = "doa_answ";

    public DataPrayAnsw(Context context) {
        super(context, DBNAMEANSW, null, VERSIONANSW);
    }









    public DataPrayAnsw(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLENAME + " (" +
                colIDAnsw + " INTEGER PRIMARY KEY AUTOINCREMENT, " + colTanggalPr + " TEXT," + colDoaPr + " TEXT, " + colTanggalAnsw + " TEXT, " +
                colDoaPrAnsw + " TEXT)";
        db.execSQL(createTable);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);
        onCreate(db);



    }


    public void insertDataAnsw(String tanggal, String doa, String tanggalAnsw, String prayAnsw){
        String insertData = "INSERT INTO "+ TABLENAME + " ("+ colTanggalPr +","+colDoaPr+","+colTanggalAnsw+","+colDoaPrAnsw+") VALUES ('"+tanggal +"', '"+doa+"','"+tanggalAnsw+"','"+prayAnsw+"')";
        this.getWritableDatabase().execSQL(insertData);
    }

    public void updateDataAnsw(int id, String tanggal, String doa, String tanggal_answ, String doa_answ){
        String updateData = "UPDATE "+TABLENAME+ " SET "+ colTanggalPr + "= '"+tanggal +"', "+colDoaPr + "= '"+doa + "',"+colTanggalAnsw+ "='"+tanggal_answ+"',"+colDoaPrAnsw+"='"+doa_answ+"' WHERE "+colIDAnsw +" ="+id;
        this.getWritableDatabase().execSQL(updateData);
    }

    public void deleteDataAnsw(int id){
        String deleteData = "DELETE FROM "+TABLENAME +" WHERE id_answ="+id;
        this.getWritableDatabase().execSQL(deleteData);
    }

    public PojoAnsw getDataAnsw(int id){
        PojoAnsw pojoAnsw = null;
        String selectData = "SELECT * FROM "+TABLENAME + " WHERE id_answ="+String.valueOf(id);
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            pojoAnsw = new PojoAnsw(Integer.parseInt(data.getString(data.getColumnIndex(colIDAnsw))),
                    data.getString(data.getColumnIndex(colTanggalPr)), data.getString(data.getColumnIndex(colDoaPr)),data.getString(data.getColumnIndex(colTanggalAnsw)),data.getString(data.getColumnIndex(colDoaPrAnsw)));
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
                        data.getString(data.getColumnIndex(colTanggalPr)), data.getString(data.getColumnIndex(colDoaPr)),data.getString(data.getColumnIndex(colTanggalAnsw)),data.getString(data.getColumnIndex(colDoaPrAnsw))));
            }while (data.moveToNext());
        }
        return pojoAnsws;
    }

}


