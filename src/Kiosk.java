import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collections;
import java.util.Optional;

public class Kiosk {
    private Order order = new Order();
    // 카테고리
    private ArrayList<Menu> categoryMenu = new ArrayList<Menu>();
    // 음식들
    private ArrayList<Food> allFoods = new ArrayList<Food>();
    // 카테고리 음식들
    private ArrayList<Food> categoryFood = new ArrayList<Food>();
    private double revenue;
    private static double totalProfit = 0;


    public void LoadMenu() {
        Menu noodleMenu = new Menu("Noodles", "수타면으로 뽑은 면 요리");
        Menu cookMenu = new Menu("Cook Menu", "특별한 레시피로 만든 맛있는 요리");
        Menu setMenu = new Menu("Set Menu", "가성비 좋게 즐길 수 있는 세트 요리");
        Menu drinkMenu = new Menu("Drink Menu", "시원한 음료");
        Menu alcoholMenu = new Menu("Alcohol Menu", "깔끔한 알코올");
        categoryMenu.add(noodleMenu);
        categoryMenu.add(cookMenu);
        categoryMenu.add(setMenu);
        categoryMenu.add(drinkMenu);
        categoryMenu.add(alcoholMenu);

        Food Jajangmyeon = new Food("Jajangmyeon", "맛있는 춘장으로 볶아낸 짜장면", 6.0, 7.0, "Noodles");
        Food Jjambbong = new Food("Jjambbong", "불향 가득 짬뽕", 7.0, 8.0, "Noodles");
        Food Udon = new Food("Udon", "시원하고 얼큰한 우동", 7.0, 8.0, "Noodles");
        Food Ulmyeon = new Food("Jajangmyeon", "칼칼한 국물이 맛있는 울면", 8.0, 9.0, "Noodles");
        allFoods.add(Jajangmyeon);
        allFoods.add(Jjambbong);
        allFoods.add(Udon);
        allFoods.add(Ulmyeon);

        Food Tangsuyuk= new Food("Tangsuyuk", "달달한 소스와 부드러운 고기의 조화, 탕수육", 21.0, 25.0, "Cook Menu");
        Food Palbochae = new Food("Palbochae", "먹으면 든든한 팔보채", 35.0, 40.0, "Cook Menu");
        allFoods.add(Tangsuyuk);
        allFoods.add(Palbochae);

        Food Set1 = new Food("Set1", "탕수육 + 짜장2 + 콜라", 22.0, 27.0, "Set Menu");
        Food Set2 = new Food("Set2", "탕수육 + 짬뽕2 + 콜라", 24.0, 29.0, "Set Menu");
        Food Set3 = new Food("Set3", "탕수육 + 우동2 + 콜라", 25.0, 30.0, "Set Menu");
        allFoods.add(Set1);
        allFoods.add(Set2);
        allFoods.add(Set3);

        Food Coke98 = new Food("Coke98", "시원한 콜라", 2.0, 3.0, "Drink Menu");
        Food Sprite = new Food("Sprite", "경쾌한 스프라이트", 2.0, 3.0, "Drink Menu");
        allFoods.add(Coke98);
        allFoods.add(Sprite);

        Food Soju = new Food("Soju", "소주", 5.0, 10.0, "Alcohol Menu");
        Food Beer = new Food("Beer", "맥주", 5.0, 10.0, "Alcohol Menu");
        allFoods.add(Soju);
        allFoods.add(Beer);
    }

    public void Display() throws InterruptedException {
        while (true) {
            int numbering;
            int selectCategoryNum = 0;
            int selectFoodNum;
            int selectCategory;


            // 카테고리
            numbering = ShowMenu();
            ShowOption(numbering);
            selectCategoryNum = getResponse(numbering, categoryMenu);

            if (selectCategoryNum >= numbering || selectCategoryNum == 0) {
                continue;
            }

            categoryFood.clear();
            for (Food food : allFoods) {
                if (food.getCategory().equals(categoryMenu.get(selectCategoryNum - 1).getName())) {
                    categoryFood.add(food);
                }
            }

            // 상세
            numbering = ShowMenuFood();
            ShowOption(numbering);
            selectFoodNum = getResponse(numbering, categoryFood);

            if (selectFoodNum >= numbering || selectCategoryNum == 0) {
                continue;
            }
            order.AddOrder(categoryFood.get(selectFoodNum - 1));
        }
    }

