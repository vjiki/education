package com.epam.lab.service;

public interface FileService {


    long countFilesInDirectory(String path);

    long countDirsInDirectory(String path);

    void copyTXTFiles(String from, String to);

    boolean createFile(String path, String name);

    String readFile(String fileName);

    void splitFileContent(String file1, String file2, String file3);
}
