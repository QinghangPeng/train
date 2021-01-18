package util;

/**
 * @ClassName: Graph
 * @Description:
 * @Author: jackson
 * @Date: 2021/1/18 下午2:11
 * @Version: v1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 邻接链表
 * @author suibibk.com
 */
public class Graph {
    public int num;//顶点个数
    //顶点
    List<Vertex> vertexs;
    public Graph(int num) {
        this.num = num;
        //初始化图的大小
        vertexs = new ArrayList<Vertex>(num);
    }
    //插入顶点
    public void addVertex(String value) {
        vertexs.add(new Vertex(value, null));
    }
    //获取顶点
    public Vertex getVertex(String value) {
        for (int i = 0; i < num; i++) {
            if(vertexs.get(i).value.equals(value)) {
                return vertexs.get(i);
            }
        }
        return null;
    }
    /**
     * 添加无向图的边
     * @param vertex1 第一个顶点
     * @param vertex2 第二个顶点
     */
    public void addUndigraphEdge(String vertex1,String vertex2) {
        //因为是无向图，所以就直接加入
        addEdgeByVertex(vertex1,vertex2,0);
        addEdgeByVertex(vertex2,vertex1,0);
    }
    /**
     * 添加有向图的边
     * @param start 开始节点
     * @param end 结束节点
     */
    public void addDigraphEdge(String start,String end) {
        //因为是有向图，所以只有一条边
        addEdgeByVertex(start,end,0);
    }
    /**
     * 添加网的边
     * @param start 开始节点
     * @param end 结束节点
     * @param weight 权重
     */
    public void addWebEdge(String start,String end,int weight) {
        //网就是有向图有权重
        addEdgeByVertex(start,end,weight);
    }
    /**
     * 添加一条由start-->end的边
     * @param start
     * @param end
     * @param weight 权重未0表示无向图或者有向图，部位0表示网
     */
    private void addEdgeByVertex(String start,String end,int weight) {
        //1、找到第一个顶点
        Vertex v1 = this.getVertex(start);
        //2、检查这条边是否已经存在，已经存在就直接报错
        Edge firstEdge = v1.firstEdge;
        while(firstEdge!=null) {
            //获取这个边
            String value = firstEdge.value;
            if(end.equals(value)) {
                System.out.println("边"+start+"-->"+end+"已经加入，不可以再加入");
                return;
            }else {
                Edge next = firstEdge.next;
                firstEdge=next;
            }
        }
        //到这里就可以加入这一条边了
        firstEdge = v1.firstEdge;
        //到这里就可以加入这条边
        if(firstEdge==null) {
            firstEdge = new Edge(end,weight, null);
            v1.firstEdge=firstEdge;
        }else {
            //当前节点变为第一个节点
            Edge nowEdge = new Edge(end,weight, null);
            v1.firstEdge=nowEdge;
            nowEdge.next=firstEdge;
        }
    }
    /**
     * 输出图
     */
    public void print() {
        for (int i = 0; i <num; i++) {
            Vertex vertex = vertexs.get(i);
            System.out.print(vertex.value+"-->");
            Edge edge = vertex.firstEdge;
            while(edge!=null) {
                System.out.print(edge.value+"["+edge.weight+"]");
                Edge next = edge.next;
                edge=next;
                if(next!=null) {
                    System.out.print("-->");
                }else {
                    System.out.print("-->∧");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        //测试无向图
        /*Graph g = new Graph(4);
        g.addVertex("V0");
        g.addVertex("V1");
        g.addVertex("V2");
        g.addVertex("V3");
        //插入边:无向图
        g.addUndigraphEdge("V0", "V1");
        g.addUndigraphEdge("V1", "V2");
        g.addUndigraphEdge("V2", "V3");
        g.addUndigraphEdge("V3", "V0");
        g.addUndigraphEdge("V2", "V0");
        System.out.println("输出无向图：");
        g.print();
        System.out.println("------------");
        Graph g1 = new Graph(4);
        g1.addVertex("V0");
        g1.addVertex("V1");
        g1.addVertex("V2");
        g1.addVertex("V3");
        //插入边:有向图
        g1.addDigraphEdge("V0", "V3");
        g1.addDigraphEdge("V1", "V0");
        g1.addDigraphEdge("V1", "V2");
        g1.addDigraphEdge("V2", "V0");
        g1.addDigraphEdge("V2", "V1");
        System.out.println("输出有向图：");
        g1.print();*/

        System.out.println("----------");
        Graph g2 = new Graph(5);
        g2.addVertex("A");
        g2.addVertex("B");
        g2.addVertex("C");
        g2.addVertex("D");
        g2.addVertex("E");
        //插入边:有向图
        g2.addWebEdge("A", "B",6);
        g2.addWebEdge("B","C",4);
        g2.addWebEdge("C","D",8);
        g2.addWebEdge("D","C",8);
        g2.addWebEdge("D","E",6);
        g2.addWebEdge("A","D",5);
        g2.addWebEdge("C","E",2);
        g2.addWebEdge("E","B",3);
        g2.addWebEdge("A","E",7);
        System.out.println("输出带权值的网：");
        g2.print();
    }
}
//定义定点
class Vertex{
    String value;//顶点的值
    Edge firstEdge;//第一条边
    public Vertex(String value, Edge firstEdge) {
        super();
        this.value = value;
        this.firstEdge = firstEdge;
    }
}
class Edge{
    String value;//该边对应的顶点
    int weight;//权重，无向图，有向图权重为0
    Edge next;//下一个边
    /**
     * 构建一条边 weight未0表示无向图或者有向图 不为0表示网
     * @param value
     * @param weight
     * @param next
     */
    public Edge(String value, int weight, Edge next) {
        super();
        this.value = value;
        this.weight = weight;
        this.next = next;
    }
}
