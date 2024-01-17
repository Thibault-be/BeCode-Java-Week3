
import domain.AllData;
import domain.TradeData;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CliAnalysis {
  
  public static void main(String[] args){
    
    AllData data = new AllData();
    
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
    
    System.out.println(data.getAllData());
    
  }
  
}
