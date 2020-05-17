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


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "PrayAppDB";
    private static final String TABLE_PRAY_NOTES = "tb_pray";

    private static final String KEY_PRAY_NOTE_ID = "id";
    private static final String KEY_PRAY_NOTE_DATE = "date";
    private static final String KEY_PRAY_NOTE_CONTENT = "content";

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

        String query = "CREATE TABLE " + TABLE_PRAY_NOTES + " (" +
                KEY_PRAY_NOTE_ID + " INTEGER PRIMARY KEY, " +
                KEY_PRAY_NOTE_DATE + " TEXT, " +
                KEY_PRAY_NOTE_CONTENT + " TEXT" + ")";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRAY_NOTES);

        onCreate(db);

    }


    public int addPray(PrayPojo prayPojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PRAY_NOTE_DATE, prayPojo.getDate());
        values.put(KEY_PRAY_NOTE_CONTENT, prayPojo.getPray());

        long ID = db.insert(TABLE_PRAY_NOTES, null, values);
        db.close();

        return (int) ID;
    }

    public List<PrayPojo> getPray() {
        List<PrayPojo> noteList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PRAY_NOTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String datePray = cursor.getString(1);
                String praycontent = cursor.getString(2);

                PrayPojo prayPojo = new PrayPojo(id, datePray, praycontent);

                noteList.add(prayPojo);
            } while (cursor.moveToNext());
        }

        return noteList;
    }

    public PrayPojo getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRAY_NOTES, new String[] {
                KEY_PRAY_NOTE_ID,
                KEY_PRAY_NOTE_DATE,
                KEY_PRAY_NOTE_CONTENT
        }, KEY_PRAY_NOTE_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        int PraynoteID = cursor.getInt(0);
        String praydate = cursor.getString(1);
        String praycontent = cursor.getString(2);

        PrayPojo prayPojo = new PrayPojo(PraynoteID, praydate, praycontent);

        return prayPojo;
    }

    public int updatePrayNote(PrayPojo prayPojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PRAY_NOTE_DATE, prayPojo.getDate());
        values.put(KEY_PRAY_NOTE_CONTENT, prayPojo.getPray());

        return db.update(TABLE_PRAY_NOTES, values, KEY_PRAY_NOTE_ID + "=?",
                new String[]{String.valueOf(prayPojo.getPrayId())});
    }

    public int deletePrayNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int ID = db.delete(TABLE_PRAY_NOTES, KEY_PRAY_NOTE_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();

        return ID;
    }
}


