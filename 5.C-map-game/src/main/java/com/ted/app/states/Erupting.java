package com.ted.app.states;

import com.ted.app.Map;
import com.ted.app.mapObjects.MapObject;
import com.ted.app.mapObjects.Roles.Role;

import java.util.ArrayList;
import java.util.List;

public class Erupting extends State{
    public Erupting(Map map, Role role) {
        super(map, role);
        setDuration(1);
    }

    @Override
    public List<MapObject> attackRange(){
        Map map = getMap();
        Role role = getRole();
        List<MapObject> attackTargets = new ArrayList<>();
        int rows = map.getGridsRows();
        int cols = map.getGridsCols();

        for(int i = 0; i < rows; i++){
            for (int j = 0; j< cols; j++){
                MapObject mapObject = map.getMapObject(i, j);

                if(mapObject instanceof Role targetRole){
                    if(targetRole.getClass() != role.getClass()){
                        attackTargets.add(targetRole);
                    }
                }
            }
        }
        role.setStateEffect(true);
        return attackTargets;
    }

    @Override
    protected void effectEnd(){
        Map map = getMap();
        Role role = getRole();
        role.enterState(new Teleport(map, role));
    }

    public void enterState(){
        Role role = getRole();
        int duration = getDuration();
        setCountdown(duration);
        role.setAttackDamage(50);
    }

    public void exitState(){
        Role role = getRole();
        int originDamage = role.getOriginDamage();
        role.setOriginDamage(originDamage);
    }

    @Override
    public String show() {
        return "爆發";
    }
}
