import java.util.regex.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

class MainApp{
	ArrayList<String> rolemap;
	ArrayList<String> player;
	boolean exitload;
	ArrayList<Pokemon> PokemonGroup1;
	ArrayList<Pokemon> PokemonGroup2;
	String args1;
	String[] P1;
	String args2;
	String[] P2;
	public MainApp(){
		rolemap = new ArrayList<String>();
		rolemap.add("Leafeon");
		rolemap.add("Flareon");
		rolemap.add("Vaporeon");
		PokemonGroup1=new ArrayList<Pokemon>();
		PokemonGroup2=new ArrayList<Pokemon>();
		player = new ArrayList<String>();
		
	}
	public void Load() throws Exception{
		boolean check1 = false;
		boolean check2 = false;
		exitload = false;
		while(!exitload){			
			System.out.println("建立新檔案請輸入1");
			System.out.println("讀取檔案請輸入2");
			System.out.print(">");
			int load = ConsoleIn.readLineInt();
			switch(load){
			case 1:
				while(!check1){//建立玩家1資料
					System.out.println("請輸入 玩家1名稱 寶可夢名稱");
					System.out.println("可選擇的寶可夢有Leafeon Flareon Vaporeon ");
					System.out.print(">");
					args1 = ConsoleIn.readLine();
					P1= args1.split(" ");
					if(P1.length==2){
						if(rolemap.contains(P1[1])){
							Pokemon thepokemon = new Pokemon(P1[1], 1);
							player.add(P1[0]);
							PokemonGroup1.add(thepokemon);
							check1 = true;
						}
						else{
							System.out.println("錯誤寶可夢名稱");
						}
					}
					else{
						System.out.println("錯誤格式");
					}
					
				}
				while(!check2){//建立玩家2資料
					System.out.println("請輸入 玩家2名稱 寶可夢名稱");
					System.out.println("可選擇的寶可夢有Leafeon Flareon Vaporeon ");
					System.out.print(">");
					args2 = ConsoleIn.readLine();
					P2= args2.split(" ");
					if(P2.length==2){
						if(rolemap.contains(P2[1])){
							Pokemon thepokemon = new Pokemon(P2[1], 1);
							player.add(P2[0]);
							PokemonGroup2.add(thepokemon);
							check2 = true;
						}
						else{
							System.out.println("錯誤寶可夢名稱");
						}
					}
					else{
						System.out.println("錯誤格式");
					}
					
				}
				exitload = true;
				break;
			case 2:
				loaddata();//讀檔
				
				exitload = true;
				break;
			default:
				System.out.println("數字輸入錯誤");
				break;
			}
		}
		movement();//選擇行動
	}
	private int turn;
	private void movement(){
		boolean exitmove = false;
		show();
		
		while(!exitmove){
			turn=0;
			System.out.println("\r\n");
			System.out.println("對戰請輸入1");
			System.out.println("捕捉請輸入2");
			System.out.println("前往回復站請輸入3");
			System.out.println("離開請輸入4");
			System.out.print(">");
			int movement = ConsoleIn.readLineInt();
			switch(movement){
				case 1:
					if(check(turn)==true){//檢查寶可夢的血量是否為0
						attack();
					}
					else{
						System.out.println("寶可夢沒有生命值了，請先前往回復");//全部為0的話提示回復
					}
					break;
				case 2:
					capture();//進入捕捉方法
					break;
				case 3:
					heal();//進入治療方法
					show();
					break;
				case 4:
					save();//存檔後離開
					exitmove = true;
					break;
				case 5:
					show();
					break;
			}
		}
		
	}
	private int u=0;
	private int v=0;
	
