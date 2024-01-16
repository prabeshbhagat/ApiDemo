package JavaPrograms100;

public class RemoveSpaces6 {

	public static void main(String[] args) {		
		String S = " geeks  for geeks  ";
		String s1=modify(S);
		System.out.println(s1);
		
		String s2=modify(S);
		System.out.println("s2=="+s2);

	}
	
	static String modify(String S)
    {
		String temp ="";
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == ' ') {
			} else {
				temp = temp + S.charAt(i);
			}
		}
		return temp;
    }
	
	static String modify2(String S)
    {
		return S.replace(" ", "");
    }

}
