class monkey
{
    
        
    private int passday;
	
    private int stayofday;
	
    private int fetchbananaunit;
	
	private int accumbananaqty;

	private boolean isleave;
	
    monkey(int stayofday,int fetchbananaunit)
    {

        this.passday = 0;
		
        this.stayofday=stayofday;
		
        this.fetchbananaunit=fetchbananaunit;
		
		this.accumbananaqty=0;

        this.isleave=false;
    }
	int getaccumbananaqty()
	{
		return this.accumbananaqty;
	}
	void setnext(int nextday,tree[] treeGroup)
	{
	    if(this.isleave)
		   return;
		   
	    nextday = (this.stayofday - this.passday - nextday) > 0 ? nextday : this.stayofday - this.passday;

        this.passday = this.passday + nextday;
                                       
        if (this.passday >= this.stayofday)///防呆用,應該不會超過,上面nextday條件運算式已經卡掉
        {
                                           
            this.passday = this.stayofday;
                                            
        }
        int requredfetchunit=nextday*this.fetchbananaunit;                       
        
        for (int i=0;i<treeGroup.length;i++)
        {
            if(treeGroup[i]==null)
			   continue;
            
			if(treeGroup[i].getcategory().equals("banana"))
			{
			    if(treeGroup[i].getcurrentqty()==0)
				   continue;
				if(treeGroup[i].getcurrentqty()<requredfetchunit)
				{
				   requredfetchunit=requredfetchunit-treeGroup[i].getcurrentqty();
				   treeGroup[i].setzerocurrentqty();
				   
				}
			    else if(treeGroup[i].getcurrentqty()>=requredfetchunit)
			    {
                   int qty= treeGroup[i].getcurrentqty()-requredfetchunit;
                   treeGroup[i].setcurrentqty(qty);
				   requredfetchunit=0;
				   break;
				}
			}
            
        }
		accumbananaqty=accumbananaqty+nextday*this.fetchbananaunit-requredfetchunit;

		if(this.passday == this.stayofday)
		{
		   this.isleave=true;
		}
	}
	void setleaveorchard()
	{
	   this.isleave=true;
	} 
	
	boolean isleaveorchard()
	{
	   return this.isleave;
	}
    
}