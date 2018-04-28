package net.twerno.carFinder.base.utils;

import java.util.function.BiConsumer;
import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamMapper<O, T>
{
  private String label;

  private BiConsumer<O, T> setter;

  private Function<String, T> valueConverter;

  public void set(O object, String rawValue)
  {
    T convertedValue = valueConverter.apply(rawValue);
    setter.accept(object, convertedValue);
  }
}
