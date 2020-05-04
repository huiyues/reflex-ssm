package com.heiye.reflex.constants;

import com.alibaba.fastjson.JSON;
import com.heiye.reflex.model.ReflexModel;
import com.heiye.reflex.model.ReflexParent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: heiye
 * @Date: 2020/5/3
 * @Time: 10:53
 * Description: No Description
 */
@Component
public class MapCode implements InitializingBean {

    private Map<Integer, List<ReflexModel>> map;

    /**
     * 初始化
     */
    public MapCode() {
        map = new HashMap<>();
    }

    /**
     * 获取值
     *
     * @param code
     * @return
     */
    public List<ReflexModel> get(int code) {
        return map.get(code);
    }

    /**
     * 添加数据
     *
     * @param code
     * @param nextCode
     */
    private void put(int code, List nextCode) {
        map.put(code, nextCode);
    }

    /** @Scheduled(cron = "0 0 0 * * ?") */
    private void taskPut() {
        System.out.println("开始加载配置文件......");
        // 加载配置文件
        ClassPathResource classPathResource = new ClassPathResource("json/mapCode.json");
        // 读取配置文件
        String jsonStr = "";
        try {
            File file = classPathResource.getFile();
            FileInputStream fileInputStream = new FileInputStream(file);

            Reader reader = new InputStreamReader(fileInputStream, "utf-8");
            int ch = 0;
            // 拼接字符串
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            jsonStr = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 解析json字符串
        Map map = JSON.parseObject(jsonStr, Map.class);
        Object properties = map.get("properties");
        String jsonString = JSON.toJSONString(properties);
        List<ReflexParent> reflexParentList = JSON.parseArray(jsonString, ReflexParent.class);

        System.out.println("加载后的解析结果：" + reflexParentList.toString());

        // 将数据存入到map中
        for (ReflexParent reflexParent : reflexParentList) {
            put(reflexParent.getCode(), reflexParent.getNextCode());
        }
    }

    /**
     * 项目启动后调用方法
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        taskPut();
    }
}
