import java.util.ArrayList;
import java.util.List;

public class Main {

    public static CPUDataSource dataSource;

    public static void main(String[] args) {
        String csvFile = args[0];
        int quantum = Integer.parseInt(args[1]);

        if (quantum <= 0) {
            System.out.println("Please try again with a quantum greater than 0");
            return;
        }

        CSVLoader csvLoader = new CSVLoader(csvFile);

        dataSource = new CPUDataSource(csvLoader.read());

        CPU cpu = new CPU(dataSource, quantum);
        cpu.start();
    }
}
