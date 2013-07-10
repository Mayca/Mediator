package Mediator.Api;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class NotifyingThreads extends Thread {

    private final Set<ThreadCompleteListener> listeners = new CopyOnWriteArraySet<ThreadCompleteListener>();

    public final void addListener(final ThreadCompleteListener listener) {
        listeners.add(listener);
    }

    public final void removeListener(final ThreadCompleteListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(int result, int id) {
        for (ThreadCompleteListener listener : listeners) {
            listener.notifyOfThreadComplete(this,result,id);
           
        }
    }

    @Override
    public final void run() {
        int[] id=null;
        
            id =doRun();
       
    }

    public abstract int[] doRun();
}
