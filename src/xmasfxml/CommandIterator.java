package xmasfxml;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandIterator implements Iterator{

    ArrayList<Command> commands;
    int position = 0;
    
    public CommandIterator(ArrayList<Command> commands){
        this.commands = commands;
    }

    @Override
    public boolean hasNext() {
        return !(position >= commands.size() || commands.get(position) == null);
    }

    @Override
    public Command next() {
        Command command = commands.get(position);
        position++;
        return command;
    }
}
