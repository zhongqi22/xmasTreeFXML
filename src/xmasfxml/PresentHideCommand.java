package xmasfxml;

public class PresentHideCommand implements Command {
    Present present;

    public PresentHideCommand(Present present){
        this.present = present;
    }

    @Override
    public String execute() {
        return present.hide();
    }

    @Override
    public String undo() {
        return present.show();
    }
}
