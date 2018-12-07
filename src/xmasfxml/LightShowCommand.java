package xmasfxml;

public class LightShowCommand implements Command{
    Light light;

    public LightShowCommand(Light light){
        this.light = light;
    }

    @Override
    public String execute() {
        return light.show();
    }

    @Override
    public String undo() {
        return light.hide();
    }
}

