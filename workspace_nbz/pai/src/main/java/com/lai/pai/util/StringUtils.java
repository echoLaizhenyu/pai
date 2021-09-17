package com.lai.pai.util;

import java.util.Collection;
import java.util.UUID;

/**
 * 继承自Spring util的工具类，减少jar依赖
 * @author L.cm
 */
public class StringUtils extends org.springframework.util.StringUtils {
    /**
     * Check whether the given {@code CharSequence} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code CharSequence} is not {@code null}, its length is greater than
     * 0, and it contains at least one non-whitespace character.
     * <p><pre class="code">
     * StringUtils.isBlank(null) = true
     * StringUtils.isBlank("") = true
     * StringUtils.isBlank(" ") = true
     * StringUtils.isBlank("12345") = false
     * StringUtils.isBlank(" 12345 ") = false
     * </pre>
     * @return {@code true} if the {@code CharSequence} is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean isBlank(final CharSequence cs) {
        return !StringUtils.isNotBlank(cs);
    }
    
    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is
     *  not empty and not null and not whitespace
     * @see Character#isWhitespace
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return StringUtils.hasText(cs);
    }
    
    /**
     * Convert a {@code Collection} into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     * @param coll the {@code Collection} to convert
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String join(Collection<?> coll, String delim) {
        return StringUtils.collectionToDelimitedString(coll, delim);
    }
    
    /**
     * Convert a {@code String} array into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     * @param arr the array to display
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String join(Object[] arr, String delim) {
        return StringUtils.arrayToDelimitedString(arr, delim);
    }
    
    /**
     * 生成uuid
     * @return UUID
     */
    public static String getUUId() {
        return UUID.randomUUID().toString();
    }
    /**
     * 生成uuid
     * @return UUID
     */
    public static String getStrFromNull(String str) {
    	if(str == null){
    		return "";
    	}else{
    		if("null".equals(str) || "NULL".equals(str)){
    			return "";
    		}else{
    			return str;
    		}
    	}
    }
    public static String getStrFromNull_2(String str) {
        if(str == null){
            return "0";
        }else{
            if("null".equals(str) || "NULL".equals(str) || "".equals(str)){
                return "0";
            }else{
                return str;
            }
        }
    }
    public static String addZero(String str,int num,boolean typeF){
  		String returnStr = str;
  		for (int i = 0; i < (num-str.trim().length()); i++) {
  			if(typeF){
  				returnStr="0"+returnStr;
  			}else{
  				returnStr=returnStr+"0";
  			}
  		}
  		return returnStr;
  	}

  	public static String mulStr(String str,int num){
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < num; i++) {
            sbf.append(str);
        }
        return sbf.toString();
    }
}
