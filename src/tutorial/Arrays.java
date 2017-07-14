/**
 * Created by Oliver on 27/06/2017.
 */
public class Arrays {
    public static void Array_for() {
        double[] myList = {1.9, 2.9, 3.4, 3.5};
        for (int i = 0; i < myList.length; i++) {
            System.out.println(myList[i]);
        }

        double total = 0d;
        for (int i = 0; i < myList.length; i++) {
            total += myList[i];
        }
        System.out.println("Total: " + total);

        double max = myList[0];
        for (int i = 0; i < myList.length; i++) {
            max = Math.max(myList[i], max);
        }
        System.out.println("Max: " + max);
    }

    public static void Array_foreach() {
        double[] myList = {1.9, 2.9, 3.4, 3.5};
        for (double item : myList) {
            System.out.println(item);
        }
    }

    public static int[] reverse(int[] array) {
        int[] result = new int[array.length];
        for (int i = 0, j = result.length - 1; i < array.length; i++, j--) {
            result[i] = array[j];
        }
        return result;
    }

    public static void Array_reverse() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] reversed = reverse(arr);
        for (int i : reversed) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Array_for();
        Array_foreach();
        Array_reverse();
    }

}
