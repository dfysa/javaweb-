package top.lfy.lfy.util;

/**
 * @author dfysa
 * @data 25/9/2023 下午12:06
 * @description
 */
public class StringUtil {
    public static String subUri(String uri){
        int position =uri.lastIndexOf("/");
        return uri.substring(position+1);
    }

}
