package nl.saxion.game.dungeonheart.combat;

public class BasicAttack implements Skill {

    @Override
    public String getName() {
        return "Basic Attack";
    }

    @Override
    public void use(Unit user, Unit target) {
        target.takeDamage(user.getAttackPower());
        System.out.println(user.getName() + " uses Basic Attack on " + target.getName());
    }
}
