package xmasfxml;

import javafx.scene.image.Image;

/**
 *
 * @author Kee Aun
 */

public class BackgroundState {
    protected Image image;
    
    public Image getImage() {
        return image;
    }
    
    public static BackgroundState InitialState(Background bg){
        String design = bg.getBgProperty();
        if(design.equals("DAY")){
            return bg.getBgDayState();
        }else if(design.equals("SUNRISE")){
            return bg.getBgSunriseState();
        }else if(design.equals("SUNSET")){
            return bg.getBgSunsetState();
        }else if(design.equals("NIGHT")){
            return bg.getBgNightState();
        }else{
            System.err.println("No this background design lor");
            throw new UnsupportedOperationException("Not supported yet");
        }
    }
    
    public void refresh(){
        throw new UnsupportedOperationException("Not supported yet");
    }
    
}
