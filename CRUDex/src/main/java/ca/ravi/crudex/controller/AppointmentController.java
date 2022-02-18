package ca.ravi.crudex.controller;

import ca.ravi.crudex.bean.Appointment;
import ca.ravi.crudex.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppointmentController {
    @Autowired
    private DatabaseAccess da;

    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("appointmentList", da.getAppointments());
        return "index";
    }

    @PostMapping("/appointmentForm")
    public String getNewAppointment(Model model, @ModelAttribute Appointment appointment){
        da.insertAppointment(appointment);
        model.addAttribute("appointment", new Appointment());
        return "redirect:/";
    }

    @GetMapping("/deleteAppointmentById/{id}")
    public String deleteAppointmentById(@PathVariable Long id){
        da.deleteId(id);
        return "redirect:/";
    }
}
