package by.bsu.statementofpayroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class MonthAndYearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_and_year);
    }

    public void onClickGoToEmployee(View view) {

        Spinner spinnerYear =(Spinner) findViewById(R.id.choose_year);
        String year=(String) spinnerYear.getSelectedItem();
        int yearInt=Integer.parseInt(year);

        Spinner spinnerMounth =(Spinner) findViewById(R.id.choose_month);
        String mounth=(String) spinnerMounth.getSelectedItem();

        Spinner spinnerDays =(Spinner) findViewById(R.id.choose_days);
        String days=(String) spinnerDays.getSelectedItem();
        int daysInt=Integer.parseInt(days);

        String message=" "+yearInt+" "+mounth+" "+daysInt;




//        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
//        toast.show();


        Intent intent= new Intent(MonthAndYearActivity.this, ChooseEmployeeActivity.class);
        intent.putExtra(ChooseEmployeeActivity.EXXPRESSION_YEAR, yearInt);
        intent.putExtra(ChooseEmployeeActivity.EXXPRESSION_MOUNTH,mounth);
        intent.putExtra(ChooseEmployeeActivity.EXXPRESSION_DAYS,daysInt);
        startActivity(intent);

    }
}
