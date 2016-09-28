package com.example.BankClient;

public class Data
{
    private static long Person_ID;
    private static String Person_Name;
    private static long Account_ID;

    public static long getPerson_ID()
    {
        return Person_ID;
    }

    public static void setPerson_ID(long pid)
    {
        Data.Person_ID = pid;
    }

    public static long getAccount_ID()
    {
        return Account_ID;
    }

    public static void setAccount_ID(long aid)
    {
        Data.Account_ID = aid;
    }

    public static String getPerson_Name()
    {
        return Person_Name;
    }

    public static void setPerson_Name(String person_Name)
    {
        Data.Person_Name = person_Name;
    }

}
