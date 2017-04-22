package com.myjungle.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.myjungle.game.MyJungle;
import com.myjungle.game.animation.AttackAnimation;
import com.myjungle.game.animation.UnitAnimation;
import com.myjungle.game.map.CellMap;
import com.myjungle.game.system.MyJungleInfo;
import com.myjungle.game.ui.game.GameAttackEffet;
import com.myjungle.game.ui.game.GameFoodIcon;
import com.myjungle.game.ui.game.GameResultDialog;
import com.myjungle.game.ui.game.GameStage;
import com.myjungle.game.unit.AllBullet;
import com.myjungle.game.unit.AllUnit;
import com.myjungle.game.unit.enemyUnit.bullet.EnemyArrowInfo;
import com.myjungle.game.unit.enemyUnit.bullet.EnemyGunbulletInfo;
import com.myjungle.game.unit.enemyUnit.cannon.EnemyCannonInfo;
import com.myjungle.game.unit.enemyUnit.ground.EnemyAxemanInfo;
import com.myjungle.game.unit.enemyUnit.ground.EnemyBulldozerInfo;
import com.myjungle.game.unit.enemyUnit.ground.EnemyClubmanInfo;
import com.myjungle.game.unit.enemyUnit.ground.EnemyGunmanInfo;
import com.myjungle.game.unit.enemyUnit.ground.EnemyHunterInfo;
import com.myjungle.game.unit.enemyUnit.ground.EnemySawmanInfo;
import com.myjungle.game.unit.playerUnit.bullet.PlayerSpitInfo;
import com.myjungle.game.unit.playerUnit.bullet.PlayerStoneInfo;
import com.myjungle.game.unit.playerUnit.cannon.PlayerCannonInfo;
import com.myjungle.game.user.UserInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by LeeWoochan on 2017-01-31.
 */

public class GameScreen extends AllScreen {
    public float cameraX;
    public float cameraY;

    public ArrayList<AllUnit> allUnits;
    public ArrayList<AllBullet> allBullets;

    public ArrayList<GameAttackEffet> attackEffets;
    public ArrayList<GameFoodIcon> gamefoodIcons;

    public int playerUnitsNum;
    public int enemyUnitsNum;

    OrthographicCamera camera;

    Vector3 touchP;

    Texture background;

    Texture foodAppleTexture = new Texture("gameImage/apple.png");
    Texture foodBucketPourTexture = new Texture("gameImage/bucketPour.png");

    public Texture musicTexture = new Texture("gameImage/music.png");
    public Texture soundTexture = new Texture("gameImage/sound.png");

    TextureRegion playerCastle;
    TextureRegion enemyCastle;

    int playerCastleX;
    int enemyCastleX;

    float fpsTime;

    float fps;

    public float playerFood = 0;
    public float enemyFood = 1000;

    public int playerCastleHp = MJ.castleInfo.castleHp*(int)Math.pow(1.1, UserInfo.userCastleInfo[1]);
    public int enemyCastleHp = MJ.castleInfo.castleHp*(int)Math.pow(1.1,MJ.stageInfo.castleLevel);

    public int gameResult = -1;

    public float foodCooltime = 0;

    public float waterGauge = 0;

    public boolean deathMatch = false;

    CellMap cellMap;

    UnitAnimation unitAnimation;
    AttackAnimation attackAnimation;

    public GameStage gameStage;

    Descending descending;
    Ascending ascending;

    public Vector2 tapVecter2 = new Vector2();

    public boolean pause;

    Label foodNumLabel;


    public GameScreen(MyJungle mj) {
        super(mj);
        MJ = mj;

        MJ.allMusic.gameMusic.setLooping(true);
        MJ.allMusic.playMusic(MJ.allMusic.gameMusic);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,MyJungleInfo.V_WIDTH,MyJungleInfo.V_HEIGHT);
        camera.position.set(MyJungleInfo.V_WIDTH/2,MyJungleInfo.V_HEIGHT/2,0);
        cameraX = camera.position.x;
        cameraY = camera.position.y;

        cellMap = new CellMap(this);

        allUnits = new ArrayList<AllUnit>();
        allBullets = new ArrayList<AllBullet>();

        attackEffets = new ArrayList<GameAttackEffet>();
        gamefoodIcons = new ArrayList<GameFoodIcon>();

        touchP = new Vector3();

