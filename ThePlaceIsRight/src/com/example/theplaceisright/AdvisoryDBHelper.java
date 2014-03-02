package com.example.theplaceisright;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class AdvisoryDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    static final String DICTIONARY_TABLE_NAME = "advisory";
    static final String KEY_COUNTRY = "Country";
    static final String VALUE_CODE = "Code";
    static final String VALUE_DATE = "Date";
	static final String VALUE_TEXT = "Text";

    private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                KEY_COUNTRY + " TEXT NOT NULL," +
                VALUE_CODE + " TEXT NOT NULL," +
                VALUE_DATE + " TEXT," +
                VALUE_TEXT + " TEXT NOT NULL," +
                "PRIMARY KEY ("+ KEY_COUNTRY + ")" +
                ");";

    AdvisoryDBHelper(Context context) {
        super(context, DICTIONARY_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	    Log.w(AdvisoryDBHelper.class.getName(),
	            "Upgrading database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
	        db.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_TABLE_NAME);
	        onCreate(db);
	}
}
