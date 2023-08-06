package com.epam.lab.service;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

public class SimpleFileService implements FileService {

    /**
     * should recursively count number of files in directory
     *
     * @param path directory path
     * @return number of files
     */
    @Override
    public long countFilesInDirectory(String path) {
        try {
             return Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * should recursively count number of folders in directory (including root)
     *
     * @param path directory path
     * @return number of folders
     */
    @Override
    public long countDirsInDirectory(String path) {
        try {
            return Files.walk(Paths.get(path))
                    .filter(Files::isDirectory)
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * should copy .txt file
     *
     * @param from path
     * @param to path
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        try {
            Files.walk(Paths.get(from)).forEach(p ->
            {
                try {
                    //System.out.println("from: " + p);
                    //System.out.println("to: " + Paths.get(to).resolve(Paths.get(from).relativize(p)));
                    Path q = Paths.get(to).resolve(Paths.get(from).relativize(p));
                    if (Files.isDirectory(p)) {
                        if (Files.notExists(q)) {
                            Files.createDirectory(q);
                        }
                    } else {
                        Files.copy(p, q, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * creates txt file
     *
     * @param path path to new file
     * @param name name of file
     * @return whether the file was created or not
     */
    @Override
    public boolean createFile(String path, String name) {
        Path fileName = Paths.get(path).resolve(name);
        Path dirName = Paths.get(path);

        System.out.println(fileName);
        if (Files.notExists(fileName)) {
            try {
                if (Files.notExists(dirName)) {
                    Files.createDirectory(dirName);
                }
                Files.createFile(fileName);
                return true;
            } catch (IOException e) {
                //e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * reads file's content
     *
     * @param path path to file
     * @return content of the file
     */
    @Override
    public String readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path)).get(0);
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * should divide file1:
     * the first half of the file should be written to file2
     * the second half of the file should be written to file3
     * If file1 contains odd number of bytes, then the file2 should contain the bigger part of file1
     * @param filePath1 - initial file
     * @param filePath2 - file to write the first half of file1
     * @param filePath3 - file to write the second half of file1
     */
    @Override
    public void splitFileContent(String filePath1, String filePath2, String filePath3) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath1));
            if(bytes.length > 0) {
                int halfLength = (bytes.length % 2) == 0 ? (bytes.length / 2) : (bytes.length/2) + 1;

                byte[] filePath2Bytes = Arrays.copyOfRange(bytes, 0, halfLength);
                byte[] filePath3Bytes = Arrays.copyOfRange(bytes, halfLength, bytes.length);

                Files.write(Paths.get(filePath2), filePath2Bytes, StandardOpenOption.CREATE);
                Files.write(Paths.get(filePath3), filePath3Bytes, StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
