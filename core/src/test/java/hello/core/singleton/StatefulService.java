package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드 (전역변수)
                       // 10000 -> 20000으로 변경됨
    public int order(String name, int price){ // 지역 변수로 선언하여 막는다.

        System.out.println("name = " + name + "price = " + price);
//        this.price = price; // 여기가 문제 발생

        return price;

    }

//    public int getPrice(){
//        return price;
//    }

}
