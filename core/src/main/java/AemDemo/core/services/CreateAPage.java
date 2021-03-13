package AemDemo.core.services;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(immediate = true, name = "Create Page Service", service = CreateAPage.class)

public class CreateAPage 
{
	@Reference
	private ResourceResolverFactory resolverFactory;
	private ResourceResolver resourceResolver; 
	@Activate
	@Modified
	private void createPage() throws Exception {
	 String path="/content/poc";
	 String pageName="samplePage";
	 String pageTitle="Sample Page";
	 String template="/apps/geometrixx/templates/homepage";
	 String renderer="geometrixx/components/homepage";

	 this.resourceResolver = this.resolverFactory.getResourceResolver(null);
	    Page prodPage = null;
	    Session session = this.resourceResolver.adaptTo(Session.class);
	    try { 
	    if (session != null) {
	 
	    // Create Page      
	    PageManager pageManager = this.resourceResolver.adaptTo(PageManager.class);
	    prodPage = pageManager.create(path, pageName, template, pageTitle);
	    Node pageNode = prodPage.adaptTo(Node.class);
	 
	 Node jcrNode = null;
	 if (prodPage.hasContent()) {

	 jcrNode = prodPage.getContentResource().adaptTo(Node.class);
	 } else {                   
	 jcrNode = pageNode.addNode("jcr:content", "cq:PageContent");
	 } 
	           jcrNode.setProperty("sling:resourceType", renderer);
	     
	     
	         Node parNode = jcrNode.addNode("par");
	         parNode.setProperty("sling:resourceType", "foundation/components/parsys");
	 
	  Node textNode = parNode.addNode("text");
	  textNode.setProperty("sling:resourceType", "foundation/components/text");
	  textNode.setProperty("textIsRich", "true");
	  textNode.setProperty("text", "Test page"); 
	 
	 session.save();
	        session.refresh(true);
	  }
	           
	 } catch (Exception e) {
	 throw e; 
	 }  
	   }    
}
