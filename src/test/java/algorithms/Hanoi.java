package algorithms;

/**
 * Created by ww on 17/2/14.
 */
public class Hanoi {
    static void move(int n,String from,String to){
        System.out.println("from"+from+"to"+to);
    }
    static void hanoi(int n,String from,String depend,String to){
    if(n==1){
        move(n,from,to);
        return;
    }
    hanoi(n-1,from,to,depend);
        move(n,from,to);
        hanoi(n-1,depend,from,to);

    }
    public static void main(String[]args){
        String from="A";
        String to="C";
        String depend="B";
        hanoi(3,from,depend,to);

    }

}
