import java.util.ArrayList;
import java.util.List;

public class Photo {

    List<String> tags = new ArrayList<>();
    int id;
    boolean used;
    boolean isHorizontal;

    public Photo(List<String> tags, int id, boolean used, boolean isHorizontal) {
        this.tags = tags;
        this.id = id;
        this.used = used;
        this.isHorizontal = isHorizontal;
    }

    public double compareTo(Photo photo) {
        return 0;
    }

    public List<String> getTags() {
        return tags;
    }
}
