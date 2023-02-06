import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        StringBuilder sb;
        Scanner sc=new Scanner(System.in);
        sb= new StringBuilder(sc.next());
        System.out.println("Input String is: "+sb);

        int l=0,h=sb.length()-1;
        // System.out.println(l);
        // System.out.println(h);

        while(l<h){
            char frontChar=sb.charAt(l);
            char backChar=sb.charAt(h);

            sb.setCharAt(l,backChar);
            sb.setCharAt(h,frontChar);

            l++;
            h--;
        }
        System.out.println(sb);
    }
}