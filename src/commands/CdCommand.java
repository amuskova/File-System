package commands;

import java.util.Arrays;
import java.util.List;
import exceptions.ExistanceException;
import filesystem.FileSystem;

public class CdCommand extends Command {

    public CdCommand(FileSystem fileSystem) {
        super(fileSystem);
        parser = new Parser();
    }
    @Override
    public List<String> getArguments(String input) {
        String[] splittedBySlash = parser.getArgumentsSplittedBySlash(parser.getPath(input));
        List<String> directoryNames = Arrays.asList(splittedBySlash);
        return directoryNames;
    }
    @Override
    public String executeCommand(List<String> arguments) throws ExistanceException {
        Command.fileSystem.changeDirectory(arguments);
        return "";
    }
}
