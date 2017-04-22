package com.myjungle.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.myjungle.game.screen.GameScreen;
import com.myjungle.game.ui.game.GameAttackEffet;

/**
 * Created by LeeWoochan on 2017-02-21.
 */

public class AttackAnimation {
    GameScreen GAMESCREEN;

    public Texture attackTexture[];
    public CreateAttackAnimation attackAnimation[];

    float stateTime;

    public AttackAnimation(GameScreen gameScreen){
        GAMESCREEN = gameScreen;

        attackTexture = new Texture[3];
        attackAnimation = new CreateAttackAnimation[3];
        for(int i = 0;i<attackAnimation.length;i++){
            attackTexture[i] = new Texture("attacks/" + i +".png");
            attackAnimation[i] = new CreateAttackAnimation(attackTexture[i]);
        }
    }

    public TextureRegion updateAttackFrame(GameAttackEffet gameAttackEffet)
    {
        return createAnimationFrame(gameAttackEffet,attackAnimation[gameAttackEffet.attackAnimationNum]);
    }

    public TextureRegion createAnimationFrame(GameAttackEffet gameAttackEffet,CreateAttackAnimation CAA){
        if(gameAttackEffet.right) return CAA.right.getKeyFrame(gameAttackEffet.removeCount/60f, true);
        else return CAA.left.getKeyFrame(gameAttackEffet.removeCount/60f, true);
    }

    public void updateStatetime()
    {
        stateTime += 0.017;
    }

    public void dispose()
    {
        for(int i=0;i<attackAnimation.length;i++){
            attackTexture[i].dispose();
            attackAnimation[i].dispose();
        }
    }
}
