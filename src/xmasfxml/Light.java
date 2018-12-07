package xmasfxml;

public class Light {
    boolean light;

    public Light(){
        this.light = false;
    }

    public String show(){
        this.light = true;
        return "The light is on.";
    }

    public String hide(){
        this.light = false;
        return "The light is off.";
    }

    public boolean isLight() {
        return light;
    }
}
