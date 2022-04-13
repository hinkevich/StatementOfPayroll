package by.bsu.statementofpayroll;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.choose_the_company);
        SQLiteOpenHelper dbHelper = new StatementOfPayrollDatabaseHelper(this);
        try {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("INFO",
                new String[] {"_id","COMPANY"},
                null,null,null, null,null);

            SimpleCursorAdapter spinnerAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_spinner_item,
                    cursor,
                    new String[] {"COMPANY"},
                    new int[] {android.R.id.text1},
                    0);
            spinner.setAdapter(spinnerAdapter);
        }

        catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onSendCompany(View view) {
        Intent intent= new Intent(MainActivity.this, CompanyActivity.class);
        startActivity(intent);
    }
    public void onSendVariantOfCalculation(View view) {
        Intent intent= new Intent(MainActivity.this, VariantOfCalculationActivity.class);
        startActivity(intent);
    }

   // @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        SQLiteOpenHelper dbHelper = new StatementOfPayrollDatabaseHelper(this);
//
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//            Cursor cursor = db.query("INFO",
//                    new String[] {"_id","COMPANY"},
//                    null,null,null, null,null);
//            cursor.close();
//            db.close();
//
//
//    }
}