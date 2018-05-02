package net.twerno.carFinder.base.helper.htmlMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
class ExtendedType<T>
{
  @Getter
  private final T value;

  @Getter
  private final String rawValue;

  public String toString()
  {
    return value != null
        ? value.toString()
        : "::NIEZNANA:" + rawValue;
  }

  // public static <T> ExtendedType<T> build(T value)
  // {
  // return new ExtendedType<T>(value, null);
  // }
  //
  // public static ExtendedType<?> buildRaw(String rawValue)
  // {
  // return new ExtendedType<>(null, rawValue);
  // }

}
