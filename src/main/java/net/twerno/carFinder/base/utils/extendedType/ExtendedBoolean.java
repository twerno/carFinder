package net.twerno.carFinder.base.utils.extendedType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExtendedBoolean extends AbstractExtendedType<Boolean>
{

  public static ExtendedBoolean buildUnknown(String wartosc)
  {
    ExtendedBoolean result = new ExtendedBoolean();
    result.ustaw(wartosc);
    return result;
  }

  public static ExtendedBoolean build(Boolean wartosc)
  {
    ExtendedBoolean result = new ExtendedBoolean();
    result.ustaw(wartosc);
    return result;
  }

}
