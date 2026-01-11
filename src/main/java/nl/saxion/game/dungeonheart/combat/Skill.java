package nl.saxion.game.dungeonheart.combat;

public interface Skill {
    String getName();
    void use(Unit user, Unit target);
}
