// More work is required
// Still some components do not go through recursive method and do not change 
import java.util.Scanner;
public class PercolationBacktracing {
    int arr[][], gridSize=0, openSites=0,path[][],temp[][];
    public PercolationBacktracing(int n){
        arr = new int[n][n];
        gridSize=n;
        temp = new int[n][n];
        path = new int[n][n];
        int k=0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                arr[i][j] = k;
                path[i][j] = 1;
                temp[i][j] = k;
                k++;
            }
        }}
    public void open(int row, int col){
        if(arr[row][col] != -2 && arr[row][col] != -1){
            if(row==0){
                arr[row][col]=-1;
            }
            else{
                arr[row][col] = -2;
            }
            openSites++;
        }
        else{
            return;
        }
        path[row][col]=0;
        if (row==0) {
            connectForFull(row,col);
        }
        else{
            connectForOpen(row,col);
        }
        path[row][col]=1;
        
    }
    public void connectForFull(int row,int col){
        try{
            if(isOpen(row-1,col)&& path[row-1][col]==1){
                arr[row-1][col] = -1;
                path[row-1][col]=0;
                connectForFull(row-1,col);
                path[row-1][col]=1;
            }
        }catch(Exception e){}

        try{
            if(isOpen(row,col-1)&& path[row][col-1]==1){
                arr[row][col-1] = -1;
                path[row][col-1]=0;
                connectForFull(row,col-1);
                path[row][col-1]=1;
            }
        }catch(Exception e){}

        try{
            if(isOpen(row+1,col)&& path[row+1][col]==1){
                arr[row+1][col] = -1;
                path[row+1][col]=0;
                connectForFull(row+1,col);
                path[row+1][col]=1;
            }
        }catch(Exception e){}

        try{
            if(isOpen(row,col+1)&& path[row][col+1]==1){
                arr[row][col+1] = -1;
                path[row][col+1]=0;
                connectForFull(row,col+1);
                path[row][col+1]=1;
            }
        }catch(Exception e){}

    }

    public void connectForOpen(int row,int col){
        try{
            if((isOpen(row-1,col) || (isFull(row-1,col)) )&& path[row-1][col]==1){
                if(isFull(row-1,col)){
                    arr[row][col] = -1;
                    path[row-1][col]=0;
                    connectForFull(row-1,col);    // return;
                    path[row-1][col]=1;
                }
                path[row-1][col]=0;
                connectForOpen(row-1,col);
                path[row-1][col]=1;
            }
        }catch(Exception e){}

        try{
            if((isOpen(row,col-1) || (isFull(row,col-1)) )&& path[row][col-1]==1){
                if(isFull(row,col-1)){
                    arr[row][col] = -1;
                    path[row][col-1]=0;
                    connectForFull(row,col-1);    // return;
                    path[row][col-1]=1;
                }
                path[row][col-1]=0;
                connectForOpen(row,col-1);
                path[row][col-1]=1;
            }
        }catch(Exception e){}
            
        try{
            if((isOpen(row+1,col) || (isFull(row+1,col)) )&& path[row+1][col]==1){
                if(isFull(row+1,col)){
                    arr[row][col] = -1;
                    path[row+1][col]=0;
                    connectForFull(row+1,col);
                    path[row+1][col]=1;
                }
                path[row+1][col]=0;
                connectForOpen(row+1,col);
                path[row+1][col]=1;
            }                
        }catch(Exception e){}
        
        try{
            if((isOpen(row,col+1) || (isFull(row,col+1)) )&& path[row][col+1]==1){
                if(isFull(row,col+1)){
                    arr[row][col+1] = -1;
                    path[row][col+1]=0;// return;
                    connectForOpen(row,col+1);
                    path[row][col+1]=1;
                }
                path[row][col+1]=0;
                connectForOpen(row,col+1);
                path[row][col+1]=1;
            }
        }catch(Exception e){}
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        try{    
            if(arr[row][col] == -2 || arr[row][col]==-1)
            return true;
        }catch(Exception e){}
        return false;
    }

    // is the site connected to the top??
    public boolean isFull(int row, int col){
        if(arr[row][col] == -1)        
        return true;
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSites;
    }

    // does the system percolate?
    public boolean percolates(){
        for(int i=0;i<gridSize;i++){
            if(arr[gridSize-1][i]==-1){
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int inp = sc.nextInt();

        PercolationBacktracing per = new PercolationBacktracing(inp);
        int n=inp;
        while(!per.percolates()){
            int randomSite = ( ( int ) ( ( Math.random() * inp*inp ) - 1 ) ) + 1;
            int row = -1,col=-1;
            for(int i=0;i<n;i++){
                for (int j=0; j<n; j++) {
                    if(per.temp[i][j] == randomSite){
                        row = i;
                        col = j;
                        break;
                    }
                }
                if(row!=-1){
                    break;
                }
            }
            System.out.println("row = " +row + ", col =" + col);
            if(!per.isOpen(row,col)){
                per.open(row,col);
            }    
        }
            for (int i=0; i<n; i++) {
                if(per.arr[0][i]!=-1)
                System.out.print(per.arr[0][i]+"  ");
                else
                System.out.print(per.arr[0][i]+" ");
            }
            System.out.println();
            for(int i=1;i<n;i++){
                for (int j=0; j<n; j++) {
                    System.out.print(per.arr[i][j]+" ");
                }
                System.out.println();
            }
            for(int i=0;i<n;i++){
                for (int j=0; j<n; j++) {
                    System.out.print(per.path[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println(per.numberOfOpenSites());
    }
}