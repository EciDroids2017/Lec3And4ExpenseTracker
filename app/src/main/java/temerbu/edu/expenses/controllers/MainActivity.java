package temerbu.edu.expenses.controllers;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import temerbu.edu.expenses.ExpenseHandler;
import temerbu.edu.expenses.ExpenseRecyclerAdapter;
import temerbu.edu.expenses.R;
import temerbu.edu.expenses.http.RatesHandler;
import temerbu.edu.expenses.models.ExchangeRate;
import temerbu.edu.expenses.models.Expense;

public class MainActivity extends AppCompatActivity implements RatesHandler.OnRatesArrivedListener, View.OnClickListener {
    Spinner spCurrency;
    EditText etExpense;//Expense Description
    EditText etAmount;
    Button btnAddExpense; // Save the expense
    RecyclerView rvExpenses; //Show the expense List

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        RatesHandler.readRatesAsync(this);

        //quick and dirty: TODO: get the expenses from a server (local storage)
        ArrayList<Expense> data = ExpenseHandler.expenses;

        ExpenseRecyclerAdapter adapter = new ExpenseRecyclerAdapter(this, data);

        rvExpenses.setLayoutManager(new LinearLayoutManager(null));//, LinearLayoutManager.HORIZONTAL, true));
        rvExpenses.setAdapter(adapter);
    }

    private void findViews() {
        etAmount = findViewById(R.id.etAmount);
        rvExpenses = findViewById(R.id.rvExpenses);
        spCurrency = findViewById(R.id.spCurrency);
        etExpense = findViewById(R.id.etExpence);
        btnAddExpense = findViewById(R.id.btnAdd);
        btnAddExpense.setOnClickListener(this);//Make MainActivity Implement
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onClick(View view) {
        //if (view == btnAddExpense){}
       /* switch (view.getId()){
            case R.id.btnAdd:
                break;
        }*/
        //init an expense according to the ui...
        String amt = etAmount.getText().toString();
        String description = etExpense.getText().toString();
        ExchangeRate exchangeRate = (ExchangeRate) spCurrency.getSelectedItem();
        double amount = 0;

        //ctrl alt t
        try {
            amount = Double.valueOf(amt);
        } catch (NumberFormatException e) {
            etAmount.setError("Numbers Only");
            return;
        }

        Expense expense = new Expense(description, amount, exchangeRate);
        //add the expense to the ExpenseHandler ArrayList...

        //1) add the datum to the list.
        ExpenseHandler.expenses.add(0, expense);

        //2) notify the recycler: updateUI ...
        rvExpenses.getAdapter().notifyItemInserted(0);

        //3) optional scroll to the item
        rvExpenses.scrollToPosition(0);

        //4) done adding? clear the ui:
        etExpense.setText(null);
        etAmount.setText(null);
        spCurrency.setSelection(0);
       // Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRatesArrived(ArrayList<ExchangeRate> result) {
        //We can show the coin names in the spinner.
        //spCurrency.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_item, R.id.tvCurrencyName, result));
        result.add(0, new ExchangeRate("ILS", 1));
        spCurrency.setAdapter(new MySpinnerArrayAdapter(this, result));
    }

    class MySpinnerArrayAdapter extends ArrayAdapter<ExchangeRate> {
        private ArrayList<ExchangeRate> data;

        public MySpinnerArrayAdapter(Context context, ArrayList<ExchangeRate> data) {
            super(context, R.layout.spinner_item);
            this.data = data;
        }

        @Nullable
        @Override
        public ExchangeRate getItem(int position) {
            return data.get(position);
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }


//
//
//
//
//    public String TAG = "iTomerBu";
//    String baseUrl = "https://expenses-c4c51.firebaseio.com/";
//    public static final MediaType JSON
//            = MediaType.parse("application/json; charset=utf-8");
//

}





