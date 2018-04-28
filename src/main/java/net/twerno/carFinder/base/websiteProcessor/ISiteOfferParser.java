package net.twerno.carFinder.base.websiteProcessor;

import java.io.IOException;

import org.jsoup.nodes.Document;

import net.twerno.carFinder.base.model.Car;

public interface ISiteOfferParser
{
  Car parseOffer(WebSiteOffer offer, Document offerDoc) throws IOException;
}
