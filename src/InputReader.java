import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    private final String file;
    private boolean isFistline = true;
    private int numberOfPhotos;
    private PhotoCollection collection;
    private int picCounter = 1;
    private int horizontalCount = 0;
    private int verticalCount = 0;

    public InputReader(String file) {
        this.file = file;
    }

    public PhotoCollection read() {
        collection = new PhotoCollection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("res/" + file))))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isFistline) {
                    numberOfPhotos = Integer.parseInt(line);
                    isFistline = false;
                } else {
                    boolean isHorizontal = false;
                    String[] detail = line.split(" ");
                    if (detail[0].toUpperCase().contains("H")) {
                        isHorizontal = true;
                        horizontalCount++;
                    } else{
                        verticalCount++;
                    }

                    int numberOfTags = Integer.parseInt(detail[1]);
                    List<String> tagList = new ArrayList<>();
                    for (int i = 0; i < numberOfTags; i++) {
                        tagList.add(detail[2 + i]);
                    }
                    Photo photo = new Photo(tagList, picCounter, isHorizontal);
                    collection.addPhoto(photo);
                    picCounter++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(collection.getPhotoCollection().size() == numberOfPhotos){
            System.out.println("All pictures in list");
        }
        collection.setHorizontalCount(horizontalCount);
        collection.setVerticalCount(verticalCount);
        return collection;
    }

    public void test() {
        PhotoCollection collectionTmp = read();
        for (Photo photo: collectionTmp.getPhotoCollection()) {
            System.out.println(photo.getId() + " " + photo.getTags() + " " + photo.isHorizontal());
        }
    }

    public static void main(String[] args){
        InputReader reader = new InputReader("a_example.txt");
        reader.test();
    }
}
