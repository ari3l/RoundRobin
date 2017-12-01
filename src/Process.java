/**
 * Created by ariel on 11/25/17.
 */
public class Process {

    /**
     * ID used to identify the process
     */
    private int id;

    /**
     * Arrival time of the process
     */
    private int arrivalTime;

    /**
     * The amount of time the process needs to execute
     * Note: This field is mutated by the scheduler when the process is executed.
     * The original execution time is stored in originalBurstTime
     */
    private int executionTime;

    /**
     * Completion time of the process. This is set by the CPU
     */
    private int completion;

    /**
     * Original burst time for use in calculations
     */
    private final int originalBurstTime;

    public Process(int id, int arrivalTime, int executionTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.executionTime = executionTime;

        // Store a copy of the burst time for use in calculations
        this.originalBurstTime = executionTime;
    }


    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }

    public int waitingTime() {
        return turnaroundTime() - originalBurstTime;
    }

    public int turnaroundTime() {
        return completion - arrivalTime;
    }

    @Override
    public String toString() {
        return "<P" + this.id + " Execution Time: " + this.executionTime + " Arrival Time: " + arrivalTime + ">";
    }

    public int getID() {
        return this.id;
    }
}
