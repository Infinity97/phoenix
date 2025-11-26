public class Calculator {

    public static void main(String[] args) {
      int value = null;  
      System.out.println("Addition Result: " + add(value, 5));
      System.out.println("Subtraction Result: " + subtract(10, value));
      System.out.println("Multiplication Result: " + multiply(value, 0));
      System.out.println("Division Result: " + divide(8, value));
    }

    public static Integer add(Integer a, Integer b) {
        return a + b;
    }

    public static Integer subtract(Integer a, Integer b) {
        return a - b;
    }

    public static Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    public static Integer divide(Integer a, Integer b) {
        return a / b;
    }
}
