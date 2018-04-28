package net.twerno.carFinder.base.model;

import lombok.Data;
import net.twerno.carFinder.base.slownik.StanEnum;
import net.twerno.carFinder.base.utils.extendedType.ExtendedBoolean;
import net.twerno.carFinder.base.utils.extendedType.ExtendedEnum;

@Data
public class CarParam
{
  private String kategoria;

  private String marka_pojazdu;

  private String model_pojazdu;

  private String wersja;

  private String rok_produkcji;

  private String przebieg;

  private String pojemnosc_skokowa;

  private String vin;

  private String rodzaj_paliwa;

  private String moc;

  private String skrzynia_biegow;

  private String naped;

  private String typ;

  private String liczba_drzwi;

  private String liczba_miejsc;

  private String kolor;

  private ExtendedBoolean metalik;

  private ExtendedBoolean mozliwosc_finansowania;

  private ExtendedBoolean faktura_vat;

  private ExtendedBoolean vat_marza;

  private ExtendedBoolean leasing;

  private String kraj_pochodzenia;

  private String pierwsza_rejestracja;

  private ExtendedBoolean zarejestrowany_w_polsce;

  private ExtendedBoolean pierwszy_wlasciciel;

  private ExtendedBoolean bezwypadkowy;

  private ExtendedBoolean serwisowany_w_aso;

  private ExtendedEnum<StanEnum> stan;

  private String numer_rejestracyjny;
}
