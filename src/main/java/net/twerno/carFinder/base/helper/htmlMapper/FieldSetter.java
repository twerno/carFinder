package net.twerno.carFinder.base.helper.htmlMapper;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.util.Assert;

import lombok.extern.log4j.Log4j2;
import net.twerno.carFinder.base.model.error.ParserErrors;

@Log4j2
public class FieldSetter<T>
{
  public final String fieldName;
  public final ParserErrors errors;
  private Consumer<ExtendedType<T>> mapper;

  public FieldSetter(String fieldName, ParserErrors errors)
  {
    super();
    this.fieldName = fieldName;
    this.errors = errors;
  }

  public FieldSetter<T> write(Consumer<ExtendedType<T>> mapper)
  {
    this.mapper = mapper;
    return this;
  }

  public void from(Function<FieldSetter<T>, ExtendedType<T>> readder)
  {
    Assert.isNull(this.mapper, "Mapper is NULL.");
    try
    {
      ExtendedType<T> value = readder.apply(this);
      this.mapper.accept(value);
    }
    catch (Exception e)
    {
      log.error(e);
      errors.add(fieldName, e.getMessage());
      this.mapper.accept(new ExtendedType<>(null, null));
    }
  }

  public void fromList(Function<FieldSetter<T>, List<ExtendedType<T>>> readder)
  {
    Assert.isNull(this.mapper, "Mapper is NULL.");
    try
    {
      List<ExtendedType<T>> value = readder.apply(this);
      value.stream().forEach(this.mapper::accept);
    }
    catch (Exception e)
    {
      log.error(e);
      errors.add(fieldName, e.getMessage());
      this.mapper.accept(new ExtendedType<>(null, null));
    }
  }
}
