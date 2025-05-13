import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
public class testRBTree {
    @Test
    public void mytest(){
        RedBlackTree<Integer> rbtree = new RedBlackTree();
        assertThat(rbtree.root).isNull();

        RedBlackTree.RBTreeNode<Integer> node1 = new RedBlackTree.RBTreeNode<>(true, 10, null, null);
        RedBlackTree.RBTreeNode<Integer> node2 = new RedBlackTree.RBTreeNode<>(false, 9, null, null);
        RedBlackTree.RBTreeNode<Integer> node3 = new RedBlackTree.RBTreeNode<>(false, 8, null, null);
       // RedBlackTree.RBTreeNode<Integer> node4 = new RedBlackTree.RBTreeNode<>(true, 11, null, null);
        node1.left = node2;
        node2.left = node3;
       // node1.right = node4;
        //rbtree.flipColors(node1);
        RedBlackTree.RBTreeNode<Integer> newRoot = rbtree.rotateRight(node1);
        assertThat(newRoot.item).isEqualTo(9);
        assertThat(newRoot.right.item).isEqualTo(10);
        assertThat(newRoot.left.item).isEqualTo(8);
    }

}
