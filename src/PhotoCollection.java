import java.util.ArrayList;
import java.util.List;

public class PhotoCollection {
<<<<<<< Updated upstream

    List<Photo> photoCollection = new ArrayList<>();
    List<Slide> slideCollection = new ArrayList<>();
=======
    private List<Photo> photoCollection = new ArrayList<>();
    private List<Slide> slideCollection = new ArrayList<>();
>>>>>>> Stashed changes

    public void addPhoto(Photo photo) {
        photoCollection.add(photo);
    }

    public List<Photo> getPhotoCollection() {
        return photoCollection;
    }

<<<<<<< Updated upstream
    public List<Slide> getSlideCollection() {
        return slideCollection;
    }

    public void addSlide(Slide slide){
=======
    public void addSlide(Slide slide) {
>>>>>>> Stashed changes
        slideCollection.add(slide);
    }
}
