import java.util.List;

import static java.lang.Math.min;

public class Photo {

    private List<String> tags;
    private int id;
    private boolean used = false;
    private boolean isHorizontal;

    public Photo(List<String> tags, int id, boolean isHorizontal) {
        this.tags = tags;
        this.id = id;
        this.isHorizontal = isHorizontal;
    }

    public int compareTo(Photo photo) {
        int entropy = 0;
        for (String photoTag : tags) {
            for (String comparedPhotoTag : photo.getTags()) {
                if (photoTag.equals(comparedPhotoTag)) {
                    entropy++;
                }
            }
        }
        int entropyActual = tags.size() - entropy;
        int entropyCompared = photo.getNumberOfTags() - entropy;

        return min(entropyActual, entropyCompared);
    }

    public int getNumberOfTagsForVerticals(Photo photo) {
        int overlapping = 0;
        for (String photoTag : tags) {
            for (String comparedPhotoTag : photo.getTags()) {
                if (photoTag.equals(comparedPhotoTag)) {
                    overlapping++;
                }
            }
        }
        return tags.size() + photo.getNumberOfTags() - overlapping * 2;
    }

    public List<String> getTags() {
        return tags;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfTags() {
        return tags.size();
    }

    public boolean isNotUsed() {
        return !used;
    }

    public void markAsUsed() {
        used = true;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isVertical() {
        return !isHorizontal;
    }
}
