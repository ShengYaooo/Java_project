public class tree
{
    private String  category;               // pomelo or banana
    private int     loc;                    // location
	private int     lifeofday;              // life 天數
	private int     passday;                //  經過的天數
	private int     maxqty;                //  果實上限
	private int     unitperday;             //  每天產生
	private int     currentqty;            //  目前累積
	private boolean stopproduction;         //  flag 是否停止產出
	
	
	public tree(String category,int loc,int life,int maxqty,int unitperday)
	{
	    this.passday=0;
		this.category=category;
		this.loc=loc;
		this.lifeofday=life;
		this.maxqty=maxqty;
		this.unitperday=unitperday;
		this.currentqty=0;
		this.stopproduction=false;
	}
	public void setzerocurrentqty()
    {
        this.currentqty=0;
    }
	public void setcurrentqty(int qty)
    {
        this.currentqty=qty;
    }
	public String getcategory()
	{
		return this.category;
	}
	public int getloc()
	{
		return this.loc;
	}
    public int getcurrentqty()
    {
        return this.currentqty;
    }
	public int getpassday()
	{
		return this.passday;
	}
	public int getlifeofday()
	{
		return this.lifeofday;
	}
	public int getmaxqty()
	{
		return this.maxqty;
	}
	public void setnext(int nextday,int hiveCount)
	{
	    if(this.stopproduction)
		   return;//達到上限的話就不結果
		   
	    nextday = (this.lifeofday - this.passday - nextday) > 0 ? nextday : this.lifeofday - this.passday;

        this.passday = this.passday + nextday;
                                       
        if (this.passday >= this.lifeofday)///防呆用,應該不會超過,上面nextday條件運算式已經卡掉
        {
                                           
            this.passday = this.lifeofday;
                                            
        }
                                      
        this.currentqty = this.currentqty + this.unitperday * nextday+hiveCount*2;
        if (this.currentqty >= this.maxqty)
        { 
		   this.currentqty = this.maxqty;   
		   
		   
		}
		if(this.passday == this.lifeofday || this.currentqty == this.maxqty)
		{
		   this.stopproduction=true;
		}
	}
	///使用陣列來集合tree後,破壞的動作用不到 destroy method
	// public boolean isdestroyed()
	// {
	//    return this.isdestroy;
	// }
	// public void setdestroy()///被破壞後須調整設定值
	// {
	//     this.passday = this.lifeofday;
		
	//     this.currentqty =0;
	//     this.isdestroy = true;
	// 	this.stopproduction=true;
	// }
	
	public void addlifeofday(int days)///proune後重啟生產
	{
	    this.lifeofday = this.lifeofday + days;
  
        this.stopproduction=false;
	
	}
	
}
