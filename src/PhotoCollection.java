import java.util.ArrayList;
import java.util.List;

public class PhotoCollection {
    ArrayList<Photo> photoCollection;

    public void setPhotoCollection(List<Photo> collection) {
        photoCollection = (ArrayList<Photo>) collection;
    }

    public List<Photo> getPhotoCollection(){
        return photoCollection;
    }
}
