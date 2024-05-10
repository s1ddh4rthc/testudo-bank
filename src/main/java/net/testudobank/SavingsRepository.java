package net.testudobank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SavingsRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SavingsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class SavingsAccountMapper implements RowMapper<SavingsAccount> {
        public SavingsAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
            SavingsAccount account = new SavingsAccount();
            account.setAccountID(rs.getString("AccountID"));
            account.setCustomerID(rs.getString("CustomerID"));
            account.setBalance(rs.getDouble("Balance"));
            account.setInterestRate(rs.getDouble("InterestRate"));
            return account;
        }
    }

    private static final class SavingsGoalMapper implements RowMapper<SavingsGoal> {
        public SavingsGoal mapRow(ResultSet rs, int rowNum) throws SQLException {
            SavingsGoal goal = new SavingsGoal();
            goal.setGoalID(rs.getString("GoalID"));
            goal.setAccountID(rs.getString("AccountID"));
            goal.setGoalName(rs.getString("GoalName"));
            goal.setTargetAmount(rs.getDouble("TargetAmount"));
            goal.setCurrentAmount(rs.getDouble("CurrentAmount"));
            goal.setDeadline(rs.getTimestamp("Deadline").toLocalDateTime());
            return goal;
        }
    }

    public SavingsAccount findSavingsAccountById(String accountID) {
        try {
            String sql = "SELECT * FROM SavingsAccounts WHERE AccountID = ?";
            return jdbcTemplate.queryForObject(sql, new SavingsAccountMapper(), accountID);
        } catch (EmptyResultDataAccessException e) {
            return null; // or handle depending on use case
        }
    }

    public void save(SavingsAccount account) {
        if (findSavingsAccountById(account.getAccountID()) == null) {
            // Insert new account
            String insertSql = "INSERT INTO SavingsAccounts (AccountID, CustomerID, Balance, InterestRate) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(insertSql, account.getAccountID(), account.getCustomerID(), account.getBalance(), account.getInterestRate());
        } else {
            // Update existing account
            String updateSql = "UPDATE SavingsAccounts SET CustomerID = ?, Balance = ?, InterestRate = ? WHERE AccountID = ?";
            jdbcTemplate.update(updateSql, account.getCustomerID(), account.getBalance(), account.getInterestRate(), account.getAccountID());
        }
    }
    
    public List<SavingsAccount> findAllSavingsAccountsByCustomerID(String customerID) {
        String sql = "SELECT * FROM SavingsAccounts WHERE CustomerID = ?";
        return jdbcTemplate.query(sql, new SavingsAccountMapper(), customerID);
    }

    public SavingsGoal findSavingsGoalById(String goalID) {
        String sql = "SELECT * FROM SavingsGoals WHERE GoalID = ?";
        return jdbcTemplate.queryForObject(sql, new SavingsGoalMapper(), goalID);
    }

    public void addSavingsGoal(SavingsGoal goal) {
        String sql = "INSERT INTO SavingsGoals (GoalID, AccountID, GoalName, TargetAmount, CurrentAmount, Deadline) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, goal.getGoalID(), goal.getAccountID(), goal.getGoalName(), goal.getTargetAmount(), goal.getCurrentAmount(), java.sql.Timestamp.valueOf(goal.getDeadline()));
    }

    public void updateSavingsGoal(SavingsGoal goal) {
        String sql = "UPDATE SavingsGoals SET AccountID = ?, GoalName = ?, TargetAmount = ?, CurrentAmount = ?, Deadline = ? WHERE GoalID = ?";
        jdbcTemplate.update(sql, goal.getAccountID(), goal.getGoalName(), goal.getTargetAmount(), goal.getCurrentAmount(), java.sql.Timestamp.valueOf(goal.getDeadline()), goal.getGoalID());
    }

    public void deleteSavingsGoal(String goalID) {
        String sql = "DELETE FROM SavingsGoals WHERE GoalID = ?";
        jdbcTemplate.update(sql, goalID);
    }
}
