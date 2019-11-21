package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.Logged;
import kz.edu.nu.cs.se.SessionFactoryListener;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/schedule-map")
public class ScheduleMapService {
    @GET
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSchedule(@QueryParam("date") String date) {
        List<Object[]> results = null;
        List data = new ArrayList<HashMap>();

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            // TODO: Delete query
            Query query = session.createQuery("select li.routeId, li.date, rl.departScheduledTime, " +
                    "rl.arrivalScheduledTime, depS.city, depS.name, arrS.city, arrS.name, li.trainId " +
                    "from LegInstanceEntity li " +
                    "inner join RouteLegEntity rl on li.routeId = rl.routeId and li.legNum = rl.legNum " +
                    "inner join StationEntity depS on depS.id = rl.departStationId " +
                    "inner join StationEntity arrS on arrS.id = rl.arrivalStationId where " +
                    "(:date is null or li.date = :date)");

            Query query1 = session.createNativeQuery("SELECT route_id, first_stat_date, " +
                    "first_stat_dep_sched_time, last_stat_arr_sched_time, " +
                    "first_station_city, first_station_name, last_station_city, last_station_name, train_id " +
                    "FROM route_legs_stations where " +
                    "(:date is null or first_stat_date = :date)");

            query1.setParameter("date", date);
            query.setParameter("date", date);

            results = query1.getResultList();

            if(!results.isEmpty()) {
                for(Object[] result : results) {
                    HashMap<String, Object> resultMap = new HashMap();
                    resultMap.put("routeId", result[0]);
                    resultMap.put("date", result[1].toString()); // sql.Date is deprecated in JDBC
                    resultMap.put("departScheduledTime", result[2].toString()); // sql.Time is deprecated
                    resultMap.put("arrivalScheduledTime", result[3].toString()); // sql.Time is deprecated
                    resultMap.put("departCity", result[4]);
                    resultMap.put("departStationName", result[5]);
                    resultMap.put("arrivalCity", result[6]);
                    resultMap.put("arrivalStationName", result[7]);
                    resultMap.put("trainId", result[8]);
                    data.add(resultMap);
                }
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(400).build();
        } finally {
            session.close();
        }

        return Response.ok().entity(data).build();
    }

    @Path("/stations")
    @GET
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStations(@QueryParam("city") String city,
                                @QueryParam("id") Integer id) {
        List<Object[]> results = null;
        List data = new ArrayList<HashMap>();

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("select name, city, latitude, longitude from StationEntity s where " +
                    "(:city is null or s.city = :city) and " +
                    "(:id is null or s.id = :id)");

            query.setParameter("city", city);
            query.setParameter("id", id);

            results = query.getResultList();

            if(!results.isEmpty()) {
                for(Object[] result : results) {
                    HashMap resultMap = new HashMap();
                    resultMap.put("id", result[0]);
                    resultMap.put("title", result[1]);
                    resultMap.put("latitude", result[2]);
                    resultMap.put("longitude", result[3]);
                    data.add(resultMap);
                }
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(400).build();
        } finally {
            session.close();
        }

        return Response.ok().entity(data).build();
    }
}
