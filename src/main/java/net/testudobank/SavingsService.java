package net.testudobank;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SavingsService {
    private final SavingsRepository savingsRepository;

    public SavingsService(SavingsRepository savingsRepository) {
        this.savingsRepository = savingsRepository;
    }

    public void createSavingsGoal(SavingsGoal goal) {
        // Validate goal details
        if (goal.getDeadline().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Deadline cannot be in the past");
        }
        savingsRepository.addSavingsGoal(goal);
    }

    public void createSavingsAccount(SavingsAccount account) {
        // Logic to save the account using SavingsRepository
        savingsRepository.save(account);
    }    

    public void contributeToGoal(String goalID, double amount) {
        SavingsGoal goal = savingsRepository.findSavingsGoalById(goalID);
        goal.contribute(amount);
        savingsRepository.updateSavingsGoal(goal);
    }

    public List<SavingsAccount> getAllSavingsAccountsForCustomer(String customerID) {
        return savingsRepository.findAllSavingsAccountsByCustomerID(customerID);
    }
}
