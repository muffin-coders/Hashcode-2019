import java.io.*;

public class InputReader {
    private final String file;
    private Pizza pizza;
    private boolean isFistline = true;
    private int rows, cols, minNrOfIngredient, maxNrOfCells;
    private char[][] ingrediants;

    public InputReader(String file) {
        this.file = file;
    }

    public Pizza read() {
        int rowCounter = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("res/" + file))))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isFistline) {
                    createPizza(line);
                    isFistline = false;
                } else {
                    addIngrediants(line, rowCounter);
                    rowCounter++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
<<<<<<< Updated upstream

        return new Pizza(rows, cols, minNrOfIngredient, maxNrOfCells, ingrediants);
=======
        pizza = new Pizza(rows, cols, minNrOfIngredient, maxNrOfCells, ingrediants);
        return pizza;
>>>>>>> Stashed changes
    }

    private void addIngrediants(String line, int rowCounter) {
        for (int i = 0; i < cols; i++) {
            ingrediants[rowCounter][i] = line.charAt(i);
        }
    }

    private void createPizza(String line) {
        String[] firstLine = line.split(" ");
        rows = Integer.parseInt(firstLine[0]);
        cols = Integer.parseInt(firstLine[1]);
        minNrOfIngredient = Integer.parseInt(firstLine[2]);
        maxNrOfCells = Integer.parseInt(firstLine[3]);
        ingrediants = new char[rows][cols];
    }

    private void test() {
        for (int i = 0; i < ingrediants.length; i++) {
            for (int j = 0; j < ingrediants[0].length; j++) {
                System.out.print(ingrediants[i][j]);
            }
            System.out.println();
        }
    }
}
