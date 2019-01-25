public class PrintStar {
    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= i; j++)
                System.out.print("*");
            System.out.println();
        }
        System.out.println();

        for (int i = 3; i >= 1; i--) {
            for (int j = 1; j <= i; j++)
                System.out.print("*");
            System.out.println();
        }
        System.out.println();

        for (int i = 1; i <= 3; i++) {

            for (int j = i; j < 5; j++) {
                System.out.print(" ");
            }
            // 정삼각형의 왼쪽 부분을 담당하는 반복문
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            // 정삼각형의 오른쪽 부분을 담당하는 반복문
            for (int j = 0; j < i - 1; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
        System.out.println();

        for (int i = 1; i <=3; i++) {
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

        for (int i = 1; i <= 3; i++) {

            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }

            for (int j = i-1; j < 3; j++) {
                System.out.print("*");
            }
            for (int j = i ; j < 3; j++) {
                System.out.print("*");
            }


            System.out.println();
        }
    }

}
