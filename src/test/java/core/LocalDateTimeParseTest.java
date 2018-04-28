package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;

import net.twerno.carFinder.module.otoMotoProcessor.OtoModoHelper;

public class LocalDateTimeParseTest
{
  @Test
  public void test1()
  {
    String pattern = "kk:mm, dd MM yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    String dateStr = OtoModoHelper.miesiacDopelniacz2Kod("22:42, 27 kwietnia 2014");

    LocalDateTime date = LocalDateTime.parse(dateStr, formatter);

    System.out.println(date);
    Assert.assertTrue(date.getMonthValue() == 04);
  }
}
