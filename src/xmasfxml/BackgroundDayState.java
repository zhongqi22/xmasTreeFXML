package xmasfxml;

import javafx.scene.image.Image;

/**
 *
 * @author Kee Aun
 */

public class BackgroundDayState extends BackgroundState {
    Background background;
    
    public BackgroundDayState(Background background){
        this.background = background;
        image = new Image(getClass().getResource("Xmastree day.png").toExternalForm());
    }
    
    @Override
    public void refresh() {
        String bgProperty = background.getBgProperty();
        
        if(bgProperty.equals("DAY")){
            // do nothing
        }else if(bgProperty.equals("SUNSET")){
            background.setBgState(background.getBgSunsetState());
        }else{
            System.err.println("Follow the sequence SUNRISE->DAY->SUNSET->NIGHT");
            throw new UnsupportedOperationException("Not support yet");        
        }
    }

}
