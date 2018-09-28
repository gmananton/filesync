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

    public void sync(String sourceFolder, String targetFolder) {

        try {
            List<Path> sourceFiles = Files.list(Paths.get(sourceFolder)).collect(toList());
            List<Path> targetFiles = Files.list(Paths.get(targetFolder)).collect(toList());


            List<String> sourceFileNamesWithoutExtension = sourceFiles
                    .stream()
                    .map(f -> f.toFile().getName().replaceAll("(?i).jpg", ""))
                    .collect(toList());

            targetFiles
                    .stream()
                    .filter(f -> getName(f).endsWith(".CR2"))
                    .forEach(f -> {
                        String nameNoExtension = getName(f).replace(".CR2", "");
                        if (!sourceFileNamesWithoutExtension.contains(nameNoExtension)) {
                            try {
                                Files.delete(f);
                                System.out.println("No file " + nameNoExtension + " in source folder. File removed from target folder.");
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
