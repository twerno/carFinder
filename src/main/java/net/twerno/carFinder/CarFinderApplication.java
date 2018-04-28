package net.twerno.carFinder;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.log4j.Log4j2;
import net.twerno.carFinder.base.model.Car;
import net.twerno.carFinder.base.websiteProcessor.WebSiteOffer;
import net.twerno.carFinder.module.otoMotoProcessor.OtoMotoCriteriaCrawler;
import net.twerno.carFinder.module.otoMotoProcessor.OtoMotoOfferParser;

@Log4j2
@SpringBootApplication
public class CarFinderApplication
{

  public final String otoMotoCriteriaStr =
      "https://www.otomoto.pl/osobowe/od-2008/?search%5Bfilter_float_price%3Ato%5D=50000&search%5Bfilter_enum_fuel_type%5D%5B0%5D=petrol&search%5Bfilter_enum_fuel_type%5D%5B1%5D=petrol-lpg&search%5Bfilter_enum_fuel_type%5D%5B2%5D=petrol-cng&search%5Bfilter_enum_gearbox%5D%5B0%5D=manual&search%5Bfilter_enum_damaged%5D=0&search%5Bfilter_enum_country_origin%5D%5B0%5D=pl&search%5Bfilter_enum_registered%5D=1&search%5Bfilter_enum_original_owner%5D=1&search%5Bfilter_enum_no_accident%5D=1&search%5Bbrand_program_id%5D%5B0%5D=&search%5Bcountry%5D=";

  public static void main(String[] args)
  {
    SpringApplication.run(CarFinderApplication.class, args);
  }

  @Bean
  ApplicationRunner test()
  {
    final OtoMotoCriteriaCrawler otoMotoListProcessor = new OtoMotoCriteriaCrawler(otoMotoCriteriaStr);
    final OtoMotoOfferParser otoMotoOfferProcessor = new OtoMotoOfferParser();

    return (props) ->
    {
      try
      {
        List<WebSiteOffer> newOffers = otoMotoListProcessor.newOffer(
            // "https://www.otomoto.pl/oferta/volkswagen-up-move-up-gwarancja-salon-polska-cena-w-finansowniu-vw-bank-ID6zYD7k.html");
            "https://www.otomoto.pl/oferta/claas-variant-360-bardzo-ladny-egzemplarz-ID6zRqIX.html");
        // otoMotoListProcessor.newOffers(Collections.emptyMap());

        for (WebSiteOffer offer : newOffers)
        {
          Document offerDoc = Jsoup.connect(offer.getOffer_url()).get();
          Car car = otoMotoOfferProcessor.parseOffer(offer, offerDoc);

          String header = (offer.isPromoted() ? "[PROMOTED]" : "") + "#" +
              offer.getArticleId() + "; " + offer.getOffer_url() + "; "
              + offer.getTitle().length();

          log.info(header + '\n' + car.toString());
        }
      }
      catch (Exception e)
      {
        log.error(e);
      }
    };
  }
}
