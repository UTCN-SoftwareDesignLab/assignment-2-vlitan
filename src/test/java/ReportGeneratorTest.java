import main.model.Book;
import main.service.CsvReportService;
import main.service.ReportService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class ReportGeneratorTest {
    private static final String REPORT_PATH = "test";
    //  @InjectMocks
    ReportService reportService = new CsvReportService();

    @Test
    public void testFileCreation(){
        try {
            reportService.generateReport("test", new ArrayList<Book>());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.getProperty("user.dir"));
        File f = new File(REPORT_PATH+".csv");
        Assert.assertTrue(f.exists() && !f.isDirectory());
    }
    @Test
    public void testFileLength(){
        try {
            reportService.generateReport("test", new ArrayList<Book>(Arrays.asList(new Book(), new Book())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.getProperty("user.dir"));
        File f = new File(REPORT_PATH+".csv");
        long numOfLines = 0;
        try (Stream<String> lines = Files.lines(Paths.get(REPORT_PATH+".csv"), Charset.defaultCharset())) {
            numOfLines = lines.count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(numOfLines, 3);
    }
}
