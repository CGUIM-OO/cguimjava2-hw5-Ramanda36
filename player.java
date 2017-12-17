import java.util.ArrayList;

public class player extends Person {//繼承Person

	private String name; 
	private int chips;
	private int bet;
	private ArrayList<card> oneRoundCard = new ArrayList<card>();
	
	
	
	public player(String name, int chips) {
	this.name=name;//因為被覆蓋要用this用instance variable
	this.chips=chips;
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public int makeBet(){
	if(chips == 0){
	System.out.println("您已經沒錢，無法繼續下注了");//沒有籌碼時return0 還有時籌碼設10
	return bet=0;
	}
	else
	return bet=10;
	}
	
//	

	
	public boolean hitMe() {
		
		if(getTotalValue()<17){//16點以下要牌，17點以上不要牌
		return true;}
		else
			return false;//是否要牌，是回傳true，不再要牌則回傳false
	}
	
//	public int getTotalValue() {
//		int rank=0;
//		int total=0;
//		for(int i=0;i<oneRoundCard.size();i++){
//			rank=oneRoundCard.get(i).getRank();
////			 for(card member :oneRoundCard ){ 
////			int	rank= member.getRank(); 
//				 if(rank==11||rank==12||rank==13){
//					 rank=10;
//				 }
//          total+=rank;
//          if(total<=21&&rank==1){
//        	  total+=10;
//          
//          
//		}
//	}
//		return total;//回傳此牌局所得的卡點數加總
//			}
//				
//				
			
		//	total+=cards.getCard();
			
		
		

	public int getCurrentChips() {
		
		return chips;
		
	}
	public void increaseChips (int diff) {
		
		chips-=diff;//玩家籌碼變動
	}
	
	public void sayHello(){
		
		
		System.out.println("Hello, I am " + name + ".");
	
		System.out.println("I have " + chips + " chips.");
	}

	@Override
	public boolean hit_me(Table table) {//here
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
}
