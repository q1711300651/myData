package com.ww.controller;

import com.ww.entity.User;
import com.ww.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/userlist.json")
    @ResponseBody
    public List<User> getUserList() {

        return userService.getAllUsers();
    }

    @RequestMapping(value = "/addUser/{userName}", method = RequestMethod.POST)

    @ResponseBody
    public void addUser(@PathVariable("userName") String userName) {
        userService.addUser(userName);
    }

    @RequestMapping(value = "/removeUser/{userName}", method = RequestMethod.DELETE)
    @ResponseBody
    public void removeUser(@PathVariable("userName") String userName) {
        userService.deleteUser(userName);
    }

    @RequestMapping(value = "/removeAllUsers", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void removeAllUsers() {
        userService.deleteAll();
    }

    @RequestMapping(value = "/test123")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
//        String t = request.getParameter("interceptor");
//        response.setHeader("a","aa");
        String ip = request.getHeader("x-forwarded-for");
        ServletInputStream inputStream = request.getInputStream();
        Enumeration<String> attributeNames = request.getAttributeNames();

        PrintWriter writer = response.getWriter();
//        writer.write("test.....");
        HashMap map = new HashMap<String,String>();
        map.put("msg1","msg11");
        map.put("msg2","msg22");
        Configuration configuration = new Configuration();
        configuration.setServletContextForTemplateLoading(request.getSession().getServletContext(),"WEB-INF/templates");

        Template template = configuration.getTemplate("test.ftl");

        template.process(map,writer);


    }

    @RequestMapping("/layout")
    public String getUserPartialPage() {
        return "users/layout";
    }

}
