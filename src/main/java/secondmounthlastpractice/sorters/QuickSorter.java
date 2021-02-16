package secondmounthlastpractice.sorters;

public class QuickSorter {
    public void quickSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is empty or null.");
        }
        int startPoint = 0;
        int endPoint = array.length - 1;
        quickSort(array, startPoint, endPoint);
    }

    private void quickSort(int[] array, int starPoint, int endPoint) {

        if (starPoint >= endPoint) {
            return;
        }

        int middlePoint = starPoint + (endPoint - starPoint) / 2;
        int flag = array[middlePoint];

        int i = starPoint;
        int j = endPoint;

        while (i <= j) {
            while (array[i] < flag) {
                i++;
            }
            while (array[j] > flag) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (starPoint < j) {
            quickSort(array, starPoint, j);
        }
        if (endPoint > i) {
            quickSort(array, i, endPoint);
        }
    }
}
