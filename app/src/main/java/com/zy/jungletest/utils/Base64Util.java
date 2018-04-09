package com.zy.jungletest.utils;


import android.text.TextUtils;
import android.util.Base64;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64Util {
    private static final String TAG = "Base64Util";
    private static Set<String> encodedUids = new HashSet();
    private static Set<String> decodedUids = new HashSet();

    public Base64Util() {
    }

    public static String encode(String key) {
        return encode(key, "UTF-8");
    }

    public static String encode(String key, String encode) {
        if(TextUtils.isEmpty(key)) {
            return null;
        } else if(encodedUids.contains(key)) {
            return key;
        } else {
            Object bytes = null;

            byte[] bytes1;
            try {
                bytes1 = key.toLowerCase().getBytes(encode);
            } catch (Exception var5) {
                bytes1 = key.toLowerCase().getBytes();
            }

            byte[] encodeBytes = Base64.encode(bytes1, 0);
            String enc = (new String(encodeBytes)).replaceAll("[\r|\n]", "");
            encodedUids.add(enc);
            return enc;
        }
    }

    public static String decode(String encodeStr) {
        if(TextUtils.isEmpty(encodeStr)) {
            return encodeStr;
        } else if(decodedUids.contains(encodeStr)) {
            return encodeStr;
        } else {
            StringWriter result;
            if(!isLetter(encodeStr)) {
                result = new StringWriter();
                RuntimeException res1 = new RuntimeException("decode error:");
                res1.printStackTrace(new PrintWriter(result));
                String crashInfo = result.toString();
                return encodeStr;
            } else {
                result = null;

                byte[] result1;
                try {
                    result1 = Base64.decode(encodeStr, 0);
                } catch (Exception var4) {
                    result1 = encodeStr.getBytes();
                }

                String res = new String(result1);
                decodedUids.add(res);
                return res;
            }
        }
    }

    public static boolean isLetter(String kk) {
        Pattern p = Pattern.compile("^[A-Za-z0-9/+=]*$");
        Matcher matcher = p.matcher(kk);
        return matcher.find();
    }

    public static String fetchEncodeAccount(String mPrefix, String account) {
        String result = null;
        if(mPrefix != null && mPrefix.startsWith("u")) {
            result = encode(account);
        } else {
            result = account;
        }

        return result;
    }

    public static String fetchDecodeAccount(String mPrefix, String account) {
        String result = null;
        if(mPrefix != null && mPrefix.startsWith("u")) {
            result = decode(account);
        } else {
            result = account;
        }

        return result;
    }

    public static String fetchDecodeAccount(String lid) {
        Object result = null;
        if(TextUtils.isEmpty(lid)) {
            return (String)result;
        } else if(lid.length() <= 8) {
            return lid;
        } else {
            String prefix = lid.substring(0, 8);
            String account = lid.substring(8);
            return fetchDecodeAccount(prefix, account);
        }
    }

    public static String fetchEcodeLongUserId(String lid) {
        Object result = null;
        if(TextUtils.isEmpty(lid)) {
            return (String)result;
        } else if(!lid.startsWith("u")) {
            return lid;
        } else if(lid.length() <= 8) {
            return lid;
        } else {
            String prefix = lid.substring(0, 8);
            String account = lid.substring(8);
            return prefix + fetchEncodeAccount(prefix, account);
        }
    }

    public static String fetchDecodeLongUserId(String lid) {
        Object result = null;
        if(TextUtils.isEmpty(lid)) {
            return (String)result;
        } else if(!lid.startsWith("u")) {
            return lid;
        } else if(lid.length() <= 8) {
            return lid;
        } else {
            String prefix = lid.substring(0, 8);
            String account = lid.substring(8);
            return prefix + fetchDecodeAccount(prefix, account);
        }
    }
}
