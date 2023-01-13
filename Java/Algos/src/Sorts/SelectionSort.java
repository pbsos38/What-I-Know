package Sorts;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SelectionSort {
    public static int[] list = new int[] {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
    public static void main(String[] args){
        for(int i=0;i<list.length-1 ; i++){
            int smallestItemPostion = i;
            for(int j=i+1; (j < list.length) ;j++) {
                if (list[smallestItemPostion] > list[j]) {
                    smallestItemPostion = j;
                }
            }
            int temp = list[smallestItemPostion];
            list[smallestItemPostion]=list[i];
            list[i] = temp;
        }
       System.out.println(Arrays.toString(list));
    }
}
