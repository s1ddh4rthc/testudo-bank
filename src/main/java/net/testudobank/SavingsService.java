package net.testudobank;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void transferBetweenAccounts(String fromAccountId, String toAccountId, double amount) {
        SavingsAccount fromAccount = savingsRepository.findSavingsAccountById(fromAccountId);
        SavingsAccount toAccount = savingsRepository.findSavingsAccountById(toAccountId);

        if (fromAccount.getBalance() >= amount) {
            fromAccount.deductFunds(amount);
            toAccount.addFunds(amount);
            savingsRepository.save(fromAccount);
            savingsRepository.save(toAccount);
        } else {
            throw new IllegalArgumentException("Insufficient funds to complete the transfer");
        }
    }

    public List<SavingsAccount> getAllSavingsAccountsForCustomer(String customerID) {
        return savingsRepository.findAllSavingsAccountsByCustomerID(customerID);
    }

    public void updateSavingsGoal(SavingsGoal goal) {
        if (goal == null || goal.getGoalID() == null) {
            throw new IllegalArgumentException("Goal or Goal ID cannot be null");
        }
        
        // Further validations and logic
        savingsRepository.updateSavingsGoal(goal);
    }
}
