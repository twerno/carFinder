package net.twerno.carFinder.module.otoMotoProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.LongValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import lombok.extern.log4j.Log4j2;
import net.twerno.carFinder.base.model.error.ParserError;
import net.twerno.carFinder.base.websiteProcessor.IWebsiteCrawler;
import net.twerno.carFinder.base.websiteProcessor.WebSiteOffer;

@Log4j2
public class OtoMotoCriteriaCrawler implements IWebsiteCrawler
{
  private final LongValidator longValidator = LongValidator.getInstance();
  final String criteriaString;

  public OtoMotoCriteriaCrawler(String criteriaString)
  {
    this.criteriaString = criteriaString;
  }

  @Override
  public String websiteId()
  {
    return "www.otoMoto.pl";
  }

  @Override
  public List<WebSiteOffer> newOffers(Map<String, WebSiteOffer> knownOffers, List<ParserError> errors)
      throws IOException
  {
    List<WebSiteOffer> result = new ArrayList<>();

    Document doc = downloadDoc(1);
    List<WebSiteOffer> offers = parseArticleList(doc, errors);
    result.add(offers.stream()
        .filter(o -> !knownOffers.containsKey(o.getArticleId()))
        .findFirst()
        .get());

    // .forEach(result::add);

    // for (int i = 2; i < totalPages(doc); i++)
    // {
    // doc = downloadDoc(i);
    // offers = parseArticleList(doc);
    // offers.stream()
    // .filter(o -> !knownOffers.containsKey(o.getArticleId()))
    // .forEach(result::add);
    // }

    return result;
  }

  public List<WebSiteOffer> newOffer(String url)
  {
    List<WebSiteOffer> result = new ArrayList<>();
    result.add(new WebSiteOffer("", "dummy", "EMPTY", url, false));
    return result;
  }

  @Override
  public String pageUrl(long pageIdx)
  {
    if (pageIdx >= 1)
      return criteriaString;
    else
      return criteriaString + "&page=" + pageIdx;
  }

  private Document downloadDoc(long pageIdx) throws IOException
  {
    String pageUrl = pageUrl(pageIdx);
    log.debug(() -> "Downloading & parsing " + websiteId() + " page: " + pageIdx + "; URL==" + pageUrl);
    return Jsoup.connect(pageUrl).get();
  }

  private Long totalPages(Document doc)
  {
    if (doc == null)
      return 1L;

    return doc.select("ul.om-pager li span.page").stream()
        .map(span -> span.text())
        .filter(longValidator::isValid)
        .map(Long::parseLong)
        .max(Comparator.comparing(Long::valueOf))
        .get();
  }

  private List<WebSiteOffer> parseArticleList(Document doc, List<ParserError> errors)
  {
    return doc.select("article").stream()
        .map(this::parseOffer)
        .collect(Collectors.toList());
  }

  private WebSiteOffer parseOffer(Element article)
  {
    Element link = article.selectFirst("a");
    String articleId = link.attr("data-ad-id");
    String offerUrl = link.attr("href").replaceAll("#.*", "");
    String title = link.attr("title");
    boolean promoted = link.select("span").attr("class").equals("promoted-label");

    return new WebSiteOffer(articleId, websiteId(), title, offerUrl, promoted);
  }

}
