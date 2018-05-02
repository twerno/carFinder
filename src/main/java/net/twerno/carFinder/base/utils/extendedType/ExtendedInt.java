package net.twerno.carFinder.base.utils.extendedType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExtendedInt // extends ExtendedType<Integer>
{

  public static ExtendedInt build(Integer wartosc)
  {
    ExtendedInt result = new ExtendedInt();
    // result.ustaw(wartosc);
    return result;
  }

  public static ExtendedInt buildUnknown(String wartosc)
  {
    ExtendedInt result = new ExtendedInt();
    // result.ustawRaw(wartosc);
    return result;
  }

}
