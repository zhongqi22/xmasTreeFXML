package xmasfxml;

import javafx.scene.image.Image;

/**
 *
 * @author Kee Aun
 */

public class BackgroundNightState extends BackgroundState {
    Background background;
    
    public BackgroundNightState(Background background){
        this.background = background;
        image = new Image(getClass().getResource("Xmastree night.png").toExternalForm());

    }
    
    @Override
    public void refresh() {
        String bgProperty = background.getBgProperty();
        
        if(bgProperty.equals("NIGHT")){
            // do nothing
        }else if(bgProperty.equals("SUNRISE")){
            background.setBgState(background.getBgSunriseState());
        }else{
            System.err.println("Follow the sequence SUNRISE->DAY->SUNSET->NIGHT");
            throw new UnsupportedOperationException("Not support yet");        
        }
    }

}
