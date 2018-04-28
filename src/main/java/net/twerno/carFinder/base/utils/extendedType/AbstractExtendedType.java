package net.twerno.carFinder.base.utils.extendedType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
abstract class AbstractExtendedType<E>
{
  @Getter
  private E enumValue;

  @Getter
  private String wartoscSpozaSlownika;

  public void ustaw(E wartosc)
  {
    this.enumValue = wartosc;
    this.wartoscSpozaSlownika = null;
  }

  public void ustaw(String wartosc)
  {
    this.enumValue = null;
    this.wartoscSpozaSlownika = wartosc;
  }

  public String toString()
  {
    return enumValue != null
        ? enumValue.toString()
        : "::NIEZNANA:" + wartoscSpozaSlownika;
  }

}
