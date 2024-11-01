import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;

public class PDFMerger {
  public static void main(String[] args) {
    // List of PDF files to be merged
    String[] sourceFiles = {
        "path/to/first.pdf",
        "path/to/second.pdf",
        "path/to/third.pdf"
    };

    // Output file
    String destinationFile = "path/to/merged.pdf";

    PDFMergerUtility mergerUtility = new PDFMergerUtility();

    // Add the source files
    for (String file : sourceFiles) {
      mergerUtility.addSource(new File(file));
    }

    // Set the destination file
    mergerUtility.setDestinationFileName(destinationFile);

    try {
      // Merge the documents
      mergerUtility.mergeDocuments(null);
      System.out.println("PDFs merged successfully into " + destinationFile);
    } catch (IOException e) {
      System.err.println("Error merging PDFs: " + e.getMessage());
    }
  }
}
