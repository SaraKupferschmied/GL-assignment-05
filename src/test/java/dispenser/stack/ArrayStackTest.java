package dispenser.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    @Test
    void put() throws StackOverflowException {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 100; i++) {
            stack.put(i);
        }
        assertThrows(StackOverflowException.class, () -> stack.put(101));
    }

    @Test
    void slowPut() throws StackOverflowException, EmptyStackException {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.slowPut("slow");
        assertEquals("slow", stack.item());
    }

    @Test
    void remove() throws StackOverflowException, EmptyStackException {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.put(10);
        int remove = stack.remove();
        assertNotNull(remove);
        assertEquals(10, remove);
        assertTrue(stack.isEmpty());
    }

    @Test
    void item() throws EmptyStackException, StackOverflowException {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.put(99);
        int top = stack.item();
        assertNotNull(top);
        assertEquals(99, top);
        assertFalse(stack.isEmpty());
    }

    @Test
    void isEmpty() {
        ArrayStack<Object> stack = new ArrayStack<>();
        assertTrue(stack.isEmpty());
        try {
            stack.put("X");
            assertFalse(stack.isEmpty());
            stack.remove();
            assertTrue(stack.isEmpty());
        } catch (Exception e) {
            fail("Unexpected exception during test.");
        }
    }

    @Test
    void removeFromEmptyStackThrowsException() {
        LinkedListStack<String> stack = new LinkedListStack<>();
        assertThrows(EmptyStackException.class, stack::remove);
    }

    @Test
    void peekOnEmptyStackThrowsException() {
        ArrayStack<String> stack = new ArrayStack<>();
        assertThrows(EmptyStackException.class, stack::item);
    }

    @Test
    void putThrowsStackOverflowExceptionWhenFull() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        try {
            for (int i = 0; i < 100; i++) {
                stack.put(i);
            }
        } catch (Exception e) {
            fail("Should not have thrown exception while filling up to max capacity: " + e.getMessage());
        }
        assertThrows(StackOverflowException.class, () -> stack.put(101));
    }

    @Test
    public void removeFromEmptyArrayStackThrowsExceptionWithMessage() {
        ArrayStack<String> stack = new ArrayStack<>();
        try {
            stack.remove();
            fail("Expected EmptyStackException was not thrown");
        } catch (EmptyStackException e) {
            assertEquals("the Stack is empty", e.getMessage());
        }
    }
}