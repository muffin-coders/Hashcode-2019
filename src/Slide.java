import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class Slide {

    private List<Photo> photos = new ArrayList<>();
    private List<Integer> photosId = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private Slide bestMatch;
    private int bestMatchEntropy = 0;

    public void addPhoto(Photo photo) {
        photos.add(photo);
        photosId.add(photo.getId());
        tags.addAll(photo.getTags());
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public List<String> getAllTags() {
        List<String> tags = new ArrayList<>();
        for (Photo photo : photos) {
            tags.addAll(photo.getTags());
        }
        return tags;
    }

    public int compareTo(Slide slide) {
        int entropy = 0;
        for (String tag : tags) {
            for (String comparedTag : slide.getTags()) {
                if (tag.equals(comparedTag)) {
                    entropy++;
                }
            }
        }
        int entropyActual = tags.size() - entropy;
        int entropyCompared = slide.getNumberOfTags() - entropy;

        return min(min(entropyActual, entropyCompared), entropy);
    }

    public List<String> getTags() {
        return tags;
    }

    public int getNumberOfTags() {
        return tags.size();
    }

    public void setBestMatch(Slide bestMatch, int bestMatchEntropy) {
        this.bestMatch = bestMatch;
        this.bestMatchEntropy = bestMatchEntropy;
    }

    public Slide getBestMatch() {
        return bestMatch;
    }

    public int getBestMatchEntropy() {
        return bestMatchEntropy;
    }
}

