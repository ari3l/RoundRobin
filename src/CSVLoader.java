import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVLoader {

    private File file;

    public CSVLoader(String path) {
        this.file = new File(path);
    }

    /**
     * Reads the CSV file and returns a List of strings representing each row
     * @return A List of strings representing each row
     */
    public List<List<String>> read() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Array that contains all the rows
            List<List<String >> csvRows = new ArrayList<>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {


                String[] row = line.split(",");
                List<String> currentRow = Arrays.asList(row);

                csvRows.add(currentRow);
            }

            fileReader.close();

            return csvRows;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
