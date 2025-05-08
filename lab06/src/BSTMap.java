import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    public class BSTNode{
         K item;
         V value;

        private BSTNode left;
        private BSTNode right;

        public BSTNode(K k , V v){
            item = k;
            value = v;
            left = null;
            right = null;
        }
    }

    private BSTNode root;
    private int size;

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode(key, value);
            size++;
            return;
        }

        BSTNode currNode = root;
        while (true) {
            int cmp = currNode.item.compareTo(key);
            if (key.equals(currNode.item)) {
                // 如果键已经存在，更新值
                currNode.value = value;
                return;
            } else if (cmp < 0) {
                if (currNode.left == null) {
                    currNode.left = new BSTNode(key, value);
                    size++;
                    return;
                } else {
                    currNode = currNode.left;
                }
            } else if (cmp > 0){
                if (currNode.right == null) {
                    currNode.right = new BSTNode(key, value);
                    size++;
                    return;
                } else {
                    currNode = currNode.right;
                }
            }
        }
    }

    @Override
    public V get(K key) {
        BSTNode currNode = root;
        while (currNode != null) {
            int cmp = currNode.item.compareTo(key);

            if (key.equals(currNode.item)) {
                return currNode.value;
            }else if (cmp < 0) {
                currNode = currNode.left;
            }else if (cmp > 0){
                currNode = currNode.right;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        BSTNode currNode = root;
        while (currNode != null) {
            if (key.equals(currNode.item)) {
                return true;
            }else if (currNode.item.compareTo(key) < 0) {
                currNode = currNode.left;
            }else if (currNode.item.compareTo(key) > 0){
                currNode = currNode.right;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        root = null;
        this.size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();

        for(K k : this){
            result.add(k);
        }

        return result;
    }

    @Override
    public V remove(K key) {
        // 找到目标节点及其父节点
        BSTNode targetNode = findNode(root, key);
        if (targetNode == null) {
            return null; // 如果未找到目标节点，直接返回 null
        }

        // 保存目标节点的值
        V value = targetNode.value;

        // 找到目标节点的父节点
        BSTNode parent = findFather(key);

        // 删除目标节点
        if (targetNode.left == null && targetNode.right == null) {
            // 情况1：目标节点是叶子节点
            if (parent == null) {
                root = null; // 如果目标节点是根节点
            } else if (parent.left == targetNode) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (targetNode.left == null || targetNode.right == null) {
            // 情况2：目标节点有一个子节点
            BSTNode child = (targetNode.left != null) ? targetNode.left : targetNode.right;
            if (parent == null) {
                root = child; // 如果目标节点是根节点
            } else if (parent.left == targetNode) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            // 情况3：目标节点有两个子节点
            // 找到右子树的最小值节点
            BSTNode minNode = findMin(targetNode.right);
            // 将目标节点的值替换为最小值节点的值
            targetNode.item = minNode.item;
            targetNode.value = minNode.value;
            // 删除右子树中的最小值节点
            remove(minNode.item);
        }

        return value; // 返回被删除节点的值
    }

    // 辅助方法：查找目标节点
    private BSTNode findNode(BSTNode node, K key) {
        while (node != null) {
            int cmp = key.compareTo(node.item);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node; // 找到目标节点
            }
        }
        return null; // 未找到目标节点
    }

    // 辅助方法：查找目标节点的父节点
    private BSTNode findFather(K key) {
        BSTNode currNode = root;
        BSTNode lastNode = null;
        while (currNode != null) {
            int cmp = currNode.item.compareTo(key);
            if (cmp < 0) {
                lastNode = currNode;
                currNode = currNode.left;
            } else if (cmp > 0) {
                lastNode = currNode;
                currNode = currNode.right;
            } else {
                return lastNode; // 找到目标节点的父节点
            }
        }
        return lastNode; // 如果未找到目标节点，返回最后一个访问的节点
    }

    // 辅助方法：查找最小值节点
    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node; // 返回最小值节点
    }


    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter(root);
    }


    private class BSTMapIter implements Iterator<K>{

        private Stack<BSTNode> stack;

        public BSTMapIter(BSTNode root) {
            stack = new Stack<>();
            // 初始化栈，将根节点的所有左子节点压入栈
            pushAllLeft(root);
        }

        /**
         * 返回 BST 中下一个最小的元素
         */
        public K next() {
            BSTNode node = stack.pop(); // 弹出栈顶元素
            pushAllLeft(node.right); // 将弹出节点的右子树的所有左子节点压入栈
            return node.item; // 返回弹出节点的值
        }

        /**
         * 判断是否还有下一个元素
         */
        public boolean hasNext() {
            return !stack.isEmpty(); // 如果栈不为空，则还有下一个元素
        }

        /**
         * 辅助方法：将某个节点的所有左子节点压入栈
         */
        private void pushAllLeft(BSTNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left; // 一直向左走
            }
        }
    }

}