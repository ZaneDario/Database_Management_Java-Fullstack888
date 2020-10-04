package utils;

public class Utils {
    public static int numberComparator(int x, int y)
    {
        if(x < y)
            return -1;
        else if(x > y)
            return 1;
        else return 0;
    }

    public static int numberComparator(float x, float y)
    {
        if(x < y)
            return -1;
        else if(x > y)
            return 1;
        else return 0;
    }
}
