package kz.edu.nu.cs.se.logging;

import kz.edu.nu.cs.se.SessionFactoryListener;
import kz.edu.nu.cs.se.models.entities.LogApiEntity;
import kz.edu.nu.cs.se.models.entities.LogUserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.JSONObject;

import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Logger {
    public static enum LoggerLevel {
        OFF,
        ON
    }

    private static Logger instance = null;
    private LoggerLevel level = LoggerLevel.ON;

    private Logger(){}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public void setLevel(LoggerLevel level) {
        this.level = level;
    }

    public LoggerLevel getLevel() {
        return level;
    }

    public void logApi(ContainerRequestContext requestContext, HttpSession webSession){
        if (level != LoggerLevel.OFF) {

            // Retrieving relevant data
            JSONObject obj =  (JSONObject) webSession.getAttribute("appMetadata");
            String role = null;

            if(obj != null) {
                role = (String) obj.getJSONObject("app_metadata").getJSONObject("authorization").getJSONArray("roles").get(0);
            }

            Integer userId = (Integer) webSession.getAttribute("userId");
            Timestamp timestamp = new Timestamp(new Date().getTime());
            String method = requestContext.getMethod();
            String requestUri  = requestContext.getUriInfo().getRequestUri().toASCIIString();

            // Loading to db
            LogApiEntity entity = new LogApiEntity();
            entity.setMethod(method);
            entity.setRole(role);
            entity.setTimestamp(timestamp);
            entity.setUri(requestUri);
            entity.setUserId(userId);

            Session dbSession = SessionFactoryListener.getSession();

            try {
                dbSession.beginTransaction();

                dbSession.save(entity);

                dbSession.getTransaction().commit();
            } catch (HibernateException e)
            {
                dbSession.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                dbSession.close();
            }
        }
    }

    public void logLogin(Integer userId) {
        if (level != LoggerLevel.OFF) {
            Timestamp timestamp = new Timestamp(new Date().getTime());
            String activity = "Login";

            LogUserEntity entity = new LogUserEntity();
            entity.setActivity(activity);
            entity.setTimestamp(timestamp);
            entity.setUserId(userId);

            Session dbSession = SessionFactoryListener.getSession();

            try {
                dbSession.beginTransaction();

                dbSession.save(entity);

                dbSession.getTransaction().commit();
            } catch (HibernateException e)
            {
                dbSession.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                dbSession.close();
            }
        }

        }

    public void logLogout(Integer userId) {
        if (level != LoggerLevel.OFF) {
            Timestamp timestamp = new Timestamp(new Date().getTime());
            String activity = "Logout";

            LogUserEntity entity = new LogUserEntity();

            entity.setActivity(activity);
            entity.setTimestamp(timestamp);
            entity.setUserId(userId);

            Session dbSession = SessionFactoryListener.getSession();

            try {
                dbSession.beginTransaction();

                dbSession.save(entity);

                dbSession.getTransaction().commit();
            } catch (HibernateException e)
            {
                dbSession.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                dbSession.close();
            }
        }

    }
}
