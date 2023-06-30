import java.util.ArrayList;
import java.lang.Math;
public class Pokemon
{
	private String name;
	private int level;
	private int health;
	private int maxhealth;
	private int str;
	private int speed;
	private String type;
	private double exp;
	private int attack;
	private ArrayList<Skill>skillgroup;
	private Skill theskill1;
	private Skill theskill2;
	private boolean skillcheck;
	private double temporaryexp;
	private double extraexp;
	private String Pitem;
	private int extrahp;
	public Pokemon(String name, int level)//建構寶可夢
	{
		skillgroup = new ArrayList<Skill>();
		Pitem="";
		this.name = name;
		this.level = level;
		this.exp = 0;
		this.temporaryexp=0;
		this.extraexp=0;
		this.extrahp=0;
		if(this.name.equals("Leafeon")){
			this.maxhealth = 80+level*40;
			this.health = maxhealth;
			this.str = 27+level*3;
			this.speed = 5+level*2;
			this.type = "Grass";
			this.attack = 12;
			theskill1 = new Skill("razorleaf",40,5,5);
			theskill2 = new Skill("leafblade",90,2,2);
			
		}
		else if(this.name.equals("Flareon")){
			this.maxhealth = 75+level*25;
			this.health = maxhealth;
			this.str = 30+level*5;
			this.speed = 7+level*1;
			this.type = "Fire";
			this.attack = 10;
			theskill1 = new Skill("ember",40,5,5);
			theskill2 = new Skill("flareblitz",120,2,2);
			
		}
		else if(this.name.equals("Vaporeon")){
			this.maxhealth = 70+level*35;
			this.health = maxhealth;
			this.str = 28+level*4;
			this.speed = 2+level*3;
			this.type = "Water";
			this.attack = 13;
			theskill1 = new Skill("watergun",40,5,5);
			theskill2 = new Skill("hydropump",110,2,2);
		}
		else if(this.name.equals("Espeon")){
			this.maxhealth = 75+level*35;
			this.health = maxhealth;
			this.str = 29+level*3;
			this.speed = 5+level*2;
			this.type = "Psychic";
			this.attack = 10;
			theskill1 = new Skill("confusion",50,5,5);
			theskill2 = new Skill("psybeam",120,2,2);
		}
		else if(this.name.equals("Umbreon")){
			this.maxhealth = 55+level*35;
			this.health = maxhealth;
			this.str = 30+level*4;
			this.speed = 3+level*3;
			this.type = "Dark";
			this.attack = 12;
			theskill1 = new Skill("snarl",40,5,5);
			theskill2 = new Skill("darkpulse",110,2,2);
		}
		skillgroup.add(theskill1);
		skillgroup.add(theskill2);
	}
	public Pokemon(String name, int level,int health,int skillpp1, int skillpp2, double exp)//建構寶可夢
	{
		skillgroup = new ArrayList<Skill>();
		this.name = name;
		this.level = level;
		this.Pitem="";
		this.extraexp=0;
		this.extrahp=0;
		this.temporaryexp=0;
		this.exp=exp;
		
		if(this.name.equals("Leafeon")){
			this.maxhealth = 80+level*40;
			this.health = health;
			this.str = 27+level*3;
			this.speed = 5+level*2;
			this.type = "Grass";
			this.attack = 12;
			theskill1 = new Skill("razorleaf",40,skillpp1,5);
			theskill2 = new Skill("leafblade",90,skillpp2,2);
			
		}
		else if(this.name.equals("Flareon")){
			this.maxhealth = 75+level*25;
			this.health = health;
			this.str = 30+level*5;
			this.speed = 7+level*1;
			this.type = "Fire";
			this.attack = 10;
			theskill1 = new Skill("ember",40,skillpp1,5);
			theskill2 = new Skill("flareblitz",120,skillpp2,2);
			
		}
		else if(this.name.equals("Vaporeon")){
			this.maxhealth = 70+level*35;
			this.health = health;
			this.str = 28+level*4;
			this.speed = 2+level*3;
			this.type = "Water";
			this.attack = 13;
			theskill1 = new Skill("watergun",40,skillpp1,5);
			theskill2 = new Skill("hydropump",110,skillpp2,2);
		}
		else if(this.name.equals("Espeon")){
			this.maxhealth = 75+level*35;
			this.health = maxhealth;
			this.str = 29+level*3;
			this.speed = 5+level*2;
			this.type = "Psychic";
			this.attack = 10;
			theskill1 = new Skill("confusion",50,5,5);
			theskill2 = new Skill("psybeam",120,2,2);
		}
		else if(this.name.equals("Umbreon")){
			this.maxhealth = 55+level*35;
			this.health = maxhealth;
			this.str = 30+level*4;
			this.speed = 3+level*3;
			this.type = "Dark";
			this.attack = 12;
			theskill1 = new Skill("snarl",40,5,5);
			theskill2 = new Skill("darkpulse",110,2,2);
		}
		skillgroup.add(theskill1);
		skillgroup.add(theskill2);
	}

