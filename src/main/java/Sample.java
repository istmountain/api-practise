import java.util.Arrays;

public class Sample {
    public static void main(String[] args) {
        String word="I am quality assurance engineer";
        String [] reverse=new String[word.length()];
        String [] arr=word.split(" ");
        System.out.println(Arrays.toString(arr));
        int m=0;
        for(int i= arr.length;i<=0;i--){
            reverse[m]=arr[i];
            m++;
        }
        System.out.println(Arrays.toString(reverse));

    }
}
