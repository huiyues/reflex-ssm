package com.heiye.reflex.service;

import com.heiye.reflex.constants.MapCode;
import com.heiye.reflex.model.ReflexModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: heiye
 * @Date: 2020/5/2
 * @Time: 12:16
 * Description: No Description
 */
@Service
public class ReflexService {

    @Autowired
    private MapCode mapCode;

    public void login() {
        System.out.println("login 方法执行.....");
    }

    public void logout(int id) {
        System.out.println("logout 方法执行....." + id);
    }

    public void check() {
        System.out.println("check 方法执行.....");
    }

    /**
     * 反射调用
     *
     * @param code
     * @return
     */
    public String reflex(int code, int nextCode) {
        // 获取所有方法
        List<ReflexModel> reflexModelList = mapCode.get(code);
        String name = "";
        for (ReflexModel reflexModel : reflexModelList) {
            if (nextCode == reflexModel.getCode()) {
                name = reflexModel.getMethodName();
            }
        }
        System.out.println("调用的方法名是：" + name);

        // 反射执行方法
        ReflexService reflexService = new ReflexService();
        Class<? extends ReflexService> aClass = reflexService.getClass();
        for (Method method : aClass.getMethods()) {
            if (name != null && name.equals(method.getName())) {
                // 测试功能，开发中去掉即可
                if (method.getParameters().length > 0) {
                    name = "(该方法需要参数，未开发！)";
                    return name;
                }
                try {
                    // 可传入参数
                    method.invoke(reflexService);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return name;
    }
}
