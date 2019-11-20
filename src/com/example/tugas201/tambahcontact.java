package com.example.tugas201;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class tambahcontact extends Activity {

	private String TAG = tambahcontact.class.getSimpleName();
    private ProgressDialog pDialog;
    
    private static String url = "http://apilearning.totopeto.com/contacts";
	
	Button btnsimpan;
	EditText ename, eaddress, eemail, ephone, edob;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tambah_contact);
		
		btnsimpan = (Button) findViewById(R.id.btnsimpan);
		ename = (EditText) findViewById(R.id.etnama);
		eaddress = (EditText) findViewById(R.id.etalamat);
		eemail = (EditText) findViewById(R.id.etemail);
		ephone = (EditText) findViewById(R.id.ethp);
		edob = (EditText) findViewById(R.id.etdob);
		
		btnsimpan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AddContact().execute();
				Intent intent = new Intent(tambahcontact.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private class AddContact extends AsyncTask<Void, Void, Void> {
		
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(tambahcontact.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        
        @Override
        protected Void doInBackground(Void... arg0) {
            String post_params = null;
            JSONObject params = new JSONObject();
 
            try {
            	params.put("name", ename.getText().toString());
            	params.put("address", eaddress.getText().toString());
            	params.put("email", eemail.getText().toString());
            	params.put("phone", ephone.getText().toString());
            	params.put("dob", edob.getText().toString());
            	post_params = params.toString();
            	
            } catch (JSONException e) {
            	e.printStackTrace();
            }
            
            HttpHandler data = new HttpHandler();
            String jsonStr = data.makePostRequest(url, post_params);
            Log.e(TAG, "Response from url: " + jsonStr);
            
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
        }
	}
}