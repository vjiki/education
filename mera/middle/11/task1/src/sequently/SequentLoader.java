package sequently;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import utils.HeaderWriter;
import utils.SiteLoader;

public class SequentLoader {

  public void load(List<URI> uriList) throws IOException, InterruptedException {
    System.out.println("link array size:" + uriList.size());

    Path path = Paths.get("sequent_sites.html");
    long start = System.currentTimeMillis();
    long size = 0;

    File file = new File(path.toString());
    file.delete();
    file.createNewFile();
    HeaderWriter headerWriter = new HeaderWriter();
    SiteLoader siteLoader = new SiteLoader();

    for (URI uri : uriList) {
      headerWriter.writeStartHeader(uri, path);
      size += siteLoader.loadSite(uri, path);
      headerWriter.writeEndHeader(uri, path);
    }

    long finish = System.currentTimeMillis();
    long timeElapsed = finish - start;

    System.out.println("time in ms: " + timeElapsed);
    System.out.println("size in bytes: " + size);

  }
}
