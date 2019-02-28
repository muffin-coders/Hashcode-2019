import java.util.ArrayList;
import java.util.List;

public class StupidSlideShowGenerator implements SlideShowGenerator {

    @Override
    public void generateSlideShow(PhotoCollection photoCollection) {
        List<Photo> photoList = photoCollection.getPhotoCollection();
        List<Photo> slideOnPhoto = new ArrayList<>();

        for (Photo photo : photoList) {
            for (Photo comparePhoto : photoList) {
                if (photo.compareTo(comparePhoto) > 1) {
                    System.out.println("Photo found");
                    photoCollection.addSlide(new Slide(slideOnPhoto));
                }
            }
        }
    }
}
