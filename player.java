import java.util.ArrayList;

public class player extends Person {//�~��Person

	private String name; 
	private int chips;
	private int bet;
	private ArrayList<card> oneRoundCard = new ArrayList<card>();
	
	
	
	public player(String name, int chips) {
	this.name=name;//�]���Q�л\�n��this��instance variable
	this.chips=chips;
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public int makeBet(){
	if(chips == 0){
	System.out.println("�z�w�g�S���A�L�k�~��U�`�F");//�S���w�X��return0 �٦����w�X�]10
	return bet=0;
	}
	else
	return bet=10;
	}
	
//	

	
	public boolean hitMe() {
		
		if(getTotalValue()<17){//16�I�H�U�n�P�A17�I�H�W���n�P
		return true;}
		else
			return false;//�O�_�n�P�A�O�^��true�A���A�n�P�h�^��false
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
//		return total;//�^�Ǧ��P���ұo���d�I�ƥ[�`
//			}
//				
//				
			
		//	total+=cards.getCard();
			
		
		

	public int getCurrentChips() {
		
		return chips;
		
	}
	public void increaseChips (int diff) {
		
		chips-=diff;//���a�w�X�ܰ�
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
