package net.twerno.carFinder.base.websiteProcessor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.twerno.carFinder.base.model.error.ParserError;

public interface IWebsiteCrawler
{
  String websiteId();

  String pageUrl(long pageIdx);

  List<WebSiteOffer> newOffers(Map<String, WebSiteOffer> knownOffers, List<ParserError> errors)
      throws IOException;
}
