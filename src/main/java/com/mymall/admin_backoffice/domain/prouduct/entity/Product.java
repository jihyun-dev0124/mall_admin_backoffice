package com.mymall.admin_backoffice.domain.prouduct.entity;

import com.mymall.admin_backoffice.domain.prouduct.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private Integer stock = 0;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ProductStatus status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
