package nl.saxion.game.dungeonheart.combat;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Unit {
    private final List<Skill> skills = new ArrayList<>();

    public Hero(String name, int health, int attackPower) {
        super(name, health, attackPower);
        skills.add(new BasicAttack());
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public List<Skill> getSkills() {
        return skills;
    }
}
