package board.base;

import board.impl.BasketActionDelete;
import board.impl.BasketActionList;
import board.impl.CategoryBasketAction;
import board.impl.CustomerActionJoin;
import board.impl.CustomerActionLogin;
import board.impl.CustomerActionLogout;
import board.impl.CustomerActionUpdate;
import board.impl.DetailActionView;
import board.impl.DetailBasketAction;
import board.impl.InfoActionform;
import board.impl.JoinActionform;
import board.impl.LoginActionform;
import board.impl.MainActionView;
import board.impl.MainBasketAction;
import board.impl.MypageActionform;
import board.impl.PurchaseActionList;
import board.impl.PurchaseActionform;
import board.impl.categoryActionList;
import board.impl.categoryActionView;

public class ActionFactory {

	public Action getAction(String command) {
		
		Action action = null;
		
		if(command == null) {
				action = new categoryActionList();               
		}
		else {
				switch(command) {
				case "MAINVIEW": 
					action = new MainActionView();
					break;
				case "CATEGORYVIEW": 
					action = new categoryActionView();
					break;
				case "DETAILVIEW":
					action = new DetailActionView();
					break;
				case "MAINBASKET":
					action = new MainBasketAction();
					break;
				case "CATEGORYBASKET":
					action = new CategoryBasketAction();
					break;
				case "DETAILBASKET":
					action = new DetailBasketAction();
					break;
				case "LOGINFORM":
					action = new LoginActionform();
					break;
				case  "LOGIN" :
			         action = new CustomerActionLogin();
			         break;
				case  "LOGOUT" :
					action = new CustomerActionLogout();
					break;
				case  "JOINFORM" :
					action = new JoinActionform();
					break;
				case  "JOIN" :
					action = new CustomerActionJoin();
					break;
				case  "MYPAGE" :
					action = new MypageActionform();
					break;				
				case  "INFO" :
					action = new InfoActionform();
					break;				
				case  "CUPDATE" :
					action = new CustomerActionUpdate();
					break;				
				case  "BASKETLIST" :
					action = new BasketActionList();
					break;
				case  "DELETEBASKET" :
					action = new BasketActionDelete();
					break;
				case  "PURCHASEFORM" :
					action = new PurchaseActionform();
					break;
				case  "PURCHASELIST" :
					action = new PurchaseActionList();
					break;
			}	
		}
		return action;
	}
}
