package com.project.uniqo;

public class test {

    public static int flip(int x) {
        return (x == 0) ? 1 : 0;
    }

    public static int getFlipWithStartingInt(int[] A, int expected) {
        int flips = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != expected) {
                flips++;
            }
            expected = flip(expected);
        }
        return flips;
    }

    public int solution(int[] A) {
        return Math.min(getFlipWithStartingInt(A,0), getFlipWithStartingInt(A, 1));
    }

    public static void main(String[] args) {
        test t = new test();

        System.out.println(t.solution(new int[]{1,0,1,0,1,1,1,1,1,1,0,0,1,0,1,1,0,0}));
        System.out.println(t.solution(new int[]{1, 1, 0, 1, 1}));
        System.out.println(t.solution(new int[]{0, 1, 0}));
        System.out.println(t.solution(new int[]{0, 1, 1, 0}));
    }
}