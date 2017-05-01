@FunctionalInterface
interface Converter<F,T>{
    T convert(F from);
}

public class FunctionalTest{
    public static void main(String[] args) {
        //lambda实现函数形接口
        // Converter<String,Integer> converter = (from)->Integer.valueOf(from);
        Converter<String,Integer> converter = Integer::valueOf; //静态方法引用
        Integer result = converter.convert("123");
        System.out.println(result);
    }
}