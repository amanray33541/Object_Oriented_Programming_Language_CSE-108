#include <iostream>
using namespace std;

class Unit{
protected:
    int range, position, health, damage, cost, step_size, return_coin;
    string shoot_type, movement, unit;

public:
    void HealthDecrease()
    {
        health=health-40;
    }

    void PositionIncrease()
    {
        position=position+step_size;
    }

    void showHealth()
    {
        cout << unit << ". Health : " << health << ". " ;
    }

    void showPosition()
    {
        cout << "Positioned at " << position << ". " ;
    }

    void Shooting()
    {
        cout << unit << " is shooting " << shoot_type << ". " ;
    }

    void showState()
    {
        cout << movement << ". ";
    }

    int getPosition()
    {
        return position;
    }

    int getRange()
    {
        return range;
    }

    int getHealth()
    {
        return health;
    }

    int getDamage()
    {
        return damage;
    }

    int getCost()
    {
        return cost;
    }

    int getReturnCoin()
    {
        return return_coin;
    }

    bool isDead()
    {
        if(health <= 0) return true;
        else return false;
    }


    virtual void setHealth(int x)=0;
    virtual void showReturnCoin()=0;

    void Game()
    {
        cout << "Choose your option :" << endl;
        cout << "1. Archer (Cost 150)" << endl;
        cout << "2. Advanced Archer (Cost 600)" << endl;
        cout << "3. Bowman (Cost 100)" << endl;
        cout << "4. Advanced Bowman (Cost 200)" << endl;
        void showReturnCoin();
    }

};

class Archer:public Unit
{

public:
    Archer(int x)
    {
        range=50;
        position=0;
        health=100;
        damage=25;
        cost=150;
        step_size=25;
        return_coin=0;
        shoot_type="Advanced Arrow";
        movement="Running";
        unit="Archer";
    }

    void showReturnCoin(){}
    void setHealth(int x){}

};

class AdvancedArcher:public Unit
{
public:
    AdvancedArcher(int x)
    {
        range=50;
        position=0;
        health=120;
        damage=50;
        cost=600;
        step_size=30;
        return_coin=0;
        shoot_type="Improved Arrow";
        movement="Riding Horse";
        unit="AdvancedArcher";
    }

    void showReturnCoin(){}
    void setHealth(int x){}
};

class Bowman:public Unit
{
public:
    Bowman(int x)
    {
        range=110;
        position=0;
        health=60;
        damage=10;
        cost=100;
        step_size=0;
        return_coin=40;
        shoot_type="Basic Arrow";
        movement="Stationary";
        unit="Bowman";
    }

    void showReturnCoin()
    {
        cout << "Bowman gave 40 coins while dying." << endl;
    }

    void setHealth(int x){}

};

class AdvancedBowman:public Unit
{
public:
    AdvancedBowman(int x)
    {
        range=130;
        position=0;
        health=85;
        damage=15;
        cost=200;
        step_size=0;
        return_coin=60;
        shoot_type="Canon";
        movement="Stationary";
        unit="AdvancedBowman";
    }

    void showReturnCoin()
    {
        cout << "Advanced Bowman gave 60 coins while dying." << endl;
    }

    void setHealth(int x){}
};

class Enemy : public Unit
{
public:
    Enemy()
    {
        range=200;
        position=100;
        health=300;
        damage=40;
        step_size=0;
        shoot_type="Fire Arrow";
        movement='\0';
        unit="Enemy Tower";
    }

    void setHealth(int x)
    {
        health=x;
    }

    /*void PositionIncrease()
    {
        position=position+step_size;
    }

    void showHealth()
    {
        cout << "EnemyTower's Health : " << health << " " ;
    }

    void Shooting()
    {
        cout << "EnemyTower is shooting Fire Arrows." << endl;
    }

    int getPosition()
    {
        return position;
    }

    int getRange()
    {
        return range;
    }

    int getHealth()
    {
        return health;
    }

    bool isDead()
    {
        if(health <= 0) return true;
        else return false;
    }*/

    void showReturnCoin()
    {

    }
};

int main()
{
    Unit *w, *e;
    //Enemy *e,enemy;
    e= new Enemy;
    int choice,i=1,total_coin=1600;

        cout << "Coin remaining : 1600" << endl;
        cout << "Choose your option :" << endl;
        cout << "1. Archer (Cost 150)" << endl;
        cout << "2. Advanced Archer (Cost 600)" << endl;
        cout << "3. Bowman (Cost 100)" << endl;
        cout << "4. Advanced Bowman (Cost 200)" << endl;

    while(total_coin>0)
    {
        cin >> choice;

        switch(choice)
        {
            case 1: w =  new Archer(0);
                    break;
            case 2: w = new AdvancedArcher(0);
                    break;
            case 3: w =  new Bowman(0);
                    break;
            default : w = new AdvancedBowman(0);
                    break;
        }

        if(total_coin < w->getCost())
        {
            cout << "You do not have enough coin" << endl;
        }
        else
            total_coin=total_coin-w->getCost();

        cout << "Round : " << i++ << endl;

        while(!w->isDead() && !e->isDead())
        {
            if(w->getPosition() < e->getPosition())
                w->PositionIncrease();

            w->showHealth();
            w->showState();
            w->showPosition();

            if(e->getPosition() - w->getPosition() <= w->getRange())
            {
                e->setHealth(e->getHealth()-w->getDamage());
            }
            w->Shooting();
            cout <<" "<< endl;
            e->showHealth();

            if(e->getPosition() - w->getPosition() <= e->getRange())
            {
                w->HealthDecrease();
            }
            e->Shooting();
            cout << endl;
            cout << "Round : " << i++ << endl;
        }

        if(w->isDead())
        {
            w->showHealth();
            total_coin=total_coin+w->getReturnCoin();
            cout << "Unit Died!!" << endl;
            w->showReturnCoin();
            i--;
        }

        cout << "Coin remaining :" << total_coin << endl;

        if(e->isDead())
        {
            cout << "Enemy Tower destroyed. Congratulation!!" << endl;
            return 0;
        }

        w->Game();
        delete w;

        ///You have to complete all the functionalities of the user using the base class pointer w.
        ///That means you have to call the derived class functions using only this.
    }

}
