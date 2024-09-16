package net.testudobank.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import net.testudobank.CryptoPriceClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.delegate.DatabaseDelegate;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import net.testudobank.MvcController;
import net.testudobank.User;
import net.testudobank.helpers.MvcControllerIntegTestHelpers;

@Testcontainers
@SpringBootTest
public class ActivityComparisonFeatureIntegTest {
  //// LITERAL CONSTANTS ////
  private static String CUSTOMER1_ID = "123456789";
  private static String CUSTOMER1_PASSWORD = "password";
  private static String CUSTOMER1_FIRST_NAME = "Foo";
  private static String CUSTOMER1_LAST_NAME = "Bar";
  public static long REASONABLE_TIMESTAMP_EPSILON_IN_SECONDS = 1L;
  
  // Spins up small MySQL DB in local Docker container
  @Container
  public static MySQLContainer db = new MySQLContainer<>("mysql:5.7.37")
    .withUsername("root")
    .withPassword("db_password")
    .withDatabaseName("testudo_bank");


  private static MvcController controller;
  private static JdbcTemplate jdbcTemplate;
  private static DatabaseDelegate dbDelegate;
  private static CryptoPriceClient cryptoPriceClient = Mockito.mock(CryptoPriceClient.class);

  @BeforeAll
  public static void init() throws SQLException {
    dbDelegate = new JdbcDatabaseDelegate(db, "");
    ScriptUtils.runInitScript(dbDelegate, "createDB.sql");
    jdbcTemplate = new JdbcTemplate(MvcControllerIntegTestHelpers.dataSource(db));
    jdbcTemplate.getDataSource().getConnection().setCatalog(db.getDatabaseName());
    controller = new MvcController(jdbcTemplate, cryptoPriceClient);
  }

  @AfterEach
  public void clearDB() throws ScriptException {
    // runInitScript() pulls all the String text from the SQL file and just calls executeDatabaseScript(),
    // so it is OK to use runInitScript() again even though we aren't initializing the DB for the first time here.
    // runInitScript() is a poorly-named function.
    ScriptUtils.runInitScript(dbDelegate, "clearDB.sql");
  }

  //// INTEGRATION TESTS ////

  /**
   * Verifies that after 5 deposits of exactly $20 (minimum for interest), it applies interest.
   * 
   * Assumes that the customer's account is in the simplest state
   * (not in overdraft, account is not frozen due to too many transaction disputes, etc.)
   * 
   * @throws SQLException
   * @throws ScriptException
   */
  @Test
  public void testWithdrawPercentChange() throws SQLException, ScriptException {
    double BALANCE_INTEREST_RATE = 1.015;
    // initialize customer1 with a balance of $123.45 (to make sure this works for non-whole dollar amounts). represented as pennies in the DB.
    double CUSTOMER1_BALANCE = 100.00;
    int CUSTOMER1_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_BALANCE);
    MvcControllerIntegTestHelpers.addCustomerToDB(dbDelegate, CUSTOMER1_ID, CUSTOMER1_PASSWORD, CUSTOMER1_FIRST_NAME, CUSTOMER1_LAST_NAME, CUSTOMER1_BALANCE_IN_PENNIES, 0);

    // Prepare Deposit Form to Deposit $12.34 to customer 1's account.
    double CUSTOMER1_AMOUNT_TO_DEPOSIT = 20.00; // user input is in dollar amount, not pennies.
    User customer1DepositFormInputs = new User();
    customer1DepositFormInputs.setUsername(CUSTOMER1_ID);
    customer1DepositFormInputs.setPassword(CUSTOMER1_PASSWORD);
    customer1DepositFormInputs.setAmountToDeposit(CUSTOMER1_AMOUNT_TO_DEPOSIT);

    // FIRST DEPOSIT, send request to the Deposit Form's POST handler in MvcController
    controller.submitDeposit(customer1DepositFormInputs);

    // fetch updated data from the DB
    List<Map<String,Object>> customersTableData = jdbcTemplate.queryForList("SELECT * FROM Customers;");
    Map<String,Object> customer1Data = customersTableData.get(0);

