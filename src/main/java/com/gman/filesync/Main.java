package com.gman.filesync;

import java.nio.file.Paths;

/**
 * Created by Anton Mikhaylov on 28/09/2018.
 */
public class Main {

    public static void main(String[] args) {

        if (args.length < 8) {
            System.err.println("The app must be run as: \n" +
                    "java -jar \n" +
                    "-SD, --source-dir <source_dir> (or -SF, --source-file) \n" +
                    "-TD, --target-dir <target_dir> \n" +
                    "-SE, --source-extension <source_extension> \n" +
                    "-TE, --target-extension <target_extension> \n" +
                    "app.jar");

            System.exit(1);
        }


        String sourceDir = "";
        String sourceFile = "";
        String targetDir = "";
        String sourceExtension = "";
        String targetExtension = "";

        for (int i = 0; i < args.length; i++) {

            String arg = args[i];
            String nextArg = args[i+1];

            if (arg.equals("-SD") || arg.equals("--source-dir")) {
                sourceDir = nextArg;

            } else if (arg.equals("-SF") || arg.equals("--source-file")) {
                sourceFile = nextArg;

            } else if (arg.equals("-TD") || arg.equals("--target-dir")) {
                targetDir = nextArg;

            } else if (arg.equals("-SE") || arg.equals("--source-extension")) {
                sourceExtension = nextArg;

            } else if (arg.equals("-TE") || arg.equals("--target-extension")) {
                targetExtension = nextArg;
            }
        }

        if (sourceFile.equals("")) {
            new DirToDirSynchronizer(sourceDir, targetDir, sourceExtension, targetExtension);
        } else {
            new FileListSynchronizer(sourceFile, targetDir, sourceExtension, targetExtension);
        }
    }

}
