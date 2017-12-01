import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ariel on 11/25/17.
 */
public class CPUDataSource {

    Map<Integer, Process> processMap;
    
    /**
     * Constructor to be used for CSV fies List from CSVLoader
     */

    public CPUDataSource(List<List<String>> processes) {

        this.processMap = new HashMap<>();

        Iterator<List<String>> iterator = processes.iterator();
        int rowCount = 0;
        while (iterator.hasNext()) {
            List<String> row = iterator.next();

            // Ignore the first row (the headers)
            if (rowCount == 0) {
                rowCount++;
                continue;
            }

            // Process id
            int pid = Integer.parseInt(row.get(0));

            // Arrival time
            int arrive = Integer.parseInt(row.get(1));

            // Burst time
            int burstTime = Integer.parseInt(row.get(2));

            Process process = new Process(pid, arrive, burstTime);

            processMap.put(arrive, process);

            rowCount++;
        }
    }

    public int getProcessCount() {
        return processMap.size();
    }

    public Process processArrive(int arrivalTime) {
        return processMap.get(arrivalTime);
    }
}
