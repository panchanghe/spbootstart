package com.ch.one.core.util;

import com.ch.one.core.support.MyGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/23 13:40
 * @Description:
 */
public class Generator {
	public static void main(String[] args) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		String filePath = ClassLoader.getSystemResource("config/generatorConfig.xml").getPath();
		System.out.println(filePath);

		File configFile = new File(filePath);
		Configuration config;
		try {

			ConfigurationParser cp = new ConfigurationParser(warnings);

			config = cp.parseConfiguration(configFile);


			DefaultShellCallback shellCallback = new DefaultShellCallback(overwrite);

			MyGenerator myBatisGenerator = new MyGenerator(config, shellCallback, warnings);

			ProgressCallback progressCallback = new VerboseProgressCallback();

			myBatisGenerator.generate(progressCallback);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}


	}
}
