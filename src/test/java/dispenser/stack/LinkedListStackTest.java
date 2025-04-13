package dispenser.stack;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTest {

    @Test
    void put() throws EmptyStackException {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        assertTrue(stack.isEmpty());
        stack.put(42);
        assertFalse(stack.isEmpty());
        int top = stack.item();
        assertNotNull(top);
        assertEquals(42, top);
    }

    @Test
    void remove() throws EmptyStackException {
        LinkedListStack<String> stack = new LinkedListStack<>();
        stack.put("A");
        stack.put("B");
        assertEquals("B", stack.remove());
        assertEquals("A", stack.item());
    }

    @Test
    void item() throws EmptyStackException {
        LinkedListStack<String> stack = new LinkedListStack<>();
        stack.put("PeekMe");
        assertEquals("PeekMe", stack.item());
        assertFalse(stack.isEmpty());
    }

    @Test
    void isEmpty() throws EmptyStackException {
        LinkedListStack<Double> stack = new LinkedListStack<>();
        assertTrue(stack.isEmpty());
        stack.put(1.1);
        assertFalse(stack.isEmpty());
        stack.remove();
        assertTrue(stack.isEmpty());
    }

    @Test
    void slowPut() throws EmptyStackException {
        LinkedListStack<String> stack = new LinkedListStack<>();
        stack.slowPut("test");
        assertEquals("test", stack.item());
    }

    @Test
    void removeFromEmptyStackThrowsException() {
        LinkedListStack<String> stack = new LinkedListStack<>();
        assertThrows(EmptyStackException.class, stack::remove);
    }

    @Test
    void peekOnEmptyStackThrowsException() {
        LinkedListStack<String> stack = new LinkedListStack<>();
        assertThrows(EmptyStackException.class, stack::item);
    }

    @Test
    public void removeFromEmptyArrayStackThrowsExceptionWithMessage() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        try {
            stack.remove();
            fail("Expected EmptyStackException was not thrown");
        } catch (EmptyStackException e) {
            assertEquals("The stack is empty", e.getMessage());
        }
    }

}