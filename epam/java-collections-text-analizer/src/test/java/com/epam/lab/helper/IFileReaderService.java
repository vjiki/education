package com.epam.lab.helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public interface IFileReaderService {

    String readFromFileToString(String fileName) throws IOException, URISyntaxException;

    Properties loadProperties(String propertiesFileName) throws IOException;
}
