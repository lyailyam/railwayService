package kz.edu.nu.cs.se.services;

import com.google.gson.Gson;
import kz.edu.nu.cs.se.DBConnector;
import kz.edu.nu.cs.se.SessionFactoryListener;
import kz.edu.nu.cs.se.models.Employee;
import kz.edu.nu.cs.se.models.Schedule;
import kz.edu.nu.cs.se.models.entities.EmployeeEntity;
import kz.edu.nu.cs.se.models.entities.ScheduleEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Path("/employees")
public class EmployeeService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
        // SQL statement to retrieve employees with their schedules
        String sql = "SELECT employee.id, employee.email, employee.firstname, employee.surname, employee.salary, " +
                "employee.role_id, employee.station_id, date(employee.employed_date), time(schedule.end_time), " +
                "time(schedule.start_time), schedule.working_day FROM employee INNER JOIN schedule ON " +
                "employee.id=schedule.employee_id;";

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            List<Employee> employeeList = new CopyOnWriteArrayList<>();

            Employee employee;
            Map<Integer, List<String>> schedule;
            List<String> time;

            rs.next();

            while (!rs.isAfterLast()) {
                int id = rs.getInt("employee.id");
                employee = new Employee();
                schedule = new HashMap<>();

                employee.setId(id);
                employee.setEmail(rs.getString("employee.email"));
                employee.setFirstName(rs.getString("employee.firstname"));
                employee.setSurname(rs.getString("employee.surname"));
                employee.setSalary(rs.getDouble("employee.salary"));
                employee.setRoleId(rs.getInt("employee.role_id"));
                employee.setStationId(rs.getInt("employee.station_id"));
                employee.setEmployedDate(rs.getString("date(employee.employed_date)"));

                time = new ArrayList<>();
                time.add(rs.getString("time(schedule.start_time)"));
                time.add(rs.getString("time(schedule.end_time)"));
                schedule.put(rs.getInt("schedule.working_day"), time);

                while (rs.next() && rs.getInt("employee.id") == id) {
                    time = new ArrayList<>();
                    time.add(rs.getString("time(schedule.start_time)"));
                    time.add(rs.getString("time(schedule.end_time)"));
                    schedule.put(rs.getInt("schedule.working_day"), time);
                }
                employee.setSchedule(schedule);
                employeeList.add(employee);
            }

            return Response.ok(gson.toJson(employeeList)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }

    @Path("/{id: [0-9]+}")
    @GET
    public Response getEmployee(@PathParam("id") Integer id) {
        EmployeeEntity result;

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from EmployeeEntity Entity where id = :id");
            query.setParameter("id", id);
            result = (EmployeeEntity) query.uniqueResult();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(400).build();
        } finally {
            session.close();
        }

        return Response.ok().entity(result).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putEmployeeSchedule(List<ScheduleEntity> employeeSchedule) {
        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();

            for (ScheduleEntity scheduleEntity : employeeSchedule) {
                session.saveOrUpdate(scheduleEntity);
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(400).build();
        } finally {
            session.close();
        }

        return Response.ok().build();
    }

}
