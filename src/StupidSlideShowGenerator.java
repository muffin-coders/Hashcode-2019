import java.util.List;

public class StupidSlideShowGenerator implements SlideShowGenerator {
    List<Photo> photoList;
    private PhotoCollection photoCollection;

    @Override
    public void generateSlideShow(PhotoCollection photoCollection) {
        this.photoCollection = photoCollection;
        photoList = photoCollection.getPhotoCollection();

        int i = 0;
        Photo firstPhoto = photoList.get(i);

        while (firstPhoto.isVertical()) {
            firstPhoto = photoList.get(++i);
        }

        Slide slide = new Slide();
        slide.addPhoto(firstPhoto);
        photoCollection.addSlide(slide);

//        while (true) {
//            addNextPhoto(firstPhoto);
//        }
    }

    private Photo addNextPhoto(Photo photo) {
        for (Photo comparePhoto : photoList) {
            if (comparePhoto.isNotUsed() && comparePhoto.isHorizontal()) {
                if (photo.compareTo(comparePhoto) > 1) {
                    System.out.println("Photo found");
                    Slide slide = new Slide();
                    slide.addPhoto(comparePhoto);
                    photo.markAsUsed();
                    photoCollection.addSlide(slide);
                    return comparePhoto;
                }
            }
        }
        return null;
    }
}
