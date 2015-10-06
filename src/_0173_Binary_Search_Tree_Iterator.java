
public class _0173_Binary_Search_Tree_Iterator {

/*	Implement an iterator over a binary search tree (BST). Your iterator will be 
 *  initialized with the root node of a BST.

	Calling next() will return the next smallest number in the BST.

	Note: next() and hasNext() should run in average O(1) time and uses O(h) 
	memory, where h is the height of the tree.*/
	
	class BSTIterator {
	public:
	    BSTIterator(TreeNode *root) {
	        while (!s.empty()) s.pop();
	        TreeNode * now = root;
	        while (now) {
	            s.push(now);
	            now = now->left;
	        }
	    }

	    /** @return whether we have a next smallest number */
	    bool hasNext() {
	        return !s.empty();
	    }

	    /** @return the next smallest number */
	    int next() {
	        TreeNode * temp = s.top();
	        s.pop();
	        int ans = temp->val;
	        temp = temp->right;
	        while (temp) {
	            s.push(temp);
	            temp = temp->left;
	        }
	        return ans;
	    }
	    stack<TreeNode *> s;
	};
	
}
