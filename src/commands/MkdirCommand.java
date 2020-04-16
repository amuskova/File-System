package commands;

import java.io.IOException;
import java.util.List;
import exceptions.ExistanceException;
import filesystem.FileSystem;

public class MkdirCommand extends Command {

    public MkdirCommand(FileSystem fileSystem) {
        super(fileSystem);
        parser = new Parser();
    }
    @Override
    public List<String> getArguments(String input) {
        return parser.getCommandArguments(input);
    }
    @Override
    public String executeCommand(List<String> arguments) throws ExistanceException, IOException {
        Command.fileSystem.makeDirectory(arguments);
        return "";

    }

}
