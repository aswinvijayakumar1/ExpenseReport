package com.example.expensereport;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableRow;

public class MainActivity extends ActionBarActivity {

	SQLiteDatabase db;
	TableRow tableRow;
	EditText email, password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        db=openOrCreateDatabase("ERAPP.db",MODE_PRIVATE, null);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public void login(View view)
	{
		email = (EditText) findViewById (R.id.email);
		password = (EditText) findViewById (R.id.password);
		try {


            Cursor c = db.rawQuery("SELECT 1 AS Output FROM Member WHERE EXISTS (SELECT * FROM Member WHERE email = '"+email.getText()+"' AND password = '"+password.getText()+"')", null);
            c.moveToFirst();
            int output = c.getInt(c.getColumnIndex("Output"));
            Log.println(1, "Output Value: ", "" + output);
            c.close();

            if (output == 1) {
                Intent intent = new Intent(this, ReportActivity.class);
                startActivity(intent);
            } else {
                email.setText("");
                password.setText("");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public void register(View view) 
	{
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
