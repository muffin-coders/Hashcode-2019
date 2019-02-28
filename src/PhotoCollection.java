import java.util.ArrayList;
import java.util.List;

public class PhotoCollection {
    List<Photo> photoCollection = new ArrayList<>();

    public void addPhoto(Photo photo) {
        photoCollection.add(photo);
    }

    public List<Photo> getPhotoCollection() {
        return photoCollection;
    }
}
