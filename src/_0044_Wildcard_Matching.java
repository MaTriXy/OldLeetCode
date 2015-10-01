
public class _0044_Wildcard_Matching {

/*	Implement wildcard pattern matching with support for '?' and '*'.

	'?' Matches any single character.
	'*' Matches any sequence of characters (including the empty sequence).

	The matching should cover the entire input string (not partial).

	The function prototype should be:
	bool isMatch(const char *s, const char *p)

	Some examples:
	isMatch("aa","a") �� false
	isMatch("aa","aa") �� true
	isMatch("aaa","aa") �� false
	isMatch("aa", "*") �� true
	isMatch("aa", "a*") �� true
	isMatch("ab", "?*") �� true
	isMatch("aab", "c*a*b") �� false*/
	
	class Solution {
	public:
	    bool isMatch(const char * s, const char * p) {
	        int posOfStar = -1;  // p�����һ��*�ŵ�λ��
	        int posOfTail = 0;  // ��s�б�p����*�Ŵ�������ַ����Ĵ�β����һλ
	        int si = 0;
	        int pi = 0;

	        while (s[si] != '\0') {

	            //���ƥ�����˶�����һλ
	            if (p[pi] == '?' || p[pi] == s[si]) {
	                pi++;
	                si++;
	                continue;
	            }

	            //���p�г�����*����һ����posOfStar��˵����*�ſ����ڲ����ѵ�ʱ��ʹ�ã�Ҳ������һ����
	            if (p[pi] == '*') {
	                posOfStar = pi;
	                pi++;  // ͬʱpi�����ƶ�׼���´�ƥ��
	                posOfTail = si;  // ���ʱ��˵����*�Ŵ�������ַ�������Ϊ0
	                continue;
	            }

	            //������ʱ��Ȳ�ƥ�䣬piλ����Ҳû��*��˵����Ͷ��·�ˣ�Ϊ�˾���ƥ��ֻ����֮ǰ���ֹ���*������s�в�ƥ�������
	            if (posOfStar != -1) {
	                pi = posOfStar + 1;  // ���piλ��Ҫ���·��ص�*�ŵ���һλ׼������������ƥ��
	                posOfTail++;  // ��ôs�б�p����*�Ŵ�������ַ�������һλ
	                si = posOfTail;  // ͬʱ��һ��si����ʼ��Ҳ���ƣ����Ƶ���s�б�p����*�Ŵ�������ַ����Ĵ�β����һλ��Ҳ������һ��ƥ��Ŀ�ʼλ
	                continue;
	            }

	            //������е���һ��˵��û���κΰ취ƥ����
	            return false;
	        }

	        while (p[pi] == '*') pi++;  // ��Ϊ����ѭ����s�����ʱ��ͽ����ˣ�����Ҫ���p�е�ʣ��Ԫ�أ�*���Դ���ûԪ�أ���˿�����

	        return p[pi] == '\0';  // ���p�л���ʣ��Ͳ�ƥ����

	    }
	};
	
}
