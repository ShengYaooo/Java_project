import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.*;
import java.time.LocalDate;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.io.*;

public class HW2{
	static ArrayList<Ledger> LedgerList = new ArrayList<Ledger>();
	static String pattern="(?<day>0[1-9]|[1-9]|[12]\\d|3[01])\\s+(?<category>[a b c])\\s+(?<price>\\d+)";
	static String addpattern="add\\s+(?<day>0[1-9]|[1-9]|[12]\\d|3[01])\\s+(?<category>[a b c])\\s+(?<price>\\d+)";
	static String updatepattern="(?<no>\\d+)\\s+(?<day>0[1-9]|[1-9]|[12]\\d|3[01])\\s+(?<category>[a b c])\\s+(?<price>\\d+)";
	String getCurrentDirectory() throws Exception
    {
        //return this.getClass().getClassLoader().getResource("").toURI().getPath();
        String p= HW2.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        //return ClassLoader.getSystemResource("").getPath();
        p=p.startsWith("/")?p.substring(1):p;
        p=p.endsWith("/")?p.substring(0, p.length()-1):p;
        return p;
    }
	
	public static void main(String arg[]) throws Exception {
		LedgerList.addAll(ReadLedgerCSV()) ;
		//將CSV裡面的資料加到LedgerList裡面
        
        ShowLedgerList();
		System.out.println("Welcome!");
		System.out.println("You can use \"add day category price\" to add the data");
		System.out.println("You can use \"show all/day/category\" to check the data");
		System.out.println("You can use \"update\" to enter update mode");
		System.out.println("You can use \"delete\" to enter delete mode");
		System.out.println("You can use \"help\" to check the detail");
		System.out.println("You can use \"exit\" to leave the ledger");
		System.out.println("You can use \"sum\" to calculate total amount\n");

		Process();
	}
	static void Process() throws Exception{
		
		
		boolean exit = false;
		while(exit == false){
			System.out.print(">");
			String keyin = ConsoleIn.readLine();
		    String[] x = keyin.split(" ");
			//判斷輸入的第一個字串
			switch(x[0]){
				case "add":
					Ledger ledger = VaildRegexPat(keyin, addpattern);
					//經過VaildRegexPat來檢查格式是否正確
					if(ledger==null){
						System.out.println("wrong Input format");
						break;
					}
					if(LedgerList.size()<100){
						LedgerList.add(ledger);
						//Collections.sort(LedgerList, new LedgerComparator());
						ShowLedgerList();
						//查看資料是否超過100筆
					break;
					}
					else{
						System.out.println("data set  over 100 limit");
						break;
					}
					
				case "show":
					if(x[1].equals("all")){
						ShowLedgerList();
						break;
					}
					if(x[1].equals("a")){
						Collections.sort(LedgerList, new LedgerComparator());
						System.out.print("No\tDay\tCategory\tPrice\n");
						int c=1;
						int i=0;
						for (Ledger a : LedgerList){
							if(a.Category.equals("a")){
							System.out.print(c+"\t"+a.Day+"\t"+ a.Category+"\t"+"\t"+ a.Price+"\n"); 
							c++;
							}
							i++;
						}
						break;
						//判斷資料是否有a，有的話就印出
					}
					if(x[1].equals("b")){
						Collections.sort(LedgerList, new LedgerComparator());
						System.out.print("No\tDay\tCategory\tPrice\n");
						int c=1;
						int i=0;
						for (Ledger a : LedgerList){
							if(a.Category.equals("b")){
							System.out.print(c+"\t"+a.Day+"\t"+ a.Category+"\t"+"\t"+ a.Price+"\n"); 
							c++;
							}
							i++;
						}
						break;
						//判斷資料是否有b，有的話就印出
					}
					if(x[1].equals("c")){
						Collections.sort(LedgerList, new LedgerComparator());
						System.out.print("No\tDay\tCategory\tPrice\n");
						int c=1;
						int i=0;
						for (Ledger a : LedgerList){
							if(a.Category.equals("c")){
							System.out.print(c+"\t"+a.Day+"\t"+ a.Category+"\t"+"\t"+ a.Price+"\n"); 
							c++;
							}
						}
						break;
						//判斷資料是否有c，有的話就印出
					}
					try{
						int date = Integer.parseInt(x[1].trim());
						boolean counter = false;
						for (Ledger a : LedgerList){
							if(a.Day==(date)){
								counter = true;
							}
						}
						//判斷資料是否有此日期
						if(!counter){
							System.out.println("wrong Input day");
							break;
						}
							Collections.sort(LedgerList, new LedgerComparator());
							System.out.print("No\tDay\tCategory\tPrice\n");
							int c=1;
							for (Ledger a : LedgerList){
							if(a.Day==(date)){
							System.out.print(c+"\t"+a.Day+"\t"+ a.Category+"\t"+"\t"+ a.Price+"\n"); 
							c++;
							}
						}
						
						
					break;
					}
					catch(Exception e){
						System.out.println("wrong Input format");
						break;
					}
					
				case "update":
				    ShowLedgerList();
					System.out.println("please input \"number day category price\"");
					System.out.println("eg:");
					System.out.println("5 15 b 200");
					System.out.print("update>");
					String update = ConsoleIn.readLine();
				    boolean isCorrect= isValidRe(update,updatepattern);
					//經過isValidRe來檢查格式是否正確
					if(!isCorrect)
					{
						System.out.println("wrong Input format");
						break;
						
					}
					String[] u = update.split(" ");
					int number = 0;
					number = Integer.parseInt(u[0].trim());
					//將第一項(編號)令為number
					if(number>=LedgerList.size()){
						System.out.println("wrong Input number");
						break;
					}
					String newledger = u[1]+" "+u[2]+" "+u[3];
					Ledger updateledger = VaildRegexPat(newledger, pattern);
					LedgerList.set(number-1,updateledger);
					//替換該編號的資料
					
					//LedgerList.get(1).Day=2;
					//LedgerList.get(1).Category="c";
					ShowLedgerList();
					
					break;
				case "delete":
					ShowLedgerList();
					System.out.println("please input \"number\"");
					System.out.println("eg:");
					System.out.println("5");
					System.out.print("delete>");
					try{
						int delete = ConsoleIn.readLineInt();
						LedgerList.remove(delete-1);
						ShowLedgerList();
						break;
					}
					//刪除該編號的資料
					catch(Exception e){
						System.out.println("wrong Input number");
						break;
					}
					
					
					
					
				case "help":
					System.out.println("**************************help**************************");
					System.out.println("add");
					System.out.println("\"add day category price\" to add the data\n");
					System.out.println("show");
					System.out.println("\"show all/day/category\" to check the data\n");
					System.out.println("update");
					System.out.println("\"update\" to enter update mode");
					System.out.println("In the update mode, \"update number day category price\" to modify the data\n");
					System.out.println("delete");
					System.out.println("\"delete\" to enter delete mode");
					System.out.println("In the delete mode, \"number\" to delete the data\n");
					System.out.println("help");
					System.out.println("\"help\" to check the detail\n");
					System.out.println("exit");
					System.out.println("\"exit\" to leave the ledger\n");
					System.out.println("sum");
					System.out.println("\"sum\" to calculate total amount\n");
					
					break;
				case "exit":
					WriteLedgerCSV(LedgerList);
					exit=true;
					//將LedgerList的資料寫入CSV中
					
					break;
				case "sum":
					int sum =0;
					for(Ledger a : LedgerList){
						sum = a.Price + sum ;
					}
					System.out.println("total amount is "+sum);
					break;
				default:
                    System.out.println("That is not a valid cmd");
			}
		}	
		
	}
	static void ShowLedgerList() throws Exception
    {
        Collections.sort(LedgerList, new LedgerComparator());
        System.out.print("No\tDay\tCategory\tPrice\n");
        int c=1;
        for (Ledger ledger : LedgerList){
            System.out.print(c+"\t"+ledger.Day+"\t"+ ledger.Category+"\t"+"\t"+ ledger.Price+"\n"); 
            c++;
        }
    }
	
