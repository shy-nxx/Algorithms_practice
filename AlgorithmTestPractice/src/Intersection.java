public class Intersection {
    //교집합
    public static void main(String[] args) {
        int[] first = {3,4,5,3};
        int[] second = {4,2,6,9,5};
        int[] intersection = new int[first.length + second.length];

        int k = 0;

        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < second.length; j++) {
                if (first[i] == second[j])
                    intersection[k++] = first[i];
            }
        }

        for (int i = 0; i < intersection.length; i++) {
            if (intersection[i] == 0)
                continue;
            System.out.println(intersection[i]);

        }
    }
}
