package org.primefaces.diamond.service.util;

public enum ServiceApi {
	
	AUTHENTICATE("/api/v1/auth/authenticate"),
	SEARCH_BY_EMAIL("/api/v1/app/users/filter/{0}") 
	;
	
	private String uri;
	
	private ServiceApi(String uri) {
		this.uri = uri;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}

}
