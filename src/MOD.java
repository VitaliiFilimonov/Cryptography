public class MOD {
    static int mod(int a, int b){
        // a = b * c + d
        if ((a >= 0 && b > 0) || (a >= 0 && b < 0)){
            return a % b;
        }
        for (int c = 0; c < Math.abs(a); c++){
            if (Math.abs(a) < Math.abs(b) * c){
                int d = Math.abs(b) * c - Math.abs(a);
                return d;
            }
            if (Math.abs(a) == Math.abs(b) * c){
                return 0;
            }
        }
        return 0;
    }
}
