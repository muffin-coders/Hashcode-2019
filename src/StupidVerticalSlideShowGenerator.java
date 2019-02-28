import java.util.ArrayList;
import java.util.List;

public class StupidVerticalSlideShowGenerator implements SlideShowGenerator {
    private List<Photo> photoList;
    private PhotoCollection photoCollection;

    @Override
    public void generateSlideShow(PhotoCollection photoCollection) {
        this.photoCollection = photoCollection;
        photoList = photoCollection.getPhotoCollection();

        Slide slide = new Slide();
        for (Photo photo : photoList) {
            if (!photo.isVertical()) {
                slide = new Slide();
                slide.addPhoto(photo);
                photoCollection.addSlide(slide);
            } else if (slide.getPhotos().size() >= 2) {
                slide = new Slide();
                slide.addPhoto(photo);
                photoCollection.addSlide(slide);
            } else {
                slide.addPhoto(photo);
            }
        }
    }

}
