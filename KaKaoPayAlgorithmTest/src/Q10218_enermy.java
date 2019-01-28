import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q10218_enermy {
    public static int N, T;
    public static ArrayList<Enermies> enermyInfo;
    public static boolean[] visitied;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= T; i++) {
            enermyInfo = new ArrayList<>();
            int result = 0;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            visitied = new boolean[N];

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                enermyInfo.add(new Enermies(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
                visitied[j] = false;
            }

            for (int j = 0; j < N; j++) {
               if (!visitied[j]) {
                   BFS(j);
                   result ++;
               }
            }
            sb.append(result + "\n");
        }

        System.out.println(sb.toString());

    }
    static void BFS(int k) {
        Queue<Enermies> q = new LinkedList<>();

        q.add(enermyInfo.get(k));
        visitied[k] = true;

        while (!q.isEmpty()) {
            Enermies e = q.poll();

            for(int i = 0; i < enermyInfo.size(); i++) {
                if (!visitied[i] && isConnected(enermyInfo.get(i), e)) {
                    q.add(enermyInfo.get(i));
                    visitied[i] = true;
                }
            }
        }
    }
    static boolean isConnected(Enermies e1, Enermies e2) {
        return Math.pow(e1.getR() + e2.getR(), 2) >= Math.pow(e2.getX() - e1.getX(), 2) + Math.pow(e2.getY() - e1.getY(), 2);
    }
    static class Enermies {
        private int x;
        private int y;
        private int r;

        public Enermies(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }
    }
}
