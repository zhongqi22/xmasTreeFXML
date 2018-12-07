package xmasfxml;

public class PresentShowCommand implements Command {
    Present present;

    public PresentShowCommand(Present present){
        this.present = present;
    }

    @Override
    public String execute() {
        return present.show();
    }

    @Override
    public String undo() {
        return present.hide();
    }
}
