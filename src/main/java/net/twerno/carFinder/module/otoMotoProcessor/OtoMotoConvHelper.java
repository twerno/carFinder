package net.twerno.carFinder.module.otoMotoProcessor;

import net.twerno.carFinder.base.slownik.SprzedajacyEnum;
import net.twerno.carFinder.base.slownik.StanEnum;
import net.twerno.carFinder.base.utils.extendedType.ExtendedEnum;

public abstract class OtoMotoConvHelper
{
  public static ExtendedEnum<StanEnum> stanValueConverter(String value)
  {
    if (value == null)
      return null;

    switch (value)
    {
      case "Używane":
        return ExtendedEnum.build(StanEnum.UZYWANY);
      case "Nowe":
        return ExtendedEnum.build(StanEnum.NOWY);
      default:
        return ExtendedEnum.buildUnknown(value, StanEnum.class);
    }
  }

  public static ExtendedEnum<SprzedajacyEnum> sellerTypeConv(String value)
  {
    if (value == null)
      return null;

    switch (value)
    {
      case "Osoba prywatna":
        return ExtendedEnum.build(SprzedajacyEnum.OSOBA_PRYWATNA);
      case "Dealer":
        return ExtendedEnum.build(SprzedajacyEnum.DEALER);
      default:
        return ExtendedEnum.buildUnknown(value, SprzedajacyEnum.class);
    }
  }
}
