package net.twerno.carFinder.module.otoMotoProcessor;

import org.apache.commons.validator.routines.IntegerValidator;

import net.twerno.carFinder.base.helper.jsoup.chainAction.ChainActionData;
import net.twerno.carFinder.base.utils.extendedType.ExtendedInt;
import net.twerno.carFinder.base.websiteProcessor.WebSiteOffer;

public abstract class OtoModoHelper
{

  private static final IntegerValidator intValidator = IntegerValidator.getInstance();

  /**
   * 22:42, 27 kwietnia 2018 --> 22:42, 27 04 2018
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

  public static ExtendedInt parseKwota(String kwotaStr, boolean isBrutto, ChainActionData actionData)
  {
    if (!intValidator.isValid(kwotaStr))
    {
      ExtendedInt result = ExtendedInt.buildUnknown((isBrutto ? "Brutto=" : "Netto=") + kwotaStr);
      actionData.errors.add(actionData.offer, actionData.label, result.toString());
      return result;
    }
    else
    {
      int kwota = Integer.parseInt(kwotaStr);
      return ExtendedInt.build(kwota);
    }
  }

  public static ExtendedInt kwotaOferty(String kwotaStr, boolean isBrutto, String label, WebSiteOffer offer)
  {
    if (!intValidator.isValid(kwotaStr))
    {
      ExtendedInt result = ExtendedInt.buildUnknown((isBrutto ? "Brutto=" : "Netto=") + kwotaStr);
      actionData.errors.add(offer, actionData.label, result.toString());
      return result;
    }
    else
    {
      int kwota = Integer.parseInt(kwotaStr);

      if (isBrutto)
        return ExtendedInt.build(kwota);
      else
      {
        int brutto = Double.valueOf(kwota * 1.23).intValue();
        return ExtendedInt.build(brutto);
      }
    }
  }

}
