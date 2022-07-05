public class NOD {
    static int nod(int a, int b){
        while(a != 0 && b != 0){
            if (Math.abs(a) > Math.abs(b)){
                a = Math.abs(a) % Math.abs(b);
            }else {
                b = Math.abs(b) % Math.abs(a);
            }
        }
        return a + b;
    }
}
