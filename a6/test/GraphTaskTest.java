import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

/** Testklass.
 * @author jaanus
 */
public class GraphTaskTest {
   @Test (timeout=20000)
   public void test1() {
      GraphTask task = new GraphTask();
      GraphTask.Graph g = task.new Graph("G");
      GraphTask.Graph mst = g.primMST();

      assertTrue(g.equals(mst));
   }

   @Test (timeout=20000)
   public void test2() {
      GraphTask task = new GraphTask();
      GraphTask.Graph g = task.new Graph("G");
      GraphTask.Vertex[] v = new GraphTask.Vertex[3];
      for (int i = 0; i < v.length; ++i) {
         v[i] = g.createVertex("v" + String.valueOf(v.length - i));
      }

      g.createArc("av1_v2", v[2], v[1], 4);
      g.createArc("av2_v1", v[1], v[2], 4);
      g.createArc("av1_v3", v[2], v[0], 8);
      g.createArc("av3_v1", v[0], v[2], 8);
      g.createArc("av2_v3", v[1], v[0], 11);
      g.createArc("av3_v2", v[0], v[1], 11);


      GraphTask.Graph exp = task.new Graph("EXP");
      for (int i = 0; i < v.length; ++i) {
         v[i] = exp.createVertex("v" + String.valueOf(v.length - i));
      }

      g.createArc("av1_v2", v[2], v[1], 4);
      g.createArc("av2_v1", v[1], v[2], 4);
      g.createArc("av1_v3", v[2], v[0], 8);
      g.createArc("av3_v1", v[0], v[2], 8);

      GraphTask.Graph mst = g.primMST();

      assertTrue(mst.equals(exp));
   }


   @Test (timeout=20000)
   public void test3() {
      GraphTask task = new GraphTask();
      GraphTask.Graph g = task.new Graph("G");
      GraphTask.Vertex[] v = new GraphTask.Vertex[7];
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


      GraphTask.Graph exp = task.new Graph("G");
      for (int i = 0; i < v.length; ++i) {
         v[i] = exp.createVertex("v" + String.valueOf(v.length - i));
      }

      exp.createArc("av1_v2", v[6], v[5], 4);
      exp.createArc("av2_v1", v[5], v[6], 4);
      exp.createArc("av3_v4", v[4], v[3], 4);
      exp.createArc("av4_v3", v[3], v[4], 4);
      exp.createArc("av4_v5", v[3], v[2], 2);
      exp.createArc("av5_v4", v[2], v[3], 2);
      exp.createArc("av5_v6", v[2], v[1], 1);
      exp.createArc("av6_v5", v[1], v[2], 1);
      exp.createArc("av1_v6", v[6], v[1], 8);
      exp.createArc("av6_v1", v[1], v[6], 8);
      exp.createArc("av7_v3", v[0], v[4], 2);
      exp.createArc("av3_v7", v[4], v[0], 2);

      GraphTask.Graph mst = g.primMST();

      assertTrue(mst.equals(exp));
   }

   @Test (timeout=20000)
   public void test4() {
      GraphTask task = new GraphTask();
      GraphTask.Graph g = task.new Graph("G");
      g.createRandomTree(2500);

      GraphTask.Graph mst = g.primMST();
      assertTrue(mst.equals(g));
   }

   @Test (timeout=20000)
   public void test5() {
      GraphTask task = new GraphTask();
      GraphTask.Graph g = task.new Graph("G");
      g.createRandomSimpleGraph(2500, 3000);
      g.primMST();
   }
}