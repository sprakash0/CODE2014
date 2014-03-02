package com.example.theplaceisright;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    
    static final String DATABASE_NAME = "infoDatabase";
    
    static final String TABLE_CONSULATE = "consulate";
    static final String KEY_COUNTRY = "Country";
    static final String KEY_OFFICE = "Office";
    static final String VALUE_CODE = "Code";
	static final String VALUE_PRIMARY = "IsPrimary";
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
	
	

    private static final String DICTIONARY_CREATE_CONSUL =
                "CREATE TABLE " + TABLE_CONSULATE + " (" +
                KEY_COUNTRY + " TEXT NOT NULL," +
                KEY_OFFICE + " INTEGER NOT NULL, " +
                VALUE_CODE + " TEXT NOT NULL," +
                VALUE_PRIMARY + " INTEGER, " +
                VALUE_PASSPORT + " INTEGER, " +
                VALUE_CITY + " TEXT," +
                VALUE_TYPE + " TEXT," +
                VALUE_LAT + " REAL, " +
                VALUE_LON + " REAL, " +
                VALUE_ADDRESS + " TEXT," +
                VALUE_TEL + " TEXT," +
                VALUE_EMERG + " TEXT," +
                VALUE_FAX + " TEXT," +
                VALUE_EMAIL + " TEXT," +
                "PRIMARY KEY ("+ KEY_COUNTRY + ", " + KEY_OFFICE +")" +
                ");";
    
    static final String TABLE_ADVISORY = "advisory";
    static final String VALUE_DATE = "Date";
	static final String VALUE_TEXT = "Text";

    private static final String DICTIONARY_CREATE_ADVIS =
                "CREATE TABLE " + TABLE_ADVISORY + " (" +
                KEY_COUNTRY + " TEXT," +
                VALUE_CODE + " TEXT," +
                VALUE_DATE + " TEXT," +
                VALUE_TEXT + " TEXT," +
                "PRIMARY KEY ("+ KEY_COUNTRY + ")" +
                ");";
    
    static final String TABLE_CONTINENT = "continent";
    static final String KEY_COUNTRY2 = "Country2";
    static final String VALUE_CONTINENT = "Continent";
    static final String VALUE_COUNTRY3 = "Country3";

    private static final String DICTIONARY_CREATE_CONTIN =
                "CREATE TABLE " + TABLE_CONTINENT + " (" +
                KEY_COUNTRY2 + " TEXT NOT NULL," +
                VALUE_CONTINENT + " TEXT NOT NULL," +
                VALUE_COUNTRY3 + " TEXT," +
                "PRIMARY KEY ("+ KEY_COUNTRY2 + ")" +
                ");";



    DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_CREATE_CONSUL);
        db.execSQL(DICTIONARY_CREATE_CONTIN);
        db.execSQL(DICTIONARY_CREATE_ADVIS);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_TABLE_NAME);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	    Log.w(DatabaseOpenHelper.class.getName(),
	            "Upgrading database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
	        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
	        onCreate(db);
	}
}
