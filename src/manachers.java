public class manachers {
    public static void main(String args[]){
        String s = "babad";
        System.out.println(Manachers(s));
    }

    public static String Manachers(String s ){
        if(s.length() < 2){
            return s;
        }

        //第一步 预处理
        String t = "$";
        for (int i = 0; i < s.length(); i++) {
            t += "#" + s.charAt(i);
        }
        t += "#@";

        //第二步 计算数组P,起始索引,最长回文半径
        int n = t.length();
        //建立P的数组
        int[] p = new int[n];
        int id = 0, mx = 0; //id为当前的中心位置 mx为右边的边界
        //最长回文字串的长度
        int maxLength = -1;
        //最长回文字串的长度
        int index = 0;

        for (int i = 1; i < n-1; i++) {
            if(mx > i){
                p[i] = Math.min(p[2 * id - i], mx - i);
            }else{
                p[i] = 1;
            }

            //向左右两边延伸 扩展右边界 比较左右的连个数组 只要相同的p[i]会一直增多 直到不想等
            while (t.charAt(i + p[i]) == t.charAt(i - p[i])){
                p[i]++;
            }
            if(mx < p[i] + i){
                mx = p[i] + i;
                id = i;
            }

            //如果回文子串的长度大于maxLength,则更新maxLength和index的值
            if(maxLength < p[i] - 1){
                maxLength = p[i] - 1;
                index = i;
            }
        }

        //第三步 截取字符串 输出结果
        //起始索引的计算
        int start = (index - maxLength) / 2;
        return s.substring(start, start + maxLength);

    }
}
