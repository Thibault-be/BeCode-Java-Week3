import domain.AllData;
import domain.TradeData;
import domain.Commands;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CliAnalysis {
  
  public static void main(String[] args){
    
    AllData data = new AllData();
    Scanner scanner = new Scanner(System.in);
    
    List<String> commandOptions = new ArrayList<>();
    for (Commands command : Commands.values()){
      commandOptions.add(command.stringCmd);
    }
    
    try{
      Files.lines(Paths.get("covid-effects.csv")).skip(1)
          .map(line -> line.split(","))
          .forEach(splitLine ->{
            TradeData newData = new TradeData(
                splitLine[0],
                splitLine[1],
                splitLine[2],
                splitLine[3],
                splitLine[4],
                splitLine[5],
                splitLine[6],
                splitLine[7],
                splitLine[8],
                splitLine[9]
            );
            data.addData(newData);
          });
    }
    catch (Exception e){
      System.out.println("Error: " + e.getMessage());
    }
    
    System.out.println("Welcome to the covid trade data analysis tool");
    System.out.println("Available commands are:");
    System.out.println("help - help <cmd> - monthly_total - monthly_average - yearly_total - yearly_average - overview");
    
    boolean flag = true;
    while (flag){
      System.out.println("Please type a command. Blank command leaves the program");
      System.out.print("> ");
      String cmd = scanner.nextLine();
      flag = createCommand(cmd, commandOptions, data);

    }
  }
  
  public static boolean createCommand(String cmd, List<String> commandOptions, AllData data){
    
    if (cmd.isEmpty()) {
      System.out.println("exiting program.");
      return false;
    }
    
    if (! commandOptions.contains(cmd)){
      System.out.println("***This is not a valid command.***\n");
      return true;
    }
    
    switch (cmd){
      case "help":
        Commands helpCmd = Commands.HELP;
        System.out.println("These are short descriptions of the available commands:\n");
        System.out.println("monthly_total: sum of both the export and import for a specified month of a specified year.");
        System.out.println("monthly_average: average of both the export and import of a specified month of a specified year.");
        System.out.println("yearly_total: overview of all the monthly totals for a particular year. This command returns the total of each month for both import and export and then gives the yearly total for both import and export.");
        System.out.println("yearly_average: overview of all the monthly averages for a particular year, for both import and export. Then it gives the yearly average for both import and export.");
        System.out.println("overview: all the unique values that span the data set: years, countries, commodities, transportation modes, and measures.\n");
        return true;
      case "help monthly_total":
        Commands helpMonthlyTotal = Commands.HELP_MONTHLY_TOTAL;
        System.out.println(helpMonthlyTotal.explanation);
        return true;
      case "help monthly_average":
        Commands helpMonthlyAverage = Commands.HELP_MONTHLY_AVERAGE;
        System.out.println(helpMonthlyAverage.explanation);
        return true;
      case "help yearly_total":
        Commands helpYearlyTotal = Commands.YEARLY_TOTAL;
        System.out.println(helpYearlyTotal.explanation);
        return true;
      case "help yearly_average":
        Commands helpYearlyAverage = Commands.HELP_YEARLY_AVERAGE;
        System.out.println(helpYearlyAverage.explanation);
        return true;
      case "help overview":
        Commands helpOverview = Commands.HELP_OVERVIEW;
        System.out.println(helpOverview.explanation);
        return true;
      case "monthly_total":
      
      case "monthly_average":
      
      case "yearly_total":
      
      case "yearly_average":
      
      case "overview":
    }
    
    
   return true;
  }
  
}
