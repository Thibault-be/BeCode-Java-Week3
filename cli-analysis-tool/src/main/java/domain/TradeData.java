package domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TradeData {
  
  private String direction;
  private String year;
  private String date;
  private String weekday;
  private String country;
  private String commodity;
  private String transportMode;
  private String measure;
  private String value;
  private String cumulative;
  
  public TradeData (
      String direction,
      String year,
      String date,
      String weekday,
      String country,
      String commodity,
      String transportMode,
      String measure,
      String value,
      String cumulative
  ){
    this.direction = direction;
    this.year = year;
    this.date = date;
    this.weekday = weekday;
    this.country = country;
    this.commodity = commodity;
    this.transportMode = transportMode;
    this.measure = measure;
    this.value = value;
    this.cumulative = cumulative;
  }
  
  public String getMonth(){
    String[] ddmmyy = this.date.split("/");
    String month = ddmmyy[1];
    
    if (month.equals("01")){
      return "January";
    }
    if (month.equals("02")){
      return "February";
    }
    if (month.equals("03")){
      return "March";
    }
    if (month.equals("04")){
      return "April";
    }
    if (month.equals("05")){
      return "May";
    }
    if (month.equals("06")){
      return "June";
    }
    if (month.equals("07")){
      return "July";
    }
    if (month.equals("08")){
      return "August";
    }
    if (month.equals("09")){
      return "September";
    }
    if (month.equals("10")){
      return "October";
    }
    if (month.equals("11")){
      return "November";
    }
    if (month.equals("12")){
      return "December";
    }
    return "";
  }
  
  public String getYear(){
    return this.year;
  }
  
  public String getDirection(){
    return this.direction;
  }
  
  public long getValue(){
    return Long.valueOf(this.value);
  }
  
  public String getValueAsString(){
    return this.value;
  }
  
  public String getMeasure(){
    return this.measure;
  }
  
  public String getDate(){
    return this.date;
  }
  
  public String getCountry(){
    return this.country;
  }
  
  public String getWeekday(){
    return this.weekday;
  }
  
  public String getCommodity(){
    return this.commodity;
  }

  public String getTransportMode(){
    return this.transportMode;
  }
  
  public String getCumulative(){
    return this.cumulative;
  }
  
}
