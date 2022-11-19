package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserRepository;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@WebServlet("/demo/hi")
public class UserController extends HttpServlet {
    private UserRepository userRepository;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("hi there");
    }
}

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//
//        if(response == null) return;
//
//        Date date = new Date();
//
//        User user = new User();
//        user.setName(name);
//        user.setReserveDate(date.toString());
//        user.setPassword(password);
//        userRepository.save(user);
//
//        try {
//            request.setAttribute("user", user);
//            request.getRequestDispatcher("jsp/request.jsp").forward(request, response);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }