package net.twerno.carFinder.module.otoMotoProcessor;

import static net.twerno.carFinder.base.helper.jsoup.JSoupChainActionBuilder.REQUIRED.OPTIONAL;
import static net.twerno.carFinder.base.helper.jsoup.JSoupChainActionBuilder.REQUIRED.REQUIRE;

import java.io.IOException;
import java.util.Objects;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.extern.log4j.Log4j2;
import net.twerno.carFinder.base.helper.jsoup.JSoupTransformHelper;
import net.twerno.carFinder.base.helper.jsoup.JSoupValidatorHelper;
import net.twerno.carFinder.base.model.Car;
import net.twerno.carFinder.base.model.Sprzedawca;
import net.twerno.carFinder.base.model.error.ParserErrors;
import net.twerno.carFinder.base.utils.extendedType.ExtendedTypeHelper;
import net.twerno.carFinder.base.websiteProcessor.BaseOfferParser;
import net.twerno.carFinder.base.websiteProcessor.ISiteOfferParser;
import net.twerno.carFinder.base.websiteProcessor.WebSiteOffer;

@Log4j2
public class OtoMotoOfferParser extends BaseOfferParser implements ISiteOfferParser
{

  private final OtoMotoOfferCarMapper otoMotoOfferCarMapper = new OtoMotoOfferCarMapper();

  public OtoMotoOfferParser(WebSiteOffer offer, Document offerDoc, ParserErrors errors)
  {
    super(offer, offerDoc, errors);
  }

  @Override
  public Car parseOffer() throws IOException
  {
    Car car = new Car(offer);

    offerParamListParse(car);
    offerWyposazenieParse(car);
    cena(car, offerDoc);
    naglowek(car);
    sprzedawca(car.getSprzedawca());

    return car;
  }

  private void sprzedawca(Sprzedawca seller)
  {
    field("seller_name", String.class)
        .write(mapper)
    // .write(seller::setNazwa)

    // .

    // label("seller_name")
    // .selectFirstAsText(".seller-box__seller-name", REQUIRE)
    // .then(seller::setNazwa);

    label("seller_type")
        .selectFirstAsText(".seller-box__seller-type", REQUIRE)
        .transform(OtoMotoConvHelper::sellerTypeConv)
        .then(seller::setTyp);

    label("seller_registration")
        .selectFirstAsText(".seller-box__seller-registration", REQUIRE)
        .then(seller::setOd_kiedy);

    label("seller_adress")
        .selectFirstAsText(".seller-box__seller-address__label", OPTIONAL)
        .then(seller::setAdres);

    label("seller_www")
        .selectFirstAsText(".website-box__link", OPTIONAL)
        .then(seller::setWww);

    label("seller_phone")
        .select(".om-button.blue.spoiler.seller-phones__button")
        .then(
            list ->
            {
              list.stream()
                  .filter(Objects::nonNull)
                  .map(e -> e.attr("data-id").trim() + "/" + e.attr("data-index").trim())
                  .filter(s -> !s.equals("/"))
                  .distinct()
                  .map(OtoMotoOfferParserHelper::pobierzNumerTelefonu)
                  .forEach(seller.getTelefon()::add);
            });

    label("seller_email")
        .selectFirst(".seller-box__seller-name a")
        .continueIf(Objects::nonNull)
        .then(
            sellerPageLink ->
            {
              String sellerPageUrl = sellerPageLink.attr("href");
              String email = OtoMotoOfferParserHelper.pobierzAdresEmail(sellerPageUrl);
              if (email != null)
                seller.getEmail().add(email);
            });
  }

  private void naglowek(Car car, Document offerDoc)
  {
    label("offer_data_dodania")
        .selectFirstAsText(".icon-calendar + span", REQUIRE)
        .transform(
            v ->
            {
              return ExtendedTypeHelper.pattern2DateTime(
                  OtoModoHelper.miesiacDopelniacz2Kod(v),
                  "kk:mm, dd MM yyyy");
            })
        .then(v -> car.setData_wystawienia(v));

    label("offer_desc")
        .selectFirstAsHTML("#description", REQUIRE)
        .then(car::setOpisHtml);

    label("offer_do_negocjacji")
        .selectFirstAsText(".offer-price__details", REQUIRE)
        .transform(v -> v.contains("Do negocjacji") ? "TAK" : "NIE")
        .transform(ExtendedTypeHelper::takNieValConv)
        .then(car::setDoNegocjacji);
  }

  private void cena(Car car, Document offerDoc)
  {
    label("offer_cena")
        .selectFirst(".offer-price__number", REQUIRE)
        .transform(
            e ->
            {
              return e
                  .attr("data-price")
                  .replaceAll("\\s+", "")
                  .trim();
            })
        .validate(JSoupValidatorHelper::NOT_BLANK)
        .transformAndValidate(JSoupTransformHelper::str2Int)
        .then(
            kwota ->
            {
              boolean isBrutto = label("isBrutto")
                  .selectFirstAsText(".offer-price__details", REQUIRE)
                  .transform(v -> v.contains("Cena Brutto"))
                  .get();

              car.setKwotaZOgloszenia(kwota);

            });
    // .transformEx(
    // (kwotaStr, actionData) ->
    // {
    // Elements rodzajKwoty = offerDoc.select(".offer-price__details");
    // boolean isBrutto = rodzajKwoty.text().contains("Cena Brutto");
    // return OtoModoHelper.kwotaOferty(kwotaStr, isBrutto, actionData);
    // })
    // .then(car::setKwota);
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

}
