public class Problem3 {
    public static void remAllStack(Stack<Object> stack, Object item) {
        Stack<Object> stack2 = new LLStack<Object>();
        while (!stack.isEmpty()) {
            if (stack.peek().equals(item)) {
                stack.pop();
            } else {
                Object O1 = stack.pop();
                stack2.push(O1);
            }
        }
        while (!stack2.isEmpty()) {
            Object O2 = stack2.pop();
            stack.push(O2);
        }
    }
    

    public static void remAllQueue(Queue<Object> queue, Object item) {
        Queue<Object> queue2 = new LLQueue<Object>();
        while (!queue.isEmpty()) {
            if (queue.peek().equals(item)) {
                queue.remove();
            } else {
                Object O1 = queue.remove();
                queue2.insert(O1);
            }
        } 
        while (!queue2.isEmpty()) {
            Object O2 = queue2.remove();
            queue.insert(O2);
        }
    }
    

    public static void main(String[] args) {
    Queue<Object> s = new ArrayQueue<Object>(5);
        s.insert(5);
        s.insert(2);
        s.insert(7);
        s.insert(2);
        s.insert(10);
        remAllQueue(s, 2);
        System.out.println(s);
    }
    
}
