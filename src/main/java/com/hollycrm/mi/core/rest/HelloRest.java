package com.hollycrm.mi.core.rest;

import com.hollycrm.mi.core.bean.Pager;
import com.hollycrm.mi.core.entity.SysUser;
import com.hollycrm.mi.core.kit.CacheKit;
import com.hollycrm.mi.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloRest {

    @Autowired
    UserService userService;

    @RequestMapping("/home")
    public String home() {
        CacheKit.put("hello", "123");

        SysUser user = userService.get("1");
        System.out.println(user);

        SysUser user2 = new SysUser();
        user2.setUser_code("0000");
        user2.setUser_name("test");
        user2.setEnabled(1);
        userService.insert(user2);

        Map<String, Object> param = new HashMap<>();
        System.out.println(userService.selectCount(param));

        userService.update(user2);
        userService.updateByExample(user2);

        userService.deleteById("0000");

        Pager pager = userService.page(param, 1, 10);
        System.out.println(pager);
        return "Hello Spring Boot!";
    }
}
