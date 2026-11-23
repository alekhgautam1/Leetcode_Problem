class ProductOfNumbers {
    private final int[] prefix;
    private int size;

    public ProductOfNumbers() {
        prefix = new int[40001];
        prefix[0] = 1;
        size = 1;
    }
    
    public void add(int num) {
        if (num == 0) {
            size = 1;
        } else {
            prefix[size] = prefix[size - 1] * num;
            size++;
        }
    }
    
    public int getProduct(int k) {
        if (k >= size) {
            return 0;
        }
        return prefix[size - 1] / prefix[size - 1 - k];
    }
}