package ca.ravi.requestparamex.controllers;

import ca.ravi.requestparamex.beans.Appointment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @PostMapping("/appointmentForm")
    public String createAppointment(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate appointmentDate,
                                    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME)LocalTime appointmentTime){
        Appointment appointment = new Appointment();
        appointment.setFirstName(firstName);
        appointment.setLastName(lastName);
        appointment.setEmail(email);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);

        System.out.println("Appointment created for " +appointment.getFirstName() +" " +appointment.getLastName() +
                " on " +appointment.getAppointmentDate() +" at: " +appointment.getAppointmentTime());
        return "index";
    }
}
