package com.example.mybankapplication.cleancode.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Patterns;
import android.view.View;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public Utils()
    {


    }

    public static void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE); // optional
        }
    }

    public static void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags = flags & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; // use XOR here for remove LIGHT_STATUS_BAR from flags
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT); // optional
        }
    }

    public static String formatCurrency(Double currency) {

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        String currencyString = format.format(currency);
        //remove the symbol of euro at the end of the string
        currencyString = currencyString.substring(0, currencyString.length() - 1);

        return currencyString;
    }

    public static String formatDate(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        // First part is for alphanumeric characters and second part for upper-case character
        // and the third part is for special characters. Fourth part avoids space character in the password field
        //
        final String PASSWORD_PATTERN = "^(?=.*[0-9a-zA-Z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static boolean isValidUsername(final String password) {

        Pattern patternCPF;
        Pattern patternEmail = Patterns.EMAIL_ADDRESS;

        Matcher matcherCPF;
        Matcher matcherEmail;

        // First part is for alphanumeric characters and second part for upper-case character
        // and the third part is for special characters. Fourth part avoids space character in the password field
        //
        final String PASSWORD_PATTERN = "^(?=.*[0-9]).{11,}$";


        patternCPF = Pattern.compile(PASSWORD_PATTERN);
        matcherCPF = patternCPF.matcher(password);
        matcherEmail = patternEmail.matcher(password);

        return matcherEmail.matches() ||  matcherCPF.matches();

    }

}
