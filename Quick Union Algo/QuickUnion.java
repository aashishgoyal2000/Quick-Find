// Quick Union Lazy Approach
// Tree like structure is achieved in this;

class QuickUnion{
	int arr[];
	QuickUnion(int n){
		arr = new int[n];
		for(int i=0;i<n;i++){
			arr[i] = i;
		}
	}
	void union(int p,int q){
		int pid = findRoot(p);
		int qid = findRoot(q);
		arr[pid] = qid;
	}
	boolean connected(int p,int q){
		int pid = findRoot(p);
		int qid = findRoot(q);
		return pid==qid;
	}
	private int findRoot(int p){
		while(p!=arr[p]){
			p = arr[p];
		}
		return p;
	}
}

// But still time expensive 
// as tree can get very tall