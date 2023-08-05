package utils;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CopyOnWriteArrayList;

public class HeaderWriter {

  public void writeStartHeader(URI uri, Path path) throws IOException {
    String startHeader = "===== НАЧАЛО САЙТА " + uri + " ======\n";

    Files.write(path, startHeader.getBytes(), StandardOpenOption.APPEND);
  }

  public void writeStartHeader(URI uri, CopyOnWriteArrayList<byte[]> arrayList) {
    String startHeader = "===== НАЧАЛО САЙТА " + uri + " ======\n";

    arrayList.add(startHeader.getBytes());
  }

  public void writeEndHeader(URI uri, Path path) throws IOException {
    String endHeader = "===== КОНЕЦ САЙТА " + uri + " ======\n";

    Files.write(path, endHeader.getBytes(), StandardOpenOption.APPEND);
  }

  public void writeEndHeader(URI uri, CopyOnWriteArrayList<byte[]> arrayList) {
    String endHeader = "===== КОНЕЦ САЙТА " + uri + " ======\n";

    arrayList.add(endHeader.getBytes());
  }

}
