import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2261 {
    //백준알고리즘 분할정복 2261 - 가장 가까운 점
    /**
     * line sweeping
     * x 값이 증가하는 순으로 정렬한다.
     * 두 점(array[0], array[1]) 사이의 거리를 최단 거리라고 가정한다.
     * x축의 차이가 최단 거리보다 크다면 비교할 필요가 없기에 후보자를 걸러준다.
     * y축을 기준으로 정렬된 후보자들을 통해 최단 거리를 갱신하게 된다.
     *후보자들 사이에서 (y - d) ~ (y + d) 영역을 구해야한다.
     *이 과정에서 시간 절약을 위해 이분 탐색을 사용하여 찾게 된다.
     *
     *입력값
     *4
     * 0 0
     * 10 10
     * 0 10
     * 10 0
     *
     * 출력값
     * 100
     * 첫째 줄에 가장 가까운 두 점의 거리의 제곱을 출력한다.
     */
    int[] number = new int[Integer.MAX_VALUE];
    public static int N;
    static ArrayList<Point> list = new ArrayList<Point>();
    static TreeSet<Point> candidate = new TreeSet<Point>(new ComparatorSet());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            list.add(new Point(row, col));

        }

        solve();

    }

    private static void solve() {

        int ans = 0;

        //x축을 기준으로 오름차순 정렬
        Collections.sort(list, new ComparatorDescending());

        //array[0]~array[1] 사이의 거리를 최단거리라고 가정한다.
        candidate.add(list.get(0));
        candidate.add(list.get(1));
        ans = distance(list.get(0), list.get(1));

        int start = 0;
        for (int i = 2; i < N; i++) {
            Point now = list.get(i);

            // x axis
            while (start < i) {
                Point pivot = list.get(start);
                int x = pivot.x - now.x;

                //x축의 차이가 최단거리보다 크다면 후보자를 걸러준다. (비교과정 없이)
                if (x * x > ans) {
                    candidate.remove(pivot);
                    start += 1;
                } else {
                    break;
                }
            }

            //array[0]~array[1] 사이의 거리
            int d = (int) Math.sqrt((double) ans) + 1;

            Point lower_point = new Point(now.y - d, now.y + d);

            SortedSet<Point> lower = candidate.tailSet(lower_point);
            Iterator<Point> it_lower = lower.iterator();

            while (it_lower.hasNext()) {
                Point p = it_lower.next();
                d = distance(now, p);
                if (d < ans) {
                    ans = d;
                }
            }
            candidate.add(list.get(i));
        }
        System.out.println(ans);
    }

    public static int distance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    static class ComparatorDescending implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            if (p1.x < p2.x) {
                return -1;
            } else if (p1.x == p2.x) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    static class ComparatorSet implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            if (p1.y == p2.y) {
                if (p1.x < p2.x) {
                    return -1;
                } else if (p1.x == p2.x) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return p1.y < p2.y ? 1 : -1;
            }
        }
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point p) {
            return x < p.y && y > p.y ? 1 : -1;
        }
    }

}
