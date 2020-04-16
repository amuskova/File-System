package commands;

import java.io.IOException;
import java.util.List;

import exceptions.ExistanceException;
import exceptions.FileDoesNotExistException;
import exceptions.InvalidArgumentException;
import exceptions.NotEnoughMemoryException;
import filesystem.FileSystem;

public class CatCommand extends Command{
    public CatCommand(FileSystem fileSystem){
        super(fileSystem);
        parser = new Parser();
    }

    @Override
    public List<String> getArguments(String input) {
        return parser.getCommandArguments(input);
    }

    @Override
    public String executeCommand(List<String> arguments) throws ExistanceException, NotEnoughMemoryException, InvalidArgumentException, IOException {
        return Command.fileSystem.readFileContent(arguments);
    }
}
