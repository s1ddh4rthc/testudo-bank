package net.testudobank;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setFirstName(rs.getString("CustomerFirstName"));
        customer.setLastName(rs.getString("CustomerLastName"));
        customer.setCustomerID(rs.getString("SubCustomerID"));
        customer.setPassword(rs.getString("Password"));

        return customer;
    }
    
}
