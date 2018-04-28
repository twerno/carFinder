package net.twerno.carFinder.module.otoMotoProcessor;

public abstract class OtoModoHelper
{

  /**
   * 22:42, 27 kwietnia 2018 --> 22:42, 27 03 2018
   * 
   * @param dateStr
   */
  public static String miesiacDopelniacz2Kod(String dateStr)
  {
    if (dateStr == null)
      return null;

    return dateStr
        .replace("stycznia", "01")
        .replace("lutego", "02")
        .replace("marca", "03")
        .replace("kwietnia", "04")
        .replace("maja", "05")
        .replace("czerwca", "06")
        .replace("lipca", "07")
        .replace("sierpnia", "08")
        .replace("września", "09")
        .replace("października", "10")
        .replace("listopada", "11")
        .replace("grudnia", "12");
  }

}
