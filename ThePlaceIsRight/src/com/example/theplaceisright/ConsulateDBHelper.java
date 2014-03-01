package com.example.theplaceisright;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class ConsulateDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    static final String DICTIONARY_TABLE_NAME = "consulate";
    static final String KEY_COUNTRY = "Country";
    static final String KEY_OFFICE = "Office";
    static final String VALUE_CODE = "Code";
	static final String VALUE_PRIMARY = "Primary";
	static final String VALUE_PASSPORT = "Passport";
    static final String VALUE_CITY = "City";
    static final String VALUE_TYPE = "Type";
    static final String VALUE_LAT = "Latitude";
    static final String VALUE_LON = "Longitude";
    static final String VALUE_ADDRESS = "Address";
    static final String VALUE_TEL = "Telephone";
	static final String VALUE_EMERG = "Emergency";
	static final String VALUE_FAX = "Fax";
	static final String VALUE_EMAIL = "Email";

    private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                KEY_COUNTRY + " TEXT NOT NULL," +
                KEY_OFFICE + " INTEGER NOT NULL, " +
                VALUE_CODE + " TEXT NOT NULL," +
                VALUE_PRIMARY + " INTEGER NOT NULL, " +
                VALUE_PASSPORT + " INTEGER NOT NULL, " +
                VALUE_CITY + " TEXT NOT NULL," +
                VALUE_TYPE + " TEXT," +
                VALUE_LAT + " REAL NOT NULL, " +
                VALUE_LON + " REAL NOT NULL, " +
                VALUE_ADDRESS + " TEXT NOT NULL," +
                VALUE_TEL + " TEXT," +
                VALUE_EMERG + " TEXT," +
                VALUE_FAX + " TEXT," +
                VALUE_EMAIL + " TEXT," +
                "PRIMARY KEY ("+ KEY_COUNTRY + ", " + KEY_OFFICE +")" +
                ");";

    ConsulateDBHelper(Context context) {
        super(context, DICTIONARY_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	    Log.w(ConsulateDBHelper.class.getName(),
	            "Upgrading database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
	        db.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_TABLE_NAME);
	        onCreate(db);
	}
}
