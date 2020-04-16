package commands;

import java.util.List;
import exceptions.ExistanceException;
import filesystem.FileSystem;

public class LsCommand extends Command {

    public LsCommand(FileSystem fileSystem) {
        super(fileSystem);
        parser = new Parser();
    }

    @Override
    public List<String> getArguments(String input) {
        return parser.getCommandArguments(input);
    }
    @Override
    public String executeCommand(List<String> arguments) throws ExistanceException {
        if (isSortedOption(arguments)) {
            return Command.fileSystem.listFilesSorted();
        }
        return Command.fileSystem.listFiles();
    }

    private boolean isSortedOption(List<String> arguments) {
        if (arguments.size() == 1) {
            if (arguments.get(0) == "--sorted") {
                return true;
            }
        }
        return false;
    }
}