	static boolean isValidRe(String content,String pattern)
	{
		 Boolean vaild = false;
        Pattern reg = Pattern.compile(pattern);
        
        Matcher matcher = reg.matcher(content);
        vaild = matcher.matches();
        
         return vaild;
		
	}
	static Ledger VaildRegexPat(String content,String pattern)
    {
//// eg:
    //     String content="2017-04-25";
    //    String pattern="(\\d{4})-((\\d{2})-(\\d{2}))";
    //    Pattern reg = Pattern.compile(pattern);
    //    Matcher matcher = reg.matcher(content);
    //    matcher.find();//沒這行會exception lang.IllegalStateException: No match found
    //     System.out.printf("\nmatcher.group(0) value:%s", matcher.group(0));
    //     System.out.printf("\nmatcher.group(1) value:%s", matcher.group(1));
    //    System.out.printf("\nmatcher.group(2) value:%s", matcher.group(2));
    //    System.out.printf("\nmatcher.group(3) value:%s", matcher.group(3));
    //     System.out.printf("\nmatcher.group(4) value:%s", matcher.group(4));

        Boolean vaild = false;
        Pattern reg = Pattern.compile(pattern);
        
        Matcher matcher = reg.matcher(content);
        vaild = matcher.matches();
        Ledger ledger=null;
        if(!vaild)
         return ledger;
	 
       
			  int day = 0;
              String category = matcher.group("category");
              int price =0;
		
              try 
              {
                price = Integer.parseInt(matcher.group("price").trim());
		        day =Integer.parseInt(matcher.group("day").trim());
                //ledger.SetPrice(price);
		        ledger= new Ledger(day,category,price);
          
              } catch (NumberFormatException e) {
         
              }
        
			 
			
		
        

        return ledger;
    }
	public static ArrayList<Ledger> ReadLedgerCSV() throws Exception
    {
         ///like Path.combine
        //Path path = Paths.get("E:\\aeda\\dcstest_勿刪","20220804112722761_52_10.13.14.151_END.dat");
        //Path path = Paths.get("ledger.csv");
        //System.out.println(path.toAbsolutePath());///same with toString();
       String directoryName =  new HW2().getCurrentDirectory();
       //System.out.println(directoryName); 
       
         ArrayList<Ledger> items = new ArrayList<Ledger>();
         LocalDate date = LocalDate.now();//.parse("2022-01-19"); 
         String fcsv=String.format("ledgerList_%02d.csv", date.getMonthValue()) ;
        File f = new File(Paths.get(directoryName,fcsv).toString());
        String fPath =f.getAbsolutePath();
        System.out.println(fcsv);
        if(!f.exists())
           return items;
        
        //System.out.println(path.toRealPath(LinkOption.values()));
        try 
        {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fPath));//檔案讀取路徑
            BufferedReader reader = new BufferedReader(isr);
           
