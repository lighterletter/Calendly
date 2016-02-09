package lighterletter.calendly;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.SensorManager;

/**
 * Created by c4q-john on 1/21/16.
 * Class that creates database for application.
 */

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    private static final int SCHEMA_VER = 1;
    private static final String DATABASE_NAME = "calendly.db";

    //table
    static final String TABLE_ACTIVITIES = "activities";

    //table column names

    static final String ACTIVITY_ID = "_id";
    static final String DATE = "date";
    static final String DAY = "day";
    static final String NAME = "name";
    static final String DESC = "description";
    static final String TIME_START = "start";
    static final String TIME_END = "end";
    static final String ACTIVITY_DUR= "duration";

    //CREATE TABLE (key_id INTEGER(P)
    //activities table
    private static final String CREATE_ACTIVITES_TABLE = "CREATE TABLE " + TABLE_ACTIVITIES + " ("
            + ACTIVITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DATE + " TEXT NOT NULL, "
            + DAY + " TEXT NOT NULL, "
            + NAME + " TEXT NOT NULL, "
            + DESC + " TEXT, "
            + TIME_START + " TEXT NOT NULL, "
            + TIME_END + " TEXT NOT NULL, "
            + ACTIVITY_DUR + " TEXT NOT NULL"
            + ")";

    public DataBaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACTIVITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        onCreate(db);
    }
}
