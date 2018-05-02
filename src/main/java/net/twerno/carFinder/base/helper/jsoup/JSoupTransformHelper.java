package net.twerno.carFinder.base.helper.jsoup;

import org.apache.commons.validator.routines.IntegerValidator;
import org.jsoup.nodes.Element;

import net.twerno.carFinder.base.helper.jsoup.chainAction.ChainActionData;

public abstract class JSoupTransformHelper
{

  private static final IntegerValidator intValidator = IntegerValidator.getInstance();

  public static final String ELEMENT_AS_TEXT(Element e)
  {
    if (e == null)
      return null;

    return e.text().trim();
  }

  public static final String ELEMENT_AS_HTML(Element e)
  {
    if (e == null)
      return null;

    return e.html().trim();
  }

  public static final Integer str2Int(String source, ChainActionData actionData)
  {
    if (!intValidator.isValid(source))
    {
      actionData.errors.add(actionData.label,
          "Wartość nie jest liczbą całkowitą: \"" + source + "\"");
      return null;
    }
    return Integer.parseInt(source);
  }

}
