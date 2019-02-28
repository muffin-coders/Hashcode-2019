import java.util.ArrayList;
import java.util.List;

public class PhotoCollection {
    private List<Photo> photoCollection = new ArrayList<>();
    private List<Slide> slideCollection = new ArrayList<>();
    private int horizontalCount;
    private int verticalCount;

    public void addPhoto(Photo photo) {
        photoCollection.add(photo);
    }

    public List<Photo> getPhotoCollection() {
        return photoCollection;
    }

    public List<Slide> getSlideCollection() {
        return slideCollection;
    }

    public void addSlide(Slide slide) {
        slideCollection.add(slide);
    }

    public int getHorizontalCount() {
        return horizontalCount;
    }

    public void setHorizontalCount(int horizontalCount) {
        this.horizontalCount = horizontalCount;
    }

    public int getVerticalCount() {
        return verticalCount;
    }

    public void setVerticalCount(int verticalCount) {
        this.verticalCount = verticalCount;
    }

    public boolean hasNotUsedPhotos(){
        for(Photo photo: photoCollection){
            if(photo.isNotUsed()){
                return true;
            }
        }
        return false;
    }
}
