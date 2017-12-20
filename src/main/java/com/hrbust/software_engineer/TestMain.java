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
	public static String FileReplace(String FilePath)//OK//过滤空格
	{
		try 
		{ 
				// 防止文件建立或读取失败，用 catch 捕捉错误并打印，也可以 throw
				/* 读入 TXT 文件 */
			String FinalString="";
				//String pathname = ".\\data\\jn_10831429.html"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
			File filename = new File(FilePath); 
				// 要读取以上路径的 input.txt 文件
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"GBK"); // 建立一个输入流对象 reader
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line = "";
			line = br.readLine();
			String lineGuoLv;
			while (line != null) 
			{
				lineGuoLv=line.replace("|", "").replace(" ", "").replaceAll("\n\r","");
				FinalString+=lineGuoLv;
					//System.out.print(lineGuoLv);
				line = br.readLine(); // 一次读入一行数据
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
			File writename = new File(FilePath); // 相对路径，如果没有则要建立一个新的 output。 txt 文件
			writename.createNewFile(); // 创建新文件
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.append(str);
			/* 写入 Txt 文件 */
			out.flush(); // 把缓存区内容压入文件
			out.close(); // 最后记得关闭文件
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
			File writename = new File(TxtFilePath); // 相对路径，如果没有则要建立一个新的output.txt 文件
					writename.createNewFile(); // 创建新文件
				System.out.println("---writename"+writename.exists());
				System.out.println("---writename"+writename.getAbsolutePath());
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			
			File writeCheckname = new File("Check.txt"); // 相对路径，如果没有则要建立一个新的output.txt 文件
					writeCheckname.createNewFile(); // 创建新文件
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
						if(!"中国".equals(StringTxt))
						{
							if(!"女".equals(StringTxt)&&!"男".equals(StringTxt))
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
			out.flush(); // 把缓存区内容压入文件
			out.close(); // 最后记得关闭文件
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
		//创建分词对象
		testICTCLAS50 = new ICTCLAS50();
	//	System.out.println("#####2");
		String argu = ".";
		//初始化
		if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
		{
			System.out.println("Init Fail!");
			return;
		}
		//设置词性标注集(0 计算所二级标注集， 1 计算所一级标注集， 2 北大二级标注集， 3 北大一级标注集)
		testICTCLAS50.ICTCLAS_SetPOSmap(2);
	//	System.out.println("#####4");
		ExcelAddToTxtFile("userinfo.xls","userdict.txt");
	//	System.out.println("#####5");
		int nCount = 0;
	//	System.out.println("#####6");
		String usrdir = "userdict.txt"; //用户字典路径
	//	System.out.println("#####7");
		byte[] usrdirb = usrdir.getBytes();//将 string 转化为 byte 类型
	//	System.out.println("#####8");
		//导入用户字典,返回导入用户词语个数第一个参数为用户字典路径，第二个参数为用户典的编码类型
		nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
	//	System.out.println("#####9");
		System.out.println("导入用户词个数" + nCount);
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
				new FileInputStream(new File("Check.txt"))); // 建立一个输入流对象 reader
		//System.out.println("****2");
		BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
		//System.out.println("****3");
		String line = "";
		line = br.readLine();
		//System.out.println("****4");
		byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(text.getBytes("GB2312"), 0,0);//分词处理
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
						System.out.println("ok:创建文件夹成功!");
					}
					else
					{
						System.out.println( "err:创建文件夹失败!");
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
		/*	BuildingResult("F:/高级软件工程/第2题数据 网络舆情数据/webdata/jn_"+Long.toString(i)+".html",
					"F:/高级软件工程/第2题数据 网络舆情数据/",
					"F:/高级软件工程/第2题数据 网络舆情数据/output_jn_"+Long.toString(i)+".txt",i);*/
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
