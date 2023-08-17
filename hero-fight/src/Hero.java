public class Hero {
    private int hp = 500;
    private String name;
    private AttackType attackType;

    public Hero(String name, AttackType attackType) {
        setName(name);
        setAttackType(attackType);
    }

    public void attack(Hero attacked) {
        attackType.attack(this, attacked);
    }

    public void damage(int damage) {
        setHp(getHp() - damage);
        System.out.printf("%s受到了%d點傷害\n", getName(), damage);
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }
}
