package by.bsu.statementofpayroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class SelectionOfDisplayOfResultsActivity extends AppCompatActivity {
    private String mounth="";
    private String year="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_of_display_of_results);

    }

    public void onClickGoToResultOne(View view) {
        Intent intent=new Intent(SelectionOfDisplayOfResultsActivity.this,
                ResultDisplayActivity.class);
        Spinner spinnerYear =(Spinner) findViewById(R.id.selection_spinner_year);
        String year=(String) spinnerYear.getSelectedItem();

        Spinner spinnerMounth =(Spinner) findViewById(R.id.selection_spinner_mounth);
        String mounth=(String) spinnerMounth.getSelectedItem();

        intent.putExtra(ResultDisplayActivity.EXXPRESSION_YEAR, year);
        intent.putExtra(ResultDisplayActivity.EXXPRESSION_MOUNTH,mounth);
        startActivity(intent);

    }
}
