public class Caesar {
    public static void main(String[] args) {
        Caesar cs = new Caesar();
        System.out.println(cs.caesar("qyyigoptvfb", "abcdefghijk", 3));
    }
    private String caesar(String encrypted_text, String key, int rotation) {

        int[] keys = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            int temp = (int)'z' - (int)ch;
            keys[i] = 26 - temp;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < encrypted_text.length(); i++) {
            char ch = encrypted_text.charAt(i);

            int ascii = (int)ch + (-1 * keys[i]);
            System.out.println((int)ch +"" + (-1*keys[i]));
            if(Character.isLowerCase(ch)) {
                if (ascii < (int)'a')
                    ascii += (int)'z' - (int)'a';
                if(!Character.isLowerCase(ascii)) {
                    ascii-=26;
                }
            }else if(Character.isUpperCase(ch)) {
                if (ascii < (int)'A')
                    ascii += (int)'Z' - (int)'A';
                if(!Character.isUpperCase(ascii)) {
                    ascii-=26;
                }
            }
            if(ch!=32) {
                sb.append((char)ascii);
            }else {
                sb.append((char)ch);
            }
        }
        String encrypted = sb.toString();
        String s = encrypted;
        if (rotation > 0) {
            for (int i = 1; i <= rotation; i++) {
                String first = s.substring(0,1);
                String sub = s.substring(1, encrypted.length()-1);
                s = sub + first;


            }
        }
        else {
            for (int i = rotation; i >= 0; i++) {
                String first = s.substring(0,1);
                String sub = s.substring(1, encrypted.length()-1);
                s = first + sub;


            }
        }
        System.out.println(encrypted);
        return s;
    }

}
