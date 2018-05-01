package main.service;

import main.model.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PdfReportService implements ReportService {

/*TODO  the cureent encoding / font does not support scedilla and other characters
* TODO add support for multiple pages
* TODO add support for multi-line entry
* */
    @Override
    public void generateReport(String name, List<Book> books) throws IOException {
        //Creating PDF document object
        PDDocument document = new PDDocument();

        PDPage blankPage = new PDPage();
        //Adding the blank page to the document
        document.addPage( blankPage );

        //Retrieving the pages of the document
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.COURIER, 12);

        //Setting the leading
        contentStream.setLeading(14.5f);

        //Setting the position for the line
        contentStream.newLineAtOffset(25, 725);

        for (Book book : books){
            contentStream.showText(book.toString());
            contentStream.newLine();
        }

        contentStream.endText();
        //Closing the content stream
        contentStream.close();

        //Saving the document
        document.save(new File(name + getFileExtension()));

        //Closing the document
        document.close();
    }

    @Override
    public String getFileExtension() {
        return ".pdf";
    }

}