	private boolean check(int a){
		
		boolean checkHP = false;
		boolean checkP1HP = false;
		boolean checkP2HP = false;
		for(int i=2;0<=i;i--){
			try{
				if(PokemonGroup1.get(i).gethealth()!=0){
					if(a==0){
						u=i;//設定出場為第一支血量不為0寶可夢(玩家1)
					}
					checkP1HP = true;
				}
			}
			catch(Exception e){
				
			}
			
		}
		for(int i=2;0<=i;i--){
			try{
				if(PokemonGroup2.get(i).gethealth()!=0){
					if(a==0){
						v=i;//設定出場為第一支血量不為0寶可夢(玩家2)
					}
					checkP2HP = true;
				}
			}
			catch(Exception e){
				
			}
		}
		if(checkP1HP==true && checkP2HP==true){
			checkHP = true;
		}
		else{
			checkHP = false;
		}
		return checkHP;
	}
	private int P1item;
	private int P2item;
	private void attack(){
		boolean battle = true;
		boolean checkhealth = false;
		
		P1item = 2;//玩家1的回復藥
		P2item = 2;//玩家2的回復藥
		
		while(battle){
			turn++;
			System.out.println("turn "+turn);
			if(PokemonGroup1.get(u).getspeed()>=PokemonGroup2.get(v).getspeed()){//判斷寶可夢速度來決定誰先攻(玩家1先攻)
				u=Playermove(player.get(0),PokemonGroup1.get(u),PokemonGroup2.get(v),P1item,u);//玩家1進入Playmove來進行戰鬥
				if(u==-1){//判斷玩家1是否選擇逃跑
					expcalculate(PokemonGroup2);//計算經驗值
					showbattle(player.get(1));
					battle=false;
				}
				else{
					if(check(1)==true){
						v=Playermove(player.get(1),PokemonGroup2.get(v),PokemonGroup1.get(u),P2item,v);//玩家2進入Playmove來進行戰鬥
						if(v==-1){//判斷玩家2是否選擇逃跑
							expcalculate(PokemonGroup1);//計算經驗值
							showbattle(player.get(0));
							battle=false;
						}
					}
					else{
						expcalculate(PokemonGroup1);//計算經驗值
						expcalculate(PokemonGroup2);//計算經驗值
						showbattle(player.get(0));
						battle=false;
					}
				}
				
			}
			else{//判斷寶可夢速度來決定誰先攻(玩家2先攻)
				v=Playermove(player.get(1),PokemonGroup2.get(v),PokemonGroup1.get(u),P2item,v);//玩家2進入Playmove來進行戰鬥
				if(v==-1){//判斷玩家2是否選擇逃跑
					expcalculate(PokemonGroup1);//計算經驗值
					showbattle(player.get(0));
					battle=false;
				}
				else{
					if(check(1)==true){
						u=Playermove(player.get(0),PokemonGroup1.get(u),PokemonGroup2.get(v),P1item,u);//玩家1進入Playmove來進行戰鬥
						if(u==-1){//判斷玩家1是否選擇逃跑
							expcalculate(PokemonGroup2);//計算經驗值
							showbattle(player.get(1));
							battle=false;
						}
					}
					else{
						expcalculate(PokemonGroup1);//計算經驗值
						expcalculate(PokemonGroup2);//計算經驗值
						showbattle(player.get(1));
						battle=false;
					}
				}
				
				
			}
			
		}
	}
	private int Playermove(String name, Pokemon a, Pokemon b, int item, int no){
		boolean hpcheck = false;
		boolean cmdcheck = false;
		double effect;
		double damage;
		while(!cmdcheck){
			System.out.println(name);
			System.out.println(a.getname()+"要做什麼呢？");
			System.out.println("要進行攻擊請輸入attack");
			System.out.println("要使用技能請輸入attack 技能名稱");
			System.out.println("要使用好傷藥請輸入super potion");
			System.out.println("要交換寶可夢請輸入switch 編號");
			System.out.println("要逃跑的話請輸入run away");
			System.out.print(">");
			String command = ConsoleIn.readLine();
			String[] cmd = command.split(" ");
			switch(cmd[0]){
				case "attack"://進行attack動作
					if(cmd.length==2){
						if(a.skillcheck(cmd[1])){
							if(a.skillPPcheck(cmd[1])){
								effect = Pokemon.typerelation(a, b);//計算倍率
								damage = a.damage(cmd[1], effect);//計算技能傷害
								a.temporaryexp(damage);//計算獲得經驗值(暫時)
								if(b.sethealth(damage)==0){//判斷接受傷害的寶可夢是否死亡
									System.out.println(b.getname()+"失去戰鬥能力");
									check(0);
								}
								cmdcheck = true;
							}
							else{
								System.out.println("沒有技能點數了");
							}				
						}
						else{
							System.out.println("技能名稱錯誤");
							break;
						}
					}
					else if(cmd.length==1){
						effect = Pokemon.typerelation(a, b);//計算倍率
						damage = a.damage(effect);//計算傷害
						a.temporaryexp(damage);//計算獲得經驗值(暫時)
						if(b.sethealth(damage)==0){//判斷接受傷害的寶可夢是否死亡
							System.out.println(b.getname()+"失去戰鬥能力");
							check(0);
						}
						cmdcheck = true;
					}
					else{
						System.out.println("錯誤攻擊指令");
					}
						
					break;
				case "super"://使用回復藥
					if(cmd.length==2){
						if(cmd[1].equals("potion")){
							item=item-1;
							if(item>=0){
								a.sethealth(-50);//進行補血
								cmdcheck = true;
							}
							else{
								System.out.println("沒有道具了");
							}
						}
						else{
							System.out.println("使用道具指令錯誤");
						}
					}
					else{
						System.out.println("使用道具指令錯誤");
					}
					break;
				case "run":
					if(name.equals(player.get(0))){
						for(int i=0;i<=2;i++){
							try{
								PokemonGroup1.get(i).runexp();//逃跑計算經驗值懲罰
							}
							catch(Exception e){
								
							}
						}
					}
					if(name.equals(player.get(1))){
						for(int i=0;i<=2;i++){
							try{
								PokemonGroup2.get(i).runexp();//逃跑計算經驗值懲罰
							}
							catch(Exception e){
								
							}
						}
					}
					cmdcheck = true;
					no =-1;
					
					break;
				case "switch":
					if(cmd.length==2){
						try{
							no = Integer.parseInt(cmd[1])-1;
							cmdcheck = true;
						}
						catch(Exception e){
							System.out.println("替換編號錯誤錯誤");
						}
					}
					else{
						System.out.println("替換寶可夢指令錯誤");
					}
					break;
				case "show":
					show();
					break;
				default:
					System.out.println("行動指令錯誤");
					break;
			}
		}
		return no;
	}
	private void expcalculate(ArrayList<Pokemon> arr){//戰鬥結束後計算經驗
		int finlevel=0;
		for(int i=2;0<=i;i--){
			try{
				finlevel=arr.get(i).expc();
				if(arr.get(i).getlevel()!=finlevel){
					System.out.println(arr.get(i).getname()+"升級!!!");
					Pokemon thepokemon = new Pokemon(arr.get(i).getname(),finlevel);
					arr.set(i,thepokemon);
				}
			}
			catch(Exception e){
				
			}
		}
	}
	private void capture(){
		boolean capture=false;
		while(!capture){
			System.out.println("請輸入 玩家名稱 寶可夢名稱 寶可夢等級");
			String keyin = ConsoleIn.readLine();
			String[]k = keyin.split(" ");
			if(k.length==3){
				try{
					int number = Integer.parseInt(k[2]);
					Pokemon newpokemon = new Pokemon(k[1],number);//建立野生寶可夢
					if(k[0].equals(player.get(0))){//玩家1進行對戰
						catchbattle(player.get(0),PokemonGroup1,newpokemon);
					}
					if(k[0].equals(player.get(1))){//玩家2進行對戰
						catchbattle(player.get(1),PokemonGroup2,newpokemon);
					}
					else{
						System.out.println("錯誤寶可夢名稱或等級");
					}
					capture=true;
				}
				catch(Exception e){
					System.out.println("錯誤格式");
				}
				
			}
			else{
				System.out.println("wrong format");
			}
		}
		
	}
	private void catchbattle(String name,ArrayList<Pokemon> arr,Pokemon b){
		double effect=0;
		double damage=0;
		P1item = 2;
		P2item = 2;
		if(arr.size()>=3){//判斷玩家的持有寶可夢數量
			System.out.println(name+"已經有三隻寶可夢了");
		}
		else{			
			if(check(arr)){//確認玩家寶可夢血量
				turn=0;
				boolean catchbattle=true;
				while(catchbattle){
					turn++;
					System.out.println("turn "+turn);
					if(turn==5){//超過四回合
						System.out.println("fail");
						catchbattle=false;
					}
					else{
						if(arr.get(u).getspeed()>=b.getspeed()){//判斷寶可夢速度
							u=Playermove(name,arr.get(u),b,P1item,u);
							if(b.gethealth()==0){//野生寶可夢血量為0則加入玩家的寶可夢中
								System.out.println("success");
								catchbattle=false;
								arr.add(b);
								
							}
							else{
								effect = Pokemon.typerelation(b, arr.get(u));
								damage = b.damage(effect);
								if(arr.get(u).sethealth(damage)==0){
									System.out.println(b.getname()+" is down");
									catchbattle=check(arr);
								}
							}
						}
						else{
							effect = Pokemon.typerelation(b, arr.get(u));
							damage = b.damage(effect);
							if(arr.get(u).sethealth(damage)==0){
								System.out.println(b.getname()+" is down");
								catchbattle=check(arr);
							}
							else{
								u=Playermove(P1[0],arr.get(u),b,P1item,u);
								if(b.gethealth()==0){
									System.out.println("success");
									catchbattle=false;
									arr.add(b);
								}
							}
						}
					}
				}
			}
		}
	}
	private boolean check(ArrayList<Pokemon> arr){
		boolean catchcheck=false;
		u=-1;
		for(int i=2;0<=i;i--){
			try{
				if(arr.get(i).gethealth()!=0){
					u=i;
				}
				catchcheck=true;
			}
			catch(Exception e){
				
			}
			
		}
		if(u==-1){
			System.out.println("需要回復生命值");
		}
		return catchcheck;
	}
	private void heal(){
		int heal1=0;
		int heal2=0;
		for(int i=0;i<3;i++){
			try{
				if(PokemonGroup1.get(i).gethealth()==PokemonGroup1.get(i).getmaxhealth()){//判斷玩家1的寶可夢是否需要回復血量
					if(PokemonGroup1.get(i).healPPcheck()==false){//判斷玩家1的寶可夢是否需要回復技能點數
						heal1++;
					}
				}
				if(PokemonGroup2.get(i).gethealth()==PokemonGroup2.get(i).getmaxhealth()){//判斷玩家2的寶可夢是否需要回復血量
					if(PokemonGroup2.get(i).healPPcheck()==false){//判斷玩家2的寶可夢是否需要回復技能點數
						heal2++;
					}
				}
				
				
			}
			catch(Exception e){
				
			}
		}
		if(PokemonGroup1.size()==heal1 && PokemonGroup2.size()==heal2){
			System.out.println("不須回復");
		}
		else{
			for(int i=0;i<3;i++){
				try{
					PokemonGroup1.get(i).heal();//回復血量和技能點數
					PokemonGroup2.get(i).heal();//回復血量和技能點數
				}
				catch(Exception e){
					
				}
			}
			
		}
	}
	private  void save(){
		try {
            // Constructs a FileWriter object given a file name.
            FileWriter fw = new FileWriter("record.txt");
            fw.write(player.get(0)+"\n");//玩家1名稱寫入record.txt
			for(int i=0;i<=2;i++){
				try{//玩家1寶可夢寫入record.txt
					fw.write(PokemonGroup1.get(i).getname()+" "+PokemonGroup1.get(i).getlevel()+" "+PokemonGroup1.get(i).gethealth()+" "+PokemonGroup1.get(i).getskill1PP()+" "+PokemonGroup1.get(i).getskill2PP()+"\r");
				}
				catch(Exception e){
				}
			}
			fw.write(player.get(1)+"\n");//玩家2名稱寫入record.txt
			for(int i=0;i<=2;i++){
				try{//玩家2寶可夢寫入record.txt
					fw.write(PokemonGroup2.get(i).getname()+" "+PokemonGroup2.get(i).getlevel()+" "+PokemonGroup2.get(i).gethealth()+" "+PokemonGroup2.get(i).getskill1PP()+" "+PokemonGroup2.get(i).getskill2PP()+"\r");
				}
				catch(Exception e){
				}
			}
			
			
			
			
            fw.flush();
            fw.close();
        } catch (Exception e) {
        }
	}
	private void loaddata(){//讀檔
		int r=0;
		try {
            // Creates a new FileReader, given the name of the file to read from.
            FileReader fr = new FileReader("record.txt");
            // Creates a buffering character-input stream that uses a default-sized input buffer.
            BufferedReader br = new BufferedReader(fr);
            String content = "";
			content = "";
			while (br.ready()) {
                content = br.readLine();
				String[]c=content.split(" ");//判斷讀進資料的陣列元素來判斷毒入的為玩家還是寶可夢，進一步分類
				if(c.length==1){//讀入的資料為玩家
					player.add(c[0]);
					r++;
				}
				if(c.length==5){//讀入的資料為寶可夢
					try{
						int c1 = Integer.parseInt(c[1]);
						double c2 = Double.parseDouble(c[2]);
						int c3 = Integer.parseInt(c[3]);
						int c4 = Integer.parseInt(c[4]);
						
						if(r==1){
							Pokemon thepokemon=new Pokemon(c[0],c1,c2,c3,c4);
							PokemonGroup1.add(thepokemon);
						}
						if(r==2){
							Pokemon thepokemon=new Pokemon(c[0],c1,c2,c3,c4);
							PokemonGroup2.add(thepokemon);
						}
					}
					catch(Exception e){
						System.out.println("Something Error");
					}
					
				}
				
				
            }
			try{
				
			}
			catch(Exception e){
				System.out.println("Something Error");
			}
			fr.close();
		}
		catch (Exception e) {
            System.out.println("Something Error");
        }
	}
	private void show(){//顯示寶可夢狀態
		System.out.println("玩家1:"+player.get(0));
		System.out.println("寶可夢名稱\t等級\t血量\t技能1點數\t技能2點數");
		try{
			for(int i=0;i<3;i++){
				System.out.println(PokemonGroup1.get(i).getname()+"\t\t"+PokemonGroup1.get(i).getlevel()+"\t"+PokemonGroup1.get(i).gethealth()+"\t"+PokemonGroup1.get(i).getskill1PP()+"\t\t"+PokemonGroup1.get(i).getskill2PP()+"\r");
			}
		}
		catch(Exception e){
			
		}
		System.out.println("\r");
		System.out.println("玩家2:"+player.get(1));
		try{
			for(int i=0;i<3;i++){
				System.out.println(PokemonGroup2.get(i).getname()+"\t\t"+PokemonGroup2.get(i).getlevel()+"\t"+PokemonGroup2.get(i).gethealth()+"\t"+PokemonGroup2.get(i).getskill1PP()+"\t\t"+PokemonGroup2.get(i).getskill2PP()+"\r");
			}
		}
		catch(Exception e){
			
		}
		
	}
	private void showbattle(String name){//戰鬥後顯示數據
		System.out.println(name+"獲得勝利");
		System.out.println("玩家1:"+player.get(0));
		System.out.println("寶可夢名稱\t等級\t血量\t經驗\t技能1點數\t技能2點數");
		try{
			for(int i=0;i<3;i++){
				System.out.println(PokemonGroup1.get(i).getname()+"\t\t"+PokemonGroup1.get(i).getlevel()+"\t"+PokemonGroup1.get(i).gethealth()+"\t"+PokemonGroup1.get(i).getexp()+"\t"+PokemonGroup1.get(i).getskill1PP()+"\t\t"+PokemonGroup1.get(i).getskill2PP()+"\r");
			}
		}
		catch(Exception e){
			
		}
		System.out.println("\r");
		System.out.println("玩家2:"+player.get(1));
		try{
			for(int i=0;i<3;i++){
				System.out.println(PokemonGroup2.get(i).getname()+"\t\t"+PokemonGroup2.get(i).getlevel()+"\t"+PokemonGroup2.get(i).gethealth()+"\t"+PokemonGroup2.get(i).getexp()+"\t"+PokemonGroup2.get(i).getskill1PP()+"\t\t"+PokemonGroup2.get(i).getskill2PP()+"\r");
			}
		}
		catch(Exception e){
			
		}
	}
}