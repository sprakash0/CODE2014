package com.example.theplaceisright;

import java.io.File;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;


public class DatabaseManager {
	// TODO: add more databases
	private static SQLiteDatabase database;
	private static DatabaseOpenHelper dbHelper;
	
	//private static SQLiteDatabase advisoryDB;
	//private static DatabaseOpenHelper advisoryHelper;

	//private static SQLiteDatabase database;
	//private static DatabaseOpenHelper dbHelper;
	
	private static boolean isInitConsulate = false;
	private static boolean isInitContinent = false;
	private static boolean isInitAdvisory = false;
	
	private static boolean overwriteConsulate = true;
	private static boolean overwriteContinent = true;
	private static boolean overwriteAdvisory = true;
	
	
	private static SplashScreenActivity activity;
	
	
	public DatabaseManager(Context context) {
		File database = context.getDatabasePath(DatabaseOpenHelper.DATABASE_NAME);
		if (database.exists()) {
			overwriteConsulate = false;
			isInitConsulate = true;
			
			overwriteContinent = false;
			isInitContinent = true;
		}

		dbHelper = new DatabaseOpenHelper(context);

		open();
	}

	private static void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public static void close() {
		database.close();
	}
	
	public static void initDatabases(SplashScreenActivity act){
		// note: all databases have overridden onOpen
		// so to drop table 
		
		activity = act;
		if (!isInitConsulate)
		{
			ConsulateDataParser consulateP = new ConsulateDataParser();
			consulateP.run();
		}
		
		if (!isInitContinent)
		{
			ContinentHTMLParser continentP = new ContinentHTMLParser();
			continentP.run();
		}
		
		if (!isInitAdvisory)
		{
			AdvisoryDataParser advisoryP = new AdvisoryDataParser();
			advisoryP.run();
		}
	}
	
	private static boolean checkInit(boolean updateUI){
		if (isInitConsulate && isInitContinent && isInitAdvisory)
		{
			if (updateUI)
			{
			// TODO update UI
			}
			return true;
		}
		return false;
	}
	
	public static void importConsulates(ArrayList<Consulate> ins) {
		if (overwriteConsulate) {
			for (Consulate c : ins) {
				insertConsulate(c);
			}
		}
		
		isInitConsulate = true;
		checkInit(true);
	}

	public static void importContinents(ArrayList<ContinentCountry> ins) {
		if (overwriteContinent) {
			for (ContinentCountry c : ins) {
				insertContinent(c);
			}
		}
		
		isInitContinent = true;
		checkInit(true);
	}

	public static void importAdvisory(ArrayList<Advisory> ins) {
		if (overwriteAdvisory) {
			for (Advisory a : ins) {
				insertAdvisory(a);
			}
		}
		
		isInitAdvisory = true;
		checkInit(true);
	}

	
	public static void insertConsulate(Consulate ins) {
		if (ins == null) return;
	    ContentValues values = new ContentValues();
	    values.put(DatabaseOpenHelper.KEY_COUNTRY, ins.getCountry());
	    values.put(DatabaseOpenHelper.KEY_OFFICE, ins.getOfficeID());
	    values.put(DatabaseOpenHelper.VALUE_CODE, ins.getCountryCode());
	    values.put(DatabaseOpenHelper.VALUE_PRIMARY, (ins.getIsPrimary()?1:0));
	    values.put(DatabaseOpenHelper.VALUE_PASSPORT, (ins.getHasPassport()?1:0));
	    values.put(DatabaseOpenHelper.VALUE_CITY, ins.getCity());
	    values.put(DatabaseOpenHelper.VALUE_TYPE, ins.getType());
	    values.put(DatabaseOpenHelper.VALUE_LAT, ins.getLatitude());
	    values.put(DatabaseOpenHelper.VALUE_LON, ins.getLongitude());
	    values.put(DatabaseOpenHelper.VALUE_ADDRESS, ins.getAddress());
	    values.put(DatabaseOpenHelper.VALUE_TEL, ins.getTelephone());
	    values.put(DatabaseOpenHelper.VALUE_EMERG, ins.getEmergency());
	    values.put(DatabaseOpenHelper.VALUE_FAX, ins.getFax());
	    values.put(DatabaseOpenHelper.VALUE_EMAIL, ins.getEmail());
	    if (database.insertWithOnConflict(DatabaseOpenHelper.TABLE_CONSULATE, null, values, SQLiteDatabase.CONFLICT_REPLACE) < 0) {
	    	Log.w(DatabaseManager.class.getName(), "Error when inserting new consulate row with " +
	    			"country = " + ins.getCountry() + ", officeID = " + ins.getOfficeID() + 
	    			", country code = " + ins.getCountryCode());
	    }
	}
	
