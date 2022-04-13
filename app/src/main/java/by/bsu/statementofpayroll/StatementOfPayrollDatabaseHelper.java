package by.bsu.statementofpayroll;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class StatementOfPayrollDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "StatementOfPayroll";
    private static final int DB_VERSION = 1;

    StatementOfPayrollDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE INFO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "COMPANY TEXT, "
                + "DIRECTOR TEXT, "
                + "ACCOUNTANT TEXT);");

        db.execSQL("CREATE TABLE EMPLOYEE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME_OF_EMPLOYEE TEXT, "
                + "POSITION_OF_EMPLOYEE TEXT, "
                + "SALARY_STAFF_LIST REAL,"
                + "PASSPORT_NUMBER TEXT,"
                + "FULL_TIME INTEGER,"
                + "QUANTITY_CHILDREN INTEGER); ");

        db.execSQL("CREATE TABLE PAYROLL (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME_OF_EMPLOYEE_PAYROLL TEXT, "
                + "POSITION_OF_EMPLOYEE_PAYROLL TEXT, "
                + "SALARY_STAFF_LIST_PAYROLL REAL,"
                + "FULFILLED_DAYS_PAYROLL REAL,"
                + "CHARGED_STAFF_LIST_PAYROLL REAL,"
                + "BONUS_PAYROLL REAL,"
                + "VOCATION_PAYROLL REAL,"
                + "SICK_LIST_PAYROLL REAL,"
                + "TOTAL_CHARGED_PAYROLL REAL,"
                + "INCOME_TAX_PAYROLL REAL,"
                + "PENSION_FUND_PAYROLL REAL,"
                + "TOTAL_WITHHELD_PAYROLL REAL,"
                + "TO_ISSUE_PAYROLL  REAL,"
                + "MONTH_PAYROLL TEXT,"
                + "YEAR_PAYROLL INTEGER); ");

//        ContentValues contentValues = new ContentValues();
//        contentValues.put("NAME_OF_EMPLOYEE","petrov");
//        contentValues.put("POSITION_OF_EMPLOYEE","dir");
//        contentValues.put("SALARY_STAFF_LIST",400.00);
//        contentValues.put("PASSPORT_NUMBER","bm");
//        contentValues.put("FULL_TIME",1);
//        contentValues.put("QUANTITY_CHILDREN",1);
//        db.insert("EMPLOYEE",null,contentValues);

    }

    private void insertINFO(SQLiteDatabase info, String company, String director, String accountant) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("COMPANY", company);
        contentValues.put("DIRECTOR", director);
        contentValues.put("ACCOUNTANT", accountant);
        info.insert("INFO", null, contentValues);
    }
    private void insertEmployee(SQLiteDatabase employee, String name_of_employee,
                                String position_of_employee, double salary_staff_list,
                                String passport_number, boolean  full_time, int quantity_children){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME_OF_EMPLOYEE", name_of_employee);
        contentValues.put("POSITION_OF_EMPLOYEE", position_of_employee);
        contentValues.put("SALARY_STAFF_LIST", salary_staff_list);
        contentValues.put("PASSPORT_NUMBER", passport_number);
        contentValues.put("FULL_TIME", full_time);
        contentValues.put("QUANTITY_CHILDREN", quantity_children);
    }


    private void insertPayroll(SQLiteDatabase payroll, String name_of_employee_payroll,
                                String position_of_employee_payroll, double salary_staff_list_payroll,
                                double fulfilled_days_payroll, double charged_staff_list_payroll,
                                double bonus_payroll, double vocation_payroll, double sick_list_payroll,
                                double total_charged_payroll, double income_tax_payroll,
                                double pension_fund_payroll, double total_withheld_payroll,
                                double to_issue_payroll, String month_payroll, int year_payroll){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME_OF_EMPLOYEE_PAYROLL", name_of_employee_payroll);
        contentValues.put("POSITION_OF_EMPLOYEE_PAYROLL", position_of_employee_payroll);
        contentValues.put("SALARY_STAFF_LIST_PAYROLL", salary_staff_list_payroll);
        contentValues.put("FULFILLED_DAYS_PAYROLL", fulfilled_days_payroll);
        contentValues.put("CHARGED_STAFF_LIST_PAYROLL", charged_staff_list_payroll);
        contentValues.put("BONUS_PAYROLL", bonus_payroll);
        contentValues.put("VOCATION_PAYROLL", vocation_payroll);
        contentValues.put("SICK_LIST_PAYROLL", sick_list_payroll);
        contentValues.put("TOTAL_CHARGED_PAYROLL", total_charged_payroll);
        contentValues.put("INCOME_TAX_PAYROLL", income_tax_payroll);
        contentValues.put("PENSION_FUND_PAYROLL", pension_fund_payroll);
        contentValues.put("TO_ISSUE_PAYROLL", to_issue_payroll);
        contentValues.put("TOTAL_WITHHELD_PAYROLL", total_withheld_payroll);
        contentValues.put("MONTH_PAYROLL", month_payroll);
        contentValues.put("YEAR_PAYROLL", year_payroll);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}