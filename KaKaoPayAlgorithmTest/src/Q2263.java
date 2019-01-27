import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2263 {
    //백준 알고리즘 분할정복 2263 - 트리순회
    /*
        입력값
        3
        1 2 3
        1 3 2
     */

    public static int E; //정점의 수
    public static int[] INORDER;
    public static int[] POSTORDER;
    public static int[] inOrderIdx;
    public static StringBuilder sb;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();

        E = Integer.parseInt(st.nextToken());

        INORDER = new int[E + 1];
        POSTORDER = new int[E + 1];
        inOrderIdx = new int[E + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= E; i++) {
            INORDER[i] = (Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= E; i++) {
            POSTORDER[i] = (Integer.parseInt(st.nextToken()));
        }

        // 중위순회에 노드들이 루트일경우 인덱스 정보를 저장
        for(int i=1; i <= E; i++) inOrderIdx[INORDER[i]] = i;
        findPreOrder(1,E, 1,E);
        System.out.println(sb.toString());
    }
    static void findPreOrder(int inO_start,int inO_end, int poO_start,int poO_end) {
        if(inO_start > inO_end || poO_start > poO_end) return;

        // 루트를 구한다. 후위 순회의 마지막 인덱스 poO_end가 루트의 인덱스이다.
        int root = POSTORDER[poO_end];
        //전위 순회- I-L-R
        sb.append(root + " ");

        // 중위 순회에서 루트의 인덱스를 알아온다.
        int rootIdx = inOrderIdx[root];
        // 중위 순회에서 루트 기준 왼쪽에 몇개가 있는지 계산한다.
        int left = rootIdx - inO_start;

        //좌측 자식 노드들을 구한다.
        findPreOrder(inO_start, rootIdx-1, poO_start, poO_start+ left-1);

        // 우측 자식 노드들을 구한다.
        findPreOrder(rootIdx+1, inO_end, poO_start + left, poO_end - 1);

    }
}
