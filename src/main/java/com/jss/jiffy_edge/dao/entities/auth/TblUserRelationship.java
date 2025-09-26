package com.jss.jiffy_edge.dao.entities.auth;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_user_relationship")
public class TblUserRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private TblUsers manager;

    @ManyToOne
    @JoinColumn(name = "subordinate_id", nullable = false)
    private TblUsers subordinate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TblUsers getManager() {
        return manager;
    }

    public void setManager(TblUsers manager) {
        this.manager = manager;
    }

    public TblUsers getSubordinate() {
        return subordinate;
    }

    public void setSubordinate(TblUsers subordinate) {
        this.subordinate = subordinate;
    }
}