import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVLoader {

    File file;

    public CSVLoader(String path) {
        this.file = new File(path);
    }

    public List<List<String>> read() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

//            StringBuffer stringBuffer = new StringBuffer();


            // Array that contains all the rows
            List<List<String >> csvRows = new ArrayList<>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
//                stringBuffer.append(line);
//                stringBuffer.append("\n");

                String[] row = line.split(",");
                List<String> currentRow = Arrays.asList(row);

                csvRows.add(currentRow);
            }

            fileReader.close();

//            System.out.println(stringBuffer.toString());

            return csvRows;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
