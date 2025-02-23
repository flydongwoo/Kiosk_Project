public class Menu {
    private String name;
    private String kind;

    public Menu(String name, String kind){
        this.name = name;
        this.kind = kind;
    }
    public void Show(){
        System.out.printf("%s | %s\n", name, kind);
    }

    public String getName() {
        return name;
    }
    public String getKind() {
        return kind;
    }

}
