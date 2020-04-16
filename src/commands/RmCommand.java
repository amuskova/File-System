package commands;

import java.io.IOException;
import java.util.List;
import exceptions.ExistanceException;
import filesystem.FileSystem;

public class RmCommand extends Command {

    public RmCommand(FileSystem fileSystem) {
        super(fileSystem);
        parser = new Parser();
    }
    @Override
    public List<String> getArguments(String input) {
        return parser.getCommandArguments(input);
    }
    @Override
    public String executeCommand(List<String> arguments) throws ExistanceException, IOException {
        Command.fileSystem.removeFile(arguments.get(0));
        return "";
    }

}