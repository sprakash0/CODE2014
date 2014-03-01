package com.example.theplaceisright;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseManager {
	// TODO: add more databases
	private SQLiteDatabase consulateDB;
	private ConsulateDBHelper consulateHelper;
	
	private SQLiteDatabase advisoryDB;
	private AdvisoryDBHelper advisoryHelper;

	public DatabaseManager(Context context) {
		consulateHelper = new ConsulateDBHelper(context);
		advisoryHelper = new AdvisoryDBHelper(context);
		open();
	}

	// TODO: add more database helpers
	private void open() throws SQLException {
		consulateDB = consulateHelper.getWritableDatabase();
		advisoryDB = advisoryHelper.getWritableDatabase();
	}

	public void close() {
		consulateHelper.close();
	}
	
	public void insertConsulate(Consulate ins) {
	    ContentValues values = new ContentValues();
	    values.put(ConsulateDBHelper.KEY_COUNTRY, ins.getCountry());
	    values.put(ConsulateDBHelper.KEY_OFFICE, ins.getOfficeID());
	    values.put(ConsulateDBHelper.VALUE_PRIMARY, (ins.getIsPrimary()?1:0));
	    values.put(ConsulateDBHelper.VALUE_PASSPORT, (ins.getHasPassport()?1:0));
	    values.put(ConsulateDBHelper.VALUE_CITY, ins.getCity());
	    values.put(ConsulateDBHelper.VALUE_TYPE, ins.getType());
	    values.put(ConsulateDBHelper.VALUE_LAT, ins.getLatitude());
	    values.put(ConsulateDBHelper.VALUE_LON, ins.getLongitude());
	    values.put(ConsulateDBHelper.VALUE_ADDRESS, ins.getAddress());
	    values.put(ConsulateDBHelper.VALUE_TEL, ins.getTelephone());
	    values.put(ConsulateDBHelper.VALUE_EMERG, ins.getEmergency());
	    values.put(ConsulateDBHelper.VALUE_FAX, ins.getFax());
	    values.put(ConsulateDBHelper.VALUE_EMAIL, ins.getEmail());
	    consulateDB.insert(ConsulateDBHelper.DICTIONARY_TABLE_NAME, null, values);
	}
	
	public ArrayList<Consulate> getConsulates(String country) {
		Cursor c = consulateDB.rawQuery("SELECT * FROM " 
				+ ConsulateDBHelper.DICTIONARY_TABLE_NAME 
				+ " WHERE UPPER(" + ConsulateDBHelper.KEY_COUNTRY 
				+ ") = '" + country.trim().toUpperCase() + "'", null);
		
		ArrayList<Consulate> cons = new ArrayList<Consulate>();
		
		if (!c.moveToFirst()) return cons;
		do
		{
			Consulate curr = new Consulate();
			curr.setOfficeID(cons.size());
			
			int idx = c.getColumnIndex(ConsulateDBHelper.KEY_COUNTRY);
			if (idx >= 0)
				curr.setCountry(c.getString(idx));

			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_CODE);
			if (idx >= 0)
				curr.setCountryCode(c.getString(idx));
			
			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_PRIMARY);
			if (idx >= 0)
				curr.setIsPrimary(c.getInt(idx) == 1 ? true : false);

			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_PASSPORT);
			if (idx >= 0)
				curr.setHasPassport(c.getInt(idx) == 1 ? true : false);

			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_CITY);
			if (idx >= 0)
				curr.setCity(c.getString(idx));

			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_TYPE);
			if (idx >= 0)
				curr.setType(c.getString(idx));
			
			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_LAT);
			if (idx >= 0)
				curr.setLongitude(c.getFloat(idx));

			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_LON);
			if (idx >= 0)
				curr.setLatitude(c.getFloat(idx));

			
			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_ADDRESS);
			if (idx >= 0)
				curr.setAddress(c.getString(idx));

			
			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_TEL);
			if (idx >= 0)
				curr.setTelephone(c.getString(idx));

			
			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_EMERG);
			if (idx >= 0)
				curr.setEmergency(c.getString(idx));

			
			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_FAX);
			if (idx >= 0)
				curr.setFax(c.getString(idx));

			idx = c.getColumnIndex(ConsulateDBHelper.VALUE_EMAIL);
			if (idx >= 0)
				curr.setEmail(c.getString(idx));

			cons.add(curr);
		} while (c.moveToNext());
		c.close();
		
		return cons;
	}

	public Advisory getAdvisory(String country) {
		Cursor c = advisoryDB.rawQuery("SELECT * FROM " 
				+ AdvisoryDBHelper.DICTIONARY_TABLE_NAME 
				+ " WHERE UPPER(" + AdvisoryDBHelper.KEY_COUNTRY 
				+ ") = '" + country.trim().toUpperCase() + "'", null);
		
		if (!c.moveToFirst()) return null;
		
		Advisory curr = new Advisory();

		int idx = c.getColumnIndex(AdvisoryDBHelper.KEY_COUNTRY);
		if (idx >= 0)
			curr.setCountry(c.getString(idx));
		
		idx = c.getColumnIndex(ConsulateDBHelper.VALUE_CODE);
		if (idx >= 0)
			curr.setCountryCode(c.getString(idx));

		idx = c.getColumnIndex(AdvisoryDBHelper.VALUE_DATE);
		if (idx >= 0)
			curr.setDate(c.getString(idx));

		idx = c.getColumnIndex(AdvisoryDBHelper.VALUE_TEXT);
		if (idx >= 0)
			curr.setText(c.getString(idx));
		
		return curr;

	}
}
