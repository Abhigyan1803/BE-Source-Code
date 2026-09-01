package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Menu  implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long menuId;
    
    @Lob
    private String description;
    //if menu is deleted then isDeleted =1, for active menu isDeleted=0
    private int isDeleted;

   
   

	

	public long getMenuId() {
		return menuId;
	}


	public int getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}


	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	

    
    
    
}
