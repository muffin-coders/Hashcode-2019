import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.min;

public class Slide {

    boolean isUsed = false;
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

    public int getNumberOfTags() {
        Set<String> tags = new HashSet<>();
        for (Photo photo : photos) {
            tags.addAll(photo.getTags());
        }
        return tags.size();
    }

    public void usePhoto() {
        isUsed = true;
    }

    public int compareTo(Slide slide) {
        int entropy = 0;
        for (String photoTag : getAllTags()) {
            for (String comparedPhotoTag : slide.getAllTags()) {
                if (photoTag.equals(comparedPhotoTag)) {
                    entropy++;
                }
            }
        }
        int entropyActual = getAllTags().size() - entropy;
        int entropyCompared = slide.getNumberOfTags() - entropy;

        return min(entropyActual, entropyCompared);
    }
}
