package net.twerno.carFinder.module.otoMotoProcessor;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.validator.routines.IntegerValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;
import net.twerno.carFinder.base.helper.JsoupHelper;
import net.twerno.carFinder.base.model.Car;
import net.twerno.carFinder.base.websiteProcessor.ISiteOfferParser;
import net.twerno.carFinder.base.websiteProcessor.WebSiteOffer;

@Log4j2
public class OtoMotoOfferParser implements ISiteOfferParser
{
  private final IntegerValidator intValidator = IntegerValidator.getInstance();
  private final OtoMotoOfferCarMapper otoMotoOfferCarMapper = new OtoMotoOfferCarMapper();

  @Override
  public Car parseOffer(WebSiteOffer offer, Document offerDoc) throws IOException
  {
    Car car = new Car(offer);

    offerParamListParse(car, offerDoc);
    offerWyposazenieParse(car, offerDoc);
    cena(car, offerDoc);
    naglowek(car, offerDoc);
    sprzedawca(car, offerDoc);

    return car;
  }

  private void sprzedawca(Car car, Document offerDoc)
  {
    String sellerType = JsoupHelper.selectFirstText(offerDoc, ".seller-box__seller-type");
    otoMotoOfferCarMapper.parse(car, "Seller_type", sellerType);

    String sellerRegistration = JsoupHelper.selectFirstText(offerDoc, ".seller-box__seller-registration");
    otoMotoOfferCarMapper.parse(car, "Seller_registration", sellerRegistration);

    String sellerAdress = JsoupHelper.selectFirstText(offerDoc, ".seller-box__seller-address__label");
    otoMotoOfferCarMapper.parse(car, "Seller_adress", sellerAdress);

    String wwwAdress = JsoupHelper.selectFirstText(offerDoc, ".website-box__link");
    car.getSprzedawca().setWww(wwwAdress);

    Elements numeryTelefonow = offerDoc.select(".om-button.blue.spoiler.seller-phones__button");
    numeryTelefonow.stream()
        .filter(Objects::nonNull)
        .map(e -> e.attr("data-id").trim() + "/" + e.attr("data-index").trim())
        .filter(s -> s.length() > 1)
        .distinct()
        .map(this::pobierzNumerTelefonu)
        .forEach(car.getSprzedawca().getTelefon()::add);

    Element sellerPage = offerDoc.selectFirst(".seller-box__seller-name a");
    if (sellerPage != null)
    {
      String sellerPageUrl = sellerPage.attr("href");
      String email = pobierzAdresEmail(sellerPageUrl);
      if (email != null)
      {
        car.getSprzedawca().getEmail().add(email);
      }
    }
  }

  private void naglowek(Car car, Document offerDoc)
  {
    String dataDodaniaStr = JsoupHelper.selectFirstText(offerDoc, ".icon-calendar + span");
    otoMotoOfferCarMapper.parse(car, "Data dodania", dataDodaniaStr);

    String opis = offerDoc.selectFirst("#description").html().trim();
    otoMotoOfferCarMapper.parse(car, "Opis", opis);

    boolean isDoNegocjacji = offerDoc.selectFirst(".offer-price__details").text().trim()
        .contains("Do negocjacji");
    otoMotoOfferCarMapper.parse(car, "Do negocjacji", isDoNegocjacji ? "TAK" : "NIE");
  }

  private void cena(Car car, Document offerDoc)
  {
    String kwotaStr = offerDoc.select(".offer-price__number")
        .attr("data-price")
        .replaceAll("\\s+", "")
        .trim();

    Elements rodzajKwoty = offerDoc.select(".offer-price__details");
    boolean isBrutto = rodzajKwoty.text().contains("Cena Brutto");

    if (!intValidator.isValid(kwotaStr))
      otoMotoOfferCarMapper.parse(car, "Kwota", (isBrutto ? "Brutto=" : "Netto=") + kwotaStr);
    else if (isBrutto)
      otoMotoOfferCarMapper.parse(car, "Kwota", kwotaStr);
    else
    {
      Double kwota = Integer.parseInt(kwotaStr) * 1.23;
      otoMotoOfferCarMapper.parse(car, "Kwota", Integer.toString(kwota.intValue()));
    }
  }

  private void offerParamListParse(Car car, Document offerDoc)
  {
    Elements offerParamList = offerDoc.select("li.offer-params__item");

    for (Element offerParam : offerParamList)
    {
      String label = offerParam.selectFirst("span.offer-params__label").text().trim();
      String value = offerParam.selectFirst(".offer-params__link,.offer-params__value").text().trim();
      log.debug(() -> label + ": " + value);
      otoMotoOfferCarMapper.parse(car, label, value);
    }
  }

  private void offerWyposazenieParse(Car car, Document offerDoc)
  {
    Elements wyposazenieList = offerDoc.select("li.offer-features__item");

    for (Element wyposazenie : wyposazenieList)
    {
      String label = wyposazenie.text().trim();
      String value = "TAK";
      log.debug(() -> label + ": " + value);
      otoMotoOfferCarMapper.parse(car, label, value);
    }
  }

  private String pobierzNumerTelefonu(String otoMotoPhoneHash)
  {
    String url = "https://www.otomoto.pl/ajax/misc/contact/multi_phone/" + otoMotoPhoneHash + "/";
    RestTemplate restTemplate = new RestTemplate();
    OtoMotoPhoneResponse response = restTemplate.getForObject(url, OtoMotoPhoneResponse.class);
    return response
        .getValue()
        .replaceAll("\\s+", "")
        .replaceAll("0048", "")
        .replaceAll("\\\\+48", "");
  }

  private String pobierzAdresEmail(String sellerPageUrl)
  {
    try
    {
      Document doc = Jsoup.connect(sellerPageUrl).get();
      Element mailTo = doc.selectFirst("#mailto");
      if (mailTo != null)
      {
        return mailTo.attr("href").replaceAll("^mailto:", "");
      }
      return null;
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

}
