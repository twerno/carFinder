package net.twerno.carFinder.base.model.error;

import java.util.ArrayList;

public class ParserErrors extends ArrayList<ParserError>
{

  /**
   * 
   */
  private static final long serialVersionUID = 232631627021793963L;

  public void add(String field, String desc)
  {
    add(new ParserError(field, desc));
  }
}
