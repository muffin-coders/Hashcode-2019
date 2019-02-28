import java.util.List;

public class StupidSlideShowGenerator implements SlideShowGenerator {

    @Override
    public void generateSlideShow(PhotoCollection photoCollection) {
        List<Photo> photos = photoCollection.getPhotoCollection();
    }
}
