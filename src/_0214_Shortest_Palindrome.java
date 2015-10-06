
public class _0214_Shortest_Palindrome {

/*	Given a string S, you are allowed to convert it to a palindrome by adding 
 *  characters in front of it. Find and return the shortest palindrome you can 
 *  find by performing this transformation.

	For example:

	Given "aacecaaa", return "aaacecaaa".

	Given "abcd", return "dcbabcd".*/
	
	//ͨ������һ���ַ���S��������KMP�㷨���Խ���������
	//����S=(�����s)+(һ����������s�г��ֵķ��ţ�����#)+(s�ķ�תrev_s)
	//������KMP�㷨֮�����ǿ��Եõ�һ������p
	//����p�ϵ�ֵΪÿ���ַ���ǰ׺������ֵ(���鿴KMP�㷨)
	//����ֻ��Ҫ��p�е����һ��ֵ����
	//��Ϊ����ʾ��rev_s�����ġ���ԭ���ַ���s��ǰ׺ƥ���ϵ��Ӵ�
	//�������ֻ��Ҫ��ǰk��rev_s�е��ַ���ԭ���ַ�����������
	//����kΪp�����һ��ֵ��ԭ���ַ���s�ĳ��ȵĲ�ֵ

	char * shortestPalindrome(char * s) {

		int length = strlen(s);
		int Length = 2 * length + 1;
		char * rev_s = (char*)malloc(sizeof(char) * (length + 2));
		char * l = (char*)malloc(sizeof(char) * (2 * length + 2));

		for (int i = length - 1; i >= 0; i--) {
			rev_s[i] = s[length - i - 1];
		}
		rev_s[length] = '\0';
		
		for (int i = 0; i < length; i++) l[i] = s[i];
		l[length] = '#';
		for (int i = 0; i < length; i++) l[i + length + 1] = rev_s[i];
		l[2 * length + 1] = '\0';

		int * p = (int*)malloc(sizeof(int) * Length);
		for (int i = 0; i < Length; i++) p[i] = 0;

		for (int i = 1; i < Length; i++) {
			int j = p[i - 1];
			while (j > 0 && l[i] != l[j]) j = p[j - 1];
			p[i] = (j += l[i] == l[j]);
		}

		char * sub_s = (char*)malloc(sizeof(char) * (2 * length - p[Length - 1] + 1));
		for (int i = length - p[Length - 1] - 1; i >= 0; i--) sub_s[i] = rev_s[i];
		for (int i = 0; i < length; i++) sub_s[i + length - p[Length - 1]] = s[i];
		sub_s[2 * length - p[Length - 1]] = '\0';

		free(rev_s);
		free(l);

		return sub_s;
	}
	
}
