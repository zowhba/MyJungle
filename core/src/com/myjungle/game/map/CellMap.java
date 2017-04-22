package com.myjungle.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.myjungle.game.screen.GameScreen;
import com.myjungle.game.system.MyJungleInfo;
import com.myjungle.game.system.MyJungleUtil;
import com.myjungle.game.unit.AllBullet;
import com.myjungle.game.unit.AllUnit;

import java.util.ArrayList;

/**
 * Created by LeeWoochan on 2017-01-31.
 */

public class CellMap {
    GameScreen GAMESCREEN;

    public ArrayList<AllUnit>[] allUnitCells;
    public ArrayList<AllUnit>[] playerCells;
    public ArrayList<AllUnit>[] enemyCells;
    public ArrayList<AllBullet>[] playerBulletCells;
    public ArrayList<AllBullet>[] enemyBulletCells;
    public ArrayList<AllUnit>[] dieCells;

    int numCells;
    int cellsPerRow;
    int cellsPerCol;

    public CellMap(GameScreen gameScreen) {
        GAMESCREEN = gameScreen;
        this.cellsPerRow = (int) (MyJungleInfo.WORLD_WIDTH / MyJungleInfo.roomSize);
        this.cellsPerCol = 3;
        numCells = cellsPerRow * cellsPerCol;
        allUnitCells = new ArrayList[numCells];
        playerCells = new ArrayList[numCells];
        enemyCells = new ArrayList[numCells];
        playerBulletCells = new ArrayList[numCells];
        enemyBulletCells = new ArrayList[numCells];
        dieCells = new ArrayList[numCells];

        for (int i = 0; i < numCells; i++) {
            allUnitCells[i] = new ArrayList<AllUnit>();
            playerCells[i] = new ArrayList<AllUnit>();
            enemyCells[i] = new ArrayList<AllUnit>();
            playerBulletCells[i] = new ArrayList<AllBullet>();
            enemyBulletCells[i] = new ArrayList<AllBullet>();
            dieCells[i] = new ArrayList<AllUnit>();
        }
    }

    public void addPlayerCellMap(AllUnit units) {
        int cellX = (int) (units.x / MyJungleInfo.roomSize);
        int cellY = (int) ((units.y - MyJungleInfo.roomSize) / MyJungleInfo.roomSize);
        int cellId = cellX + cellY * cellsPerRow;
        units.cellNum = cellId;
        playerCells[cellId].add(units);
        allUnitCells[cellId].add(units);
    }

    public void addEnemyCellMap(AllUnit units) {
        int cellX = (int) (units.x / MyJungleInfo.roomSize);
        int cellY = (int) ((units.y - MyJungleInfo.roomSize) / MyJungleInfo.roomSize);
        int cellId = cellX + cellY * cellsPerRow;
        units.cellNum = cellId;
        enemyCells[cellId].add(units);
        allUnitCells[cellId].add(units);
    }

    public void addPlayerBulletCellMap(AllBullet units) {
        int cellX = (int) (units.x / MyJungleInfo.roomSize);
        int cellY = (int) ((units.y - MyJungleInfo.roomSize) / MyJungleInfo.roomSize);
        int cellId = cellX + cellY * cellsPerRow;
        units.cellNum = cellId;
        playerBulletCells[cellId].add(units);
    }

    public void addEnemyBulletCellMap(AllBullet units) {
        int cellX = (int) (units.x / MyJungleInfo.roomSize);
        int cellY = (int) ((units.y - MyJungleInfo.roomSize) / MyJungleInfo.roomSize);
        int cellId = cellX + cellY * cellsPerRow;
        units.cellNum = cellId;
        enemyBulletCells[cellId].add(units);
    }

    public void addDieCellMap(AllUnit units) {
        int cellX = (int) (units.x / MyJungleInfo.roomSize);
        int cellY = (int) ((units.y - MyJungleInfo.roomSize) / MyJungleInfo.roomSize);
        int cellId = cellX + cellY * cellsPerRow;
        units.cellNum = cellId;
        dieCells[cellId].add(units);
    }

