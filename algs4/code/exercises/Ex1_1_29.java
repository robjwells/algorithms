import edu.princeton.cs.algs4.*;

class Ex1_1_29 {
    public static void main(String[] args) {
        int[] keys = { 1, 2, 3, 4, 4, 4, 4, 4, 4, 6, 7, 8 };
        int key = 4;
        StdOut.println(BinarySearch.rank(keys, key));
        StdOut.println(BinarySearch.count(keys, key));
    }

    static class BinarySearch {
        static int indexOf(int[] a, int key) {
            return indexOf(a, key, 0, a.length - 1);
        }

        static int indexOf(int[] a, int key, int lo, int hi) {
            if (lo > hi) {
                return -1;
            }
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                return indexOf(a, key, lo, mid - 1);
            } else if (key > a[mid]) {
                return indexOf(a, key, mid + 1, hi);
            } else {
                // Found key
                return mid;
            }
        }

        static int rank(int[] a, int key) {
            return rank(a, key, 0, a.length - 1);
        }

        static int rank(int[] a, int key, int lo, int hi) {
            boolean found = false;
            int mid = lo + (hi - lo) / 2;
            if (lo <= hi) {
                if (key < a[mid]) {
                    return rank(a, key, lo, mid - 1);
                } else if (key > a[mid]) {
                    return rank(a, key, mid + 1, hi);
                } else {
                    found = true;
                }
            }

            if (found) {
                while (mid > 0 && a[mid - 1] == key) {
                    mid--;
                }
                return mid;
            }

            // lo is in the position where the key would be
            // inserted in sorted order; so also the number
            // of elements smaller than the key.
            // For a key smaller than a[0], then lo is 0,
            // for a key greater than a[-1], then lo is a.length
            return lo;
        }

        static int count(int[] a, int key) {
            int keyRank = rank(a, key);
            if (keyRank >= a.length || a[keyRank] != key) {
                return 0;
            }
            int keyCount = 1;
            while (a[keyRank + keyCount] == key) {
                keyCount++;
            }
            return keyCount;
        }
    }
}
