package com.gman.filesync;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Anton Mikhaylov on 28/09/2018.
 */
public class Synchronizer {

    //Расширение исходных файлов без учета регистра
    private final String sourceExtension;

    //Расширение файлов в target-папке, которые надо синхронизировать
    private final String targetExtension;

    public Synchronizer(String sourceExtension, String targetExtension) {
        this.sourceExtension = sourceExtension;
        this.targetExtension = targetExtension;
    }

    public void sync(String sourceFolder, String targetFolder) {

        String srcExtensionIgnoreCase = "(?i)." + sourceExtension;
        String targetExtensionIgnoreCase = "(?i)." + targetExtension;

        try {
            List<Path> sourceFiles = Files.list(Paths.get(sourceFolder)).collect(toList());
            List<Path> targetFiles = Files.list(Paths.get(targetFolder)).collect(toList());


            //Удаление расширения без учета регистра

            List<String> sourceFileNamesWithoutExtension = sourceFiles
                    .stream()
                    .map(f -> f.toFile().getName().replaceAll(srcExtensionIgnoreCase, ""))
                    .collect(toList());

            targetFiles
                    .stream()
                    .filter(f -> getName(f).endsWith(".CR2"))
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getName(Path path) {
        return path.toFile().getName();
    }
}
