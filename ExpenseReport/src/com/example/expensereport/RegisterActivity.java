package com.example.expensereport;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;

public class RegisterActivity extends ActionBarActivity {

	SQLiteDatabase db;
	TableRow tableRow;
	EditText email, password, name, type, code;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		
		db=openOrCreateDatabase("ERAPP.db",MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Member(email VARCHAR, password VARCHAR, name VARCHAR, type VARCHAR, code VARCHAR);");
	}
	
	public void getData(View view)
	{
		email = (EditText) findViewById (R.id.email);
		password = (EditText) findViewById (R.id.name);
		name = (EditText) findViewById (R.id.password);
		type = (EditText) findViewById (R.id.type);
		code = (EditText) findViewById (R.id.companyCode);
		db.execSQL("INSERT INTO Member VALUES('"+email.getText()+"','"+password.getText()+"','"+name.getText()+"','"+type.getText()+"','"+code.getText()+"');");

		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}