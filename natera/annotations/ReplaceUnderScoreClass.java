import static java.util.Locale.forLanguageTag;

import java.util.Locale;

public class ReplaceUnderScoreClass {

  private static final int LOCALE_MAX_LENGTH = 5;

  public static Locale replaceUnderScores(Locale locale) {
    String localeName = locale.toString();
    if (locale.toString().length() > LOCALE_MAX_LENGTH) {
      String[] languageCountry =  localeName.split("_+",2);
      return languageCountry.length == 2 ? new Locale(languageCountry[0], languageCountry[1].toUpperCase()) : locale;
    } else {
      return locale;
    }
  }

  public static void main(String[] args) {

    String euEU = "en__EU";
    Locale locale = new Locale(euEU);
    Locale usLocale = Locale.US;
    System.out.println(replaceUnderScores(locale));
    System.out.println(replaceUnderScores(usLocale));

  }
}
