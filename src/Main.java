import java.util.ArrayList;
import java.util.List;

public class Main {

    public static CPUDataSource dataSource;

    public static void main(String[] args) {

        String csvFile = args[0];
        CSVLoader csvLoader = new CSVLoader(csvFile);

        dataSource = new CPUDataSource(csvLoader.read());
        int quantum = Integer.parseInt(args[1]);

        CPU cpu = new CPU(dataSource, quantum);
        cpu.start();
    }
}
