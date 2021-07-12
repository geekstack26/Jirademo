
package com.geekstack.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id" })
public class Issuetype {

	@JsonProperty("id")
	private String id;

	@JsonProperty("id")
	public String getid() {
		return id;
	}

	@JsonProperty("id")
	public void setid(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Issuetype [id=" + id + "]";
	}

}
