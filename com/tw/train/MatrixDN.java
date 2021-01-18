import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName: MatrixDN
 * @Description:
 * @Author: jackson
 * @Date: 2021/1/18 上午11:29
 * @Version: v1.0
 */
public class MatrixDN {
    private String[] vertexs; //顶点集合
    private int[][] matrixs; //边或弧，邻接矩阵

    /*
     * 创建图(自己输入数据)
     */
    public MatrixDN() {

        // 输入"顶点数"和"边数"
        System.out.printf("input vertex number: ");
        int vlen = readInt();
        System.out.printf("input edge number: ");
        int elen = readInt();
        if ( vlen < 1 || elen < 1 || (elen > (vlen*(vlen - 1)))) {
            System.out.printf("input error: invalid parameters!\n");
            return ;
        }

        // 初始化"顶点"
        vertexs = new String[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            System.out.printf("vertex(%d): ", i);
            vertexs[i] = String.valueOf(readChar());
        }

        // 初始化"边"
        matrixs=new int[vlen][vlen];
        for (int i = 0; i < elen; i++) {
            // 读取边的起始顶点和结束顶点
            System.out.printf("edge(%d):", i);
            String  c1 = String.valueOf(readChar());
            String c2 = String.valueOf(readChar());
            int p1 = getPostion(c1);
            int p2 = getPostion(c2);
            int weight = readInt();

            if (p1==-1 || p2==-1) {
                System.out.printf("input error: invalid edge!\n");
                return ;
            }

            matrixs[p1][p2] = weight;
        }
    }

    public MatrixDN(String[] vertex, ArrayList<String> matrix) {
        int verLen = vertex.length;
        int edgeLen = matrix.size();
        //初始化顶点
        vertexs = new String[verLen];
        for (int i = 0; i < verLen; i++) {
            vertexs[i] = vertex[i];
        }
        //初始化边或弧
        matrixs = new int[verLen][verLen];
        for (int i = 0; i < verLen; i++) {
            String v1 = matrix.get(i).substring(0, 1);
            String v2 = matrix.get(i).substring(1, 2);
            int p1 = getPostion(v1);
            int p2 = getPostion(v2);
            int weight = Integer.parseInt(matrix.get(i).substring(2));
            matrixs[p1][p2] = weight;
        }

    }

    public int getPostion(String v) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i].equals(v)) {
                return i;

            }
        }
        return -1;
    }


    /*
     * 读取一个输入字符
     */
    private char readChar() {
        char ch='0';

        do {
            try {
                ch = (char)System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(!((ch>='a'&&ch<='z') || (ch>='A'&&ch<='Z')));

        return ch;
    }

    /*
     * 读取一个输入字符
     */
    private int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void print() {
        System.out.println("MatrixDN:\n");
        for(int i=0;i<matrixs.length;i++){
            for(int j=0;j<matrixs[i].length;j++){
                System.out.println("顶点"+vertexs[i]+"->顶点"+vertexs[j]+" weight:"+matrixs[i][j]);
            }
        }
    }

    /*
     * 打印矩阵队列图
     */
    public void print1() {
        System.out.printf("Martix Graph:\n");
        for (int i = 0; i < matrixs.length; i++) {
            for (int j = 0; j < matrixs.length; j++){
                System.out.printf("%d ", matrixs[i][j]);}
            System.out.printf("\n");
        }
    }



    public static void main(String[] args) {
        String[] vertexs = {"A","B","C","D","E"};
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("AB5");
        arrayList.add("BC4");
        arrayList.add("CD8");
        arrayList.add("DC8");
        arrayList.add("DE6");
        arrayList.add("AD5");
        arrayList.add("CE2");
        arrayList.add("EB3");
        arrayList.add("AE7");
//        MatrixDN dn = new MatrixDN(vertexs,arrayList);
        MatrixDN dn = new MatrixDN(vertexs,arrayList);
        dn.print();
        dn.print1();

    }
}
