import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] files = {"a_example.txt", "b_lovely_landscapes.txt", "c_memorable_moments.txt", "d_pet_pictures.txt", "e_shiny_selfies.txt"};
        SlideShowGenerator slideShowGeneratorStrategy = new CliffsSlideShowGenerator();

        for (String file : files) {
            System.out.println("Input " + file);

            // Call Input Reader
            InputReader inputReader = new InputReader(file);
            PhotoCollection photoCollection = inputReader.read();

            long startTime = System.currentTimeMillis();
            photoCollection = slideShowGeneratorStrategy.generateSlideShow(photoCollection);
            printTimeDifference(startTime);

            // Call output writer
            OutputReader.generateOutput(photoCollection, slideShowGeneratorStrategy.getClass().getName() + "_" + file);
        }
    }

    private static void printTimeDifference(long startTime){
        long difference = Math.abs(System.currentTimeMillis() - startTime);
        int minutes = (int) (difference / 60000);
        int seconds = (int) ((difference - minutes * 60000) / 1000);
        int milliseconds = (int) ((difference - minutes * 60000 - seconds * 1000));
        System.out.println("Composing successful! Required Time: " + minutes + "min " + seconds + "sec " + milliseconds + "millisec\n");
    }
}
