package by.bsu.statementofpayroll;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ResultDisplayActivity extends AppCompatActivity {
    public static final String EXXPRESSION_MOUNTH ="mounth" ;
    public static final String EXXPRESSION_YEAR ="year" ;
    private String mounth="September";
    private String year="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);
        Intent intent=getIntent();
        year=intent.getStringExtra(EXXPRESSION_YEAR);
       mounth=intent.getStringExtra(EXXPRESSION_MOUNTH);
       double summaChargeStaff=0;
       double summaBonus=0;
       double summaVocation=0;
       double summaSick=0;
       double summaTotalCharge=0;
       double summaIncomeTax=0;
       double summaPensionFound=0;
       double summatotalWithheld=0;
       double summaToIssue=0;

        Toast toastOne = Toast.makeText(this, mounth, Toast.LENGTH_SHORT);
        toastOne.show();
        TextView textViewInfo = (TextView) findViewById(R.id.name_of_payroll);
        textViewInfo.setText("Ведомость расчета ЗП за "+mounth+ " "+year+" года");

        //mounth="September";


        //  ListView listViewResult=(ListView)findViewById(R.id.result_list_view);
        SQLiteOpenHelper dbHelper = new StatementOfPayrollDatabaseHelper(this);
        try {
            SQLiteDatabase database = dbHelper.getReadableDatabase();
//
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("NAME_OF_EMPLOYEE_PAYROLL", name_of_employee_payroll);
//            contentValues.put("POSITION_OF_EMPLOYEE_PAYROLL", position_of_employee_payroll);
//            contentValues.put("SALARY_STAFF_LIST_PAYROLL", salary_staff_list_payroll);
//            contentValues.put("FULFILLED_DAYS_PAYROLL", fulfilled_days_payroll);
//            contentValues.put("CHARGED_STAFF_LIST_PAYROLL", charged_staff_list_payroll);
//            contentValues.put("BONUS_PAYROLL", bonus_payroll);
//            contentValues.put("VOCATION_PAYROLL", vocation_payroll);
//            contentValues.put("SICK_LIST_PAYROLL", sick_list_payroll);
//            contentValues.put("TOTAL_CHARGED_PAYROLL", total_charged_payroll);
//            contentValues.put("INCOME_TAX_PAYROLL", income_tax_payroll);
//            contentValues.put("PENSION_FUND_PAYROLL", pension_fund_payroll);
//            contentValues.put("TO_ISSUE_PAYROLL", to_issue_payroll);
//            contentValues.put("TOTAL_WITHHELD_PAYROLL", total_withheld_payroll);
//            contentValues.put("MONTH_PAYROLL", month_payroll);
//            contentValues.put("YEAR_PAYROLL", year_payroll);


            Cursor cursor = database.query("PAYROLL",
                    new String[]{"_id", "NAME_OF_EMPLOYEE_PAYROLL", "POSITION_OF_EMPLOYEE_PAYROLL ",
                            "SALARY_STAFF_LIST_PAYROLL","FULFILLED_DAYS_PAYROLL",
                            "CHARGED_STAFF_LIST_PAYROLL", "BONUS_PAYROLL", "VOCATION_PAYROLL",
                            "SICK_LIST_PAYROLL", "TOTAL_CHARGED_PAYROLL","INCOME_TAX_PAYROLL",
                            "PENSION_FUND_PAYROLL", "TOTAL_WITHHELD_PAYROLL", "TO_ISSUE_PAYROLL",
                            "MONTH_PAYROLL","YEAR_PAYROLL"},

                    "MONTH_PAYROLL=? AND YEAR_PAYROLL=?",new String[]{mounth,"2019"}, null, null, null);

            TableLayout tableLayout = (TableLayout) findViewById(R.id.table_result);


            TableRow tableDataHeader = new TableRow(this);
            TextView nameTVHeader = new TextView(this);
            nameTVHeader.setText("| "+"ФИО сотрудника"+" ");
            tableDataHeader.addView(nameTVHeader);

            TextView positionTVHeader = new TextView(this);
            positionTVHeader.setText("| "+"Должность"+" ");
            tableDataHeader.addView(positionTVHeader);

            TextView salaryTVHeader = new TextView(this);
            salaryTVHeader.setText("| "+"Оклад"+" ");
            tableDataHeader.addView(salaryTVHeader);

            TextView fulfilledTVHeader = new TextView(this);
            fulfilledTVHeader.setText("| "+"Отработано дней"+" ");
            tableDataHeader.addView(fulfilledTVHeader);

            TextView chargedTVHeader = new TextView(this);
            chargedTVHeader.setText("| "+"Начисл. по окладу"+" ");
            tableDataHeader.addView(chargedTVHeader);

            TextView bonusTVHeader = new TextView(this);
            bonusTVHeader.setText("| "+"Премия"+" ");
            tableDataHeader.addView(bonusTVHeader);

            TextView vocationTVHeader = new TextView(this);
            vocationTVHeader.setText("| "+"Отпускные"+" ");
            tableDataHeader.addView(vocationTVHeader);

            TextView sickListTVHeader = new TextView(this);
            sickListTVHeader.setText("| "+"Больничный"+" ");
            tableDataHeader.addView(sickListTVHeader);

            TextView totalChargedTVHeader = new TextView(this);
            totalChargedTVHeader.setText("| "+"ВСЕГО начислено"+" ");
            tableDataHeader.addView(totalChargedTVHeader);

            TextView incomeTVHeader = new TextView(this);
            incomeTVHeader.setText("| "+"Подоходный"+" ");
            tableDataHeader.addView(incomeTVHeader);

            TextView pensionTVHeader = new TextView(this);
            pensionTVHeader.setText("| "+"Пенсионный"+" ");
            tableDataHeader.addView(pensionTVHeader);

            TextView toIssueTVHeader = new TextView(this);
            toIssueTVHeader.setText("| "+"ВСЕГО удержано"+" ");
            tableDataHeader.addView(toIssueTVHeader);

            TextView totalWithheldTVHeader = new TextView(this);
            totalWithheldTVHeader.setText("| "+"К ВЫДАЧЕ"+" ");
            tableDataHeader.addView(totalWithheldTVHeader);



            tableLayout.addView(tableDataHeader);




            cursor.moveToFirst();
            //название столбцов


            int i=0;
            do {
            TableRow tableData = new TableRow(this);


            String nameOfEmployee = cursor.getString(1);
            TextView nameTV = new TextView(this);
            nameTV.setText("| "+nameOfEmployee+" ");
            tableData.addView(nameTV, 0);

            String positionOfEmployee = cursor.getString(2);
            TextView positionTV = new TextView(this);
            positionTV.setText("| "+positionOfEmployee+" ");
            tableData.addView(positionTV, 1);

            double salaryStaffList=cursor.getDouble(3);
            TextView salaryTV = new TextView(this);
            salaryTV.setText("| "+salaryStaffList +" ");
            tableData.addView(salaryTV , 2);

             double fulfilledDays=cursor.getDouble(4);
             TextView fulfilledTV = new TextView(this);
             fulfilledTV.setText("| "+fulfilledDays +" ");
             tableData.addView(fulfilledTV , 3);

             double chargedStaffList=cursor.getDouble(5);
             TextView chargedTV = new TextView(this);
             chargedTV.setText("| "+chargedStaffList +" ");
             tableData.addView(chargedTV , 4);
             summaChargeStaff=summaChargeStaff+chargedStaffList;
             summaChargeStaff=PayrollAction.convertMoney(summaChargeStaff);

             double bonus=cursor.getDouble(6);
             TextView bonusTV = new TextView(this);
             bonusTV.setText("| "+bonus +" ");
             tableData.addView(bonusTV , 5);
             summaBonus=summaBonus+bonus;
             summaBonus=PayrollAction.convertMoney(bonus);

             double vocation=cursor.getDouble(7);
             TextView vocationTV = new TextView(this);
             vocationTV.setText("| "+vocation +" ");
             tableData.addView(vocationTV , 6);
             summaVocation=summaVocation+vocation;
             summaVocation=PayrollAction.convertMoney(summaVocation);

             double sickList=cursor.getDouble(8);
             TextView sickListTV = new TextView(this);
             sickListTV.setText("| "+sickList +" ");
             tableData.addView(sickListTV , 7);
             summaSick=summaSick+sickList;
             summaSick=PayrollAction.convertMoney(summaSick);

             double totalCharged=cursor.getDouble(9);
             TextView totalChargedTV = new TextView(this);
             totalChargedTV.setText("| "+totalCharged+" ");
             tableData.addView(totalChargedTV , 8);
             summaTotalCharge=summaTotalCharge+totalCharged;
             summaTotalCharge=PayrollAction.convertMoney(summaTotalCharge);

             double incomeTax=cursor.getDouble(10);
             TextView incomeTaxTV = new TextView(this);
             incomeTaxTV.setText("| "+incomeTax +" ");
             tableData.addView(incomeTaxTV , 9);
             summaIncomeTax=summaIncomeTax+incomeTax;
             summaIncomeTax=PayrollAction.convertMoney(summaIncomeTax);

             double pensionFund=cursor.getDouble(11);
             TextView pensionFundTV = new TextView(this);
             pensionFundTV.setText("| "+pensionFund+" ");
             tableData.addView(pensionFundTV , 10);
             summaPensionFound=summaPensionFound+pensionFund;
             summaPensionFound=PayrollAction.convertMoney(summaPensionFound);

             double toIssue=cursor.getDouble(12);
             TextView toIssueTV = new TextView(this);
             toIssueTV.setText("| "+toIssue+" ");
             tableData.addView(toIssueTV , 11);
             summaToIssue=summaToIssue+toIssue;
             summaToIssue=PayrollAction.convertMoney(summaToIssue);

             double totalWithheld =cursor.getDouble(13);
             TextView totalWithheldTV = new TextView(this);
             totalWithheldTV.setText("| "+totalWithheld +" ");
             tableData.addView(totalWithheldTV , 12);
             summatotalWithheld=summatotalWithheld+totalWithheld;
             summatotalWithheld=PayrollAction.convertMoney(summatotalWithheld);

             String month =cursor.getString(14);
             TextView monthTV = new TextView(this);
             monthTV.setText("| "+month+" ");
             tableData.addView(monthTV , 13);

            tableLayout.addView(tableData);
            i++;

            }while(cursor.moveToNext());

           // ИТОГО

            TableRow tableSum = new TableRow(this);
            tableSum.setBackgroundColor(getResources().getColor(R.color.colorTable));

            TextView TVOne = new TextView(this);
            TVOne.setText(" " );
            tableSum.addView(TVOne , 0);

            TextView TVTwo = new TextView(this);
            TVTwo.setText(" " );
            tableSum.addView(TVTwo , 1);

            TextView TVThree = new TextView(this);
            TVThree.setText(" " );
            tableSum.addView(TVThree , 2);

            TextView TVFour = new TextView(this);
            TVFour.setText(" ИТОГО" );
            tableSum.addView(TVFour , 3);



            TextView summChargedTV = new TextView(this);
            summChargedTV.setText("| "+summaChargeStaff +" ");
            tableSum.addView(summChargedTV , 4);


            TextView bonusTV = new TextView(this);
            bonusTV.setText("| "+summaBonus +" ");
            tableSum.addView(bonusTV , 5);


            TextView vocationTV = new TextView(this);
            vocationTV.setText("| "+summaVocation +" ");
            tableSum.addView(vocationTV , 6);


            TextView sickListTV = new TextView(this);
            sickListTV.setText("| "+summaSick +" ");
            tableSum.addView(sickListTV , 7);


            TextView totalChargedTV = new TextView(this);
            totalChargedTV.setText("| "+summaTotalCharge+" ");
            tableSum.addView(totalChargedTV , 8);


            TextView incomeTaxTV = new TextView(this);
            incomeTaxTV.setText("| "+summaIncomeTax +" ");
            tableSum.addView(incomeTaxTV , 9);



            TextView pensionFundTV = new TextView(this);
            pensionFundTV.setText("| "+summaPensionFound+" ");
            tableSum.addView(pensionFundTV , 10);


            TextView toIssueTV = new TextView(this);
            toIssueTV.setText("| "+summaToIssue+" ");
            tableSum.addView(toIssueTV , 11);


            TextView totalWithheldTV = new TextView(this);
            totalWithheldTV.setText("| "+summatotalWithheld +" ");
            tableSum.addView(totalWithheldTV , 12);

            tableLayout.addView(tableSum);


            TextView paybudget =(TextView) findViewById(R.id.text_transferred_to_the_budget);
            paybudget.setText( PayrollAction.transferredToBudget(summaTotalCharge,summaIncomeTax));


        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}