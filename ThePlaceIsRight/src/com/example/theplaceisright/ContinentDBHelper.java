package com.example.theplaceisright;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class ContinentDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    static final String DICTIONARY_TABLE_NAME = "continent";
    static final String KEY_COUNTRY2 = "Country2";
    static final String VALUE_CONTINENT = "Continent";
    static final String VALUE_COUNTRY3 = "Country3";

    private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                KEY_COUNTRY2 + " TEXT NOT NULL," +
                VALUE_CONTINENT + " TEXT NOT NULL," +
                VALUE_COUNTRY3 + " TEXT," +
                "PRIMARY KEY ("+ KEY_COUNTRY2 + ")" +
                ");";

    ContinentDBHelper(Context context) {
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
