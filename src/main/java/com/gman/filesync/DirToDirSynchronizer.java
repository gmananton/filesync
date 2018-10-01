package com.gman.filesync;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Anton Mikhaylov on 01/10/2018.
 */
public class DirToDirSynchronizer extends AbstractSynchronizer {

    private final String sourceDir;

    public DirToDirSynchronizer(String sourceDir, String targetDir, String sourceExtension, String targetExtension) {
        super(targetDir, sourceExtension, targetExtension);
        this.sourceDir = sourceDir;
    }

    @Override
    protected List<Path> getSourceFiles() {
        return getFilesFromDir(Paths.get(sourceDir));
    }
}
