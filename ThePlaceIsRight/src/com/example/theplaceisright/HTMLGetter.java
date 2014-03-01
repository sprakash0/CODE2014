package com.example.theplaceisright;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;


import android.os.AsyncTask;
import android.util.Log;

public class HTMLGetter extends AsyncTask<String,Void,String> {

	@Override
    protected String doInBackground(String... url) {
		String resString = null;
		try {
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters,3000); // 3s max for connection
			HttpConnectionParams.setSoTimeout(httpParameters, 4000); // 4s max to get data
			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpGet httpget = new HttpGet(url[0]);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent(); // Create an InputStream with the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) // Read line by line
			    sb.append(line + "\n");

			resString = sb.toString(); // Result is here

			is.close(); // Close the stream
		} catch (Exception e) {
			Log.e(ContinentHTMLParser.class.getName(),e.getMessage());
		}

		return resString;
    }
	
	  @Override
	  protected void onPostExecute(String result) {
	   // execution of result of Long time consuming operation
		  ContinentHTMLParser.parse(result);
	  }
}