	public Pokemon(String name, int level,int health,int skillpp1, int skillpp2, double exp, String Pitem)//建構寶可夢
	{
		skillgroup = new ArrayList<Skill>();
		this.name = name;
		this.level = level;
		this.Pitem=Pitem;
		this.extraexp=0;
		this.extrahp=0;
		this.temporaryexp=0;
		this.exp=exp;
		if(this.name.equals("Leafeon")){
			this.maxhealth = 80+level*40;
			this.health = health;
			this.str = 27+level*3;
			this.speed = 5+level*2;
			this.type = "Grass";
			this.attack = 12;
			theskill1 = new Skill("razorleaf",40,skillpp1,5);
			theskill2 = new Skill("leafblade",90,skillpp2,2);
			
		}
		else if(this.name.equals("Flareon")){
			this.maxhealth = 75+level*25;
			this.health = health;
			this.str = 30+level*5;
			this.speed = 7+level*1;
			this.type = "Fire";
			this.attack = 10;
			theskill1 = new Skill("ember",40,skillpp1,5);
			theskill2 = new Skill("flareblitz",120,skillpp2,2);
			
		}
		else if(this.name.equals("Vaporeon")){
			this.maxhealth = 70+level*35;
			this.health = health;
			this.str = 28+level*4;
			this.speed = 2+level*3;
			this.type = "Water";
			this.attack = 13;
			theskill1 = new Skill("watergun",40,skillpp1,5);
			theskill2 = new Skill("hydropump",110,skillpp2,2);
		}
		else if(this.name.equals("Espeon")){
			this.maxhealth = 75+level*35;
			this.health = maxhealth;
			this.str = 29+level*3;
			this.speed = 5+level*2;
			this.type = "Psychic";
			this.attack = 10;
			theskill1 = new Skill("confusion",50,5,5);
			theskill2 = new Skill("psybeam",120,2,2);
		}
		else if(this.name.equals("Umbreon")){
			this.maxhealth = 55+level*35;
			this.health = maxhealth;
			this.str = 30+level*4;
			this.speed = 3+level*3;
			this.type = "Dark";
			this.attack = 12;
			theskill1 = new Skill("snarl",40,5,5);
			theskill2 = new Skill("darkpulse",110,2,2);
		}
		skillgroup.add(theskill1);
		skillgroup.add(theskill2);
	}
	public String getname(){
		return this.name;
	}
	public int getlevel(){
		return this.level;
	}
	public int gethealth(){
		return this.health;
	}
	public int getmaxhealth(){
		return this.maxhealth;
	}
	public String gettype(){
		return this.type;
	}
	
