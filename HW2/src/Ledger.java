

public class Ledger 
{

   public int Day; 
   public String Category;
   public int Price;
   Ledger()
   {
       
       Category="";

   }
   Ledger( int Day,String Category,int Price)
   {
       this.Day = Day; 
       this.Category=Category;
       this.Price=Price;
   }

}