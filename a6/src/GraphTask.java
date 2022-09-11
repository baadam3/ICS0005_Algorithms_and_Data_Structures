import java.util.*;

/** Container class to different classes, that makes the whole
 * set of classes one class formally.
 */
public class GraphTask {

   /** Main method. */
   public static void main (String[] args) {
      GraphTask a = new GraphTask();
      a.run();
      // throw new RuntimeException ("Nothing implemented yet!"); // delete this
   }

   /** Actual main method to run examples and everything. */
   public void run() {
      Graph g = new Graph ("G");
      // TODO!!! Your experiments here

      Vertex[] v = new Vertex[3];
      for (int i = 0; i < v.length; ++i) {
         v[i] = g.createVertex("v" + String.valueOf(v.length - i));
      }

      g.createArc("av1_v2", v[2], v[1], 4);
      g.createArc("av2_v1", v[1], v[2], 4);
      g.createArc("av1_v3", v[2], v[0], 8);
      g.createArc("av3_v1", v[0], v[2], 8);
      g.createArc("av2_v3", v[1], v[0], 11);
      g.createArc("av3_v2", v[0], v[1], 11);

      Graph mst = g.primMST();

      System.out.println("Original graph:");
      System.out.println(g);
      System.out.println("Minimum spanning tree:");
      System.out.println(mst);




      g = new Graph("G");
      v = new Vertex[7];
      for (int i = 0; i < v.length; ++i) {
         v[i] = g.createVertex("v" + String.valueOf(v.length - i));
      }

      g.createArc("av1_v2", v[6], v[5], 4);
      g.createArc("av2_v1", v[5], v[6], 4);
      g.createArc("av2_v3", v[5], v[4], 8);
      g.createArc("av3_v2", v[4], v[5], 8);
      g.createArc("av3_v4", v[4], v[3], 4);
      g.createArc("av4_v3", v[3], v[4], 4);
      g.createArc("av4_v5", v[3], v[2], 2);
      g.createArc("av5_v4", v[2], v[3], 2);
      g.createArc("av5_v6", v[2], v[1], 1);
      g.createArc("av6_v5", v[1], v[2], 1);
      g.createArc("av1_v6", v[6], v[1], 8);
      g.createArc("av6_v1", v[1], v[6], 8);
      g.createArc("av2_v6", v[5], v[1], 11);
      g.createArc("av6_v2", v[1], v[5], 11);
      g.createArc("av7_v5", v[0], v[2], 6);
      g.createArc("av5_v7", v[2], v[0], 6);
      g.createArc("av7_v6", v[0], v[1], 7);
      g.createArc("av6_v7", v[1], v[0], 7);
      g.createArc("av7_v3", v[0], v[4], 2);
      g.createArc("av3_v7", v[4], v[0], 2);

      mst = g.primMST();

      System.out.println("Original graph:");
      System.out.println(g);
      System.out.println("Minimum spanning tree:");
      System.out.println(mst);
   }

   // TODO!!! add javadoc relevant to your problem
   class Vertex {

      private String id;
      private Vertex next;
      private Arc first;
      private int info = 0;

      private int key;
      private Vertex parent;
      // You can add more fields, if needed

      Vertex (String s, Vertex v, Arc e) {
         id = s;
         next = v;
         first = e;
      }

      Vertex (String s) {
         this (s, null, null);
      }

      @Override
      public String toString() {
         return id;
      }
   }


   public class VertexComparator implements Comparator<Vertex> {
      public int compare(Vertex x, Vertex y) {
         return x.key - y.key;
      }
   }

   /** Arc represents one arrow in the graph. Two-directional edges are
    * represented by two Arc objects (for both directions).
    */
   class Arc {

      private String id;
      private Vertex target;
      private Arc next;
      private int info = 0;
      // You can add more fields, if needed

      private int weight;

      Arc (String s, Vertex v, Arc a, int w) {
         id = s;
         target = v;
         next = a;
         weight = w;
      }

      Arc (String s, int w) {
         this (s, null, null, w);
      }

      @Override
      public String toString() {
         return id;
      }

      // TODO!!! Your Arc methods here!
   }

   class Graph {

      private String id;
      private Vertex first;
      private int info = 0;
      // You can add more fields, if needed

      Graph (String s, Vertex v) {
         id = s;
         first = v;
      }

      Graph (String s) {
         this (s, null);
      }

      @Override
      public String toString() {
         String nl = System.getProperty ("line.separator");
         StringBuffer sb = new StringBuffer (nl);
         sb.append (id);
         sb.append (nl);
         Vertex v = first;
         while (v != null) {
            sb.append (v.toString());
            sb.append (" -->");
            Arc a = v.first;
            while (a != null) {
               sb.append (", ");
               sb.append (a.toString());
               sb.append (" (");
               sb.append (v.toString());
               sb.append ("->");
               sb.append (a.target.toString());
               sb.append (")");
               sb.append(" w: " + a.weight);
               a = a.next;
            }
            sb.append (nl);
            v = v.next;
         }
         return sb.toString();
      }

      public Vertex createVertex (String vid) {
         Vertex res = new Vertex (vid);
         res.next = first;
         first = res;
         return res;
      }

      public Arc createArc (String aid, Vertex from, Vertex to, int weight) {
         Arc res = new Arc (aid, weight);
         res.next = from.first;
         from.first = res;
         res.target = to;
         return res;
      }

