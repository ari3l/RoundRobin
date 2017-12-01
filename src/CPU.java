import java.util.*;

public class CPU {

    private CPUDataSource dataSource;

    private int quantum;

    public CPU(CPUDataSource dataSource, int quantum) {
        this.dataSource = dataSource;
        this.quantum = quantum;
    }

    public void start()
    {
        // Count of the finished processes
        int finishedProcessCount = 0;

        // Total count of the processes that need to be executed
        int processCount = dataSource.getProcessCount();

        // Keep track of the time
        int time = 0;

        // Track amount of context switches
        int contextSwitches = 0;

        // CPU Utilization
        int utilization = 0;

        // Request Queue
        Queue<Process> requestQueue = new LinkedList<>();

        // Keeps track of the currently executing process quantum
        int currentRemainingQauntum = quantum;

        /**
         * Array of the completed processes
         */
        List<Process> completedProcesses = new ArrayList<>();

        while (finishedProcessCount != processCount)
        {

            /**
             * Capture arriving processes and add them to the queue
             */
            Process arriving = dataSource.processArrive(time);

            if (arriving != null)
            {
                requestQueue.add(arriving);
            }

            /**
             * Process execution
             */
            Process p = requestQueue.peek();

            // No process, just a wasted cycle
            if (p == null)
            {
                time++;
                continue;
            }

            execute(p);
            currentRemainingQauntum--;
            time++;
            System.out.println("time = " + time + " P" + p.getID() + " is running");
            utilization++;

            /**
             * Context Switching
             */

            // If the execution time runs out
            if (p.getExecutionTime() == 0)
            {
                // Reset the quantum
                currentRemainingQauntum = quantum;
                finishedProcessCount++;

                // Set its completion time
                p.setCompletion(time);

                // Remove the process from the queue and add it to the completed array
                completedProcesses.add(requestQueue.poll());

                System.out.println("time = " + time + " P" + p.getID() + " completed");
                System.out.println("Context Switch");
                System.out.println();

                contextSwitches++;
            }

            // If the quantum expired
            else if (currentRemainingQauntum == 0)
            {
                // Reset quantum counter
                currentRemainingQauntum = quantum;

                // If the process still has time, add it to the back of the queue
                if (p.getExecutionTime() > 0)
                {
                    // Add the process to the back of the queue
                    requestQueue.add(requestQueue.poll());
                    System.out.println("Context Switch");
                    System.out.println();
                }

                else // Otherwise, it has completed
                {
                    finishedProcessCount++;
                    currentRemainingQauntum = quantum;
                    System.out.println("time = " + time + " P" + p.getID() + " has completed");
                    System.out.println("Context Switch");
                    System.out.println();

                    p.setCompletion(time);
                    completedProcesses.add(requestQueue.poll());
                }

                contextSwitches++;
            }
        }

        /**
         * Calculate some statistics
         */

        Iterator<Process> completedProcessIterator = completedProcesses.iterator();

        int totalTurnAround = 0;
        int totalWaitingTime = 0;

        while (completedProcessIterator.hasNext())
        {
            Process p = completedProcessIterator.next();
            totalTurnAround += p.turnaroundTime();
            totalWaitingTime += p.waitingTime();
        }

        float averageTurnAround = (float) totalTurnAround / processCount;
        float averageWaitingTime = (float) totalWaitingTime / processCount;
        float cpuUtilization = (float) (utilization / time) * 100;
        float throughput = (float) processCount / time;

        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("Time Quantum: " + quantum);
        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turn Around Time: " + averageTurnAround);
        System.out.println("CPU Utilization: " + cpuUtilization + "%");
        System.out.println("CPU Throughout: " + throughput);
        System.out.println("Number of Context Switches: "  + contextSwitches);
        System.out.println("-----------------------------------------------------");
    }


    /**
     * Helper method to decrement one unit of time from a Process
     * @param process Process to remove 1 (one) unit of time from
     */
    private void execute(Process process) {
        int executionTime = process.getExecutionTime();
        process.setExecutionTime(executionTime - 1);
    }
}