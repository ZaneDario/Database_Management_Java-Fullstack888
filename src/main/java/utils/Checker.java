package utils;

public class Checker {
    public static boolean checkClientMenuInput(String input, int options){

        try{
            int in = Integer.parseInt(input);
            if(in > 0 && in < options)
                return true;
            else return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean checkClientIntegerInput(String input)
    {
        try{
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public static boolean checkClientNameInput(String string)
    {
        //TODO Create regex to check name.
        return true;
    }
    public static boolean checkClientFloatInput(String input)
    {
        try{
            Float.parseFloat(input);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
