package javafiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class EmpController {
    @Autowired
    EmpDao dao;//will inject dao from xml file





    @RequestMapping("/")
    public String viewemp(Model m) {
        List<Emp> list = dao.getEmployees();
        m.addAttribute("list", list);
        return "viewemp";
    }





}