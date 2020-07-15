package com.hollycrm.mi.system.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hollycrm.mi.core.bean.Pager;
import com.hollycrm.mi.core.entity.SysUser;
import com.hollycrm.mi.core.kit.MiKit;
import com.hollycrm.mi.core.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping(value="UserRest")
public class UserRest {

    private static final Logger LOG = LoggerFactory.getLogger(UserRest.class);

    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/list.btl");
        return mv;
    }

    @RequestMapping("/addForm")
    public ModelAndView addForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/add.btl");
        return mv;
    }

    @RequestMapping("/editForm")
    public ModelAndView editForm(@RequestParam(name = "id") String id) {
        ModelAndView mv = new ModelAndView();
        SysUser user = userService.get(id);
        mv.addObject("user", user);
        mv.setViewName("/user/edit.btl");
        return mv;
    }

    /**
     * 分页查询
     * @param param
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public String page(@RequestParam Map<String, Object> param,
                       int page,
                       int limit){
        String result = null;
        try {
            Pager pageObj = userService.page(param, page, limit);
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(pageObj);
        } catch(Exception e){
            LOG.error("系统异常", e);
            result = MiKit.getDefaultPageJson("90000", "系统异常");
        }
        return result;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id){
        userService.deleteById(id);
        return MiKit.getDefaultPageJson("0", "删除成功!");
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/deleteAll")
    @ResponseBody
    public String deleteAll(String ids){
        userService.deleteAll(ids);
        return MiKit.getDefaultPageJson("0", "删除成功!");
    }

    /**
     * 新增
     * @param param
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam Map<String, Object> param){
        SysUser user = new SysUser();
        MiKit.copyProperties(user, param);
        user.setUser_code( MiKit.generateUUID());
        userService.insert(user);
        return MiKit.getDefaultJson(true, "新增成功!");
    }

    /**
     * 编辑
     * @param param
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam Map<String, Object> param,
                         @RequestParam(name = "id") String id){
        SysUser user = new SysUser();
        user.setUser_code(id);
        MiKit.copyProperties(user, param);
        userService.updateByExample(user);
        return MiKit.getDefaultJson(true, "编辑成功!");
    }

}
