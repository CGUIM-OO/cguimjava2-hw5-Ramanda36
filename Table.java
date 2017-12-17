import java.util.ArrayList;


public  class Table  {
	
private player[] players;//�s��Ҧ������a
	
	final int MAXPlAYER = 4 ;//�̦h�@�i�P��৤�X�ӤH
	
	private deck deckofTable ;//�s��Ҧ����P
	
	private Dealer dealerofTable;//�s��@�Ӳ��a
	
	private int[] pos_betArray;//�s��C�Ӫ��a�b�@���U���`(instance field)

	
	public Table(int nDeck)
	{
		deckofTable = new deck(nDeck);
		players = new player[MAXPlAYER];//�ŧi�@�Ӫ��׬OMAXPLAYER ��Player array
		
	}
	
	public void set_player(int pos , player p)//�NPlayer���P��W
	{
		pos_betArray = new int[MAXPlAYER];//�̦hMAXPLAYER�H�Ap�h��Player instance
		
//		if(pos <= MAXPlAYER)
//		{
//			pos_betArray[pos] = pos;
			players[pos] = p;//pos���P���m
//		}
	}
	
	public player[] get_player()
	{
		return players;//�^�ǩҦ��b�P��W��player
	}
	
	public void set_dealer(Dealer d)
	{
		dealerofTable = d;//�NDealer���P��W
	}
	
	public card get_face_up_card_of_dealer()
	{
		
		card temp = dealerofTable.getOneRoundCard().get(1);//�^��dealer���}�����i�P�A�]�N�O�ĤG�i�P
		return  temp;
	}
	
	private void ask_each_player_about_bets()
	{
		//here 
		for(int p = 0 ; p < players.length; p++)
		{
			players[p].sayHello();
			pos_betArray[p] = players[p].makeBet();//�C�Ӫ��a�U���`�n�s�bpos_betArray�Ѥ���ϥ�
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
			players[p].setOneRoundCard(Tempcard);//�o�P�����a����a�A���o��i���}���P�����a�A�A�@�i�\�۪��P�A�H�Τ@�i���}���P�����a
		}
		
		ArrayList<card> cardForTheDealer = new ArrayList<card>();
		cardForTheDealer.add(deckofTable.getOneCard(true));
		cardForTheDealer.add(deckofTable.getOneCard(false));
		dealerofTable.setOneRoundCard(cardForTheDealer);//�o�P�����a��A�b�e���W�L�X���a���}���P
		System.out.print("Dealer's face up card is "  );
		cardForTheDealer.get(1).printCard();
		System.out.println("");
		
		//here
	}
	private void ask_each_player_about_hits()
	{
		for(int p = 0 ; p < players.length; p++)
		{
			ArrayList<card> Tempcard = players[p].getOneRoundCard();//�ݨC�Ӫ��a�n���n�P
			while(players[p].hitMe()){
				Tempcard.add(deckofTable.getOneCard(true));
				players[p].setOneRoundCard(Tempcard);
				System.out.print("Hit! " + players[p].getName() + "'s card now:");
				players[p].printAllCard();
				System.out.println("");//�p�G�z�F�A�Ф��n�A�ݪ��a�O�_�n�P
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
		System.out.println("Dealer's hit is over");//�߰ݲ��a�O�_�n�P�A������A�L�X"Dealer's hit is over!"
		//dealerofTable.printAllCard();
		//here
	}
	private void calculate_chips()
	{
		System.out.print("Dealer's card value is" + " " + dealerofTable.getTotalValue() + " " +"Cards : ");//�L�X���a���I�ƩM�P
		dealerofTable.printAllCard();
		System.out.println("");
	
		
		for(int p = 0 ; p < players.length; p++)
		{
			System.out.println(players[p].getName() + "card value is " + players[p].getTotalValue());//�w��C�Ӫ��a�A���L�X ���a�m�W+" card value is "+���a�`�I��
			if(players[p].getTotalValue() > 21)//�ݽ�Ĺ�A�n�O���aĹ�A�⪱�a�w�X�S��
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
					else if(dealerofTable.getTotalValue() < players[p].getTotalValue())//�n�O���a��A�h�ߪ��a�P�U�`�w�X�۲Ť��w�X
					{
						players[p].increaseChips(-10);
						System.out.println("Get" +  " " +pos_betArray[p] +  " " +"Chips, the Chips now is" + " " +players[p].getCurrentChips() );
					}
					else if(dealerofTable.getTotalValue() == players[p].getTotalValue())
					{
						players[p].increaseChips(0);//���餣Ĺ
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

	
	 
	 
	 




