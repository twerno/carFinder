package net.twerno.carFinder.module.otoMotoProcessor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.client.RestTemplate;

public class OtoMotoOfferParserHelper
{
  public static String pobierzNumerTelefonu(String otoMotoPhoneHash)
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

  public static String pobierzAdresEmail(String sellerPageUrl)
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
