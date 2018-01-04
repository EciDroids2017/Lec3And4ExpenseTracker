package temerbu.edu.expenses;


import org.joda.time.LocalDate;

import java.util.ArrayList;

import temerbu.edu.expenses.models.ExchangeRate;
import temerbu.edu.expenses.models.Expense;

public class ExpenseHandler {
    public static ArrayList<Expense> expenses = new ArrayList<>();

    //constructor for static
    static {
        for (int i = 0; i < 100; i++) {
            //fabricate some dummy data:
            expenses.add(
                    new Expense("Hamburger",
                            LocalDate.now(),
                            50,
                            new ExchangeRate("AUD", 10)
                    )
            );
            expenses.add(
                    new Expense("Pizza",
                            LocalDate.now(),
                            10,
                            new ExchangeRate("USD", 3.49)
                    )
            );
            expenses.add(
                    new Expense("Falafel",
                            LocalDate.now(),
                            20,
                            new ExchangeRate("ILS", 1)
                    )
            );
        }
    }

}
