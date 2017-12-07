package service;

import dao.EmployeeDAO;
import entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        EmployeeDAO dao = new EmployeeDAO();

        String uri = request.getRequestURI();
        String s = uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf("."));


        if("add".equals(s)){
            request.getRequestDispatcher("/WEB-INF/add.jsp").forward(request,response);
        }else if("save".equals(s)){
            Employee emp = new Employee();
            emp.setId(request.getParameter("id"));
            emp.setName(request.getParameter("name"));
            emp.setAge(request.getParameter("age"));
            emp.setJob(request.getParameter("job"));
            emp.setSalary(request.getParameter("salary"));
            try {
                dao.save(emp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/list.do").forward(request,response);
        }else if("delete".equals(s)){
            String id = request.getParameter("id");
            try {
                dao.delete(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/list.do").forward(request,response);
        }else if("list".equals(s)){
            try {
                List<Employee> list = dao.list();
                request.setAttribute("empList",list);
                request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("load".equals(s)){
            request.getRequestDispatcher("/WEB-INF/modify.jsp").forward(request,response);
        }else if("modify".equals(s)){
            Employee e = new Employee();
            e.setId(request.getParameter("id"));
            e.setName(request.getParameter("name"));
            e.setAge(request.getParameter("age"));
            e.setJob(request.getParameter("job"));
            e.setSalary(request.getParameter("salary"));
            try {
                dao.modify(e);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            request.getRequestDispatcher("/WEB-INF/list.do").forward(request,response);
        }
    }
}
