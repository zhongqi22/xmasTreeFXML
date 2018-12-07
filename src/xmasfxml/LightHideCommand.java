package xmasfxml;

public class LightHideCommand implements Command{
    Light light;

    public LightHideCommand(Light light){
        this.light = light;
    }

    @Override
    public String execute() {
        return light.hide();
    }

    @Override
    public String undo() {
        return light.show();
    }
}
