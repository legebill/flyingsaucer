// package PACKAGE;
package org.xhtmlrenderer.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.test.DocumentDiffTest;

import java.io.File;

/**
 * New class
 */
public class Boxer {

    private static final Logger log = LoggerFactory.getLogger(Boxer.class);

    private void run(String filen) {
        try {
            File file = new File(filen);
            if (!file.exists() || file.isDirectory()) {
                throw new IllegalArgumentException(filen + " not a file, or is a directory. Give me a single file name.");
            }

            log.info(DocumentDiffTest.xhtmlToDiff(filen, 1024, 768));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("Give a file name");
            }
            new Boxer().run(args[0]);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
} // end class
