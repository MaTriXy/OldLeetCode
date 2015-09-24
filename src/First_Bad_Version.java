
public class First_Bad_Version {

//	You are a product manager and currently leading a team to develop a new 
//	product. Unfortunately, the latest version of your product fails the quality
//	check. Since each version is developed based on the previous version, all 
//	the versions after a bad version are also bad.
//
//	Suppose you have n versions [1, 2, ..., n] and you want to find out the 
//	first bad one, which causes all the following ones to be bad.
//
//	You are given an API bool isBadVersion(version) which will return whether 
//	version is bad. Implement a function to find the first bad version. 
//	You should minimize the number of calls to the API.
	
	public class Solution extends VersionControl {
	    public int firstBadVersion(int n) {
	    	int l = 1, r = n;
	        while (l < r) {
	        	int m = l + (r - l) / 2;
	        	if (isBadVersion(m)) {
	        		r = m;
	        	} else {
	        		l = m + 1;
	        	}
	        }
	        return l;
	    }
	}
	
//	Binary search.
//	Notice that int m = l + (r - l) / 2;
//	When I use int m = (r + l) / 2, there is an WA.
//	Because (r + l) will be overflow.
	
}
