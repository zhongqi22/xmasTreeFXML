package xmasfxml;

public class Present {
    boolean present;

    public Present(){
        this.present = false;
    }

    public String show(){
        this.present = true;
        return "The present is on.";
    }

    public String hide(){
        this.present = false;
        return "The present is off";
    }

    public boolean isPresent() {
        return present;
    }
}
