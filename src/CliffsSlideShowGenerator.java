import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CliffsSlideShowGenerator implements SlideShowGenerator {

    private List<Slide> slideList;
    private List<Photo> photoList;
    private PhotoCollection slideShow;

    @Override
    public PhotoCollection generateSlideShow(PhotoCollection photoCollection) {

        photoList = photoCollection.getPhotoCollection();
        slideList = new ArrayList<>();
        slideShow = new PhotoCollection();

        addHorizontalPhotos();
        combineAndAddVerticalPhotos();

        composeSlideshow();

        return slideShow;
    }

    private void combineAndAddVerticalPhotos() {
        if (photoList.size() > 0) {
            int entropy;
            Photo actualPhoto = photoList.get(0);
            boolean completed = false;
            System.out.println("Combining Vertical Photos...");
            while (!completed) {
                if (actualPhoto.isVertical()) {
                    for (Photo comparedPhoto : photoList) {
                        if (comparedPhoto.isVertical()) {
                            entropy = actualPhoto.getNumberOfTagsForVerticals(comparedPhoto);
                            if (entropy > actualPhoto.getBestMatchEntropy()) {
                                actualPhoto.setBestMatch(comparedPhoto, entropy);
                            }
                        }
                    }
                    if (actualPhoto.getBestMatchEntropy() > 0) {
                        Slide slide = new Slide();
                        slide.addPhoto(actualPhoto);
                        slide.addPhoto(actualPhoto.getBestMatch());
                        slideList.add(slide);
                        photoList.remove(actualPhoto);
                        photoList.remove(actualPhoto.getBestMatch());
                    } else {
                        photoList.remove(actualPhoto);
                    }
                    if (photoList.size() > 1) {
                        actualPhoto = photoList.get(0);
                    } else {
                        completed = true;
                    }
                } else {
                    throw new IllegalArgumentException("Error while combining Vertical photos: List also contains " + "Horizontal photographs");
                }
            }
        }
    }

    private void addHorizontalPhotos() {
        Iterator<Photo> iterator = photoList.iterator();
        Photo photo;
        System.out.println("Adding Horizontal Photos...");
        while (iterator.hasNext()) {
            photo = iterator.next();
            if (photo.isHorizontal()) {
                Slide slide = new Slide();
                slide.addPhoto(photo);
                iterator.remove();
                slideList.add(slide);
            }
        }
    }

    private void composeSlideshow() {
        if (slideList.size() > 0) {
            System.out.println("Composing Slideshow...");
            int entropy;
            boolean completed = false;

            Slide actualSlide = slideList.get(0);
            slideList.remove(actualSlide);
            slideShow.addSlide(actualSlide);

            while (!completed) {
                for (Slide comparedSlide : slideList) {
                    entropy = actualSlide.compareTo(comparedSlide);
                    if (entropy > actualSlide.getBestMatchEntropy()) {
                        actualSlide.setBestMatch(comparedSlide, entropy);
                    }
                }
                if (actualSlide.getBestMatchEntropy() > 0) {
                    actualSlide = actualSlide.getBestMatch();
                    slideShow.addSlide(actualSlide);
                    slideList.remove(actualSlide);
                } else if (slideList.size() > 1) {
                    actualSlide = slideList.get(0);
                    slideList.remove(actualSlide);
                    slideShow.addSlide(actualSlide);
                } else {
                    completed = true;
                }
            }
        }
    }
}
