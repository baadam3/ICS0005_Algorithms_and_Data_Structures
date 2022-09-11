
import java.util.*;

public class Node {

    private String name;
    private Node firstChild;
    private Node nextSibling;

    Node(String n, Node d, Node r) {
        // TODO!!! Your constructor here
        this.name = n;
        this.firstChild = d;
        this.nextSibling = r;
    }

    public static Node parsePostfix(String s) {
        //https://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
       /* if (!s.matches("\\s+"))
            throw new RuntimeException("Not allowed white spaces: " + "\"" + s + "\"");*/
        if (s.contains(",,"))
            throw new RuntimeException("No next sibling was found: " + "\"" + s + "\"");
        else if (s.contains("()"))
            throw new RuntimeException("Input contains empty sub tree: " + "\"" + s + "\"");
        else if (s.contains("),"))
            throw new RuntimeException("Invalid bracket in input: " + "\"" + s + "\"");
        else if (s.matches("\\s+"))
            throw new RuntimeException("Not allowed white spaces: " + "\"" + s + "\"");
        else if (s.contains("( , ) "))
            throw new RuntimeException("Empty input: " + "\"" + s + "\"");
        else if (((!s.contains("(") || !s.contains(")")) && s.matches("^$")) || ((!s.contains("(") || !s.contains(")")) && s.contains(",")))
            throw new RuntimeException("No brackets found: " + "\"" + s + "\"");
        else if (s.contains("))"))
            throw new RuntimeException("Invalid syntax of brackets: " + "\"" + s + "\"");

        boolean rootIsIncorrect = false;
        String[] tokens = s.split("");
        Stack<Node> stack = new Stack();
        Node root = new Node(null, null, null);
        for (int i = 0; i < tokens.length; i++) {
            String current = tokens[i].trim();
            switch (current) {
                case "(":
                    if (rootIsIncorrect) {
                        throw new RuntimeException("Incorrect root: " + "\"" + s + "\"");
                    }
                    stack.push(root);
                    root.firstChild = new Node(null, null, null);
                    root = root.firstChild;
                    if (tokens[i + 1].trim().equals(",")) {
                        throw new RuntimeException("Invalid input syntax: " + "\"" + s + "\"");
                    }
                    break;
                case ")":
                    try {
                        root = stack.pop();
                    } catch (EmptyStackException e) {
                        throw new RuntimeException("Invalid input syntax: " + "\"" + s + "\"");
                    }
                    if (stack.size() == 0) {
                        rootIsIncorrect = true;
                    }
                    break;
                case ",":
                    if (rootIsIncorrect) {
                        throw new RuntimeException("Incorrect root: " + "\"" + s + "\"");
                    }
                    root.nextSibling = new Node(null, null, null);
                    root = root.nextSibling;
                    break;
                default:
                    if (root.name == null) {
                        root.name = current;
                    } else {
                        if (current.matches("^$"))
                            throw new RuntimeException("Space between node chars: " + "\"" + s + "\"");
                        root.name += current;
                    }
                    break;
            }
        }
        return root;
    }

    public String leftParentheticRepresentation() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        if (this.firstChild != null) {
            sb.append("(");
            sb.append(this.firstChild.leftParentheticRepresentation());
            sb.append(")");
        }
        if (this.nextSibling != null) {
            sb.append(",");
            sb.append(this.nextSibling.leftParentheticRepresentation());
        }
        return sb.toString(); // TODO!!! return the string without spaces
    }

    public static void main(String[] param) {
        String s = "(B1,C)A";
        Node t = Node.parsePostfix(s);
        String v = t.leftParentheticRepresentation();
        System.out.println(s + " ==> " + v); // (B1,C)A ==> A(B1,C)
        //String s = "%%";            // ok
        //String s = "((@,#)+)-34";   // ok
        // String s = "((@, #)+)-34";  // ok
        // String s = "     \t ";      // not ok
        // String s = "((1),(2)3)4";   // not ok
        // String s = "A B";           // not ok
        // String s = "((3 4, 5)6)7";  // not ok
        System.out.println("Input: " + s);
        Node tree = Node.parsePostfix(s);
        System.out.println(" Left: "
                + tree.leftParentheticRepresentation());
    }
    /* sources:
       Tree using stack:  https://www.youtube.com/watch?v=VsxLHGUqAKs
                          https://www.youtube.com/watch?v=WHs-wSo33MM
                          https://www.youtube.com/watch?v=VQTF_pRTZek&t
                          https://www.youtube.com/watch?v=cviyUv2TFG8
                          https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/ (I know we do not use inorder but I use these as sources too to make my implementation)
                          https://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
                          https://medium.com/swlh/tree-traversal-algorithms-theory-and-practice-in-java-7e7d1fe9ed30
                          https://www.tutorialspoint.com/data_structures_algorithms/tree_data_structure.htm

       Some other sources: https://stackoverflow.com/questions/15625629/regex-expressions-in-java-s-vs-s
                           https://stackoverflow.com/questions/3164452/regex-for-specifying-an-empty-string


        these are the main sources I could find again. Once again sorry for forgetting to include them.
     */
}

