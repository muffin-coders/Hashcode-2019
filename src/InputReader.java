import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    private final String file;
    private boolean isFistline = true;
    private int numberOfPhotos;
    private PhotoCollection collection;
    private int picCounter = 1;

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
        return collection;
    }

    public void test() {
        PhotoCollection collectionTmp = read();
        for (Photo photo: collectionTmp.getPhotoCollection()) {
            System.out.println(photo.getTags());
        }
    }

    public static void main(String[] args){
        InputReader reader = new InputReader("a_example.txt");
        reader.test();
    }
}
