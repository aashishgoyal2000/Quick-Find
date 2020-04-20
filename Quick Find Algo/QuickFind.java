// quick-find eager approach

class QuickFind{
	int arr[];
	QuickFind(int n){ // takes o(n) times to initialize the array
		arr = new int[n];
		for(int i=0;i<n;i++){
			arr[i]=i;
		}
	}
	// To check the connection between two components
	public boolean connected(int p,int q){// takes o(1) time to return weather the two poits are connected or not
		return arr[p]==arr[q]; 
	}
	// To merge components p and q, change all ids equal to arr[p] and arr[q]
	public void union(int p,int q){ // takes o(n)+o(2) time to connect two points.
		if(arr[p]==arr[q]){
			return;
		}
		int pid = arr[p];
		int qid = arr[q];
		for(int i=0;i<arr.length;i++){
			arr[q]=pid;
		}
	}
}


// Too expensive as o(n*n) time to process n union commands on n objects.