package com.epam.rd.autotask.io.serialization;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class Order implements Serializable {
    private Long id;
    private BigDecimal total;
    private Map<Item, Integer> items;

    public Order(Long id, Map<Item, Integer> items) {
        this.id = id;
        this.items = items;
        this.total = calculateTotal();
    }

    private BigDecimal calculateTotal() {
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            sum = sum.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        if (total == null) {
            total = calculateTotal();
        }
        return total;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
        this.total = calculateTotal();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return Objects.equals(id, order.id) &&
                Objects.equals(total, order.total) &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total, items);
    }
}
