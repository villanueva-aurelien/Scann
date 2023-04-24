package src;

import java.io.File;

public class Main
{
    private static String _name = "Scann.txt";
    private static String _address = "C:"+File.separator;
    public static void main(String[] args)
    {
        Scann sc = new Scann(_address, _name);
        sc.runScann();
    }
}