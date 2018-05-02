package net.twerno.carFinder.base.helper.jsoup;

import java.util.Objects;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.Value;
import net.twerno.carFinder.base.helper.jsoup.chainAction.ChainAction;
import net.twerno.carFinder.base.helper.jsoup.chainAction.ChainActionData;
import net.twerno.carFinder.base.model.error.ParserErrors;
import net.twerno.carFinder.base.websiteProcessor.WebSiteOffer;

@Value
public class JSoupChainActionBuilder
{
  private String label;
  private WebSiteOffer offer;
  private Document offerDoc;
  private ParserErrors errors;

  public ChainAction<Elements> select(String cssQuery)
  {
    ChainActionData actionData = new ChainActionData(errors, offer, label);
    ChainAction<Element> result = new ChainAction<Element>(offerDoc, actionData);
    return result.transform(r -> r.select(cssQuery));
  }

  public ChainAction<Element> selectFirst(String cssQuery)
  {
    ChainActionData actionData = new ChainActionData(errors, offer, label);
    ChainAction<Element> result = new ChainAction<Element>(offerDoc, actionData);
    return result.transform(r -> r.selectFirst(cssQuery));
  }

  public ChainAction<String> selectFirstAsText(String cssQuery, REQUIRED required)
  {
    return selectFirst(cssQuery, required)
        .transform(JSoupTransformHelper::ELEMENT_AS_TEXT);
  }

  public ChainAction<String> selectFirstAsHTML(String cssQuery, REQUIRED required)
  {
    return selectFirst(cssQuery, required)
        .transform(JSoupTransformHelper::ELEMENT_AS_HTML);
  }

  public ChainAction<Element> selectFirst(String cssQuery, REQUIRED required)
  {
    ChainAction<Element> action = selectFirst(cssQuery);
    return required == REQUIRED.REQUIRE
        ? action.validate(JSoupValidatorHelper::NOT_NULL)
        : action.continueIf(Objects::nonNull);
  }

  public static enum REQUIRED
  {
    REQUIRE, OPTIONAL;
  }
}
