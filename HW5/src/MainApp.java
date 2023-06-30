import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.Collections;

class MainApp{
	Hashtable<String, String> rolemap;
	ArrayList<String> player;
	ArrayList<Pokemon> Pokemonbox1;
	ArrayList<Pokemon> Pokemonbox2;
	ArrayList<String> Pitembox1;
	ArrayList<String> Pitembox2;
	int[]item1;
	int[]item2;
	public MainApp(){
		rolemap = new Hashtable<String, String>();
		rolemap.put("Leafeon", "quickclaw");
		rolemap.put("Flareon", "protector");
		rolemap.put("Vaporeon", "leftovers");
		rolemap.put("Espeon", "kingsrock");
		rolemap.put("Umbreon", "luckyegg");
		Pokemonbox1=new ArrayList<Pokemon>();
		Pokemonbox2=new ArrayList<Pokemon>();
		Pitembox1=new ArrayList<String>();
		Pitembox2=new ArrayList<String>();
		player = new ArrayList<String>();
		
	}
	public void Load() throws Exception{
		boolean exitload = false;
		boolean Pdata = false;
		String[] Parray;
		String Playerdata;
		while(!exitload){			
			System.out.println("建立新檔案請輸入1");
			System.out.println("讀取檔案請輸入2");
			System.out.print(">");
			int load = ConsoleIn.readLineInt();
			switch(load){
			case 1:
				for(int i=1;i<3;i++){
					Pdata = false;
					while(!Pdata){
						System.out.println("請輸入 玩家"+i+"名稱 寶可夢名稱");
						System.out.println("可選擇的寶可夢有Leafeon Flareon Vaporeon Espeon Umbreon");
						System.out.print(load+">");
						Playerdata = ConsoleIn.readLine();
						Parray= Playerdata.split(" ");
						if(Parray.length==2){
							if(rolemap.containsKey(Parray[1])){
								Pokemon thepokemon = new Pokemon(Parray[1], 1);
								player.add(Parray[0]);
								if(i==1){
									Pokemonbox1.add(thepokemon);
								}
								else if(i==2){
									Pokemonbox2.add(thepokemon);
								}
								else{
									System.out.println("something wrong\n");
								}
								Pdata = true;
							}
							else{
								System.out.println("錯誤寶可夢名稱\n");
							}
						}
						else{
							System.out.println("錯誤格式\n");
						}
					}
				}
				exitload = true;
				break;
			case 2:
				loaddata();//讀檔
				
				exitload = true;
				break;
			default:
				System.out.println("數字輸入錯誤\n");
				break;
			}
		}
		System.out.println("玩家資訊建立成功\n");
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
			System.out.println("顯示遊戲狀態請輸入4");
			System.out.println("裝備/卸除道具請輸入5");
			System.out.println("更改順序請輸入6");
			System.out.println("離開請輸入7");
			System.out.print(">");
			int movement = ConsoleIn.readLineInt();
			switch(movement){
				case 1:
					if(check(turn)){//檢查寶可夢的血量是否為0
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
					show();
					break;
				case 5:
					equip();
					break;
				case 6:
					switchpokemon();
					break;
				case 7:
					save();//存檔後離開
					exitmove = true;
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
		for(int i=0;i<2;i++){
			for(int j=3;0<=j;j--){
				try{
					if(i==0){
						if(Pokemonbox1.get(j).gethealth()!=0){
							if(a==0){
								u=j;//設定出場為第一支血量不為0寶可夢(玩家1)
							}
							else if(a==2){
								u=j;
							}
							checkP1HP = true;
						}
					}
					else if(i==1){
						if(Pokemonbox2.get(j).gethealth()!=0){
							if(a==0){
								v=j;//設定出場為第一支血量不為0寶可夢(玩家1)
							}
							else if(a==1){
								v=j;
							}
							checkP2HP = true;
						}
					}
				}
				catch(Exception e){
					
				}
				
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
	private void attack(){
		boolean battle = true;
		item1=new int[2];//{2,2};
		item2=new int[2];//{2,2};
		item1[0]=2;
		item1[1]=2;
		item2[0]=2;
		item2[1]=2;
		
		while(battle){
			turn++;
			System.out.println("turn "+turn);
			if(Pokemonbox1.get(u).getspeed()>=Pokemonbox2.get(v).getspeed()){//判斷寶可夢速度來決定誰先攻(玩家1先攻)
				u=Playermove(player.get(0),Pokemonbox1.get(u),Pokemonbox2.get(v),item1,u);//玩家1進入Playmove來進行戰鬥
				if(u==-1){//判斷玩家1是否選擇逃跑
					expcalculate(Pokemonbox2);//計算經驗值
					System.out.println(player.get(1)+"獲勝");
					show();
					battle=false;
				}
				else{
					if(check(-1)){
						v=Playermove(player.get(1),Pokemonbox2.get(v),Pokemonbox1.get(u),item2,v);//玩家2進入Playmove來進行戰鬥
						if(v==-1){//判斷玩家2是否選擇逃跑
							expcalculate(Pokemonbox1);//計算經驗值
							System.out.println(player.get(0)+"獲勝");
							show();
							battle=false;
						}
						else if(!check(-1)){
							expcalculate(Pokemonbox1);//計算經驗值
							expcalculate(Pokemonbox2);//計算經驗值
							System.out.println(player.get(1)+"獲勝");
							show();
							battle=false;
						}
					}
					else{
						expcalculate(Pokemonbox1);//計算經驗值
						expcalculate(Pokemonbox2);//計算經驗值
						System.out.println(player.get(0)+"獲勝");
						show();
						battle=false;
					}
				}
				
			}
			else{//判斷寶可夢速度來決定誰先攻(玩家2先攻)
				v=Playermove(player.get(1),Pokemonbox2.get(v),Pokemonbox1.get(u),item2,v);//玩家2進入Playmove來進行戰鬥
				if(v==-1){//判斷玩家2是否選擇逃跑
					expcalculate(Pokemonbox1);//計算經驗值
					System.out.println(player.get(0)+"獲勝");
					show();
					battle=false;
				}
				else{
					if(check(-1)==true){
						u=Playermove(player.get(0),Pokemonbox1.get(u),Pokemonbox2.get(v),item1,u);//玩家1進入Playmove來進行戰鬥
						if(u==-1){//判斷玩家1是否選擇逃跑
							expcalculate(Pokemonbox2);//計算經驗值
							System.out.println(player.get(1)+"獲勝");
							show();
							battle=false;
						}
						else if(check(-1)==false){
							expcalculate(Pokemonbox1);//計算經驗值
							expcalculate(Pokemonbox2);//計算經驗值
							System.out.println(player.get(0)+"獲勝");
							show();
							battle=false;
						}
					}
					else{
						expcalculate(Pokemonbox1);//計算經驗值
						expcalculate(Pokemonbox2);//計算經驗值
						System.out.println(player.get(1)+"獲勝");
						show();
						battle=false;
					}
				}
				
				
			}
			
		}
	}
	private int Playermove(String name, Pokemon a, Pokemon b, int[] item, int no){
		boolean cmdcheck = false;
		double effect;
		int damage;
		showbattle(a,b);
		while(!cmdcheck){
			System.out.println(a.getname()+"要做什麼呢？");
			System.out.println("要進行攻擊請輸入attack");
			System.out.println("要使用技能請輸入attack 技能名稱");
			System.out.println("要使用好傷藥請輸入super potion");
			System.out.println("要使用技能回復劑請輸入max elixir");
			System.out.println("要交換寶可夢請輸入switch 編號");
			System.out.println("要逃跑的話請輸入run away");
			System.out.print(name+">");
			String command = ConsoleIn.readLine();
			String[] cmd = command.split(" ");
			switch(cmd[0]){
				case "attack"://進行attack動作
					if(cmd.length==2){
						if(a.skillcheck(cmd[1])){
							if(a.skillPPcheck(cmd[1])){
								System.out.println(a.getname()+"使用技能"+cmd[1]);
								effect = Pokemon.typerelation(a, b);//計算倍率
								damage = a.damage(cmd[1], effect);//計算技能傷害
								a.temporaryexp(damage);//計算獲得經驗值(暫時)
								if(b.sethealth(damage)==0){//判斷接受傷害的寶可夢是否死亡
									System.out.println(b.getname()+"失去戰鬥能力");
									if(name.equals(player.get(0))){
										check(1);
									}
									else if(name.equals(player.get(1))){
										check(2);
									}
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
							if(name.equals(player.get(0))){
								check(1);
							}
							else if(name.equals(player.get(1))){
								check(2);
							}
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
							if(player.get(0).equals(name)){
								item1[0]=item1[0]-1;
								if(item1[0]>=0){

									a.healhealth();//進行補血
									cmdcheck = true;
								}
								else{
									System.out.println("沒有道具了");
								}
							}
							if(player.get(1).equals(name)){
								item2[0]=item2[0]-1;
								if(item2[0]>=0){
									a.healhealth();//進行補血
									cmdcheck = true;
								}
								else{
									System.out.println("沒有道具了");
								}
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
				case "max":
					if(cmd.length==2){
						if(cmd[1].equals("elixir")){
							if(player.get(0).equals(name)){
								item1[1]=item1[1]-1;
								if(item1[1]>=0){
									a.ppplus();
									cmdcheck = true;
								}
								else{
									System.out.println("沒有道具了");
								}
							}
							if(player.get(1).equals(name)){
								item2[1]=item2[1]-1;
								if(item2[1]>=0){
									a.ppplus();
									cmdcheck = true;
								}
								else{
									System.out.println("沒有道具了");
								}
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
								Pokemonbox1.get(i).runexp();//逃跑計算經驗值懲罰
							}
							catch(Exception e){
							
							}
						}
					}
					else if(name.equals(player.get(1))){
						for(int i=0;i<=2;i++){
							try{
								Pokemonbox2.get(i).runexp();//逃跑計算經驗值懲罰
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
							if(name.equals(player.get(0))){
								if(Pokemonbox1.size()>no){
									if(Pokemonbox1.get(no).gethealth()!=0){
										cmdcheck = true;
									}
									else{
										System.out.println("該編號寶可夢血量為0");
									}
								}
								else{
									System.out.println("該編號無寶可夢");
								}
								
							}
							else if(name.equals(player.get(1))){
								if(Pokemonbox2.size()>no){
									if(Pokemonbox2.get(no).gethealth()!=0){
										cmdcheck = true;
									}
									else{
										System.out.println("該編號寶可夢血量為0");
									}
								}
								else{
									System.out.println("該編號無寶可夢");
								}
							}
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
		for(int i=3;0<=i;i--){
			try{
				finlevel=arr.get(i).expc();
				if(arr.get(i).getlevel()<finlevel){
					System.out.println(arr.get(i).getname()+"升級!!!");
					arr.get(i).levelup(finlevel);
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
			System.out.println("可選擇的寶可夢有Leafeon Flareon Vaporeon Espeon Umbreon");
			System.out.println("請輸入exit即可離開");
			String keyin = ConsoleIn.readLine();
			String[]k = keyin.split(" ");
			if(k.length==3){
				if(rolemap.containsKey(k[1])){
					int number = 0;
					try{
						number = Integer.parseInt(k[2]);
					}
					catch(Exception e){
						System.out.println("等級錯誤");
					}
					Pokemon newpokemon = new Pokemon(k[1],number);//建立野生寶可夢
					if(k[0].equals(player.get(0))){//玩家1進行對戰
						catchbattle(player.get(0),Pokemonbox1,newpokemon,Pitembox1);
					}
					else if(k[0].equals(player.get(1))){//玩家2進行對戰
						catchbattle(player.get(1),Pokemonbox2,newpokemon,Pitembox2);
					}
					else{
						System.out.println("something wrong");
					}
				}
				else{
					System.out.println("錯誤寶可夢名稱");
				}
				capture=true;
				
			}
			else if(k.length==1){
				if(k[0].equals("exit")){
					capture=true;
				}
				else{
					System.out.println("錯誤格式");
				}
			}
			else{
				System.out.println("錯誤格式");
			}
		}
		
	}
	private void catchbattle(String name,ArrayList<Pokemon> arr,Pokemon b,ArrayList<String> Pitembox){
		double effect=0;
		int damage=0;
		item1=new int[2];
		item1[0]=2;
		item1[1]=2;
		item2=new int[2];
		item2[0]=2;
		item2[1]=2;
		turn=0;
		if(arr.size()>=4){//判斷玩家的持有寶可夢數量
			System.out.println(name+"已經有四隻寶可夢了");
		}
		else{			
			if(check(arr, turn)){//確認玩家寶可夢血量
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
							u=Playermove(name,arr.get(u),b,item1,u);
							if(u==-1){
								System.out.println("fail");
								catchbattle=false;
							}
							else if(b.gethealth()==0){//野生寶可夢血量為0則加入玩家的寶可夢中
								System.out.println("success");
								catchbattle=false;
								arr.add(b);
								b.heal();
								if(Pitembox.size()<4){
									Pitembox.add((rolemap.get(b.getname())));
									itemshow(name);
								}
								else{
									System.out.println("itembox is full");
								}
								
							}
							else{
								effect = Pokemon.typerelation(b, arr.get(u));
								damage = b.damage(effect);
								if(arr.get(u).sethealth(damage)==0){
									System.out.println(b.getname()+" is down");
									catchbattle=check(arr,0);
								}
							}
						}
						else{
							effect = Pokemon.typerelation(b, arr.get(u));
							damage = b.damage(effect);
							if(arr.get(u).sethealth(damage)==0){
								System.out.println(b.getname()+" is down");
								catchbattle=check(arr,0);
							}
							else{
								u=Playermove(name,arr.get(u),b,item1,u);
								if(u==-1){
									System.out.println("fail");
									catchbattle=false;
								}
								else if(b.gethealth()==0){
									System.out.println("success");
									catchbattle=false;
									arr.add(b);
									b.heal();
									if(Pitembox.size()<4){
										Pitembox.add((rolemap.get(b.getname())));
										itemshow(name);
									}
									else{
										System.out.println("itembox is full");
									}
									
									
								}
							}
						}
					}
				}
			}
		}
	}
	private boolean check(ArrayList<Pokemon> arr,int a){
		boolean catchcheck=false;
		for(int i=3;0<=i;i--){
			try{
				if(arr.get(i).gethealth()!=0){
					if(a==0){
						u=i;
					}
					catchcheck=true;
				}
				
			}
			catch(Exception e){
				
			}
			
		}
		return catchcheck;
	}
	private void heal(){
		int heal1=0;
		int heal2=0;
		for(int i=0;i<4;i++){
			try{
				if(Pokemonbox1.get(i).gethealth()==Pokemonbox1.get(i).getmaxhealth()){//判斷玩家1的寶可夢是否需要回復血量
					if(Pokemonbox1.get(i).healPPcheck()==false){//判斷玩家1的寶可夢是否需要回復技能點數
						heal1++;
					}
				}
				if(Pokemonbox2.get(i).gethealth()==Pokemonbox2.get(i).getmaxhealth()){//判斷玩家2的寶可夢是否需要回復血量
					if(Pokemonbox2.get(i).healPPcheck()==false){//判斷玩家2的寶可夢是否需要回復技能點數
						heal2++;
					}
				}
				
				
			}
			catch(Exception e){
				
			}
		}
		if(Pokemonbox1.size()==heal1 && Pokemonbox2.size()==heal2){
			System.out.println("不須回復");
		}
		else{
			for(int i=0;i<4;i++){
				try{
					Pokemonbox1.get(i).heal();//回復血量和技能點數
				}
				catch(Exception e){
					
				}
				try{
					Pokemonbox2.get(i).heal();//回復血量和技能點數
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
			for(int i=0;i<2;i++){
				fw.write(player.get(i)+"\n");//玩家1名稱寫入record.txt
				for(int j=0;j<=3;j++){
					try{//玩家1寶可夢寫入record.txt
						if(i==0){
							fw.write(Pokemonbox1.get(j).getname()+" "+Pokemonbox1.get(j).getlevel()+" "+Pokemonbox1.get(j).gethealth()+" "+Pokemonbox1.get(j).getskill1PP()+" "+Pokemonbox1.get(j).getskill2PP()+" "+Pokemonbox1.get(j).getexp()+" "+Pokemonbox1.get(j).getPitem()+"\r");
						}
						else if(i==1){
							fw.write(Pokemonbox2.get(j).getname()+" "+Pokemonbox2.get(j).getlevel()+" "+Pokemonbox2.get(j).gethealth()+" "+Pokemonbox2.get(j).getskill1PP()+" "+Pokemonbox2.get(j).getskill2PP()+" "+Pokemonbox2.get(j).getexp()+" "+Pokemonbox2.get(j).getPitem()+"\r");
						}
					}
					catch(Exception e){
					}
				}
				for(int j=0;j<=3;j++){
					try{//玩家1寶可夢寫入record.txt
						if(i==0){
							fw.write(Pitembox1.get(j)+"\r");
						}
						else if(i==1){
							fw.write(Pitembox2.get(j)+"\r");
						}
					}
					catch(Exception e){
					}
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
				if(c.length==1){//讀入的資料為玩家或道具
					if(rolemap.contains(c[0])){
						if(r==1){
							Pitembox1.add(c[0]);
						}
						else if(r==2){
							Pitembox2.add(c[0]);
						}
					}
					else{
						player.add(c[0]);
						r++;
					}
					
				}
				else if(c.length==6){//讀入的資料為寶可夢
					try{
						int c1 = Integer.parseInt(c[1]);
						int c2 = Integer.parseInt(c[2]);
						int c3 = Integer.parseInt(c[3]);
						int c4 = Integer.parseInt(c[4]);
						double c5 = Double.parseDouble(c[5]);
						
						if(r==1){
							Pokemon thepokemon=new Pokemon(c[0],c1,c2,c3,c4,c5);
							Pokemonbox1.add(thepokemon);
						}
						else if(r==2){
							Pokemon thepokemon=new Pokemon(c[0],c1,c2,c3,c4,c5);
							Pokemonbox2.add(thepokemon);
						}
					}
					catch(Exception e){
						System.out.println("Something Error");
					}
					
				}
				else if(c.length==7){//讀入的資料為寶可夢
					try{
						int c1 = Integer.parseInt(c[1]);
						int c2 = Integer.parseInt(c[2]);
						int c3 = Integer.parseInt(c[3]);
						int c4 = Integer.parseInt(c[4]);
						double c5 = Double.parseDouble(c[5]);
						String c6 = c[6];
						
						if(r==1){
							Pokemon thepokemon=new Pokemon(c[0],c1,c2,c3,c4,c5,c6);
							Pokemonbox1.add(thepokemon);
							thepokemon.equip(c6);
						}
						else if(r==2){
							Pokemon thepokemon=new Pokemon(c[0],c1,c2,c3,c4,c5,c6);
							Pokemonbox2.add(thepokemon);
							thepokemon.equip(c6);
						}
						
					}
					catch(Exception e){
						System.out.println("Something Error");
					}
					
				}
				
				
            }
			fr.close();
		}
		catch (Exception e) {
            System.out.println("Something Error");
        }
	}
	private void show(){//顯示寶可夢狀態
		System.out.println("玩家1:"+player.get(0));
		System.out.println("寶可夢名稱\t\t等級\t血量\t經驗\t技能1點數\t技能2點數\t道具");
		for (Pokemon item : Pokemonbox1) {
			System.out.println(item.getname()+"\t\t\t"+item.getlevel()+"\t"+item.gethealth()+"\t"+item.getexp()+"\t"+item.getskill1PP()+"\t\t"+item.getskill2PP()+"\t\t"+item.getPitem()+"\r");

		}
		for (String item : Pitembox1) {
			System.out.println(item);
		}

		System.out.println("\r");
		System.out.println("玩家2:"+player.get(1));
		for (Pokemon item : Pokemonbox2) {
			System.out.println(item.getname()+"\t\t\t"+item.getlevel()+"\t"+item.gethealth()+"\t"+item.getexp()+"\t"+item.getskill1PP()+"\t\t"+item.getskill2PP()+"\t\t"+item.getPitem()+"\r");

		}
		for (String item : Pitembox2) {
			System.out.println(item);
		}
		
	}
	private void showbattle(Pokemon a, Pokemon b){//戰鬥後顯示數據
		System.out.println("寶可夢名稱\t\t等級\t血量\t經驗\t技能1點數\t技能2點數");
		System.out.println(a.getname()+"\t\t\t"+a.getlevel()+"\t"+a.gethealth()+"\t"+a.getexp()+"\t"+a.getskill1PP()+"\t\t"+a.getskill2PP()+"\r");
		System.out.println("\r");
		System.out.println(b.getname()+"\t\t\t"+b.getlevel()+"\t"+b.gethealth()+"\t"+b.getexp()+"\t"+b.getskill1PP()+"\t\t"+b.getskill2PP()+"\r");

	}
	private void equip(){//處理裝備/卸除道具的方法
		boolean equip=false;
		while(!equip){
			show();
			System.out.println("input load Player NOofPokemon NOofitem");
			System.out.println("input unload Player NOofPokemon");
			System.out.println("input exit to exit");
			System.out.print(">");
			String args = ConsoleIn.readLine();
			String[] arg = args.split(" ");
			switch(arg[0]){
				case "load":
					if(arg.length==4){
						try{
							int nopoke = Integer.parseInt(arg[2]);
							int noitem = Integer.parseInt(arg[3]);
							if(arg[1].equals(player.get(0))){
								try{
									Pokemonbox1.get(nopoke-1).equip(Pitembox1.get(noitem-1));//裝上道具
									Pitembox1.remove(noitem-1);//將該道具從道具包包移除
									equip=true;

								}
								catch(Exception e){
									System.out.println("寶可夢編號或道具編號錯誤");
								}
							}
							else if(arg[1].equals(player.get(1))){
								try{
									Pokemonbox2.get(nopoke-1).equip(Pitembox2.get(noitem-1));//裝上道具
									Pitembox2.remove(noitem-1);//將該道具從道具包包移除
									equip=true;
								}
								catch(Exception e){
									System.out.println("寶可夢編號或道具編號錯誤");
								}
							}
							else{
								System.out.println("玩家名稱錯誤");
							}
						}
						catch(Exception e){
							System.out.println("格式錯誤");
						}
						
					}
					else{
						System.out.println("格式錯誤");
					}
					break;
				case "unload":
					if(arg.length==3){
						try{
							int nopoke = Integer.parseInt(arg[2]);
							if(arg[1].equals(player.get(0))){
								try{
									Pitembox1.add(Pokemonbox1.get(nopoke-1).getPitem());//加到道具包包
									Pokemonbox1.get(nopoke-1).unequip();//從寶可夢身上移除道具
									equip=true;
								}
								catch(Exception e){
									System.out.println("寶可夢編號或道具編號錯誤");
								}
							}
							else if(arg[1].equals(player.get(1))){
								try{
									Pitembox2.add(Pokemonbox2.get(nopoke-1).getPitem());//加到道具包包
									Pokemonbox2.get(nopoke-1).unequip();//從寶可夢身上移除道具
									equip=true;
								}
								catch(Exception e){
									System.out.println("寶可夢編號或道具編號錯誤");
								}
							}
							else{
								System.out.println("玩家名稱錯誤");
							}
						}
						catch(Exception e){
							System.out.println("格式錯誤");
						}
					}
					else{
						System.out.println("格式錯誤");
					}
					break;
				case "exit":
					equip=true;
					break;
				default:
				System.out.println("指令錯誤");
					break;
			}

		}
		show();
	}
	private void switchpokemon(){//交換寶可夢編號
		boolean switchpokemon=false;
		while(!switchpokemon){
			show();
			System.out.println("switch 玩家名字 編號 編號");
			System.out.print(">");
			String switchno = ConsoleIn.readLine();
			String[] switchpoke = switchno.split(" ");
			if(switchpoke.length==4){
				if(switchpoke[0].equals("switch")){
					try{
						int switch1 = Integer.parseInt(switchpoke[2]);
						int switch2 = Integer.parseInt(switchpoke[3]);
						switch1=switch1-1;
						switch2=switch2-1;
						if(switchpoke[1].equals(player.get(0))){
							Collections.swap(Pokemonbox1, switch1, switch2);//將arraylist編號裡面的值對調
							show();
							switchpokemon=true;
						}
						else if(switchpoke[1].equals(player.get(1))){
							Collections.swap(Pokemonbox2, switch1, switch2);//將arraylist編號裡面的值對調
							show();
							switchpokemon=true;
						}
					}
					catch(Exception e){
						System.out.println("該編號無寶可夢");
					}
					
				}
				else{
					System.out.println("格式錯誤");
				}
				
			}
			else{
				System.out.println("格式錯誤");
			}
			
		}
	}
	private void itemshow(String name){//顯示道具
		if(name.equals(player.get(0))){
			System.out.println("玩家1道具");
			for (String item : Pitembox1) {
				System.out.println(item);
			}
		}
		else if(name.equals(player.get(1))){
			System.out.println("玩家2道具");
			for (String item : Pitembox2) {
				System.out.println(item);
			}
		}
	}
}