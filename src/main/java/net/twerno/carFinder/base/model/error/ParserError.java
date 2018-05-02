package net.twerno.carFinder.base.model.error;

import lombok.Value;

@Value
public class ParserError
{
  private String field;

  private String desc;

}
