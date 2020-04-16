package files;

//import java.nio.file.FileAlreadyExistsException;
import java.util.Map;
import java.util.Queue;
import exceptions.FileAlreadyExistsException;
import exceptions.FileDoesNotExistException;
import exceptions.InvalidArgumentException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Files {
    private static final int MEMORY = 200;
    private Map<String,File> availableFiles;
    private Queue<File> removedFiles;
    public Files(){
        availableFiles = new HashMap<String,File>();
        removedFiles = new LinkedList<File>();
    }
    public Queue<File> getRemovedFiles(){
        return removedFiles;
    }
    public Map<String,File> getAvailableFiles(){
        return availableFiles;
    }
    public void add(String name) throws FileAlreadyExistsException{
        checkIfFileExists(name);
        File file = new File(name);
        availableFiles.put(name,file);
    }
    public void writeToFile(String name, boolean toOverwrite, String text, int line)throws FileDoesNotExistException, InvalidArgumentException{
        checkIfFileDoesNotExist(name);
        availableFiles.get(name).write(toOverwrite,line,text);
    }
    public String readFileContent(String name) throws FileDoesNotExistException{
        checkIfFileDoesNotExist(name);
        return availableFiles.get(name).getContent();
    }
    public String getFileNames(){
        StringBuilder stringBuilder = new StringBuilder();
        for(String currentFile : availableFiles.keySet()){
            stringBuilder.append(currentFile);
            stringBuilder.append("\n");
        }
        return new String(stringBuilder);
    }
    public String getFileNamesSortedByFileSize(){
        StringBuilder stringBuilder = new StringBuilder();
        for(File f: getSortedFiles()){
            stringBuilder.append(f.getName());
            stringBuilder.append("\n");
        }
        return new String(stringBuilder);
    }
    public long getWordsCount(String name) throws FileDoesNotExistException{
        this.checkIfFileDoesNotExist(name);
        return availableFiles.get(name).countWords();
    }
    public long getLinesCount(String name) throws FileDoesNotExistException{
        this.checkIfFileDoesNotExist(name);
        return availableFiles.get(name).countLines();
    }
    public boolean isExistingFile(String name){
        return availableFiles.containsKey(name);
    }
    public File getSpecificFileByName(String name) throws FileDoesNotExistException{
        if(availableFiles.containsKey(name)){
            return availableFiles.get(name);
        }
        throw new FileDoesNotExistException("Files does not exist");
    }
    public void remove(String name) throws FileDoesNotExistException{
        if(!availableFiles.containsKey(name)){
            throw new FileDoesNotExistException("File does not exist");
        }
        removedFiles.add(availableFiles.get(name));
        availableFiles.remove(name);
    }
    public void deleteRemovedFile(){
        removedFiles.remove();
    }
    public void deleteLines(String name, int firstLineNumber, int secondLineNumber)throws FileDoesNotExistException, InvalidArgumentException{
        checkIfFileDoesNotExist(name);
        availableFiles.get(name).removeLines(firstLineNumber,secondLineNumber);
    }
    public long getSizeAfterWritingToFile(String name, boolean toOverwrite,String text, int line) throws FileDoesNotExistException, InvalidArgumentException{
        checkIfFileDoesNotExist(name);
        return availableFiles.get(name).getSizeAfterWriting(toOverwrite,text,line);
    }
    public int getUsedMemory(){
        return getUsedMemoryOfAvailableFiles() + getUsedMemoryOfRemovedFiles();
    }
    public int getAvailableMemory(){
        return getMemory() - getUsedMemory();
    }
    public int getMemory(){
        return MEMORY;
    }
    private List<File> getSortedFiles(){
        List<File> files = new ArrayList<File>();
        for(Map.Entry<String,File> entry: availableFiles.entrySet()){
            files.add(entry.getValue());
        }
        Collections.sort(files,new FileComparator());
        return files;
    }
    private void checkIfFileExists(String name) throws FileAlreadyExistsException{
        if(isExistingFile(name)){
            throw new FileAlreadyExistsException("The file already exists!");
        }
    }
    private void checkIfFileDoesNotExist(String name) throws FileDoesNotExistException{
        if(!this.isExistingFile(name)){
            throw new FileDoesNotExistException("The file does not exist!");
        }
    }
    private int getUsedMemoryOfAvailableFiles() {
        int usedMemory = 0;
        for (Map.Entry<String, File> entry : availableFiles.entrySet()) {
            File currentFile = entry.getValue();
            usedMemory += currentFile.getCalculatedSize();
        }
        return usedMemory;
    }

    private int getUsedMemoryOfRemovedFiles() {
        int usedMemory = 0;
        for (File file : removedFiles) {
            usedMemory += file.getCalculatedSize();
        }
        return usedMemory;
    }

}
