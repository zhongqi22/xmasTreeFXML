
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class ChangeTime {
    private static PropertiesConfiguration configuration;
    
    public static void main (String[] args) {
         loadConfig();
         Scanner scn = new Scanner(System.in);
         while (true) {
             System.out.println("Current Time: " + getProperty("Background"));
             System.out.println("Please select the time");
             System.out.println("1. Sunrise\n2. Day\n3. Sunset\n4. Night\n5. Exit");
             int userChoice = scn.nextInt(); 
             switch (userChoice) {
                 case 1: 
                     changeTime("SUNRISE");
                     break;
                 case 2: 
                     changeTime("DAY");
                     break;
                 case 3: 
                     changeTime("SUNSET");
                     break;
                 case 4:
                     changeTime("NIGHT");
                 case 5: 
                     System.exit(0);
             }
         }
    }
    
    public static void changeTime(String period) {
        configuration.setProperty("Background", period);
        try {
            configuration.save();        
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        System.out.println("\nTime changed to " + period + "\n");
    }
    
    public static void loadConfig () {
        try {
            configuration = new PropertiesConfiguration("christmas.properties");
            configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
            
        } catch (ConfigurationException e) {
            System.out.println("Cannot read property");
        }
    }
    
    public static String getProperty(final String key) {
        configuration.reload();
        return (String)configuration.getProperty(key);
    }
}
