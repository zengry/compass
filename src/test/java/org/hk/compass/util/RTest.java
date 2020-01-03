package org.hk.compass.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.hk.compass.modules.sys.service.ISysMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author zengry
 * @description
 * @since 2019/12/27
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RTest {

    @Autowired
    ISysMenuService sysMenuService;

    @Test
    public void test(){
        System.out.println(JSON.toJSONString(sysMenuService.listMenuByUserId(1L)));
    }

}