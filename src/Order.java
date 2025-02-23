import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

public class Order {
    private ArrayList<Food> shoppingBasket = new ArrayList<Food>();
    private HashSet<Food> soldProduct = new HashSet<Food>();
    private int counter = 0;

    public void AddOrder(Food food) {
        Scanner sc = new Scanner(System.in);
        int input;

        if (food.getdoubleSize() > 0) {
            food.Show();
            Food doubleSize = new Food(food.getName(), food.getKind(), food.getdoubleSize(), food.getdoubleSize(), food.getCategory());
            System.out.println("어떤 옵션을 추가하겠습니까?");
            System.out.println("1. Single(W " + food.getPrice() + ")     2. Double(W " + doubleSize.getPrice() + ")");
            input = sc.nextInt();
            // 일반 사이즈
            if (input == 1) {
                question(food);
            }
            // 곱배기 사이즈
            else if (input == 2) {
                question(doubleSize);
            }
            else {
                question(food);
            }
        }
        else {
            question(food);
        }
    }

    public void question(Food food) {
        Scanner sc = new Scanner(System.in);
        int input;
        food.Show();
        System.out.println("고른 메뉴를 장바구니에 추가할까요?");
        System.out.println("1. 네      2. 아니오");
        input = sc.nextInt();
        // 장바구니에 추가하기
        if(input == 1) {
            shoppingBasket.add(food);
            System.out.println(food.getName() + "가 장바구니에 추가되었습니다.");
        }
        else if(input == 2) {
            System.out.println(food.getName() + "를 장바구니에 추가하지 않았습니다.");
        }
    }

    // 장바구니 메뉴 보여주기
    public double getShoppingBasket() {
        double totalprice = 0;
        int EA;
        HashSet<Food> foodCountSet = new HashSet<Food>(shoppingBasket);
        for (Food item : foodCountSet) {
            // 음식의 개수임! EA는!
            EA = Collections.frequency(shoppingBasket, item);
            item.Show(EA);
            totalprice += item.getPrice() * EA;
        }
        totalprice = Math.round((totalprice * 100)) / 100.0;
        return totalprice;
    }

    // 총 상품 hashset에 목록 넣음
    public int CompleteOrder() {
        Kiosk.addProfit(getShoppingBasket());
        soldProduct.addAll(shoppingBasket);
        shoppingBasket.clear();
        counter++;
        return counter;
    }

    public void CancelOrder() {
        shoppingBasket.clear();
    }

    public void SoldList() {
        System.out.println("[ 총 상품 목록 현황 ]");
        System.out.println("현재까지 판매된 상품 목록입니다.");
        for(Food item : soldProduct) {
            System.out.printf("%s | W %s\n", item.getName(), item.getPrice());
        }
    }

    public Optional<String> selectPay(){
        Scanner sc = new Scanner(System.in);
        System.out.println("결제 방법을 골라주세요! 카드는 1번, 현금은 2번을 눌러주세요!");

        int choice = sc.nextInt();

        if (choice == 1) {
            return Optional.of("카드 결제로 진행 하겠습니다!");
        }
        else if (choice == 2) {
            return Optional.of("현금 결제로 진행 하겠습니다!");
        }
        else {
            System.out.println("잘못된 입력이므로 기본설정인 현금으로 계산합니다. 카드 결제 시 카운터에 문의해주세요!");
            return Optional.of("현금 결제로 진행 하겠습니다!");
        }
    }
}
