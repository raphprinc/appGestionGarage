package com.configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * Fichier d'initialisation de Spring
 */
 
public class AppGestionGarageInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppGestionGarageConfiguration.class };
    }
   
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
   
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
     
    @Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter() };
        return singleton;
    }
}
