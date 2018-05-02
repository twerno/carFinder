package net.twerno.carFinder.base.helper.htmlMapper;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.util.Assert;

import lombok.Value;
import lombok.extern.log4j.Log4j2;
import net.twerno.carFinder.base.model.error.ParserErrors;

//@Log4j2
public class FieldSetter2
{
  public final String fieldName;
  public final ParserErrors errors;

  public FieldSetter2(String fieldName, ParserErrors errors)
  {
    super();
    this.fieldName = fieldName;
    this.errors = errors;
  }

  public <T> ValueSetter<T> from(Function<ValueSetter<?>, ExtendedType<T>> reader)
  {
    return new ValueSetter<T>(fieldName, errors, reader);
  }

  public <T> ValuesSetter<T> fromList(Function<ValuesSetter<?>, List<ExtendedType<T>>> reader)
  {
    return new ValuesSetter<T>(fieldName, errors, reader);
  }

  @Value
  @Log4j2
  public static class ValueSetter<T>
  {
    private String fieldName;
    private ParserErrors errors;
    private Function<ValueSetter<?>, ExtendedType<T>> reader;

    public void into(Consumer<ExtendedType<T>> writter)
    {
      try
      {
        Assert.isNull(reader, "Reader is NULL.");
        Assert.isNull(writter, "Writter is NULL.");

        ExtendedType<T> value = reader.apply(this);
        writter.accept(value);
      }
      catch (Exception e)
      {
        log.error(e);
        errors.add(fieldName, e.getMessage());
      }
    }
  }

  @Log4j2
  @Value
  public static class ValuesSetter<T>
  {

    private String fieldName;
    private ParserErrors errors;
    private Function<ValuesSetter<?>, List<ExtendedType<T>>> reader;

    public void into(Consumer<ExtendedType<T>> writter)
    {
      try
      {
        Assert.isNull(reader, "Reader is NULL.");
        Assert.isNull(writter, "Writter is NULL.");

        List<ExtendedType<T>> values = reader.apply(this);
        values.stream().forEach(v -> setValue(writter, v));
      }
      catch (Exception e)
      {
        log.error(e);
        errors.add(fieldName, e.getMessage());
      }
    }

    private void setValue(Consumer<ExtendedType<T>> writter, ExtendedType<T> value)
    {
      try
      {
        writter.accept(value);
      }
      catch (Exception e)
      {
        log.error(e);
        errors.add(fieldName, e.getMessage());
      }
    }
  }
}
