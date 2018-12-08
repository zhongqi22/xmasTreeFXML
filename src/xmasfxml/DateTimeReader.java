/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xmasfxml;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Kee Aun
 */

public class DateTimeReader implements Subject {
    private ArrayList<Observer> observers;
    public long time;
    public String period;
    
    public DateTimeReader(){
        observers = new ArrayList<Observer>();
    }
    
    @Override
    public void registerObserver(Observer obs) {
        observers.add(obs);
        timeChanged();
    }

    @Override
    public void removeObserver(Observer obs) {
        int i = observers.indexOf(obs);
        if(i>=0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObserver() {
        for(Observer obs:observers){
            obs.update(time, period);
        }
    }

    public void setTime(long time){
        this.time = time;
        timeChanged();
    }
    
    public void timeChanged(){
        setPeriod();
        notifyObserver();
    }
    
    public String getPeriod(){
        Properties p = new Properties();
        String bgProperty = "";
        try{
            p.load(ClassLoader.getSystemResourceAsStream("christmas.properties"));
            bgProperty = p.getProperty("Background");
        } catch (IOException e){
            System.out.println("Error in properties file");
        }
        p.clear();
        
        return bgProperty;
    }
    
    public void setPeriod(){
        this.period = this.getPeriod();
        System.out.println(period);
    }
}
