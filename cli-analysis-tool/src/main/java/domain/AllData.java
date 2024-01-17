package domain;

import java.util.List;
import java.util.ArrayList;

public class AllData {
  
  private List<TradeData> data;
  
  public AllData(){
    this.data = new ArrayList<>();
  }
  
  public List<TradeData> getAllData(){
    return this.data;
  }
  
  public void addData(TradeData tradeData){
    this.data.add(tradeData);
  }
  
}
