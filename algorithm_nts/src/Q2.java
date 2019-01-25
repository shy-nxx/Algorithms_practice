import java.util.StringTokenizer;

public class Q2 {
    public static void main(String[] args) {
        System.out.println(solution(11010));
    }
    public static String solution(int num) {
        String[] ones1 = {"","일","이","삼","사","오","육","칠","팔","구"};
        String[] twice2 = {"","십","백","천"};
        String[] thrid3 = {"","만","억","조","경"};

        String money = String.valueOf(num);
        StringBuffer result = new StringBuffer();
        int len = money.length();
        for(int i=len-1; i>=0; i--){
            if (Integer.parseInt(money.substring(len-i-1, len-i)) != 1)
                result.append(ones1[Integer.parseInt(money.substring(len-i-1, len-i))]);
            if(Integer.parseInt(money.substring(len-i-1, len-i)) > 0)
                result.append(twice2[i%4]);
            if(i%4 == 0)
                result.append(thrid3[i/4]);

        }

        String answer = result.toString();
        return answer;
    }
    public static String convertHangul(String money){
        String[] ones1 = {"","일","이","삼","사","오","육","칠","팔","구"};
        String[] twice2 = {"","십","백","천"};
        String[] thrid3 = {"","만","억","조","경"};

        StringBuffer result = new StringBuffer();
        int len = money.length();
        for(int i=len-1; i>=0; i--){
            result.append(ones1[Integer.parseInt(money.substring(len-i-1, len-i))]);
            if(Integer.parseInt(money.substring(len-i-1, len-i)) > 0)
                result.append(twice2[i%4]);
            if(i%4 == 0)
                result.append(thrid3[i/4]);
        }

        return result.toString();
    }
    public static long hangulToNum(String input){
        long result = 0;
        long tmpResult =0;
        long num = 0;

        final String NUMBER="영일이삼사오육칠팔구";
        final String UNIT= "십백천만억조";
        final long[] UNIT_NUM = {
                10,100,1000,10000,(long)Math.pow(10, 8),(long)Math.pow(10,12)
        };

        StringTokenizer st = new StringTokenizer(input,UNIT,true);//단위
        while(st.hasMoreTokens()){//삼,*,*,삼,*,*
            String token =st.nextToken();
            //숫자인지 단위(UNIT)인지 확인한다.
            int check =NUMBER.indexOf(token);//1)삼 ->3 2)십 ->-1
            System.out.println("CHECK:"+check);

            if(check==-1){//단위인경우
                if("만억조".indexOf(token)==-1){//만억조가 아니면 3)만
                    tmpResult+=(num!=0?num:1)*UNIT_NUM[UNIT.indexOf(token)];//num=30 * 10000
                }else{
                    //만,억,조 경우 ->result
                    tmpResult +=num;
                    result +=(tmpResult!=0?tmpResult:1)*UNIT_NUM[UNIT.indexOf(token)];
                    tmpResult = 0;
                }
                num = 0;
            }else{//숫자
                num = check;
            }
        }
        System.out.println("result:"+result);
        System.out.println("tmpResult:"+tmpResult);
        System.out.println("num:"+num);

        return result +tmpResult + num;
    }
}
