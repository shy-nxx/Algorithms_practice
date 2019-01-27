import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2263_2 {
    public static int E;
    public static int[] INORDER;
    public static int[] POSTORDER;
    public static int[] INORDER_IDX;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        E = Integer.parseInt(st.nextToken());

        INORDER = new int[E + 1];
        POSTORDER = new int[E + 1];
        INORDER_IDX = new int[E + 1];

        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= E; i++) {
            INORDER[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= E; i++) {
            POSTORDER[i] = Integer.parseInt(st.nextToken());
        }

        //중위순회의 인덱스 값을 저장
        for (int i = 1; i <= E; i++) {
            INORDER_IDX[INORDER[i]] = i;
        }

        findPreOrder(1, E, 1, E);

        System.out.println(sb.toString());

    }
    static void findPreOrder(int inOStart, int inOEnd, int poOStart, int poOEnd) {
        if (inOStart > inOEnd || poOStart > poOEnd)
            return;

        int root = POSTORDER[poOEnd];
        sb.append(root + " ");

        //중위순회 인덱스 값에서 루트 인덱스의 순서를 알아온다.
        int rootIdx = INORDER_IDX[root];
        //왼쪽 바디
        int left = rootIdx - inOStart;

        findPreOrder(inOStart, rootIdx -1, poOStart, poOStart+left -1 );
        findPreOrder(rootIdx+1, inOEnd, poOStart + left, poOEnd-1);
    }
}
