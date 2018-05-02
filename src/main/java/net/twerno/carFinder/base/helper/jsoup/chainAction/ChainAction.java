package net.twerno.carFinder.base.helper.jsoup.chainAction;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import net.twerno.carFinder.base.model.error.ParserErrors;

public class ChainAction<S>
{

  private final S value;
  private final ChainActionData actionData;

  public ChainAction(S value, ChainActionData actionData)
  {
    this.value = value;
    this.actionData = actionData;
  };

  public ChainAction<S> validate(BiFunction<S, ChainActionData, ParserErrors> validator)
  {
    if (actionData.canContinue)
    {
      ParserErrors bledy = validator.apply(value, actionData);
      if (bledy != null && !bledy.isEmpty())
      {
        actionData.canContinue = false;
        actionData.errors.addAll(bledy);
      }
    }
    return this;
  }

  public <T> ChainAction<T> transform(Function<S, T> converter)
  {
    T newValue = null;

    if (actionData.canContinue)
      newValue = converter.apply(value);

    return new ChainAction<T>(newValue, actionData);
  }

  public <T> ChainAction<T> transformAndValidate(BiFunction<S, ChainActionData, T> converter)
  {
    T newValue = null;

    if (actionData.canContinue)
      newValue = converter.apply(value, actionData);

    return new ChainAction<T>(newValue, actionData);
  }

  public void then(Consumer<S> consumer)
  {
    if (actionData.canContinue)
      consumer.accept(value);
  }

  public S get()
  {
    return value;
  }

  public ChainAction<S> continueIf(Function<S, Boolean> condition)
  {
    if (actionData.canContinue)
      if (!condition.apply(value))
        actionData.canContinue = false;

    return this;
  }

}
