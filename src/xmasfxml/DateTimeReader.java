/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xmasfxml;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Properties;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 *
 * @author Kee Aun
 */

public class DateTimeReader implements Subject {
    private ArrayList<Observer> observers;
    public long time;
    public String period;
    
    private PropertiesConfiguration configuration;

    public DateTimeReader(){
        observers = new ArrayList<Observer>();
        loadConfig();
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
        String bgProperty = "";
        bgProperty = getProperty("Background");
        return bgProperty;
    }
    
    public String getProperty(final String key) {
        configuration.reload();
        return (String)configuration.getProperty(key);
    }
    
    public void loadConfig () {
        try {
            configuration = new PropertiesConfiguration("christmas.properties");
            configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
 
        } catch (ConfigurationException e) {
            System.out.println("Cannot read property");
        }
    }
    
    public void setPeriod(){
        this.period = this.getPeriod();
    }
}