            String line = null;
            while((line=reader.readLine())!=null)
            {
               if(line.length()<=0)
                continue;
               String[] item = line.split(",");
              if(item.length==3)
              {
                Ledger ledger=new Ledger();
                // ledger.SetDate(item[0].trim());  
                // ledger.SetCategory(item[1].trim());
               
                ledger.Category=item[1].trim();
                int price=0;
				int day=0;
                try 
                {
				  ledger.Day=Integer.parseInt(item[0].trim());
                   price = Integer.parseInt(item[2].trim());
                   //ledger.SetPrice(price);
                   ledger.Price=price;
                } catch (NumberFormatException e) {
                 
                }
               
                items.add(ledger);
              }
            }
          
            reader.close();

        } 
        catch (FileNotFoundException e) 
        {
           
            e.printStackTrace();
        }
        return items;
    }
    public static void WriteLedgerCSV(ArrayList<Ledger> lItem) throws Exception
    {
        String directoryName =  new HW2().getCurrentDirectory();
        //System.out.println(directoryName); 
        LocalDate date = LocalDate.now();//.parse("2022-01-19"); 
         String fcsv=String.format("ledgerList_%02d.csv", date.getMonthValue()) ;
       
       
        File f = new File(Paths.get(directoryName,fcsv).toString());
        String fPath =f.getAbsolutePath();

       
        try {
            System.out.println(fcsv);
            //BufferedWriter bw = new BufferedWriter(new FileWriter(fPath,true));//檔案輸出路徑
            BufferedWriter bw = new BufferedWriter(new FileWriter(fPath));//檔案輸出路徑
            StringBuilder sb = new StringBuilder();
            for (Ledger ledger : lItem) 
            {
                
              
               String  data1= ((Integer)ledger.Day).toString();
               String  data2= ledger.Category.trim();
               String  data3=((Integer)ledger.Price).toString();
               //System.out.print(data1+"\t"+ data2+"\t"+ data3+"\n"); 
         
               //bw.newLine();//新起一行
               /// %3s vs %3$s 
               /// 向右靠齊 , 第三個object的索引
               //bw.write(String.format("%s,%s,%3s\r\n",data1,data2,data3));
               sb.append(String.format("%s,%s,%s\r\n",data1,data2,data3));
            } 
            bw.write(sb.toString());
            bw.close();
           
           }
           catch (FileNotFoundException e) 
           {
           
            e.printStackTrace();
           }
    }
}