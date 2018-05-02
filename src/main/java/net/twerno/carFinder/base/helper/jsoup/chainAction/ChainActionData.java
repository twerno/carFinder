package net.twerno.carFinder.base.helper.jsoup.chainAction;

import net.twerno.carFinder.base.model.error.ParserErrors;
import net.twerno.carFinder.base.websiteProcessor.WebSiteOffer;

public class ChainActionData
{
  public final WebSiteOffer offer;
  public final String label;

  public boolean canContinue = true;
  public ParserErrors errors;

  public ChainActionData(ParserErrors errors, WebSiteOffer offer, String label)
  {
    this.errors = errors;
    this.offer = offer;
    this.label = label;
  }

}
