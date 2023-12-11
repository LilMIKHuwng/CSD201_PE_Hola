/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

    int size() {
        int size = 0;
        Node p = head;
        while (p != null) {
            size++;
            p = p.next;
        }
        return size;
    }
//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    void addLast(String xSea, int xSail, int xPaddle) {
        //You should write here appropriate statements to complete this function.
        if (xSea.charAt(0) != 'A') {
            Boat b = new Boat(xSea, xSail, xPaddle);
            Node n = new Node(b);
            if (head == null) {
                head = tail = n;
            } else {
                tail.next = n;
                tail = n;
            }
        }
    }

    //You do not need to edit this function. Your task is to complete the addLast function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

//==================================================================
    public void insertPositionK(Boat x, int position) {
        if (isEmpty()) {
            head = tail = new Node(x);
        }
        int count = 1;
        Node p = head;
        while (p != null && count < position) {
            p = p.next;
            count++;
        }
        Node temp = p.next;
        p.next = new Node(x, temp);
    }

    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Boat x, y, z;
        x = new Boat("X", 1, 2);
        y = new Boat("Y", 2, 3);
        z = new Boat("Z", 3, 4);
        if (size() >= 3) {
            //------------------------------------------------------------------------------------
            /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
            insertPositionK(x, size());
            insertPositionK(y, size() - 2);
            insertPositionK(z, 4);
        }

//        int index = 0;
//        int posA = 1;
//        int posB = 3;
//        int posC = 4;
//        Node cur = head;
//        Node pre = null;
//        while (cur != null) {
//            if (posA == index) {
//                if (pre != null) {
//                    pre.next = a;
//                    a.next = cur;
//                    break;
//                }
//            }
//            index++;
//            pre = cur;
//            cur = cur.next;
//        }
//
//        index = 0;
//        cur = head;
//        pre = null;
//        while (cur != null) {
//            if (posB == index) {
//                if (pre != null) {
//                    pre.next = b;
//                    b.next = cur;
//                    break;
//                }
//            }
//            index++;
//            pre = cur;
//            cur = cur.next;
//        }
//
//        index = 0;
//        cur = head;
//        pre = null;
//        while (cur != null) {
//            if (posC == index) {
//                if (pre != null) {
//                    pre.next = c;
//                    c.next = cur;
//                    break;
//                }
//            }
//            index++;
//            pre = cur;
//            cur = cur.next;
//        }
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    Node findMax() {
        Node max = head;
        Node p = head.next;
        int count = 0;
        if (!isEmpty()) {
            while (p != null) {
                if (p.info.paddle >= max.info.paddle) {
                    max = p;
                }
                p = p.next;
            }
            p = head;
            while (p != null) {
                if (p.info.paddle == max.info.paddle) {
                    count++;
                }
                p = p.next;
            }
        }
        if (count != 1) {
            return max;
        }
        return null;
    }
//==================================================================

    public void dele(Node q) {
        Node f, p;
        f = null;
        p = head;
        while (p != null) {
            if (p == q) {
                break;
            }
            f = p;
            p = p.next;
        }
        if (p == null) {
            return;
        }
        if (f == null) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return;
        }
        f.next = p.next;
        if (f.next == null) {
            tail = f;
        }
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
        ftraverse(f);

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        Node max = findMax();
        if (max != null) {
            dele(max);
            addLast(max.info.sea, max.info.sail, max.info.paddle);
        }
//        int max = Integer.MIN_VALUE;
//        Node cur = head;
//        while (cur != null) {
//            if (max < cur.info.paddle) {
//                max = cur.info.paddle;
//            }
//            cur = cur.next;
//        }
//
//        int index = 0;
//        Node temp = null;
//        cur = head;
//        while (cur != null) {
//
//            if (max == cur.info.paddle && index == 1) {
//                temp = cur;
//            }
//
//            if (max == cur.info.paddle) {
//                index++;
//            }
//
//            cur = cur.next;
//        }
//        if (temp == null) {
//            return;
//        } else {
//            cur = head;
//            Node pre = null;
//            while (cur != null) {
//
//                if (cur.info.sea.equalsIgnoreCase(temp.info.sea)) {
//                    if (pre != null) {
//                        pre.next = cur.next;
//                    }
//                    cur = cur.next;
//                }
//
//                pre = cur;
//                cur = cur.next;
//            }
//            tail.next = temp;
//            tail = temp;
//            tail.next = null;
//
//        }
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    Node get(int x) {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            if (count == x) {
                return temp;
            }
            count++;
            temp = temp.next;
        }
        return null;
    }

    void sortAsc(int start, int end) {
        Node pi, pj;
        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                pi = get(i);
                pj = get(j);
                if (pi.info.paddle > pj.info.paddle) {
                    Boat temp;
                    temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
            }
        }
    }

    void sortDesc(int start, int end) {
        Node pi, pj;
        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                pi = get(i);
                pj = get(j);
                if (pi.info.paddle < pj.info.paddle) {
                    Boat temp;
                    temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
            }
        }
    }
//==================================================================

    public void reverse(int start, int end) {
        int n = size();
        Node pi, pj;
        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                pi = get(i);
                pj = get(j);
                Boat t = pi.info;
                pi.info = pj.info;
                pj.info = t;
            }
        }
    }

    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
//        Node a = head;
//        Node b = tail;
//        Node c = head.next;
//        dele(a);
//        dele(b);
//        dele(c);
        if (size() >= 7) {
            reverse(2, 6);
        }
//        List<Node> fr = new ArrayList<>();
//        List<Node> se = new ArrayList<>();
//        List<Node> tr = new ArrayList<>();
//        List<Node> k = new ArrayList<>();
//
//        Node cur = head;
//        int i = 0;
//        while (cur != null) {
//            if (i < 2) {
//                fr.add(cur);
//            }
//            if (i >= 2 && i < 7) {
//                se.add(cur);
//            }
//            if (i >= 7) {
//                tr.add(cur);
//            }
//            i++;
//            cur = cur.next;
//        }
//
//        Collections.sort(se, new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                return o1.info.paddle - o2.info.paddle;
//            }
//        });
//
//        k.addAll(fr);
//        k.addAll(se);
//
//        k.addAll(tr);
//
//        clear();
//        head = k.get(0);
//        for (int j = 1; j < k.size(); j++) {
//            k.get(j - 1).next = k.get(j);
//        }
//        tail = k.get(k.size() - 1);
//        tail.next = null;

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

}
