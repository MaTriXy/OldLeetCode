
public class _0224_Basic_Calculator {

/*	Implement a basic calculator to evaluate a simple expression string.

	The expression string may contain open ( and closing parentheses ), the 
	plus + or minus sign -, non-negative integers and empty spaces .

	You may assume that the given expression is always valid.

	Some examples:
	"1 + 1" = 2
	" 2-1 + 2 " = 3
	"(1+(4+5+2)-3)+(6+8)" = 23*/
	
	class ExpressionTransformation {
	public:
	    string trans_to_postfix_expression_to_s(string);  // ���õ��ı��ʽת��Ϊ��׺���ʽ
	    long long int calculate_from_postfix_expression();  // ���ݺ�׺���ʽ����ֵ

	private:
	    vector<string> ans_vector_post;  // ��ź�׺���ʽ
	    string post_string;  // ��ź�׺���ʽ
	};

	inline int prior(char op) {  // �������ȼ�����
	    if (op == '+' || op == '-') {
	        return 1;
	    }
	    else if (op == '*' || op == '/' || op == '%') {
	        return 2;
	    }
	    else {
	        return 0;
	    }
	}

	long long int string_to_int(string in) {  // ��������ַ���ת��Ϊ��Ӧ���ֺ���
	    char s[50];
	    for (int i = 0; i < 50; i++) {
	        s[i] = '\0';
	    }
	    for (int i = 0; i < in.size(); i++) {
	        s[i] = in[i];
	    }
	    long long int ans;
	    sscanf(s, "%lld", &ans);
	    return ans;
	}

	string deleteBlank(string in) {
	    string ans;
	    int size = in.size();
	    for (int i = 0; i < size; i++) {
	        if (in[i] != ' ') ans.push_back(in[i]);
	    }
	    return ans;
	}

	string ExpressionTransformation::trans_to_postfix_expression_to_s(string in) {

	    stack<char> op;  // ������ջ
	    ans_vector_post.clear();  // ��׺���ʽ�������
	    for (int i = 0; i < in.size();) {
	        char c = in[i];
	        if ('0' <= c && c <= '9') {  // ������ֱ�Ӳ���
	            string num;
	            int j;
	            for (j = i; j < in.size() && '0' <= in[j] && in[j] <= '9'; j++) {
	                num.push_back(in[j]);
	            }
	            ans_vector_post.push_back(num);
	            i = j;
	        }
	        else {
	            if (c == '(') {  // �ǿ�����ֱ�Ӳ���
	                op.push('(');
	            }
	            else {
	                if (c == ')') {  // �Ǳ����žͰ�ԭ��ջ�е�������������ֱ�����������ţ�ע�⿪����Ҫ����
	                    while (op.top() != '(') {
	                        string temp;
	                        temp.push_back(op.top());
	                        ans_vector_post.push_back(temp);
	                        op.pop();
	                    }
	                    op.pop();
	                }
	                else {  // �����ǼӼ��˳�ȡ��
	                    if (op.empty()) {  // ������ջ�ǿվ�ֱ�Ӳ���
	                        op.push(c);
	                    }
	                    else {  // ���ɨ�赽����������ȼ�����ջ��������򣬰������ѹ��ջ������Ļ��������ΰ�ջ������������ӵ�����ans��ĩβ��ֱ���������ȼ�����ɨ�赽���������ջ�գ����Ұ�ɨ�赽�������ѹ��ջ��
	                        if (prior(c) > prior(op.top())) {
	                            op.push(c);
	                        }
	                        else {
	                            while (!op.empty() && prior(c) <= prior(op.top())) {
	                                string temp;
	                                temp.push_back(op.top());
	                                ans_vector_post.push_back(temp);
	                                op.pop();
	                            }
	                            op.push(c);
	                        }
	                    }
	                }
	            }
	            i++;
	        }
	    }
	    while (!op.empty()) {  // ע��Ѳ�����ջ�е�ʣ����������
	        string temp;
	        temp.push_back(op.top());
	        ans_vector_post.push_back(temp);
	        op.pop();
	    }

	    post_string.clear();  // ����string������
	    for (int i = 0; i < ans_vector_post.size(); i++) {
	        post_string += ans_vector_post[i];
	    }

	    return post_string;
	}

	long long int ExpressionTransformation::calculate_from_postfix_expression() {

	    //����ջ�Ժ�׺���ʽ��ֵ��ֱ�ӴӺ�׺���ʽ��������ɨ�裬�������ַ���ջ�У������ַ��Ͱ�ջ�������������ó����㣬Ȼ���ٷŽ�ջ

	    stack<long long int> ans_post;
	    for (int i = 0; i < ans_vector_post.size(); i++) {
	        long long int x, y;
	        if ('0' <= ans_vector_post[i][0] && ans_vector_post[i][0] <= '9') {
	            ans_post.push(string_to_int(ans_vector_post[i]));
	        }
	        else {
	            y = ans_post.top();  // ע��˳������ñ�xy+����x+y
	            ans_post.pop();
	            x = ans_post.top();
	            ans_post.pop();
	            if (ans_vector_post[i][0] == '+') {
	                ans_post.push(x + y);
	            }
	            else if (ans_vector_post[i][0] == '-') {
	                ans_post.push(x - y);
	            }
	            else if (ans_vector_post[i][0] == '*') {
	                ans_post.push(x * y);
	            }
	            else if (ans_vector_post[i][0] == '/') {
	                ans_post.push(x / y);
	            }
	            else {
	                ans_post.push(x % y);
	            }
	        }
	    }
	    return ans_post.top();
	}

	class Solution {
	public:
	    int calculate(string s) {
	        ExpressionTransformation e;
	        s = deleteBlank(s);
	        e.trans_to_postfix_expression_to_s(s);
	        int postAns = e.calculate_from_postfix_expression();
	        return postAns;
	    }
	};
	
}
