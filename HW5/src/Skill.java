public class Skill{
	private String skillname;
	private int skillattack;
	private int PP;
	private int maxPP;
	private boolean PPcheck;
	public Skill(String skillname, int skillattack,int PP, int maxPP){//建構技能屬性
		this.skillname = skillname;
		this.skillattack = skillattack;
		this.maxPP = maxPP;
		this.PP = PP;
	}
	public int getPP(){
		return this.PP;
	}
	public int getmaxPP(){
		return this.maxPP;
	}
	public String getskillname(){
		return this.skillname;
	}
	public boolean PPcheck(){
		if(this.PP>0){
			PPcheck=true;
		}
		else{
			PPcheck=false;
		}
		return PPcheck;
	}
	public int useskill(){
		this.PP = this.PP-1;
		return skillattack;
	}
	public void setPP(){
		this.PP=maxPP;
	}
}