package bewareofthetruth.inventory;

public interface BarInventoryObserver {
    public static enum BarInventoryEvent {
        PLAYER_INVENTORY_UPDATED
    }

    void onNotify(String value, BarInventoryEvent event);
}
