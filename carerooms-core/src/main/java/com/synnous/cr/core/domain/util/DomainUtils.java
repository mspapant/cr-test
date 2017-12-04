package com.synnous.cr.core.domain.util;

import org.apache.tika.Tika;

/**
 * The domain utils.
 *
 * @author : Manos Papantonakos on 30/1/2017.
 */
public class DomainUtils {

    /** The type tika. */
    private static final Tika typeTika = new Tika();

    /**
     * Returns the filename extension.
     *
     * @param fileName
     *         the file name
     * @return the extension
     */
    public static String getFilenameExtension(final String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * Returns the file type.
     *
     * @param fileName
     *         the filename
     * @return the file type
     */
    public static String getFileMimeType(final String fileName) {
        return typeTika.detect(fileName);
    }

}