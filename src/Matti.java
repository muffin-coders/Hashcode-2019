import java.util.List;

public class Matti implements SlideShowGenerator {
    private List<Photo> photoList;
    private PhotoCollection photoCollection;

    @Override
    public void generateSlideShow(PhotoCollection photoCollection) {
        this.photoCollection = photoCollection;
        photoList = photoCollection.getPhotoCollection();

        Photo nextNode = photoList.get(photoList.size() / 2);

        if (nextNode.isHorizontal()) {
            add(nextNode);

            for (int i = 0; i < photoList.size() * 4; i++) {
                nextNode = searchNext(nextNode);
                if (nextNode == null) {
                    nextNode = searchNextVertical(nextNode);
                    add(nextNode);
                    nextNode = searchNextVertical(nextNode);
                }

                if (nextNode == null)
                    continue;
                System.out.println("photo found");
                add(nextNode);
            }
        }
    }

    private Photo searchNextVertical(Photo startNode) {
        for (Photo comparePhoto : photoList) {
            if (!comparePhoto.isNotUsed() || comparePhoto.isHorizontal()) {
                continue;
            }
            if (startNode.compareTo(comparePhoto) > 1) {
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
            if (startNode.compareTo(comparePhoto) > 1) {
                comparePhoto.markAsUsed();
                return comparePhoto;
            }
        }
        return null;
    }
}
