

import java.util.ArrayList;
import java.util.Random;

public class deck {
	
		private ArrayList<card> cards;
		private ArrayList<card> usedCard = new ArrayList<card>();
		public int nUsed;
		private ArrayList<card> openCard;
		
		
		
		
		//TODO: Please implement the constructor (30 points)
		public deck(int nDeck){
			cards=new ArrayList<card>();
			
			//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
			//Hint: Use new Card(x,y) and 3 for loops to add card into deck
			//Sample code start
			//Card card=new Card(1,1); ->means new card as clubs ace
			//cards.add(card);
			//Sample code end
			
			for(int q = 0 ; q < nDeck; q++)
			{
//				for(int x = 1; x <= 4 ; x++)
//				{
//					for(int y = 1 ; y <= 13 ; y ++)
//					{
//						Card card=new Card(x,y); 
//						cards.add(card);
//					}
//				}
				
				for(card.Suit s: card.Suit.values())//for(int s=0;s<4;s++)花色
				{
					for(int y = 1 ; y <= 13 ; y ++)
						{
							card card=new card(s,y); //存入每一張牌
							cards.add(card);
						}
				}
				
			}
			
			shuffle();

		}	
		
		
		public void shuffle()
		{
			openCard=new ArrayList<card>();//重置private ArrayList openCard; 
		
		Random rmd = new Random();//隨機選一張牌
			for(int a = 0 ; a < 52;a ++)
			{
				int j = rmd.nextInt(52-a);
				
				card temp = cards.get(j);//temp當作暫存空間
				cards.set(j, cards.get(a));//牌兩兩交換
				cards.set(a, temp);
				
			}
		
			
			
			nUsed = 0;
			usedCard.clear();
			
		}
		
		public card getOneCard(boolean isOpened)//決定發出去的牌是開著還是蓋起來的，若是開著的牌，加入openCard
		{
			if(nUsed==51)//當發出去52張牌時就洗牌
			{ 
				shuffle();
				return cards.get(nUsed);//nused重回0
			}
			else
			{   if(isOpened=true){
				openCard.add(cards.get(nUsed));
				}
				usedCard.add(cards.get(nUsed));//記錄發出去的牌到usedcard
				
				nUsed ++;//多發一張卡
				return cards.get(nUsed-1);//把nUsed+1的誤差減回來
			}
			
			
		}
		public ArrayList<card> getOpenedCard(){
			
			return openCard;//回傳此副牌中所有打開過的牌
		}
		
		//TODO: Please implement the method to print all cards on screen (10 points)
		public void printDeck(){
			//Hint: print all items in ArrayList<Card> cards, 
			//TODO: please implement and reuse printCard method in Card class (5 points)
			for(int k = 0 ; k < cards.size();k++)
			{
				cards.get(k).printCard();
			}
		}
		public ArrayList<card> getAllCards(){
			return cards;
		
		}
	
}
