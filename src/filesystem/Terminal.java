package filesystem;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import commands.Command;
import commands.CommandFactory;
import commands.Parser;
import exceptions.CommandDoesNotExistException;
import exceptions.ExistanceException;
import exception.InvalidArgumentException;
import exceptions.NotEnoughMemoryException;

public class Terminal {

    private Parser parser;
    public Terminal(){
        parser = new Parser();
    }
    public void run() throws ExistanceException, NotEnoughMemoryException, InvalidArgumentException, IOException{
        String choice = getChoiceForFileSystem();
        if(choice.equals("-v")){
            runVirtualFileSystem();
        }else if(choice.equals("-r")){
            runRealFileSystem();
        } else{
            System.out.println("Invalid!");
        }
    }
    private void runRealFileSystem() throws ExistanceException, NotEnoughMemoryException, InavlidArgumentException,IOException{
        Command.setFileSystem(new RealFileSystem());
        execute.Commands;
    }
    private void runVirtualFileSystem() throws ExistanceException, NotEnoughMemoryException, InavlidArgumentException,IOException{
        Command.setFileSystem(new VirtualFileSystem());
        executeCommands();
    }
    private String getChoiceForFileSystem(){
        System.out.println("Choose -v for Virtual filesystem, -r for Real filesystem or something else for quit: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        return choice;
    }
    private void executeCommands() throws IOException, CommandDoesNotExistException{
        CommandFactory commandFactory = new CommandFactory();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String input = scanner.nextLine();
            String commandName = parser.getCommand(input);
            Command currentCommand = commandFactory.getCommand(commandName);
            try{
                String result = currentCommand.execute(input);
                System.out.println(result);
            }catch (ExistanceException|InvalidArgumentException|NotEnoughMemoryException|IOException e){
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
