package logic;

import domain.AllData;
import domain.TradeData;

public class GenerateReports {
  
  private AllData allData;
  
  
  public GenerateReports(AllData allData){
    this.allData = allData;
  }
  
  public void getMonthlyTotal(String month, String year){
    System.out.println("in monthly total");
  }
  
  public void getMonthlyAverage(String month, String year){
    System.out.println("in monthly average");
  
  }
  
  public void getYearlyTotal(String year){
    System.out.println("in yearly total");
  
  }
  
  public void getYearlyAverage( String year){
    System.out.println("in yearly average");
  
  }
  
  public void getOverview(){
    System.out.println("in get overview");
    
  }
  
  
}
