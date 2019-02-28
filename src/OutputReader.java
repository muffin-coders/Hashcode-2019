import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class OutputReader {

    public static void generateOutput(PhotoCollection photoCollection, String filename) throws IOException {

        List<Slide> slideCollection = photoCollection.getSlideCollection();

        String fileData = slideCollection.size() + "\n";

        for (Slide slide : slideCollection) {
            List<Photo> photos = slide.getPhotos();
            for (Photo photo : photos) {
                fileData += photo.getId() + " ";
            }
            fileData += "\n";
        }

        FileOutputStream fos = new FileOutputStream("output_" + filename + ".txt");
        fos.write(fileData.getBytes());
        fos.flush();
        fos.close();
    }

}
