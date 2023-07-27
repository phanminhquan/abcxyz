package com.mtb.foodorderreview.global;

import com.mtb.foodorderreview.something.Order;
import com.mtb.foodorderreview.utils.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public class OrderGlobal {
    private Order order;
    private List<IChangeListener<OrderGlobal>> changeListeners = new ArrayList<>();
    private static final OrderGlobal instance = new OrderGlobal();

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public static OrderGlobal getInstance() {
        return instance;
    }


    private OrderGlobal() {
    }

    public void reset() {
        order = null;
        runListener();
    }

    public void addListener(IChangeListener<OrderGlobal> changeListener) {
        for (IChangeListener<OrderGlobal> c : changeListeners) {
            if (c.getId() == changeListener.getId()) return;
        }

        this.changeListeners.add(changeListener);
    }

    private void runListener() {
        this.changeListeners.forEach(r -> {
            r.dataChange(instance);
        });
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        runListener();
    }
}
