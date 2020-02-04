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
 * @version 2016��9��18��
 */
public class FileUtil {
    protected static Log log = LogFactory.getLog(FileUtil.class.getName());

    /**
     * ���ļ���ת��byte
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
     * ��ȡ�ļ�
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
     * д�ļ�
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
     * ɾ���ļ�
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
