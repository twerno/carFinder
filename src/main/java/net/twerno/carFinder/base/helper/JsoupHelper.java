package net.twerno.carFinder.base.helper;

import org.jsoup.nodes.Element;

public abstract class JsoupHelper
{

  public static String selectFirstText(Element root, String css)
  {
    Element element = root.selectFirst(css);
    if (element == null)
      return null;
    else
      return element.text().trim();
  }

}
