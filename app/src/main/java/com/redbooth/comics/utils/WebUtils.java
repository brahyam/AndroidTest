package com.redbooth.comics.utils;

/**
 * Created by Brahyam on 20/11/2016.
 */

public class WebUtils {

    /**
     * Takes a string and returns its getMD5Hash hash
     * @param md5 plain text to hash
     * @return getMD5Hash hash value of given string.
     */
    public static String getMD5Hash(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
