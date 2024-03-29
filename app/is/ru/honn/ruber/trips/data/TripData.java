package is.ru.honn.ruber.trips.data;

import is.ru.honn.ruber.domain.Driver;
import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.trips.service.TripExistsException;
import is.ru.honn.ruber.trips.service.TripNotFoundException;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arnif on 10/24/14.
 */
public class TripData extends RuData implements TripDataGateway {

    @Override
    public int addTrip(Trip trip) {

        SimpleJdbcInsert insert =
                new SimpleJdbcInsert(getDataSource())
                        .withTableName("ru_trips")
                        .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<String, Object>(6);
        parameters.put("uuid", trip.getId());
        parameters.put("requestTime", trip.getRequestTime());
        parameters.put("productID", trip.getProductId());
        parameters.put("distance", trip.getDistance());
        parameters.put("startTime", trip.getStartTime());
        parameters.put("endTime", trip.getEndTime());
        parameters.put("status", trip.getStatus().toString());
        parameters.put("longitude", trip.getLongitude());
        parameters.put("latitude", trip.getLatitude());
        parameters.put("endLongitude", trip.getEndLongitude());
        parameters.put("endLatitude", trip.getEndLatitude());

        int returnKey;

        try
        {
            returnKey = insert.executeAndReturnKey(parameters).intValue();
        }
        catch(DataIntegrityViolationException divex)
        {
            throw new TripExistsException("User " + trip.getId() + " already exits", divex);
        }

        return returnKey;
    }

    @Override
     public List<Trip> getTripsByUserID(int userID) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        try
        {
            List<Trip> t = jdbcTemplate.query("select * from ru_trips where uuid =?", new TripRowMapper(), userID);
            return t;

        }
        catch (EmptyResultDataAccessException erdaex)
        {
            throw new TripNotFoundException("No trip found for user with userid: " + userID);
        }

    }

    @Override
    public Trip getTripByID(int tripID) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String queryString = "SELECT * FROM ru_trips t WHERE t.id =" + tripID;

        try {
            Trip t = (Trip)jdbcTemplate.queryForObject(queryString, new TripRowMapper());
            return t;
        }
        catch (EmptyResultDataAccessException erdaex)
        {
            throw new TripNotFoundException("No trip found with id: " + tripID);
        }

    }

    @Override
    public Driver getDriverOfProduct(int productID) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String queryString = "SELECT u.username, u.firstname, u.lastname, p.description, p.displayName, p.capacity, p.image\n" +
                "FROM ru_users u\n" +
                "  JOIN ru_drivers d\n" +
                "    ON d.uuid = u.id\n" +
                "  JOIN ru_products p\n" +
                "    ON " + productID +" = p.id\n" +
                "  WHERE d.productID = " + productID +";\n";

        try
        {
            Driver d = (Driver)jdbcTemplate.queryForObject(queryString, new DriverRowMapper());
            return d;
        }
        catch (EmptyResultDataAccessException erdaex)
        {
            throw new TripNotFoundException("No product found with productid: " + productID);
        }
    }
}
