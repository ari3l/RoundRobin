rebuild: clean compile

clean:
	@rm -rf CPU.class *~
	@rm -rf CPUDataSource.class *~
	@rm -rf CSVLoader.class *~
	@rm -rf Main.class *~
	@rm -rf Process.class *~

compile: Main.java
	@javac Main.java


test: rebuild
	@java Main

exec:
	# change the quantum and csv file as needed
	@java Main processes.csv 3