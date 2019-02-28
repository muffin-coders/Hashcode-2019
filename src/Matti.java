import java.util.List;

public class Matti implements SlideShowGenerator {
    private List<Photo> photoList;
    private PhotoCollection photoCollection;

    @Override
    public void generateSlideShow(PhotoCollection photoCollection) {
        this.photoCollection = photoCollection;
        photoList = photoCollection.getPhotoCollection();

        int i = 0;
        Photo nextNode = photoList.get(i);
        while (nextNode.isVertical()) {
            i++;
            if (i > photoList.size()) {
                new StupidVerticalSlideShowGenerator().generateSlideShow(photoCollection);
            }
            nextNode = photoList.get(i);
        }

        if (nextNode.isHorizontal()) {
            nextNode.markAsUsed();
            add(nextNode);

            for (i = 0; i < photoList.size(); i++) {
                Photo previousNode = nextNode;
                nextNode = searchNext(nextNode);
                if (nextNode == null) {
                    nextNode = searchNextVertical(previousNode);
                    if (nextNode == null)
                        return;
                    Slide slide = new Slide();

                    Photo nextNode2 = searchNextVertical(nextNode);

                    if (nextNode2 == null)
                        return;
                    slide.addPhoto(nextNode);
                    nextNode.markAsUsed();
                    slide.addPhoto(nextNode2);
                    nextNode2.markAsUsed();

                    photoCollection.addSlide(slide);
                    nextNode = findNextFree();
                }

                add(nextNode);
            }
        }
    }

    private Photo findNextFree() {
        for (Photo found : photoList) {
            if (found.isNotUsed()) {
                found.markAsUsed();
                return found;
            }
        }
        return null;
    }

    private Photo searchNextVertical(Photo startNode) {
        if (startNode == null)
            return null;
        for (Photo comparePhoto : photoList) {
            if (!comparePhoto.isNotUsed() || comparePhoto.isHorizontal()) {
                continue;
            }
            if (startNode.compareTo(comparePhoto) > 0) {
                comparePhoto.markAsUsed();
                return comparePhoto;
            }
        }
        return null;
    }

    private void add(Photo startNode) {
        Slide slide = new Slide();
        slide.addPhoto(startNode);
        photoCollection.addSlide(slide);
    }

    private Photo searchNext(Photo startNode) {
        for (Photo comparePhoto : photoList) {
            if (!comparePhoto.isNotUsed() || comparePhoto.isVertical()) {
                continue;
            }
            if (startNode.compareTo(comparePhoto) > 8) {
                comparePhoto.markAsUsed();
                return comparePhoto;
            }
        }
        return null;
    }
}
