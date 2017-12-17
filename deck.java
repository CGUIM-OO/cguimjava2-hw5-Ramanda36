

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
				
				for(card.Suit s: card.Suit.values())//for(int s=0;s<4;s++)���
				{
					for(int y = 1 ; y <= 13 ; y ++)
						{
							card card=new card(s,y); //�s�J�C�@�i�P
							cards.add(card);
						}
				}
				
			}
			
			shuffle();

		}	
		
		
		public void shuffle()
		{
			openCard=new ArrayList<card>();//���mprivate ArrayList openCard; 
		
		Random rmd = new Random();//�H����@�i�P
			for(int a = 0 ; a < 52;a ++)
			{
				int j = rmd.nextInt(52-a);
				
				card temp = cards.get(j);//temp��@�Ȧs�Ŷ�
				cards.set(j, cards.get(a));//�P���洫
				cards.set(a, temp);
				
			}
		
			
			
			nUsed = 0;
			usedCard.clear();
			
		}
		
		public card getOneCard(boolean isOpened)//�M�w�o�X�h���P�O�}���٬O�\�_�Ӫ��A�Y�O�}�۪��P�A�[�JopenCard
		{
			if(nUsed==51)//��o�X�h52�i�P�ɴN�~�P
			{ 
				shuffle();
				return cards.get(nUsed);//nused���^0
			}
			else
			{   if(isOpened=true){
				openCard.add(cards.get(nUsed));
				}
				usedCard.add(cards.get(nUsed));//�O���o�X�h���P��usedcard
				
				nUsed ++;//�h�o�@�i�d
				return cards.get(nUsed-1);//��nUsed+1���~�t��^��
			}
			
			
		}
		public ArrayList<card> getOpenedCard(){
			
			return openCard;//�^�Ǧ��ƵP���Ҧ����}�L���P
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
