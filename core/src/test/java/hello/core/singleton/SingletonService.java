package hello.core.singleton;

public class SingletonService {

    // 자기자신을 내부에 static으로 가지고있음
    // -> 클래스 레벨에 올라가기때문에 하나만 존재
    private static final SingletonService instance = new SingletonService();

    
    // 객체 인스턴스가 필요하면 오직 getInstance() 메소드를 통해서만 조회가능
    // 무분별한 호출을 막아 "항상" 같은 인스턴스를 반환
    public static SingletonService getInstance(){
        return instance;
    }


    // private "생성자"를 만들어 무분별한 호출을 막는다.
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤");
    }

}
