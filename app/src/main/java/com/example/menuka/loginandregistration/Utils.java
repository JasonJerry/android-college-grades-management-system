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
        Pattern p = Pattern.compile("[A-Z]{2}[0-9]{4}");
        Matcher m = p.matcher(input);
        if(m.find()){
            return true;
        }else {
            return false;
        }
    }
}
