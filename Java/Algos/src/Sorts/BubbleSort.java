package Sorts;
import java.util.Arrays;
public class BubbleSort {
    public static void main(String[] args){
        int[] list= new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        for(int i=1;i<=list.length;i++){
            for ( int j=0;j< list.length;j++ ){
                if(list[i-1]>list[j]){
                    int temp = list[i-1];
                    list[i-1] = list[j];
                    list[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(list));
    }
}
