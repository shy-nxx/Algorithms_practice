import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Conversation {
    //알파벳 값의 합을 구함
    private static ArrayList<String> list = new ArrayList<String>();
    private static ArrayList<String> outlist = new ArrayList<String>();

    public static void main(String[] args) {
        String message = "";

        File f = new File("/Users/shineeseo");

        if (f.exists() == false) {
            System.out.println("경로가 존재하지 않습니다");
        }

        File[] fileList = f.listFiles();

        int tab = 1;

        for (int i = 0; i < fileList.length; i++) {

            if (fileList[i].isDirectory()) {

                message = "[디렉토리] ";

                message += fileList[i].getName();

                System.out.println(message);

//                if (!fileList[i].getName().startsWith("."))
//                    subDirList(fileList[i].getName(), tab);

//                Collections.reverse(list);
//
//                for (String str : list) {
//
//                    System.out.println(str);
//
//                }
//
//                list.clear();

            } else {

                message = "[파일] ";

                message += fileList[i].getName();

                System.out.println(message);

            }
        }
    }
    /**
     *
     * @param source
     * @param tab
     */
    private static void subDirList(String source, int tab) {

        File dir = new File(source);
        File[] fileList = dir.listFiles();
        String sTab = "";

        for (int i = 0; i < tab; i++) {
            sTab += "\t";
        }

        try {
            for (int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if (file.isFile()) {

                    // 파일이 있다면 파일 이름 출력

                    if (i == 0) {
                        list.add("[디렉토리 이름1] = " + dir.getPath());

                    }

                    list.add(sTab + " 파일 이름 = " + file.getName());

                    fileRead(file);

                } else if (file.isDirectory()) {

                    // 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색

                    subDirList(file.getCanonicalPath().toString(), tab + 1);

                    list.add("[디렉토리 이름2] = " + dir.getPath());

                    // System.out.println("[디렉토리 이름] = " + dir.getPath() );

                }

            }

        } catch (IOException e) {

        }


        fileWrite();

    }
    /**
     * 파일읽시
     * @throws FileNotFoundException
     */

    @SuppressWarnings("unused")
    private static void fileRead(File file)  {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int i = 0;
            while(true) {
                StringBuilder outline = new StringBuilder();
                String line = br.readLine();

                if (line==null) break;
                i++;

                if(line.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
                    // 한글이 포함된 문자열
                    outline.append(file.getAbsolutePath());
                    outline.append(" : ");
                    outline.append(i + " line");
                    outline.append(" : ");
                    outline.append(line);

                    outlist.add(outline.toString());

                    System.out.println(line);

                }
            }

            br.close();

        } catch (IOException e) {

        }
    }

    /**
     * 파일쓰기
     */
    private static void fileWrite() {
        try {
            PrintWriter pw = new PrintWriter("D:/out.txt");

            if (!outlist.isEmpty()) {
                for (String s : outlist) {
                    pw.println(s);
                }
            }
            pw.close();
        } catch (IOException e) {

        }
    }
}

