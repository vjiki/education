package consequently;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import utils.HeaderWriter;
import utils.SiteLoader;

public class ConsequentLoader {

  public void load(List<URI> uriList) throws IOException, InterruptedException, ExecutionException {
    System.out.println("link array size:" + uriList.size());

//    ExecutorService executorService = Executors.newScheduledThreadPool(20);


    Path path = Paths.get("consequent_sites.html");
    long start = System.currentTimeMillis();
    long size = 0;

    File file = new File(path.toString());
    file.delete();
    file.createNewFile();
//    List<Future<Long>> futureList = new ArrayList<>();
//    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
//    Lock writeLock = rwLock.writeLock();

//    CopyOnWriteArrayList<byte[]> copyOnWriteArrayList = new CopyOnWriteArrayList();





    try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file, false))){
//      for (URI uri : uriList) {
//        final Future<Long> taskResult = executorService.submit(new LoadSite(uri, copyOnWriteArrayList));
//        futureList.add(taskResult);
//      }

//      for (byte[] toWrite: copyOnWriteArrayList) {
//        outputStream.write(toWrite);
//      }

      loadUrlsToStream(uriList, outputStream);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

//    executorService.shutdown();



//    for (Future<Long> f: futureList) {
//      size += f.get();
//    }


    long finish = System.currentTimeMillis();
    long timeElapsed = finish - start;

    System.out.println("time in ms: " + timeElapsed);
    System.out.println("size in bytes: " + size);

  }

  public void loadUrlsToStream(List<URI> uriList, OutputStream outputStream) throws IOException {
    ExecutorService executorService = Executors.newFixedThreadPool(uriList.size());
    List<Callable<byte[]>> taskList = new ArrayList<>();
    for(URI uri: uriList){
      taskList.add(() -> {
        ByteArrayOutputStream tmpStream = new ByteArrayOutputStream();
         loadUrl(uri, tmpStream);
        return tmpStream.toByteArray();
      });
    }
    try {
      List<Future<byte[]>> result = executorService.invokeAll(taskList);
      System.out.println("invoke all");
      for(Future<byte[]> future: result){
        outputStream.write(future.get());
      }
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }finally {
      System.out.println("shutdown");
      executorService.shutdown();
    }
  }

  public void loadUrl(URI uri, OutputStream outputStream) throws IOException {
    String beginSiteString = "===== НАЧАЛО САЙТА "+uri+" ======\n";
    String endSiteString = "===== КОНЕЦ САЙТА "+uri+" ======\n";

    try(InputStream stream = uri.toURL().openStream()){
      outputStream.write(beginSiteString.getBytes());
      outputStream.write(stream.readAllBytes());
      outputStream.write(endSiteString.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
