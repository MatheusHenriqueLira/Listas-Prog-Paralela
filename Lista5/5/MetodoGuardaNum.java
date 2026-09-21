class MetodoGuardaNum {
    private int num = 0;

    public synchronized void somaNum(int outroNum) {
        this.num += outroNum;
        System.out.println("O novo numero eh: " + this.num);
    }
}