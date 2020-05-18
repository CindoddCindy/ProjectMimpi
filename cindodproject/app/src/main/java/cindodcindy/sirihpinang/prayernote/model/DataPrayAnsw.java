package cindodcindy.sirihpinang.prayernote.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataPrayAnsw extends SQLiteOpenHelper {
    private static final int VERSIONANSW = 1;
    private static final String DBNAMEANSW = "db_pray";
    private static final String TABLENAMEANSW = "pray";

    private static String colIDAnsw = "_id";
    private static String colTanggalAnsw = "tanggalansw";
    private static String colDoaAnsw = "doaansw";

    public DataPrayAnsw(Context context) {
        super(context, DBNAMEANSW, null, VERSIONANSW);
    }




    public DataPrayAnsw(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLENAMEANSW + " (" +
                colIDAnsw + " INTEGER PRIMARY KEY AUTOINCREMENT, " + colTanggalAnsw + " TEXT," +
                colDoaAnsw + " TEXT)";
        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLENAMEANSW);
        onCreate(db);


    }

    public void insertDataAnsw(String tanggalAnsw, String doaAnsw){
        String insertData = "INSERT INTO "+ TABLENAMEANSW + " ("+ colTanggalAnsw +","+colDoaAnsw+") VALUES ('"+tanggalAnsw +"', '"+doaAnsw+"')";
        this.getWritableDatabase().execSQL(insertData);
    }

    public void updateDataAnsw(int id, String tanggalAnsw, String doaAnsw){
        String updateData = "UPDATE "+TABLENAMEANSW+ " SET "+ colTanggalAnsw + "= '"+tanggalAnsw +"', "+colDoaAnsw + "= '"+doaAnsw + "' WHERE "+colIDAnsw +" ="+id;
        this.getWritableDatabase().execSQL(updateData);
    }

    public void deleteDataAnsw(int id){
        String deleteData = "DELETE FROM "+TABLENAMEANSW +" WHERE id="+id;
        this.getWritableDatabase().execSQL(deleteData);
    }

    public PojoAnsw getDataAnsw(int id){
        PojoAnsw pojoAnsw = null;
        String selectData = "SELECT * FROM "+TABLENAMEANSW + " WHERE id="+String.valueOf(id);
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            pojoAnsw= new PojoAnsw(Integer.parseInt(data.getString(data.getColumnIndex(colIDAnsw))),
                    data.getString(data.getColumnIndex(colTanggalAnsw)), data.getString(data.getColumnIndex(colDoaAnsw)));
        }
        return pojoAnsw;
    }

    public List<PojoAnsw> getAllAnsw(){
        List<PojoAnsw> pojoAnsws = new ArrayList<>();
        String selectData = "SELECT * FROM "+TABLENAMEANSW;
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if(data.moveToFirst()){
            do{
                pojoAnsws.add(new PojoAnsw(Integer.parseInt(data.getString(data.getColumnIndex(colIDAnsw))),
                        data.getString(data.getColumnIndex(colTanggalAnsw)), data.getString(data.getColumnIndex(colDoaAnsw))));
            }while (data.moveToNext());
        }
        return pojoAnsws;
    }

}
