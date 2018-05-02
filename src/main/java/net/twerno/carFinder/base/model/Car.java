package net.twerno.carFinder.base.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.twerno.carFinder.base.utils.extendedType.ExtendedBoolean;
import net.twerno.carFinder.base.utils.extendedType.ExtendedLocalDateTime;
import net.twerno.carFinder.base.websiteProcessor.WebSiteOffer;

@Data
@NoArgsConstructor
public class Car
{

  private String id;

  private String siteId;

  private Sprzedawca sprzedawca = new Sprzedawca();

  private CarParam params = new CarParam();

  private String tytul;

  private String url;

  private boolean isPromoted;

  private ExtendedLocalDateTime data_wystawienia;

  private int kwotaZOgloszenia;

  private int kwotaBrutto;

  private ExtendedBoolean doNegocjacji;

  private Wyposazenie wyposazenie = new Wyposazenie();

  private String opisHtml;

  public Car(WebSiteOffer offer)
  {
    id = offer.getArticleId();
    siteId = offer.getSiteId();
    tytul = offer.getTitle();
    url = offer.getOffer_url();
    isPromoted = offer.isPromoted();

  }
}
