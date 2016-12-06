package employee.controller;

import employee.model.Employee;
import employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by employee on 12/6/16.
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> getAll() {
        return service.getAll();
    }

}
