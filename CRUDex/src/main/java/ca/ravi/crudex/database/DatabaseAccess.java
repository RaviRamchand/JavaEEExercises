package ca.ravi.crudex.database;

import ca.ravi.crudex.bean.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    //Returns Appointment Table to the AppointmentController as a List
    public List<Appointment> getAppointments(){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "SELECT * FROM Appointment";
        return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(Appointment.class));
    }

    //Inserts appointment information from user into table
    public void insertAppointment(Appointment appointment){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "INSERT INTO Appointment(FirstName, Email, AppointmentDate, AppointmentTime)" +
                "VALUES(:FirstName, :Email, :AppointmentDate, :AppointmentTime)";
        namedParameter.addValue("FirstName", appointment.getFirstName());
        namedParameter.addValue("Email", appointment.getEmail());
        namedParameter.addValue("AppointmentDate", appointment.getAppointmentDate());
        namedParameter.addValue("AppointmentTime", appointment.getAppointmentTime());

        jdbc.update(query, namedParameter);
    }

    //Deletes a specific appointment using the id
    public void deleteId(long id){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "DELETE FROM Appointment WHERE id = :id";
        namedParameter.addValue("id", id);

        jdbc.update(query, namedParameter);
    }

    //Get the record that has the wanted id and sends it back to the controller
    public List<Appointment> getAppointment(long id){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "SELECT * FROM Appointment WHERE id=:id";
        namedParameter.addValue("id", id);

        return jdbc.query(query, namedParameter, new BeanPropertyRowMapper<>(Appointment.class));
    }

    //Updates the record with whatever the user replaces original content with
    public void updateAppointment(Appointment appointment){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "UPDATE Appointment SET FirstName=:FirstName, Email=:Email," +
                "AppointmentDate=:AppointmentDate, AppointmentTime=:AppointmentTime WHERE id=:id";
        namedParameter.addValue("FirstName", appointment.getFirstName());
        namedParameter.addValue("id", appointment.getId());
        namedParameter.addValue("Email", appointment.getEmail());
        namedParameter.addValue("AppointmentDate", appointment.getAppointmentDate());
        namedParameter.addValue("AppointmentTime", appointment.getAppointmentTime());

        int rowsAffected = jdbc.update(query, namedParameter);

        if(rowsAffected>0){
            System.out.println("Table updated");
        }
    }
}
