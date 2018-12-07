package xmasfxml;

public class Ornament {
    
    boolean ornament;
    
    public Ornament() {
        ornament = false;
    }
     
    public String show() {
        this.ornament = true;
        return "Ornament is on.";
    }

    public String hide() {
        this.ornament = false;
        return "Ornament is off.";
    }

    public boolean isOrnament() {
        return ornament;
    }

}
