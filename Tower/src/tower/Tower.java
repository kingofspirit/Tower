package tower;

import javafx.beans.property.*;

import javafx.scene.text.Text;

public class Tower extends Block{
	
	//enum KindOfTower {TOWER_A, TOWER_B}
	
	String name;
	int number;
	int coordinateX;
	int coordinateY;
	
	int level;
	int valueBuild;
	int valueSell;
	int valueUpdate;
	
	int damage;
	int scale;
	double time;
	
	String describe;
	
	String special = null;
	
	String kindOfTower;
	

	
	Tower() {
		
	}
	

	void towerSell(Tower tower) {
		
		Money.incMoney(valueSell);
		// code
	}
	
	void towerBuild(Tower tower) {
		
		//Money.decMoney(valueBuild);
		// to be continued
	}
}

class TowerA extends Tower{
	
	TowerA() {
		name = "Low-Power-Tower";
		level = 1;
		valueBuild = 15;
		valueSell = 12;
		valueUpdate = 10;
		
		damage = 10;
		scale = 2;
		time = 1.0;
		
		kindOfTower = "Tower_A";
		kindOfBlock = "tower";
		
		describe = ("Level " + level + "  " + name + 
				"\nDamage: " + damage + "\nColdingTime: " + time + " seconds" + 
				"\nAttackScale: " + scale + " blocks" + 
				"\nBuildCost:  $" + valueBuild + "\nSellCost:   $" + valueSell + 
				"\nUpdateCost: $" + valueUpdate + "\n\n" + special);
	}
	
	void towerUpdate(Tower tower) {
		
		tower.level++;
		tower.valueBuild += 15;
		tower.valueSell += 12;
		tower.valueUpdate += 10;
		tower.damage += 2;
		tower.time -= 0.1;
		
	}
	

}



class TowerB extends Tower{

	
	TowerB() {
		name = "Hign-Power-Tower";
		level = 1;
		valueBuild = 30;
		valueSell = 24;
		valueUpdate = 20;
		
		damage = 50;
		scale = 2;
		time = 5.0;
		
		special = "Slow down the speed of sheep.";
		
		kindOfTower = "Tower_B";
		kindOfBlock = "tower";
		
		describe = ("Level " + level + "  " + name + 
				"\nDamage: " + damage + "\nColdingTime: " + time + " seconds" + 
				"\nAttackScale: " + scale + " blocks" + 
				"\nBuildCost:  $" + valueBuild + "\nSellCost:   $" + valueSell + 
				"\nUpdateCost: $" + valueUpdate + "\n\n" + special);
	}
	

	void towerUpdate(Tower tower) {
		//this is a test
		tower.level++;
		tower.valueBuild += 30;
		tower.valueSell += 24;
		tower.valueUpdate += 20;
		tower.damage += 5;
		tower.time -= 0.2;
		
	}
	
	
		
}

class Money {
	
	
	static IntegerProperty money = new SimpleIntegerProperty();
	
	//
	
	static void setMoney(int money) {
		Money.money.setValue(money);
	}
	
	static int getMoney() {
		
		System.out.println(Money.money.getValue());
		return Money.money.getValue();
		
	}
	
	static void incMoney(int sell) {
		Money.money.setValue(Money.money.getValue() + sell);
	}
	
	static boolean decMoney(int build) {
		
		if((Money.money.getValue() - build) < 0) {
			errorDialog.errorDialogCorrect("Error, you don\'t have enouge money!");
			return false;
		}
		else {
			
			Money.money.setValue(Money.money.getValue() - build);
			return true;
		}
			
		
		
	}

	
}

