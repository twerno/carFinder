package net.twerno.carFinder.base.utils.extendedType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.validator.routines.IntegerValidator;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class ExtendedTypeHelper
{

  private static final IntegerValidator intValidator = IntegerValidator.getInstance();

  public static ExtendedBoolean takNieValConv(String value)
  {
    if (value == null)
      return null;

    if (value.equalsIgnoreCase("tak"))
    {
      return ExtendedBoolean.build(true);
    }
    else if (value.equalsIgnoreCase("nie"))
    {
      return ExtendedBoolean.build(false);
    }
    else
      return ExtendedBoolean.buildUnknown(value);
  }

  public static ExtendedInt kwotaConv(String value)
  {
    if (value == null)
      return null;

    if (intValidator.isValid(value))
      return ExtendedInt.build(Integer.parseInt(value));
    else
      return ExtendedInt.buildUnknown(value);
  }

  public static ExtendedLocalDateTime dateTimeConv(String value, String pattern)
  {
    if (value == null)
      return null;

    try
    {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
      LocalDateTime dateTime = LocalDateTime.parse(value, formatter);
      return ExtendedLocalDateTime.build(dateTime);
    }
    catch (Exception e)
    {
      log.debug(() -> e.getMessage());
      return ExtendedLocalDateTime.buildUnknown(value);
    }
  }

}
