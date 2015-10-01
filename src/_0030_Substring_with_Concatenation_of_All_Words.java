
public class _0030_Substring_with_Concatenation_of_All_Words {

/*	You are given a string, s, and a list of words, words, that are all of the 
 *  same length. Find all starting indices of substring(s) in s that is a 
 *  concatenation of each word in words exactly once and without any intervening 
 *  characters.

	For example, given:
	s: "barfoothefoobarman"
	words: ["foo", "bar"]

	You should return the indices: [0,9].
	(order does not matter).*/
	
	class Solution {
	public:
	    map<string, int> shouldBe;  // L��stringӦ�е�����
	    map<string, int> now;  // ��ǰ������string������
	    vector<int> findSubstring(string S, vector<string> &L) {
	        vector<int> ans;
	        for (int i = 0; i < L.size(); i++) {
	            if (shouldBe.find(L[i]) != shouldBe.end()) {
	                shouldBe[L[i]]++;
	            } else {
	                shouldBe[L[i]] = 1;
	            }
	        }
	        int alength = L[0].size();  // L��ÿ��string�ĳ���
	        int sNum, nNum;  // S��ĳ��string�ĸ�����now�ж�Ӧstring�ĸ���
	        int startPos;  // ����������ĳ��substr�����

	        for (int q = 0; q < alength; q++) {
	            now.clear();
	            for (int i = q; i + alength <= S.size(); i += alength) {

	                if (now.empty()) startPos = i;  // ���now�ǿյ�˵�����µĿ�ʼ

	                string temp = S.substr(i, alength);

	                if (shouldBe.find(temp) != shouldBe.end()) sNum = shouldBe[temp];  // �ж����ڵ�temp�Ƿ���S��
	                else sNum = 0;

	                if (sNum == 0) {  // ����������now��׼����һ���µĿ�ʼ
	                    now.clear();
	                } else {  // �����S��
	                    if (now.find(temp) == now.end()) {  // �������now��
	                        now[temp] = 1;
	                        nNum = 1;
	                    } else {  // �����now��
	                        nNum = ++now[temp];
	                    }
	                    if (nNum > sNum) {  // ���now�е�temp�����Ѿ������S�У���ô�����¿�ʼ��ע��i������Ӧ�ǵ�ǰstartPos����һλ
	                        now.clear();
	                        i = startPos;
	                    }
	                    if (now == shouldBe) {  // ���ƥ��ɹ�
	                        ans.push_back(startPos);
	                        i = startPos;
	                        now.clear();
	                    }
	                }
	            }
	        }
	        return ans;
	    }
	};
	
}
