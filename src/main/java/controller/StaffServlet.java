package controller;

import model.Department;
import model.Staff;
import service.DepartmentService;
import service.StaffService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StaffServlet", value = "/staffs")
public class StaffServlet extends HttpServlet {
    StaffService staffService = new StaffService();
    DepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        if (act == null) {
            act = "";
        }
        switch (act) {
            case "add":
                showFormAdd(request,response);
                break;
            case "edit":
                showFormEdit(request,response);
                break;
            case "delete":
                deleteStaff(request, response);
                break;
            case "search":
                showSearch(request, response);
                break;
            default:
                showStaffs(request, response);
                break;
        }
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
        String nameSearch = request.getParameter("nameSearch");
        List<Staff> staffs = staffService.findByName(nameSearch);
        request.setAttribute("staffs", staffs);
        requestDispatcher.forward(request, response);
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        staffService.deleteStaff(id);
        response.sendRedirect("/staffs");
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        List<Department> departments = departmentService.findAll();
        request.setAttribute("departments", departments);
        Staff staff = staffService.findById(id);
        request.setAttribute("staff", staff);
        requestDispatcher.forward(request, response);
    }

    private void showFormAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        List<Department> departments = departmentService.findAll();
        request.setAttribute("departments", departments);
        requestDispatcher.forward(request, response);
    }

    private void showStaffs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
        List<Staff> staffs = staffService.findAll();
        request.setAttribute("staffs", staffs);
        List<Department> departments = new ArrayList<>();
        for (Staff staff: staffs) {
            departments.add(departmentService.findById(staff.getId()));
        }
        request.setAttribute("departments", departments);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        if (act == null) {
            act = "";
        }
        switch (act) {
            case "add":
                addStaff(request,response);
                break;
            case "edit":
                editStaff(request,response);
                break;
        }
    }

    private void editStaff(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idS = Integer.parseInt(request.getParameter("id")); // lay id trên thanh url
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int phone = Integer.parseInt(request.getParameter("phone"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        int idD = Integer.parseInt(request.getParameter("idD"));
        staffService.updateStaff(new Staff(idS, name, email, address, phone, salary, idD ));
        response.sendRedirect("/staffs");
    }

    private void addStaff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int phone = Integer.parseInt(request.getParameter("phone"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        int idD = Integer.parseInt(request.getParameter("idD"));
        staffService.saveStaff(new Staff(1, name, email, address, phone, salary, idD ));
        response.sendRedirect("/staffs");
    }

}