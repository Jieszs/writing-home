package com.jiesz.writinghome.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.util.StringUtils;

import java.util.*;

public class CodeGenerator {
    //表名
    private static final String TABLE_NAME = "user_info";
    //实体名
    private static final String ENTITY_NAME = "User";
    //是否分页
    private static final Boolean ENABLE_PAGE = true;
    //是否树结构
    private static final Boolean ENABLE_TREE = false;
    private static final List<String> SAVE_AND_UPDATE_IGNORE_FIELDS = Arrays.asList("insertTime", "updateTime", "state");
    //查询条件
    private static final List<String> SELECT_IGNORE_FIELDS = Arrays.asList("insertTime", "updateTime", "state");
    private static final IdType ID_TYPE = IdType.AUTO;
    //逻辑删除字段
    private static final String LOGIC_DELETE_FIELD_NAME = "state";
    private static final String EXIST_FIELD_NAME = null;
    private static final String URL = "jdbc:mysql://localhost:3306/writing_home?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatementshedStatements=true&allowMultiQueries=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    //根包名
    private static final String PARENT_PACKAGE_NAME = "com.jiesz.writinghome";
    private static final String IGNORE_TABLE_PREFIX = null;
    private static final String VERSION_FIELD_NAME = null;
    private static final String PROJECT_PATH = System.getProperty("user.dir");


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        GlobalConfig gc = getGlobalConfig();
        mpg.setGlobalConfig(gc);
        //数据源配置
        DataSourceConfig dsc = getDataSourceConfig();
        mpg.setDataSource(dsc);
        //包名配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);
        //模板配置
        TemplateConfig templateConfig = getTemplateConfig();
        mpg.setTemplate(templateConfig);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //策略配置
        StrategyConfig strategy = getStrategyConfig();
        mpg.setStrategy(strategy);
        //自定义注入信息配置
        Map<String, Object> map = new HashMap<>();
        map.put("parentPackageName", PARENT_PACKAGE_NAME);
        map.put("urlName", ENTITY_NAME.toLowerCase() + "s");
        map.put("enablePage", ENABLE_PAGE);
        map.put("enableTree", ENABLE_TREE);
        map.put("saveAndUpdateIgnoreFields", SAVE_AND_UPDATE_IGNORE_FIELDS);
        map.put("selectIgnoreFields", SELECT_IGNORE_FIELDS);
        map.put("existFieldName", EXIST_FIELD_NAME);
        InjectionConfig cfg = getInjectionConfig(map);
        // 自定义输出文件配置
        List<FileOutConfig> focList = new ArrayList<>();
        setFileOutConfig(focList);
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        //生成
        mpg.execute();
    }

    private static void setFileOutConfig(List<FileOutConfig> focList) {
        String mappterTemplatePath = "/templates/mapper.xml.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(mappterTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return PROJECT_PATH + "/src/main/resources/mapper/" + ENTITY_NAME + "Mapper" + StringPool.DOT_XML;
            }
        });
    }

    /**
     * 自定义配置
     *
     * @return
     */
    private static InjectionConfig getInjectionConfig(Map<String, Object> map) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                this.setMap(map);
            }
        };
        return cfg;
    }

    /**
     * 配置模板
     *
     * @return
     */
    private static TemplateConfig getTemplateConfig() {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setController("templates/controller.java");
        templateConfig.setXml(null);
        return templateConfig;
    }

    /**
     * 策略配置
     *
     * @return
     */
    private static StrategyConfig getStrategyConfig() {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setChainModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 忽视前缀
        if (!StringUtils.isEmpty(IGNORE_TABLE_PREFIX)) {
            strategy.setTablePrefix(IGNORE_TABLE_PREFIX);
        }
        // 逻辑删除
        if (!StringUtils.isEmpty(LOGIC_DELETE_FIELD_NAME)) {
            strategy.setLogicDeleteFieldName(LOGIC_DELETE_FIELD_NAME);
        }
        // 乐观锁
        if (!StringUtils.isEmpty(VERSION_FIELD_NAME)) {
            strategy.setVersionFieldName(VERSION_FIELD_NAME);
        }
        strategy.setInclude(TABLE_NAME);
        strategy.setControllerMappingHyphenStyle(true);
        return strategy;
    }

    /**
     * 包配置
     *
     * @return
     */
    private static PackageConfig getPackageConfig() {
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT_PACKAGE_NAME);//主包名
        return pc;
    }

    /**
     * 获取全局配置
     *
     * @return
     */
    private static GlobalConfig getGlobalConfig() {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(PROJECT_PATH + "/src/main/java");
        gc.setAuthor("Jiesz");
        gc.setOpen(false);
        // 是否覆盖目录
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // 是否Swagger2注解
        gc.setSwagger2(true);
        // 是否开启二级缓存
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        //主键策略
        gc.setIdType(ID_TYPE);
        gc.setEntityName(ENTITY_NAME);
        gc.setControllerName(ENTITY_NAME + "Controller");
        gc.setMapperName(ENTITY_NAME + "Mapper");
        gc.setServiceName("I" + ENTITY_NAME + "Service");
        gc.setServiceImplName(ENTITY_NAME + "ServiceImpl");
        return gc;
    }

    /**
     * 数据源配置
     *
     * @return
     */
    private static DataSourceConfig getDataSourceConfig() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        return dsc;
    }
}