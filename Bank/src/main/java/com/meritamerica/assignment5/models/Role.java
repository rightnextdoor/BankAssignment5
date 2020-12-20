package com.meritamerica.assignment5.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    private String authority;
    
    
    public Integer getId() {
        return id;
    }


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
