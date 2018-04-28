package net.twerno.carFinder.base.websiteProcessor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteOffer
{
  private String articleId;

  private String siteId; 

  private String title;

  private String offer_url;

  private boolean promoted;
}
