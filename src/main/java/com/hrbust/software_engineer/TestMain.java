package com.hrbust.software_engineer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ICTCLAS.I3S.AC.ICTCLAS50;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestMain {
	public static ICTCLAS50 testICTCLAS50;
	public static String FileReplace(String FilePath)//OK//���˿ո�
	{
		try 
		{ 
				// ��ֹ�ļ��������ȡʧ�ܣ��� catch ��׽���󲢴�ӡ��Ҳ���� throw
				/* ���� TXT �ļ� */
			String FinalString="";
				//String pathname = ".\\data\\jn_10831429.html"; // ����·�������·�������ԣ������Ǿ���·����д���ļ�ʱ��ʾ���·��
			File filename = new File(FilePath); 
				// Ҫ��ȡ����·���� input.txt �ļ�
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"GBK"); // ����һ������������ reader
			BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
			String line = "";
			line = br.readLine();
			String lineGuoLv;
			while (line != null) 
			{
				lineGuoLv=line.replace("|", "").replace(" ", "").replaceAll("\n\r","");
				FinalString+=lineGuoLv;
					//System.out.print(lineGuoLv);
				line = br.readLine(); // һ�ζ���һ������
			}
			br.close();
			return FinalString;
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			return "404htm";
		}
	}
	public static void StringOutToFile(String str,String FilePath)//ok
	{
		try
		{
			File writename = new File(FilePath); // ���·�������û����Ҫ����һ���µ� output�� txt �ļ�
			writename.createNewFile(); // �������ļ�
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.append(str);
			/* д�� Txt �ļ� */
			out.flush(); // �ѻ���������ѹ���ļ�
			out.close(); // ���ǵùر��ļ�
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void ExcelAddToTxtFile(String ExcelFilePath,String TxtFilePath)//ok
	{
		//ExcelAddToTxtFile("userinfo.xls","userdict.txt");
		try 
		{
			File writename = new File(TxtFilePath); // ���·�������û����Ҫ����һ���µ�output.txt �ļ�
					writename.createNewFile(); // �������ļ�
				System.out.println("---writename"+writename.exists());
				System.out.println("---writename"+writename.getAbsolutePath());
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			
			File writeCheckname = new File("Check.txt"); // ���·�������û����Ҫ����һ���µ�output.txt �ļ�
					writeCheckname.createNewFile(); // �������ļ�
				System.out.println("---writeCheckname"+writeCheckname.exists());
			BufferedWriter check = new BufferedWriter(new FileWriter(writeCheckname));
			
			Sheet sheet;
			Cell cell1;
			String StringTxt="";
			
			Workbook book=Workbook.getWorkbook(new File(ExcelFilePath));
			
			sheet=book.getSheet(0);
			for(int i=1;i<sheet.getRows();i++)
			{
				for(int j=1;j<sheet.getColumns();j++)
				{
					cell1=sheet.getCell(j,i);
					StringTxt=cell1.getContents();
					if(!("".equals(StringTxt)))
					{
						if(!"�й�".equals(StringTxt))
						{
							if(!"Ů".equals(StringTxt)&&!"��".equals(StringTxt))
							{
								if(StringTxt.contains(","))
								{
									StringTxt=replaceBlank(StringTxt,",");
								}
								out.append(StringTxt+"@@EX\r\n");
								check.append(StringTxt+"\r\n");
							}
						}
					}
				}
				out.append("*************************************"+"@@EX\r\n");
				check.append("###################################\r\n");
			}
			book.close();
			out.flush(); // �ѻ���������ѹ���ļ�
			out.close(); // ���ǵùر��ļ�
			check.flush();
			check.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static String replaceBlank(String strsrc,String Strtoreplase)
	{
		String dest = "";
		if (strsrc!=null&&Strtoreplase!=null) 
		{
			Pattern p = Pattern.compile(Strtoreplase);
			Matcher m = p.matcher(strsrc);
			dest = m.replaceAll("\r\n");
		}
		return dest;
	}
	public static void Init() throws IOException
	{
	//	System.out.println("#####1");
		//�����ִʶ���
		testICTCLAS50 = new ICTCLAS50();
	//	System.out.println("#####2");
		String argu = ".";
		//��ʼ��
		if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
		{
			System.out.println("Init Fail!");
			return;
		}
		//���ô��Ա�ע��(0 ������������ע���� 1 ������һ����ע���� 2 ���������ע���� 3 ����һ����ע��)
		testICTCLAS50.ICTCLAS_SetPOSmap(2);
	//	System.out.println("#####4");
		ExcelAddToTxtFile("userinfo.xls","userdict.txt");
	//	System.out.println("#####5");
		int nCount = 0;
	//	System.out.println("#####6");
		String usrdir = "userdict.txt"; //�û��ֵ�·��
	//	System.out.println("#####7");
		byte[] usrdirb = usrdir.getBytes();//�� string ת��Ϊ byte ����
	//	System.out.println("#####8");
		//�����û��ֵ�,���ص����û����������һ������Ϊ�û��ֵ�·�����ڶ�������Ϊ�û���ı�������
		nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
	//	System.out.println("#####9");
		System.out.println("�����û��ʸ���" + nCount);
		nCount = 0;
	}
	public static void BuildingResult(String INFilePath,
									  String OutFilePath,
									  String FileNameBuild,
									  long Index) throws IOException
	{
		//System.out.println("****start");
		String text=FileReplace(INFilePath);
		//System.out.println("****1");
		InputStreamReader reader = new InputStreamReader(
				new FileInputStream(new File("Check.txt"))); // ����һ������������ reader
		//System.out.println("****2");
		BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
		//System.out.println("****3");
		String line = "";
		line = br.readLine();
		//System.out.println("****4");
		byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(text.getBytes("GB2312"), 0,0);//�ִʴ���
		//System.out.println("****5");
		String nativeStr = new String(nativeBytes, 0, nativeBytes.length);
		//System.out.println("****6");
		nativeStr=replaceBlank(nativeStr," ");
		//System.out.println("****7");
		if(!nativeStr.matches("404\r\n"+"\u003C\r\n"+"\u002F\r\n"+"htm\r\n"))
		{
			//System.out.println("****8");
			String FileName=OutFilePath;
			long i=1;
			while(line!=null&&line!="\r\n")
			{
				//System.out.print(i+"\n");
				//System.out.print(line+"\n");
				FileName=OutFilePath+"\\ID_"+Long.toString(i)+"_Name_"+line;
				File dirFile = null ;
				dirFile = new File(FileName);
				if (!(dirFile.exists())&&!(dirFile.isDirectory()))
				{
					boolean creadok=dirFile.mkdirs();
					if (creadok)
					{
						System.out.println("ok:�����ļ��гɹ�!");
					}
					else
					{
						System.out.println( "err:�����ļ���ʧ��!");
					}
				}
				while(!line.contains("######"))
				{
					if(nativeStr.contains(line))
					{
						FileWriter Consoleout = new FileWriter("Console.txt", true);
						Consoleout.write(FileName+FileNameBuild+" ContainKeyWord:"+line+"\r\n");
						System.out.print(FileName+FileNameBuild+" ContainKeyWord:"+line+"\r\n");
						StringOutToFile(nativeStr,FileName+FileNameBuild);
						Consoleout.flush();
						Consoleout.close();
					}
					line = br.readLine();
				}
				line = br.readLine();
				i++;
			}
		}
		br.close();
	}
	public static void SystemQuit()
	{
		if(testICTCLAS50.ICTCLAS_Exit()) System.out.print("QuitSystem!");
		else System.out.print("QuitSystem Error!");
	}
	public static void main(String[] args) throws IOException, BiffException 
	{
		Init();
		for(long i=10831429;i<10838261;i++)
		{
		//D:\eclipse _workplace\analysis\webdata
			BuildingResult(".\\webdata\\jn_"+Long.toString(i)+".html",
					".\\divresult\\html","\\output_jn_"+Long.toString(i)+".txt",i);
		/*	BuildingResult("F:/�߼��������/��2������ ������������/webdata/jn_"+Long.toString(i)+".html",
					"F:/�߼��������/��2������ ������������/",
					"F:/�߼��������/��2������ ������������/output_jn_"+Long.toString(i)+".txt",i);*/
		/*	public static void BuildingResult(String INFilePath,
					  						  String OutFilePath,
					  						  String FileNameBuild,
					  						  long Index) throws IOException*/
		}
		//System.out.print("Complish 25%!\n");
		for(long i=10831429;i<10838261;i++)
		{
			BuildingResult(".\\webdata\\jn_"+Long.toString(i)+".txt",
					".\\divresult\\txt","\\output_jn_"+Long.toString(i)+".txt",i);
		}
		//System.out.print("Complish 50%!\n");
		for(long i=1030935;i<1031937;i++)
		{
			BuildingResult(".\\webdata\\jw_"+Long.toString(i)+".html",
					".\\divresult\\html","\\output_jw_"+Long.toString(i)+".txt",i);
		}
		//System.out.print("Complish 75%!\n");
		for(long i=1030935;i<1031937;i++)
		{
			BuildingResult(".\\webdata\\jw_"+Long.toString(i)+".txt",
					".\\divresult\\txt","\\output_jw_"+Long.toString(i)+".txt",i);
		}
		System.out.print("Complish 100%!\n");
		SystemQuit();
	}
}
