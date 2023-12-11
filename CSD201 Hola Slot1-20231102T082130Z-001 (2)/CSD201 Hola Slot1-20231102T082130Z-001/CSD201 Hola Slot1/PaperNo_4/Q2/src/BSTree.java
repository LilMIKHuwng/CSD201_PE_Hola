/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    Node insertTreeDeHieuHonChut(Node root, String xsea, int xsail, int xpaddle) {
        //---------------------------------------------------------------------------------------
        //------ Start your code here------------------------------------------------------------
        Boat x = new Boat(xsea, xsail, xpaddle);
        Node newNode = new Node(x);
        if (root == null) {
            root = newNode;
            return root;
        }
        if (x.sail < root.info.sail) {
            if (root.left == null) {
                root.left = newNode;
            } else {
                insertTreeDeHieuHonChut(root.left, x.sea, x.sail, x.paddle);
            }
        } else if (x.sail > root.info.sail) {
            if (root.right == null) {
                root.right = newNode;
            } else {
                insertTreeDeHieuHonChut(root.right, x.sea, x.sail, x.paddle);
            }
        }
        return root;
        //------ End your code here--------------------------------------------------------------
        //---------------------------------------------------------------------------------------
    }

    void insert(String xSea, int xSail, int xPaddle) {
        if (xSea.charAt(0) == 'B') {
            return;
        } else {
            root = insertTreeDeHieuHonChut(root, xSea, xSail, xPaddle);
        }
    }
