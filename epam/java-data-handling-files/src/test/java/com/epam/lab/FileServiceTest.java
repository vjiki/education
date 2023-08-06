package com.epam.lab;

import com.epam.lab.service.FileService;
import com.epam.lab.service.SimpleFileService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileServiceTest {

  private static final String TEST_DIR_COUNT_PATH = "src/test/resources/testDirCountFiles";
  private static final String TEST_DIR_CREATE_PATH = "src/test/resources/testDirCreateFile";
  private static final String TEST_FILE_TO_CREATE = "newFile.txt";
  private static final String SOURCE_DIRECTORY = "src/test/resources/fileRepository/source";
  private static final String COPY_DIRECTORY = "src/test/resources/fileRepository/copy";
  private static final String COPY_DIRECTORY2 = "src/test/resources/fileRepository/copy/directory2";
  private static final String SOURCE_FILE1 = "src/test/resources/fileRepository/source/TestFileToCopy.txt";
  private static final String SOURCE_FILE2 = "src/test/resources/fileRepository/source/directory2/TestFileToCopy2.txt";
  private static final String COPY_FILE1 = "src/test/resources/fileRepository/copy/TestFileToCopy.txt";
  private static final String COPY_FILE2 = "src/test/resources/fileRepository/copy/directory2/TestFileToCopy2.txt";
  private static final String FILE_TO_READ_EVEN = "src/test/resources/file1_even.txt";
  private static final String FILE_TO_READ_ODD = "src/test/resources/file1_odd.txt";
  private static final String FILE_TO_WRITE1 = "src/test/resources/file2.txt";
  private static final String FILE_TO_WRITE2 = "src/test/resources/file3.txt";

  private static FileService fileService;

  @BeforeAll
  static void setup() {
    fileService = new SimpleFileService();
  }

  @BeforeEach
  void clean() {
    File file = new File(TEST_DIR_CREATE_PATH + "/" + TEST_FILE_TO_CREATE);
    if (file.exists()) {
      file.delete();
    }
    File copyFile1 = new File(COPY_FILE1);
    if (copyFile1.exists()) {
      copyFile1.delete();
    }
    File copyFile2 = new File(COPY_FILE2);
    if (copyFile2.exists()) {
      copyFile2.delete();
    }
    File copyDirectory2 = new File(COPY_DIRECTORY2);
    if (copyDirectory2.exists()) {
      copyDirectory2.delete();
    }
    File fileToWrite1 = new File(FILE_TO_WRITE1);
    if (fileToWrite1.exists()) {
      fileToWrite1.delete();
    }
    File fileToWrite2 = new File(FILE_TO_WRITE2);
    if (fileToWrite2.exists()) {
      fileToWrite2.delete();
    }
  }

  @Test
  void testCountDirsInDirectory() {
    assertEquals(7, fileService.countDirsInDirectory(TEST_DIR_COUNT_PATH));
  }

  @Test
  void testCountFilesInDirectory() {
    assertEquals(10, fileService.countFilesInDirectory(TEST_DIR_COUNT_PATH));
  }

  @Test
  void testCopyTXTFiles() {
    fileService.copyTXTFiles(SOURCE_DIRECTORY, COPY_DIRECTORY);
    File sourceFile1 = new File(SOURCE_FILE1);
    String sourceFile1Content = readLineByLineJava(sourceFile1.getAbsolutePath());
    File copyFile1 = new File(COPY_FILE1);
    String copyFile1Content = readLineByLineJava(copyFile1.getAbsolutePath());
    assertEquals(sourceFile1Content, copyFile1Content);
    File sourceFile2 = new File(SOURCE_FILE2);
    String sourceFile2Content = readLineByLineJava(sourceFile2.getAbsolutePath());
    File copyFile2 = new File(COPY_FILE2);
    String copyFile2Content = readLineByLineJava(copyFile2.getAbsolutePath());
    assertEquals(sourceFile2Content, copyFile2Content);
  }

  @Test
  void testCreateFile() {
    fileService.createFile(TEST_DIR_CREATE_PATH, TEST_FILE_TO_CREATE);

    assertTrue(new File(TEST_DIR_CREATE_PATH + "/" + TEST_FILE_TO_CREATE).exists());
  }

  @Test
  void testReadFileFromResources() {
    assertEquals("Ya-hoo!", fileService.readFile("src/test/resources/readme.txt"));
  }

  @Test
  void testSplitFileContent() {
    fileService.splitFileContent(FILE_TO_READ_EVEN, FILE_TO_WRITE1, FILE_TO_WRITE2);
    File file2 = new File(FILE_TO_WRITE1);
    File file3 = new File(FILE_TO_WRITE2);

    String file2Content = readLineByLineJava(file2.getAbsolutePath());
    assertEquals("abcd", file2Content);
    String file3Content = readLineByLineJava(file3.getAbsolutePath());
    assertEquals("abcd", file3Content);
    file2.delete();
    file3.delete();

    fileService.splitFileContent(FILE_TO_READ_ODD, FILE_TO_WRITE1, FILE_TO_WRITE2);
    file2 = new File(FILE_TO_WRITE1);
    file3 = new File(FILE_TO_WRITE2);
    file2Content = readLineByLineJava(file2.getAbsolutePath());
    assertEquals("abcde", file2Content);
    file3Content = readLineByLineJava(file3.getAbsolutePath());
    assertEquals("abcd", file3Content);
  }

  private String readLineByLineJava(String filePath) {
    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
      stream.forEach(contentBuilder::append);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return contentBuilder.toString();
  }
}
