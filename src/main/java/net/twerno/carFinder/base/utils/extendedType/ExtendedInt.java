package net.twerno.carFinder.base.utils.extendedType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExtendedInt extends AbstractExtendedType<Integer>
{

  public static ExtendedInt buildUnknown(String wartosc)
  {
    ExtendedInt result = new ExtendedInt();
    result.ustaw(wartosc);
    return result;
  }

  public static ExtendedInt build(Integer wartosc)
  {
    ExtendedInt result = new ExtendedInt();
    result.ustaw(wartosc);
    return result;
  }

}
