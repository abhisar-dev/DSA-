class Solution {
    static boolean armstrongNumber(int n) {
        // code here
        int  originalNumber = n ;
        int sumofCubes = 0 ;
        
        while (n>0){
            int digit = n % 10 ;
            sumofCubes +=(digit*digit*digit);
            n/=10;
        }
        return sumofCubes == originalNumber;
    }
}