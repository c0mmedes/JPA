package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // 하나의 카테고리에 여러아이템, 한 아이템도 여러 카테고리에 들어갈 수 있음
    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            // 내가 조인하는 애
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            // 반대쪽이 조인하는 애
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<Item> items = new ArrayList<>();
}
