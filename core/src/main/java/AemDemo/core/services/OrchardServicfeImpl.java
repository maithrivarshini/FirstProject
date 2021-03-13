package AemDemo.core.services;

import org.osgi.service.component.annotations.Component;

@Component(service = OrchardService.class , immediate = true)
public class OrchardServicfeImpl implements OrchardService{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "My message from Orchard";
	}

}
