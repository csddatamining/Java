package com.study.springboot.controller;

import com.study.springboot.entity.Users;
import com.study.springboot.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String login(Users users, ModelMap modelMap, HttpSession session) {
        if (!StringUtils.isEmpty(users.getUsername()) && !StringUtils.isEmpty(users.getPassword())
                && "admin".equals(users.getUsername()) && "admin".equals(users.getPassword())) {

//            登录成功保存session
            session.setAttribute("userInfo", users);
            return "redirect:/user/list";
        } else {
            modelMap.addAttribute("login_error", "用户名密码错误");
            return "login";
        }
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public Users findById(@PathVariable int id){
        return usersRepository.findById(id).get();
    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public String saveUsers(@ModelAttribute Users users){
        usersRepository.save(users);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "update.do", method = RequestMethod.PUT)
    public String updateUsers(@ModelAttribute Users users){
        usersRepository.saveAndFlush(users);
        return "redirect:/user/list";
    }

    @RequestMapping("/user/list")
    public String findAll(ModelMap modelMap) {
        List<Users> users = usersRepository.findAll();
        modelMap.addAttribute("list", users);
        return "list";
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public String deleteById(@PathVariable int id) {
        usersRepository.deleteById(id);
        return "list";
    }
}