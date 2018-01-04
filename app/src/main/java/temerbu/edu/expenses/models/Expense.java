package temerbu.edu.expenses.models;


import org.joda.time.LocalDate;

public class Expense {
    private String description;
    private LocalDate date;
    private double amount;
    private ExchangeRate rate; //which currency what was the rate

    //2 constructors, getters, and setters, toString
    //alt insert

    //Constructors:
    public Expense() {
    }
    public Expense(String description, LocalDate date, double amount, ExchangeRate rate) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.rate = rate;
    }
    public Expense(String description, double amount, ExchangeRate rate) {
        this.description = description;
        this.amount = amount;
        this.rate = rate;
        this.date = LocalDate.now();
    }

    //getters and setters
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public ExchangeRate getRate() {
        return rate;
    }
    public void setRate(ExchangeRate rate) {
        this.rate = rate;
    }

    //toString
    @Override
    public String toString() {
        return "Expense{" +
                "amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                '}';
    }
}
