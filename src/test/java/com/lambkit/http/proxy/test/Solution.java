package com.lambkit.http.proxy.test;

public class Solution {
	public static boolean isMatch(String s, String p) {
        int idxs = 0, idxp = 0, idxstar = -1, idxmatch = 0;
        while(idxs < s.length()){
            // 当两个指针指向完全相同的字符时，或者p中遇到的是?时
            if(idxp < p.length() && (s.charAt(idxs) == p.charAt(idxp) || p.charAt(idxp) == '?')){
                idxp++;
                idxs++;
            // 如果字符不同也没有?，但在p中遇到是*时，我们记录下*的位置，但不改变s的指针
            } else if(idxp < p.length() && p.charAt(idxp)=='*'){
                idxstar = idxp;
                idxp++;
                //遇到*后，我们用idxmatch来记录*匹配到的s字符串的位置，和不用*匹配到的s字符串位置相区分
                idxmatch = idxs;
            // 如果字符不同也没有?，p指向的也不是*，但之前已经遇到*的话，我们可以从idxmatch继续匹配任意字符
            } else if(idxstar != -1){
                // 用上一个*来匹配，那我们p的指针也应该退回至上一个*的后面
                idxp = idxstar + 1;
                // 用*匹配到的位置递增
                idxmatch++;
                // s的指针退回至用*匹配到位置
                idxs = idxmatch;
            } else {
                return false;
            }
        }
        // 因为1个*能匹配无限序列，如果p末尾有多个*，我们都要跳过
        while(idxp < p.length() && p.charAt(idxp) == '*'){
            idxp++;
        }
        // 如果p匹配完了，说明匹配成功
        return idxp == p.length();
    }
	
	public static void main(String[] args) {
		test("*", "toto");
        test("toto.java", "tutu.java");
        test("12345", "1234");
        test("1234", "12345");
        test("*f", "");
        test("***", "toto");
        test("*.java", "toto.");
        test("*.java", "toto.jav");
        test("*.java", "toto.java");
        test("abc*", "");
        test("a*c", "abbbbbccccc");
        test("abc*xyz", "abcxxxyz");
        test("*xyz", "abcxxxyz");
        test("abc**xyz", "abcxxxyz");
        test("abc**x", "abcxxx");
        test("*a*b*c**x", "aaabcxxx");
        test("abc*x*yz", "abcxxxyz");
        test("abc*x*yz*", "abcxxxyz");
        test("a*b*c*x*yf*z*", "aabbccxxxeeyffz");
        test("a*b*c*x*yf*zze", "aabbccxxxeeyffz");
        test("a*b*c*x*yf*ze", "aabbccxxxeeyffz");
        test("a*b*c*x*yf*ze", "aabbccxxxeeyfze");
        test("*LogServerInterface*.java", "_LogServerInterfaceImpl.java");
        test("abc*xyz", "abcxyxyz");
        System.out.println("--------------------------------");
        test("/abc/*", "/abc/xyx/yz");
        test("/abc/*", "/abc/xyxyz");
        test("/*", "/abc/xyx/yz");
        test("/abc/xyz", "/abc/xyz/yx");
        test("/abc/xyz", "/abc/xyxyz");
  }

  private static void test(String pattern, String str) {
      System.out.println("[" + pattern+"],[" + str + "] =>> " + isMatch(str, pattern));        
  }
}
