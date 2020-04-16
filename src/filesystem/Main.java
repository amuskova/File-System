package filesystem;

import java.io.IOException;
import exceptions.ExistanceExseption;
import exceptions.InvalidArgumentException;
import exceptions.NoEnoughMemoryExceptions;

public class Main {

    public static void main(String[] args) throws ExistanceException, NotEnoughMemoryException, InvalidArgumentException,IOException{
        Terminal terminal = new Terminal();
        terminal.run();
    }
}
