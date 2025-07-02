import java.util.Random;

public class HeapSortTest {

    // Fungsi utama untuk heap sort
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Bangun max heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Ekstrak elemen dari heap satu per satu
        for (int i = n - 1; i > 0; i--) {
            // Tukar elemen root (terbesar) dengan elemen terakhir
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Panggil heapify pada heap yang dikurangi
            heapify(arr, i, 0);
        }
    }

    // Fungsi heapify untuk menjaga properti max heap
    public static void heapify(int[] arr, int n, int i) {
        int largest = i;  // Root
        int left = 2 * i + 1;  // Anak kiri
        int right = 2 * i + 2; // Anak kanan

        // Jika anak kiri lebih besar dari root
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // Jika anak kanan lebih besar dari yang terbesar sejauh ini
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // Jika root bukan yang terbesar, tukar dan lanjutkan heapify
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Rekursif heapify sub-tree yang terpengaruh
            heapify(arr, n, largest);
        }
    }

    // Fungsi untuk mencetak array
    public static void printArray(int[] arr) {
        for (int value : arr)
            System.out.print(value + " ");
        System.out.println();
    }

    // Fungsi untuk generate data acak
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] data = new int[size];
        for (int i = 0; i < size; i++)
            data[i] = rand.nextInt(10000); // 0 - 9999
        return data;
    }

    public static void main(String[] args) {
        // Data kecil (20 data)
        int[] smallData = generateRandomArray(10);
        System.out.println("Data kecil (sebelum sorting):");
        printArray(smallData);

        long startSmall = System.nanoTime();
        heapSort(smallData);
        long endSmall = System.nanoTime();

        System.out.println("Data kecil (sesudah sorting):");
        printArray(smallData);
        System.out.println("Durasi sorting data kecil: " + (endSmall - startSmall) + " nanodetik\n");

        // Data besar (2000 data)
        int[] largeData = generateRandomArray(1000);
        System.out.println("Data besar (sebelum sorting - 20 data pertama):");
        for (int i = 0; i < 20; i++) System.out.print(largeData[i] + " ");
        System.out.println();

        long startLarge = System.nanoTime();
        heapSort(largeData);
        long endLarge = System.nanoTime();

        System.out.println("Data besar (sesudah sorting - 20 data pertama):");
        for (int i = 0; i < 20; i++) System.out.print(largeData[i] + " ");
        System.out.println();

        System.out.println("Durasi sorting data besar: " + (endLarge - startLarge) + " nanodetik");
    }
}
