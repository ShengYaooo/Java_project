
public class bear
{
   
    private int passday;
	
    private int stayofday;
	
    private int destorytreeunit;
	
	private boolean isleave;
	
    public bear(int stayofday,int destorytreeunit)
    {

        this.passday = 0;
		
        this.stayofday=stayofday;
		
        this.destorytreeunit=destorytreeunit;
		
        isleave=false;
    }
	
	public void setnext(int nextday,tree[] treeGroup,hive hiveGroup)
	{
	    if(this.isleave)
		   return;//查看bear是否離開
		   
	    nextday = (this.stayofday - this.passday - nextday) > 0 ? nextday : this.stayofday - this.passday;

        this.passday = this.passday + nextday;
                                       
        if (this.passday >= this.stayofday)///防呆用,應該不會超過,上面nextday條件運算式已經卡掉
        {
                                           
            this.passday = this.stayofday;
                                            
        }
        if(hiveGroup.getcount()>0)
            hiveGroup.subone();//有hive的話破壞
        else
        {	
		    int requireddestroytreeunit = this.destorytreeunit;
           
			
            for (int i=0 ;i<treeGroup.length;i++) 
            {
			    if(requireddestroytreeunit<=0)///破壞樹的數量由destorytreeunit決定,這裡destorytreeunit為1
				   break;
				if(treeGroup[i]==null)
                   continue;  
                
                treeGroup[i]=null;
                requireddestroytreeunit--;
               
			}
            
        }
		
		if(this.passday == this.stayofday)
		{
		   this.isleave=true;//bear離開
		}
	}
	public boolean isleaveorchard()
	{
	   return this.isleave=true;
	}
    
}