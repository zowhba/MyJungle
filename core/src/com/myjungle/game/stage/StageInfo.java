package com.myjungle.game.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by LeeWoochan on 2017-02-08.
 */

public class StageInfo {

    public ArrayList<EnemyInfo> enemyInfos = new ArrayList<EnemyInfo>();
    public int castleLevel = 0;
    public int foodLevel = 100;
    public int stage;

    public StageInfo()
    {

    }
    public void setStage(int x)
    {
        try {
            stage = x;
            FileHandle handle = Gdx.files.internal("stages/stageInfo.txt");
            BufferedReader reader = handle.reader((int)handle.length());
            String line;
            while ((line = reader.readLine())!=null){
                if(line.indexOf("stage" + x)>=0)
                {
                    break;
                }
            }
            line = reader.readLine();
            castleLevel = Integer.parseInt(line);
            line = reader.readLine();
            foodLevel = Integer.parseInt(line);
            while((line = reader.readLine())!=null){
                if(line.indexOf("stageEnd")>=0) {
                    break;
                }
                String type = line;
                line = reader.readLine();
                enemyInfos.add(new EnemyInfo(type,Integer.parseInt(line)));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void reset()
    {
        enemyInfos.clear();
        castleLevel = 0;
        foodLevel = 100;
        stage = 0;
    }
}
