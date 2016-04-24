package br.edu.ifpb.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import br.edu.ifpb.service.UserService;

public class ExercicioRevisao1Application extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public ExercicioRevisao1Application() {
		
		CorsFilter filter = new CorsFilter();
		filter.getAllowedOrigins().add("*");
		
		this.singletons.add(filter);
		this.singletons.add(new UserService());
	}

	public Set<Class<?>> setSingletons() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}	

}
