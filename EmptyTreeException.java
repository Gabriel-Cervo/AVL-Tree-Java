public class EmptyTreeException extends RuntimeException {

  /**
   * Throws a RuntimeException if a Tree is empty
   */
  private static final long serialVersionUID = 1L;

  public EmptyTreeException(String message) {
    super(message);
  }

  public EmptyTreeException() {
  }

}
