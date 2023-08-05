package consequently;

import java.net.URI;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import utils.HeaderWriter;
import utils.SiteLoader;

public class LoadSite implements Callable {

  URI uri;
  CopyOnWriteArrayList<byte[]> arrayList;

  public LoadSite(URI uri, CopyOnWriteArrayList<byte[]> arrayList) {
    this.uri = uri;
    this.arrayList = arrayList;
  }

  @Override
  public synchronized Long call() throws Exception {


    HeaderWriter headerWriter = new HeaderWriter();
    SiteLoader siteLoader = new SiteLoader();


    headerWriter.writeStartHeader(uri, arrayList);
    Long size = siteLoader.loadSite(uri, arrayList);
    headerWriter.writeEndHeader(uri, arrayList);

    return size;
  }
}
