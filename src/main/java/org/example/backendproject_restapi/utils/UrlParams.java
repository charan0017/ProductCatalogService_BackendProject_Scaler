package org.example.backendproject_restapi.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to create URL parameters in a builder-like fashion
 *
 * @author Inderjeet Singh
 */
public class UrlParams {
    // Using two lists instead of a map to ensure that parameters are printed in the order
    // they were put. This also allows a key to appear multiple times.
    private final List<String> keys = new ArrayList<String>();
    private final List<String> values = new ArrayList<String>();

    /** Adds the URL parameter if and only if both key and value are not empty */
    public UrlParams add(String key, String value) {
        if (!isNullOrWhitespace(key) && !isNullOrWhitespace(value)) {
            keys.add(key);
            values.add(value);
        }
        return this;
    }

    public UrlParams add(String key, boolean value) {
        keys.add(key);
        values.add(String.valueOf(value));
        return this;
    }

    public UrlParams add(String key, List<String> strings) {
        return add(key, toCsvString(strings));
    }

    /** appends the parameters to a base URL in the form ?a=b&c=d */
    public void appendToUrl(StringBuilder sb) {
        try {
            boolean first = true;
            for (int i = 0; i < keys.size(); ++i) {
                if (first) {
                    first = false;
                    sb.append('?');
                } else {
                    sb.append('&');
                }
                sb.append(URLEncoder.encode(keys.get(i), "UTF-8"));
                sb.append('=');
                sb.append(URLEncoder.encode(values.get(i), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendToUrl(sb);
        return sb.toString();
    }

    private static boolean isNullOrWhitespace(String str) {
        return str == null || str.trim().isEmpty();
    }

    private static String toCsvString(List<String> strings) {
        String value = null;
        if (strings != null) {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (String str : strings) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append(str);
            }
            value = sb.toString();
        }
        return value;
    }
}
