package by.bsu.statementofpayroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FillEmployeeActivity extends AppCompatActivity {

    public static final String EXXPRESSION_YEAR = "year";
    public static final String EXXPRESSION_MOUNTH = "mouth";
    public static final String EXXPRESSION_DAYS = "days";
    int yearInt,daysInt;
     String mounth="";

    public static final String EXTRA_EMPLOYEE_ID = "EmployeeId";
    String nameOfEmployee="";
    String positionOfEmployee;
    double salaryStaffList;
    int fullTime;
    int quantityChildren;
    boolean chekEmployee=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_employee);

        //получение данных из других активностей способ 1
        int employeeId = (Integer) getIntent().getExtras().get(EXTRA_EMPLOYEE_ID);

        //получение данных из других активностей способ 2
        Intent intent=getIntent();
        yearInt=intent.getIntExtra(EXXPRESSION_YEAR,0);
        daysInt=intent.getIntExtra(EXXPRESSION_DAYS,0);
        mounth=intent.getStringExtra(EXXPRESSION_MOUNTH);

        //создание помошника и чтение базы

        SQLiteOpenHelper dataBase = new StatementOfPayrollDatabaseHelper(this);

        try {
            SQLiteDatabase db = dataBase.getReadableDatabase();
            Cursor cursor = db.query("EMPLOYEE",
                    new String[]{"_id", "NAME_OF_EMPLOYEE", "POSITION_OF_EMPLOYEE",
                            "SALARY_STAFF_LIST", "FULL_TIME", "QUANTITY_CHILDREN"},
                    "_id = ?",
                    new String[]{Integer.toString(employeeId)},
                    null, null, null);
            // if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            nameOfEmployee = cursor.getString(1);
            positionOfEmployee = cursor.getString(2);
            salaryStaffList = cursor.getDouble(3);
            fullTime = cursor.getInt(4);
            quantityChildren = cursor.getInt(5);
            TextView textViewnameOfEmployee = (TextView) findViewById(R.id.fill_name_of_employee);
            textViewnameOfEmployee.setText(nameOfEmployee);
            TextView textViewPositionOnEployee = (TextView) findViewById(R.id.fill_position_of_employee);
            textViewPositionOnEployee.setText(positionOfEmployee);
            //}
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onBackToEmployee(View view) {
        Intent intent = new Intent(FillEmployeeActivity.this, ChooseEmployeeActivity.class);
        startActivity(intent);
    }


    public void onAddEmployeeToPayroll(View view) {

        EditText editFullFulfilledDays = (EditText) findViewById(R.id.fill_fulfilled_days);
        EditText editBonus = (EditText) findViewById(R.id.fill_bonus);
        EditText editVocationPay = (EditText) findViewById(R.id.fill_vacation_pay);
        EditText editSickList = (EditText) findViewById(R.id.fill_sick_list);

        //перем которые нужно получить из активити месяца и года
        int daysPerMounth = daysInt;
        String monthPayroll = mounth;
        int yearPayroll = yearInt;

        int fullFulfilledDays;
        if (editFullFulfilledDays.getText().toString().trim().length()==0){
            fullFulfilledDays=0;
        }else {
             fullFulfilledDays = Integer.parseInt(editFullFulfilledDays.getText().toString());
        }


        double bonus;
        if (editBonus.getText().toString().trim().length()==0){
            bonus=0;
        }else {
            bonus = Double.parseDouble(editBonus.getText().toString());
        }
        double vocationPay;
        if (editVocationPay.getText().toString().trim().length()==0){
            vocationPay=0;
        }else {
            vocationPay = Double.parseDouble(editVocationPay.getText().toString());
        }

        double sickList;
        if (editSickList.getText().toString().trim().length()==0){
            sickList=0;
        }else {
            sickList = sickList = Double.parseDouble(editSickList.getText().toString());}

        SQLiteOpenHelper sqLiteOpenHelper =new StatementOfPayrollDatabaseHelper(this);

        //Проверяем есть ли в ведомости этот сотрудник за этот месяц
        try {
            SQLiteDatabase database =sqLiteOpenHelper.getReadableDatabase();
            Cursor cursorTest = database.query("PAYROLL",
                    new String[]{"_id", "NAME_OF_EMPLOYEE_PAYROLL", "MONTH_PAYROLL","YEAR_PAYROLL"},
                    "NAME_OF_EMPLOYEE_PAYROLL=? AND MONTH_PAYROLL=? AND YEAR_PAYROLL=?",
                    new String[]{nameOfEmployee, mounth, Integer.toString(yearInt)},
                    null, null, null);
            if (cursorTest.moveToFirst()){
                Toast toastTest = Toast.makeText(this,
                        "Такой сотрудник уже есть в ведомости за указанный период",
                        Toast.LENGTH_SHORT);
                toastTest.show();


            }else {
                chekEmployee=true;
            }

            cursorTest.close();
            database.close();


        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }

        if (chekEmployee) {


            double chargedStaffList = PayrollAction.accruedBySalary(salaryStaffList, daysPerMounth, fullFulfilledDays);
            double totalCharged = PayrollAction.totalCharged(chargedStaffList, bonus, vocationPay, sickList);
            double incomeTax = PayrollAction.incomeTax(totalCharged, fullTime, quantityChildren);
            double pensionFund = PayrollAction.pensionFund(totalCharged, sickList);
            double totalWitheld = PayrollAction.totaWithheld(incomeTax, pensionFund);
            double toIssue = PayrollAction.toIssuePayroll(totalCharged, totalWitheld);

            //SQLiteOpenHelper sqLiteOpenHelper=new StatementOfPayrollDatabaseHelper(this);
            SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME_OF_EMPLOYEE_PAYROLL", nameOfEmployee);
            contentValues.put("POSITION_OF_EMPLOYEE_PAYROLL", positionOfEmployee);
            contentValues.put("SALARY_STAFF_LIST_PAYROLL", salaryStaffList);
            contentValues.put("FULFILLED_DAYS_PAYROLL", fullFulfilledDays);
            contentValues.put("CHARGED_STAFF_LIST_PAYROLL", chargedStaffList);
            contentValues.put("BONUS_PAYROLL", bonus);
            contentValues.put("VOCATION_PAYROLL", vocationPay);
            contentValues.put("SICK_LIST_PAYROLL", sickList);
            contentValues.put("TOTAL_CHARGED_PAYROLL", totalCharged);
            contentValues.put("INCOME_TAX_PAYROLL", incomeTax);
            contentValues.put("PENSION_FUND_PAYROLL", pensionFund);
            contentValues.put("TO_ISSUE_PAYROLL", toIssue);
            contentValues.put("TOTAL_WITHHELD_PAYROLL", totalWitheld);
            contentValues.put("MONTH_PAYROLL", monthPayroll);
            contentValues.put("YEAR_PAYROLL", yearPayroll);

            db.insert("PAYROLL", null, contentValues);
        }

        editFullFulfilledDays.setText("");
        editBonus.setText("");
        editVocationPay.setText("");
        editSickList.setText("");
    }
    }

