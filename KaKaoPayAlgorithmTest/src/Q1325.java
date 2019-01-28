import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1325 {
    /**
     * 백준알고리즘 그래프 1325 - 해킹
     * 입력값
     * 5 4
     * 3 1
     * 3 2
     * 4 3
     * 5 3
     * 출력값
     * 1 2
     *
     */
    public static int N, M;
    public static ArrayList<Integer> matrix[];
    public static boolean[] visited;
    public static int res[] = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new ArrayList[N + 1];


        for (int i = 1; i <= N; i++ ){
            matrix[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));

        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            DFS(i);
        }

        int max = 0;
        for(int i =1 ; i <= N ; i++){
            if(max <res[i]){
                max = res[i];
            }
        }
        for(int i = 1 ; i <= N ; i++){
            if(res[i]==max){
                System.out.print(i+" ");
            }
        }
    }
    static void DFS(int k) {
        visited[k]=true;
        for(int vv : matrix[k]){
            if(!visited[vv]){
                DFS(vv);
                res[vv]++;
            }
        }
    }


}
