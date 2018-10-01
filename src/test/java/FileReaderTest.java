import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anton Mikhaylov on 01/10/2018.
 */
public class FileReaderTest {

    String extension = "JPG";

    final String PATH = "/Users/gman/GoogleDrive/1.txt";

    //Space character, than any char sequence except space, than ending with file extension ignoring case
    final String REGEXP = "\\s[^\\s]*.(?i)" + extension;
    Pattern pattern = Pattern.compile(REGEXP);


    @Test
    public void readFile() throws Exception {



        BufferedReader br = new BufferedReader(new FileReader(PATH));

        String line;
        while ((line = br.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                System.out.println(matcher.group());
            }

        }
    }

}
