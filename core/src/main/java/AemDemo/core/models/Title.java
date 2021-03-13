package AemDemo.core.models;

import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = Resource.class)

public class Title 
{


	@SlingObject
    private Resource currentResource;
    @SlingObject
    private ResourceResolver resourceResolver;
	private String title , description;
	
	 @PostConstruct
	    protected void init() {
	        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
	        Page currentPage = pageManager.getContainingPage(currentResource);

	        title = currentPage != null ? currentPage.getTitle() : "";
			description = currentPage != null ? currentPage.getDescription() : "";
	 
	    }
	 
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

}
