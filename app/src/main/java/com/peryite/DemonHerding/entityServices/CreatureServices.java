package com.peryite.DemonHerding.entityServices;

import com.peryite.DemonHerding.entity.Creature;

public interface CreatureServices {
    int calcDemonAmount(Creature pitLord, Creature sacrifice);

    int calcSpareAmount(Creature sacrifice, Creature demon);

    int calcNeedAmount(int amount, int spare);
}
