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

    //Home page
    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("appointmentList", da.getAppointments());
        return "index";
    }

    //Adds users input into Appointment table
    @PostMapping("/appointmentForm")
    public String getNewAppointment(Model model, @ModelAttribute Appointment appointment){
        da.insertAppointment(appointment);
        model.addAttribute("appointment", new Appointment());
        return "redirect:/";
    }

    //Delete the row of whatever ID user clicks
    @GetMapping("/deleteAppointmentById/{id}")
    public String deleteAppointmentById(@PathVariable Long id){
        da.deleteId(id);
        return "redirect:/";
    }

    //Send the record user wants to edit to update template
    @GetMapping("/editAppointmentById/{id}")
    public String editAppointmentById(@PathVariable Long id, Model model){
        Appointment appointment = da.getAppointment(id).get(0);
        model.addAttribute("appointment", appointment);
        model.addAttribute("appointmentList", da.getAppointments());
        return "update";
    }

    //Take the new values the users entered and update the table with it
    @PostMapping("/updateForm")
    public String updateAppointment(Model model,@ModelAttribute Appointment appointment){
        da.updateAppointment(appointment);
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("appointmentList", da.getAppointments());
        return "redirect:/";
    }
}
