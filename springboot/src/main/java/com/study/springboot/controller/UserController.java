package com.study.springboot.controller;

import com.study.springboot.entity.Users;
import com.study.springboot.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Cdu
 * @discription:
 * @create 2020-07-04 23:06
 */
@Controller
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping("/user/login")
    public String login(String username, String password, ModelMap modelMap, HttpSession session) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)
                && "admin".equals(username) && "admin".equals(password)) {

//            登录成功保存session
            session.setAttribute("username", username);
            return "redirect:/user/list";
        } else {
            modelMap.addAttribute("login_error", "用户名密码错误");
            return "login";
        }
    }

    @RequestMapping("/user/list")
    public String findAll(ModelMap modelMap) {
        List<Users> users = usersRepository.findAll();
        modelMap.addAttribute("list", users);
        return "list";
    }

    @RequestMapping("/user/delete")
    public String deleteById(int id) {
        usersRepository.deleteUsersById(id);
        return "list";
    }
}