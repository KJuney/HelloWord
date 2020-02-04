package com.helloword.test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class TestMonthJob implements SimpleJob {

	@Override
	public void execute(ShardingContext shardingContext) {
		// TODO Auto-generated method stub

		try {
			
			String monthTime = DateUtil.getLastMonthTime();//获取上个月 eg：2019-12
			String dayTime = DateUtil.getCurrentTime();
			String startMonth = DateUtil.getLastMonthFirstDay(dayTime);
			String endMonth = DateUtil.getLastMonthLastDay(dayTime);
			/**
			 * 1.将数据变成json
			 * 2.写入文件
			 */
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
			byte[] fileData = str.getBytes("utf-8");
					
			String pathUrl = "E:/工作/测试上传文档/";//测试环境服务器路径
//			String pathUrl = "/XXXXXXX/";//正式环境服务器路径
			String fileName = "KF_"+monthTime;//文件夹名称
			File monthFile = new File(pathUrl+fileName);
			if(!monthFile.exists() || !monthFile.isDirectory()){
				monthFile.mkdir();
			}
			
			String saveDataFile = "KF_"+monthTime+"_kpi_000.txt";//中间业务数据时间
			String saveDataPathUrl = pathUrl+fileName + "/" + saveDataFile;
			FileUtil.writeFile(saveDataPathUrl, fileData);
			
			//传入指定的sftp服务器
		
			String toFilePath = "/root/bomc_notice/toptea_file/"+fileName;
			String host = "101.200.41.9";
			int port = 22;
			String username = "root";
			String password = "Njcrm%321";
			MiniFtp.sftpUpload(host, port, username, password,toFilePath, saveDataPathUrl);//上传数据文件
		} catch (Exception e) {
            System.out.println("异常");
        }

	}

}
