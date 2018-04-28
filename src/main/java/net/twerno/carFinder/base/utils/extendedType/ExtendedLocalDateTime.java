package net.twerno.carFinder.base.utils.extendedType;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExtendedLocalDateTime extends AbstractExtendedType<LocalDateTime>
{

  public static ExtendedLocalDateTime buildUnknown(String wartosc)
  {
    ExtendedLocalDateTime result = new ExtendedLocalDateTime();
    result.ustaw(wartosc);
    return result;
  }

  public static ExtendedLocalDateTime build(LocalDateTime wartosc)
  {
    ExtendedLocalDateTime result = new ExtendedLocalDateTime();
    result.ustaw(wartosc);
    return result;
  }

}