    public void move() {
        unitMove(playerCells, enemyCells);
        unitMove(enemyCells, playerCells);
        bulletMove(playerBulletCells, enemyCells);
        bulletMove(enemyBulletCells, playerCells);
        allUnitCheckOut(playerCells);
        allUnitCheckOut(enemyCells);
        allBulletCheckOut(playerBulletCells);
        allBulletCheckOut(enemyBulletCells);
    }

    public void clearCells() {
        for (int i = 0; i < numCells; i++) {
            allUnitCells[i].clear();
            playerCells[i].clear();
            enemyCells[i].clear();
            playerBulletCells[i].clear();
            enemyBulletCells[i].clear();
            dieCells[i].clear();
        }
    }


    public void unitMove(ArrayList<AllUnit>[] x, ArrayList<AllUnit>[] y) {
        for (int i = 0; i < numCells; i++) {
            for (AllUnit units : x[i]) {
                if(!units.isNearCastle) {
                    if (allTrace(units, y, i) == false) {
                        units.obId = -1;
                        units.isAttack = false;
                    }
                }
                if(units.move) moveWay(units);
                if(units.collision) {
                    checkUnitCollision(units, x);
                    checkUnitCollision(units, y);
                }
                if(units.isAttack) {
                    units.attackCooltimeUpdate();
                    units.attackStateUpdate();
                } else{
                    units.attack = 0;
                    if(units.attackState>0) {
                        units.attackState = 1;
                        units.attackCooltime = 0;
                    } else{
                        units.attackCooltimeUpdate();
                    }
                }
            }
        }
    }
    public void allUnitCheckOut(ArrayList<AllUnit>[] x) {
        for (int i = 0; i < numCells; i++) {
            for (AllUnit units : x[i]) {
                unitCheckOut(units);
            }
        }
    }
    public void allBulletCheckOut(ArrayList<AllBullet>[] x) {
        for (int i = 0; i < numCells; i++) {
            for (AllBullet units : x[i]) {
                bulletCheckOut(units);
            }
        }
    }


    public void traceOther(AllUnit units, AllUnit units2, ArrayList<AllUnit>[] y) {
        units.obId = units2.id;
        units.direction.x = units2.x;
        units.direction.y = units2.y;
        if(units.x<units.direction.x){
            units.right = true;
        } else{
            units.right = false;
        }
        if ((units.attackRange * MyJungleInfo.roomSize) > Math.sqrt((MyJungleUtil.Abnum(units.x - units2.x) - units2.radius) * (MyJungleUtil.Abnum(units.x - units2.x) - units2.radius) + (MyJungleUtil.Abnum(units.y - units2.y) - units2.radius) * (MyJungleUtil.Abnum(units.y - units2.y) - units2.radius))) {
            units.isAttack = true;
            units.stop = true;
            if (units.attack == 1) {
                attack(units, units2, y);
            }
        } else {
            units.isAttack = false;
            if(units.move) units.stop = false;
        }
    }

