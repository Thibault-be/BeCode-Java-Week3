import domain.AllData;
import domain.TradeData;
import domain.Commands;
import logic.GenerateReports;

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
          .map(line -> line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")) //complex regex to allow for commas in quoted data
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
    GenerateReports reports = new GenerateReports(data);
    
    System.out.println("Welcome to the covid trade data analysis tool");
    System.out.println("Available commands are:");
    System.out.println("help - help <cmd> - monthly_total - monthly_average - yearly_total - yearly_average - overview");
    
    boolean flag = true;
    while (flag){
      System.out.println("Please type a command. Blank command leaves the program");
      System.out.print("> ");
      String cmd = scanner.nextLine();
      flag = createCommand(cmd, commandOptions, reports);

    }
  }
  
  private static boolean createCommand(String cmd, List<String> commandOptions, GenerateReports reports){
    
    if (cmd.isEmpty()) {
      System.out.println("exiting program.");
      return false;
    }
    
    //cmd = "yearly_average";       //REMOVE*********************
    
    if (! commandOptions.contains(cmd)){
      System.out.println("***This is not a valid command.***\n");
      return true;
    }
    
    //first check if any of the help commands or overview were entered. These don't need further user input.
    switch (cmd) {
      case "help":
        Commands helpCmd = Commands.HELP;
        System.out.println("These are short descriptions of the available commands:\n");
        System.out.println("monthly_total: sum of both the export and import for a specified month of a specified year.");
        System.out.println("monthly_average: average of both the export and import of a specified month of a specified year.");
        System.out.println("yearly_total: overview of all the monthly totals for a particular year. This command returns the total of each month for both import and export and then gives the yearly total for both import and export.");
        System.out.println("yearly_average: overview of all the monthly averages for a particular year, for both import and export. Then it gives the yearly average for both import and export.");
        System.out.println("overview: all the unique values that span the data set: years, countries, commodities, transportation modes, and measures.\n");
        
        System.out.println("for more information write 'help + command'.\n");
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
        Commands helpYearlyTotal = Commands.HELP_YEARLY_TOTAL;
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
      case "overview":
        Commands overview = Commands.OVERVIEW;
        reports.getOverview();
        return true;
    }
    
    //no help command or overview was entered, so we want to generate a report with user input.
    //create scanner so user can input requested data
    Scanner scanner = new Scanner(System.in);
    String year = getYear(scanner);
    //String year = "2019";                     //REMOVE**************************************
    
    //Yearly reports only need a year
    switch (cmd){
      case "yearly_total":
        Commands yearlyTotal = Commands.YEARLY_TOTAL;
        reports.getYearlyTotal(year);
        return true;
      case "yearly_average":
        Commands yearlyAverage = Commands.YEARLY_AVERAGE;
        reports.getYearlyAverage(year);
        return true;
    }
    
    //monthly reports need a year and a month
    String month = getMonth(scanner);
    //String month = "December";           ///REMOVE**********************
    
    switch (cmd){
      case "monthly_total":
        Commands monthlyTotal = Commands.MONTHLY_TOTAL;
        ArrayList<Long> importExportMonthly = reports.getMonthlyTotal(month, year);
        System.out.println("Exports for " + month + " of " + year + " amounted to " + importExportMonthly.get(0) + " USD.");
        System.out.println("Imports for " + month + " of " + year + " amounted to " + importExportMonthly.get(1) + " USD.\n");
        break;
      case "monthly_average":
        Commands monthlyAverage = Commands.MONTHLY_AVERAGE;
        reports.getMonthlyAverage(month, year);
        break;
    }
   return true;
  }
  
  private static String getMonth(Scanner scanner){
    System.out.println("Which month do you want to look at?");
    System.out.print("> ");
    return scanner.nextLine();
    
    
  }
  
  private static String getYear(Scanner scanner){
    System.out.println("Which year do you want to look at?");
    System.out.print("> ");
    return scanner.nextLine();
  }
}
