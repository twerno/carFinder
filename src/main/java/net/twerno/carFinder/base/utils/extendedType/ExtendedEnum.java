package net.twerno.carFinder.base.utils.extendedType;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExtendedEnum<E extends Enum<?>> extends AbstractExtendedType<E>
{

  public static <E extends Enum<?>> ExtendedEnum<E> build(E wartosc)
  {
    ExtendedEnum<E> result = new ExtendedEnum<>();
    result.ustaw(wartosc);
    return result;
  }

  public static <E extends Enum<?>> ExtendedEnum<E> buildUnknown(String wartosc, Class<E> type)
  {
    ExtendedEnum<E> result = new ExtendedEnum<>();
    result.ustaw(wartosc);
    return result;
  }
}
