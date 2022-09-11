import java.util.*;

public class LongStack {

    private LinkedList<Long> stack;

    public static void main(String[] argum) {
        // TODO!!! Your tests here!
    }

    LongStack() {
        // TODO!!! Your constructor here!
        this.stack = new LinkedList<>();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        LongStack cloneStack = new LongStack();
        Iterator<Long> iter = this.stack.descendingIterator();
        while (iter.hasNext()) {
            cloneStack.push(iter.next());
        }
        return cloneStack;// TODO!!! Your code here!
    }

    public boolean stEmpty() {
        return this.stack.isEmpty(); // TODO!!! Your code here!
    }

    public void push(long a) {
        // TODO!!! Your code here!
        this.stack.push(a);
    }

    public long pop() {
        if (this.stEmpty()) {
            throw new RuntimeException("Popping from an empty stack");
        }

        return this.stack.pop(); // TODO!!! Your code here!
    }

    public void op(String s) {
        // TODO!!!
        if (this.stack.size() < 2) {
            throw new RuntimeException("Not enough elements on the stack");
        }

        long num1;
        long num2;
        switch (s) {
            case "+":
                this.stack.push(this.stack.pop() + this.stack.pop());
                break;
            case "-":
                num1 = this.stack.pop();
                num2 = this.stack.pop();
                this.stack.push(num2 - num1);
                break;
            case "*":
                this.stack.push(this.stack.pop() * this.stack.pop());
                break;
            case "/":
                num1 = this.stack.pop();
                num2 = this.stack.pop();
                this.stack.push(num2 / num1);
                break;
            default:
                throw new RuntimeException(s + " is not a valid operator");
        }
    }

    public long tos() {
        if (this.stEmpty()) {
            throw new RuntimeException("Reading top of the stack from an empty stack");
        }
        return this.stack.peek(); // TODO!!! Your code here!
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof LongStack) && ((LongStack) o).stack.equals(this.stack); // TODO!!! Your code here!
    }

    @Override
    public String toString() { //I just choose this one because I have never used it before https://stackoverflow.com/questions/4767615/java-iterating-a-linked-list
        StringBuilder sb = new StringBuilder();
        Iterator<Long> iter = this.stack.descendingIterator();
        while (iter.hasNext()) {
            sb.append(iter.next());
        }
        return sb.toString(); // TODO!!! Your code here!
    }

    public static long interpret(String pol) { //https://stackoverflow.com/questions/13440506/find-all-numbers-in-the-string
        if (pol.matches("\\s*")) {
            throw new RuntimeException("The expression provided is empty");
        }
        String[] tokens = pol.split("\\s+");
        LongStack stack = new LongStack();
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            if (token.matches("-?\\d+")) stack.push(Long.parseLong(token));
            else if (token.matches("^[-+*/]$")) {
                try {
                    stack.op(token);
                } catch(RuntimeException ex) {
                    throw new RuntimeException(pol + " the expression does not contain enough numbers");
                }
            }
            else throw new RuntimeException(pol + " contains an illegal symbol");

        }

        long tos = stack.pop();
        if (!stack.stEmpty()) throw new RuntimeException(pol + " contains too many numbers");

        return tos;
    }
}