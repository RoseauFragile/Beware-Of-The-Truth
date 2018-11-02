package bewareofthetruth.inventory;

public interface BarInventorySubject {
    public void addObserver(BarInventoryObserver barObserver);
    public void removeObserver(BarInventoryObserver barObserver);
    public void removeAllObservers();
    public void notify(String value, BarInventoryObserver.BarInventoryEvent event);
}
