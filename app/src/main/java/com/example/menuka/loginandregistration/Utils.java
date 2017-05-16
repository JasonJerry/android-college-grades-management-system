package com.example.menuka.loginandregistration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by menuka on 5/16/17.
 */

public class Utils {
    public static boolean isInteger(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean isDouble(String input){
        try{
            Double.parseDouble(input);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean isLegitModuleCode(String input){
        Pattern p = Pattern.compile("^[A-Z|a-z]{2}[0-9]{4}$");
        Matcher m = p.matcher(input);
        return m.find();
    }

    public static boolean isLegitIndexNumber(String input){
        Pattern p = Pattern.compile("^[0-9]{6}[A-Z|a-z]$");
        Matcher m = p.matcher(input);
        return m.find();
    }

    public static void main(String[] args) {
        String[] indexNumbers = {"140650E", "456789A", "asfd465", "", " ", "/", "456123 A", "1234564A"};
        for(String s: indexNumbers){
            System.out.println(">>" + s.toUpperCase() + "<<: " + isLegitIndexNumber(s));
        }

        String[] moduleCodes = {"CE1012", "CE101", "CE 1012", "ce1012", "asfdfa", "asf!"};
        for(String s: moduleCodes){
            System.out.println(">>" + s.toUpperCase() + "<<: " + isLegitModuleCode(s));
        }
    }

}
