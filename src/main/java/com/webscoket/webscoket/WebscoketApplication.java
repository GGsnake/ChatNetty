package com.webscoket.webscoket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableRabbit
@MapperScan("com.webscoket.webscoket.dao")
public class WebscoketApplication {

        public static void main(String[] args) {
        SpringApplication.run(WebscoketApplication.class, args);
    }
//    public static void main(String[] args) {
//        int[] arr = {23, 22, 3333, 11, 123, 44, 221, 9};
//        sort(0,arr.length-1,arr);
//        for (int i:arr){
//            System.out.println(i);
//
//        }
//    }
//
//    public static void sort(int start, int end, int[] arr) {
//        if (start < end) {
//            int pov = dviparent(start, end, arr);
//            sort(start,pov-1,arr);
//           sort(pov+1,end,arr);
//        }
//
//    }
//
//    public static int dviparent(int start, int end, int[] arr) {
//        //基准指针
//        int pov = start;
//        while (start < end&&end!=0) {
//            while (pov < end) {
//                if (arr[pov] > arr[end]) {
//                    int temp = arr[end];
//                    arr[end] = arr[pov];
//                    arr[pov] = temp;
//                    pov = end;
//                }
//                end--;
//
//            }
//            while (pov > start) {
//                if (arr[pov] < arr[start]) {
//                    int temp = arr[pov];
//                    arr[pov] = arr[start];
//                    arr[start] = temp;
//                    pov = start;
//                }
//                start++;
//            }
//        }
//        return pov;
//
//    }
//    public void swap(int start, int end,int[] arr) {
//        int temp= arr[end];
//        arr[end]=arr[pov];
//        arr[pov]=temp;
//
//    }


}
