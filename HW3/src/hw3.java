public  class hw3 
{
   
   
    public static void main(String[] args) throws Exception
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//清除cmd
        MainApp myApp=new MainApp();
        myApp.Start();//進入主程式
        
        
    }
}