package by.bsu.statementofpayroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseEmployeeActivity extends AppCompatActivity {

    public static final String EXXPRESSION_YEAR = "year";
    public static final String EXXPRESSION_MOUNTH = "mouth";
    public static final String EXXPRESSION_DAYS = "days";
    private static int yearInt,daysInt;
    private static String mounth="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_employee);

        Intent intent=getIntent();
        yearInt=intent.getIntExtra(EXXPRESSION_YEAR,0);
        daysInt=intent.getIntExtra(EXXPRESSION_DAYS,0);
        mounth=intent.getStringExtra(EXXPRESSION_MOUNTH);


//        Toast toastOne = Toast.makeText(this, ""+yearInt+mounth+daysInt, Toast.LENGTH_SHORT);
////       toastOne.show();





        ListView listViewEmployee = (ListView) findViewById(R.id.choose_employee_list_view);

        SQLiteOpenHelper dbHelper = new StatementOfPayrollDatabaseHelper(this);
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.query("EMPLOYEE",
                    new String[] {"_id","NAME_OF_EMPLOYEE"},
                    null,null,null, null,null);

            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[] {"NAME_OF_EMPLOYEE"},
                    new int[] {android.R.id.text1},
                    0);
            listViewEmployee.setAdapter(listAdapter);
        }

        catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?>listViewEmployee ,
                                            View itemView,
                                            int position,
                                            long id) {
        //Сотрудник, выбранный пользователем
                        Intent intent = new Intent(ChooseEmployeeActivity.this,
                                FillEmployeeActivity.class);

                        intent.putExtra(FillEmployeeActivity.EXTRA_EMPLOYEE_ID, (int) id);
                        intent.putExtra(FillEmployeeActivity.EXXPRESSION_YEAR, yearInt);
                        intent.putExtra(FillEmployeeActivity.EXXPRESSION_MOUNTH,mounth);
                        intent.putExtra(FillEmployeeActivity.EXXPRESSION_DAYS,daysInt);


                        startActivity(intent);
                    }
                };
        listViewEmployee.setOnItemClickListener(itemClickListener);


    }

    public void onBackToMonthAndYear(View view){

        Intent intent= new Intent(ChooseEmployeeActivity.this, MonthAndYearActivity.class);
        startActivity(intent);
    }
    public void onClickGoToSelectionResult(View view) {
        Intent intent =new Intent(ChooseEmployeeActivity.this,SelectionOfDisplayOfResultsActivity.class);


        startActivity(intent);
    }
}
