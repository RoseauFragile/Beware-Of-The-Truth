package bewareofthetruth.controller.ui;

import bewareofthetruth.contract.maininterfaces.Order;

public class UserOrder {
	private Order order;

	public UserOrder(final Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(final Order order) {
		this.order = order;
	}
}
