package net.twerno.carFinder.base.utils;

import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

import lombok.extern.log4j.Log4j2;
import net.twerno.carFinder.base.utils.extendedType.ExtendedBoolean;
import net.twerno.carFinder.base.utils.extendedType.ExtendedTypeHelper;

@Log4j2
public abstract class AbstractParamsMapper<P>
{

  private HashMap<String, ParamMapper<P, ?>> registeredMappers = new HashMap<>();

  public void parse(P car, String label, String value)
  {
    ParamMapper<P, ?> paramMapper = registeredMappers.get(label);
    if (paramMapper == null)
    {
      log.info("Brak mappera dla parametru: \"" + label + "\" o wartości=\"" + value + "\"");
      return;
    }

    paramMapper.set(car, value);
  }

  protected void registerParamMapper(String label, BiConsumer<P, String> setter)
  {
    ParamMapper<P, String> paramMapper = new ParamMapper<>(label, setter, v -> v);
    registeredMappers.put(label, paramMapper);
  }

  protected <T> void registerParamMapper(String label, BiConsumer<P, T> setter,
      Function<String, T> converter)
  {
    ParamMapper<P, T> mapper = new ParamMapper<>(label, setter, converter);
    registeredMappers.put(label, mapper);
  }

  protected ParamMapperBuilder<P, String> pole(String label)
  {
    return new ParamMapperBuilder<P, String>(this, label, v -> v);
  }

  protected ParamMapperBuilder<P, ExtendedBoolean> poleTakNie(String label)
  {
    return new ParamMapperBuilder<P, ExtendedBoolean>(this, label, ExtendedTypeHelper::takNieValConv);
  }

  public static final class ParamMapperBuilder<P, T>
  {
    private final String label;
    private final AbstractParamsMapper<P> paramsMapper;
    private final Function<String, T> converter;

    public ParamMapperBuilder(AbstractParamsMapper<P> paramsMapper, String label, Function<String, T> converter)
    {
      this.label = label;
      this.converter = converter;
      this.paramsMapper = paramsMapper;
    }

    public <T2> ParamMapperBuilder<P, T2> konwertujWartosc(Function<String, T2> converter)
    {
      return new ParamMapperBuilder<P, T2>(paramsMapper, label, converter);
    }

    public void zarejestruj(BiConsumer<P, T> setter)
    {
      paramsMapper.registerParamMapper(label, setter, converter);
    }

    public void ignoruj()
    {
      BiConsumer<P, T> dummySetter = (p, v) ->
      {
      };

      paramsMapper.registerParamMapper(label, dummySetter, converter);
    }

  }

}
