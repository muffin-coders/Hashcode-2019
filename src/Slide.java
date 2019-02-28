import java.util.ArrayList;
import java.util.List;

public class Slide {

    List<Photo> photos = new ArrayList<>();
    private boolean isUsed = false;

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }


    public List<Photo> getPhotos() {
        return photos;
    }

    public List<String> getAllTags() {
        List<String> tags = new ArrayList<>();
        for (Photo photo : photos) {
            for (String tag : photo.getTags()) {
                if (!tags.contains(tag)) {
                    tags.add(tag);
                }
            }
            // old: tags.addAll(photo.getTags());
        }
        return tags;
    }

    public boolean containsTag(String tagInput) {
        List<String> allTags = getAllTags();
        for (String tag : allTags) {
            if (tag.equals(tagInput)) {
                return true;
            }
        }
        return false;
    }


    public int compareTo(Slide slide) {
        int slide1TagCounter = 0;
        int slide2TagCounter = 0;
        int slideTogetherTagCounter = 0;

        for (String tag : slide.getAllTags()) {
            if (containsTag(tag)) {
                slideTogetherTagCounter++;
            } else {
                slide2TagCounter++;
            }
        }
        slide1TagCounter = (slide.getAllTags().size() + getAllTags().size()) - slideTogetherTagCounter - slide2TagCounter;

        return Math.min(slide1TagCounter, Math.min(slide2TagCounter, slideTogetherTagCounter));
    }

    public boolean isUsed(){
        return isUsed;
    }

    public void markAsUsed(){
        isUsed = true;
    }

}
