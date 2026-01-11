package SpendingsTracker.SpendingsTracker.Controller;

import SpendingsTracker.SpendingsTracker.Service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@Controller
public class DashboardController {

    private final TransactionService transactionService;

    public DashboardController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public String getDashboard(Model model) {
        Map<String, Double> categoryData = transactionService.getSpendingByCategory();
        Map<String, Double> dailyData = transactionService.getSpendingByDate();

        model.addAttribute("categoryLabels", categoryData.keySet());
        model.addAttribute("categoryValues", categoryData.values());

        model.addAttribute("dateLabels", dailyData.keySet());
        model.addAttribute("dateValues", dailyData.values());

        model.addAttribute("totalSpent", String.format("%.2f", transactionService.getTotalSpent()));

        return "dashboard";
    }
}
