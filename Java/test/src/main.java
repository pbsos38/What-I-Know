import java.lang.reflect.Method;

public class main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {

//        subclass o = new subclass();
//        o.subclass_method();
//        String s2 = "subclass_method";
//
//        Class reflected = Class.forName("subclass");
//        Object o = reflected.newInstance();
//        Method m = o.getClass().getMethod(s2);
//        m.invoke();


        System.out.printf("\n\n%-15s\n\t%-15s%-5.3f\n\t%-15s%-5.3f\n\t%-15s%-5.3f","Cone","Radius: ",4564.255456,"Area: ",4564.255456,"Volume: ",4564.255456);
    }
}
    Shape temp;
    Comparator<Shape> compare;

    public void heapSort(Shape arr[], Comparator<Shape> compare) {
        start = System.currentTimeMillis();
        this.compare = compare;
        int size = arr.length;
        int i;

        for (i = size / 2 - 1; i >= 0; i--)
            heapify(arr, size, i);

        for (i = size - 1; i >= 0; i--) {
            if (i == 0)
                System.out.println("The first value is: " + arr[i].toString());
            else if (i == arr.length - 1)
                System.out.println("The last value: " + arr[i].toString());
            else if (i % 1000 == 999) {
                System.out.println("The " + (i + 1) + "th value: " + arr[i].toString());
            }
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);

        }
        stop = System.currentTimeMillis();
        timeTaken = stop - start;
        System.out.println("The time talen for sorting is: " + timeTaken + "ms");

    }

    void heapify(Shape arr[], int size, int i) {
        if (compare != null) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && compare.compare(arr[left], arr[largest]) == -1)
                largest = left;

            if (right < size && compare.compare(arr[right], arr[largest]) == -1)
                largest = right;

            if (largest != i) {
                temp = arr[i];
                arr[i] = arr[largest];
                arr[largest] = temp;
                heapify(arr, size, largest);
            }
        } else {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && arr[left].compareTo(arr[largest]) == -1)
                largest = left;

            if (right < size && arr[right].compareTo(arr[largest]) == -1)
                largest = right;

            if (largest != i) {
                temp = arr[i];
                arr[i] = arr[largest];
                arr[largest] = temp;
                heapify(arr, size, largest);
            }


}

