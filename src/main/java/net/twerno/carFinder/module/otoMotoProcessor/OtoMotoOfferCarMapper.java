package net.twerno.carFinder.module.otoMotoProcessor;

import net.twerno.carFinder.base.model.Car;
import net.twerno.carFinder.base.slownik.SprzedajacyEnum;
import net.twerno.carFinder.base.slownik.StanEnum;
import net.twerno.carFinder.base.utils.AbstractParamsMapper;
import net.twerno.carFinder.base.utils.extendedType.ExtendedEnum;
import net.twerno.carFinder.base.utils.extendedType.ExtendedTypeHelper;

public class OtoMotoOfferCarMapper extends AbstractParamsMapper<Car>
{

  public final String DATA_WYSTAWIENIA_OGLOSZNIA_PATTERN = "kk:mm, dd MM yyyy";

  public OtoMotoOfferCarMapper()
  {

    naglowek();
    sprzedawca();
    parametrySamochodu();
    wyposazenie();
  }

  private void sprzedawca()
  {

    pole("Seller_type")
        .konwertujWartosc(this::sprzedawcaConverter)
        .zarejestruj((p, v) -> p.getSprzedawca().setTyp(v));

    pole("Seller_registration")
        .zarejestruj((p, v) -> p.getSprzedawca().setOd_kiedy(v));

    pole("Seller_adress")
        .zarejestruj((p, v) -> p.getSprzedawca().setAdres(v));

  }

  private void naglowek()
  {
    pole("Kwota")
        .konwertujWartosc(ExtendedTypeHelper::kwotaConv)
        .zarejestruj((p, v) -> p.setKwota(v));

    pole("Data dodania")
        .konwertujWartosc(
            v -> ExtendedTypeHelper.dateTimeConv(
                OtoModoHelper.miesiacDopelniacz2Kod(v), DATA_WYSTAWIENIA_OGLOSZNIA_PATTERN))
        .zarejestruj((p, v) -> p.setData_wystawienia(v));

    pole("Opis")
        .zarejestruj((p, v) -> p.setOpisHtml(v));

    poleTakNie("Do negocjacji")
        .zarejestruj((p, v) -> p.setDoNegocjacji(v));
  }

