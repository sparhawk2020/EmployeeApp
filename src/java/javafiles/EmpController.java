package javafiles;

import lombok.AllArgsConstructor;
import org.jvnet.staxex.BinaryText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javafiles.Emp.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@SessionAttributes({"emp1", "oldid", "id", "name", "salary", "designation"})
@RequestMapping
@Controller
@AllArgsConstructor
public class EmpController {
    @Autowired
    EmpDao dao;//will inject dao from xml file




    @GetMapping("/empedit1")
    public String edit(ModelMap model,@RequestParam(defaultValue = "") String id, HttpSession session){

      Integer id1 = Integer.parseInt(id);

        Emp ee = dao.getEmpById(id1);

        // Put the values to inputs
        model.put("oldid", ee.getId());

       // session.setAttribute("oldid", ee.getId());
       model.put("name", ee.getName());
        model.put("salary", ee.getSalary());
        model.put("designation", ee.getDesignation());



        return "empedit1";
    }





    @PostMapping(path= "/empedit1")
    public String editemployee(ModelMap model, @RequestParam Integer id, @RequestParam String name,
                               @RequestParam float salary, @RequestParam String designation, HttpSession session) {

        Emp emp = new Emp();



        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);
        emp.setDesignation(designation);

        Object x= session.getAttribute("oldid");

        Integer sid = (Integer) x;


        //  filteredCustomers = model.get("customers");
        dao.update(emp, sid);



        return "redirect:/viewemp1";
    }








    @GetMapping("/empform1")
    public String formEmp(Model model){





        model.addAttribute("emp1", new Emp());




        return "empform1";
    }





    @PostMapping(path= "/empform1")
    public String addCustomer(ModelMap model, @RequestParam Integer id, @RequestParam String name,
                              @RequestParam float salary, @RequestParam String designation) {

        Emp emp = new Emp();

        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);
        emp.setDesignation(designation);

        model.addAttribute("emp", emp);
       System.out.println("Hey");


      //  filteredCustomers = model.get("customers");
       dao.save(emp);



        return "redirect:/viewemp1";
    }




    //@GetMapping(path="/")
   // @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping(path="/")
    public String viewemp(ModelMap m) {
        List<Emp> list = dao.getEmployees();
        m.addAttribute("list", list);
        return "viewemp1";
    }



    @GetMapping(path="/viewemp1")
    public String viewemp1(ModelMap m) {
        List<Emp> list = dao.getEmployees();
        m.addAttribute("list", list);
        return "viewemp1";
    }




}