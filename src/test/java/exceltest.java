import com.util.ReadExcel;

public class exceltest {

	public static void main(String[] args) {
		String filepath="E:\\testData\\YKKData.xlsx";
		String sheetName="YKKData1";
		
	Object[][] objects=	ReadExcel.readExcel_XLSX(filepath, sheetName);
		for(int i=0;i<objects.length;i++) {
			
			for (int j = 0; j < objects[i].length; j++) {
				
				System.out.println(objects[i][j]+"");
				
			}
			
		}
		
	}

}
