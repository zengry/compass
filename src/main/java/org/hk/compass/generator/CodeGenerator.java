package org.hk.compass.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author zengry
 * @description  代码生成器
 * @since 2019/12/27
 */
public class CodeGenerator {
    public static void main(String[] args) {

        /**
         *   代码生成器
         */
       AutoGenerator mpg = new AutoGenerator();


        /**
         *   全局配置
         */
        GlobalConfig gc = new GlobalConfig();
        // 开发人员
        gc.setAuthor("zengry");
        // 生成文件的输出目录
        gc.setOutputDir("/Users/danny/HomeKingX/demo/compass/src/main/java");
        // 否打开输出目录弹窗
        gc.setOpen(false);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        // 文件覆盖
        gc.setFileOverride(true);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);

        /**
         *   数据源配置
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://118.31.68.230:3306/myshiro?useSSL=true&verifyServerCertificate=false&allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("hkx123210");
        mpg.setDataSource(dsc);


        /**
         *   包配置
         */
        PackageConfig pc = new PackageConfig();
        pc.setParent("org.hk.compass.modules.sys");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setEntity("entity");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);


        /**
         *   策略配置
         */
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude("sys_menu","sys_user","sys_user_token","sys_captcha","sys_role", "sys_user_role", "sys_role_menu", "sys_config", "sys_log", "sys_oss");
        mpg.setStrategy(strategy);


        // 自定义配置 (必需保留, 否则NPE)
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        mpg.setCfg(cfg);

        mpg.execute();
    }


}
