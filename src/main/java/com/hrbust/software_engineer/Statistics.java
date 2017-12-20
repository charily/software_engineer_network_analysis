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
	public static HSSFWorkbook workbook = null; //�൱�� Excel �����ļ�
	public static FileOutputStream fos = null;
	public static Vector<String> verFile=null;
	
	public static void ExcelBuild(String FilePath) throws IOException 
	{	
		//ProcessWithData("\\html",".\\htmlResult.xls");
		//ExcelBuild(".\\Result.xls");
		workbook= new HSSFWorkbook(); //�൱�� Excel �����ļ�
		try 
		{
			fos = new FileOutputStream(FilePath);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HSSFSheet sheet = workbook.createSheet("sheet1"); //���� Excel �е� shee
		HSSFRow row = sheet.createRow(0); //������һ��
		HSSFCell cell = row.createCell(0); //������һ����Ԫ��
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("�ļ�����:"); //�趨��Ԫ�������
				 cell = row.createCell(1); //�����ڶ�����Ԫ��
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("Id:");
				 cell = row.createCell(2);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����:");
				 cell = row.createCell(3);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(4);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("�Ա�:");
				 cell = row.createCell(5);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(6);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("סַ:");
				 cell = row.createCell(7);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(8);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����:");
				 cell = row.createCell(9);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(10);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("���֤��:");
				 cell = row.createCell(11);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(12);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("�绰����:");
				 cell = row.createCell(13);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(14);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("��������:");
				 cell = row.createCell(15);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(16);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("QQ ����:");
				 cell = row.createCell(17);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(18);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("E-mail:");
				 cell = row.createCell(19);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(20);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("MSN:");
				 cell = row.createCell(21);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(22);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("���ӹؼ���:");
				 cell = row.createCell(23);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(24);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("��Ƭ:");
				 cell = row.createCell(25);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("����Ƶ��:");
				 cell = row.createCell(26);
				 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				 cell.setCellValue("�����ܴ���:");
				 /**����Ϊֹ����ͷ���־Ͷ������**/
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
			if(row.getLastCellNum()-1<Col) //������һ����Ԫ��
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
				new FileInputStream(new File(AriticleFilePath)))); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
		while (br.readLine()!= null) ++Length;
		br.close();
		return Length;
	}
	public static void ExcelClose()
	{
	try {
	//������ļ����� HSSFWorkbook �� ��������д��
	workbook.write(fos);
	fos.flush();
	//�ر������
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
	File filename = new File(FilePath); // Ҫ��ȡ����·���� input.txt �ļ�
	InputStreamReader reader = new InputStreamReader(
	new FileInputStream(filename),"GBK"); // ����һ������������ reader
	BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
	String line = "";
	line = br.readLine();
	while (line != null)
	{
	if(Word.contains(","))
	{
	//���ƥ��
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
		
		//��ȡ Check.txt;ͨ�� Id �����ֶ�ȡ divresult ���ļ��У�
		String FilePath="Check.txt";
		String line = "";
		InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(FilePath)),"GBK"); // ����һ������������reader
			BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
			//��һ�ζ�ȡ Check.txt �ض��õ���һ���û�����
		String UserInfohtmlDir="";//�û��ķִ��ļ�·��
		long eRow=1;
		line = br.readLine();
		long UserID=1;
		//���� Check.txt �ж�ÿһ���û�����##############������ɨ�赽���˵���û���Ϣ������ϣ���#####&&#######���� excel �е����š�
		while(line != null)//�ļ�����
		{
			//String FileNameForFind="\\txt";
			UserInfohtmlDir=".\\divresult"+FileNameForFind+"\\"+"ID_"+Long.toString(UserID)+"_Name_"+line;//���� html �ļ���
			FileList(UserInfohtmlDir);//��ȡȫ���ļ�����
			Vector<String> ExcelTxtBuffer=new Vector<String>();//���浱ǰ�û���Ϣ
			while (!line.contains("###################################"))
			{
				ExcelTxtBuffer.add(line);
				line=br.readLine();
			}
			while(!verFile.isEmpty())
			{
				//����·���µ������ļ������д�Ƶͳ��
				//System.out.print(UserInfohtmlDir+"\\"+verFile.get(0)+"\n");
				//��һ���ļ���������б���
				//д���ļ�����
				String outputFileName=verFile.get(0);
				if(FileNameForFind.contains("\\html"))
					ExcelAddWrite("sheet1",(int)eRow,(int)0,outputFileName.replaceAll("output_","").replaceAll(".txt", ".html"),HSSFCell.CELL_TYPE_STRING);
				else ExcelAddWrite("sheet1",(int)eRow,(int)0,outputFileName.replaceAll("output_",""),HSSFCell.CELL_TYPE_STRING);
				//д�� ID
				ExcelAddWrite("sheet1",(int)eRow,(int)1,UserID,HSSFCell.CELL_TYPE_NUMERIC);

				int i,j;
				for (i=0,j=2;i<ExcelTxtBuffer.size();i++,j+=2)
				{
					String WoldToFind=ExcelTxtBuffer.get(i);
					long WoldFreq=Frequent(UserInfohtmlDir+"\\"+verFile.get(0),WoldToFind);// ��Ƶͳ��
					ExcelAddWrite("sheet1",(int)eRow,(int)j,WoldToFind,HSSFCell.CELL_TYPE_STRING);//д��ؼ���
					ExcelAddWrite("sheet1",(int)eRow,(int)j+1,WoldFreq,HSSFCell.CELL_TYPE_NUMERIC);//д���Ƶ
				}
					ExcelAddWrite("sheet1",(int)eRow,(int)j,HowManyWordInTheAriticle(UserInfohtmlDir+"\\"+outputFileName),HSSFCell.CELL_TYPE_NUMERIC);//д�����´���
					++eRow; //�м� 1
					verFile.remove(0);//�Ƴ��Ѿ�����������ļ�
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
