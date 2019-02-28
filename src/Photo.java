import java.util.List;

public class Photo {

    private List<String> tags;
    private int id;
    private boolean used = false;
    private boolean isHorizontal;

    public Photo(List<String> tags, int id, boolean isHorizontal) {
        this.tags = tags;
        this.id = id;
        this.used = used;
        this.isHorizontal = isHorizontal;
    }

    public double compareTo(Photo photo) {
        double entropy = 0;
        for (String photoTag : tags) {
            for (String comparedPhotoTag : photo.getTags()) {
                if (photoTag.equals(comparedPhotoTag)) {
                    entropy++;
                }
            }
        }
        return entropy;
    }

    public List<String> getTags() {
        return tags;
    }

    public int getNumberOfTags(){
        return tags.size();
    }
}
