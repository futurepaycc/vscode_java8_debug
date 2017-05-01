import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaTest{
    public static void main(String[] args) {
        List<String> names = Arrays.asList("liunix","jack","asdf");
        // Collections.sort(names, new Comparator<String>(){
        //     public int compare(String a,String b){
        //         return a.compareTo(b);
        //     }
        // });
        Collections.sort(names,(a,b)->a.compareTo(b));
        for(int i=0;i<names.size();i++){
            System.out.println(names.get(i));
        }
    }
}