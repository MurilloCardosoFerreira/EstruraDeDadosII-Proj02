
public class BST {
    private Node root;

    // Construtor
    public BST() {
        root = null;
    }

    // Inserção
    public void inserir(int key, Object data) {
        root = inserirRecursivamente(root, key, data);
    }

    private Node inserirRecursivamente(Node root, int key, Object data) {
        if (root == null) {
            return new Node(key, data);
        }
        if (key < root.key) {
            root.left = inserirRecursivamente(root.left, key, data);
        } else if (key > root.key) {
            root.right = inserirRecursivamente(root.right, key, data);
        }
        return root;
    }

    // Busca
    public Object buscar(int key) {
        Node result = buscarRecursivamente(root, key);
        return (result != null) ? result.data : null;
    }

    private Node buscarRecursivamente(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (key < root.key) {
            return buscarRecursivamente(root.left, key);
        }
        return buscarRecursivamente(root.right, key);
    }

    // Remoção
    public void remover(int key) {
        root = removerRecursivamente(root, key);
    }

    private Node removerRecursivamente(Node root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.key) {
            root.left = removerRecursivamente(root.left, key);
        } else if (key > root.key) {
            root.right = removerRecursivamente(root.right, key);
        } else {
            // Nó encontrado

            // Caso 1: Nó sem filhos
            if (root.left == null && root.right == null) {
                return null;
            }
            // Caso 2: Nó com um filho
            else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Caso 3: Nó com dois filhos
            else {
                Node successor = getMin(root.right);
                root.key = successor.key;
                root.data = successor.data;
                root.right = removerRecursivamente(root.right, successor.key);
            }
        }
        return root;
    }

    private Node getMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
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

    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.key + " ");
        }
    }
}
