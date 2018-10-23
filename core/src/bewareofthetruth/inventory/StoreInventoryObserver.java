package bewareofthetruth.inventory;

public interface StoreInventoryObserver {
    public static enum StoreInventoryEvent {
        PLAYER_GP_TOTAL_UPDATED,
        PLAYER_INVENTORY_UPDATED
    }

    void onNotify(String value, StoreInventoryEvent event);
}