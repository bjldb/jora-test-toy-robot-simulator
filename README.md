This project was created and submitted as a solution for the Jora Toy Robot Simulator Exercise.

# TESTING THIS PROJECT

## A. Pre-requisites

### 1. Java (JDK 8 or higher) 
  - Installation instructions found [here](https://www.oracle.com/technetwork/java/javase/documentation/index.html).
### 2. Maven 
  - Installation instructions found [here](https://maven.apache.org/install.html).
### 3. Git 
  - Installation instructions found [here](https://www.atlassian.com/git/tutorials/install-git).
	
NOTE: In order to use command line for this project, make sure that environment variables Path, JAVA_HOME, M2_HOME, etc are configured correctly

## B. Running the tests (Command Line or Bash (e.g. GitBash))

### 1. Clone this project
  - $ git clone https://github.com/bjldb/jora-test-toy-robot-simulator.git
### 2. Move to the project directory and run maven install to generate jar file
  - $ cd jora-test-toy-robot-simulator
  - $ mvn clean install
  - Generated jar file is under directory "target": ToyRobotSimulator.jar
### 3. Execute generated jar file to read commands from test command sets in testdata folder
  - $ java -jar target/ToyRobotSimulator.jar "testdata/singleMove.txt"
  - sample above that goes with the project should output something like below:

```
PLACE 0,0,NORTH
MOVE
REPORT: 0,1,NORTH
```
