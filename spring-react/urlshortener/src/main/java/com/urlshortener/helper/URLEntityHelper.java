package com.urlshortener.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class URLEntityHelper {

    public static final String MD_5 = "MD5";
    public static final int THREE_SLASHES = 3;
    public static final String SLASH = "/";

    public static String shortenURL(String url) throws NoSuchAlgorithmException {
        String[] urlSplit=url.split(SLASH);
        if(urlSplit.length<=THREE_SLASHES)return url;
        var domainUrl=urlSplit[0]+SLASH+urlSplit[1]+SLASH+urlSplit[2]+SLASH;
        StringBuffer urlToEncodeBuffer=new StringBuffer(urlSplit[THREE_SLASHES]);
        for(int i=THREE_SLASHES+1;i<urlSplit.length;i++){
            urlToEncodeBuffer.append(urlSplit[i]);
        }
        MessageDigest md = MessageDigest.getInstance(MD_5);
        md.update(urlToEncodeBuffer.toString().getBytes());
        return domainUrl + Base64.getUrlEncoder().encodeToString(md.digest());
    }
}
