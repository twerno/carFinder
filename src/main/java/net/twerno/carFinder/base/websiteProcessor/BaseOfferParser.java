package net.twerno.carFinder.base.websiteProcessor;

import org.jsoup.nodes.Document;

import net.twerno.carFinder.base.helper.htmlMapper.FieldSetter;
import net.twerno.carFinder.base.helper.jsoup.JSoupChainActionBuilder;
import net.twerno.carFinder.base.model.error.ParserErrors;

public abstract class BaseOfferParser
{
  protected final WebSiteOffer offer;
  protected final Document offerDoc;
  protected final ParserErrors errors;

  public BaseOfferParser(WebSiteOffer offer, Document offerDoc, ParserErrors errors)
  {
    this.offer = offer;
    this.offerDoc = offerDoc;
    this.errors = errors;
  }

  protected JSoupChainActionBuilder label(String label)
  {
    return new JSoupChainActionBuilder(label, offer, offerDoc, errors);
  }

  protected <T> FieldSetter<T> field(String fieldName, Class<T> Fieldtype)
  {
    return new FieldSetter<T>(fieldName, this.errors);
  }
}
