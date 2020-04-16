package files;
import java.util.Map;
import exceptions.DirectoryDoesNotExistException;
import exceptions.InvalidArgumentException;
import java.util.HashMap;

public class File {
    private String name;
    private Map<Integer,String> content;
    public File(){
        name = "";
        content = new HashMap<Integer, String>();
    }
    public File(String name){
        this.name = name;
        content = new HashMap<Integer, String>();
    }
    public String getName(){
        return name;
    }
    public String getContentOnSpecificLine(int line){
        return content.get(line);
    }
    public void write(boolean toOverwrite, int line, String text) throws InvalidArgumentException{
        isPositiveLineNumber(line);
        if(countLines() >= line){
            if(!toOverwrite){
                append(line,text);
            }else{
                content.put(line,text);
            }
        }else{
            for(int i = content.size(); i<line; i++){
                content.put(i,"");
            }
            content.put(line,text);
        }
    }
    public long getCalculatedSize(){
        long sizeOfFile = countLines() + countSymbols();
        return sizeOfFile;
    }
    public String getContent(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < getNumberOfLastRow()+1;i++){
            if(content.containsKey(i)){
                stringBuilder.append(content.get(i));
                stringBuilder.append("\n");
            }
        }
        return new String(stringBuilder);
    }
    public long countWords(){
        String allContent = getContent();
        String[] words = allContent.trim().split("\\s+");
        return words.length;
    }
    public int countLines(){
        return content.size();
    }
    public void removeLines(int firstLineNumber, int secondLineNumber) throws InvalidArgumentException{
        isPositiveLineNumber(firstLineNumber);
        for(int i = 0;i < secondLineNumber; i++){
            content.remove(i);
        }
    }
    public long getSizeAfterWriting(boolean toOverwrite,String text, int line) throws InvalidArgumentException{
        isPositiveLineNumber(line);
        long size =getCalculatedSize();
        if(content.size() <= line){
            long numberNewLines = line - content.size();
            size += numberNewLines;
            size += text.length();
        }else{
            if(toOverwrite){
                size+=text.length();
                int newLineContentSize = getContentOnSpecificLine(line).length();
                size-= newLineContentSize;
            }else{
                size+= text.length();
            }
        }
        return size;
    }
    private long countSymbols(){
        String currentLine;
        long countSymbols = 0;
        for (int i = 0 ; i < content.size(); i++){
            currentLine = content.get(i);
            countSymbols+= currentLine.length();
        }
        return countSymbols;
    }
    private void append(int index, String text){
        String newLine = content.get(index) + text;
        content.replace(index, newLine);
    }
    private int getNumberOfLastRow(){
        Map.Entry<Integer,String> entry = content.entrySet().iterator().next();
        int lastRow = entry.getKey();
        for(Map.Entry<Integer,String> map:content.entrySet()){
            int currentRow = map.getKey();
            if(currentRow > lastRow){
                lastRow = currentRow;
            }
        }
        return lastRow;
    }
    private void isPositiveLineNumber(int line) throws InvalidArgumentException{
        if(line < 0){
            throw new InvalidArgumentException("Invalid arguments!");
        }
    }

}
