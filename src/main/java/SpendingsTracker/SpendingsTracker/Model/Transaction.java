package SpendingsTracker.SpendingsTracker.Model;

public class Transaction {
    private String date;
    private String description;
    private Double amount;
    private String category;

    public Transaction(String date, String description, Double amount, String category) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDate() { return date; }
    public String getDescription() { return description; }
    public Double getAmount() { return amount; }
    public String getCategory() { return category; }

    public void setDate(String date) { this.date = date; }
    public void setDescription(String description) { this.description = description; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setCategory(String category) { this.category = category; }
}