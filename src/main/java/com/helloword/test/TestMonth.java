package com.helloword.test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class TestMonth {
	public static void main(String[] args) {
try {
			System.out.println("��ʼ");
			String monthTime = DateUtil.getLastMonthTime();//��ȡ�ϸ��� eg��2019-12
			System.out.println(monthTime);

			String dayTime = DateUtil.getCurrentTime();
			String startMonth = DateUtil.getLastMonthFirstDay(dayTime);
			String endMonth = DateUtil.getLastMonthLastDay(dayTime);
			System.out.println(startMonth);
			System.out.println(endMonth);
			/**
			 * 1.�����ݱ��json
			 * 2.д���ļ�
			 */
			System.out.println("----------1----------------");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("busstime", monthTime);
			jsonObject.put("type", "MONTH");

			Map<String, Integer> map = new LinkedHashMap<String, Integer>();
			map.put("ID-YWGL-KHTSCL-01-01",1123);
			map.put("ID-YWGL-KHTSCL-02-01",11138-128);
			map.put("ID-YWGL-KHTSCL-02-02",11138);
			map.put("ID-YWGL-KHTSCL-03-01",5321);
			map.put("ID-YWGL-KHTSCL-03-02",11138);
			map.put("ID-YWGL-KHTSCL-04-01",5321+289);
			map.put("ID-YWGL-KHTSCL-04-02",11138);
			jsonObject.put("data", map);
			
			jsonObject.put("source", "BOMC");

			String str = jsonObject.toJSONString();
			System.out.println(str);
			byte[] fileData = str.getBytes("utf-8");
					
			String pathUrl = "E:/����/�����ϴ��ĵ�/";//���Ի���������·��
//			String pathUrl = "/XXXXXXX/";//��ʽ����������·��
			String fileName = "KF_"+monthTime;//�ļ�������
			File monthFile = new File(pathUrl+fileName);
			if(!monthFile.exists() || !monthFile.isDirectory()){
				monthFile.mkdir();
			}
			System.out.println("����");
			String saveDataFile = "KF_"+monthTime+"_kpi_000.txt";//�м�ҵ������ʱ��
			String saveDataPathUrl = pathUrl+fileName + "/" + saveDataFile;
			FileUtil.writeFile(saveDataPathUrl, fileData);
			
			//����ָ����sftp������
		
			String toFilePath = "/root/bomc_notice/toptea_file/"+fileName;
			String host = "101.200.41.9";
			int port = 22;
			String username = "root";
			String password = "Njcrm%321";
			MiniFtp.sftpUpload(host, port, username, password,toFilePath, saveDataPathUrl);//�ϴ������ļ�
		} catch (Exception e) {
            System.out.println("�쳣");
        }
	}
}
