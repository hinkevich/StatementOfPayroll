package by.bsu.statementofpayroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        }

    public void onClickAddEmployee (View view){

        EditText editNameOfEmployee = (EditText) findViewById(R.id.name_of_employee);
        EditText editPositionOfEmployee = (EditText) findViewById(R.id.position_of_employee);
        EditText editSalaryStaffList = (EditText) findViewById(R.id.salary_staff_list);
        EditText editPassportNumber = (EditText) findViewById(R.id.passport_number);
        CheckBox editFullTime = (CheckBox) findViewById(R.id.full_time);
        Spinner editQuantityOfChildren=(Spinner) findViewById(R.id.choose_children_spinner);




        String nameOfEmployee = editNameOfEmployee.getText().toString();
        String positionOfEmployee = editPositionOfEmployee.getText().toString();
        double salaryStaffList = Double.parseDouble(editSalaryStaffList.getText().toString());
        String passportNumber= editPassportNumber.getText().toString();
        boolean fullTime = editFullTime.isChecked();
        //radioGroup
        String chooseChildrenString=editQuantityOfChildren.getSelectedItem().toString();
        int chooseChildren=Integer.parseInt(chooseChildrenString);

        SQLiteOpenHelper dbHelper = new StatementOfPayrollDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();



        int fullTimeInt=0;
        if(fullTime) {fullTimeInt=1;}



        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME_OF_EMPLOYEE", nameOfEmployee);
        contentValues.put("POSITION_OF_EMPLOYEE", positionOfEmployee);
        contentValues.put("SALARY_STAFF_LIST",salaryStaffList);
        contentValues.put("PASSPORT_NUMBER", passportNumber);
        contentValues.put("FULL_TIME", fullTimeInt);
        contentValues.put("QUANTITY_CHILDREN", chooseChildren);
        db.insert("EMPLOYEE", null, contentValues);

        editNameOfEmployee.setText("");
        editPositionOfEmployee.setText("");
        editSalaryStaffList.setText("");
        editPassportNumber.setText("");
        editFullTime.setText("");
        editQuantityOfChildren.setSelection(0,true);




       // db.close();
//        //test
//        TextView test=(TextView) findViewById(R.id.test_employee);
//
//        if(noChild){
//            test.setText("Radio button one");
//
//        }else if(oneChild){
//            test.setText("Radio button two");
//        }else if(twoChild){
//            test.setText("Radio button Three");
//        }else {
//            test.setText("не выбрано");
//        }
//        //test


    }
    public void onBackToVariantOfCalculation(View view){
        Intent intent= new Intent(EmployeeActivity.this, VariantOfCalculationActivity.class);
        startActivity(intent);
    }
}
