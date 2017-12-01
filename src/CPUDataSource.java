import java.util.*;

/**
 * Created by ariel on 11/25/17.
 */
public class CPUDataSource {

    private Map<Integer, Queue<Process>> processMap;

    private int processCount;

    /**
     * Constructor to be used for CSV fies List from CSVLoader
     */

    public CPUDataSource(List<List<String>> processes) {

        this.processMap = new HashMap<>();

        int processCount = 0;

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

            Queue<Process> arriving = processMap.get(arrive);

            if (arriving != null) {
                arriving.add(process);
            } else {
                Queue<Process> queue = new LinkedList<>();
                queue.add(process);
                processMap.put(arrive, queue);
            }

            processCount++;
            rowCount++;
        }

        this.processCount = processCount;
    }

    public int getProcessCount() {
        return processCount;
    }

    /**
     * Returns a queue of all arriving processes for a give time
     * @param arrivalTime The arrival time requested
     * @return Queue of arriving processes
     */
    public Queue<Process> arrivingProcesses(int arrivalTime) {
        return processMap.get(arrivalTime);
    }
}
