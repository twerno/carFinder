package net.twerno.carFinder.base.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import net.twerno.carFinder.base.slownik.SprzedajacyEnum;
import net.twerno.carFinder.base.utils.extendedType.ExtendedEnum;

@Data
public class Sprzedawca
{
  public ExtendedEnum<SprzedajacyEnum> typ;

  public String od_kiedy;

  public String adres;

  public String www;

  public List<String> telefon = new ArrayList<>();

  public List<String> email = new ArrayList<>();

}
