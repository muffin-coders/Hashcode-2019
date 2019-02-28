import java.util.ArrayList;
import java.util.List;

public class Slide {

    List<Photo> photos = new ArrayList<>();

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }


    public List<Photo> getPhotos() {
        return photos;
    }

    public List<String> getAllTags() {
        List<String> tags = new ArrayList<>();
        for (Photo photo : photos) {
            tags.addAll(photo.getTags());
        }
        return tags;
    }
}
