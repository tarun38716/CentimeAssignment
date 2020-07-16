package com.centime.modal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Node {

	@JsonProperty(value = "Name")
	private String name;

	@JsonProperty(value = "Sub Classes")
	@JsonInclude(Include.NON_NULL)
	private List<Node> childs;

	@JsonIgnore
	private Node parent;

	public Node () {
		super();
	}

	public Node(String name, List<Node> childs, Node parent) {
		super();
		this.name = name;
		this.childs = childs;
		this.parent = parent;
	}
}
