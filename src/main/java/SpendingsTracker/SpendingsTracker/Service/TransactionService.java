package SpendingsTracker.SpendingsTracker.Service;

import SpendingsTracker.SpendingsTracker.Model.Transaction;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/transactions.csv")))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] v = line.split(",");
                transactions.add(new Transaction(v[0], v[1], Double.parseDouble(v[2]), v[3]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public Map<String, Double> getSpendingByCategory() {
        return loadTransactions().stream()
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));
    }

    public Map<String, Double> getSpendingByDate() {
        //treeMap keeps the dates in chronological order for the Bar Chart
        return loadTransactions().stream()
                .collect(Collectors.groupingBy(Transaction::getDate, TreeMap::new, Collectors.summingDouble(Transaction::getAmount)));
    }

    public Double getTotalSpent() {
        return loadTransactions().stream().mapToDouble(Transaction::getAmount).sum();
    }
}