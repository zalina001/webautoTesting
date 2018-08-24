package main.java;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.util.ReadExcel;

public class SuZhouBuPinShanghaiCheck {

	public static void main(String[] args) {
		File factoryFile = new File("D:\\微软项目文档\\核对数据\\部品2018-07-01之后.xls");
		File systemFile = new File("D:\\微软项目文档\\核对数据\\7月AP上海社.xlsx");
		ReadExcel readExcel = new ReadExcel();
//		List factoryMap=readExcel.switchReadExcel(factoryFile, 30,10);
		
//		readExcel.switchReadExcel(systemFile, 8);

//		System.out.println(factoryMap.keySet()+"---------");
//		System.out.println(factoryMap.values());
//		
//		System.out.println(systemMap.keySet()+"------------");
//		System.out.println(systemMap.values());
//		System.out.println(factorySalesOrder_no.get(0) + "-----------" + factoryShipement_amount.get(0));

		
		
//		List<String> shanghaiOrder_no = readExcel.switchReadExcel(shanghaiFile, 8);
//		List<String> shanghaiTotalMoney = readExcel.switchReadExcel(shanghaiFile, 20);


	}

}
