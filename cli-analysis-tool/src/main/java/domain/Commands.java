package domain;

public enum Commands {
  
  HELP ("help", ""),
  HELP_MONTHLY_TOTAL ("help monthly_total", "After you provide a month and a year the tool will generate a report summarizing the total of exports and imports of the month and year that you've specified.\n"),
  HELP_MONTHLY_AVERAGE ("help monthly_average", "After you provide a month and a year the tool will generate a report summarizing the average of exports and imports of the month and year that you've specified.\n"),
  HELP_YEARLY_TOTAL ("help yearly_total", "Please provide a year and the tool will generate a report for all months indicating totals for exports and imports for each month.\n"),
  HELP_YEARLY_AVERAGE ("help yearly_average", "Please provide a year and the tool will generate a report for all months indicating averages for exports and imports for each month.\n"),
  HELP_OVERVIEW ("help overview", "This command will generate a report with all unique values in the data set\n"),
  MONTHLY_TOTAL ("monthly_total", ""),
  MONTHLY_AVERAGE ("monthly_average", ""),
  YEARLY_TOTAL ("yearly_total", ""),
  YEARLY_AVERAGE ("yearly_average",""),
  OVERVIEW ("overview", "");

  public final String stringCmd;
  public final String explanation;
  
  Commands (String stringCmd, String explanation){
    this.stringCmd = stringCmd;
    this.explanation = explanation;
  }
  
}
