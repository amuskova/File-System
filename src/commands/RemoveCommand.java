package commands;

import java.io.IOException;
import java.util.List;
import exceptions.ExistanceException;
import exceptions.InvalidArgumentException;
import filesystem.FileSystem;

public class RemoveCommand extends Command {

    public RemoveCommand(FileSystem fileSystem) {
        super(fileSystem);
        parser = new Parser();
    }
    @Override
    public List<String> getArguments(String input) {
        return parser.getCommandArguments(input);
    }

    @Override
    public String executeCommand(List<String> arguments)
            throws ExistanceException, InvalidArgumentException, IOException {
        Command.fileSystem.removeContent(arguments);
        return "";
    }
}