  public void wyposazenie()
  {
    pole("Oferta od")
        .ignoruj();

    poleTakNie("ABS")
        .zarejestruj((p, v) -> p.getWyposazenie().setABS(v));

    poleTakNie("CD")
        .zarejestruj((p, v) -> p.getWyposazenie().setCD(v));

    poleTakNie("Centralny zamek")
        .zarejestruj((p, v) -> p.getWyposazenie().setCentralny_zamek(v));

    poleTakNie("Elektryczne szyby przednie")
        .zarejestruj((p, v) -> p.getWyposazenie().setElektryczne_szyby_przednie(v));

    poleTakNie("Elektrycznie ustawiane lusterka")
        .zarejestruj((p, v) -> p.getWyposazenie().setElektrycznie_ustawiane_lusterka(v));

    poleTakNie("Immobilizer")
        .zarejestruj((p, v) -> p.getWyposazenie().setImmobilizer(v));

    poleTakNie("Poduszka powietrzna kierowcy")
        .zarejestruj((p, v) -> p.getWyposazenie().setPoduszka_powietrzna_kierowcy(v));

    poleTakNie("Poduszka powietrzna pasażera")
        .zarejestruj((p, v) -> p.getWyposazenie().setPoduszka_powietrzna_pasażera(v));

    poleTakNie("Radio fabryczne")
        .zarejestruj((p, v) -> p.getWyposazenie().setRadio_fabryczne(v));

    poleTakNie("Wspomaganie kierownicy")
        .zarejestruj((p, v) -> p.getWyposazenie().setWspomaganie_kierownicy(v));

    poleTakNie("Alarm")
        .zarejestruj((p, v) -> p.getWyposazenie().setAlarm(v));

    poleTakNie("Alufelgi")
        .zarejestruj((p, v) -> p.getWyposazenie().setAlufelgi(v));

    poleTakNie("ASR (kontrola trakcji)")
        .zarejestruj((p, v) -> p.getWyposazenie().setAsr(v));

    poleTakNie("Asystent parkowania")
        .zarejestruj((p, v) -> p.getWyposazenie().setAsystent_parkowania(v));

    poleTakNie("Asystent pasa ruchu")
        .zarejestruj((p, v) -> p.getWyposazenie().setAsystent_pasa_ruchu(v));

    poleTakNie("Bluetooth")
        .zarejestruj((p, v) -> p.getWyposazenie().setBluetooth(v));

    poleTakNie("Czujnik deszczu")
        .zarejestruj((p, v) -> p.getWyposazenie().setCzujnik_deszczu(v));

    poleTakNie("Czujnik martwego pola")
        .zarejestruj((p, v) -> p.getWyposazenie().setCzujnik_martwego_pola(v));

    poleTakNie("Czujnik zmierzchu")
        .zarejestruj((p, v) -> p.getWyposazenie().setCzujnik_zmierzchu(v));

    poleTakNie("Czujniki parkowania przednie")
        .zarejestruj((p, v) -> p.getWyposazenie().setCzujniki_parkowania_przednie(v));

    poleTakNie("Czujniki parkowania tylne")
        .zarejestruj((p, v) -> p.getWyposazenie().setCzujniki_parkowania_tylne(v));

    poleTakNie("Bluetooth")
        .zarejestruj((p, v) -> p.getWyposazenie().setBluetooth(v));

    poleTakNie("Dach panoramiczny")
        .zarejestruj((p, v) -> p.getWyposazenie().setDach_panoramiczny(v));

    poleTakNie("Elektrochromatyczne lusterka boczne")
        .zarejestruj((p, v) -> p.getWyposazenie().setElektrochromatyczne_lusterka_boczne(v));

    poleTakNie("Elektrochromatyczne lusterko wsteczne")
        .zarejestruj((p, v) -> p.getWyposazenie().setElektrochromatyczne_lusterko_wsteczne(v));

    poleTakNie("Elektryczne szyby tylne")
        .zarejestruj((p, v) -> p.getWyposazenie().setElektryczne_szyby_tylne(v));

    poleTakNie("Elektrycznie ustawiane fotele")
        .zarejestruj((p, v) -> p.getWyposazenie().setElektrycznie_ustawiane_fotele(v));

    poleTakNie("ESP (stabilizacja toru jazdy)")
        .zarejestruj((p, v) -> p.getWyposazenie().setEsp(v));

    poleTakNie("Gniazdo AUX")
        .zarejestruj((p, v) -> p.getWyposazenie().setGniazdo_AUX(v));

    poleTakNie("Gniazdo SD")
        .zarejestruj((p, v) -> p.getWyposazenie().setGniazdo_SD(v));

    poleTakNie("Gniazdo USB")
        .zarejestruj((p, v) -> p.getWyposazenie().setGniazdo_SD(v));

    poleTakNie("Hak")
        .zarejestruj((p, v) -> p.getWyposazenie().setHak(v));

    poleTakNie("HUD (wyświetlacz przezierny)")
        .zarejestruj((p, v) -> p.getWyposazenie().setHUD(v));

    poleTakNie("Isofix")
        .zarejestruj((p, v) -> p.getWyposazenie().setIsofix(v));

    poleTakNie("Kamera cofania")
        .zarejestruj((p, v) -> p.getWyposazenie().setKamera_cofania(v));

    poleTakNie("Klimatyzacja automatyczna")
        .zarejestruj((p, v) -> p.getWyposazenie().setKlimatyzacja_automatyczna(v));

    poleTakNie("Klimatyzacja czterostrefowa")
        .zarejestruj((p, v) -> p.getWyposazenie().setKlimatyzacja_czterostrefowa(v));

    poleTakNie("Klimatyzacja dwustrefowa")
        .zarejestruj((p, v) -> p.getWyposazenie().setKlimatyzacja_dwustrefowa(v));

    poleTakNie("Klimatyzacja manualna")
        .zarejestruj((p, v) -> p.getWyposazenie().setKlimatyzacja_manualna(v));

    poleTakNie("Komputer pokładowy")
        .zarejestruj((p, v) -> p.getWyposazenie().setKomputer_pokładowy(v));

    poleTakNie("Kurtyny powietrzne")
        .zarejestruj((p, v) -> p.getWyposazenie().setKurtyny_powietrzne(v));

    poleTakNie("Łopatki zmiany biegów")
        .zarejestruj((p, v) -> p.getWyposazenie().setLopatki_zmiany_biegów(v));

    poleTakNie("MP3")
        .zarejestruj((p, v) -> p.getWyposazenie().setMp3(v));

    poleTakNie("Nawigacja GPS")
        .zarejestruj((p, v) -> p.getWyposazenie().setNawigacja_GPS(v));

    poleTakNie("Odtwarzacz DVD")
        .zarejestruj((p, v) -> p.getWyposazenie().setOdtwarzacz_DVD(v));

    poleTakNie("Ogranicznik prędkości")
        .zarejestruj((p, v) -> p.getWyposazenie().setOgranicznik_prędkości(v));

    poleTakNie("Ogrzewanie postojowe")
        .zarejestruj((p, v) -> p.getWyposazenie().setOgrzewanie_postojowe(v));

    poleTakNie("Podgrzewana przednia szyba")
        .zarejestruj((p, v) -> p.getWyposazenie().setPodgrzewana_przednia_szyba(v));

    poleTakNie("Podgrzewane lusterka boczne")
        .zarejestruj((p, v) -> p.getWyposazenie().setPodgrzewane_lusterka_boczne(v));

    poleTakNie("Podgrzewane przednie siedzenia")
        .zarejestruj((p, v) -> p.getWyposazenie().setPodgrzewane_przednie_siedzenia(v));

    poleTakNie("Podgrzewane tylne siedzenia")
        .zarejestruj((p, v) -> p.getWyposazenie().setPodgrzewane_tylne_siedzenia(v));

    poleTakNie("Poduszka powietrzna chroniąca kolana")
        .zarejestruj((p, v) -> p.getWyposazenie().setPoduszka_powietrzna_chroniąca_kolana(v));

    poleTakNie("Poduszki boczne przednie")
        .zarejestruj((p, v) -> p.getWyposazenie().setPoduszki_boczne_przednie(v));

    poleTakNie("Poduszki boczne tylne")
        .zarejestruj((p, v) -> p.getWyposazenie().setPoduszki_boczne_tylne(v));

    poleTakNie("Przyciemniane szyby")
        .zarejestruj((p, v) -> p.getWyposazenie().setPrzyciemniane_szyby(v));

    poleTakNie("Radio niefabryczne")
        .zarejestruj((p, v) -> p.getWyposazenie().setRadio_niefabryczne(v));

    poleTakNie("Regulowane zawieszenie")
        .zarejestruj((p, v) -> p.getWyposazenie().setRegulowane_zawieszenie(v));

    poleTakNie("Relingi dachowe")
        .zarejestruj((p, v) -> p.getWyposazenie().setRelingi_dachowe(v));

    poleTakNie("System Start-Stop")
        .zarejestruj((p, v) -> p.getWyposazenie().setSystem_StartStop(v));

    poleTakNie("Szyberdach")
        .zarejestruj((p, v) -> p.getWyposazenie().setSzyberdach(v));

    poleTakNie("Światła do jazdy dziennej")
        .zarejestruj((p, v) -> p.getWyposazenie().setSwiatła_do_jazdy_dziennej(v));

    poleTakNie("Światła LED")
        .zarejestruj((p, v) -> p.getWyposazenie().setSwiatła_LED(v));

    poleTakNie("Światła przeciwmgielne")
        .zarejestruj((p, v) -> p.getWyposazenie().setSwiatła_przeciwmgielne(v));

    poleTakNie("Światła Xenonowe")
        .zarejestruj((p, v) -> p.getWyposazenie().setSwiatła_Xenonowe(v));

    poleTakNie("Tapicerka skórzana")
        .zarejestruj((p, v) -> p.getWyposazenie().setTapicerka_skórzana(v));

    poleTakNie("Tapicerka welurowa")
        .zarejestruj((p, v) -> p.getWyposazenie().setTapicerka_welurowa(v));

    poleTakNie("Tempomat")
        .zarejestruj((p, v) -> p.getWyposazenie().setTempomat(v));

    poleTakNie("Tempomat aktywny")
        .zarejestruj((p, v) -> p.getWyposazenie().setTempomat_aktywny(v));

    poleTakNie("Tuner TV")
        .zarejestruj((p, v) -> p.getWyposazenie().setTuner_TV(v));

    poleTakNie("Wielofunkcyjna kierownica")
        .zarejestruj((p, v) -> p.getWyposazenie().setWielofunkcyjna_kierownica(v));

    poleTakNie("Zmieniarka CD")
        .zarejestruj((p, v) -> p.getWyposazenie().setZmieniarka_CD(v));

  }