	public int getattack(){
		return this.attack;
	}
	public int getstr(){
		return this.str;
	}
	public int getspeed(){
		return this.speed;
	}
	public double getexp(){
		return this.exp;
	}
	public int getskill1PP(){
		return theskill1.getPP();
	}
	public int getskill2PP(){
		return theskill2.getPP();
	}
	public String getPitem(){
		return this.Pitem;
	}
	public int damage(String skillname, double effect){//計算技能傷害
		int skill = 0;
		for(int i=0;i<2;i++){
			if(skillgroup.get(i).getskillname().equals(skillname)){
				skill = skillgroup.get(i).useskill();
			}
		}
		double damage = (this.str+skill)*effect;
		
		return(int) Math.round(damage);
	}
	public int damage(double effect){	//計算普攻傷害	
		double damage = (this.str+this.attack)*effect;

		return (int) Math.round(damage);
	}
	public int sethealth(int damage){
		int remainhealth;
		remainhealth = this.health - damage+this.extrahp;
		if(remainhealth<0){
			remainhealth = 0;
		}
		this.health = remainhealth;
		return this.health;
		
	}
	public static double typerelation(Pokemon u, Pokemon v){//計算屬性克制與否的倍率
		double times=1;
		if(("Grass").equals(u.gettype())){
			if(("Grass").equals(v.gettype())){
				times=1;
			}
			else if(("Fire").equals(v.gettype())){
				times=0.8;
			}
			else if(("Water").equals(v.gettype())){
				times=1.2;
			}
			else if(("Psychic").equals(v.gettype())){
				times=1;
			}
			else if(("Dark").equals(v.gettype())){
				times=1;
			}
		}
		if(("Fire").equals(u.gettype())){
			if(("Grass").equals(v.gettype())){
				times=1.2;
			}
			else if(("Fire").equals(v.gettype())){
				times=1;
			}
			else if(("Water").equals(v.gettype())){
				times=0.8;
			}
			else if(("Psychic").equals(v.gettype())){
				times=1;
			}
			else if(("Dark").equals(v.gettype())){
				times=1;
			}
		}
		if(("Water").equals(u.gettype())){
			if(("Grass").equals(v.gettype())){
				times=0.8;
			}
			else if(("Fire").equals(v.gettype())){
				times=1.2;
			}
			else if(("Water").equals(v.gettype())){
				times=1;
			}
			else if(("(Psychic").equals(v.gettype())){
				times=1;
			}
			else if(("Dark").equals(v.gettype())){
				times=1;
			}
		}
		if(("Psychic").equals(u.gettype())){
			if(("Grass").equals(v.gettype())){
				times=1;
			}
			else if(("Fire").equals(v.gettype())){
				times=1;
			}
			else if(("Water").equals(v.gettype())){
				times=1;
			}
			else if(("Psychic").equals(v.gettype())){
				times=1;
			}
			else if(("Dark").equals(v.gettype())){
				times=0.8;
			}
		}
		if(("Dark").equals(u.gettype())){
			if(("Grass").equals(v.gettype())){
				times=1;
			}
			else if(("Fire").equals(v.gettype())){
				times=1;
			}
			else if(("Water").equals(v.gettype())){
				times=1;
			}
			else if(("Psychic").equals(v.gettype())){
				times=1.2;
			}
			else if(("Dark").equals(v.gettype())){
				times=1;
			}
		}
		return times;
	}
	public boolean skillcheck(String name){//確認技能名稱是否正確
		skillcheck = false;
		for(int i=0;i<2;i++){
			if(skillgroup.get(i).getskillname().equals(name)){
				skillcheck = true;
			}
		}

		return skillcheck;
	}
	public boolean skillPPcheck(String name){//確認技能點數有無
		boolean skillPPcheck = false;
		for(int i=0;i<2;i++){
			if(skillgroup.get(i).getskillname().equals(name)){
				if(skillgroup.get(i).PPcheck()){
					skillPPcheck = true;
				}
			}
		}
		return skillPPcheck;
	}
	public void temporaryexp(double temporaryexp){
		this.temporaryexp=this.temporaryexp+temporaryexp;
	}
	public int expc(){
		this.exp=this.exp+this.temporaryexp+this.extraexp;
		this.temporaryexp=0;
		return levelup(this.exp);
	}
	public int runexp(){
		this.exp=this.exp-50;
		if(this.exp<=0){
			this.exp=0;
		}
		return levelup(this.exp);
	}
	private int levelup(double exp){//計算是否提升等級
		int finlevel=1;
		
		if(200<=exp && exp<800)
			finlevel=2;
		if(800<=exp){
			finlevel=3;
		}
		return finlevel;
	}
	public void heal(){//回復血量和技能點數
		this.health=this.maxhealth;
		theskill1.setPP();
		theskill2.setPP();
	}
	public boolean healPPcheck(){//確認是否需要回復技能點數
		boolean healPPcheck=true;
		if(theskill1.getPP()==theskill1.getmaxPP()){
			if(theskill2.getPP()==theskill2.getmaxPP()){
				healPPcheck=false;
			}
		}
		return healPPcheck;
	}
	public void healhealth(){
		this.health=this.health+50;
		if(this.health>this.maxhealth){
			this.health=this.maxhealth;
		}
	}
	public void ppplus(){
		theskill1.setPP();
		theskill2.setPP();
	}
	public void equip(String Pitem){//裝備道具
		this.Pitem=Pitem;
		if(this.Pitem.equals("quickclaw")){
			this.speed=this.speed+3;
		}
		else if(this.Pitem.equals("protector")){
			this.maxhealth=this.maxhealth+50;
		}
		else if(this.Pitem.equals("leftovers")){
			this.extrahp=20;
		}
		else if(this.Pitem.equals("kingsrock")){
			this.str=this.str+5;
		}
		else if(this.Pitem.equals("luckyegg")){
			this.extraexp=50;
		}
	}
	public void unequip(){//移除道具
		if(this.Pitem.equals("quickclaw")){
			this.speed=this.speed-3;
		}
		else if(this.Pitem.equals("protector")){
			this.maxhealth=this.maxhealth-50;
		}
		else if(this.Pitem.equals("leftovers")){
			this.extrahp=0;
		}
		else if(this.Pitem.equals("kingsrock")){
			this.str=this.str-5;
		}
		else if(this.Pitem.equals("luckyegg")){
			this.extraexp=0;
		}
		this.Pitem="";
	}
	public void levelup(int level){
		if(this.name.equals("Leafeon")){
			this.maxhealth = 80+level*40;
			this.str = 27+level*3;
			this.speed = 5+level*2;
			
		}
		else if(this.name.equals("Flareon")){
			this.maxhealth = 75+level*25;
			this.str = 30+level*5;
			this.speed = 7+level*1;
			
		}
		else if(this.name.equals("Vaporeon")){
			this.maxhealth = 70+level*35;
			this.str = 28+level*4;
			this.speed = 2+level*3;
		}
		else if(this.name.equals("Espeon")){
			this.maxhealth = 75+level*35;
			this.str = 29+level*3;
			this.speed = 5+level*2;
		}
		else if(this.name.equals("Umbreon")){
			this.maxhealth = 55+level*35;
			this.str = 30+level*4;
			this.speed = 3+level*3;
		}
	}
}