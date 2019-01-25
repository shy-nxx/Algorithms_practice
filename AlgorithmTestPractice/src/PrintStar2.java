public class PrintStar2 {
    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            for (int j = i; j < 3; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < i-1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println();

        for (int i =1; i <=3; i++) {
            for (int j = 1; j < i; j++ ){
                System.out.print(" ");
            }
            for (int j = i; j < 3; j++) {
                System.out.print("*");
            }
            for (int j = i-1; j < 3; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
