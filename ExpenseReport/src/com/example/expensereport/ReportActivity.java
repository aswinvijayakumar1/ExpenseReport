package com.example.expensereport;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ReportActivity extends Activity implements OnClickListener
{
	private EditText fromDate;
	private Calendar mCalen;
	private int day;
	private int month;
	private int year;
	SQLiteDatabase db;

	private static final int DATE_PICKER_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);

		fromDate = (EditText) findViewById(R.id.fromDate);
		mCalen = Calendar.getInstance();
		day = mCalen.get(Calendar.DAY_OF_MONTH);
		month = mCalen.get(Calendar.MONTH);
		year = mCalen.get(Calendar.YEAR);
		fromDate.setOnClickListener(this);

		db = openOrCreateDatabase("ERAPP", MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS Expense(email VARCHAR, cost VARCHAR, date VARCHAR, name VARCHAR, "
				+ "category VARCHAR, type VARCHAR, image VARCHAR");
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) 
	{
		switch (id) 
		{
			case DATE_PICKER_ID:
			return new DatePickerDialog(this, datePickerListener, year, month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() 
	{
		// while dialog box is closed, below method is called.
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) 
		{
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// Set the Date String in Button
			fromDate.setText(day + " / " + (month + 1) + " / " + year);
		}
	};

	@Override
	public void onClick(View v) 
	{
		showDialog(DATE_PICKER_ID);
	}

	public void showdata(View view) 
	{
		Cursor c = db.rawQuery("SELECT * from EXPENSES", null);
		int count = c.getCount();
		c.moveToFirst();
		TableLayout tableLayout = new TableLayout(getApplicationContext());
		tableLayout.setVerticalScrollBarEnabled(true);
		TableRow tableRow;
		TextView textView, textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9;
		tableRow = new TableRow(getApplicationContext());
		textView = new TextView(getApplicationContext());
		textView.setText("Date");
		textView.setTextColor(Color.RED);
		textView.setTypeface(null, Typeface.BOLD);
		textView.setPadding(20, 20, 20, 20);
		tableRow.addView(textView);
		textView4 = new TextView(getApplicationContext());
		textView4.setText("Exp");
		textView4.setTextColor(Color.RED);
		textView4.setTypeface(null, Typeface.BOLD);
		textView4.setPadding(20, 20, 20, 20);
		tableRow.addView(textView4);
		textView5 = new TextView(getApplicationContext());
		textView5.setText("Cat");
		textView5.setTextColor(Color.RED);
		textView5.setTypeface(null, Typeface.BOLD);
		textView5.setPadding(20, 20, 20, 20);
		tableRow.addView(textView5);
		textView6 = new TextView(getApplicationContext());
		textView6.setText("Type");
		textView6.setTextColor(Color.RED);
		textView6.setTypeface(null, Typeface.BOLD);
		textView6.setPadding(20, 20, 20, 20);
		tableRow.addView(textView6);
		textView7 = new TextView(getApplicationContext());
		textView7.setText("Exp");
		textView7.setTextColor(Color.RED);
		textView7.setTypeface(null, Typeface.BOLD);
		textView7.setPadding(20, 20, 20, 20);
		tableRow.addView(textView7);
		tableLayout.addView(tableRow);
		
		for (Integer j = 0; j < count; j++) 
		{
			tableRow = new TableRow(getApplicationContext());
			textView1 = new TextView(getApplicationContext());
			textView1.setText(c.getString(c.getColumnIndex("fname")));
			textView2 = new TextView(getApplicationContext());
			textView2.setText(c.getString(c.getColumnIndex("lname")));
			textView3 = new TextView(getApplicationContext());
			textView3.setText(c.getString(c.getColumnIndex("email")));
			textView1.setPadding(20, 20, 20, 20);
			textView2.setPadding(20, 20, 20, 20);
			textView3.setPadding(20, 20, 20, 20);
			tableRow.addView(textView1);
			tableRow.addView(textView2);
			tableRow.addView(textView3);
			tableLayout.addView(tableRow);
			c.moveToNext();
		}
		setContentView(tableLayout);
		db.close();
	}

	public void close(View view) 
	{
		System.exit(0);
	}
	/*
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) 
	{ 
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ReportActivity, menu); 
		return true; 
	}
	*/
}
