import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnotherSlideShowGenerator implements SlideShowGenerator {
    private List<Photo> photoList;
    private PhotoCollection photoCollection;

    @Override
    public void generateSlideShow(PhotoCollection photoCollection) {
        this.photoCollection = photoCollection;
        photoList = photoCollection.getPhotoCollection();

        List<Slide> allSlides = new ArrayList<>();

        List<Photo> allVerticalPhotos = new ArrayList<>();

        for (Photo photo : photoList) {
            if (!photo.isVertical()) {
                allVerticalPhotos.add(photo);
            } else {
                Slide slide = new Slide();
                slide.addPhoto(photo);
                allSlides.add(slide);
            }
        }

        for (int i = 0; i < allVerticalPhotos.size(); i++) {
            Photo left = allVerticalPhotos.get(i);
            if (left.isUsed()) {
                continue;
            }
            Photo right = null;
            int cnt = 0;
            for (int j = i + 1; j < allVerticalPhotos.size(); j++) {
                if (right == null) {
                    if (allVerticalPhotos.get(j).isUsed()) {
                        continue;
                    }
                    right = allVerticalPhotos.get(j);
                } else {
                    if (right.isUsed()) {
                        continue;
                    }
                    Set<String> tagsBefore = new HashSet<>();
                    tagsBefore.addAll(left.getTags());
                    tagsBefore.addAll(right.getTags());

                    Set<String> tagsAfter = new HashSet<>();
                    tagsAfter.addAll(left.getTags());
                    tagsAfter.addAll(allVerticalPhotos.get(j).getTags());

                    if (tagsBefore.size() < tagsAfter.size()) {
                        right = allVerticalPhotos.get(j);
                    }
                }
                System.out.println("blub" + cnt++);
            }
            Slide slide = new Slide();
            slide.addPhoto(left);
            left.markAsUsed();
            slide.addPhoto(right);
            right.markAsUsed();

            allSlides.add(slide);
        }

        Slide currentSlide = allSlides.get(0);
        photoCollection.addSlide(currentSlide);

        allSlides.remove(0);

        while (!allSlides.isEmpty()) {
            Slide nextSlide = null;
            int indexNextSlide = 0;
            int cnt = 0;
            for (Slide slide : allSlides) {
                if (nextSlide == null) {
                    nextSlide = slide;
                    continue;
                }
                if (currentSlide.compareTo(slide) > currentSlide.compareTo(nextSlide)) {
                    nextSlide = slide;
                    indexNextSlide = cnt;
                }
                cnt++;
            }

            photoCollection.addSlide(nextSlide);

            allSlides.remove(indexNextSlide);
            System.out.println("added" + indexNextSlide);
        }
    }

}