  public void parametrySamochodu()
  {
    pole("Kategoria")
        .zarejestruj((p, v) -> p.getParams().setKategoria(v));

    pole("Marka pojazdu")
        .zarejestruj((p, v) -> p.getParams().setMarka_pojazdu(v));

    pole("Model pojazdu")
        .zarejestruj((p, v) -> p.getParams().setModel_pojazdu(v));

    pole("Wersja")
        .zarejestruj((p, v) -> p.getParams().setWersja(v));

    pole("Rok produkcji")
        .zarejestruj((p, v) -> p.getParams().setRok_produkcji(v));

    pole("Przebieg")
        .zarejestruj((p, v) -> p.getParams().setPrzebieg(v));

    pole("Pojemność skokowa")
        .zarejestruj((p, v) -> p.getParams().setPojemnosc_skokowa(v));

    pole("VIN")
        .zarejestruj((p, v) -> p.getParams().setVin(v));

    pole("Rodzaj paliwa")
        .zarejestruj((p, v) -> p.getParams().setRodzaj_paliwa(v));

    pole("Moc")
        .zarejestruj((p, v) -> p.getParams().setMoc(v));

    pole("Skrzynia biegów")
        .zarejestruj((p, v) -> p.getParams().setSkrzynia_biegow(v));

    pole("Napęd")
        .zarejestruj((p, v) -> p.getParams().setNaped(v));

    pole("Typ")
        .zarejestruj((p, v) -> p.getParams().setTyp(v));

    pole("Liczba drzwi")
        .zarejestruj((p, v) -> p.getParams().setLiczba_drzwi(v));

    pole("Liczba miejsc")
        .zarejestruj((p, v) -> p.getParams().setLiczba_miejsc(v));

    pole("Kolor")
        .zarejestruj((p, v) -> p.getParams().setKolor(v));

    poleTakNie("Metalik")
        .zarejestruj((p, v) -> p.getParams().setMetalik(v));

    poleTakNie("Możliwość finansowania")
        .zarejestruj((p, v) -> p.getParams().setMozliwosc_finansowania(v));

    poleTakNie("Faktura VAT")
        .zarejestruj((p, v) -> p.getParams().setFaktura_vat(v));

    poleTakNie("Leasing")
        .zarejestruj((p, v) -> p.getParams().setLeasing(v));

    pole("Kraj pochodzenia")
        .zarejestruj((p, v) -> p.getParams().setKraj_pochodzenia(v));

    pole("Pierwsza rejestracja")
        .zarejestruj((p, v) -> p.getParams().setPierwsza_rejestracja(v));

    poleTakNie("Zarejestrowany w Polsce")
        .zarejestruj((p, v) -> p.getParams().setZarejestrowany_w_polsce(v));

    poleTakNie("Pierwszy właściciel")
        .zarejestruj((p, v) -> p.getParams().setPierwszy_wlasciciel(v));

    poleTakNie("Bezwypadkowy")
        .zarejestruj((p, v) -> p.getParams().setBezwypadkowy(v));

    poleTakNie("Serwisowany w ASO")
        .zarejestruj((p, v) -> p.getParams().setSerwisowany_w_aso(v));

    pole("Stan")
        .konwertujWartosc(this::stanValueConverter)
        .zarejestruj((p, v) -> p.getParams().setStan(v));

    pole("Numer rejestracyjny pojazdu")
        .konwertujWartosc(v -> v.replace("\\s+", ""))
        .zarejestruj((p, v) -> p.getParams().setNumer_rejestracyjny(v));

  }

  private ExtendedEnum<StanEnum> stanValueConverter(String value)
  {
    if (value == null)
      return null;

    switch (value)
    {
      case "Używane":
        return ExtendedEnum.build(StanEnum.UZYWANY);
      case "Nowe":
        return ExtendedEnum.build(StanEnum.NOWY);
      default:
        return ExtendedEnum.buildUnknown(value, StanEnum.class);
    }
  }

  private ExtendedEnum<SprzedajacyEnum> sprzedawcaConverter(String value)
  {
    if (value == null)
      return null;

    switch (value)
    {
      case "Osoba prywatna":
        return ExtendedEnum.build(SprzedajacyEnum.OSOBA_PRYWATNA);
      case "Dealer":
        return ExtendedEnum.build(SprzedajacyEnum.DEALER);
      default:
        return ExtendedEnum.buildUnknown(value, SprzedajacyEnum.class);
    }
  }

}
