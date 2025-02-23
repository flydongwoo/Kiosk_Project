import java.util.Objects;

public class Food extends Menu {
    private double price;
    private double doubleSize;
    private String category;

    public Food(String name, String kind, double price, double doubleSize, String category) {
        super(name, kind);
        this.price = price;
        this.doubleSize = doubleSize;
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public double getdoubleSize() {
        return doubleSize;
    }
    public String getCategory() {
        return category;
    }

    public void Show() {
        System.out.printf("%s | W %s | %s\n", getName(), price, getKind());
    }
    public void Show(int EA) {
        System.out.printf("%s | W %s | %s개 | %s\n", getName(), price, EA, getKind());
    }

    public boolean equals(Object obj){
        if(obj instanceof Food) {
            Food temp = (Food) obj;
            return this.getName().equals(temp.getName()) &&
                    this.getPrice() == temp.getPrice() &&
                    this.getdoubleSize() == temp.getdoubleSize() &&
                    this.getKind().equals(temp.getKind()) &&
                    this.getCategory().equals(temp.getCategory());
        }
        return false;
    }
    public int hashCode() {
        return Objects.hash(getName(), getKind(), getPrice(), getdoubleSize(), getCategory());
    }
}
