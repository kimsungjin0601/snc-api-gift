package com.snc.gift.common.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneratorDomain {
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("C:\\project\\snc\\snc-api-gift\\src\\main\\resources\\generator-config.xml");
        if (!configFile.exists()) {
            System.out.println("❌ generatorConfig.xml not found: " + configFile.getAbsolutePath());
            return;
        }

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        System.out.println("✅ MyBatis Generator 완료!");
        warnings.forEach(w -> System.out.println("⚠️ " + w));
    }
}
