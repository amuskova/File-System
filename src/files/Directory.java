package files;

//import java.nio.file.FileAlreadyExistsException;
//import java.nio.file.Files;
//import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import exceptions.DirectoryAlreadyExistsException;
import exceptions.DirectoryDoesNotExistException;
import exceptions.FileAlreadyExistsException;
import exceptions.FileDoesNotExistException;
import exceptions.InvalidArgumentException;

public class Directory {
    private String name;
    private Directory parent;
    private Map<String, Directory> subdirectories;
    private Files files;
    public Directory(){
        name = "/";
        parent = this;
        files = new Files();
        subdirectories = new HashMap<String, Directory>();
    }
    public Directory(String name){
        this(name,null);
    }
    public Directory(String name, Directory parent){
        this.name = name;
        this.parent = parent;
        parent.subdirectories.put(name,this);
        files = new Files();
        subdirectories = new HashMap<String, Directory>();
    }

    public Files getFiles(){
        return files;
    }
    public Directory getParent(){
        return parent;
    }
    public String getName(){
        return name;
    }
    public void makeDirectory(String name) throws DirectoryAlreadyExistsException{
        this.checkIfDirectoryExists(name);
        Directory directory = new Directory(name, this);
        subdirectories.put(name, directory);
    }
    public Directory getSubDirectoryIfExists(String name) throws DirectoryDoesNotExistException{
        checkIfDirectoryDoesNotExists(name);
        return subdirectories.get(name);
    }
    public long getNumberOfWords(String name) throws FileDoesNotExistException{
        return files.getWordsCount(name);
    }
    public long getNumberOfLines(String name) throws FileDoesNotExistException{
        return files.getLinesCount(name);
    }
    public void deleteLinesFromFileContent(String name, int firstLineNumber, int secondLineNumber) throws FileDoesNotExistException, InvalidArgumentException{
        files.deleteLines(name,firstLineNumber,secondLineNumber);
    }
    public boolean isExistingDirectory(String name){
        if(subdirectories.containsKey(name)){
            return true;
        }
        return false;
    }
    public Directory getPreviousDirectory(){
        return this.getParent();
    }
    public void addFile(String name) throws FileAlreadyExistsException{
        files.add(name);
    }
    public void writeToFile(String name, boolean toOverwrite, String text, int line) throws FileDoesNotExistException, InvalidArgumentException{
        files.writeToFile(name, toOverwrite,text,line);
    }
    public String getFileNameInDirectory(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getSubdirectories());
        stringBuilder.append(files.getFileNamesSortedByFileSize());
        return new String(stringBuilder);
    }
    public String getFileNamesInDirectoryBySize(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getDirectoryNamesSortedBySize());
        stringBuilder.append(files.getFileNamesSortedByFileSize());
        return new String(stringBuilder);
    }
    public String concatenateFiles(List<String> arguments) throws FileDoesNotExistException{
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < arguments.size() ; i++){
            stringBuilder.append(this.files.readFileContent(arguments.get(i)));
        }
        return new String(stringBuilder);
    }
    public void removeFile(String name) throws FileDoesNotExistException{
        files.remove(name);
    }
    public int getDirectorySize(){
        return files.getUsedMemory();
    }
    private void checkIfDirectoryExists(String name) throws DirectoryAlreadyExistsException{
        if(isExistingDirectory(name)){
            throw new DirectoryAlreadyExistsException("The directory already exist!");
        }
    }
    private void checkIfDirectoryDoesNotExists(String name) throws DirectoryDoesNotExistException{
        if(!isExistingDirectory(name)){
            throw new DirectoryDoesNotExistException("The directory does not exists!");
        }
    }
    private String getSubdirectories(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String currentFile: subdirectories.keySet()){
            stringBuilder.append(currentFile);
            stringBuilder.append("\n");
        }
        return new String(stringBuilder);
    }
    private List<Directory> getSortedDirectories(){
        List<Directory> directories = new ArrayList<Directory>();
        for(Map.Entry<String,Directory> entry : subdirectories.entrySet()){
            directories.add(entry.getValue());
        }
        Collections.sort(directories, new DirectoryComparator());
        return directories;
    }
    private String getDirectoryNamesSortedBySize(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Directory directory: getSortedDirectories()){
            stringBuilder.append(directory.getName());
            stringBuilder.append("\n");
        }
        return new String(stringBuilder);
    }
}
