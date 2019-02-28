import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class OutputReader {

    public static void generateOutput(PhotoCollection photoCollection, String filename) throws IOException {

        List<Slide> slideshow = photoCollection.getSlideshow();

        String fileData = slideshow.size() + "\n";

        for (Slide slide : slideshow) {
            List<Photo> photos = slide.getPhotos();
            for (Photo photo : photos) {
                fileData += photo.getId() + " ";
            }
            fileData += " ";
        }

        FileOutputStream fos = new FileOutputStream("output_" + filename + ".txt");
        fos.write(fileData.getBytes());
        fos.flush();
        fos.close();
    }

}
