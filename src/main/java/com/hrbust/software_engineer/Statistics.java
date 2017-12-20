package com.hrbust.software_engineer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class Statistics {
	public static HSSFWorkbook workbook = null; //相当于 Excel 整个文件
	public static FileOutputStream fos = null;
	public static Vector<String> verFile=null;
	
	public static void ExcelBuild(String FilePath) throws IOException 
	{	
		//ProcessWithData("\\html",".\\htmlResult.xls");
		//ExcelBuild(".\\Result.xls");
		workbook= new HSSFWorkbook(); //相当于 Excel 整个文件
		try 
		{
			fos = new FileOutputStream(FilePath);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HSSFSheet sheet = workbook.createSheet("sheet1"); //生成 Excel 中的 shee
		HSSFRow row = sheet.createRow(0); //创建第一行
		HSSFCell cell = row.createCell(0); //创建第一个单元格
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("文件名称:"); //设定单元格的名字
				 cell = row.createCell(1); //创建第二个单元格
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("Id:");
				 cell = row.createCell(2);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("姓名:");
				 cell = row.createCell(3);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(4);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("性别:");
				 cell = row.createCell(5);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(6);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("住址:");
				 cell = row.createCell(7);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(8);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("国别:");
				 cell = row.createCell(9);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(10);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("身份证号:");
				 cell = row.createCell(11);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(12);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("电话号码:");
				 cell = row.createCell(13);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(14);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出生日期:");
				 cell = row.createCell(15);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(16);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("QQ 号码:");
				 cell = row.createCell(17);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(18);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("E-mail:");
				 cell = row.createCell(19);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(20);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("MSN:");
				 cell = row.createCell(21);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(22);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("附加关键字:");
				 cell = row.createCell(23);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(24);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("照片:");
				 cell = row.createCell(25);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("出现频率:");
				 cell = row.createCell(26);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("本文总词数:");
				 /**至此为止，表头部分就定义好了**/
	}
	
	public static void ExcelAddWrite(String sheetname,int Row,int Col,Object Data,int CellType)
	throws IOException, IOException
	{
		HSSFRow row=null;
		HSSFCell cell=null;
		HSSFSheet sheet=workbook.getSheet(sheetname);
		if(sheet==null)
		{
			try {
				sheet=workbook.createSheet(sheetname);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(sheet.getLastRowNum()<Row)
		{
			try 
			{
				row = sheet.createRow(Row);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			row = sheet.getRow(Row);
		}
	
		if(row!=null)
		{
			if(row.getLastCellNum()-1<Col) //创建第一个单元格
			{
				cell = row.createCell(Col);
			}
			else
				cell = row.getCell(Col);
		}
		cell.setCellType(CellType);
		switch (CellType)
		{
			case HSSFCell.CELL_TYPE_STRING:cell.setCellValue((String) Data);break;
			case HSSFCell.CELL_TYPE_NUMERIC:cell.setCellValue((Long)Data);break;
		}
	//workbook.write(fos);
	}
	public static long HowManyWordInTheAriticle(String AriticleFilePath) throws IOException
	{
		long Length=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(AriticleFilePath)))); // 建立一个对象，它把文件内容转成计算机能读懂的语言
		while (br.readLine()!= null) ++Length;
		br.close();
		return Length;
	}
	public static void ExcelClose()
	{
	try {
	//将这个文件交给 HSSFWorkbook 类 由它负责写入
	workbook.write(fos);
	fos.flush();
	//关闭输出流
	fos.close();
	} catch (IOException e1) {
	e1.printStackTrace();
	}
	}
	public static void FileList(String FilePath)
	{
	verFile = new Vector<String>();
	File[] files = new File(FilePath).listFiles();
	int len=files.length;
	for(int i=0;i<len;i++)
	{
	String tmp=files[i].getName();
	if(!files[i].isDirectory())
	verFile.add(tmp);
	}
	}
	public static long Frequent(String FilePath,String Word) throws IOException, FileNotFoundException
	{
	long FreHz=0;
	File filename = new File(FilePath); // 要读取以上路径的 input.txt 文件
	InputStreamReader reader = new InputStreamReader(
	new FileInputStream(filename),"GBK"); // 建立一个输入流对象 reader
	BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
	String line = "";
	line = br.readLine();
	while (line != null)
	{
	if(Word.contains(","))
	{
	//拆分匹配
	String[] strArray = null;
	strArray = Word.split(",");
	for(int i=0;i<strArray.length;i++)
	{
	if(line.equals(strArray[i]))
	++FreHz;
	//System.out.print(strArray[i]+"\n");
	}
	}
	else
	{
	if(line.equals(Word))
	++FreHz;
	}
	line=br.readLine();
	}
	br.close();
	return FreHz;
	}
	
	
	public static void ProcessWithData(String FileNameForFind,String OutPutExcelName) throws IOException
	{
		//ProcessWithData("\\html",".\\htmlResult.xls");
		//ExcelBuild(".\\Result.xls");
		//ok
		ExcelBuild(OutPutExcelName);
		
		//读取 Check.txt;通过 Id 和名字读取 divresult 的文件夹；
		String FilePath="Check.txt";
		String line = "";
		InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(FilePath)),"GBK"); // 建立一个输入流对象reader
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			//第一次读取 Check.txt 必定得到第一个用户名字
		String UserInfohtmlDir="";//用户的分词文件路径
		long eRow=1;
		line = br.readLine();
		long UserID=1;
		//由于 Check.txt 中对每一个用户有用##############隔开；扫描到这个说明用户信息搜索完毕；但#####&&#######代表 excel 中的括号。
		while(line != null)//文件结束
		{
			//String FileNameForFind="\\txt";
			UserInfohtmlDir=".\\divresult"+FileNameForFind+"\\"+"ID_"+Long.toString(UserID)+"_Name_"+line;//遍历 html 文件夹
			FileList(UserInfohtmlDir);//获取全部文件名字
			Vector<String> ExcelTxtBuffer=new Vector<String>();//缓存当前用户信息
			while (!line.contains("###################################"))
			{
				ExcelTxtBuffer.add(line);
				line=br.readLine();
			}
			while(!verFile.isEmpty())
			{
				//遍历路径下的所有文件；进行词频统计
				//System.out.print(UserInfohtmlDir+"\\"+verFile.get(0)+"\n");
				//打开一个文件；对其进行遍历
				//写下文件名字
				String outputFileName=verFile.get(0);
				if(FileNameForFind.contains("\\html"))
					ExcelAddWrite("sheet1",(int)eRow,(int)0,outputFileName.replaceAll("output_","").replaceAll(".txt", ".html"),HSSFCell.CELL_TYPE_STRING);
				else ExcelAddWrite("sheet1",(int)eRow,(int)0,outputFileName.replaceAll("output_",""),HSSFCell.CELL_TYPE_STRING);
				//写入 ID
				ExcelAddWrite("sheet1",(int)eRow,(int)1,UserID,HSSFCell.CELL_TYPE_NUMERIC);

				int i,j;
				for (i=0,j=2;i<ExcelTxtBuffer.size();i++,j+=2)
				{
					String WoldToFind=ExcelTxtBuffer.get(i);
					long WoldFreq=Frequent(UserInfohtmlDir+"\\"+verFile.get(0),WoldToFind);// 词频统计
					ExcelAddWrite("sheet1",(int)eRow,(int)j,WoldToFind,HSSFCell.CELL_TYPE_STRING);//写入关键字
					ExcelAddWrite("sheet1",(int)eRow,(int)j+1,WoldFreq,HSSFCell.CELL_TYPE_NUMERIC);//写入词频
				}
					ExcelAddWrite("sheet1",(int)eRow,(int)j,HowManyWordInTheAriticle(UserInfohtmlDir+"\\"+outputFileName),HSSFCell.CELL_TYPE_NUMERIC);//写入文章词数
					++eRow; //行加 1
					verFile.remove(0);//移除已经完成搜索的文件
			}
				ExcelTxtBuffer.clear();
				line=br.readLine();
				++UserID;
		}
				br.close();
				ExcelClose();
				System.out.print("Complish!100%\n");
	}
	public static void main(String[] args) throws IOException
	{
		//String FileNameForFind,String OutPutExcelName
		ProcessWithData("\\html",".\\htmlResult.xls");
		ProcessWithData("\\txt",".\\txtResult.xls");
	}
}
