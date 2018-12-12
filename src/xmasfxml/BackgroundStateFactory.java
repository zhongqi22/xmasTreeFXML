/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmasfxml;

/**
 *
 * @author user
 */
public class BackgroundStateFactory {
    Background context;
    
    public BackgroundStateFactory (Background bg) {
        context = bg;
    }
    
    public BackgroundState createBgState (String type, Background context) {
        BackgroundState bg = null;
        if (type.equals("DAY")) 
            bg = new BackgroundDayState(context);
        else if (type.equals("SUNRISE"))
            bg = new BackgroundSunriseState(context);
        else if (type.equals("SUNSET"))
            bg = new BackgroundSunsetState(context);
        else if (type.equals("NIGHT"))
            bg = new BackgroundNightState(context);
        return bg;
    }
}