	public static ArrayList<Consulate> getConsulates(String country) {
		ArrayList<Consulate> cons = new ArrayList<Consulate>();
		if (!checkInit(false)) return cons;
		Cursor c = database.rawQuery("SELECT * FROM " 
				+ DatabaseOpenHelper.TABLE_CONSULATE 
				+ " WHERE UPPER(" + DatabaseOpenHelper.KEY_COUNTRY 
				+ ") = '" + country.trim().toUpperCase() + "'", null);
		
		
		if (!c.moveToFirst()) return cons;
		do
		{
			Consulate curr = new Consulate();
			curr.setOfficeID(cons.size());
			
			int idx = c.getColumnIndex(DatabaseOpenHelper.KEY_COUNTRY);
			if (idx >= 0)
				curr.setCountry(c.getString(idx));

			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_CODE);
			if (idx >= 0)
				curr.setCountryCode(c.getString(idx));
			
			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_PRIMARY);
			if (idx >= 0)
				curr.setIsPrimary(c.getInt(idx) == 1 ? true : false);

			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_PASSPORT);
			if (idx >= 0)
				curr.setHasPassport(c.getInt(idx) == 1 ? true : false);

			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_CITY);
			if (idx >= 0)
				curr.setCity(c.getString(idx));

			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_TYPE);
			if (idx >= 0)
				curr.setType(c.getString(idx));
			
			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_LAT);
			if (idx >= 0)
				curr.setLongitude(c.getFloat(idx));

			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_LON);
			if (idx >= 0)
				curr.setLatitude(c.getFloat(idx));

			
			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_ADDRESS);
			if (idx >= 0)
				curr.setAddress(c.getString(idx));

			
			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_TEL);
			if (idx >= 0)
				curr.setTelephone(c.getString(idx));

			
			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_EMERG);
			if (idx >= 0)
				curr.setEmergency(c.getString(idx));

			
			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_FAX);
			if (idx >= 0)
				curr.setFax(c.getString(idx));

			idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_EMAIL);
			if (idx >= 0)
				curr.setEmail(c.getString(idx));

			cons.add(curr);
		} while (c.moveToNext());
		c.close();
		
		return cons;
	}
	
	public static void insertAdvisory(Advisory ins) {
		if (ins == null) return;
	    ContentValues values = new ContentValues();
	    values.put(DatabaseOpenHelper.KEY_COUNTRY, ins.getCountry());
	    values.put(DatabaseOpenHelper.VALUE_CODE, ins.getCountryCode());
	    values.put(DatabaseOpenHelper.VALUE_DATE , ins.getDate());
	    values.put(DatabaseOpenHelper.VALUE_TEXT, ins.getText());
	    try{
	    if (database.insertWithOnConflict(DatabaseOpenHelper.TABLE_ADVISORY, null, values, SQLiteDatabase.CONFLICT_REPLACE) < 0) {
	    	Log.w(DatabaseManager.class.getName(), "Error when inserting new advisory row with " +
	    			"country = " + ins.getCountry() + ", country code = " + ins.getCountryCode());
	    }
	    } catch (SQLiteException ex) {
	    	Log.e(DatabaseManager.class.getName(), "SQLiteException: " + ex.getMessage());
	    }
	}
	

	public static Advisory getAdvisory(String country) {
		if (!checkInit(false)) return null;
		Cursor c = database.rawQuery("SELECT * FROM " 
				+ DatabaseOpenHelper.TABLE_ADVISORY 
				+ " WHERE UPPER(" + DatabaseOpenHelper.KEY_COUNTRY 
				+ ") = '" + country.trim().toUpperCase() + "'", null);
		
		if (!c.moveToFirst()) return null;
		
		Advisory curr = new Advisory();

		int idx = c.getColumnIndex(DatabaseOpenHelper.KEY_COUNTRY);
		if (idx >= 0)
			curr.setCountry(c.getString(idx));
		
		idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_CODE);
		if (idx >= 0)
			curr.setCountryCode(c.getString(idx));

		idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_DATE);
		if (idx >= 0)
			curr.setDate(c.getString(idx));

		idx = c.getColumnIndex(DatabaseOpenHelper.VALUE_TEXT);
		if (idx >= 0)
			curr.setText(c.getString(idx));
		
		return curr;

	}
	
	public static void insertContinent(ContinentCountry ins) {
		if (ins == null) return;
	    ContentValues values = new ContentValues();
	    values.put(DatabaseOpenHelper.KEY_COUNTRY2, ins.getCountryCode2());
	    values.put(DatabaseOpenHelper.VALUE_CONTINENT, ins.getContinent());
	    values.put(DatabaseOpenHelper.VALUE_COUNTRY3, ins.getCountryCode3());
	    database.insertWithOnConflict(DatabaseOpenHelper.TABLE_CONTINENT, null, values, SQLiteDatabase.CONFLICT_REPLACE);
	}
	
	public static ArrayList<String> getCountries(String continentName) {
		// TODO select for certain columns only?
		ArrayList<String> countries = new ArrayList<String>();
		countries.add("Countries");
		if (!checkInit(false)) return countries;
		String code = ContinentCountry.continentNameToCode(continentName.trim());
		if (code == null) return countries;
		String q = "SELECT * FROM " 
				+ DatabaseOpenHelper.TABLE_CONTINENT +  " a "
				+ " LEFT JOIN " 
				+ DatabaseOpenHelper.TABLE_CONSULATE +  " b "
				+ " ON "
				+ "a." + DatabaseOpenHelper.KEY_COUNTRY2 + " = "
				+ "b." + DatabaseOpenHelper.VALUE_CODE
				+ " WHERE "
				+ "UPPER(a." + DatabaseOpenHelper.KEY_COUNTRY2 + ") = '" 
				+ code + "'";
		
		database = dbHelper.getWritableDatabase();
		Cursor c = database.rawQuery(q, null);
		
		if (!c.moveToFirst()) return countries;
		do
		{
			int idx = c.getColumnIndex(DatabaseOpenHelper.KEY_COUNTRY2);
			if (idx >= 0)
				countries.add(c.getString(idx));
		} while (c.moveToNext());
		c.close();
		
		return countries;
	}
}
