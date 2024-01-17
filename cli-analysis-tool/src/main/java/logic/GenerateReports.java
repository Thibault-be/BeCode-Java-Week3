package logic;

import domain.AllData;
import domain.Months;
import domain.TradeData;

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class GenerateReports {
  
  private AllData allData;
  
  
  public GenerateReports(AllData allData){
    this.allData = allData;
  }
  
  //Returns the sum of both the export and import for a specified month of a specified year.
  public ArrayList<Long> getMonthlyTotal(String month, String year){
    
    ArrayList<Long> exportImportTotal = new ArrayList<>();
    
    long exportSum = 0;
    long importSum = 0;
    for (TradeData td : allData.getAllData()){
      if (td.getMonth().equals(month) && td.getYear().equals(year) && td.getMeasure().equals("$")){
          if (td.getDirection().equals("Exports")) exportSum += td.getValue();
          if (td.getDirection().equals("Imports")) importSum += td.getValue();
      }
    }
    exportImportTotal.add(exportSum);
    exportImportTotal.add(importSum);
    System.out.println("Exports for " + month + " of " + year + " amounted to " + exportSum + " USD.");
    System.out.println("Imports for " + month + " of " + year + " amounted to " + importSum + " USD.");
    return exportImportTotal;
  }
  
  //Returns the average of both the export and import of a specified month of a specified year.
  public ArrayList<Long> getMonthlyAverage(String month, String year){
    
    ArrayList<Long> exportImportTotal = getMonthlyTotal(month, year);
    
    ArrayList<Long> exportImportAverageAndCount = new ArrayList<>();
    
    long exportCount = 0;
    long importCount = 0;
    
    for (TradeData td : allData.getAllData()){
      if (td.getMonth().equals(month) && td.getYear().equals(year) && td.getMeasure().equals("$")){
        if (td.getDirection().equals("Exports")) exportCount++;
        if (td.getDirection().equals("Imports")) importCount++;
      }
    }
    
    long exportAverage = exportImportTotal.get(0)/ exportCount;
    long importAverage = exportImportTotal.get(1) / importCount;
    
    exportImportAverageAndCount.add(exportImportTotal.get(0));
    exportImportAverageAndCount.add(exportAverage);
    exportImportAverageAndCount.add(exportCount);
    exportImportAverageAndCount.add(exportImportTotal.get(1));
    exportImportAverageAndCount.add(importAverage);
    exportImportAverageAndCount.add(importCount);
    
    System.out.println("Average of exports for " + month + " of " + year + " amounted to " + exportAverage + " USD.");
    System.out.println("Average of imports for " + month + " of " + year + " amounted to " + importAverage + " USD.");
    
    return exportImportAverageAndCount;
  }
  
  
  //Provides an overview of all the monthly totals for a particular year.
  // This command returns the total of each month for both import and export and then gives
  // the yearly total for both import and export.
  public void getYearlyTotal(String year){
  
    long importTotal = 0;
    long exportTotal = 0;
    for (Months month : Months.values()){
      ArrayList<Long> monthTotal = getMonthlyTotal(month.month, year);
      importTotal += monthTotal.get(1);
      exportTotal += monthTotal.get(0);
    }
    System.out.println("The total export value for " + year + " amounted to " + exportTotal + " USD.");
    System.out.println("The total import value for " + year + " amounted to " + importTotal + " USD.");
  
  }
  
  //Provides an overview of all the monthly averages for a particular year, for both import and export.
  // Then it gives the yearly average for both import and export.
  public void getYearlyAverage( String year){
    
    long yearTotalExportValue = 0;
    long yearTotalImportValue = 0;
    long exportCount = 0;
    long importCount = 0;
    
    for (Months month : Months.values()){
      
      ArrayList<Long> monthData = getMonthlyAverage(month.month, year);
      
      yearTotalExportValue += monthData.get(0);
      yearTotalImportValue += monthData.get(3);
      
      exportCount += monthData.get(2);
      importCount += monthData.get(5);
      
    }
    
    System.out.println("The yearly average for " + year + " of exports is " + yearTotalExportValue / exportCount);
    System.out.println("The yearly average for " + year + " of imports is " + yearTotalImportValue / importCount);
  }
  
  public void getOverview(){
    System.out.println("in get overview");
    
  }
  
  
}
