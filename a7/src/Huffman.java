
import java.util.*;

/**
 * Prefix codes and Huffman tree.
 * Tree depends on source data.
 */
public class Huffman {
    public class HuffmanNode {
        private byte character;
        private int frequency;
        private HuffmanNode left;
        private HuffmanNode right;
        private HuffmanNode parent;

        public HuffmanNode(byte c, int f) {
            character = c;
            frequency = f;
            left = right = parent = null;
        }

        public HuffmanNode() {
            this((byte) 0, 0);
        }
    }

    public class HuffmanNodeComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode x, HuffmanNode y) {
            return x.frequency - y.frequency;
        }
    }

    public class BitString {
        private String bits;

        public BitString(byte[] bytes, int length) {
            bits = "";
            for (byte b : bytes) {
                String str = Integer.toBinaryString(b & 0xff);
                bits += String.join("", Collections.nCopies(8 - str.length(), "0")) + str;
            }
            bits = bits.substring(0, length);
        }

        public BitString() {
            this(new byte[0], 0);
        }

        public int length() {
            return bits.length();
        }

        public char[] getChars() {
            return bits.toCharArray();
        }

        public void append(String s) {
            bits += s;
        }


        public byte[] toBytes() {
            String str = bits;
            if (str.length() % 8 != 0) {
                str += String.join("", Collections.nCopies(8 - bits.length() % 8, "0"));
            }
            String[] stringBytes = str.split("(?<=\\G.{8})");
            byte[] bytes = new byte[stringBytes.length];

            for (int i = 0; i < stringBytes.length; ++i) {
                bytes[i] = (byte) Integer.parseInt(stringBytes[i], 2);
            }
            return bytes;
        }
    }
    // TODO!!! Your instance variables here!
    private HuffmanNode root;
    private Map<Byte, HuffmanNode> encoder;
    private int bitLength;

    /**
     * Constructor to build the Huffman code for a given bytearray.
     *
     * @param original source data
     */
    Huffman(byte[] original) {
        bitLength = 0;
        Map<Byte, Integer> frequencies = getFrequencies(original);
        PriorityQueue<HuffmanNode> minHeap = getHeap(frequencies, new HuffmanNodeComparator());
        encoder = new HashMap<>();
        for (HuffmanNode node : minHeap) {
            encoder.put(node.character, node);
        }

        buildHuffmanTree(minHeap);
        // TODO!!! Your constructor here!
    }
    private void buildHuffmanTree(PriorityQueue<HuffmanNode> minHeap) {
        if (minHeap.size() == 1) {
            root = minHeap.poll();
            return;
        }

        int n = minHeap.size() - 1;
        root = null;

        for (int i = 1; i <= n; ++i) {
            HuffmanNode z = new HuffmanNode();
            z.left = minHeap.poll();
            z.right = minHeap.poll();
            z.right.parent = z.left.parent = z;
            z.frequency = z.left.frequency + z.right.frequency;
            root = z;
            minHeap.add(z);
        }
    }
    private Map<Byte, Integer> getFrequencies(byte[] string) {
        Map<Byte, Integer> frequencies = new HashMap<>();
        //make frequency table
        for (byte c : string) {
            if (frequencies.containsKey(c)) {
                frequencies.put(c, frequencies.get(c) + 1);
            } else {
                frequencies.put(c, 1);
            }
        }
        return frequencies;
    }

    private PriorityQueue<HuffmanNode> getHeap(Map<Byte, Integer> map, Comparator<HuffmanNode> cmp) {
        PriorityQueue<HuffmanNode> heap = new PriorityQueue<>(map.size(), cmp);

        for (Map.Entry<Byte, Integer> pair : map.entrySet()) {
            heap.add(new HuffmanNode(pair.getKey(), pair.getValue()));
        }

        return heap;
    }

    /**
     * Length of encoded data in bits.
     *
     * @return number of bits
     */
    public int bitLength() {
        return bitLength; // TODO!!!
    }
    public String encodeLetter(byte letter) {
        if (encoder.size() == 1) {
            return "0";
        }

        String s = "";
        for (HuffmanNode p = encoder.get(letter); p.parent != null; p = p.parent) {
            s = ((p == p.parent.left) ? "0" : "1") + s;
        }

        return s;

    }

    /**
     * Encoding the byte array using this prefixcode.
     *
     * @param origData original data
     * @return encoded data
     */
    public byte[] encode(byte[] origData) {
        BitString str = new BitString();
        for (byte b : origData) {
            str.append(encodeLetter(b));
        }

        bitLength = str.length();
        return str.toBytes();// TODO!!!
    }

    /**
     * Decoding the byte array using this prefixcode.
     *
     * @param encodedData encoded data
     * @return decoded data (hopefully identical to original)
     */
    public byte[] decode(byte[] encodedData) {
        BitString str = new BitString(encodedData, bitLength);


        List<Byte> data = new ArrayList<>();

        if (encoder.size() == 1) {
            for (char c : str.getChars()) {
                data.add(root.character);
            }
        } else {
            HuffmanNode p = root;
            for (char c : str.getChars()) {
                if (c == '0') p = p.left;
                else p = p.right;

                if (p.left == null && p.right == null) {
                    data.add(p.character);
                    p = root;
                }
            }
        }

        byte[] ret = new byte[data.size()];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = data.get(i);
        }

        return ret; // TODO!!!
    }

    /**
     * Main method.
     */
    public static void main(String[] params) {
        String tekst = "AAAAAAAAAAAAABBBBBBCCCDDEEF";
        byte[] orig = tekst.getBytes();
        Huffman huf = new Huffman(orig);
        byte[] kood = huf.encode(orig);
        byte[] orig2 = huf.decode(kood);
        // must be equal: orig, orig2
        System.out.println(Arrays.equals(orig, orig2));
        int lngth = huf.bitLength();
        System.out.println("Length of encoded data in bits: " + lngth);
        // TODO!!! Your tests here!
    }
}
/*sources:
https://www.youtube.com/watch?v=zSsTG3Flo-I
https://www.programiz.com/dsa/huffman-coding
https://www.youtube.com/watch?v=co4_ahEDCho
https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
https://www.baeldung.com/java-comparator-comparable
https://www.youtube.com/watch?v=oNPyYF_Cz5I
https://www.geeksforgeeks.org/java-lang-integer-tobinarystring-method/
https://www.javatpoint.com/java-integer-tobinarystring-method
https://stackoverflow.com/questions/12310017/how-to-convert-a-byte-to-its-binary-string-representation
https://www.geeksforgeeks.org/collections-ncopies-java/
https://www.geeksforgeeks.org/priority-queue-class-in-java-2/
https://www.freecodecamp.org/news/priority-queue-implementation-in-java/
https://www.cs.cmu.edu/~tcortina/15-121sp10/Unit06B.pdf
https://www.geeksforgeeks.org/min-heap-in-java/
 */
