package com.damu.febs.server.test.byMySelf.lambda;

public class Java8Tester {

    public static void main(String[] args) {
        Java8Tester java8Tester = new Java8Tester();
        MathOperation operation = (int a, int b) -> a + b;
//        System.out.println(operation);

        MathOperation subtraction = (a, b) -> a + b;

        MathOperation multiplication = (a, b) -> {
            return a * b;
        };

        MathOperation division = (a, b) -> a / b;

        System.out.println(java8Tester.operate(5, 6, subtraction));


    }

    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);
//        int k(int b,int c);
    }

    interface greetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }


}
