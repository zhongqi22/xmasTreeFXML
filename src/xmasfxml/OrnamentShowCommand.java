package xmasfxml;

public class OrnamentShowCommand implements Command{
    
    Ornament ornament;
    
    public OrnamentShowCommand(Ornament ornament) {
        
        this.ornament = ornament;
        
    }

    @Override
    public String execute() {
        return ornament.show();
    }

    @Override
    public String undo() {
        return ornament.hide();
    }
}
