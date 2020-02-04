package com.helloword.test;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {
    private static final Log log = LogFactory.getLog(StringUtil.class);

    /**
     * ��֤id�Ƿ�Ϸ�
     * 
     * @param id
     * @return
     */
    public static boolean validateId(String id) {

        Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{1}[0-9a-zA-Z_\\.]*$");
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    /**
     * ��ʽ��String
     * 
     * @param str
     * @param def ������Ĭ��ֵ
     * @return
     */
    public static String parseString(String str, String def) {
        return StringUtils.isEmpty(str) ? def : str;
    }

    /**
     * ��ʽ��int
     * 
     * @param str
     * @param def ������Ĭ��ֵ
     * @return
     */
    public static int parseInt(String str, int def) {
        int v = def;
        if (str != null && str.length() > 0) {
            try {
                v = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                log.error(e.getMessage(),e);
            }
        }
        return v;
    }

    /**
     * ��ʽ��long
     * 
     * @param str
     * @param def
     * @return
     */
    public static long parseLong(String str, long def) {
        long v = def;
        if (str != null && str.length() > 0) {
            try {
                v = Long.parseLong(str);
            } catch (NumberFormatException e) {
                log.error(e.getMessage(),e);
            }
        }
        return v;
    }
    
    /**
     * ת���ַ�
     * @param str ��ת���ַ���
     * @param chars key=Ҫת����ַ���value=ת�����ַ�
     * @return
     */
    public static String escape(String str,Map<String,String> chars) {
        if(isEmpty(str) || chars == null ||chars.size() == 0)
            return str;
        
        int len = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            String v = chars.get(String.valueOf(c));
            if (isEmpty(v))
                sb.append(c);
            else
                sb.append(v);
        }
        return sb.toString();
    }

    /**
     * ת��html�ַ�
     * 
     * @param str
     * @return
     */
    public static String escapeHtml(String str) {
        if (!hasText(str))
            return str;

        int len = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '"')
                sb.append("&quot;");
            else if (c == '&')
                sb.append("&amp;");
            else if (c == '<')
                sb.append("&lt;");
            else if (c == '>')
                sb.append("&gt;");
            else
                sb.append(c);
        }
        return sb.toString();
    }
    
    public static String escapeJSON(String str) {
        if (!hasText(str))
            return str;

        int len = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '<')
                sb.append("&lt;");
            else if (c == '>')
                sb.append("&gt;");
            else
                sb.append(c);
        }
        return sb.toString();
    }

    /**
     * ת�������ַ�
     * 
     * @param str
     * @return
     */
    public static String escapeSpecialCharacter(String str) {
        if (!hasText(str))
            return str;

        int len = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '$')
                sb.append("\\$");
            else
                sb.append(c);
        }
        return sb.toString();
    }

    /**
     * ת��xml�ַ�
     * 
     * @param str
     * @return
     */
    public static String escapeXml(String str) {
        if (!hasText(str))
            return str;
        
        return StringEscapeUtils.escapeXml(str);

    }

    public static String escapeJava(String str) {
        return StringEscapeUtils.escapeJava(str);
    }



    /**
     * �ж��Ƿ�Ϊ������!
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str.trim());
        return isNum.matches();
    }

    public static String getGBStr(String str) {
        try {
            byte[] temp_t = str.getBytes("ISO8859-1");
            return new String(temp_t, "GBK");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
        }
        return "NULL";
    }

    /**
     * ���ֽ����ȡ�ַ�������
     * 
     * @param str
     * @param length
     * @param charSet �����ʽ��Ĭ��GBK
     * @return
     */
    public static String subString(String str, int length, String charSet) {

        if (str == null)
            return "";

        if (isEmpty(charSet))
            charSet = "GBK";

        int tempSubLength = length;// ��ȡ�ֽ���
        String subStr = str.substring(0, str.length() < length ? str.length() : length);// ��ȡ���Ӵ�
        try {
            int subStrByetsL;
            subStrByetsL = subStr.getBytes(charSet).length;
            // ��ȡ�Ӵ����ֽڳ���
            // ˵����ȡ���ַ����а����к���
            while (subStrByetsL > tempSubLength) {
                length--;
                subStr = str.substring(0, length > str.length() ? str.length() : length);
                subStrByetsL = subStr.getBytes(charSet).length;
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
        }
        return subStr;

    }
}
