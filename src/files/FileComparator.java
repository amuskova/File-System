package files;

import java.util.Comparator;

public class FileComparator  implements Comparator<File>{
    @Override
    public int compare(File o1, File o2) {
        return Long.compare(o1.getCalculatedSize(),o2.getCalculatedSize());
    }
}
