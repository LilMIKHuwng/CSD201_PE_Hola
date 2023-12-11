/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)============================================
import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------
public class Graph {
  int [][] a; int n;
  char v[];
  int deg[];
  Graph() {
    v = "ABCDEFGHIJKLMNOP".toCharArray();
    deg = new int[20];
    a = new int[20][20];
    n = 0;
   }

  void loadData(int k) {  //do not edit this function
    RandomAccessFile f;int i,j,x;
    String s;StringTokenizer t;
    a = new int[20][20];
    try {
     f = new RandomAccessFile("data.txt","r");
     for(i=0;i<k;i++) f.readLine();
     s = f.readLine();s = s.trim();
     n = Integer.parseInt(s);
     for(i=0;i<n;i++) {
       s = f.readLine();s = s.trim();
       t = new StringTokenizer(s);
       for(j=0;j<n;j++) { 
         x = Integer.parseInt(t.nextToken().trim());
         a[i][j] = x;
        }
       }
     f.close();
     }
    catch(Exception e) {}

   }

  void dispAdj() {
    int i,j;
    for(i=0;i<n;i++) {
      System.out.println();
      for(j=0;j<n;j++)
        System.out.printf("%4d",a[i][j]);
     }
   }

  void fvisit(int i, RandomAccessFile f) throws Exception {
    f.writeBytes("  "+v[i]);
   }

 void fdispAdj(RandomAccessFile f) throws Exception { 
    int i,j;
    f.writeBytes("n = "+n+"\r\n");
    for(i=0;i<n;i++) {
      f.writeBytes("\r\n");
      for(j=0;j<n;j++)  f.writeBytes("  " + a[i][j]);
     }
    f.writeBytes("\r\n");
   }

  void breadth(boolean [] en, int i, RandomAccessFile f) throws Exception {
    Queue q = new Queue();
    int r,j;
    q.enqueue(i); en[i]=true;
    while(!q.isEmpty()) {
      r = q.dequeue();
      fvisit(r,f);
      for(j=0;j<n;j++) {
        if(!en[j] && a[r][j]>0) {
         q.enqueue(j);en[j]=true;
        }
       }
     }
   }

  void breadth(int  k, RandomAccessFile f) throws Exception {
    boolean [] en = new boolean[20];
    int i;
    for(i=0;i<n;i++) en[i]=false;
    breadth(en,k,f);
    for(i=0;i<n;i++) 
      if(!en[i]) breadth(en,i,f);
   }

