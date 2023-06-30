public class hive
{
   private int count;
   public hive()
   {
      this.count=0;
	  
   }
   public void add(int no)
   {
      this.count=this.count+no;
   }
   public int getcount()
   {
      return this.count;
   }
   public void subone()
   {
      if(this.count>0)
	   this.count--;//破壞hive
	   
   }
}