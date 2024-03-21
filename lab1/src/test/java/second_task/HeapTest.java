package second_task;

import com.tpo.second_task.Heap;
import com.tpo.second_task.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HeapTest {

    @Test
    @DisplayName("Common insert test")
    void commonInsertTest() {
        Node[] nodes1 = new Node[10];
        Node[] nodes2 = new Node[10];
        for (int i = 0; i < 10; i++) {
            nodes1[i] = new Node(i);
            nodes2[i] = new Node(i);
        }

        Node actualHeap = nodes1[4];
        actualHeap = Heap.insert(actualHeap, nodes1[8]);
        actualHeap = Heap.insert(actualHeap, nodes1[6]);
        actualHeap = Heap.insert(actualHeap, nodes1[2]);
        actualHeap = Heap.insert(actualHeap, nodes1[3]);
        actualHeap = Heap.insert(actualHeap, nodes1[7]);

        nodes2[2].setDist(1);
        nodes2[4].setDist(1);
        Node expectedHeap = nodes2[2];
        expectedHeap.setLeftNode(nodes2[4]);
        expectedHeap.getLeftNode().setLeftNode(nodes2[8]);
        expectedHeap.getLeftNode().setRightNode(nodes2[6]);
        expectedHeap.setRightNode(nodes2[3]);
        expectedHeap.getRightNode().setLeftNode(nodes2[7]);

        assertEquals(expectedHeap, actualHeap);
    }

    @Test
    @DisplayName("same numbers test")
    void sameNumbersTest() {
        Node actualHeap = new Node(0);
        for (int i = 0; i < 7; i++) {
            Heap.insert(actualHeap, new Node(0));
        }
        Node expectedHeap = new Node(0);
        expectedHeap.setDist(2);
        expectedHeap.setLeftNode(new Node(0));
        expectedHeap.getLeftNode().setDist(1);
        expectedHeap.getLeftNode().setLeftNode(new Node(0));
        expectedHeap.getLeftNode().setRightNode(new Node(0));
        expectedHeap.setRightNode(new Node(0));
        expectedHeap.getRightNode().setDist(1);
        expectedHeap.getRightNode().setLeftNode(new Node(0));
        expectedHeap.getRightNode().setRightNode(new Node(0));
        expectedHeap.getRightNode().getRightNode().setLeftNode(new Node(0));

        assertEquals(expectedHeap, actualHeap);
    }

    @Test
    @DisplayName("common extract min test")
    void commonExtractMinTest() {
        Node[] nodes1 = new Node[10];
        for (int i = 0; i < 10; i++) {
            nodes1[i] = new Node(i);
        }

        Node actualHeap = nodes1[4];
        actualHeap = Heap.insert(actualHeap, nodes1[8]);
        actualHeap = Heap.insert(actualHeap, nodes1[6]);
        actualHeap = Heap.insert(actualHeap, nodes1[2]);
        actualHeap = Heap.insert(actualHeap, nodes1[3]);
        actualHeap = Heap.insert(actualHeap, nodes1[7]);

        List<Node> ans1 = Heap.extractMin(actualHeap);
        actualHeap = ans1.get(1);
        List<Node> ans2 = Heap.extractMin(actualHeap);
        assertEquals(2, ans1.get(0).getValue());
        assertEquals(3, ans2.get(0).getValue());
    }

    @Test
    @DisplayName("extract min if more than 1 min")
    void ExtractMinIfSeveral() {
        Node actualHeap = new Node(0);
        for (int i = 0; i < 5; i++) {
            actualHeap = Heap.insert(actualHeap, new Node(0));
        }
        actualHeap = Heap.insert(actualHeap, new Node(8));
        actualHeap = Heap.insert(actualHeap, new Node(6));
        List<Node> ans1 = Heap.extractMin(actualHeap);
        actualHeap = ans1.get(1);
        List<Node> ans2 = Heap.extractMin(actualHeap);
        assertEquals(0, ans1.get(0).getValue());
        assertEquals(0, ans2.get(0).getValue());
    }
}
