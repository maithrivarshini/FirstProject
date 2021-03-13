package AemDemo.core.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;


@Component(service=ReadExcel.class, immediate = true, name="ExcelReadServiceComponent")
public class ReadExcelImlp implements ReadExcel 
{

	@ValueMapValue
	private List<String> nameList;
    @ValueMapValue
	private List<String> numberList;
    
	
	 
    @Activate
    @Modified
    protected void activate() throws Exception
    {
    	nameList = new ArrayList<String>();
    	numberList = new ArrayList<String>();
    	File file = new File("C:\\Users\\Maithri\\Downloads\\Maithri1.xlsx");
    	FileInputStream fis = new FileInputStream(file);
    	XSSFWorkbook wb = new XSSFWorkbook(fis);
    	XSSFSheet sheet = wb.getSheetAt(0);
    
       int row = sheet.getPhysicalNumberOfRows();
       for(int i = 0; i < row; i++)
       {
    	String name = String.valueOf(sheet.getRow(i).getCell(0).getStringCellValue());
    	String MobileNumber = sheet.getRow(i).getCell(i).getStringCellValue();
    	nameList.add(name);
    	numberList.add(MobileNumber);
       }
    
    }

	@Override
	public List<String> getName() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<String> getNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
