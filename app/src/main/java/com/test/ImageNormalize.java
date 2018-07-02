package com.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ImageNormalize {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        /* Getting input in following format
        * array length - 3
        * array 1 - {1, 2, 3}
        * array 2 - {4, 5, 6}
        */

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arrImage1 = new int[n];

        String[] arrItems1 = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems1[i]);
            arrImage1[i] = arrItem;
        }

        int[] arrImage2 = new int[n];

        String[] arrItems2 = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems2[i]);
            arrImage2[i] = arrItem;
        }

        scanner.close();

        Integer[] finalArray = new Integer[n];

        for( int i=0; i<n; i++){
            finalArray[i] = (arrImage1[i]+arrImage2[i])/2;
            System.out.print(finalArray[i]+" ");
        }

        int max = Collections.max(Arrays.asList(finalArray));
        int limit = 255;

        for( int i=0; i<n; i++){
            finalArray[i] = (finalArray[i]*limit)/max;
            System.out.print(finalArray[i]+" ");
        }
    }

}
