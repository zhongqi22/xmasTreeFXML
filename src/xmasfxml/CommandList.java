package xmasfxml;

import java.util.Iterator;

import java.util.ArrayList;

public class CommandList{
    private ArrayList<Command> commands;

    public CommandList(){
        commands = new ArrayList<Command>();
    }

    public Command getCommand(int slot){
        return commands.get(slot);
    }

    public void addCommand(Command command){
        commands.add(command);
    }
    
    public Iterator<Command> createIterator() {
        return commands.iterator();
    }
}
