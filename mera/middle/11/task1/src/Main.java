import consequently.ConsequentLoader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import sequently.SequentLoader;

/*
Задание 9. Параллельные измерения.

1. Создайте упорядоченную коллекцию из 20 http ссылок
2. Загрузите содержимое всех 20 ссылок последовательно.
3. Выведите на экран время, которое потребовалось, чтобы загрузить всё содержимое и суммарный размер содержимого всех 20 ссылок в байтах
4. Запишите содержимое всех сайтов в файл sequential.txt в формате:
===== НАЧАЛО САЙТА <ссылка1> ======
 <содержимое по ссылке>
===== КОНЕЦ САЙТА <ссылка1> ======
===== НАЧАЛО САЙТА <ссылка2> ======
....

Это важно: Порядок должен быть тем же самым, в котором у вас были упорядочены ссылки в исходной коллекции.

5. Теперь загрузите содержимое всех 20 ссылок параллельно.
3. Выведите на экран время, которое потребовалось, чтобы загрузить всё содержимое и суммарный размер содержимого всех 20 ссылок в байтах
4. Запишите содержимое всех сайтов в файл parallel.txt в таком же формате, как и для последовательного скачивания.
 Напоминаю, что содержимое должно быть упорядоченно.


Советы:
- Для хранения содержимого разных ссылок используйте потокобезопасные коллекции.(например, CopyOnWriteArrayList)
- Для получения содержимого используйте new URL().openStream() (не забывайте закрывать поток)
 */

public class Main {


  static List<URI> getURIList() throws URISyntaxException {
    List<URI> uriList = new ArrayList<>();

    uriList.add(new URI("https://news.google.com/home?hl=ru&gl=RU&ceid=RU:ru"));
    uriList.add(new URI("https://bigpicture.ru/"));
    uriList.add(new URI("https://www.gazeta.ru/"));
    uriList.add(new URI("https://www.maxim.com/"));
    uriList.add(new URI("https://www.maximonline.ru/"));
    uriList.add(new URI("https://meduza.io/"));
//    uriList.add(new URI("https://www.baeldung.com/"));
    uriList.add(new URI("https://www.woolha.com/tutorials/project-reactor-cachemono-cacheflux-with-caffeine-examples"));
//    uriList.add(new URI("https://www.baeldung.com/spring-webflux-cacheable"));
//    uriList.add(new URI("https://nickolasfisher.com/blog/InMemory-Caching-in-Sprint-Boot-WebfluxProject-Reactor"));
    uriList.add(new URI("https://bigpicture.ru/dobrye-kotoskazki-xudozhnika-aleksandra-maskaeva/"));
    uriList.add(new URI("https://bigpicture.ru/stalnoe-ocharovanie-dani-jell-shpigel/"));
    uriList.add(new URI("https://nickolasfisher.com/blog/InMemory-Caching-in-Sprint-Boot-WebfluxProject-Reactor"));
    uriList.add(new URI("https://bigpicture.ru/rabotaem-i-hudeem-5-sovetov-po-borbe-s-lishnim-vesom-v-ofise/"));
    uriList.add(new URI("https://bigpicture.ru/10-stydnyx-kinoscen-o-kotoryx-aktery-predpochli-by-ne-vspominat/"));
    uriList.add(new URI("https://bigpicture.ru/durnaja-krov-jelizabet-holms/"));
    uriList.add(new URI("https://bigpicture.ru/kak-prozhivat-neprijatnye-jemocii/"));
    uriList.add(new URI("https://bigpicture.ru/pochemu-kaznili-uchitelnicu/"));
    uriList.add(new URI("https://www.decipherzone.com/blog-detail/static-code-analysis-tools-for-java"));
    uriList.add(new URI("https://stackoverflow.com/questions/65600895/how-to-get-call-back-when-all-files-are-uploaded"));

    return uriList;
  }


  public static void main(String[] args)
      throws URISyntaxException, IOException, InterruptedException, ExecutionException {
    SequentLoader sequentLoader = new SequentLoader();
    sequentLoader.load(getURIList());

    ConsequentLoader consequentLoader = new ConsequentLoader();
    consequentLoader.load(getURIList());
  }
}