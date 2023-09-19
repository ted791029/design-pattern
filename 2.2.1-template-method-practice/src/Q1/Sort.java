package Q1;

public abstract class Sort {
    protected void sort(int[] k) {
        int n = k.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compare(k[j], k[j + 1])) {
                    int ppp = k[j];
                    k[j] = k[j + 1];
                    k[j + 1] = ppp;
                }
            }
        }
    }

    protected abstract boolean compare(int n1, int n2);
}
