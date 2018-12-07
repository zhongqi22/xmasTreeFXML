package xmasfxml;

public class OrnamentHideCommand implements Command {
    
    Ornament ornament;
    
    public OrnamentHideCommand(Ornament ornament) {
        this.ornament = ornament;
    }

    @Override
    public String execute() {
        return ornament.hide();
    }
    
    public String undo() {
        return ornament.show();
    }
}
