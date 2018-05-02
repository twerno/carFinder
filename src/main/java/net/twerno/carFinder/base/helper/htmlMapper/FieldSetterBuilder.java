package net.twerno.carFinder.base.helper.htmlMapper;

import java.util.function.Consumer;

import lombok.Value;
import net.twerno.carFinder.base.model.error.ParserErrors;

@Value
public class FieldSetterBuilder
{

  private String fieldName;
  private ParserErrors errors;

  public <T> FieldSetter<T> write(Consumer<ExtendedType<T>> mapper)
  {
    return new FieldSetter<T>(fieldName, errors)
        .write(mapper);
  }

}
