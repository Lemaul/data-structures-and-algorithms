package finalsHelp;

public class MaxHeap {

    int[] arr;

    public MaxHeap(int[] arr) {
        this.arr = arr;
        adjustHeap();
    }

    private void adjustHeap() {
        for (int i = arr.length/2 -1; i >= 0; i--)
            maxHeapify(i, arr.length);
    }

    private void maxHeapify(int node, int max) {
        if (node >= max) return;
        int largest = node;
        int left = node*2 + 1;
        int right = node*2 + 2;
        if (left < max && arr[left] > arr[node]) largest = left;
        if (right < max && arr[right] > arr[largest]) largest = right;

        if (largest != node) {
            swap(arr, node, largest);
            maxHeapify(largest, max);
        }
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public void heapSort() {
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, i, 0);
            maxHeapify(0, i);
        }
    }

    public String toString() {
        StringBuilder heap = new StringBuilder();
        heap.append('[');
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1) heap.append(arr[i]).append(", ");
            else heap.append(arr[i]).append("]");
        }
        return heap.toString();
    }
}
