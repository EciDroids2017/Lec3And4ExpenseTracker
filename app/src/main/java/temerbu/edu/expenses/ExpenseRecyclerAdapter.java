package temerbu.edu.expenses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import temerbu.edu.expenses.models.Expense;

public class ExpenseRecyclerAdapter extends RecyclerView.Adapter<ExpenseRecyclerAdapter.ExpenseViewHolder> {
    //Fields:
    //data
    private ArrayList<Expense> data;
    //context: Activity extends Context
    //context: access to resources, database, Layout Files, Access to System Service
    private Context context;
    //Constructor: (alt + insert)
    public ExpenseRecyclerAdapter(Context context, ArrayList<Expense> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //get a reference to the Layout Inflater
        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.expense_item, parent, false);// ... //We have an XML item...
        return new ExpenseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder v, int position) {
        Expense expense = data.get(position);

        v.tvDescription.setText(expense.getDescription());
        v.tvAmount.setText(String.valueOf(expense.getAmount()));
        v.tvDate.setText(expense.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    //3 times new expense_item

    //when scrolling and when starting -> data binding?
    //present the data in the textViews...

    //hold the result of findViewByID as fields.
    //Find view By ID...

    class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescription;
        TextView tvAmount;
        TextView tvDate;

        public ExpenseViewHolder(View v) {
            super(v);
            tvDescription = v.findViewById(R.id.tvDescription);
            tvAmount = v.findViewById(R.id.tvAmount);
            tvDate = v.findViewById(R.id.tvDate);
        }
    }

}



