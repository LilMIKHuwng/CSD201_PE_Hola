/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {
   Node root;
   BSTree() {root=null;}
   boolean isEmpty() {
       return(root==null);
      }
   void clear() {
       root=null;
      }
   void visit(Node p) {
      System.out.print("p.info: ");
      if(p != null) System.out.println(p.info + " ");
     }
   void fvisit(Node p, RandomAccessFile f) throws Exception {
      if(p != null) f.writeBytes(p.info + " ");
     }
   void breadth(Node p, RandomAccessFile f) throws Exception {
     if(p==null) return;
     Queue q = new Queue();
     q.enqueue(p);Node r;
     while(!q.isEmpty()) {
        r = q.dequeue();
        fvisit(r,f);
        if(r.left!=null) q.enqueue(r.left);
        if(r.right!=null) q.enqueue(r.right);
       }
    }
   void preOrder(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      fvisit(p,f);
      preOrder(p.left,f);
      preOrder(p.right,f);
     }
   void inOrder(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      inOrder(p.left,f);
      fvisit(p,f);
      inOrder(p.right,f);
     }
   void postOrder(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      postOrder(p.left,f);
      postOrder(p.right,f);
      fvisit(p,f);
     }

   void loadData(int k) { //do not edit this function
      String [] a = Lib.readLineToStrArray("data.txt", k);
      int [] b = Lib.readLineToIntArray("data.txt", k+1);
      int [] c = Lib.readLineToIntArray("data.txt", k+2);
      int n = a.length;
      for(int i=0;i<n;i++) insert(a[i],b[i],c[i]);
     }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
  void insert(String xSea, int xSail, int xPaddle) {
    //You should insert here statements to complete this function
        if(xSea.charAt(0)!='B'){
            Boat b = new Boat(xSea, xSail, xPaddle);
            Node n = new Node(b);
            if(root ==null){
                root = n;
            }else{
                Node cur = root;
                Node pre = null;
                while (cur!=null) {
                    if(n.info.sail==cur.info.sail){
                        return;
                    }else
                    if(n.info.sail<cur.info.sail){
                        pre = cur;
                        cur = cur.left;
                    }else{
                        pre = cur;
                        cur = cur.right;
                    }
                }
                if(pre!=null){
                    if(n.info.sail<pre.info.sail){
                        pre.left = n;
                    }else{
                        pre.right = n;
                    }
                }
                
            }
        }

   }

//Do not edit this function. Your task is to complete insert function above only.
  void f1() throws Exception {
    clear();
    loadData(1);
    String fname = "f1.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    breadth(root,f);
    f.writeBytes("\r\n");
    inOrder(root,f);
    f.writeBytes("\r\n");
    f.close();
   }  
  
//=============================================================
 void f2() throws Exception {
    clear();
    loadData(5);
    String fname = "f2.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    breadth(root,f);
    f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
    Node cur = root;    
    if(cur==null) return;
    else{
        Queue q = new Queue();
        q.enqueue(cur);
        Node temp = null;
        while (!q.isEmpty()) {            
            temp = q.dequeue();
            if(temp.info.paddle>5){
                            fvisit(temp, f);
            }
            
            
            
            if(temp.left!=null){
                q.enqueue(temp.left);
            }
            if(temp.right!=null){
                q.enqueue(temp.right);
            }
        }
    }


    //------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }  

 
 
 int count = 0;

    Node preOrderVisit(Node p, RandomAccessFile f) throws Exception {
        if (p == null || count == 4) {
            return p;
        }

        // Tăng giá trị của count sau mỗi lần thăm
        count++;

        // Nếu đã đạt node thứ 4, trả về node
        if (count == 4) {
            return p;
        }

        // Tiếp tục duyệt bên trái và bên phải
        Node leftResult = preOrderVisit(p.left, f);
        Node rightResult = preOrderVisit(p.right, f);

        return p;
    }
 
 
//=============================================================
    
    int countForFind = 0;
    Node first  = null;
    Node second = null;
    Node parent = null;
    void InOrderfind(Node root){
        Node cur = root;
        if(cur ==null ) return;
        InOrderfind(cur.left);
        if(cur.left!=null && cur.right == null){
            count++;
            if(count==1){
                first = cur;
            }else if(count==2){
                second = cur;
                return;
            }
        }
        
        
        InOrderfind(cur.left);
    }
    
    
    
      private void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);

        if (node.left != null) {
            count++;
            if (count == 1) {
                first = node;  // Store the first node with a left child
            } else if (count == 2) {
                second = node; // Store the second node with a left child
                return;        // We found the second node, no need to continue traversal
            }
        }

        inOrderTraversal(node.right);
    }
    
    void deleteByCopy(Node root, Node remove){
        Node parent = null;
        Node cur = root;
        while (cur!=null) {            
            if(cur.info.sail==remove.info.sail){
                break;
            }
            if(remove.info.sail<cur.info.sail){
                parent= cur;
                cur = cur.left;
            }else{
                parent= cur;
                cur = cur.right;
            }
        }
        if(cur==null) return;
        if(cur.left==null && cur.right==null){
            if(cur==root) root =null;
            else if(parent.left==cur){
                parent.left = null;
            }else{
                                parent.right = null;
            }
        }else if(cur.left!=null && cur.right!=null){
            Node sucParent = cur;
            Node suc = cur.left;
            while (suc!=null) {                
                sucParent = suc;
                suc = suc.right;
            }
            cur.info = suc.info;
            if(sucParent == cur){
                cur.left = suc.left;
            }else{
                sucParent.right = cur.left;
            }
        }else{
            Node child = cur.left!=null? cur.left: cur.right;
            if(cur==root){
                root = child; 
            }else if(parent.left==cur){
                parent.left = child;
            }else{
                                parent.right = child;
            }
        }
    }
    
    
  void f3() throws Exception {
    clear();
    loadData(9);
    String fname = "f3.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    breadth(root,f);
    f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
      InOrderfind(root);
      deleteByCopy(root, second);

    //------------------------------------------------------------------------------------
    breadth(root,f);
    f.writeBytes("\r\n");
    f.close();
   }

  
  
  

