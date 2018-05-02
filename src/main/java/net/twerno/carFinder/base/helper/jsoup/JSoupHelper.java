package net.twerno.carFinder.base.helper.jsoup;

import org.jsoup.nodes.Element;

public abstract class JSoupHelper
{

  public static String selectFirstText(Element root, String css)
  {
    Element element = root.selectFirst(css);
    if (element == null)
      return null;
    else
      return element.text().trim();
  }

  // public static ChainAction<Elements> select(Element root, String cssQuery,
  // String label, WebSiteOffer offer,
  // ParserErrors errors)
  // {
  // ChainActionData actionData = new ChainActionData(errors, offer, label);
  // ChainAction<Element> result = new ChainAction<Element>(root, actionData);
  // return result.transform(r -> r.select(cssQuery));
  // }
  //
  // public static ChainAction<Element> selectFirst(Element root, String
  // cssQuery, String label, WebSiteOffer offer,
  // ParserErrors errors)
  // {
  // ChainActionData actionData = new ChainActionData(errors, offer, label);
  // ChainAction<Element> result = new ChainAction<Element>(root, actionData);
  // return result.transform(r -> r.selectFirst(cssQuery));
  // }

}
