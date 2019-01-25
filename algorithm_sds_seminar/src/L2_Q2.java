import java.util.*;

public class L2_Q2 {
    static ArrayList<Integer>[] graph;

    static ArrayList<Integer> bfs;

    static boolean[] visit;  //이미 방문한 정점의 정보를 담을 배열

    static Queue<Integer> Q;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int testcase = scan.nextInt();


        for (int i = 0; i < testcase; i++) {
            int n = scan.nextInt(); //V
            int m = scan.nextInt(); //E
            int k = scan.nextInt(); //배달해야하는 마을의 수
            int s = scan.nextInt(); //가게가 있는 마을의 번호

            graph = new ArrayList[m + 1];

            for (int j = 1; j <= m; j++) {
                graph[j] = new ArrayList<Integer>();
            }

            int[] deliver = new int[k];

            for (int j = 0; j < k; j++)
                deliver[j] = scan.nextInt();


            for (int j = 0; j < m; j++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                graph[x].add(y);
                graph[y].add(x);
            }

            for (int j = 1; j <= m; j++) {
                Collections.sort(graph[j]);
            }

            bfsSol(deliver, s, m);
//            for (int j = 0; j < bfs.size(); j++) {
//                System.out.print(bfs.get(j) + " ");
//            }
//            System.out.println();
        }
    }

    static void bfsSol(int[] deliver, int start, int e) {
        bfs = new ArrayList<Integer>();
        visit = new boolean[e + 1];
        Q = new LinkedList<Integer>();

        //시작정점을 큐에 넣어줌
        Q.add(start);
        //시작정점을 방문했다는 정보 저장
        visit[start] = true;

        int k = 0;
        //큐에 정점이 없어질 때까지 반복
        while (!Q.isEmpty()) {
            //큐에서 정점을 poll해서 이동함
            int q = Q.poll();
            bfs.add(q);
            int flag = 0;
            int j = 0;
            int temp = 0;
            //이동한 정점에서 연결된 정점들을 큐에 넣어주고 visit배열에 체크
            for(int i : graph[q]) {
                if (i == deliver[k]) {
                    flag = 1;
                    j = i;
                    temp = q;
                    break;
                }
                else {
                    continue;
                }
            }
            if (flag == 1)  {
                System.out.println(j + " " + temp);
                for (int i : graph[temp]) {
                    if (!visit[i]) {
                        System.out.println(i + "발견!!" + k);
                        bfs.add(bfs.size(), i);

                        visit[i] = true;
                        k++;
                    }
                }
            }
            System.out.println();
//            for (int i : graph[q]) {
//                if (i == deliver[k]) {
//                    if (!visit[i]) {
////                        System.out.println(q + "발견!!" + k);
//                        bfs.add(bfs.size(), i);
//                        Q.add(i);
//                        visit[i] = true;
//                        k++;
//                    }
//                }
//
//            }
//            for (int i : graph[q]) {
//                if (!visit[i]) {
//                    Q.add(i);
//                    visit[i] = true;
//                }
//            }
        }
    }


}
