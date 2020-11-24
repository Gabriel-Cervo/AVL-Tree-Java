
public class EmptyQueueException extends RuntimeException {

  /**
   * Throws a RuntimeException if the queue is empty
   */
  private static final long serialVersionUID = 1L;

  public EmptyQueueException(String message) {
    super(message);
  }

}
