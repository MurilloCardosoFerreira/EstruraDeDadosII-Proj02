
public class Node {
    int key; // Chave de ordenação
    Object data; // Dados associados
    Node left, right; // Filhos
    int height; // Apenas para AVL

    public Node(int key, Object data) {
        this.key = key;
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1; // Altura inicial
    }
}
