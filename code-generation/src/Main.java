import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

import java.io.IOException;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        GeneratorFacade g = new GeneratorFacade();

        String webAppName = "";// web app context
        String moduleName = "REFULE"; // 模块英文名(包名)
        String tableName = "REFULE"; // 数据库表名
        String clazzName = "REFULE"; // java实体bean类名
        String pageName = "REFULE"; // ftl/jsp/js名

        GeneratorProperties.setProperty("appName", webAppName);
        GeneratorProperties.setProperty("moduleName", moduleName);
        GeneratorProperties.setProperty("clazzName", clazzName);
        GeneratorProperties.setProperty("pageName", pageName);
        GeneratorProperties.setProperty("tableName", tableName);
        try {

            /**
             * 慎重使用g.deleteOutRootDir()
             * 会清空代码生成器输出目录,并且找不回来
             * 如果你的代码生成器输出目录是项目路径,则会把你的项目删除,如果项目有未提及的代码就找不回来了
             */
//            g.deleteOutRootDir(); 删除生成器的输出目录

//            g.deleteByTable(tableName, "kit-template");//根据代码模版删除对应文件
            g.generateByTable(tableName, "kit-template");//根据表名和代码模版生成代码文件
            Runtime.getRuntime().exec("cmd.exe /c start " + GeneratorProperties.getProperty("outRoot") + "/src/main/java/com/ctw");// 打开文件夹
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