      /**
       * Create a connected undirected random tree with n vertices.
       * Each new vertex is connected to some random existing vertex.
       * @param n number of vertices added to this graph
       */
      public void createRandomTree (int n) {
         if (n <= 0)
            return;
         Random randGen = new Random();
         Vertex[] varray = new Vertex [n];
         for (int i = 0; i < n; i++) {
            varray [i] = createVertex ("v" + String.valueOf(n-i));
            if (i > 0) {
               int vnr = (int)(Math.random()*i);

               int weight = randGen.nextInt(20);

               createArc ("a" + varray [vnr].toString() + "_"
                       + varray [i].toString(), varray [vnr], varray [i], weight);
               createArc ("a" + varray [i].toString() + "_"
                       + varray [vnr].toString(), varray [i], varray [vnr], weight);
            } else {}
         }
      }

      /**
       * Create an adjacency matrix of this graph.
       * Side effect: corrupts info fields in the graph
       * @return adjacency matrix
       */
      public int[][] createAdjMatrix() {
         info = 0;
         Vertex v = first;
         while (v != null) {
            v.info = info++;
            v = v.next;
         }
         int[][] res = new int [info][info];
         v = first;
         while (v != null) {
            int i = v.info;
            Arc a = v.first;
            while (a != null) {
               int j = a.target.info;
               res [i][j]++;
               a = a.next;
            }
            v = v.next;
         }
         return res;
      }

      /**
       * Create a connected simple (undirected, no loops, no multiple
       * arcs) random graph with n vertices and m edges.
       * @param n number of vertices
       * @param m number of edges
       */
      public void createRandomSimpleGraph (int n, int m) {
         if (n <= 0)
            return;
         if (n > 2500)
            throw new IllegalArgumentException ("Too many vertices: " + n);
         if (m < n-1 || m > n*(n-1)/2)
            throw new IllegalArgumentException
                    ("Impossible number of edges: " + m);
         first = null;
         createRandomTree (n);       // n-1 edges created here
         Vertex[] vert = new Vertex [n];
         Vertex v = first;
         int c = 0;
         while (v != null) {
            vert[c++] = v;
            v = v.next;
         }
         Random randGen = new Random();
         int[][] connected = createAdjMatrix();
         int edgeCount = m - n + 1;  // remaining edges
         while (edgeCount > 0) {
            int i = (int)(Math.random()*n);  // random source
            int j = (int)(Math.random()*n);  // random target
            if (i==j)
               continue;  // no loops
            if (connected [i][j] != 0 || connected [j][i] != 0)
               continue;  // no multiple edges
            Vertex vi = vert [i];
            Vertex vj = vert [j];
            int weight = randGen.nextInt(20);
            createArc ("a" + vi.toString() + "_" + vj.toString(), vi, vj, weight);
            connected [i][j] = 1;
            createArc ("a" + vj.toString() + "_" + vi.toString(), vj, vi, weight);
            connected [j][i] = 1;
            edgeCount--;  // a new edge happily created
         }
      }

      // TODO!!! Your Graph methods here! Probably your solution belongs here.
      @Override
      public boolean equals(Object o) {
         if (!(o instanceof Graph)) {
            return false;
         }
         Graph g = (Graph) o;

         Map<String, Arc> other = new HashMap<>();

         for (Vertex v = first; v != null; v = v.next) {
            other.put(v.id, v.first);
         }

         int n = 0;
         for (Vertex v = g.first; v != null; v = v.next, ++n) {
            if (!other.containsKey(v.id)) {
               return false;
            }
            Set<String> arcs = new HashSet<>();

            for (Arc e = other.get(v.id); e != null; e = e.next) {
               arcs.add(e.id);
            }

            int i = 0;
            for (Arc e = v.first; e != null; e = e.next, ++i) {
               if (!arcs.contains(e.id)) {
                  return false;
               }
            }

            if (arcs.size() != i) {
               return false;
            }

         }

         if (n != other.size()) {
            return false;
         }

         return true;
      }

      public Graph primMST() {
         PriorityQueue<Vertex> minHeap = new PriorityQueue<>(new VertexComparator());
         for (Vertex u = first; u != null; u = u.next) {
            u.key = Integer.MAX_VALUE;
            u.info = 0;
            minHeap.add(u);
         }

         Graph mst = new Graph("MST");
         if (minHeap.isEmpty()) {
            return mst;
         }

         minHeap.remove(first);
         first.key = 0;
         minHeap.add(first);

         while (!minHeap.isEmpty()) {
            Vertex u = minHeap.poll();
            Vertex mstVertex = mst.createVertex(u.id);

            u.info = 1;
            if (u.parent != null) {
               mst.createArc("a" + mstVertex.id + "_" + u.parent.id, mstVertex, u.parent, u.key);
               mst.createArc("a" + u.parent.id + "_" + mstVertex.id, u.parent, mstVertex, u.key);
            }

            for (Arc e = u.first; e != null; e = e.next) {
               if (e.target.info == 0 && e.weight < e.target.key) {
                  minHeap.remove(e.target);
                  e.target.key = e.weight;
                  e.target.parent = mstVertex;
                  minHeap.add(e.target);
               }
            }
         }
         return mst;

      }
   }

}

/*sources:
https://www.baeldung.com/java-prim-algorithm
https://enos.itcollege.ee/~japoia/allalaadimised/reading/Introduction_to_algorithms-3rd%20Edition.pdf
https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
https://www.youtube.com/watch?v=agNQb6rme8U&t=37s
https://steemit.com/programming/@drifter1/programming-java-graph-minimum-spanning-tree-algorithms
https://www.youtube.com/watch?v=jsmMtJpPnhU
 */