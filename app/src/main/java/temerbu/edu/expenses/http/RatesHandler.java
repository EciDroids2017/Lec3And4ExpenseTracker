package temerbu.edu.expenses.http;


import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import temerbu.edu.expenses.models.ExchangeRate;

public class RatesHandler {

    //Event to report the rates result.
    //Observer Design pattern
    public interface OnRatesArrivedListener {
        void onRatesArrived(ArrayList<ExchangeRate> result);

        void onError(Exception e);
    }

    //new Thread -> do all the work on the new Thread
    //report the result on the (UI Thread)
    public static void readRatesAsync(final OnRatesArrivedListener listener) {
        //get a reference to the Current Thread. (UI Thread)
        //the result will be reported on the UI Thread.
        final Handler uiThread = new Handler();

        new Thread(() -> {
            //Code that runs in the background:
            try {
                String json = HTTPHandler.read("https://api.fixer.io/latest?base=ILS");
                final ArrayList<ExchangeRate> result = parseJson(json);

                //report the result to the ui thread:
                uiThread.post(() -> {
                    //code that runs on the UI Thread.
                    listener.onRatesArrived(result);
                });
            } catch (final Exception e) {
                uiThread.post(() -> listener.onError(e));
            }
        }).start();
    }

    public static ArrayList<ExchangeRate> parseJson(String json) throws JSONException {
        //Json are javaScript objects -> JavaScript Object Notation
        JSONObject ratesObject = new JSONObject(json);

        String date = ratesObject.getString("date");
        String base = ratesObject.getString("base");

        JSONObject innerRates = ratesObject.getJSONObject("rates");
        //double aud = innerRates.getDouble("AUD");

        Iterator<String> iterator = innerRates.keys();

        ArrayList<ExchangeRate> result = new ArrayList<>();
        while (iterator.hasNext()) {
            String rateKey = iterator.next();
            double rateValue = innerRates.getDouble(rateKey);

            ExchangeRate rate = new ExchangeRate(rateKey, rateValue);
            result.add(rate);
        }
        ExchangeRate.date = date;
        return result;
    }
}