//
//  void insert(String xSea, int xSail, int xPaddle) {
//    //You should insert here statements to complete this function
//        if(xSea.charAt(0)!='B'){
//            Boat b = new Boat(xSea, xSail, xPaddle);
//            Node n = new Node(b);
//            if(root ==null){
//                root = n;
//            }else{
//                Node cur = root;
//                Node pre = null;
//                while (cur!=null) {
//                    if(n.info.sail==cur.info.sail){
//                        return;
//                    }else
//                    if(n.info.sail<cur.info.sail){
//                        pre = cur;
//                        cur = cur.left;
//                    }else{
//                        pre = cur;
//                        cur = cur.right;
//                    }
//                }
//                if(pre!=null){
//                    if(n.info.sail<pre.info.sail){
//                        pre.left = n;
//                    }else{
//                        pre.right = n;
//                    }
//                }
//                
//            }
//        }
//
//   }

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        breadth2(root, f);
//        Node cur = root;
//        if (cur == null) {
//            return;
//        } else {
//            Queue q = new Queue();
//            q.enqueue(cur);
//            Node temp = null;
//            while (!q.isEmpty()) {
//                temp = q.dequeue();
//                if (temp.info.paddle > 5) {
//                    fvisit(temp, f);
//                }
//
//                if (temp.left != null) {
//                    q.enqueue(temp.left);
//                }
//                if (temp.right != null) {
//                    q.enqueue(temp.right);
//                }
//            }
//        }

        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    void breadth2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (r.info.paddle > 5) {
                fvisit(r, f);
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    int count = 0; // Khai báo và khởi tạo biến count

    Node findFourthNodeInPreOrder(Node p) {
        if (p == null) {
            return null;
        }
        count++;
        if (count == 4) {
            return p;
        }

        Node leftResult = findFourthNodeInPreOrder(p.left);
        if (leftResult != null) {
            return leftResult;
        }
        return findFourthNodeInPreOrder(p.right);
    }

    Node roateBreadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return null;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (hasChild(r)) {
                count++;
                if (count == 2) {
                    return r;
                }
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
        return null;
    }

    Node findPostOrder(Node p) {
        if (p == null) {
            return null;
        }

        Node leftResult = findPostOrder(p.left);
        if (leftResult != null) {
            return leftResult;
        }

        Node rightResult = findPostOrder(p.right);
        if (rightResult != null) {
            return rightResult;
        }
        count++;
        if (count == 4) {
            return p;
        } else {
            return null;
        }
    }

    Node findFourthNodeInPreeOrder(Node p) {
        if (p == null) {
            return null;
        }
        count++;
        if (count == 4) {
            return p;
        }

        Node leftResult = findFourthNodeInPreOrder(p.left);
        if (leftResult != null) {
            return leftResult;
        }
        return findFourthNodeInPreOrder(p.right);
    }

    Node findLeftNode(Node root) {
        if (root == null) {
            return null;
        }
        Node nodeLeft = root;
        while (nodeLeft.left != null) {
            nodeLeft = nodeLeft.left;
        }
        return nodeLeft;
    }

    Node deleteNode(Node root, String xOwner, int xPrice, int xColor) {
        Boat x = new Boat(xOwner, xPrice, xColor);
        if (root == null) {
            return null;
        }
        if (x.sail < root.info.sail) {
            root.left = deleteNode(root.left, xOwner, xPrice, xColor);
        } else if (x.sail > root.info.sail) {
            root.right = deleteNode(root.right, xOwner, xPrice, xColor);
        } else {
            //TH node cần xóa là node lá
            if (root.right == null && root.left == null) {
                return null;
            }
            //TH node cần xóa chỉ có 1 con bên trái
            if (root.left != null && root.right == null) {
                return root.left;
            }
            //TH node cần xóa chỉ có 1 con bên phải
            if (root.left == null && root.right != null) {
                return root.right;
            }
            //TH node cần xóa có cả 2 con bên phải và bên trái
            //Xóa theo trái cùng của cây con bên phải
            Node leftNode = findLeftNode(root);
            root.info.sail = leftNode.info.sail;
            root.right = deleteNode(root.right, leftNode.info.sea, leftNode.info.sail, leftNode.info.paddle);
        }
        return root;
    }

    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        Node pre = findFourthNodeInPreOrder(root);
        System.out.println(pre.info);
//        Node delete = findSecondNodeWithLeftChild(root);
//        if (delete != null) {
//            root = deleteNode(root, delete.info.sea, delete.info.sail, delete.info.paddle);
////            root = deleteNode(root, deleteNode.info.sea, deleteNode.info.sail, deleteNode.info.paddle);
//        }

        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void deleteByCopying(Node p) {
        Node rightMost = p.left, f = null;
        while (rightMost.right != null) {
            f = rightMost;
            rightMost = rightMost.right;
        }
        p.info = rightMost.info;
        if (f == null) {
            p.left = rightMost.left;
        } else {
            f.right = rightMost.left;
        }
    }

    int countHung = 0;

    Node findSecondNodeWithLeftChild(Node p) {
        //
        if (p == null) {
            return null;
        }
        Node leftResult = findSecondNodeWithLeftChild(p.left);
        //321
        if (leftResult != null) {
            return leftResult;
        }
        if (hasChild(p)) {
            countHung++;
            if (countHung == 2) {
                return p;
            }
        }
        return findSecondNodeWithLeftChild(p.right);
    }

    boolean hasChild(Node p) {
        return p.left != null;
    }
//=============================================================

    Node searchParent(Node a) {
        if (a == null) {
            return null;
        }
        Node p = root, f = null;
        while (p != null && p != a) {
            f = p;
            if (p.info.sail > a.info.sail) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return f;
    }

    Node rotateLeft(Node p) {
        if (p.right == null) {
            return p;
        }
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        Node parent = searchParent(p);
        if (parent != null) {
            if (parent.left == p) {
                parent.left = q;
            } else {
                parent.right = q;
            }
        }
        return q;
    }

    public Node rotateRight(Node p) {
        if (p.left == null) {
            return p;
        }
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        Node parent = searchParent(p);
        if (parent != null) {
            if (parent.left == p) {
                parent.left = q;
            } else {
                parent.right = q;
            }
        }

        return q;
    }

    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
//        Node delete = findSecondNodeWithLeftChild(root);
        Node post = findPostOrder(root);
        if(post != null) {
            System.out.println(post.info);
        }
//        if (delete != null) {
//            rotateRight(delete);
//        }
        //------------------------------------------------------------------------------------
        postOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }
    
    public boolean hasPathSum(Node roott, int targetSum) {
        return duyetCay(roott, 0, targetSum);
    }
    
    public boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

//    public boolean duyetCay(Node curNode, int curSum, int targetSum) {
//        if (curNode == null) {
//            return false;
//        }
//        curSum += curNode.info.rate;
//        if (isLeaf(curNode)) {
//            return curSum == targetSum;
//        }
//
//        boolean ketQuaBenTrai = duyetCay(curNode.left, curSum, targetSum);
//        boolean ketQuaBenPhai = duyetCay(curNode.right, curSum, targetSum);
//        return ketQuaBenTrai || ketQuaBenPhai;
//    }

}
