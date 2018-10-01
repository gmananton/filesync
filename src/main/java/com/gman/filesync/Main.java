package com.gman.filesync;

/**
 * Created by Anton Mikhaylov on 28/09/2018.
 */
public class Main {

    public static void main(String[] args) {

        if (args.length < 4) {
            System.err.println("The app must be run as: java -jar <source_folder> <target_folder> <source_extension> <target_extension>");
            System.exit(1);
        }

        String sourceFolder = args[0];
        String targetFolder = args[1];
        String sourceExtension = args[2];
        String targetExtension = args[3];


        new Synchronizer(sourceExtension, targetExtension).sync(sourceFolder, targetFolder);
    }
}
