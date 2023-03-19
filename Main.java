import java.util.ArrayList; // Import the ArrayList class for dynamically resizable lists
import java.util.Scanner;  // Import the Scanner class for user input

public class Main {
  static Scanner userInput = new Scanner(System.in); // module for user input
  
  static ArrayList<String> tasks = new ArrayList<String>();
  static ArrayList<Boolean> done = new ArrayList<Boolean>();  
  static ArrayList<Boolean> important = new ArrayList<Boolean>();  
  static String input;
  static String[] splitInput;
  
    // Color constants
  static final String RESET = "\u001B[0m";
  static final String RED = "\u001B[31m";
  static final String GREEN = "\u001B[32m";
  static final String YELLOW = "\u001B[33m";
  static final String BLUE = "\u001B[34m";
  static final String MAGENTA = "\u001B[35m";
  static final String CYAN = "\u001B[36m";
  static final String WHITE = "\u001B[37m";
  
  public static void main(String[] args) {
    while (true) {
      System.out.print("\033[H\033[2J"); // cls
      System.out.println("###################\n#                 #\n#  MY TO-DO LIST  #\n#                 #\n###################\n"); // stylized title 
      for (int i = 0; i < tasks.size(); i++) {
        if (important.get(i)) {
          System.out.print(RED);
        }
        if (done.get(i)) {
          System.out.print("[X] " + tasks.get(i));
        } else {
          System.out.print("[ ] " + tasks.get(i));
        }
        System.out.println(RESET);
      }
      
      input = userInput.nextLine( ); // Read user imput
      
      if (input.startsWith("/")){
        try {
          splitInput = input.split(" ");
          switch (splitInput[0]) {
            case "/add":
              appendTask(lastArg(1));
              break;
            case "/insert":
              insertTask(splitInput[1], lastArg(2));
              break;
            case "/del":
              deleteTask(splitInput[1]);
              break;
            case "/edit":
              editTask(splitInput[1], lastArg(2));
              break;
            case "/done":
              doneTask(splitInput[1]);
              break;
            case "/!":
              importantTask(splitInput[1]);
              break;
            case "/clear":
              clearTasks();
              break;
            default:
              appendTask(input);
          }
        } catch (Exception e) {
          appendTask(input);
        }
      } else {
        appendTask(input);
      }
    }
  }
  
  static void appendTask(String task) {
    tasks.add(task);
    done.add(false);
    important.add(false);
  }
  static void insertTask(String indexStr, String task) {
    int index = Integer.parseInt(indexStr) - 1;
    tasks.add(index, task);
    done.add(index, false);
    important.add(index, false);
  }
  static void editTask(String indexStr, String newTask) {
    int index = Integer.parseInt(indexStr);
    tasks.set(index - 1, newTask);
  }
  static void deleteTask(String indexStr) {
    int index = Integer.parseInt(indexStr);
    tasks.remove(index - 1);
    done.remove(index - 1);
    important.remove(index - 1);
  }
  static void doneTask(String indexStr) {
    int index = Integer.parseInt(indexStr) - 1;
    if (done.get(index)) {
      done.set(index, false);
    } else {
      done.set(index, true);
    }
  }
  static void importantTask(String indexStr) {
    int index = Integer.parseInt(indexStr) - 1;
    if (important.get(index)) {
      important.set(index, false);
    } else {
      important.set(index, true);
    }
  }
  static void clearTasks() {
    tasks.clear();
    done.clear();
    important.clear();
  }
  
  static String lastArg(int index) {
    int startIndex = 0;
    for (int i = 0; i < index; i++) {
      startIndex += splitInput[i].length() + 1;
    }
    return input.substring(startIndex);
  }
}