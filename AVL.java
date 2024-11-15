public class AVL {
    private Node root;

    // Construtor
    public AVL() {
        root = null;
    }

    // Inserção
    public void insert(int key, Object data) {
        root = insertRec(root, key, data);
    }

    private Node insertRec(Node node, int key, Object data) {
        if (node == null) {
            return new Node(key, data);
        }
        if (key < node.key) {
            node.left = insertRec(node.left, key, data);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key, data);
        } else {
            return node; // Chaves duplicadas não são permitidas
        }

        // Atualizar a altura do nó atual
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // Balancear o nó
        return balance(node);
    }

    // Remoção
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node node, int key) {
        if (node == null) {
            return node;
        }
        if (key < node.key) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.key) {
            node.right = deleteRec(node.right, key);
        } else {
            // Caso 1: Nó folha
            if (node.left == null && node.right == null) {
                return null;
            }
            // Caso 2: Apenas um filho
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // Caso 3: Dois filhos
            Node successor = getMin(node.right);
            node.key = successor.key;
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.key);
        }

        // Atualizar a altura e balancear o nó
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return balance(node);
    }

    private Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Métodos auxiliares para balanceamento
    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalanceFactor(Node node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        // Rotação à direita
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        // Rotação à esquerda
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Rotação
        x.right = y;
        y.left = T2;

        // Atualizar alturas
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Rotação
        y.left = x;
        x.right = T2;

        // Atualizar alturas
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // Traversals
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.key + " ");
            inOrderRec(root.right);
        }
    }
}
