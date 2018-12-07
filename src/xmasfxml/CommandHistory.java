/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmasfxml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 *
 * @author zhongqi
 */
public class CommandHistory {
    private static CommandHistory commandHistory;
    private PrintWriter writer;
    
    private CommandHistory(){
    }
    
    public static CommandHistory getInstance(){
        if(commandHistory == null){
            commandHistory = new CommandHistory();
        }
        return commandHistory;
    }
    
    public void log(String message){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        try {
            writer = new PrintWriter(new FileOutputStream(new File("log.txt"),true));
            writer.println(time + ": " + message);
            writer.flush();
            writer.close();
        } catch (IOException e) {
        }
    }
}
