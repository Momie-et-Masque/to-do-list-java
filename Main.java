import java.util.ArrayList; // Import the ArrayList class for dynamically resizable lists
import java.util.Scanner;  // Import the Scanner class for user input

public class Main {
  static ArrayList<String> tasks = new ArrayList<String>(); // Create an ArrayList object
  static Scanner userInput = new Scanner(System.in);  // Create a Scanner object
  static String input; // Initialize the input variable
  static String[] splitInput; // Initialize the splitInput variable
  static String command; // Initialize the command variable
  
  public static void main(String[] args) {
    while (true) {
      System.out.print("\033[H\033[2J"); // cls
      System.out.println("###################\n#                 #\n#  MY TO-DO LIST  #\n#                 #\n###################\n"); // stylized title 
      for (int i = 0; i < tasks.size(); i++) {
        System.out.println(tasks.get(i));
      }
      
      input = userInput.nextLine(); // Read user imput
      
      if (input.startsWith("/")){
        splitInput = input.split(" ");
        switch (splitInput[0]) {
          case "/add":
            appendTask(lastArg(1));
            break;
          case "/del":
            deleteTask(splitInput[1]);
            break;
          default:
            appendTask(input);
        }
      } else {
        appendTask(input);
      }
    }
  }
  
  static void appendTask(String task) {
    tasks.add(task);
  }
  static void editTask(String task) {
    tasks.add(task);
  }
  static void deleteTask(String indexStr) {
    try {
      int index = Integer.parseInt(indexStr);
      tasks.remove(index - 1);
    } catch (Exception e) {
      // error : argument cannot be converted into int or index given is incorrect
    }
  }
  static String lastArg(int index) {
    int startIndex = 0;
    for (int i = 0; i < index; i++) {
      startIndex += splitInput[i].length() + 1;
    }
    return input.substring(startIndex);
  }
}
