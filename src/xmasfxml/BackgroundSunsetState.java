package xmasfxml;

import javafx.scene.image.Image;

/**
 *
 * @author Kee Aun
 */

public class BackgroundSunsetState extends BackgroundState {
    Background background;
    
    public BackgroundSunsetState(Background background){
        this.background = background;
        image = new Image(getClass().getResource("Xmastree sunset.png").toExternalForm());

    }
    
    @Override
    public void refresh() {
        String bgProperty = background.getBgProperty();
        
        if(bgProperty.equals("SUNSET")){
            // do nothing
        }else if(bgProperty.equals("NIGHT")){
            background.setBgState(background.getBgNightState());
        }else{
            System.err.println("Follow the sequence SUNRISE->DAY->SUNSET->NIGHT");
            throw new UnsupportedOperationException("Not support yet");        
        }
    }

}