//=============================================================
 void f4() throws Exception {
    clear();
    loadData(13);;
    String fname = "f4.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    breadth(root,f);
    f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/

     InOrderfind(root);
     Node parent = FindParent(root, second);
     parent.right =  rotateR(second);
     
     
    //------------------------------------------------------------------------------------
    breadth(root,f);
    f.writeBytes("\r\n");
    f.close();
   }
 

 public Node rotateL(Node par) {
        if (par == null) {
            return null;
        }
        if (par.left == null) {
            return null;
        }
        Node p = root;
        Node gr = null;
        while (p != null) {
            if (p == par) {
                break;
            }
            gr = p;
            if (p.info.sail > par.info.sail) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        Node ch = par.right;
        par.right = ch.left;
        ch.left = par;
        if (gr == null) {
            root = ch;
        } else if (gr.left == p) {
            gr.left = ch;
        } else if (gr.right == p) {
            gr.right = ch;
        }
        return ch;
    }
 
 
 
 public Node rotateR(Node par) {
        if (par == null) {
            return null;
        }
        if (par.right == null) {
            return null;
        }
        Node p = root;
        Node gr = null;
        while (p != null) {
            if (p == par) {
                break;
            }
            gr = p;
            if (p.info.sail > par.info.sail) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        Node ch = par.left;
        par.left = ch.right;
        ch.right = par;
        if (gr == null) {
            root = ch;
        } else if (gr.left == p) {
            gr.left = ch;
        } else if (gr.right == p) {
            gr.right = ch;
        }
        return ch;
    }
 
 

 
 
 
    Node rotateLeft(Node k1){
        if(k1.right!=null){
            Node k2 = k1.right;
            k1.left = k2.right;
            k2.right = k1;
            return k2;
        }
        System.out.println("N");
        return null;
    }
    
    Node rotateRight(Node k1){
        if(k1.left!=null){
            Node k2 = k1.left;
            k1.right = k2.left;
            k2.left = k1;
            return k2;
        }
        System.out.println("NB");
        return null;
    }

    Node FindParent(Node root,Node needFind) {
    //You should insert here statements to complete this function
            if(root ==null){
                root = null;
            }else{
                Node cur = root;
                Node pre = null;
                while (cur!=null) {
                    if(cur.left==needFind){
                        return cur;
                    }
                    if(cur.right==needFind){
                                            return cur;
                    }
                    
                    if(needFind.info.sail<cur.info.sail){
                        pre = cur;
                        cur = cur.left;
                    }else{
                        pre = cur;
                        cur = cur.right;
                    }
                }
               
                return null;
            }
         return null;

        }
    
    
    
    


}
    
    
    
 
