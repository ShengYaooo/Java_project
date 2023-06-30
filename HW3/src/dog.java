import java.util.ArrayList;


public class dog
{
   
	
    private int chasemonkeyno;///驅趕monkey數
	
    private int accumchasemonkeyno;
    public dog(int chasemonkeyno)
    {
		
        this.chasemonkeyno=chasemonkeyno;
       this.accumchasemonkeyno=0;
    }
	public int getaccumchasemonkeyno()
    {
        return this.accumchasemonkeyno;
    }
    public void setnext(int nextday,ArrayList<monkey> monkeyGroup)
    {
        
        
        int requiredchasemonkeyno = this.chasemonkeyno*nextday;
           
            
        for (monkey themonkey : monkeyGroup) 
        {
            if(requiredchasemonkeyno<=0)///破壞monkey的數量由chasemonkeyno決定,題目chasemonkeyno為3
                break;
                   
                
            if(!themonkey.isleaveorchard())
            {
                themonkey.setleaveorchard();
                requiredchasemonkeyno--;
                       
            }
        }
        this.accumchasemonkeyno=this.accumchasemonkeyno+this.chasemonkeyno*nextday-requiredchasemonkeyno;
	
    }
}