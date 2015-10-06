
public class _0212_Word_Search_II {

/*	Given a 2D board and a list of words from the dictionary, find all words in 
 *  the board.

	Each word must be constructed from letters of sequentially adjacent cell, 
	where "adjacent" cells are those horizontally or vertically neighboring. The 
	same letter cell may not be used more than once in a word.

	For example,
	Given words = ["oath","pea","eat","rain"] and board =

	[
	  ['o','a','a','n'],
	  ['e','t','a','e'],
	  ['i','h','k','r'],
	  ['i','f','l','v']
	]
	Return ["eat","oath"].
	Note:
	You may assume that all inputs are consist of lowercase letters a-z.*/
	
	struct TrieNode {
		char c;
		// sons for "abcdefghijklmnopqrstuvwxyz\0"
		struct TrieNode * son[27];  
	};

	struct TrieNode * trieCreate() {
		struct TrieNode * trieNode = 
			(struct TrieNode*)malloc(sizeof(struct TrieNode));
		trieNode->c = '\0';
		memset(trieNode->son, 0, sizeof(trieNode->son));
		return trieNode;
	}

	void insert(struct TrieNode * root, char * word) {
		if (*word == '\0') {
			root->son[26] = trieCreate();
			// notice that '\0' is important. There's "abc\0" 
			// in Trie doesn't mean there's a word "ab\0".
			root->son[26]->c = '\0';
			return;
		}
		if (root->son[*word - 'a'] == NULL) {
			root->son[*word - 'a'] = trieCreate();
			root->son[*word - 'a']->c = *word;
			insert(root->son[*word - 'a'], word + 1);
		}
		else {
			insert(root->son[*word - 'a'], word + 1);
		}
	}

	bool search(struct TrieNode * root, char * word) {
		if (*word == '\0') {
			// notice that '\0' is important. There's "abc\0" 
			// in Trie doesn't mean there's a word "ab\0".
			if (root->son[26] != NULL) return true;  
			else return false;
		}
		if (root->son[*word - 'a'] == NULL) {
			return false;
		}
		else {
			return search(root->son[*word - 'a'], word + 1);
		}
	}

	void trieFree(struct TrieNode* root) {
		if (root != NULL) {
			for (int i = 0; i < 26; i++) {
				trieFree(root->son[i]);
			}
			free(root);
		}
	}

	char * AnAns;  // һ����
	char ** Ans;  // һ���
	char ** Board;  // �����board
	bool ** vis;  // �Ƿ���ʹ�
	int AnsSize;  // �𰸵�����
	int WordsSize;  // ����ĵ�������
	int WordMaxLength;  // ����ĵ��ʵ���󳤶�
	int LastWordSize = 0;  // ��һ�ε����뵥������
	int BoardRowSize, BoardColSize;  // board������
	int dir[4][2] = { -1, 0, 1, 0, 0, -1, 0, 1 };  // �ĸ�����
	struct TrieNode * ExistWords, *Root;  // ǰ��Ϊ�Ƿ��Ѿ��ҵ�������Ϊ������Trie

	void dfs(struct TrieNode * nowLevel, int pi, int pj, int p) {
		if (AnsSize >= WordsSize) return;  // ��������Ѿ��ﵽ���
		if (p > WordMaxLength) return;  // ���λ�ó�������ĵ��ʳ���
		if (nowLevel->son[26] != NULL) {  // ����ҵ���ĳ�����ʵĽ�β
			AnAns[p] = '\0';  // ���Ľ�β��
			if (!search(ExistWords, AnAns)) {  // ����������û�б��ҵ���
				insert(ExistWords, AnAns);  // ��ô�������Ϊ�ҵ���
				strcpy(Ans[AnsSize++], AnAns);  // Ȼ��������
			}
		}
		for (int k = 0; k < 4; k++) {
			int gi = pi + dir[k][0];
			int gj = pj + dir[k][1];
			// ����ڷ�Χ�ڡ�û�з��ʹ�����Trie��Ҳ�����������ĸ���Ǿͼ���DFS
			if (0 <= gi && gi < BoardRowSize && 0 <= gj && gj < BoardColSize
				&& !vis[gi][gj] && nowLevel->son[Board[gi][gj] - 'a'] != NULL) {
				vis[gi][gj] = true;
				AnAns[p] = Board[gi][gj];
				dfs(nowLevel->son[Board[gi][gj] - 'a'], gi, gj, p + 1);
				vis[gi][gj] = false;
			}
		}
	}

	char ** findWords(char ** board, int boardRowSize,
		int boardColSize, char ** words, int wordsSize, int * returnSize) {

		Root = trieCreate();  // ����Trie
		ExistWords = trieCreate();  // �������Trie
		Board = board;
		BoardRowSize = boardRowSize;
		BoardColSize = boardColSize;
		WordsSize = wordsSize;
		WordMaxLength = 0;
		AnsSize = 0;

		// �����е��ʲ���Trie
		for (int i = 0; i < wordsSize; i++) {
			int Length = strlen(words[i]);
			if (Length > WordMaxLength) WordMaxLength = Length;
			insert(Root, words[i]);
		}
		// ��ʼ��vis
		vis = (bool**)malloc(sizeof(bool*) * boardRowSize);
		for (int i = 0; i < boardRowSize; i++) {
			vis[i] = (bool*)malloc(sizeof(bool) * (boardColSize));
			for (int j = 0; j < boardColSize; j++) vis[i][j] = false;
		}
		// ����һ��ʹ�õ�Ans����free�������½�һ��
		for (int i = 0; i < LastWordSize; i++) free(Ans[i]);
		free(Ans);
		LastWordSize = WordsSize;
		Ans = (char**)malloc(sizeof(char*) * wordsSize);
		for (int i = 0; i < wordsSize; i++) {
			Ans[i] = (char*)malloc(sizeof(char) * (WordMaxLength + 1));
		}

		AnAns = (char*)malloc(sizeof(char*) * (WordMaxLength + 1));

		for (int i = 0; i < boardRowSize && !(AnsSize >= wordsSize); i++) {
			for (int j = 0; j < boardColSize; j++) {
				if (Root->son[board[i][j] - 'a'] != NULL) {
					vis[i][j] = true;
					AnAns[0] = board[i][j];
					dfs(Root->son[board[i][j] - 'a'], i, j, 1);
					vis[i][j] = false;
					if (AnsSize >= wordsSize) break;
				}
			}
		}
		// free
		for (int i = 0; i < boardRowSize; i++) free(vis[i]);
		free(vis);
		free(AnAns);
		trieFree(Root);
		trieFree(ExistWords);

		*returnSize = AnsSize;
		return Ans;
	}
	
}
