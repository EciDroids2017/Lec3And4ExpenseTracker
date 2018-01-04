package temerbu.edu.expenses.models;


/**
 * A Model Object
 * A Data Structure to represent an Exchange Rate.
 */

public class ExchangeRate {
    public static String date;//not instance field

    private String name;
    private double rate;

    //alt+insert -> constructor
    public ExchangeRate(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    //alt+insert -> constructor
    public ExchangeRate() {
    }

    //alt+insert -> getters and setters
    public static String getDate() {
        return date;
    }
    public static void setDate(String date) {
        ExchangeRate.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }

    //alt+insert -> toString
    @Override
    public String toString() {
        return name;
    }
}
