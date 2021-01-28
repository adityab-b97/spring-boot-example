package com.mdits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Department implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int dNumber;
	private String Name;
}
