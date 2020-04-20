class WQUPC{
	int arr[],connectedComponents[];

	WQUPC(int n){
		arr = new int[n];
		connectedComponents = new int[n];
		for(int i=0;i<n;i++){
			arr[i] = i;
			connectedComponents[i] = 1;
		}
	}
	
	void union(int p,int q){
		int pid = findRoot(p);
		int qid = findRoot(q);
		if(pid == qid)return;
		if( connectedComponents[pid] > connectedComponents[qid] ){
			arr[qid] = arr[pid];
			connectedComponents[pid] += connectedComponents[qid];
		}
		else{
			arr[pid] = arr[qid];
			connectedComponents[qid] += connectedComponents[pid];
		}
	}
	
	boolean connected(int p,int q){
		return findRoot(p) == findRoot(q);
	}
	
	private int findRoot(int p){
		while(p!=arr[p]){
			arr[p]=arr[arr[p]];
			p = arr[p];
		}
		return p;
	}
}