 void depth(boolean [] visited,int k, RandomAccessFile f) throws Exception {
    fvisit(k,f);visited[k]=true;
    for(int i=0;i<n;i++) {
      if(!visited[i] && a[k][i]>0) depth(visited,i,f);
     }
   }
  void depth(int k, RandomAccessFile f) throws Exception {
    boolean [] visited = new boolean[20];
    int i;
    for(i=0;i<n;i++) visited[i]=false;
    depth(visited,k,f);
    for(i=0;i<n;i++) 
       if(!visited[i]) depth(visited,i,f);
   }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
  void f1() throws Exception {
    loadData(1);
    String fname = "f1.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    breadth(4,f);
    f.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

      breadth2(4, f);
    //-------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }
  
  void breadth2(int k, RandomAccessFile f) throws Exception {
        Queue q = new Queue();
        int i,h;
        boolean [] enqueued = new boolean[n];
        for(i = 0; i < n; i++) 
            enqueued[i] = false;
        
        q.enqueue(k);
        enqueued[k] = true;
        int count =0;
        while(!q.isEmpty()){
            h = q.dequeue();
            if(count >=1 && count <=5) {
                fvisit(h, f); 
            }
                count++;
            for(i = 0; i < n; i++)
                if(!enqueued[i] && a[h][i] > 0) {
                    q.enqueue(i);
                    enqueued[i] = true;
                }
        }
        
        System.out.println();
    }

//=================================================================================================
  void f2() throws Exception {
    loadData(12);
    String fname = "f2.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    f.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
      // You can use the statement fvisit(i,f); i = 0, 1, 2,...,n-1 to display the vertex i to file f2.txt 
      //  and statement f.writeBytes(" " + k); to write  variable k to the file f2.txt  
      
      findPathDijkstra2(2, 5, f);


    //-------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }
  
  void findPathDijkstra(int start,int des, RandomAccessFile f) throws Exception{
        //initiation
        int check[]=new int[n];  //kiem tra dinh da xet
        int d[]=new int[n];      // khoang cach ngan nhat hien tai
        int pre[]=new int[n];   // luu vet
        ArrayList<Integer> s  = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if(i!=start){
                check[i] = 0;    //dinh i chua xet
                pre[i] = start;      //truoc i la s 
                if (a[start][i] != 99)  d[i] = a[start][i];                    
                else d[i]=Integer.MAX_VALUE;
            }
        }
        d[start]=0;
        pre[start]=-1;
        check[start]=1;
        //routine for setting label
        int count=1;
        do {          
            //find vertex u having d[u] min for setting label
            int min=Integer.MAX_VALUE;
            int u=0;
            for (int i = 0; i < n; i++) {
                if(min>d[i] && check[i]==0){
                    u=i;
                    min=d[i];
                }
            }
            check[u]=1;
            s.add(u); // cap nhat dinh S 
            //update the paths of the vertices adjacent to vertex u these are not labeling
            for (int i = 0; i < n; i++) {
                if (u!=i && check[i] == 0) {
                    if (d[i]>d[u]+a[u][i]) {
                        d[i]=d[u]+a[u][i];
                        pre[i]=u;
                    }
                }
            }
            count++;
        } while (count<=n);
        //export the shortest path from vertex start
      ArrayList<Integer> path=new ArrayList<>();   // duyet theo cai pre suy ra cai duong di 
      path.add(des);
      int j = des;
      do {
          if (pre[j] != -1) {
              j = pre[j];
              path.add(j);
          }
      } while (pre[j] != -1);
      //print the shortest path
      for (int i = path.size()-1; i >=0; i--) {
          fvisit(path.get(i),f);
      }
      f.writeBytes("\n");
    }
  
  void findPathDijkstra2(int start,int des, RandomAccessFile f) throws Exception{
        //initiation
        int check[]=new int[n];  //kiem tra dinh da xet
        int d[]=new int[n];      // khoang cach ngan nhat hien tai
        int pre[]=new int[n];   // luu vet
        ArrayList<Integer> s  = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if(i!=start){
                check[i] = 0;    //dinh i chua xet
                pre[i] = start;      //truoc i la s 
                if (a[start][i] != 99)  d[i] = a[start][i];                    
                else d[i]=Integer.MAX_VALUE;
            }
        }
        d[start]=0;
        pre[start]=-1;
        check[start]=1;
        //routine for setting label
        int count=1;
        do {          
            //find vertex u having d[u] min for setting label
            int min=Integer.MAX_VALUE;
            int u=0;
            for (int i = 0; i < n; i++) {
                if(min>d[i] && check[i]==0){
                    u=i;
                    min=d[i];
                }
            }
            check[u]=1;
            s.add(u); // cap nhat dinh S 
            //update the paths of the vertices adjacent to vertex u these are not labeling
            for (int i = 0; i < n; i++) {
                if (u!=i && check[i] == 0) {
                    if (d[i]>d[u]+a[u][i]) {
                        d[i]=d[u]+a[u][i];
                        pre[i]=u;
                    }
                }
            }
            count++;
        } while (count<=n);
        //export the shortest path from vertex start
      //print the shortest path
      for (int i = s.size() -4; i <s.size()-1;i++) {
          fvisit(s.get(i),f);
      }
      f.writeBytes("\n");
    }

}

















































