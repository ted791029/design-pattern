package com.ted.app.role;

import com.ted.app.CandidateGroup;
import com.ted.app.RPG;
import com.ted.app.Troop;
import com.ted.app.actionTarget.ActionTargetGroup;
import com.ted.app.observer.Observer;
import com.ted.app.state.Normal;
import com.ted.app.state.State;
import com.ted.app.strategy.action.ActionStrategy;
import com.ted.app.strategy.action.BasicAttack;
import com.ted.app.strategy.action.skill.Skill;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.ted.app.util.ExceptionUtil.getExceptionFormatMsg;

public abstract class Role {

    protected static final ActionStrategy BASIC_ATTACK = new BasicAttack();

    private int hp;

    private String name;

    private int mp;

    private Set<Observer> observers;

    private RPG rpg;

    private int str;

    private State state;

    private List<Skill> skills;

    private Troop troop;

    public Role(int hp, String name, int mp, RPG rpg, int str, List<Skill> skills, Troop troop) {
        setHp(hp);
        this.name = name;
        setMp(mp);
        this.rpg = rpg;
        this.str = str;
        this.skills = skills;
        this.troop = troop;
        this.observers = new HashSet<>();
        this.state = new Normal(this);
    }

    public void addHp(int val){
        setHp(this.hp + val);
    }

    protected abstract List<Role> choose(List<Role> candidates, int targetCount);

    public void countDownState(){
        state.countDown();
    }

    public void damage(Object attacker, int val) {

        if(isDead()){
            return;
        }

        int totalVal = val;

        if(attacker instanceof Role){
            Role role = (Role) attacker;
            totalVal = role.damageBonus(val);
            printDamageHint(role, totalVal);
        }

        int hp = getHp() - totalVal;
        setHp(hp);

        if(isDead()){
            dead();
        }
    }

    private int damageBonus(int damage) {
        return state.damageBonus(damage);
    }

    public void dead() {
        setHp(0);
        troop.remove(this);
        notifyObservers();
        printDead();
    }

    public void enterState(State state) {
        state.exitState();
        setState(state);
        state.enterState();
    }

    public void executeAction(ActionStrategy actionStrategy, List<Role> roles) {
        int mpCost = actionStrategy.getMpCost();

        if(!hasEnoughMP(mpCost)){
            String msg = getExceptionFormatMsg("魔力不足");
            throw new RuntimeException(msg);
        }

        reduceMP(mpCost);
        actionStrategy.execute(this);
        actionStrategy.applyToTargets(this, roles);
    }

    public void generateStateEffect() {
        state.effect();
    }

    public abstract ActionStrategy getAction();

    public List<Role> getActionTargets(ActionStrategy actionStrategy, List<CandidateGroup> candidateGroups) {
        List<Role> actionTargets = new ArrayList<>();

        for(CandidateGroup candidateGroup : candidateGroups){
            List<ActionTargetGroup> actionTargetGroups = actionStrategy.getActionTargetGroups();
            List<Role> candidates = candidateGroup.getRoles();

            for(ActionTargetGroup actionTargetGroup : actionTargetGroups){
                int targetCount = actionTargetGroup.getTargetCount();
                //-1 代表 全選
                if(targetCount != -1 && shouldChoose(candidates, targetCount)){
                    printChooseHint(candidates, targetCount);
                    actionTargets =  choose(candidates, targetCount);
                }else {
                    actionTargets = candidateGroup.getRoles();
                }
            }
        }

        return actionTargets;
    }

    public boolean hasEnoughMP(int mpCost) {
        boolean isEnough = mp >= mpCost;

        if(!isEnough){
            printHasNotEnoughMPHint();
        }

        return isEnough;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public boolean isAlly(Role actionTarget){
        return troop.isSame(actionTarget) && actionTarget != this;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public boolean isEnemy(Role actionTarget){
        return troop.isDifferent(actionTarget);
    }

    public boolean isSelf(Role actionTarget){
        return this == actionTarget;
    }


    public void notifyObservers() {

        for(Observer observer : observers){
            observer.update();
        }
    }

    public void printChooseHint(List<Role> roles, int targetCount){
        System.out.printf("選擇 %d 位目標: ", targetCount);

        for(int i = 0; i < roles.size(); i++){
            System.out.printf("(%d) %s", i, roles.get(i));

            if(i < roles.size() - 1){
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    private void printDamageHint(Role attacker, int val){
        System.out.printf("%s 對 %s 造成 %d 點傷害。\n", attacker, this, val);
    }

    public void printGetActionHint() {
        System.out.print("選擇行動：(0) 普通攻擊");

        for (int i = 0; i < skills.size(); i++) {
            System.out.printf(" (%d) %s", i + 1, skills.get(i));
        }
        System.out.println();
    }

    public void printDead(){
        System.out.printf("%s 死亡。\n", this);
    }

    public void printHasNotEnoughMPHint(){
        System.out.println("你缺乏 MP，不能進行此行動。");
    }

    public void printTakeTurn(){
        System.out.printf("輪到 %s (HP: %d, MP: %d, STR: %d, State: %s)。\n", this, hp, mp, str, state);
    }

    private void reduceMP(int mpCost){
        setMp(mp - mpCost);
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    public boolean shouldChoose(List<Role> roles, int targetCount) {
        return roles.size() > targetCount;
    }

    public void takeTurn() {
        countDownState();
        printTakeTurn();
        generateStateEffect();

        if(isDead()){
            return;
        }

        state.takeTurn();
    }

    public void unRegister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public String toString(){
        return "[" + troop.getId() + "]" + name;
    }

    //========================================================================

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {

        if(hp < 0){
            hp = 0;
        }

        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {

        if(mp < 0){
            mp = 0;
        }

        this.mp = mp;
    }

    public Set<Observer> getObservers() {
        return observers;
    }

    public void setObservers(Set<Observer> observers) {
        this.observers = observers;
    }

    public RPG getRpg() {
        return rpg;
    }

    public void setRpg(RPG rpg) {
        this.rpg = rpg;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Troop getTroop() {
        return troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }
}
