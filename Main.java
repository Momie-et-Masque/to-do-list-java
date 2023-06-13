import java.util.ArrayList; // Import the ArrayList class for dynamically resizable lists
import java.util.Scanner;  // Import the Scanner class for user input
import java.io.File;  // Import the File class for file handling
import java.io.FileWriter;   // Import the FileWriter class for file writing

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;  // Import the IOException class to handle errors

public class Main {
  static Scanner userInput = new Scanner(System.in); // module for user input
  
  static ArrayList<String> tasks = new ArrayList<String>();
  static ArrayList<Boolean> done = new ArrayList<Boolean>();  
  static ArrayList<Boolean> important = new ArrayList<Boolean>();  
  static String input;
  static String[] splitInput;
  static String currentFilename = "todo";

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
    if (args.length > 0) {
      currentFilename = args[0];
    }
    loadList(currentFilename);
    while (true) {
      System.out.print("\033[H\033[2J"); // cls
      System.out.println("###################\n#                 #\n#  MY TO-DO LIST  #\n#                 #\n###################\n"); // stylized title 
      System.out.println("List : " + currentFilename);
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
             case "/save":
              saveList(lastArg(1).replace("_", "-"));
              break;
            case "/load":
              loadList(lastArg(1).replace("_", "-"));
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
      saveList(currentFilename);
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

  static void saveList(String filename) {
    currentFilename = filename;
    saveData(currentFilename, convertStringArrayListToString(tasks));
    saveData(currentFilename + "_done", convertBooleanArrayListToString(done));
    saveData(currentFilename + "_important", convertBooleanArrayListToString(important));
  }

  static void loadList(String filename) {
    currentFilename = filename;
    tasks = convertStringToStringArrayList(loadData(currentFilename));
    done = convertStringToBooleanArrayList(loadData(currentFilename + "_done"));
    important = convertStringToBooleanArrayList(loadData(currentFilename + "_important"));
  }

  // get the last user command argument, which may contain spaces, from its index
  static String lastArg(int index) {
    int startIndex = 0;
    for (int i = 0; i < index; i++) {
      startIndex += splitInput[i].length() + 1;
    }
    return input.substring(startIndex);
  }
  
  static void saveData(String filename, String data) {
    try {
      File dataFile = new File(filename + ".txt");
      dataFile.createNewFile();
      FileWriter myWriter = new FileWriter(filename + ".txt");
      myWriter.write(data);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  static String loadData(String filename) {
      String content = "";
      try {
          Path path = Paths.get(filename + ".txt");
          content = Files.readString(path);
      } catch (IOException e) {
          e.printStackTrace();
      }
      return content;
  }

  static ArrayList<String> convertStringToStringArrayList(String input) {
    String[] lines = input.split("\n");
    ArrayList<String> list = new ArrayList<>();
    for (String line : lines) {
      list.add(line);
    }
    return list;
  }

  static ArrayList<Boolean> convertStringToBooleanArrayList(String input) {
    String[] lines = input.split("\n");
    ArrayList<Boolean> list = new ArrayList<>();
    for (String line : lines) {
      list.add(Boolean.parseBoolean(line));
    }
    return list;
  }

  static String convertStringArrayListToString(ArrayList<String> list) {
    StringBuilder sb = new StringBuilder();
    for (String item : list) {
      sb.append(item).append("\n");
    }
    // Remove the last line break if needed
    if (!list.isEmpty()) {
      sb.setLength(sb.length() - 1);
    }
    return sb.toString();
  }
  
  static String convertBooleanArrayListToString(ArrayList<Boolean> list) {
    StringBuilder sb = new StringBuilder();
    for (Boolean item : list) {
      sb.append(item).append("\n");
    }
    // Remove the last line break if needed
    if (!list.isEmpty()) {
      sb.setLength(sb.length() - 1);
    }
    return sb.toString();
  }
}