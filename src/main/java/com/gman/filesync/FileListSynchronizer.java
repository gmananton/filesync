package com.gman.filesync;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anton Mikhaylov on 01/10/2018.
 */
public class FileListSynchronizer extends AbstractSynchronizer {

    private final String sourceFile;

    public FileListSynchronizer(String sourceFile, String targetDir, String sourceExtension, String targetExtension) {
        super(targetDir, sourceExtension, targetExtension);
        this.sourceFile = sourceFile;
    }

    @Override
    protected List<Path> getSourceFiles() {

        List<Path> result = new LinkedList<>();

        final String REGEXP = "\\s[^\\s]*.(?i)" + sourceExtension;
        Pattern pattern = Pattern.compile(REGEXP);

        try (FileReader fr = new FileReader(sourceFile); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    result.add(Paths.get(matcher.group()));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
