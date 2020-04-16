package files;

import java.util.Comparator;
public class DirectoryComparator implements Comparator<Directory>{
    @Override
    public int compare(Directory o1, Directory o2) {
        return Long.compare(o1.getDirectorySize(),o2.getDirectorySize());
    }
}
