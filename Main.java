class Main {
  public static void main(String[] args) {
    AVLTreeOfInteger arvore = new AVLTreeOfInteger();
    for (int i = 0; i <= 10; i++) {
      arvore.add(i);
    }

    System.out.println("Árvore está balanceada: " + arvore.isBalanced());
    System.out.println("Altura da árvore: " + arvore.height());

    System.out.println("\nCaminhamento pré-fixado: ");
    System.out.println(arvore.positionsPre());

    System.out.println("Caminhamento pós-fixado: ");
    System.out.println(arvore.positionsPos());

    System.out.println("Caminhamento central: ");
    System.out.println(arvore.positionsCentral());

    System.out.println("Caminhamento por largura: ");
    System.out.println(arvore.positionsWidth());

  }
}