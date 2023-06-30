import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import java.util.regex.*;



class MainApp
{
    boolean exited;
    Hashtable<String, String> rolemap;// = new Hashtable<String, String>();
    //Hashtable<Integer, tree> treeGroup = new Hashtable<Integer, tree>();
    tree[] treeGroup;//=new tree[9];
    ArrayList<monkey> monkeyGroup;// = new ArrayList<monkey>();
    ArrayList<bear> bearGroup;// = new ArrayList<bear>();
    ArrayList<dog> dogGroup;// = new ArrayList<dog>();
   
    hive hiveGroup;
    int currentday;
    public MainApp()
    {
        exited = false;
        currentday = 1;
        rolemap = new Hashtable<String, String>();//rolemap 使用 "tree" , "animal" , "props" 來分類add指令[樹種][動物]建立相對應物件
        rolemap.put("pomelo", "tree");
        rolemap.put("banana", "tree");
        rolemap.put("monkey", "animal");
        rolemap.put("bear", "animal");
        rolemap.put("dog", "animal");
        rolemap.put("hive", "props");
        treeGroup=new tree[9];//植物陣列
        monkeyGroup = new ArrayList<monkey>();//monkey集合
        bearGroup = new ArrayList<bear>();//bear集合
        dogGroup = new ArrayList<dog>();//dog集合
        hiveGroup = new hive();//hive數量管理
    }
    
