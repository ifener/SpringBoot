package com.wey.console;

/**
 * SOAP时报以下错误： Invalid white space character (0x17) in text to output 
 * 网上找的三种方法
 * @author weisunqing
 *
 */
public class SoapInvalidCharacterTest {
    
    public static void main(String[] args) {
        System.out.println(trimAllISOControl(" helloworld"));
        System.out.println(wrapXmlContent(" helloworld"));
        System.out.println(xmlFilter(" helloworld"));
    }
    
    public static String trimAllISOControl(String str) {
        if (str == null || str.trim().isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int index = 0;
        while (sb.length() > index) {
            if (Character.isISOControl(sb.charAt(index))) {
                sb.deleteCharAt(index);
            }
            else {
                index++;
            }
        }
        return sb.toString();
    }
    
    public static String wrapXmlContent(String content) {
        StringBuffer appender = new StringBuffer("");
        
        if ((content != null) && (!content.trim().isEmpty())) {
            appender = new StringBuffer(content.length());
            
            for (int i = 0; i < content.length(); i++) {
                char ch = content.charAt(i);
                if ((ch == '\t') || (ch == '\n') || (ch == '\r') || ((ch >= ' ') && (ch <= 55295))
                        || ((ch >= 57344) && (ch <= 65533)) || ((ch >= 65536) && (ch <= 1114111))) {
                    appender.append(ch);
                }
            }
        }
        String result = appender.toString();
        
        return "<![CDATA[" + result.replaceAll("]]>", "]]<") + "]]>";
    }
    
    public static String xmlFilter(String str) {
        StringBuffer out = new StringBuffer();
        char current;
        for (int i = 0; i < str.length(); i++) {
            current = str.charAt(i);
            if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
                    || ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
                out.append(current);
        }
        return out.toString();
    }
    
}
