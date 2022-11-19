package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;



@Controller // This means that this class is a Controller
@RequestMapping(path="/demo")
public class MainController {

  static int SEC = 60;
  static int MIN = 60 * SEC;
  @Autowired // This means to get the bean called userRepository
  private UserRepository userRepository;

  // 회원가입
  @PostMapping("/signup")
  public @ResponseBody RedirectView signUp(HttpServletResponse res, @RequestParam String name, @RequestParam String password) {
    LocalDate date = LocalDate.now();
    LocalTime sec = LocalTime.now();

    // 이미 존재하는 유저일 경우,
    Iterable<User> users = userRepository.findAll();
    for(User user:users){
      if(user.getName().equals(name)) return new RedirectView("../page/failView.html");
    }

    // create user instance
    User user = new User();
    user.setName(name);
    user.setReserveDate(sec.toString());
    user.setPassword(password);

    userRepository.save(user);

    String nowTime =  date.toString() + "|" + sec.toString();
    Cookie cookie = new Cookie("LOGIN_USER", name + "|"+ nowTime);
    cookie.setDomain("localhost");
    cookie.setPath("/");

    cookie.setMaxAge(10 * MIN);
    cookie.setSecure(true);
    res.addCookie(cookie);

    return new RedirectView("../page/userView.html");
  }


  @PostMapping("/add")
  public @ResponseBody RedirectView signIn(HttpServletResponse res, @RequestParam String name, @RequestParam String password) {
    LocalDate date = LocalDate.now();
    LocalTime sec = LocalTime.now();

    // 존재하는 유저일 경우,
    Iterable<User> users = userRepository.findAll();
    for(User user : users) {
      if (user.getName().equals(name)) {
        user.setReserveDate(sec.toString());

        String nowTime = date.toString() + "|" + sec.toString();
        Cookie cookie = new Cookie("LOGIN_USER", name + "|" + nowTime);
        cookie.setDomain("localhost");
        cookie.setPath("/");

        cookie.setMaxAge(10 * MIN);
        cookie.setSecure(true);
        res.addCookie(cookie);

        return new RedirectView("../page/userView.html");
      }
    }

    // 존재하지않는 아이디
    return new RedirectView("../page/failView.html");
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }
}