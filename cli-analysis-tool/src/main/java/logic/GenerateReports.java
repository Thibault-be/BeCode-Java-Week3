package logic;

import domain.AllData;
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
  public void getMonthlyAverage(String month, String year){
    
    ArrayList<Long> exportImportTotal = getMonthlyTotal(month, year);
    int exportCount = 0;
    int importCount = 0;
    
    for (TradeData td : allData.getAllData()){
      if (td.getMonth().equals(month) && td.getYear().equals(year) && td.getMeasure().equals("$")){
        if (td.getDirection().equals("Exports")) exportCount++;
        if (td.getDirection().equals("Imports")) importCount++;
      }
    }
    
    long exportAverage = exportImportTotal.get(0)/ exportCount;
    long importAverage = exportImportTotal.get(1) / importCount;
    
    System.out.println("Average of exports for " + month + " of " + year + " amounted to " + exportAverage + " USD.");
    System.out.println("Average of imports for " + month + " of " + year + " amounted to " + importAverage + " USD.");
  }
  
  
  //Provides an overview of all the monthly totals for a particular year.
  // This command returns the total of each month for both import and export and then gives
  // the yearly total for both import and export.
  public void getYearlyTotal(String year){
  
  
  
  }
  
  public void getYearlyAverage( String year){
    System.out.println("in yearly average");
  
  }
  
  public void getOverview(){
    System.out.println("in get overview");
    
  }
  
  
}
