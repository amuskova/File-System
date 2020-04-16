package commands;
import java.util.List;

import exceptions.ExistanceException;
import filesystem.FileSystem;

public class PwdCommand extends Command {

    public PwdCommand(FileSystem fileSystem) {
        super(fileSystem);
        parser = new Parser();
    }
    @Override
    public List<String> getArguments(String input) {
        return parser.getCommandArguments(input);
    }
    @Override
    public String executeCommand(List<String> arguments) throws ExistanceException {
        return Command.fileSystem.getCurrentDirectoryPath();
    }
}