    public boolean allTrace(AllUnit units, ArrayList<AllUnit>[] y, int i) {
        int range = units.range;
        double shortEnemyDistance = 999999;
        AllUnit traceEnemy = null;
        units.ObCellNum = -1;
        for (int k = -range; k <= range; k++) {
            for (int j = -range; j <= range; j++) {
                if (0 <= i + k * cellsPerRow + j && 60> i + k * cellsPerRow + j) {
                    if ((i + k * cellsPerRow + j) / cellsPerRow == (i + k * cellsPerRow) / cellsPerRow) {
                        for (AllUnit units2 : y[i + k * cellsPerRow + j]) {
                            if(units2.isTargeted) {
                                double distance = Math.sqrt((units2.x - units.x) * (units2.x - units.x) + (units2.y - units.y) * (units2.y - units.y));
                                if (distance < shortEnemyDistance) {
                                    shortEnemyDistance = distance;
                                    traceEnemy = units2;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (traceEnemy != null) {
            traceOther(units, traceEnemy, y);
            units.ObCellNum = traceEnemy.cellNum;
            return true;
        } else {
            return false;
        }
    }


    public void moveWay(AllUnit units) {
        if (units.obId != -1) {
            if (units.stop == false) {
                units.setLocationX(units.x + 0.017f * units.speed * ((units.direction.x - units.x) / (MyJungleUtil.Abnum(units.direction.x - units.x) + MyJungleUtil.Abnum(units.direction.y - units.y))));
                units.setLocationY(units.y + 0.017f * units.speed * ((units.direction.y - units.y) / (MyJungleUtil.Abnum(units.direction.x - units.x) + MyJungleUtil.Abnum(units.direction.y - units.y))));
            }
        } else {
            if(!isNearCastle(units)) {
                units.isNearCastle =false;
                units.stop = false;
                units.setLocationX(units.x + 0.017f * units.speed * ((units.destination.x - units.x) / (MyJungleUtil.Abnum(units.destination.x - units.x) + MyJungleUtil.Abnum(units.destination.y - units.y))));
                units.setLocationY(units.y + 0.017f * units.speed * ((units.destination.y - units.y) / (MyJungleUtil.Abnum(units.destination.x - units.x) + MyJungleUtil.Abnum(units.destination.y - units.y))));
            } else {
                units.isNearCastle = true;
                units.stop = true;
                if(units.obId==-1){
                    units.isAttack = true;
                }
            }
        }
    }

    public void bulletMove(ArrayList<AllBullet>[] x, ArrayList<AllUnit>[] y) {
        for (int i = 0; i < numCells; i++) {
            for (AllBullet units : x[i]) {
                checkBulletCollision(units,y);
                units.setLocationX(units.x + 0.017f * units.speed * units.direction.x);
                units.setLocationY(units.y + 0.017f * units.speed * units.direction.y);
            }
        }
    }
    public void checkUnitCollision(AllUnit units,ArrayList<AllUnit>[] y) {
        int i = units.cellNum;
        for (int k = -1; k <= 1; k++) {
            for (int j = -1; j <= 1; j++) {
                if (0 <= i + k * cellsPerRow + j &&60 > i + k * cellsPerRow + j) {
                    if ((i + k * cellsPerRow + j) / cellsPerRow == (i + k * cellsPerRow) / cellsPerRow) {
                        for (AllUnit units2 : y[i + k * cellsPerRow + j]) {
                            if (units.id != units2.id&& units2.collision) {
                                if(units.overlaps(units2)) {
                                    float partX = units2.x - units.x;
                                    float partY = units2.y - units.y;
                                    float partX2 = units.x - units2.x;
                                    if(partX<0) {
                                        if(units.team == 1){
                                            units.pushLocationX(units.x- Gdx.graphics.getDeltaTime() * units.speed * (partX / (MyJungleUtil.Abnum(partX) + MyJungleUtil.Abnum(partY))));
                                            units2.pushLocationX(units2.x- 1.5f*Gdx.graphics.getDeltaTime() * units2.speed * (partX2 / (MyJungleUtil.Abnum(partX) + MyJungleUtil.Abnum(partY))));
                                        } else{
                                            units.pushLocationX(units.x- 1.5f*Gdx.graphics.getDeltaTime() * units.speed * (partX / (MyJungleUtil.Abnum(partX) + MyJungleUtil.Abnum(partY))));
                                            units2.pushLocationX(units2.x- Gdx.graphics.getDeltaTime() * units2.speed * (partX2 / (MyJungleUtil.Abnum(partX) + MyJungleUtil.Abnum(partY))));
                                        }
                                    } else{
                                        if(units.team == 1){
                                            units.pushLocationX(units.x- 1.5f*Gdx.graphics.getDeltaTime() * units.speed * (partX / (MyJungleUtil.Abnum(partX) + MyJungleUtil.Abnum(partY))));
                                            units2.pushLocationX(units2.x - Gdx.graphics.getDeltaTime() * units2.speed * (partX2 / (MyJungleUtil.Abnum(partX) + MyJungleUtil.Abnum(partY))));
                                        } else{
                                            units.pushLocationX(units.x- Gdx.graphics.getDeltaTime() * units.speed * (partX / (MyJungleUtil.Abnum(partX) + MyJungleUtil.Abnum(partY))));
                                            units2.pushLocationX(units2.x- 1.5f*Gdx.graphics.getDeltaTime() * units2.speed * (partX2 / (MyJungleUtil.Abnum(partX) + MyJungleUtil.Abnum(partY))));
                                        }
                                    }
                                    units.pushLocationY(units.y + MathUtils.randomSign() - Gdx.graphics.getDeltaTime() * units.speed * (partY / (MyJungleUtil.Abnum(partX) + MyJungleUtil.Abnum(partY))));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void checkBulletCollision(AllUnit units,ArrayList<AllUnit>[] y) {
        int i = units.cellNum;
        for (int k = -1; k <= 1; k++) {
            for (int j = -1; j <= 1; j++) {
                if (0 <= i + k * cellsPerRow + j && 60 > i + k * cellsPerRow + j) {
                    if ((i + k * cellsPerRow + j) / cellsPerRow == (i + k * cellsPerRow) / cellsPerRow) {
                        for (AllUnit units2 : y[i + k * cellsPerRow + j]) {
                            if (units.id != units2.id) {
                                if (units.overlaps(units2)) {
                                    bulletAttack(units,units2,y);
                                    units.hp = -1;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void bulletCheckOut(AllUnit units) {
        if (units.x < perstpective(units)) {
            if(units.team == 0){
                bulletCastleAttack(units);
                units.hp = -1;
            } else{
                if(units.x < MyJungleInfo.roomSize){
                    units.hp = -1;
                }
            }
        }
        if (units.x > MyJungleInfo.WORLD_WIDTH-perstpective(units)) {
            if(units.team == 1){
                bulletCastleAttack(units);
                units.hp = -1;
            }else{
                if(units.x >MyJungleInfo.WORLD_WIDTH-MyJungleInfo.roomSize){
                    units.hp = -1;
                }
            }
        }
        if (units.y < MyJungleInfo.roomSize*1.5) {
            units.hp = -1;
        }
        if (units.y > MyJungleInfo.roomSize*3.5) {
            units.hp = -1;
        }
    }

    public void attack(AllUnit units, AllUnit units2, ArrayList<AllUnit>[] y) {
        if (units.attackType == "short") {
            units2.hp -= units.damage;
            units2.injured = true;
            GAMESCREEN.addAttackEffet(units.attackAnimationNum,units.right,units2.center);
        } else if(units.attackType=="long"){
            GAMESCREEN.addBullet(units.type,new Vector2(units.x,units.y),new Vector2(units2.x,units2.y),units2.id,units.damage,units.team);
        } else if(units.attackType=="push"){
            units2.hp -= units.damage;
            units2.injured = true;
            if(units.x<units2.x){
                units.setLocationX(units.x+units.speed/25);
                units2.setLocationX(units2.x+units.speed/25);
            } else{
                units.setLocationX(units.x-units.speed/25);
                units2.setLocationX(units2.x-units.speed/25);
            }
            GAMESCREEN.addAttackEffet(units.attackAnimationNum,units.right,units2.center);
        }
        if(units.team == 1){
            GAMESCREEN.MJ.allUnitSound.playerUnitSound[units.unitNumber].playSound(GAMESCREEN.MJ.allUnitSound.playerUnitSound[units.unitNumber].attackSound);
        } else{
            GAMESCREEN.MJ.allUnitSound.enemyUnitSound[units.unitNumber].playSound(GAMESCREEN.MJ.allUnitSound.enemyUnitSound[units.unitNumber].attackSound);
        }
        units.attack = 0;
    }

    public void bulletAttack(AllUnit units, AllUnit units2, ArrayList<AllUnit>[] y) {
        if(units.attackType=="collision"){
            units2.hp -= units.damage;
            units2.injured = true;
            GAMESCREEN.addAttackEffet(units.attackAnimationNum,units.right,units2.center);
        }
        if(units.team == 1){
            GAMESCREEN.MJ.allUnitSound.playSound(GAMESCREEN.MJ.allUnitSound.playerBulletSound[units.unitNumber]);
        } else{
            GAMESCREEN.MJ.allUnitSound.playSound(GAMESCREEN.MJ.allUnitSound.enemyBulletSound[units.unitNumber]);
        }
    }

    public void castleAttack(AllUnit units) {
        if(units.team == 1) {
            units.right = true;
            if (units.attackType == "short") {
                GAMESCREEN.setEnemyCastleHp(GAMESCREEN.enemyCastleHp-= units.damage);
                GAMESCREEN.shakeCastle(0);
            } else if (units.attackType == "long") {
                GAMESCREEN.addBullet(units.type,new Vector2(units.x,units.y), new Vector2(units.x + MyJungleInfo.roomSize * units.attackRange, units.y), -1,units.damage, units.team);
            } else if(units.attackType=="push") {
                GAMESCREEN.setEnemyCastleHp(GAMESCREEN.enemyCastleHp-= units.damage);
                GAMESCREEN.shakeCastle(0);
            }
        } else{
            units.right = false;
            if (units.attackType == "short") {
                GAMESCREEN.setPlayerCastleHp(GAMESCREEN.playerCastleHp-= units.damage);
                GAMESCREEN.shakeCastle(1);
            } else if (units.attackType == "long") {
                GAMESCREEN.addBullet(units.type,new Vector2(units.x,units.y), new Vector2(units.x - MyJungleInfo.roomSize * units.attackRange, units.y), -1,units.damage, units.team);
            } else if(units.attackType=="push") {
                GAMESCREEN.setPlayerCastleHp(GAMESCREEN.playerCastleHp-= units.damage);
                GAMESCREEN.shakeCastle(1);
            }
        }
        if(units.team == 1){
            GAMESCREEN.MJ.allUnitSound.playerUnitSound[units.unitNumber].playSound(GAMESCREEN.MJ.allUnitSound.playerUnitSound[units.unitNumber].attackSound);
        } else{
            GAMESCREEN.MJ.allUnitSound.enemyUnitSound[units.unitNumber].playSound(GAMESCREEN.MJ.allUnitSound.enemyUnitSound[units.unitNumber].attackSound);
        }
        units.attack = 0;
    }

    public void bulletCastleAttack(AllUnit units) {
        if(units.team == 1) {
            units.right = true;
            if (units.attackType == "collision") {
                GAMESCREEN.setEnemyCastleHp(GAMESCREEN.enemyCastleHp-= units.damage);
                GAMESCREEN.shakeCastle(0);
            }
        } else{
            units.right = false;
            if (units.attackType == "collision") {
                GAMESCREEN.setPlayerCastleHp(GAMESCREEN.playerCastleHp-= units.damage);
                GAMESCREEN.shakeCastle(1);
            }
        }
        if(units.team == 1){
            GAMESCREEN.MJ.allUnitSound.playSound(GAMESCREEN.MJ.allUnitSound.playerBulletSound[units.unitNumber]);
        } else{
            GAMESCREEN.MJ.allUnitSound.playSound(GAMESCREEN.MJ.allUnitSound.enemyBulletSound[units.unitNumber]);
        }
    }

    public void unitCheckOut(AllUnit units) {
        if(!units.outMap) {
            if (units.x < perstpective(units)) {
                units.pushLocationX(perstpective(units) + (float) Math.random());
            } else if (units.x > MyJungleInfo.WORLD_WIDTH - perstpective(units)) {
                units.pushLocationX(MyJungleInfo.WORLD_WIDTH - perstpective(units) - (float) Math.random());
            }
            if (units.y < MyJungleInfo.roomSize * 1.5f + units.radius) {
                units.pushLocationY(MyJungleInfo.roomSize * 1.5f + units.radius + (float) Math.random());
            } else if (units.y > MyJungleInfo.roomSize * 3.5f - units.radius) {
                units.pushLocationY(MyJungleInfo.roomSize * 3.5f - units.radius - (float) Math.random());
            }
        }
    }

    public boolean isNearCastle(AllUnit units){
        if(units.team == 1){
            if(units.x>MyJungleInfo.WORLD_WIDTH-perstpective(units) - units.attackRange*MyJungleInfo.roomSize){
                if(units.attack == 1) castleAttack(units);
                return true;
            }else{
                return false;
            }
        } else{
            if(units.x<perstpective(units) + units.attackRange*MyJungleInfo.roomSize){
                if(units.attack == 1) castleAttack(units);
                return true;
            }else{
                return false;
            }
        }
    }

    public float perstpective(AllUnit units){
        return (float)(MyJungleInfo.roomSize*2+units.radius+((units.y-1.5*MyJungleInfo.roomSize)/2));
    }
}