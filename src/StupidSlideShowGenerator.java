import java.util.List;

public class StupidSlideShowGenerator implements SlideShowGenerator {
    List<Photo> photoList;
    private PhotoCollection photoCollection;

    @Override
    public void generateSlideShow(PhotoCollection photoCollection) {
        this.photoCollection = photoCollection;
        photoList = photoCollection.getPhotoCollection();

        Photo firstPhoto = photoList.get(0);
        Slide slide = new Slide();
        slide.addPhoto(firstPhoto);
        photoCollection.addSlide(slide);

        addNextPhoto(firstPhoto);
    }

    private void addNextPhoto(Photo photo) {
        for (Photo comparePhoto : photoList) {
            if (comparePhoto.isNotUsed() && photo.isHorizontal()) {
                if (photo.compareTo(comparePhoto) > 1) {
                    System.out.println("Photo found");
                    Slide slide = new Slide();
                    slide.addPhoto(comparePhoto);
                    photo.markAsUsed();
                    photoCollection.addSlide(slide);
                }
            }
        }
    }
}
