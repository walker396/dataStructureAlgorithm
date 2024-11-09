package com.jh.recursion;

import java.util.Arrays;

public class Fibonacci {
    public static int f(int n){
        if(n == 0){
            return 0;
        }

        if(n ==1){
            return 1;
        }

        return  f(n-2)+ f(n-1);
    }

    public static int fiboCache(int n){
        int[] cache = new int[n+1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        return fWithCache(n, cache);
    }
    public static int fWithCache(int n,int[] cache){
        if(cache[n] != -1){
            return cache[n];
        }
        int l2 = f(n - 2);
        int l1 = f(n - 1);
        cache[n] = l2+l1;
        return  cache[n] ;
    }

    public static void main(String[] args) {
        System.out.println(f(14));
        System.out.println(fiboCache(14));
    }
}
