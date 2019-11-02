package kz.edu.nu.cs.se.services;

import com.google.gson.Gson;
import kz.edu.nu.cs.se.ConfiguredSessionFactory;
import kz.edu.nu.cs.se.DBConnector;
import kz.edu.nu.cs.se.models.Schedule;
import kz.edu.nu.cs.se.models.TicketInfo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Path("/schedule")
public class ScheduleService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets(@QueryParam("from") String departCity,
                               @QueryParam("to") String arrivalCity,
                               @QueryParam("date") String departDate) {
        String sql;
        sql = "SELECT route_id, first_stat_leg_num, last_stat_leg_num, first_station_city, first_station_name, last_station_city, last_station_name, date(first_stat_date)," +
                "time(first_stat_dep_sched_time), date(last_stat_date), time(last_stat_arr_sched_time)" +
                "FROM route_legs_stations WHERE first_station_city = '" + departCity + "'" +
                "AND last_station_city = '" + arrivalCity + "'" +
                "AND first_stat_date = '" + departDate + "'";
        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

            List<Schedule> scheduleList = new CopyOnWriteArrayList<>();

            while (rs.next()) {
                Schedule s = new Schedule();

                s.setRouteId(rs.getInt("route_id"));
                s.setDepStationCity(rs.getString("first_station_city"));
                s.setDepStationName(rs.getString("first_station_name"));
                s.setArrStationCity(rs.getString("last_station_city"));
                s.setArrStationName(rs.getString("last_station_name"));
                s.setDepSchedTime(rs.getString("time(first_stat_dep_sched_time)"));
                s.setArrSchedTime(rs.getString("time(last_stat_arr_sched_time)"));
                s.setDepDate(rs.getString("date(first_stat_date)"));
                s.setArrDate(rs.getString("date(last_stat_date)"));
                s.setFirstStatLegNum(rs.getInt("first_stat_leg_num"));
                s.setLastStatLegNum(rs.getInt("last_stat_leg_num"));

                scheduleList.add(s);
            }
            for(Schedule s: scheduleList) {
                System.out.println(s);
            }
            return Response.ok(gson.toJson(scheduleList)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }
}