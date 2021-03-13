package AemDemo.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import AemDemo.core.services.ReadExcelImlp;

@Model(adaptables = Resource.class)
public class ReadExcelModel 
{
	private List<String> nameList;
	private List<String> numberList;
	
	@OSGiService
	private ReadExcelImlp ers;
	
	@PostConstruct
	protected void init()
	{
		nameList = ers.getName();
		numberList = ers.getNumber();
	}

	public List<String> getNameList() {
		return nameList;
	}

	public List<String> getNumberList() {
		return numberList;
	}
	
}
