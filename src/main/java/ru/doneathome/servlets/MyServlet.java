package ru.doneathome.servlets;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServlet")
public class MyServlet {

    public MyServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {

        String str_a = request.getParameter("a");
        String str_b = request.getParameter("b");
        String str_op = request.getParameter("op");


    }
}