    // verify customer balance was increased by $20
    double CUSTOMER1_EXPECTED_FIRST_BALANCE = CUSTOMER1_BALANCE + CUSTOMER1_AMOUNT_TO_DEPOSIT;
    int CUSTOMER1_EXPECTED_FIRST_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_EXPECTED_FIRST_BALANCE);
    assertEquals(CUSTOMER1_EXPECTED_FIRST_BALANCE_IN_PENNIES, (int)customer1Data.get("Balance"));

    // SECOND DEPOSIT, send request to the Deposit Form's POST handler in MvcController
    controller.submitDeposit(customer1DepositFormInputs);

    // fetch updated data from the DB
    customersTableData = jdbcTemplate.queryForList("SELECT * FROM Customers;");
    customer1Data = customersTableData.get(0);

    // verify customer balance was increased by $20
    double CUSTOMER1_EXPECTED_SECOND_BALANCE = CUSTOMER1_EXPECTED_FIRST_BALANCE + CUSTOMER1_AMOUNT_TO_DEPOSIT;
    int CUSTOMER1_EXPECTED_SECOND_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_EXPECTED_SECOND_BALANCE);
    assertEquals(CUSTOMER1_EXPECTED_SECOND_BALANCE_IN_PENNIES, (int)customer1Data.get("Balance"));

    // THIRD DEPOSIT, send request to the Deposit Form's POST handler in MvcController
    controller.submitDeposit(customer1DepositFormInputs);

    // fetch updated data from the DB
    customersTableData = jdbcTemplate.queryForList("SELECT * FROM Customers;");
    customer1Data = customersTableData.get(0);

    // verify customer balance was increased by $20
    double CUSTOMER1_EXPECTED_THIRD_BALANCE = CUSTOMER1_EXPECTED_SECOND_BALANCE + CUSTOMER1_AMOUNT_TO_DEPOSIT;
    int CUSTOMER1_EXPECTED_THIRD_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_EXPECTED_THIRD_BALANCE);
    assertEquals(CUSTOMER1_EXPECTED_THIRD_BALANCE_IN_PENNIES, (int)customer1Data.get("Balance"));

    // FOURTH DEPOSIT, send request to the Deposit Form's POST handler in MvcController
    controller.submitDeposit(customer1DepositFormInputs);

    // fetch updated data from the DB
    customersTableData = jdbcTemplate.queryForList("SELECT * FROM Customers;");
    customer1Data = customersTableData.get(0);

    // verify customer balance was increased by $20
    double CUSTOMER1_EXPECTED_FOURTH_BALANCE = CUSTOMER1_EXPECTED_THIRD_BALANCE + CUSTOMER1_AMOUNT_TO_DEPOSIT;
    int CUSTOMER1_EXPECTED_FOURTH_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_EXPECTED_FOURTH_BALANCE);
    assertEquals(CUSTOMER1_EXPECTED_FOURTH_BALANCE_IN_PENNIES, (int)customer1Data.get("Balance"));

    // FIFTH DEPOSIT, send request to the Deposit Form's POST handler in MvcController
    controller.submitDeposit(customer1DepositFormInputs);

    // fetch updated data from the DB
    customersTableData = jdbcTemplate.queryForList("SELECT * FROM Customers;");
    customer1Data = customersTableData.get(0);

    // verify customer balance was increased by $20
    double CUSTOMER1_EXPECTED_FIFTH_BALANCE = CUSTOMER1_EXPECTED_FOURTH_BALANCE + CUSTOMER1_AMOUNT_TO_DEPOSIT;
    CUSTOMER1_EXPECTED_FIFTH_BALANCE = Math.round(CUSTOMER1_EXPECTED_FIFTH_BALANCE * BALANCE_INTEREST_RATE);
    int CUSTOMER1_EXPECTED_FIFTH_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_EXPECTED_FIFTH_BALANCE);
    assertEquals(CUSTOMER1_EXPECTED_FIFTH_BALANCE_IN_PENNIES, (int)customer1Data.get("Balance"));
  }

  /**
   * Verifies that after 5 deposits of exactly $20 (minimum for interest), it applies interest.
   * 
   * Assumes that the customer's account is in the simplest state
   * (not in overdraft, account is not frozen due to too many transaction disputes, etc.)
   * 
   * @throws SQLException
   * @throws ScriptException
   */
  @Test
  public void testDepositPercentChange() throws SQLException, ScriptException {
    double BALANCE_INTEREST_RATE = 1.015;
    // initialize customer1 with a balance of $123.45 (to make sure this works for non-whole dollar amounts). represented as pennies in the DB.
    double CUSTOMER1_BALANCE = 100.00;
    int CUSTOMER1_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_BALANCE);
    MvcControllerIntegTestHelpers.addCustomerToDB(dbDelegate, CUSTOMER1_ID, CUSTOMER1_PASSWORD, CUSTOMER1_FIRST_NAME, CUSTOMER1_LAST_NAME, CUSTOMER1_BALANCE_IN_PENNIES, 0);

    // Prepare Deposit Form to Deposit $12.34 to customer 1's account.
    double CUSTOMER1_AMOUNT_TO_DEPOSIT = 20.00; // user input is in dollar amount, not pennies.
    User customer1DepositFormInputs = new User();
    customer1DepositFormInputs.setUsername(CUSTOMER1_ID);
    customer1DepositFormInputs.setPassword(CUSTOMER1_PASSWORD);
    customer1DepositFormInputs.setAmountToDeposit(CUSTOMER1_AMOUNT_TO_DEPOSIT);

    // FIRST DEPOSIT, send request to the Deposit Form's POST handler in MvcController
    controller.submitDeposit(customer1DepositFormInputs);

    // fetch updated data from the DB
    List<Map<String,Object>> customersTableData = jdbcTemplate.queryForList("SELECT * FROM Customers;");
    Map<String,Object> customer1Data = customersTableData.get(0);

    // verify customer balance was increased by $20
    double CUSTOMER1_EXPECTED_FIRST_BALANCE = CUSTOMER1_BALANCE + CUSTOMER1_AMOUNT_TO_DEPOSIT;
    int CUSTOMER1_EXPECTED_FIRST_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_EXPECTED_FIRST_BALANCE);
    assertEquals(CUSTOMER1_EXPECTED_FIRST_BALANCE_IN_PENNIES, (int)customer1Data.get("Balance"));

    // SECOND DEPOSIT, send request to the Deposit Form's POST handler in MvcController
    controller.submitDeposit(customer1DepositFormInputs);

    // fetch updated data from the DB
    customersTableData = jdbcTemplate.queryForList("SELECT * FROM Customers;");
    customer1Data = customersTableData.get(0);

    // verify customer balance was increased by $20
    double CUSTOMER1_EXPECTED_SECOND_BALANCE = CUSTOMER1_EXPECTED_FIRST_BALANCE + CUSTOMER1_AMOUNT_TO_DEPOSIT;
    int CUSTOMER1_EXPECTED_SECOND_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_EXPECTED_SECOND_BALANCE);
    assertEquals(CUSTOMER1_EXPECTED_SECOND_BALANCE_IN_PENNIES, (int)customer1Data.get("Balance"));

    // THIRD DEPOSIT, send request to the Deposit Form's POST handler in MvcController
    controller.submitDeposit(customer1DepositFormInputs);

    // fetch updated data from the DB
    customersTableData = jdbcTemplate.queryForList("SELECT * FROM Customers;");
    customer1Data = customersTableData.get(0);

    // verify customer balance was increased by $20
    double CUSTOMER1_EXPECTED_THIRD_BALANCE = CUSTOMER1_EXPECTED_SECOND_BALANCE + CUSTOMER1_AMOUNT_TO_DEPOSIT;
    int CUSTOMER1_EXPECTED_THIRD_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_EXPECTED_THIRD_BALANCE);
    assertEquals(CUSTOMER1_EXPECTED_THIRD_BALANCE_IN_PENNIES, (int)customer1Data.get("Balance"));

    // FOURTH DEPOSIT, send request to the Deposit Form's POST handler in MvcController
    controller.submitDeposit(customer1DepositFormInputs);

    // fetch updated data from the DB
    customersTableData = jdbcTemplate.queryForList("SELECT * FROM Customers;");
    customer1Data = customersTableData.get(0);

    // verify customer balance was increased by $20
    double CUSTOMER1_EXPECTED_FOURTH_BALANCE = CUSTOMER1_EXPECTED_THIRD_BALANCE + CUSTOMER1_AMOUNT_TO_DEPOSIT;
    int CUSTOMER1_EXPECTED_FOURTH_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_EXPECTED_FOURTH_BALANCE);
    assertEquals(CUSTOMER1_EXPECTED_FOURTH_BALANCE_IN_PENNIES, (int)customer1Data.get("Balance"));

    // FIFTH DEPOSIT, send request to the Deposit Form's POST handler in MvcController
    controller.submitDeposit(customer1DepositFormInputs);

    // fetch updated data from the DB
    customersTableData = jdbcTemplate.queryForList("SELECT * FROM Customers;");
    customer1Data = customersTableData.get(0);

    // verify customer balance was increased by $20
    double CUSTOMER1_EXPECTED_FIFTH_BALANCE = CUSTOMER1_EXPECTED_FOURTH_BALANCE + CUSTOMER1_AMOUNT_TO_DEPOSIT;
    CUSTOMER1_EXPECTED_FIFTH_BALANCE = Math.round(CUSTOMER1_EXPECTED_FIFTH_BALANCE * BALANCE_INTEREST_RATE);
    int CUSTOMER1_EXPECTED_FIFTH_BALANCE_IN_PENNIES = MvcControllerIntegTestHelpers.convertDollarsToPennies(CUSTOMER1_EXPECTED_FIFTH_BALANCE);
    assertEquals(CUSTOMER1_EXPECTED_FIFTH_BALANCE_IN_PENNIES, (int)customer1Data.get("Balance"));

    assertEquals(-25, (int)customer1Data.get("DepositPercentChange"));
  }
}