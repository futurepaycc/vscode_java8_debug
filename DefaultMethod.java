interface Formula{
    default double sqrt(int a){
        return Math.sqrt(a);
    }

    double calculate(int a);
}

public class DefaultMethod{

    public static void main(String[] args){
        Formula formula = new Formula(){
            public double calculate(int a){
                return sqrt(a*100);
            }
        };
        double result = formula.sqrt(16);
        System.out.println(result);
        System.out.println(formula.calculate(100));
    }

}