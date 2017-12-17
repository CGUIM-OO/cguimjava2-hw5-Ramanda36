import java.util.ArrayList;


public  class Table  {
	
private player[] players;//存放所有的玩家
	
	final int MAXPlAYER = 4 ;//最多一張牌桌能坐幾個人
	
	private deck deckofTable ;//存放所有的牌
	
	private Dealer dealerofTable;//存放一個莊家
	
	private int[] pos_betArray;//存放每個玩家在一局下的注(instance field)

	
	public Table(int nDeck)
	{
		deckofTable = new deck(nDeck);
		players = new player[MAXPlAYER];//宣告一個長度是MAXPLAYER 的Player array
		
	}
	
	public void set_player(int pos , player p)//將Player放到牌桌上
	{
		pos_betArray = new int[MAXPlAYER];//最多MAXPLAYER人，p則為Player instance
		
//		if(pos <= MAXPlAYER)
//		{
//			pos_betArray[pos] = pos;
			players[pos] = p;//pos為牌桌位置
//		}
	}
	
	public player[] get_player()
	{
		return players;//回傳所有在牌桌上的player
	}
	
	public void set_dealer(Dealer d)
	{
		dealerofTable = d;//將Dealer放到牌桌上
	}
	
	public card get_face_up_card_of_dealer()
	{
		
		card temp = dealerofTable.getOneRoundCard().get(1);//回傳dealer打開的那張牌，也就是第二張牌
		return  temp;
	}
	
	private void ask_each_player_about_bets()
	{
		//here 
		for(int p = 0 ; p < players.length; p++)
		{
			players[p].sayHello();
			pos_betArray[p] = players[p].makeBet();//每個玩家下的注要存在pos_betArray供之後使用
		}
		
	}
	
	private void distribute_card_to_dealer_and_players()
	{
		for(int p = 0 ; p < players.length; p++)
		{
			ArrayList<card> Tempcard = new ArrayList<card>();
			for(int times = 0 ; times < 2 ; times ++)
			{	
				Tempcard.add(deckofTable.getOneCard(true));
			}
			players[p].setOneRoundCard(Tempcard);//發牌給玩家跟莊家，先發兩張打開的牌給玩家，再一張蓋著的牌，以及一張打開的牌給莊家
		}
		
		ArrayList<card> cardForTheDealer = new ArrayList<card>();
		cardForTheDealer.add(deckofTable.getOneCard(true));
		cardForTheDealer.add(deckofTable.getOneCard(false));
		dealerofTable.setOneRoundCard(cardForTheDealer);//發牌給莊家後，在畫面上印出莊家打開的牌
		System.out.print("Dealer's face up card is "  );
		cardForTheDealer.get(1).printCard();
		System.out.println("");
		
		//here
	}
	private void ask_each_player_about_hits()
	{
		for(int p = 0 ; p < players.length; p++)
		{
			ArrayList<card> Tempcard = players[p].getOneRoundCard();//問每個玩家要不要牌
			while(players[p].hitMe()){
				Tempcard.add(deckofTable.getOneCard(true));
				players[p].setOneRoundCard(Tempcard);
				System.out.print("Hit! " + players[p].getName() + "'s card now:");
				players[p].printAllCard();
				System.out.println("");//如果爆了，請不要再問玩家是否要牌
			}
			System.out.println(players[p].getName()+ " " + "Pass hit !");
		}
		
		
		
		//here
	}
	private void ask_dealer_about_hits()
	{
		ArrayList<card> Tempcard = dealerofTable.getOneRoundCard();
		while(dealerofTable.hit_me(this)){
			Tempcard.add(deckofTable.getOneCard(true));
			dealerofTable.setOneRoundCard(Tempcard);
		}
		System.out.println("Dealer's hit is over");//詢問莊家是否要牌，完成後，印出"Dealer's hit is over!"
		//dealerofTable.printAllCard();
		//here
	}
	private void calculate_chips()
	{
		System.out.print("Dealer's card value is" + " " + dealerofTable.getTotalValue() + " " +"Cards : ");//印出莊家的點數和牌
		dealerofTable.printAllCard();
		System.out.println("");
	
		
		for(int p = 0 ; p < players.length; p++)
		{
			System.out.println(players[p].getName() + "card value is " + players[p].getTotalValue());//針對每個玩家，先印出 玩家姓名+" card value is "+玩家總點數
			if(players[p].getTotalValue() > 21)//看誰贏，要是莊家贏，把玩家籌碼沒收
			{
				players[p].increaseChips(10);
				System.out.println("Loss" + pos_betArray[p] + "Chips, the Chips now is" + players[p].getCurrentChips() );
			}
			else
			{
				if(dealerofTable.getTotalValue() <=21)
				{
					if(dealerofTable.getTotalValue() > players[p].getTotalValue())
					{
						players[p].increaseChips(10);
						System.out.println("Loss" +  " " +pos_betArray[p] + " " +"Chips, the Chips now is" +" "+ players[p].getCurrentChips() );
					}
					else if(dealerofTable.getTotalValue() < players[p].getTotalValue())//要是莊家輸，則賠玩家與下注籌碼相符之籌碼
					{
						players[p].increaseChips(-10);
						System.out.println("Get" +  " " +pos_betArray[p] +  " " +"Chips, the Chips now is" + " " +players[p].getCurrentChips() );
					}
					else if(dealerofTable.getTotalValue() == players[p].getTotalValue())
					{
						players[p].increaseChips(0);//不輸不贏
						System.out.println("Chips have no change! The Chips now is" + " "+ players[p].getCurrentChips() );
					}
				}
				else
				{
					players[p].increaseChips(-10);
					System.out.println("Get" + " " + pos_betArray[p] +  " " +"Chips, the Chips now is" + " "+ players[p].getCurrentChips() );
				}
			}
			
			
		}
		
		
		//here
	}
	public int[] get_palyers_bet()
	{
		return pos_betArray;
	}
	
	public void play()
	{
		ask_each_player_about_bets();
		distribute_card_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	
	
	 }

	
	 
	 
	 