        background = new Texture("gameImage/gameBackground.jpg");

        unitAnimation = new UnitAnimation(this);
        attackAnimation = new AttackAnimation(this);

        gameStage = new GameStage(new StretchViewport(1280,720),this);

        foodNumLabel  = new Label("ttt",gameStage.gameSkin);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new GestureDetector(new MyGestureListener()));
        multiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(multiplexer);


        descending = new Descending();
        ascending = new Ascending();

        Collections.sort(allUnits,descending);

        PlayerCannonInfo playerCannon = new PlayerCannonInfo(200,400, UserInfo.userCastleInfo[0],MJ.castleInfo.castleDamage,MJ.castleInfo.castleCooltime, UserInfo.userCastleInfo[2], UserInfo.userCastleInfo[3],1);
        playerCannon.type = "monkey";
        playerCannon.unitNumber = MJ.convert.convertPlayerUnitTypeToNum(playerCannon.type);
        allUnits.add(playerCannon);


        EnemyCannonInfo enemyCannon = new EnemyCannonInfo(2400,400,MJ.castleInfo.castleDamage,MJ.castleInfo.castleCooltime,MJ.stageInfo.castleLevel,0);
        enemyCannon.type = "hunter";
        enemyCannon.unitNumber = MJ.convert.convertEnemyUnitTypeToNum(enemyCannon.type);
        allUnits.add(enemyCannon);
    }

    @Override
    public void show()
    {

    }

    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(1, 1, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MJ.batch.setProjectionMatrix(camera.combined);

        fpsTime += Gdx.graphics.getDeltaTime();
        playerFood += 0.017*5;
        gameStage.foodLabel.setText(playerFood+"");
        enemyFood += 0.017*MJ.stageInfo.foodLevel/100f;
        if(enemyFood>=1000){
            if(gameResult==-1) spawnEnemy();
        }

        moveUpdate();
        if(waterGauge>0) {
            foodCooltime += 1.5;
            waterGauge--;
        } else{
            foodCooltime ++;
        }
        gameStage.waterBarImage.setWidth(128*(waterGauge/1000f));
        if(foodCooltime>200/(int)Math.pow(1.1, UserInfo.userCastleInfo[5])){
            foodCooltime = MathUtils.random((200/(int)Math.pow(1.1, UserInfo.userCastleInfo[5]))/2);
            gamefoodIcons.add(new GameFoodIcon(MathUtils.random(MyJungleInfo.roomSize*3,MyJungleInfo.roomSize*10),MathUtils.random(MyJungleInfo.roomSize*4,MyJungleInfo.roomSize*5),"apple"));
        }
        if (Gdx.input.isTouched()) {
            touchP.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchP);
        }
        gameStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        gameStage.draw();
    }

    public void addBullet(String type,Vector2 v1, Vector2 v2, int obId,int damage, int team)
    {
        if(team == 1){
            if(type == "cannon") allBullets.add(new PlayerStoneInfo(v1.x,v1.y,v2.x,v2.y,obId,damage,team));
            if(type == "monkey") allBullets.add(new PlayerStoneInfo(v1.x,v1.y,v2.x,v2.y,obId,damage,team));
            if(type == "alpaca") allBullets.add(new PlayerSpitInfo(v1.x,v1.y,v2.x,v2.y,obId,damage,team));
        } else{
            if(type == "cannon") allBullets.add(new EnemyArrowInfo(v1.x,v1.y,v2.x,v2.y,obId,damage,team));
            if(type == "hunter") allBullets.add(new EnemyArrowInfo(v1.x,v1.y,v2.x,v2.y,obId,damage,team));
            if(type == "gunman") allBullets.add(new EnemyGunbulletInfo(v1.x,v1.y,v2.x,v2.y,obId,damage,team));
        }
    }

    public void addAttackEffet(int attackAnimationNum,boolean right,Vector2 v){
        attackEffets.add(new GameAttackEffet(v.x,v.y,attackAnimationNum,right));
    }

    public void moveUpdate()
    {
        Collections.sort(allUnits,descending);
        MJ.batch.begin();
        MJ.batch.draw(background,0,0);



        Iterator<GameFoodIcon> foodIter = gamefoodIcons.iterator();
        while(foodIter.hasNext()) {
            GameFoodIcon gameFoodIcon = foodIter.next();//
            if(gameFoodIcon.type == "apple") {
                if(tapVecter2.x>gameFoodIcon.x-gameFoodIcon.imageWidth*2&& tapVecter2.x<gameFoodIcon.x+gameFoodIcon.imageWidth*2&& tapVecter2.y>gameFoodIcon.y-gameFoodIcon.imageHeight*2&& tapVecter2.y<gameFoodIcon.y+gameFoodIcon.imageHeight*2){
                    if(!gameFoodIcon.click) {
                        MJ.allEffectSound.playSound(MJ.allEffectSound.foodSound);
                        gameFoodIcon.click = true;
                        playerFood += 50*Math.pow(1.1, UserInfo.userCastleInfo[4]);
                        tapVecter2.set(0, 0);
                    }
                }
                if(gameFoodIcon.click) {
                    gameStage.font.setColor(Color.RED);
                    gameStage.font.draw(MJ.batch, "+" + (int)(50*Math.pow(1.1, UserInfo.userCastleInfo[4])), gameFoodIcon.x - gameFoodIcon.imageWidth / 2, gameFoodIcon.y + gameFoodIcon.removeCount * 2);
                    gameFoodIcon.removeCount++;
                    if (gameFoodIcon.removeCount > 30) {
                        foodIter.remove();
                    }
                } else{
                    MJ.batch.draw(foodAppleTexture, gameFoodIcon.x - gameFoodIcon.imageWidth / 2, gameFoodIcon.y - gameFoodIcon.imageHeight / 2);
                }
            } else if(gameFoodIcon.type == "bucket"){
                MJ.batch.draw(foodBucketPourTexture, gameFoodIcon.x - gameFoodIcon.imageWidth / 2, gameFoodIcon.y - gameFoodIcon.imageHeight / 2);
                gameFoodIcon.removeCount++;
                if (gameFoodIcon.removeCount > 15) {
                    foodIter.remove();
                }
            }
        }
        playerCastle = unitAnimation.createChangeTextureFrame(playerCastleHp, unitAnimation.playerCastle);
        enemyCastle = unitAnimation.createChangeTextureFrame(enemyCastleHp, unitAnimation.enemyCastle);
        MJ.batch.draw(playerCastle,playerCastleX,MyJungleInfo.roomSize);
        MJ.batch.draw(enemyCastle,(MyJungleInfo.WORLD_WIDTH-MyJungleInfo.roomSize*4)+enemyCastleX,MyJungleInfo.roomSize);
        playerCastleX = 0;
        enemyCastleX = 0;


        Iterator<AllUnit> unitIter = allUnits.iterator();
        while(unitIter.hasNext()) {
            AllUnit units = unitIter.next();//
            TextureRegion Frame = unitAnimation.updateUnitFrame(units);
            if (units.hp <= 0) {
                units.die = true;
                if(units.dieState==0){
                    if(units.team == 1){
                        MJ.allUnitSound.playerUnitSound[units.unitNumber].playSound(MJ.allUnitSound.playerUnitSound[units.unitNumber].dieSound);
                    } else{
                        MJ.allUnitSound.enemyUnitSound[units.unitNumber].playSound(MJ.allUnitSound.enemyUnitSound[units.unitNumber].dieSound);
                    }
                }
                if(units.dieState>=50){
                    unitIter.remove();
                } else {
                    units.dieStateUpdate();
                    cellMap.addDieCellMap(units);
                    MJ.batch.draw(Frame, units.x-units.imageWidth/2, units.y-units.radius);
                }
            } else{
                if (units.team == 1) {
                    cellMap.addPlayerCellMap(units);
                    if(gameResult==0){
                        units.hp = -1;
                    }
                } else{
                    cellMap.addEnemyCellMap(units);
                    if(gameResult==1){
                        units.hp = -1;
                    }
                }
                enemyUnitsNum++;
                if(units.injured){
                    if(units.right){
                        MJ.batch.draw(Frame, units.x-units.imageWidth/2 - 5, units.y-units.radius);
                    } else{
                        MJ.batch.draw(Frame, units.x-units.imageWidth/2 + 5, units.y-units.radius);
                    }
                    units.injured = false;
                } else{
                    MJ.batch.draw(Frame, units.x-units.imageWidth/2, units.y-units.radius);
                }
            }
        }

        Iterator<AllBullet> bulletIter = allBullets.iterator();
        while(bulletIter.hasNext()){
            AllBullet units = bulletIter.next();//
            TextureRegion Frame = unitAnimation.updateBulletFrame(units);
            if(units.hp<=0)
            {
                bulletIter.remove();
            }
            else if(units.team == 1){
                cellMap.addPlayerBulletCellMap(units);
            } else{
                cellMap.addEnemyBulletCellMap(units);
            }
            if(units.right){
                MJ.batch.draw(Frame, units.x, units.y+units.bulletHeight,units.imageWidth,units.imageHeight/2,units.imageWidth,units.imageHeight,1,1,units.rotate);
            }else{
                MJ.batch.draw(Frame, units.x, units.y+units.bulletHeight,0,units.imageHeight/2,units.imageWidth,units.imageHeight,1,1,units.rotate);
            }
        }

        Iterator<GameAttackEffet> attackEffetIter = attackEffets.iterator();
        while(attackEffetIter.hasNext()){
            GameAttackEffet gameAttackEffet = attackEffetIter.next();
            TextureRegion Frame = attackAnimation.updateAttackFrame(gameAttackEffet);
            if(gameAttackEffet.removeCount<=0)
            {
                attackEffetIter.remove();
            }
            gameAttackEffet.removeCount --;
            MJ.batch.draw(Frame, gameAttackEffet.x-gameAttackEffet.imageWidth/2,gameAttackEffet.y);
        }



        MJ.batch.end();
        allUnits.clear();
        allBullets.clear();
        cellMap.move();
        playerUnitsNum = 0;
        enemyUnitsNum = 0;
        unitAnimation.updateStatetime();
        attackAnimation.updateStatetime();
        for(int i=cellMap.playerCells.length-1;i>=0;i--)
        {
            for(AllUnit units : cellMap.dieCells[i]) {
                allUnits.add(units);
            }
            for(AllUnit units : cellMap.playerCells[i]) {
                allUnits.add(units);
            }
            for(AllUnit units : cellMap.enemyCells[i]) {
                allUnits.add(units);
            }
            for (AllBullet units : cellMap.playerBulletCells[i]) {
                allBullets.add(units);
            }
            for (AllBullet units : cellMap.enemyBulletCells[i]) {
                allBullets.add(units);
            }
        }
        cellMap.clearCells();
        gameStage.render();
        gameStage.act();
        gameStage.draw();






        fps++;
        if(fpsTime>=1)
        {
            System.out.println("FPS : " + fps);
            fps = 0;
            fpsTime = 0;
        }
    }

    public void gameWin()
    {
        int gold = 100;
        gold *= Math.pow(1.5,MJ.stageInfo.stage);
        UserInfo.gold += gold;
        if(UserInfo.clearStage <MJ.stageInfo.stage){
            UserInfo.clearStage = MJ.stageInfo.stage;
        }
        GameResultDialog gameResultDialog = new GameResultDialog("게임 승리",gameStage.gameSkin,gameStage);
        if(MJ.stageInfo.stage==20){
            gameResultDialog.text("최종 스테이지 클리어!\n플레이 해주셔서 감사합니다!\n다음 업데이트를 기대해주세요!\n곧 업데이트 하겠습니다!");
            gameResultDialog.button("승리","EXIT");
        }else {
            gameResultDialog.text("스테이지 클리어!\n\n" + gold + " 골드 획득!");
            gameResultDialog.button("승리", "EXIT");
        }
        gameResultDialog.setVisible(true);
        gameStage.addActor(gameResultDialog);
        pause = true;
    }

    public void gameOver()
    {
        int gold = 100;
        gold *= Math.pow(1.4,MJ.stageInfo.stage);
        gold = (int)(gold*(1f-((float)enemyCastleHp/(MJ.castleInfo.castleHp*(int)Math.pow(1.1,MJ.stageInfo.castleLevel)))));
        UserInfo.gold += gold;
        GameResultDialog gameResultDialog = new GameResultDialog("게임 패배",gameStage.gameSkin,gameStage);
        gameResultDialog.text("스테이지 실패!\n\n" + gold + " 골드 획득!");
        gameResultDialog.button("패배","EXIT");
        gameResultDialog.setVisible(true);
        gameStage.addActor(gameResultDialog);
        pause = true;
    }

    public AllUnit spawn(int i) throws CloneNotSupportedException {
        AllUnit units = (AllUnit) MJ.userUnitSettingInfo.unitInfo[i].clone();
        units.hp *= Math.pow(1.1,MJ.userUnitSettingInfo.unitStat[i].stat[0]);
        units.damage *= Math.pow(1.1,MJ.userUnitSettingInfo.unitStat[i].stat[1]);
        units.speed *= Math.pow(1.1,MJ.userUnitSettingInfo.unitStat[i].stat[2]);
        units.attackCooltimeMax /=Math.pow(1.05,MJ.userUnitSettingInfo.unitStat[i].stat[3]);
        units.attackStateMax /=Math.pow(1.05,MJ.userUnitSettingInfo.unitStat[i].stat[3]);
        units.setLocationY(touchP.y + (float)Math.random());
        units.setLocationX((float)(MyJungleInfo.roomSize*2+units.radius+((units.y-1.5*MyJungleInfo.roomSize)/2) + (float)Math.random()));
        return units;
    }

    public void spawnEnemy(){
        AllUnit unit;
        int random = MathUtils.random(MJ.stageInfo.enemyInfos.size());
        for(int i=0;i<MJ.stageInfo.enemyInfos.size();i++){
            if(i == random){
                if (MJ.stageInfo.enemyInfos.get(i).type.equals("axeman")) unit = new EnemyAxemanInfo(MyJungleInfo.WORLD_WIDTH-MyJungleInfo.roomSize*2 + (float)Math.random(), MathUtils.random(MyJungleInfo.roomSize*1.5f,MyJungleInfo.roomSize*3.5f), 0);
                else if (MJ.stageInfo.enemyInfos.get(i).type.equals("hunter")) unit = new EnemyHunterInfo(MyJungleInfo.WORLD_WIDTH-MyJungleInfo.roomSize*2 + (float)Math.random(), MathUtils.random(MyJungleInfo.roomSize*1.5f,MyJungleInfo.roomSize*3.5f), 0);
                else if (MJ.stageInfo.enemyInfos.get(i).type.equals("sawman")) unit = new EnemySawmanInfo(MyJungleInfo.WORLD_WIDTH-MyJungleInfo.roomSize*2 + (float)Math.random(), MathUtils.random(MyJungleInfo.roomSize*1.5f,MyJungleInfo.roomSize*3.5f), 0);
                else if (MJ.stageInfo.enemyInfos.get(i).type.equals("gunman")) unit = new EnemyGunmanInfo(MyJungleInfo.WORLD_WIDTH-MyJungleInfo.roomSize*2 + (float)Math.random(), MathUtils.random(MyJungleInfo.roomSize*1.5f,MyJungleInfo.roomSize*3.5f), 0);
                else if (MJ.stageInfo.enemyInfos.get(i).type.equals("clubman")) unit = new EnemyClubmanInfo(MyJungleInfo.WORLD_WIDTH-MyJungleInfo.roomSize*2 + (float)Math.random(), MathUtils.random(MyJungleInfo.roomSize*1.5f,MyJungleInfo.roomSize*3.5f), 0);
                else if (MJ.stageInfo.enemyInfos.get(i).type.equals("bulldozer")) unit = new EnemyBulldozerInfo(MyJungleInfo.WORLD_WIDTH-MyJungleInfo.roomSize*2 + (float)Math.random(), MathUtils.random(MyJungleInfo.roomSize*1.5f,MyJungleInfo.roomSize*3.5f), 0);
                else return;
                unit.hp *= Math.pow(1.1,MJ.stageInfo.enemyInfos.get(i).level);
                unit.damage *= Math.pow(1.1,MJ.stageInfo.enemyInfos.get(i).level);
                unit.speed *= Math.pow(1.1,MJ.stageInfo.enemyInfos.get(i).level);
                unit.attackCooltimeMax /= Math.pow(1.1,MJ.stageInfo.enemyInfos.get(i).level);
                unit.attackStateMax /= Math.pow(1.1,MJ.stageInfo.enemyInfos.get(i).level);
                enemyFood -= unit.food;
                if(enemyCastleHp<(MJ.castleInfo.castleHp*(int)Math.pow(1.1,MJ.stageInfo.castleLevel))/2){
                    enemyFood += MathUtils.random(unit.food);
                }
                MJ.allUnitSound.playSound(MJ.allUnitSound.enemyUnitSound[unit.unitNumber].spawnSound);
                allUnits.add(unit);
            }
        }
    }


    public class MyGestureListener implements GestureListener{

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {

            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            if(!pause) {
                if (touchP.y > MyJungleInfo.roomSize * 1.5f && touchP.y < MyJungleInfo.roomSize * 3.5f) {
                    for (int i = 0; i < 6; i++) {
                        if (gameStage.unitButton[i].isChecked()) {
                            try {
                                if (spawn(i) != null) {
                                    if(gameResult==-1) {
                                        AllUnit unit = spawn(i);
                                        if (!(playerFood - unit.food < 0)) {
                                            playerFood -= unit.food;
                                            allUnits.add(unit);
                                            MJ.allUnitSound.playerUnitSound[unit.unitNumber].playSound(MJ.allUnitSound.playerUnitSound[unit.unitNumber].spawnSound);
                                        }
                                    }
                                }
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                tapVecter2.x = touchP.x;
                tapVecter2.y = touchP.y;
            }
            return false;
        }

        @Override
        public boolean longPress(float x, float y) {

            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {

            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            if(!pause) {
                float positionX = camera.position.x - camera.viewportWidth / Gdx.graphics.getWidth() * deltaX;
                float positionY = camera.position.y + camera.viewportWidth / Gdx.graphics.getHeight() * deltaY;
                if (positionX - camera.viewportWidth / 2 < 0) {
                    positionX = camera.viewportWidth / 2;
                }
                if (positionX + camera.viewportWidth / 2 > MyJungleInfo.WORLD_WIDTH) {
                    positionX = MyJungleInfo.WORLD_WIDTH - camera.viewportWidth / 2;
                }
                if (positionY - camera.viewportHeight / 2 < 0) {
                    positionY = camera.viewportHeight / 2;
                }
                if (positionY + camera.viewportHeight / 2 > MyJungleInfo.WORLD_HEIGHT) {
                    positionY = MyJungleInfo.WORLD_HEIGHT - camera.viewportHeight / 2;
                }
                camera.position.set(positionX, positionY, 0);
                cameraX = camera.position.x;
                cameraY = camera.position.y;
            }
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom (float originalDistance, float currentDistance){
            return false;
        }

        @Override
        public boolean pinch (Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4){
            return false;
        }

        @Override
        public void pinchStop () {
        }
    }
    public void shakeCastle(int team){
        if(team == 1){
            playerCastleX = -10;
        } else {
            enemyCastleX = 10;
        }
    }
    public void setPlayerCastleHp(int hp){
        playerCastleHp = hp;
        if(playerCastleHp<=0&&gameResult==-1){
            gameResult = 0;
            gameOver();
            MJ.allEffectSound.playSound(MJ.allEffectSound.playerCastleExplosionSound);
        }
    }
    public void setEnemyCastleHp(int hp){
        enemyCastleHp = hp;
        if(enemyCastleHp<=0&&gameResult==-1){
            gameResult = 1;
            gameWin();
            MJ.allEffectSound.playSound(MJ.allEffectSound.enemyCastleExplosionSound);
        }
        if(enemyCastleHp<=1500&&deathMatch==false) {
            deathMatch = true;
            enemyFood += MJ.stageInfo.stage*50;
        }
    }

    @Override
    public void dispose()
    {
        foodAppleTexture.dispose();
        foodBucketPourTexture.dispose();
        background.dispose();

        musicTexture.dispose();
        soundTexture.dispose();

        allUnits.clear();
        allBullets.clear();

        attackEffets.clear();
        gamefoodIcons.clear();

        cellMap.clearCells();

        unitAnimation.dispose();
        attackAnimation.dispose();

        gameStage.dispose();
        gameStage.allDispose();

        MJ.reset();
    }

    class Descending implements Comparator<AllUnit> {
        @Override
        public int compare(AllUnit units1, AllUnit units2) {
            Integer integer1 = (int)units1.y;
            Integer integer2 = (int)units2.y;
            return integer2.compareTo(integer1);
        }
    }

    class Ascending implements Comparator<AllUnit> {

        @Override
        public int compare(AllUnit units1, AllUnit units2) {
            Integer integer1 = (int) units1.y;
            Integer integer2 = (int) units2.y;
            return integer1.compareTo(integer2);
        }
    }
}