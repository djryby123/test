package dao;

import entity.Employee;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Employee emp = new Employee();

    public EmployeeDAO() {

    }

    public EmployeeDAO(Employee emp) {
        this.emp = emp;
    }

    public void save(Employee emp) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "insert into employee_list values (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, emp.getId());
        ps.setString(2, emp.getName());
        ps.setString(3, emp.getAge());
        ps.setString(4, emp.getJob());
        ps.setString(5, emp.getSalary());
        ps.executeUpdate();
        ps.close();
        DBUtil.close();
    }

    public void delete(String id) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "delete from employee_list where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);
        ps.executeUpdate();
        ps.close();
        DBUtil.close();
    }

    public List<Employee> list() throws SQLException {

        Connection conn = DBUtil.getConn();
        String sql = "select * from employee_list";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Employee> empList = new ArrayList<Employee>();
        while (rs.next()) {
            Employee emp = new Employee();
            emp.setId(rs.getString(1));
            emp.setName(rs.getString(2));
            emp.setAge(rs.getString(3));
            emp.setJob(rs.getString(4));
            emp.setSalary(rs.getString(5));
            empList.add(emp);
        }
        rs.close();
        ps.close();
        DBUtil.close();
        return empList;
    }

    public void modify(Employee emp) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "update employee_list set 姓名=?,年龄=?,工作=?,工资=? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,emp.getName());
        ps.setString(2,emp.getAge());
        ps.setString(3,emp.getJob());
        ps.setString(4,emp.getSalary());
        ps.setString(5,emp.getId());
        ps.executeUpdate();
        ps.close();
        DBUtil.close();
    }
}
