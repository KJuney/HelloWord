package com.helloword.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 */


/**
 * @author <a href="mailto:lihz2@asiainfo.com">FluteD</a>
 * @version 2016年9月18日
 */
public class FileUtil {
    protected static Log log = LogFactory.getLog(FileUtil.class.getName());

    /**
     * 从文件流转成byte
     * @param fileStream
     * @return
     */
    public static byte[] parseToByte(InputStream fileStream) {
        try (
            BufferedInputStream bis = new BufferedInputStream(fileStream);
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();) {
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = bis.read(buff)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            return swapStream.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return new byte[0];
    }
    
    /**
     * 读取文件
     * @param filePath
     * @return
     */
    public static byte[] readFile(String filePath) {
        try (
            FileInputStream fis = new FileInputStream(new File(filePath));) {
            return FileUtil.parseToByte(fis);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new byte[0];
    }
    
    /**
     * 写文件
     * @param savePath
     * @param fileData
     */
    public static void writeFile(String savePath, byte[] fileData) {
        try (
            FileOutputStream out = new FileOutputStream(new File(savePath));
            BufferedOutputStream bos = new BufferedOutputStream(out);) {
            bos.write(fileData);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }
    

    /**
     * 删除文件
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] subs = file.listFiles();
            for (File subf : subs) {
                deleteFile(subf);
            }
            file.delete();
        } else {
            file.delete();
        }
    }
}
