public class AVLTreeOfInteger {
  private static final class Node {
    public Node father;
    public Node left;
    public Node right;
    public Integer element;
    public int height;
    public int size;

    public Node(Integer element) {
      father = null;
      left = null;
      right = null;
      height = 0;
      size = 1;
      this.element = element;
    }
  }

  private int count;
  private Node root;

  public AVLTreeOfInteger() {
    count = 0;
    root = null;
  }

  /**
   * limpa a arvore
   */
  public void clear() { // O (1)
    count = 0;
    root = null;
  }

  /**
   * Retorna se a arvore esta vazia ou nao
   */
  public boolean isEmpty() { // O (1)
    return root == null;
  }

  /**
   * Retorna o total de elementos na arvore
   */
  public int size() { // O (1)
    return count;
  }

  /**
   * Chama o metodo height passando root
   * 
   * @return o tamanho da arvore
   */
  public int height() {
    return height(root);
  }

  /**
   * Soma o tamanho das maiores sub-arvores do nodo e retorna o tamanho total da
   * arvore
   * 
   * @param n o nodo inicial
   * @return o tamanho da arvore
   */
  public int height(Node n) { // O (log n)
    // Base case:
    if (n == null) {
      return 0;
    }

    return pickHigher(height(n.left), height(n.right)) + 1;
  }

  private int pickHigher(int a, int b) {
    if (a >= b) {
      return a;
    } else {
      return b;
    }
  }

  /**
   * Chama o subHeight passando o root como parametro
   * 
   * @return Retorna a altura da sub-arvore
   */
  public int subHeight() {
    return subHeight(root);
  }

  /**
   * Retorna o tamanho da sub-arvore
   * 
   * @param subTree a sub-arvore
   * @return o tamanho da sub-arvore
   */
  private int subHeight(Node subTree) { // O (1)
    if (subTree == null) {
      return -1;
    }

    return subTree.height;
  }

  /**
   * Chama o subSize passando o root como parametro
   * 
   * @return Retorna o numero de nodos na sub-arvore
   */
  public int subSize() {
    return subSize(root);
  }

  /**
   * Retorna o numero de nodos na subTree
   * 
   * @param subTree a subTree
   * @return Retorna o numero de nodos na sub-arvore
   */
  private int subSize(Node subTree) { // O (1)
    if (subTree == null) {
      return 0;
    }

    return subTree.size;
  }

  /**
   * Pega o elemento pai do elemento passado por parametro
   * 
   * @param element o elemento a ser ter o pai pego
   * @return o elemento pai
   */
  public Integer getParent(Integer element) { // O (log n)
    Node nodoDoElemento = findNode(element, root);

    if (nodoDoElemento == null) {
      return null;
    }

    return nodoDoElemento.father.element;
  }

  /**
   * Retorna a raiz da arvore
   * 
   * @return a raiz da arvore
   */
  public Integer getRoot() { // O (1)
    if (isEmpty()) {
      throw new EmptyTreeException();
    }
    return root.element;
  }

  /**
   * Verifica se determinado elemento esta presente na arvore
   * 
   * @param element o elemento a verificar se esta na arvore
   * @return se o elemento esta na arvore ou nao
   */
  public boolean contains(Integer element) { // O (log n)
    Node aux = findNode(element, root);

    return aux != null;
  }

  /**
   * Acha o nodo de determinado elemento na arvore
   * 
   * @param element o elemento a ser procurado
   * @param target  a partir de qual nodo sera procurado
   * @return o nodo caso seja encontrado
   */
  private Node findNode(Integer element, Node target) { // O (log n)
    if (element == null || target == null) {
      return null;
    }

    int valorComparacao = element.compareTo(target.element);

    if (valorComparacao == 0) {
      return target;
    } else if (valorComparacao < 0) {
      return findNode(element, target.left);
    } else {
      return findNode(element, target.right);
    }
  }

  /**
   * chama o metodo add passando o root e o elemento
   * 
   * @param element o elemento a ser adicionado
   */
  public void add(Integer element) { // O (log n)
    if (element == null) {
      throw new IllegalArgumentException("O elemento nao pode ser nulo!");
    }

    root = add(root, element, null);
    count++;
  }

  /**
   * Insere um novo elemento na arvore e balancea a sub-arvore de onde for
   * adicionado
   * 
   * @param n       nodo atual a ser comparado
   * @param elem    o elemento a ser adicionado
   * @param nodoPai o nodo pai do nodo atual
   * @return a sub-arvore balanceada
   */
  public Node add(Node n, Integer elem, Node nodoPai) { // O (log n)
    if (n == null) { // insere
      Node aux = new Node(elem);
      aux.father = nodoPai;
      return aux;
    }

    int valorComparacao = n.element.compareTo(elem);

    if (valorComparacao > 0) {
      n.left = add(n.left, elem, n);
    } else if (valorComparacao < 0) {
      n.right = add(n.right, elem, n);
    } else {
      return n;
    }

    n.size = 1 + subSize(n.left) + subSize(n.right);
    n.height = 1 + Math.max(subHeight(n.left), subHeight(n.right));

    return balance(n);
  }

