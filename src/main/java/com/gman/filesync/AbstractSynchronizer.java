package com.gman.filesync;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Anton Mikhaylov on 28/09/2018.
 */
public abstract class AbstractSynchronizer {

    //Расширение исходных файлов без учета регистра
    protected final String sourceExtension;

    //Расширение файлов в target-папке, которые надо синхронизировать
    protected final String targetExtension;

    protected final String targetDir;

    public AbstractSynchronizer(String targetDir, String sourceExtension, String targetExtension) {
        this.sourceExtension = sourceExtension;
        this.targetExtension = targetExtension;
        this.targetDir = targetDir;
    }

    protected void sync() {
        String srcExtensionIgnoreCase = "(?i)." + sourceExtension;
        String targetExtensionIgnoreCase = "(?i)." + targetExtension;

        List<Path> sourceFiles = getSourceFiles();
        List<Path> targetFiles = getFilesFromDir(Paths.get(targetDir));


        //Удаление расширения без учета регистра

        List<String> sourceFileNamesWithoutExtension = sourceFiles
                .stream()
                .map(f -> f.toFile().getName().replaceAll(srcExtensionIgnoreCase, ""))
                .collect(toList());

        targetFiles
                .stream()
                .filter(f -> getName(f).toUpperCase().endsWith(targetExtension.toUpperCase()))
                .forEach(f -> {
                    String nameWithoutExtension = getName(f).replaceAll(targetExtensionIgnoreCase, "");
                    if (!sourceFileNamesWithoutExtension.contains(nameWithoutExtension)) {
                        try {
                            Files.delete(f);
                            System.out.println("No file " + nameWithoutExtension + " in source folder. File removed from target folder.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    protected abstract List<Path> getSourceFiles();

    protected List<Path> getFilesFromDir(Path dir) {

        List<Path> result = new LinkedList<>();

        try {
            result = Files.list(dir).collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected String getName(Path path) {
        return path.toFile().getName();
    }
}
