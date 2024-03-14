package com.tpo.second_task;

import java.util.ArrayList;
import java.util.List;

public class Heap {

    public static Node merge(Node ft, Node sd) {
        if (ft == null) return sd;
        if (sd == null) return ft;
        if (sd.getValue() < ft.getValue()) {
            Node x = ft;
            ft = sd;
            sd = x;
        }

        ft.setRightNode(merge(ft.getRightNode(), sd));

        if (ft.getLeftNode() == null) {
            ft.setLeftNode(ft.getRightNode());
            ft.setRightNode(null);
        } else if (ft.getRightNode().getDist() > ft.getLeftNode().getDist()) {
            Node x = ft.getLeftNode();
            ft.setLeftNode(ft.getRightNode());
            ft.setRightNode(x);
        }
        ft.setDist((ft.getRightNode() == null) ? 0 : ft.getRightNode().getDist() + 1);
        return ft;
    }

    public static Node insert(Node dest, Node src) {
        return merge(dest, src);
    }

    public static List<Node> extractMin(Node heap) {
        return List.of(heap, merge(heap.getLeftNode(), heap.getRightNode()));
    }
}