    public void Start() throws Exception
    {
        Scanner keyin = new Scanner(System.in);//讀進資料
        
            while(!exited)
            {
                System.out.printf("%s>",currentday);
                
                String line=  keyin.nextLine();
                String[] cmd= line.trim().split(" ");
                String[] args=null;
                switch( cmd[0])
                {
                    case "add":
                        String pattern = "(?<arg0>[a-z]+)\\s*(?<arg1>[a-z]+)\\s*(?<arg2>[1-9]\\d*)";
                        args= isVaildExpressPattern(line, pattern,3);//利用正規表示式來判斷讀入資料的格式是否正確
                        if (args==null)
                        {
                            System.out.println("add format error : no must be larger than 0");
                            break;
                        }

                        if (args.length == 3)
                        {
                               
                            int no = 0;

                            if (rolemap.containsKey(args[1]))//判斷資料中第二個值是否有在rolemap裡面(進一步做出分類)
                            {
                                String type = rolemap.get(args[1]);//type為該元素的value
                                try
                                {
                                  no = Integer.parseInt(args[2]);//把資料的第三個值(原為字串)換成整數
                                }catch (NumberFormatException e) {
             
                                }
                                if(no<=0)
								{
								   System.out.println("no of input can not be 0 or negative number");//第三個值小於等於0的情況
                                   break;
								}

                                if (type.equals("tree"))//要是讀進資料的value為tree的話做以下動作
                                {
                                    boolean allUsed=true;
                                    for(int i=0;i<treeGroup.length;i++)
                                    {
                                        if(treeGroup[i]==null)
                                        {
                                           allUsed=false;
                                           break;
                                        }
                                    }

                                    if (allUsed)//(treeGroup.length >= 9)//判斷還有沒有位置可以增加tree
                                    {
                                        System.out.println(String.format("amount of tree:%d is up to Limit 9",treeGroup.length));
                                        break;
                                    }
                                    if(no>9)//位置不能超過9
								    {
								        System.out.println("loc of tree can not be over 9");
                                        break;
								    }
                                    if(treeGroup[no-1]!=null)//if(treeGroup.containsKey(no))
                                    {
                                        System.out.println(String.format("location of tree:%d is used",no));//該位置已被使用
                                        break;
                                    }
                                    if(args[1].equals("banana"))//判斷讀入的資料為banana的話
                                    {
                                        tree theTree = new tree(args[1], no,60,100,5);//建構banana object
                                        //treeGroup.put(no, theTree);
                                        treeGroup[no-1]=theTree;//放入list中
                                    }
                                    else if(args[1].equals("pomelo"))//判斷讀入的資料為pomelo的話
                                    {
                                        tree theTree = new tree(args[1], no,30,20,2);//建構pomelo object
                                        treeGroup[no-1]=theTree;//放入list中
                                    }
                                  
                                    System.out.println(String.format("%5s%10s", "loc", "category", "current_unit", "max_unit", "life_of_day", "pass_day"));
                                    System.out.println(String.format("%5s%10s",treeGroup[no-1].getloc(),treeGroup[no-1].getcategory()));//顯示增加的結果
                                    
                                    // TreeMap<Integer,tree> tm 
                                    // = new TreeMap<Integer,tree>(treeGroup);
                                    // Set<Integer> keys = tm.keySet();
                                    // System.out.println(String.format("%5s%10s%16s%16s%16s%16s", "loc", "category", "current_unit", "max_unit", "life_of_day", "pass_day"));
                                    // for (int loc : keys) 
                                    // {
                                    //     tree thetree = treeGroup.get(loc);
                                        
                                    //     System.out.println(String.format("%5s%10s%16d%16d%16d%16d",thetree.getloc(),thetree.getcategory(),thetree.getcurrentqty(),thetree.getmaxqty(),thetree.getlifeofday(),thetree.getpassday()));
                                    // }

                                   
                                }
                                else if (type.equals("animal"))//要是value為animal的話做以下判斷
                                {
                                    if(args[1].equals("monkey"))
                                        {
                                            for (int i = 0; i < no; i++)
                                            {
                                                monkey m= new monkey(3,3);//做出每一隻猴子的object
                                                monkeyGroup.add(m);//放入list中
                                               
                                            }
                                            System.out.println(String.format("%10s%16s", "category",  "quantity "));
                                            
                                            System.out.println(String.format("%10s%16d",args[1],monkeyGroup.size()));//顯示加入後的結果
                                        }
                                        else if(args[1].equals("bear"))
                                        {
                                            for (int i = 0; i < no; i++)
                                            {
                                                
                                                bear b = new bear(1,1);//做出每一隻bear的object
                                                bearGroup.add(b);//放入list中
                                            }

                                            System.out.println(String.format("%10s%16s", "category",  "quantity "));                                            
                                            System.out.println(String.format("%10s%16d",args[1],bearGroup.size()));//顯示加入後的結果
                                        }
                                        else if(args[1].equals("dog"))
                                        {
                                            for (int i = 0; i < no; i++)
                                            {
                                                dog d = new dog(2);//做出每一隻dog的object
                                                dogGroup.add(d);//放入list中
                                               
                                            }
                                            System.out.println(String.format("%10s%16s", "category",  "quantity "));
                                            System.out.println(String.format("%10s%16d",args[1],dogGroup.size()));//顯示加入後結果
                                            
                                        }
                                                    
                                    
                                }
                                else if(type.equals("props"))
                                {
                                    hiveGroup.add(no);//加入hive
                                    System.out.println(String.format("%10s%16s", "category",  "quantity "));
                                            
                                    System.out.println(String.format("%10s%16d",args[1],hiveGroup.getcount()));//顯示結果
                                }
                            }
                            else
                            {
                               
                                System.out.println(String.format("not existed command %s",args[1]));
                                
                                break;
                            }
                        }
                        else
                            System.out.println("add format error");
                            break;
                    case "next":
                        pattern = "(?<arg0>[a-z]+)\\s*(?<arg1>[1-9]\\d*)";
                        args= isVaildExpressPattern(line, pattern,2);//利用正規表示式來判斷讀入資料的格式是否正確
                        if (args==null)
                        {
                            System.out.println("next format error=> eg: next 2");
                            break;
                        }
                        
                            int nextday = 0;
                            try
                            {
                                nextday = Integer.parseInt(args[1]);//把資料的第二個值(原為字串)換成整數
                            }catch (NumberFormatException e) {
    
                            }
                          
                            if(nextday>0)
                            {
                                currentday = currentday + nextday;
                                UpdateAll( nextday);//進入Update方法調整全部object的數值
                            }
                        
                            break;
                    case "harvest":
                        
                        if (cmd.length == 1)
                        {
                            //Hashtable<String,Integer> harvestAQty=new Hashtable<String,Integer>();
                            int bananaqty=0;//建構harvest這次banana的數量為0
                            int pomeloqty=0;//建構harvest這次pomelo的數量為0

                            // TreeMap<Integer,tree> tm = new TreeMap<Integer,tree>(treeGroup);
                            // Set<Integer> keys = tm.keySet();
                           
                            // for (int loc : keys) 
                            // {
                                
                            //     tree thetree = treeGroup.get(loc);
                            //     if(thetree.getcategory().equals("banana"))
                            //       bananaqty=bananaqty+thetree.getcurrentqty();
                            //     else if(thetree.getcategory().equals("pomelo"))
                            //       pomeloqty=pomeloqty+thetree.getcurrentqty();
                            //     thetree.setzerocurrentqty();
                                
                            // }

                            for(int i=0;i<treeGroup.length;i++)
                            {
                                if(treeGroup[i]!=null)
                                {
                                    if(treeGroup[i].getcategory().equals("banana"))
                                       bananaqty=bananaqty+treeGroup[i].getcurrentqty();//將該位置的banana目前結果數量加到bananaqty中
                                    else if(treeGroup[i].getcategory().equals("pomelo"))
                                       pomeloqty=pomeloqty+treeGroup[i].getcurrentqty();//將該位置的pemelo目前結果數量加到pomeloqty中
                                    treeGroup[i].setzerocurrentqty();//進入setzerocurrentqty中將目前結果數量改成0
                                }
                            }

                            System.out.println(String.format("%10s%16s","category","harvest qty"));
                            System.out.println(String.format("%10s%16d","banana",bananaqty));
                            System.out.println(String.format("%10s%16d","pomelo",pomeloqty));//顯示採收數量
                        }
                        else
                           System.out.println("harvest format error");
                        
                        break;
                    case "prune":
                        if (cmd.length != 1)
                        {
                            System.out.println("show format error");//判斷指令是否正確
                            break;
                        }
                        // Set<Integer> keys = treeGroup.keySet();
                        // for (int loc : keys) 
                        // {
                        
                        //     tree thetree = treeGroup.get(loc);
                        //     thetree.addlifeofday(5);
                        
                        // }
                        
                        for(int i=0;i<treeGroup.length;i++)
                        {
                            if(treeGroup[i]!=null)
                            {
                                 treeGroup[i].addlifeofday(5);//增加tree的壽命
                            }
                        }
                        break;
                    case "show":
                        if (cmd.length != 1)
                        {
                            System.out.println("show format error");
                            break;
                        }
                        System.out.println(String.format("%5s%10s%16s%16s%16s%16s", "loc", "category", "current_unit", "max_unit", "life_of_day", "pass_day"));

                        for(int i=0;i<treeGroup.length;i++)
                        {
                            if(treeGroup[i]!=null)
                                System.out.println(String.format("%5s%10s%16d%16d%16d%16d",treeGroup[i].getloc(),treeGroup[i].getcategory(),treeGroup[i].getcurrentqty(),treeGroup[i].getmaxqty(),treeGroup[i].getlifeofday(),treeGroup[i].getpassday()));
                            else
                                System.out.println(String.format("%5s%10s%16s%16s%16s%16s",i+1,"","","","",""));//顯示tree
                            }

                        System.out.println(String.format("%10s%16s%18s",  "category", "animal qty", "accum fruit qty"));
                        int totalaccumqty=0;
                        for (monkey themonkey : monkeyGroup) 
                        {
                            totalaccumqty =totalaccumqty+themonkey.getaccumbananaqty();
                        }
                        System.out.println(String.format("%10s%16d%16d","monkey",monkeyGroup.size(),totalaccumqty));
                        System.out.println(String.format("%10s%16d%16d","bear",bearGroup.size(),0));
                        System.out.println(String.format("%10s%16d%16d","dog",dogGroup.size(),0));
                        System.out.println(String.format("%10s%16d%16d","hive",hiveGroup.getcount(),0));//顯示動物
                        break;

                    case "exit":
                        if (cmd.length==1)
                            exited = true;
                        else
                            System.out.println("exit format error");//離開程式
                        break;
                    default:
                        System.out.println("format error");//第一個值錯誤跳出錯誤提示
                        break;
                    }
                    

            }
            keyin.close();//關掉scanner

    }
    void UpdateAll(int nextday)//Update方法
    {
        for (bear thebear : bearGroup) 
        {
            
              thebear.setnext(nextday, treeGroup, hiveGroup); //所有的bear object 進行setnext方法      
        }
        int hiveqty=hiveGroup.getcount();
        
        for (int i=0;i<treeGroup.length;i++)
        {
            if(treeGroup[i]==null)
               continue;
           
            treeGroup[i].setnext(nextday, hiveqty);
            
        }
        for (dog thedog : dogGroup) 
        {
            thedog.setnext(nextday, monkeyGroup);    
        }
        for (monkey themonkey : monkeyGroup) 
        {
            themonkey.setnext(nextday,treeGroup);    
        }
        ArrayList<bear> tmpbearGroup = new ArrayList<>();
        for (bear thebear : bearGroup) 
        {
            
            if(!thebear.isleaveorchard())
              tmpbearGroup.add(thebear);//將還沒離開的bear放入tmpbearGroup
        }
        bearGroup.clear();
        bearGroup.addAll(tmpbearGroup);//將tmpbearGroup的bear放入bearGroup中

        ArrayList<monkey> tmpmonkeyGroup = new ArrayList<>();
        for (monkey themonkey : monkeyGroup) 
        {
            
            if(!themonkey.isleaveorchard())
            tmpmonkeyGroup.add(themonkey);//同bear
        }
        monkeyGroup.clear();
        monkeyGroup.addAll(tmpmonkeyGroup);
    }

    private String[] isVaildExpressPattern(String content, String pattern,int type)//建立正規表示式
    {
        Boolean vaild = false;
        Pattern reg = Pattern.compile(pattern);
        
        Matcher matcher = reg.matcher(content);
        vaild = matcher.matches();
        String[] args=null;
        if(!vaild)
          return args;
        
        switch(type)
        {
            case 3:
                String arg0 = matcher.group("arg0").trim();
                String arg1 = matcher.group("arg1").trim();
                String arg2 = matcher.group("arg2").trim();
                 args=new String[]{arg0,arg1,arg2};//檢查add的格式
            break;
            case 2:
                String arg20 = matcher.group("arg0").trim();
                String arg21 = matcher.group("arg1").trim();//檢查next的格式
          
                 args=new String[]{arg20,arg21};
            break;
        }
       
       

        return args;
    }
}