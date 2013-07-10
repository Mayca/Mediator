
package Mediator.Api;

public interface ThreadCompleteListener {
    void notifyOfThreadComplete(final Thread thread, int result, int id);
}