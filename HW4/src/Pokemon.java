import java.util.ArrayList;
public class Pokemon
{
	private String name;
	private int level;
	private double health;
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
	public Pokemon(String name, int level,double health,int skillpp1, int skillpp2)//建構寶可夢
	{
		skillgroup = new ArrayList<Skill>();
		
		this.name = name;
		this.level = level;
		if(this.level==1){
			this.exp=0;
		}
		else if(this.level==2){
			this.exp=200;
		}
		else if(this.level==3){
			this.exp=800;
		}
		
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
		if(this.name.equals("Flareon")){
			this.maxhealth = 75+level*25;
			this.health = health;
			this.str = 30+level*5;
			this.speed = 7+level*1;
			this.type = "Fire";
			this.attack = 10;
			theskill1 = new Skill("ember",40,skillpp1,5);
			theskill2 = new Skill("flareblitz",120,skillpp2,2);
			
		}
		if(this.name.equals("Vaporeon")){
			this.maxhealth = 70+level*35;
			this.health = health;
			this.str = 28+level*4;
			this.speed = 2+level*3;
			this.type = "Water";
			this.attack = 13;
			theskill1 = new Skill("watergun",40,skillpp1,5);
			theskill2 = new Skill("hydropump",110,skillpp2,2);
		}
		this.temporaryexp=0;
		skillgroup.add(theskill1);
		skillgroup.add(theskill2);
	}
	public Pokemon(String name, int level)//建構寶可夢
	{
		skillgroup = new ArrayList<Skill>();
		
		this.name = name;
		this.level = level;
		this.exp = 0;
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
		if(this.name.equals("Flareon")){
			this.maxhealth = 75+level*25;
			this.health = maxhealth;
			this.str = 30+level*5;
			this.speed = 7+level*1;
			this.type = "Fire";
			this.attack = 10;
			theskill1 = new Skill("ember",40,5,5);
			theskill2 = new Skill("flareblitz",120,2,2);
			
		}
		if(this.name.equals("Vaporeon")){
			this.maxhealth = 70+level*35;
			this.health = maxhealth;
			this.str = 28+level*4;
			this.speed = 2+level*3;
			this.type = "Water";
			this.attack = 13;
			theskill1 = new Skill("watergun",40,5,5);
			theskill2 = new Skill("hydropump",110,2,2);
		}
		this.temporaryexp=0;
		skillgroup.add(theskill1);
		skillgroup.add(theskill2);
	}
	public String getname(){
		return this.name;
	}
	public int getspeed(){
		return this.speed;
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
	public double gethealth(){
		return this.health;
	}
	public int getmaxhealth(){
		return this.maxhealth;
	}
	public double damage(String skillname, double effect){//計算技能傷害
		int skill = 0;
		for(int i=0;i<2;i++){
			if(skillgroup.get(i).getskillname().equals(skillname)){
				skill = skillgroup.get(i).useskill();
			}
		}
		double damage = (this.str+skill)*effect;
		return damage;
	}
	public double damage(double effect){	//計算普攻傷害	
		double damage = (this.str+this.attack)*effect;
		return damage;
	}
	public double sethealth(double damage){
		double remainhealth;
		remainhealth = this.health - damage;
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
			if(("Fire").equals(v.gettype())){
				times=0.8;
			}
			if(("Water").equals(v.gettype())){
				times=1.2;
			}
		}
		if(("Fire").equals(u.gettype())){
			if(("Grass").equals(v.gettype())){
				times=1.2;
			}
			if(("Fire").equals(v.gettype())){
				times=1;
			}
			if(("Water").equals(v.gettype())){
				times=0.8;
			}
		}
		if(("Water").equals(u.gettype())){
			if(("Grass").equals(v.gettype())){
				times=0.8;
			}
			if(("Fire").equals(v.gettype())){
				times=1.2;
			}
			if(("Water").equals(v.gettype())){
				times=1;
			}
		}
		return times;
	}
	public boolean skillcheck(String name){//確認技能名稱是否正確
		skillcheck = true;
		if(skillgroup.contains(name)){
			skillcheck = true;
		}
		else{
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
		return skillcheck;
	}
	public int getlevel(){
		return this.level;
	}
	public void temporaryexp(double temporaryexp){
		this.temporaryexp=this.temporaryexp+temporaryexp;
	}
	public int expc(){
		this.exp=this.temporaryexp;
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
	public int getskill1PP(){
		return theskill1.getPP();
	}
	public int getskill2PP(){
		return theskill2.getPP();
	}
	public double getexp(){
		return this.exp;
	}
}