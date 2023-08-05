package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SiteLoader {


  //    HttpRequest request = HttpRequest.newBuilder()
//        .uri(new URI("https://bigpicture.ru/"))
//        .GET()
//        .build();

//    HttpResponse<String> response = HttpClient.newBuilder()
//        .build()
//        .send(request, BodyHandlers.ofString());
//
//    System.out.println(response);
//
//    HttpResponse<Path> response1 =
//        HttpClient.newBuilder()
//            .build().send(request, BodyHandlers.ofFile(Paths.get("body.html")));

//
//      HttpResponse<Path> response1 =
//          HttpClient.newBuilder()
//              .build().send(request, BodyHandlers.ofFile(path));
  public long loadSite(URI uri, Path path) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(uri)
        .GET()
        .build();

    System.out.println("fetching uri: " + uri);


    HttpResponse<byte[]> response1 =
        HttpClient.newBuilder()
            .build().send(request, BodyHandlers.ofByteArray());


    Files.write(path, response1.body(), StandardOpenOption.APPEND);

    System.out.println("status code: " + response1.statusCode());
    return response1.body().length;
  }

  public long loadSite(URI uri, List<byte[]> arrayList) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(uri)
        .GET()
        .build();

    System.out.println("fetching uri: " + uri);


    HttpResponse<byte[]> response1 =
        HttpClient.newBuilder()
            .build().send(request, BodyHandlers.ofByteArray());

    arrayList.add(response1.body());

    System.out.println("status code: " + response1.statusCode());
    return response1.body().length;
  }

}
