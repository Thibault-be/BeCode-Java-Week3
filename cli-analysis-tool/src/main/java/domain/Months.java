package domain;

public enum Months {
  
  JAN ("January"),
  FEB ("February"),
  MAR ("March"),
  APR ("April"),
  MAY ("May"),
  JUN ("June"),
  JUL ("July"),
  AUG ("August"),
  SEP ("September"),
  OCT ("October"),
  NOV ("November"),
  DEC ("December");
  
  public final String month;
  
  Months(String month){
    this.month = month;
  }
  
}
