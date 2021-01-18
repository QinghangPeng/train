import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: Adjacency
 * @Description:
 * @Author: jackson
 * @Date: 2021/1/18 下午2:46
 * @Version: v1.0
 */
public class Adjacency {

    /**
     *  顶点
     */
    private List<String> VNodes;
    /**
     *  有向有权重边
     */
    private List<EData> EDatas;

    /**
     *  邻接链表
     */
    private List<Vertex> vertexs;

    public Adjacency(List<String> VNodes, List<EData> EDatas) {
        this.VNodes = VNodes;
        this.EDatas = EDatas;
        this.vertexs = new ArrayList<>();
        buildVertex(VNodes,EDatas);
    }

    public static void main(String[] args) {
        List<String> vnodes = Stream.of("A", "B", "C", "D", "E").collect(Collectors.toList());
        List<EData> edatas = new ArrayList<>();
        edatas.add(new EData("A","B",5));
        edatas.add(new EData("B","C",4));
        edatas.add(new EData("C","D",8));
        edatas.add(new EData("D","C",8));
        edatas.add(new EData("D","E",6));
        edatas.add(new EData("A","D",5));
        edatas.add(new EData("C","E",2));
        edatas.add(new EData("E","B",3));
        edatas.add(new EData("A","E",7));
        Adjacency ad = new Adjacency(vnodes,edatas);
        ad.print();
        ad.BFS();
    }

    /**
     *  广度优先遍历
     */
    private void BFS() {
        int head = 0;
        int rear = 0;
        int[] queue = new int[vertexs.size()];
        boolean[] visited = new boolean[vertexs.size()];
        for (int i = 0; i < vertexs.size(); i++) {
            visited[i] = false;
        }
        System.out.println("BFS: ");
        for (int i = 0; i < vertexs.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%s ", vertexs.get(i).value);
                // 入队列
                queue[rear++] = i;
            }

            while (head != rear) {
                // 出队列
                int j = queue[head++];
                Edge node = vertexs.get(j).firstEdge;
                while (node != null) {
                    String k = node.value;
                    /*if (!visited[k])
                    {
                        visited[k] = true;
                        System.out.printf("%s ", mVexs[k].data);
                        queue[rear++] = k;
                    }*/
                    node = node.next;
                }
            }
        }
        System.out.printf("\n");
    }

    /**
     *  组装有向有权邻接链表
     * @param vnodes
     * @param edatas
     * @return
     */
    public void buildVertex(List<String> vnodes,List<EData> edatas) {
        vnodes.forEach(value -> vertexs.add(new Vertex(value,null)));
        edatas.forEach(eData -> addEdgeByVertex(eData));
    }

    /**
     * 获取顶点
     * @param value
     * @return
     */
    public Vertex getVertex(String value) {
        for (int i = 0; i < VNodes.size(); i++) {
            if(vertexs.get(i).value.equals(value)) {
                return vertexs.get(i);
            }
        }
        return null;
    }

    /**
     * 添加一条由start-->end的边
     * @param eData
     */
    private void addEdgeByVertex(EData eData) {
        //1、找到第一个顶点
        Vertex v1 = this.getVertex(eData.getStart());
        //2、检查这条边是否已经存在，已经存在就直接报错
        Edge firstEdge = v1.firstEdge;
        while(firstEdge!=null) {
            //获取这个边
            String value = firstEdge.value;
            if(eData.getEnd().equals(value)) {
                System.out.println("边"+eData.getStart()+"-->"+eData.getEnd()+"已经加入，不可以再加入");
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
            firstEdge = new Edge(eData.getEnd(),eData.getWeight(), null);
            v1.firstEdge=firstEdge;
        }else {
            //当前节点变为第一个节点
            Edge nowEdge = new Edge(eData.getEnd(),eData.getWeight(), null);
            v1.firstEdge=nowEdge;
            nowEdge.next=firstEdge;
        }
    }

    /**
     * 输出图
     */
    public void print() {
        for (int i = 0; i < VNodes.size(); i++) {
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

    /**
     *  有向有权重边结构体
     */
    static class EData {
        String start; // 边的起点
        String end;   // 边的终点
        int weight; // 边的权重

        public EData(String start, String end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }
    }
}
