package xmasfxml;

import javafx.scene.image.ImageView;

/**
 *
 * @author Kee Aun
 */

public class Background implements Observer {
    private ImageView treeBackground;
    private DateTimeReader dateTimeReader;
    
    private BackgroundStateFactory bgFactory;
      
    private BackgroundState bgDayState = new BackgroundDayState(this);
    private BackgroundState bgSunriseState;
    private BackgroundState bgSunsetState;
    private BackgroundState bgNightState;

    private BackgroundState bgState;
    private String bgProperty;

    long curTime = 0;
    
    public Background(DateTimeReader dateTimeReader, ImageView treeBackground){
        bgFactory = new BackgroundStateFactory (this);
        
        bgDayState = bgFactory.createBgState("DAY", this);
        bgSunriseState = bgFactory.createBgState("SUNRISE", this);
        bgSunsetState = bgFactory.createBgState("SUNSET", this);
        bgNightState = bgFactory.createBgState("NIGHT", this);
        
        this.treeBackground = treeBackground;
                
        this.dateTimeReader = dateTimeReader;
        dateTimeReader.registerObserver(this);
        bgState = BackgroundState.InitialState(this);
        long time = System.currentTimeMillis();        
        this.dateTimeReader.setTime(time);
    }

    public BackgroundState getBgDayState() {
        return bgDayState;
    }

    public BackgroundState getBgSunriseState() {
        return bgSunriseState;
    }

    public BackgroundState getBgSunsetState() {
        return bgSunsetState;
    }

    public BackgroundState getBgNightState() {
        return bgNightState;
    }

    public BackgroundState getBgState() {
        return bgState;
    }

    public void setBgState(BackgroundState bgState) {
        this.bgState = bgState;
    }

    public void setBgProperty(String bgProperty) {
        this.bgProperty = bgProperty;
    }

    public String getBgProperty() {
        return bgProperty;
    }
    
    @Override
    public void update(long time, String period) {
        this.curTime = time;
        this.bgProperty = period;
        System.out.println("curtime: "+time);
        System.out.println("period" + bgProperty);
        if(bgState!= null){
            refresh();
        }
    }

    public void refresh(){
        bgState.refresh();
        refreshUI();
    }
    
    private void refreshUI(){
        treeBackground.setImage(bgState.getImage());
    }

}
