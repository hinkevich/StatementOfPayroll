package by.bsu.statementofpayroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VariantOfCalculationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variant_of_calculation);
    }
    public void onAddEmployee(View view) {
        Intent intent= new Intent(VariantOfCalculationActivity.this, EmployeeActivity.class);
        startActivity(intent);
    }
    public void onGoToChooseMonthAndYear(View view) {
        Intent intent= new Intent(VariantOfCalculationActivity.this, MonthAndYearActivity.class);
        startActivity(intent);
    }
}
