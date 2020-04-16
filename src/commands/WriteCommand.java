package commands;

import java.io.IOException;
import java.util.List;
import exceptions.ExistanceException;
import exceptions.FileDoesNotExistException;
import exceptions.InvalidArgumentException;
import exceptions.NotEnoughMemoryException;
import filesystem.FileSystem;
import filesystem.RealFileSystem;

public class WriteCommand extends Command {

    public WriteCommand(FileSystem fileSystem) {
        super(fileSystem);
        parser = new Parser();
    }
    @Override
    public List<String> getArguments(String input) {
        return parser.getCommandArguments(input);
    }
    @Override
    public String executeCommand(List<String> arguments)
            throws ExistanceException, NotEnoughMemoryException, InvalidArgumentException, IOException {
        freeUpMemory(arguments);
        Command.fileSystem.writeFileContent(arguments);
        return "";
    }
    private void freeUpMemory(List<String> arguments) throws NumberFormatException, FileDoesNotExistException,
            InvalidArgumentException, NotEnoughMemoryException {
        if (Command.getFileSystem().getClass() == RealFileSystem.class) {
            long sizeToAdd = ((RealFileSystem) fileSystem).getFiles().getSizeAfterWritingToFile(arguments.get(0),
                    Boolean.parseBoolean(arguments.get(1)), arguments.get(2), Integer.parseInt(arguments.get(3)));
            while (((RealFileSystem) fileSystem).getFiles().getAvailableMemory() - sizeToAdd < 0) {
                if (((RealFileSystem) fileSystem).getFiles().getRemovedFiles().size() == 0) {
                    throw new NotEnoughMemoryException("Not enough memory!");
                }
                ((RealFileSystem) fileSystem).getFiles().deleteRemovedFile();
            }
        }
    }
}