import java.util.*;

public class BetterSlideShowGenerator implements SlideShowGenerator {
    List<Photo> photoList;
    private PhotoCollection photoCollection;
    private PhotoCollection verticalCollection;
    private PhotoCollection slidesCollection;
    private int averageTagCount = 0;


    @Override
    public void generateSlideShow(PhotoCollection photoCollection) {
        this.photoCollection = photoCollection;
        setVerticalCollection();
        averageTagCount = calculateAverageTagCount();
        createVerticalSlides();
         createHorizontalSlides();
         matchingSlides();
//        photoList = photoCollection.getPhotoCollection();
//
//        Photo firstPhoto = photoList.get(0);
//        Slide slide = new Slide();
//        slide.addPhoto(firstPhoto);
//        photoCollection.addSlide(slide);
//
//        addNextPhoto(firstPhoto);
        System.out.println(calculateAverageTagCount());
        System.out.println(calculateMedianTagCount());
    }

    private void matchingSlides() {
        slidesCollection = new PhotoCollection();
        int slideIndex = 0;
        int maxSlideValue = 0;
        int bestMatchId = 0;
        while (slidesCollection.getSlideCollection().size() < photoCollection.getSlideCollection().size()) {
            maxSlideValue = 0;
            bestMatchId = -1;

            for (int i = 0; i < photoCollection.getSlideCollection().size(); i++) {
                if (i != slideIndex && !photoCollection.getSlideCollection().get(i).isUsed()) {
                    int tagCount2Slides = photoCollection.getSlideCollection().get(slideIndex).compareTo(photoCollection.getSlideCollection().get(i));
                    if (maxSlideValue < tagCount2Slides) {
                        maxSlideValue = tagCount2Slides;
                        bestMatchId = i;
                    }
                }
            }
            if(bestMatchId == -1){
                slidesCollection.addSlide(photoCollection.getSlideCollection().get(slideIndex));
                photoCollection.getSlideCollection().get(slideIndex).markAsUsed();
            } else{
                slidesCollection.addSlide(photoCollection.getSlideCollection().get(slideIndex));
                photoCollection.getSlideCollection().get(slideIndex).markAsUsed();
                slidesCollection.addSlide(photoCollection.getSlideCollection().get(bestMatchId));
                photoCollection.getSlideCollection().get(bestMatchId).markAsUsed();
            }
            slideIndex++;
        }

    }

    private void createHorizontalSlides() {
        for(Photo photo : photoCollection.getPhotoCollection()){
            if(photo.isHorizontal() && photo.isNotUsed()){
                Slide slide = new Slide();
                slide.addPhoto(photo);
                photo.markAsUsed();
                photoCollection.addSlide(slide);
            }
        }
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

    private int calculateAverageTagCount() {
        int tagCount = 0;
        for (Photo photo : photoCollection.getPhotoCollection()) {
            tagCount = tagCount + photo.getNumberOfTags();
        }
        return (tagCount / (photoCollection.getHorizontalCount() + photoCollection.getVerticalCount()));
    }

    private int calculateMedianTagCount() {
        HashMap<Integer, Integer> tagMap = new HashMap<>();
        for (Photo photo : photoCollection.getPhotoCollection()) {
            int tagCount = photo.getNumberOfTags();
            if (tagMap.containsKey(tagCount)) {
                int value = tagMap.get(tagCount);
                tagMap.replace(tagCount, value, value + 1);
            }
        }
        Integer maxTagCount = 0;
        Integer maxKey = 0;

        Iterator it = tagMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            if ((Integer) pair.getValue() > maxTagCount) {
                maxTagCount = (Integer) pair.getValue();
                maxKey = (Integer) pair.getKey();
            }
        }
        return maxTagCount;
    }

    private void setVerticalCollection() {
        verticalCollection = new PhotoCollection();
        for (Photo photo : photoCollection.getPhotoCollection()) {
            if (photo.isVertical()) {
                verticalCollection.addPhoto(photo);
            }
        }
    }

    private void createVerticalSlides() {
        int photoIndex = 0;
        int maxIndividualTags = 0;
        int bestMatchId = -1;
        while (verticalCollection.hasNotUsedPhotos()) {
            maxIndividualTags = 0;
            bestMatchId = -1;

            for (int i = 0; i < photoCollection.getVerticalCount(); i++) {
                if (i != photoIndex && verticalCollection.getPhotoCollection().get(i).isNotUsed()) {
                    int tagCount2pics = verticalCollection.getPhotoCollection().get(photoIndex).compareTo(verticalCollection.getPhotoCollection().get(i));
                    if (maxIndividualTags < tagCount2pics) {
                        maxIndividualTags = tagCount2pics;
                        bestMatchId = i;
                    }
                }
            }
            Slide slide = new Slide();
            slide.addPhoto(verticalCollection.getPhotoCollection().get(photoIndex));
            slide.addPhoto(verticalCollection.getPhotoCollection().get(bestMatchId));
            verticalCollection.getPhotoCollection().get(photoIndex).markAsUsed();
            verticalCollection.getPhotoCollection().get(bestMatchId).markAsUsed();

            photoCollection.addSlide(slide);
            photoIndex++;
        }

    }
}