  //
  // Caminhamentos
  //
  public LinkedListOfInteger positionsPre() {
    LinkedListOfInteger res = new LinkedListOfInteger();
    positionsPreAux(root, res);

    return res;
  }

  private void positionsPreAux(Node n, LinkedListOfInteger res) { // O (log n)
    if (n != null) {
      res.add(n.element);
      positionsPreAux(n.left, res);
      positionsPreAux(n.right, res);
    }
  }

  public LinkedListOfInteger positionsPos() {
    LinkedListOfInteger res = new LinkedListOfInteger();
    positionsPosAux(root, res);

    return res;
  }

  private void positionsPosAux(Node n, LinkedListOfInteger res) { // O (log n)
    if (n != null) {
      positionsPosAux(n.left, res);
      positionsPosAux(n.right, res);
      res.add(n.element);
    }
  }

  public LinkedListOfInteger positionsCentral() {
    LinkedListOfInteger res = new LinkedListOfInteger();
    positionsCentralAux(root, res);

    return res;
  }

  private void positionsCentralAux(Node n, LinkedListOfInteger res) { // O (log n)
    if (n != null) {
      positionsCentralAux(n.left, res);
      res.add(n.element);
      positionsCentralAux(n.right, res);
    }
  }

  public LinkedListOfInteger positionsWidth() { // O (log n)
    LinkedListOfInteger res = new LinkedListOfInteger();
    Queue<Node> fila = new Queue<Node>();

    Node atual = null;

    if (root != null) {
      fila.enqueue(root);

      while (!fila.isEmpty()) {
        atual = fila.dequeue();

        if (atual.left != null) {
          fila.enqueue(atual.left);
        }

        if (atual.right != null) {
          fila.enqueue(atual.right);
        }

        res.add(atual.element);
      }
    }

    return res;
  }

  //
  // Rotações
  //

  /**
   * Rotaciona sub-arvore para a direita
   * 
   * @param subTree a sub-arvore
   * @return a subTree rotacionada para direita
   */
  private Node rotateRight(Node subTree) { // O (1)
    Node aux = subTree.left;
    subTree.left = aux.right;
    aux.right = subTree;
    aux.size = subTree.size;
    subTree.size = 1 + subSize(subTree.left) + subSize(subTree.right);
    subTree.height = 1 + Math.max(subHeight(subTree.left), subHeight(subTree.right));
    aux.height = 1 + Math.max(subHeight(aux.left), subHeight(aux.right));

    return aux;
  }

  /**
   * Rotaciona sub-arvore para a esquerda
   * 
   * @param subTree a sub-arvore
   * @return a subTree rotacionada para esquerda
   */
  private Node rotateLeft(Node subTree) { // O (1)
    Node aux = subTree.right;
    subTree.right = aux.left;
    aux.left = subTree;
    aux.size = subTree.size;
    subTree.size = 1 + subSize(subTree.left) + subSize(subTree.right);
    subTree.height = 1 + Math.max(subHeight(subTree.left), subHeight(subTree.right));
    aux.height = 1 + Math.max(subHeight(aux.left), subHeight(aux.right));

    return aux;
  }

  /**
   * Balanceia a sub-arvore
   * 
   * @param subTree a sub-arvore
   * @return a sub-arvore balanceada
   */
  private Node balance(Node subTree) { // O (1)
    int peso = balanceWeight(subTree);
    if (peso > 1) {
      if (balanceWeight(subTree.left) < 0) {
        subTree.left = rotateLeft(subTree.left);
      }
      subTree = rotateRight(subTree);
    } else if (peso < -1) {
      if (balanceWeight(subTree.right) > 0) {
        subTree.right = rotateRight(subTree.right);
      }
      subTree = rotateLeft(subTree);
    }

    return subTree;
  }

  /**
   * Define o peso da sub-arvore
   * 
   * @param subTree a sub-arvore
   * @return o peso da sub-arvore, -1, 0 e 1 significa que ela está balanceada
   */
  private int balanceWeight(Node subTree) { // O (1)
    return subHeight(subTree.left) - subHeight(subTree.right);
  }

  /**
   * Chama o metodo isBalanced, passando o root como nodo inicial
   */
  public boolean isBalanced() {
    return isBalanced(root);
  }

  private boolean isBalanced(Node n) { // O (log n)
    if (n != null) {
      int peso = balanceWeight(n);
      if (peso < -1 || peso > 1) {
        return false;
      }

      isBalanced(n.left);
      isBalanced(n.right);
    }

    return true;
  }

}
