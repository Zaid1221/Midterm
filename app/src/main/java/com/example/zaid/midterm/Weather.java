package com.example.zaid.midterm;

/**
 * Created by Zaid Javaid on 10/25/2017.
 */

public class Weather
{
    private String Description;
    private String Temp;

    public Weather(String description, String temp)
    {
        Description = description;
        Temp = temp;
    }

    public void setDescription(String des)
    {
        Description = des;
    }

    public void setTemp(String temperature)
    {
        Temp = temperature;
    }

    @Override
    public String toString()
    {
        return "Description: " + Description + "\n" + "Temperature: " + Temp;
    }
}
