import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] files = {"a_example.txt", "b_lovely_landscapes.txt", "c_memorable_moments.txt", "d_pet_pictures.txt", "e_shiny_selfies.txt"};
        SlideShowGenerator slideShowGeneratorStrategy = new StupidSlideShowGenerator();

        for (String file : files) {
            System.out.println("Input " + file);

            // Call Input Reader
            InputReader inputReader = new InputReader(file);
            PhotoCollection photoCollection = inputReader.read();

            slideShowGeneratorStrategy.generateSlideShow(photoCollection);

            // Call output writer
            OutputReader.generateOutput(photoCollection, slideShowGeneratorStrategy.getClass().getName() + "_" + file);
        }
    }

}
