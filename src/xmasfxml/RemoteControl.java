package xmasfxml;

import java.util.Iterator;

public class RemoteControl {
    CommandList showCommands;
    CommandList hideCommands;

    Command undoCommand;

    public RemoteControl() {
        showCommands = new CommandList();
        hideCommands = new CommandList();
    }

    public void setCommand(Command showCommand, Command hideCommand) {
        showCommands.addCommand(showCommand);
        hideCommands.addCommand(hideCommand);
    }

    public String showButtonPushed(int slot) {
        undoCommand = showCommands.getCommand(slot);
        return showCommands.getCommand(slot).execute();
    }
    
    public String showAll () {
        Iterator<Command> iter = showCommands.createIterator();
        while(iter.hasNext()) {
            Command c = iter.next();
            c.execute();
        }
        return "All decorations are shown";
    }
    
    public String hideAll () {
        Iterator<Command> iter = hideCommands.createIterator();
        while(iter.hasNext()) {
            Command c = iter.next();
            c.execute();
        }
        return "All decorations are hidden";
    }

    public String hideButtonPushed(int slot) {
        undoCommand = hideCommands.getCommand(slot);
        return hideCommands.getCommand(slot).execute();
    }

    public String undoButtonPushed() {
        return undoCommand.undo();
    }
}
