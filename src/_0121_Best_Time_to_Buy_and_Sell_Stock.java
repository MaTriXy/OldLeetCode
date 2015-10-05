
public class _0121_Best_Time_to_Buy_and_Sell_Stock {

/*	Say you have an array for which the ith element is the price of a given 
 *  stock on day i.

	If you were only permitted to complete at most one transaction (ie, buy one 
	and sell one share of the stock), design an algorithm to find the maximum 
	profit.*/
	
	class Solution {
	public:
		int maxProfit(vector<int> &prices) {
			if (prices.size() == 0) return 0;
			int ans = 0, s = prices.size(), m = prices[0];
			for (int i = 0; i < s; i++) {
				if (m > prices[i]) m = prices[i];
				if (prices[i] - m > ans) ans = prices[i] - m;
			}
			return ans;
		}
	};
	
}
