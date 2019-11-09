package ch1.sec3.ex43;

import edu.princeton.cs.algs4.Queue;

import java.io.File;
import java.util.Arrays;

public class ListingFiles {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please supply a directory name as the first and only argument.");
            return;
        }
        File root = new File(args[0]);
        Queue<String> directoryListing = listFiles(root);
        for (String filename : directoryListing) {
            System.out.println(filename);
        }
    }

    private static Queue<String> listFiles(File root) {
        Queue<String> listing = new Queue<>();
        if (root == null) return listing;
        listing.enqueue(root.getName());
        for (String name : listFiles(root, 1)) {
            listing.enqueue(name);
        }
        return listing;
    }

    private static Queue<String> listFiles(File root, int depth) {
        if (root == null) throw new IllegalArgumentException("Root cannot be null.");

        Queue<String> filenames = new Queue<>();
        File[] filesInDirectory = root.listFiles();
        if (filesInDirectory == null) return filenames;
        Arrays.sort(filesInDirectory);

        for (File f : filesInDirectory) {
            filenames.enqueue("  ".repeat(depth) + f.getName());
            if (f.isDirectory()) {
                for (String g : listFiles(f, depth + 1)) {
                    filenames.enqueue(g);
                }
            }
        }

        return filenames;
    }
}
