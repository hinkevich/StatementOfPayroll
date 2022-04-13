package by.bsu.statementofpayroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CompanyActivity extends AppCompatActivity {
    boolean checkNameCompany=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
    }

        public void onSendMessage(View view) {
            Intent intent= new Intent(CompanyActivity.this, MainActivity.class);
            startActivity(intent);
        }

        public void onClickAddCompany(View view) {
            EditText editCompany = (EditText) findViewById(R.id.name_of_company);
            EditText editDirector = (EditText) findViewById(R.id.surname_director);
            EditText editAccountant = (EditText) findViewById(R.id.surname_chief_accountant);

            String company = editCompany.getText().toString();
            String director = editDirector.getText().toString();
            String accountant = editAccountant.getText().toString();

            SQLiteOpenHelper dbHelper = new StatementOfPayrollDatabaseHelper(this);

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("INFO",
                    new String[]{"_id", "COMPANY"},
                    null,
                    null,
                    null, null, null);
            int check=0;
            String companyTest=company.trim();
            String companyDataBase;
            if (cursor.moveToFirst()) {
               // String companyDataBase;
                // check=0;
                do {
                    companyDataBase=cursor.getString(1).trim();

                    if (companyTest.equalsIgnoreCase(companyDataBase)){
                        check=check+1;
                    }
//                    Toast toastTest = Toast.makeText(this,
//                            companyTest+"  "+companyDataBase+"  "+test+"  "+check,
//                            Toast.LENGTH_SHORT);
//                    toastTest.show();


                }while (cursor.moveToNext());

            } else {
                checkNameCompany = true;
            }
            cursor.close();
            db.close();
            if (check==0){
                checkNameCompany=true;

            }else {checkNameCompany=false;}


            if (checkNameCompany) {

                db = dbHelper.getWritableDatabase();ContentValues contentValues = new ContentValues();
                contentValues.put("COMPANY", company);
                contentValues.put("DIRECTOR", director);
                contentValues.put("ACCOUNTANT", accountant);
                db.insert("INFO", null, contentValues);
            }else {
                Toast toastTest = Toast.makeText(this,
                        "Такая компания уже существует!",
                        Toast.LENGTH_SHORT);
                toastTest.show();
            }




            editCompany.setText("");
            editDirector.setText("");
            editAccountant.setText("");
        }

    }
