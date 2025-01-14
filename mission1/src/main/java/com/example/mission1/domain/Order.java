package com.example.mission1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @EmbeddedId
    private OrderId id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    public Order(OrderId id) {
        this.id = id;
        createdAt = LocalDateTime.now();
    }
}
