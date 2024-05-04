package concurrency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BoundedBufferTest  {
    @Test
    public void testWhenConstructedIsEmpty(){
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);
        assertTrue(boundedBuffer.isEmpty());
        assertFalse(boundedBuffer.isFull());
    }

    @Test
    public void testWhenPutItemsIsFull() throws InterruptedException {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);
        for(int i=0;i<10;i++){
            boundedBuffer.addItem((int) (Math.random()*10));
        }
        assertTrue(boundedBuffer.isFull());
        assertFalse(boundedBuffer.isEmpty());
    }
    @Test
    public void testTakerBlockWhenEmpty() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread(() -> {
            try {
                Integer unused = bb.getItem();
                fail();
            } catch (InterruptedException e) {

            }
        });
        taker.start();
        Thread.sleep(100);
        taker.interrupt();
        taker.join(100);
        assertFalse(taker.isAlive());
    }
}
