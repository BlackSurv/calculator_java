public enum RomanNumber {
    I(1), V (5), X (10), L (50),C (100), D (500), M (1000);
    private int arabNum;
    RomanNumber (int arabNum){
        this.arabNum = arabNum;
    }
    public int getArabNum (){
        return arabNum;
    }
}
