public class hw1{
	public static void main(String[] args){
		int a = 0;
		//總人數

		boolean b = false;
		//確認是否繼續預約下一位顧客的服務項目
		
		int t = 0;
		//總金額
		
		while(b == false)
		{
		int p = 0;
		//個人金額
		
		//1.
        System.out.println("Welcome!");
		
		while(p == 0)
		{
		//2.
		System.out.print("Please enter m or f (m is for male, f is for female):");
		char gender = ConsoleIn.readLineNonwhiteChar();
		System.out.print("Gender:");
		if(gender == 'm')
		{
			System.out.println("male");
		}
		if(gender == 'f')
		{
			System.out.println("female");
		}
		//以gender來辦別男女
		
		
		//3.
		System.out.print("是否指定設計師(y is for Yes, n is for No):");
		char d1 = ConsoleIn.readLineNonwhiteChar();
		if(d1 == 'y')
		{
			System.out.println("指定設計師");
			System.out.print("Price:");
			p = p + 500;
			System.out.println(p);
		}
		if(d1 == 'n')
		{
			System.out.println("不指定設計師");
			System.out.print("Price:");
			p = p + 0;
			System.out.println(p);
		}
		//以d1來辦別是否指定設計師
		
		
		//4.
		System.out.print("是否需要剪髮服務(y is for Yes, n is for No):");
		char d2 = ConsoleIn.readLineNonwhiteChar();
		if(gender == 'f')
		{
			if(d2 == 'y')
			{
				System.out.print("是否需要只剪瀏海(y is for Yes, n is for No):");
				char d21 = ConsoleIn.readLineNonwhiteChar();
				if(d21 == 'y')
				{
					System.out.println("修剪瀏海");
					System.out.print("Price:");
					p = p + 50;
					System.out.println(p);
				}
				if(d21 == 'n')
				{
					System.out.println("精緻剪髮");
					System.out.print("Price:");
					p = p + 150;
					System.out.println(p);
				}
				//以d21來辨別是否女生只修剪劉海
			}
			else
			{
				System.out.println("無剪髮服務");
				System.out.print("Price:");
				p = p + 0;
				System.out.println(p);
			}
		}
		//以d2來辨別女生是否需要剪髮服務
		if(gender == 'm')
		{
			if(d2 == 'y')
			{
				System.out.println("精緻剪髮");
				System.out.print("Price:");
				p = p + 100;
				System.out.println(p);
			}
			if(d2 == 'n')
			{
				System.out.println("無剪髮服務");
				System.out.print("Price:");
				p = p + 0;
				System.out.println(p);
			}
		}
		//以d2來辨別男生是否需要剪髮服務
		
		
		//5.
		System.out.print("是否需要染髮服務(y is for Yes, n is for No):");
		char d3 = ConsoleIn.readLineNonwhiteChar();
		if(d3 == 'y')
		{
			System.out.print("選擇染劑(1 為一般染劑，2為天然護髮染):");
			int d31 = ConsoleIn.readLineInt();
			if(d31 == 1)
			{
				System.out.println("一般染劑");
				System.out.print("Price:");
				p = p + 499;
				System.out.println(p);
			}
			if(d31 == 2)
			{
				System.out.println("天然護髮染");
				System.out.print("Price:");
				p = p + 999;
				System.out.println(p);
			}
			//以d31來辨別染劑種類
		}
		if(d3 == 'n')
		{
			System.out.println("無染髮服務");
			System.out.print("Price:");
			p = p + 0;
			System.out.println(p);
		}
		//以d3來辨別是否需要染髮服務
		
		
		//6.
		System.out.print("是否需要護髮服務(y is for Yes, n is for No):");
		char d4 = ConsoleIn.readLineNonwhiteChar();
		if(d4 == 'y')
		{
			if(gender == 'f')
			{
				System.out.println("護髮服務");
				System.out.print("Price:");
				p = p + 720;
				System.out.println(p);
			}
			if(gender == 'm')
			{
				System.out.println("護髮服務");
				System.out.print("Price:");
				p = p + 360;
				System.out.println(p);
			}
			
		}
		if(d4 == 'n')
		{
			System.out.println("無護髮服務");
			System.out.print("Price:");
			p = p + 0;
			System.out.println(p);
		}
		//以d4來辨別是否需要護髮服務
		
		
		//7.
		System.out.print("是否需要燙髮服務(y is for Yes, n is for No):");
		char d5 = ConsoleIn.readLineNonwhiteChar();
		if(d5 == 'y')
		{
			System.out.print("選擇燙髮項目( 1 為髮根澎澎燙，2 為局部燙，3 為哥德式閃亮):");
			int d51 = ConsoleIn.readLineInt();
			if(d51 == 1)
			{
				System.out.println("髮根澎澎燙");
				System.out.print("Price:");
				p = p + 1000;
				System.out.println(p);
			}
			if(d51 == 2)
			{
				System.out.println("局部燙");
				System.out.print("Price:");
				p = p + 500;
				System.out.println(p);
			}
			if(d51 == 3)
			{
				System.out.println("哥德式閃亮");
				System.out.print("Price:");
				p = p + 350;
				System.out.println(p);
			}
			//以d51來辨別燙髮種類
		}
		if(d5 == 'n')
		{
			System.out.println("無燙髮服務");
			System.out.print("Price:");
			p = p + 0;
			System.out.println(p);
		}
		//以d5來辨別是否需要燙髮服務
		
		
		//8.
		System.out.print("是否需要洗髮服務(y is for Yes, n is for No):");
		char d6 = ConsoleIn.readLineNonwhiteChar();
		if(d6 == 'y')
		{
			System.out.println("洗髮服務");
			System.out.print("Price:");
			p = p + 50;
			System.out.println(p);
		}
		if(d6 == 'n')
		{
			System.out.println("無洗髮服務");
			System.out.print("Price:");
			p = p + 0;
			System.out.println(p);
		}
		//以d6來辨別是否需要洗髮服務
		}
		//要是p(個人金額)為0的話返回步驟2.
		if(p == 0)
		{
			a = a;
		}
		else
		{
			a = a + 1;
			//增加人數
		}
		t = t + p;
		//將個人金額加到總金額中
		
		
		//10.
		System.out.print("目前已輸入");
		System.out.print(a);
		System.out.println("位顧客");
		System.out.print("是否繼續預約下一位顧客的服務項目(y is for Yes, n is for No):");
		char d7 = ConsoleIn.readLineNonwhiteChar();
		if(d7 == 'y')
		{
			b = false;
		}
		if(d7 == 'n')
		{
			b = true;
		}
		}
		//利用布林值b來確認是否有下一位顧客
		System.out.print("總人數:");
		System.out.println(a);
		if(a == 1)
		{
			System.out.println("無折扣優惠");
			System.out.print("總金額:");
			System.out.println(t);
		}
		if(a == 2)
		{
			System.out.println("折扣優惠9折");
			System.out.print("總金額:");
			System.out.println(Math.floor(t*0.9) );
			
		}
		if(a >=3)
		{
			System.out.println("折扣優惠8折");
			System.out.print("總金額:");
			System.out.println(Math.floor(t*0.8) );
		}
		//確認人數、適用折扣、總金額
	}
}