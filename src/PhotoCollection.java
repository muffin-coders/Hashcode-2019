import java.util.ArrayList;
import java.util.List;

public class PhotoCollection {

    List<Photo> photoCollection = new ArrayList<>();
    List<Slide> slideCollection = new ArrayList<>();

    public void addPhoto(Photo photo) {
        photoCollection.add(photo);
    }

    public List<Photo> getPhotoCollection() {
        return photoCollection;
    }

    public List<Slide> getSlideCollection() {
        return slideCollection;
    }

    public void addSlide(Slide slide){
        slideCollection.add(slide);
    }
}