    public int ShowMenu() {
        int numbering = 1;
        System.out.println("부경 반점에 오신 것을 환영합니다~!");
        System.out.println("메뉴를 골라주세요.");
        System.out.println("[부경 반점 Menu]");

        for (Menu item : categoryMenu) {
            System.out.print(numbering + ". ");
            item.Show();
            numbering++;
        }
        return numbering;
    }

    public int ShowMenuFood() {
        int numbering = 1;
        System.out.println("[부경 반점 Menu]");

        for (Food food : categoryFood) {
            System.out.print(numbering + ". ");
            food.Show();
            numbering++;
        }
        return numbering;
    }


    public void ShowOption(int numbering) {
        System.out.println("[ 메뉴 주문 ]");
        System.out.printf(numbering + ". %s | %s\n", "Order", "장바구니 최종 확인 후 주문 완료~!");
        System.out.printf(numbering + 1 + ". %s | %s\n", "Cancel", "진행중인 주문을 취소합니당~");
    }

    public <PKNU extends Menu> int getResponse(int numbering, ArrayList<PKNU> list) throws InterruptedException {
        int input;
        int optionInput;
        double totalPrice;
        Scanner sc = new Scanner(System.in);
        input = sc.nextInt();
        if(1 <= input && input < numbering) {
            System.out.println(list.get(input - 1).getName() + " 선택했습니다.");
        }
        else if (input == numbering) {
            System.out.println("아래와 같이 주문하시겠습니까?");
            System.out.println("[ Orders ]");
            totalPrice = order.getShoppingBasket();
            System.out.println("[ Total ]");
            System.out.println("W " + totalPrice + "\n");
            System.out.println("1. 주문     2. 메뉴판");
            optionInput = sc.nextInt();
            if (optionInput == 1 && totalPrice !=0) {
                System.out.println("주문이 완료되었습니다!");
                System.out.println("대기번호는 [ " + order.CompleteOrder() + " ]번 입니다.");
                revenue +=totalPrice;

                Optional<String> payment = order.selectPay();

                System.out.println("잠시 후 키오스크가 종료됩니다.");
                Thread.sleep(3000);
                System.exit(0);
            }
            else if (optionInput == 2) {
                System.out.println("주문이 완료되지 않았습니다.");
                System.out.println("(초기 메뉴판으로 돌아갑니다.)");
                Thread.sleep(500);
            }
            else if(totalPrice == 0){
                System.out.println("주문하신 내용이 없습니다.");
                System.out.println("(초기 메뉴판으로 돌아갑니다.)");
                Thread.sleep(500);
            }
        }
        else if (input == numbering + 1) {
            System.out.println("진행하던 주문을 취소하겠습니까?");
            System.out.println("1. 확인     2. 취소");
            optionInput= sc.nextInt();
            if (optionInput == 1) {
                order.CancelOrder();
                System.out.println("진행하던 주문이 취소되었습니다. 이전 화면으로 돌아갑니다.");
                Thread.sleep(500);
            }
            if (optionInput == 2) {
                System.out.println("주문이 취소되지 않았습니다. 이전 화면으로 돌아갑니다.");
                Thread.sleep(500);
            }
        }
        else if (input == 0) { //옵션 기능 선택 시
            System.out.println("[ 총 판매금액 현황 ]");
            System.out.println("현재까지 총 판매된 금액은 [ W "+Math.round((revenue*100))/100.0 +"] 입니다.\n");
            order.SoldList();
            while(true) {
                System.out.println("1. 돌아가기");
                optionInput = sc.nextInt();
                if (optionInput == 1) {
                    System.out.println("이전 화면으로 돌아갑니다.");
                    break;
                }
                else{
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            }
        }

        return input;
    }

    public static void addProfit(double amount) {
        totalProfit += amount;
    }

    public static double getTotalProfit() {
        return totalProfit;
    }
}
