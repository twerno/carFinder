package net.twerno.carFinder.base.helper.jsoup;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

import net.twerno.carFinder.base.helper.jsoup.chainAction.ChainActionData;
import net.twerno.carFinder.base.model.error.ParserError;
import net.twerno.carFinder.base.model.error.ParserErrors;

public abstract class JSoupValidatorHelper
{

  public static final ParserErrors NOT_NULL(Element e, ChainActionData d)
  {
    ParserErrors result = new ParserErrors();
    if (e == null)
    {
      ParserError blad = new ParserError(d.label, "Nie znaleziono elementu");
      result.add(blad);
    }
    return result;
  }

  public static final ParserErrors NOT_BLANK(String str, ChainActionData d)
  {
    ParserErrors result = new ParserErrors();
    if (StringUtils.isBlank(str))
    {
      ParserError blad = new ParserError(d.label, "String nie może być pusty.");
      result.add(blad);
    }
    return result;
  }

}
